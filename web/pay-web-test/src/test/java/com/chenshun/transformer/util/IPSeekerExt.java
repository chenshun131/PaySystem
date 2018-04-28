package com.chenshun.transformer.util;

import java.io.File;
import java.io.IOException;

import com.chenshun.transformer.common.GlobalConstants;
import com.chenshun.transformer.util.ip.IPSeeker;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;

/**
 * Ip解析工具类
 * 
 * @author ibf
 *
 */
public class IPSeekerExt extends IPSeeker {
	private static final Logger logger = Logger.getLogger(IPSeekerExt.class);
	/**
	 * 保存纯真ip库的ip文件路径<br/>
	 * 如果在集群上运行jar文件，需要将该参数改成具体的linux环境路径或者hdfs上的环境路径，如果是linux环境路径，要求所有机器该文件都存在，
	 * 如果是hdfs路径，要求给定全称<br/>
	 * TODO： 自行修改
	 */
//	private static final String ipFilePath = "ip/qqwry.dat";
	// HDFS路径
	 private static final String ipFilePath =
	 "hdfs://hadoop-senior01:8020/ips/qqwry.dat";

	/**
	 * 用于单例对象的属性
	 */
	private static IPSeekerExt single = null;

	/**
	 * 获取实际环境路径
	 * 
	 * @return
	 */
	private static String getActualIpFilePath() {
		String actualIpFilePath = "ip/qqwry.dat";
		if (StringUtils.isNotBlank(ipFilePath)) {
			if (ipFilePath.toLowerCase().startsWith("hdfs:")) {
				// 使用的是hdfs路径
				try {
					final String deleteDirPath = "/tmp/bf_transformer_" + System.currentTimeMillis();
					actualIpFilePath = deleteDirPath + "/qqwry.dat";
					Path srcPath = new Path(ipFilePath);
					Path destPath = new Path(actualIpFilePath);
					FileSystem fs = FileSystem.get(new Configuration());
					// 复制数据到本地
					fs.copyToLocalFile(srcPath, destPath);
					// 复制成功，当jvm退出的时候，删除文件
					Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

						@Override
						public void run() {
							try {
								// 关闭资源
								single.close();
							} catch (IOException e) {
								// nothings
							} finally {
								// 设置为空
								single = null;
								// 删除文件，如果存在，删除
								LocalFileDeleteUtil.deleteLocalFile(deleteDirPath);
							}

						}
					}));
				} catch (Exception e) {
					logger.warn("读取数据异常", e);
					actualIpFilePath = "ip/qqwry.dat";
				}
			} else {
				// 使用普通路径
				actualIpFilePath = ipFilePath;
			}
		}

		logger.debug("实际IP文件解决路径为:" + actualIpFilePath);

		// 返回最终结果
		return actualIpFilePath;
	}

	/**
	 * 构造函数，private修饰，单例模式
	 * 
	 * @param ipFilePath
	 */
	private IPSeekerExt(String ipFilePath) {
		super(ipFilePath);
	}

	/**
	 * 获取ip解析对象
	 * 
	 * @return
	 */
	public static IPSeekerExt getInstance() {
		if (single == null) {
			synchronized (IPSeekerExt.class) {
				if (single == null) {
					single = new IPSeekerExt(getActualIpFilePath());
				}
			}
		}
		return single;
	}

	/**
	 * 解析ip地址<br/>
	 * 如果解析正常，返回具体的值<br/>
	 * 如果无法解析，返回unknown<br/>
	 * 如果解析过程中出现异常信息，直接返回null
	 * 
	 * @param ip
	 *            需要进行解析的ip地址库
	 * @return
	 */
	public RegionInfo analysisIp(String ip) {
		RegionInfo info = new RegionInfo();
		// 判断参数是否为空
		if (ip != null && !"".equals(ip.trim())) {
			// ip不为空
			String country = super.getCountry(ip);
			if (country == null || country.isEmpty()) {
				// 数据库中没有找到ip，直接返回unknown
				return info;
			}

			if (!ERROR_RESULT.equals(country)) {
				// 能够正常解析出国家名称
				if ("局域网".equals(country) || "IANA".equals(country) || country.trim().endsWith("CZ88")) {
					// 都可以认为是本地, 没有找到ip返回的值是空
					info.setCountry("中国");
					info.setProvince("上海市");
				} else {
					int length = country.length();
					int index = country.indexOf("省");
					if (index > 0) {
						// 表示是国家的某个省份
						info.setCountry("中国");
						info.setProvince(country.substring(0, Math.min(index + 1, length)));
						int index2 = country.indexOf('市', index);
						if (index2 > 0) {
							info.setCity(country.substring(index + 1, Math.min(index2 + 1, length)));
						}
					} else {
						// 单独的处理, 自治区以及直辖市 特别行政区
						String flag = country.substring(0, 2);
						switch (flag) {
						case "内蒙":
							info.setCountry("中国");
							info.setProvince("内蒙古自治区");
							country = country.substring(3);
							if (country != null && !country.isEmpty()) {
								index = country.indexOf('市');
								if (index > 0) {
									info.setCity(country.substring(0, Math.min(index + 1, country.length())));
								}
								// :TODO 针对是旗、盟之类的不考虑
							}
							break;
						case "广西":
						case "宁夏":
						case "西藏":
						case "新疆":
							info.setCountry("中国");
							info.setProvince(flag);
							country = country.substring(2);
							if (country != null && !country.isEmpty()) {
								index = country.indexOf('市');
								if (index > 0) {
									info.setCity(country.substring(0, Math.min(index + 1, country.length())));
								}
							}
							break;
						case "上海":
						case "北京":
						case "重庆":
						case "天津":
							info.setCountry("中国");
							info.setProvince(flag + "市");
							country = country.substring(3);
							if (country != null && !country.isEmpty()) {
								index = country.indexOf('区');
								if (index > 0) {
									char ch = country.charAt(index - 1);
									if (ch != '小' && ch != '校') {
										info.setCity(country.substring(0, Math.min(index + 1, country.length())));
									}
								}
							}

							if (GlobalConstants.DEFAULT_VALUE.equals(info.getCity())) {
								// 没有区，可能是县
								index = country.indexOf('县');
								if (index > 0) {
									info.setCity(country.substring(0, Math.min(index + 1, country.length())));
								}
							}
							break;
						case "香港":
						case "澳门":
							info.setCountry("中国");
							info.setProvince(flag + "特别行政区");
							break;
						default:
							info.setCountry(country); // 针对其他国家
							break;
						}
					}
				}
			} else {
				// 文件异常，直接返回null
				info = null;
			}
		}
		return info;
	}

	/**
	 * 地域描述信息内部类
	 * 
	 * @author ibf
	 *
	 */
	public static class RegionInfo {
		private String country = GlobalConstants.DEFAULT_VALUE;
		private String province = GlobalConstants.DEFAULT_VALUE;
		private String city = GlobalConstants.DEFAULT_VALUE;

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getProvince() {
			return province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		@Override
		public String toString() {
			return "RegionInfo [country=" + country + ", province=" + province + ", city=" + city + "]";
		}
	}

	/**
	 * 删除本地文件的工具类
	 * 
	 * @author ibf
	 *
	 */
	private static class LocalFileDeleteUtil {
		/**
		 * 删除指定文件
		 * 
		 * @param file
		 */
		public static void deleteLocalFile(String file) {
			// 构建对象
			File localFile = new File(file);
			if (localFile.exists()) {
				// 文件存在，则进行删除操作
				if (localFile.isFile()) {
					// 是本地文件
					deleteFile(localFile);
				} else {
					// 是本地目录
					deleteDirectory(localFile);
				}
			}
		}

		/**
		 * 删除指定文件
		 * 
		 * @param file
		 */
		private static void deleteFile(File file) {
			file.delete();
		}

		/**
		 * 删除指定文件目录，删除规则是：先删除子文件，再删除父文件夹
		 * 
		 * @param file
		 */
		private static void deleteDirectory(File file) {
			File[] childFiles = file.listFiles();
			for (File childFile : childFiles) {
				if (childFile.isFile()) {
					// 删除文件
					deleteFile(childFile);
				} else {
					// 删除目录
					deleteDirectory(childFile);
				}
			}
			// 子文件夹以及文件已经删除完成，开始删除当前文件夹
			file.delete();
		}
	}
}
