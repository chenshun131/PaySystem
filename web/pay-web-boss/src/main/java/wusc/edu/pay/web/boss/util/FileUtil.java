package wusc.edu.pay.web.boss.util;

import com.jcraft.jsch.ChannelSftp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wusc.edu.pay.common.config.PublicConfig;
import wusc.edu.pay.common.utils.FileUtils;
import wusc.edu.pay.common.utils.sftp.Sftp;
import wusc.edu.pay.web.boss.exceptions.BossException;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * @ClassName: FileUtil
 * @Description: 上传文件到sftp服务器
 * @author huangbin
 * @date 2015-3-5 下午1:57:24
 *
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(org.aspectj.util.FileUtil.class);

    private static String SFTP_IP = PublicConfig.SFTP_IP;

    private static int SFTP_PORT = PublicConfig.SFTP_PORT;

    private static String SFTP_USER = PublicConfig.SFTP_USER;

    private static String SFTP_PWD = PublicConfig.SFTP_PWD;

    private static String SFTP_DIR = PublicConfig.SFTP_DIR;

    /**
     * 上传文件到文件服务器
     *
     * @param srcFile
     *         源文件
     * @param bankChannelCode
     *         银行渠道编码
     * @param fileDate
     *         文件日期
     * @param fileType
     *         文件类型 1：银行原始对账文件 ，2：转换后的对账文件
     * @return
     */
    public static String uploadFileToSFTP(File srcFile, String bankChannelCode, Date fileDate, int fileType) {
        if (srcFile == null) {
            // 文件为空，直接返回null值
            logger.error("File is null");
            return null;
        }
        // Context.SYSTEM_CONFIG.get("sftp.dir") + ""
        StringBuilder filePath = new StringBuilder();
        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(fileDate);
        if (fileType == 1) {
            // 银行系统源文件
            filePath.append(SFTP_DIR).append("src/")
                    .append(bankChannelCode).append("/")
                    .append(dateStr.substring(0, 4)).append("/")
                    .append(dateStr.substring(5, 7)).append("/");
        } else if (fileType == 2) {
            // 解析后对账文件
            filePath.append(SFTP_DIR).append("conver/")
                    .append(bankChannelCode).append("/")
                    .append(dateStr.substring(0, 4)).append("/")
                    .append(dateStr.substring(5, 7)).append("/");
        } else if (fileType == 3) {
            // 上传小pos的数字签名-by huangbin
            filePath.append(SFTP_DIR).append("posticket/").append(dateStr).append("/");
        } else {
            // 错误的业务类型
            logger.error("FileType is err");
            // 文件类型异常，返回null
            return null;
        }

        Sftp sf = new Sftp();
        String host = SFTP_IP;
        int port = SFTP_PORT;
        String username = SFTP_USER;
        String password = SFTP_PWD;
        ChannelSftp sftp = null;
        try {
            logger.info("host:" + host);
            logger.info("port:" + port);
            logger.info("username:" + username);
            logger.info("password:" + password);
            sftp = sf.connect(host, port, username, password);
            if (sftp == null) {
                logger.error("文件上传SFTP服务器连接失败");
                return null;
            }
            Sftp.creatDir(filePath.toString(), sftp);
            Sftp.upload(filePath.toString(), srcFile.getPath(), sftp);
            return filePath.append(srcFile.getName()).toString();
        } catch (Exception e) {
            logger.error("文件上传SFTP服务器失败：" + e);
            return null;
        } finally {
            if (sftp != null) {
                Sftp.sftpClose(sftp);
            }
        }
    }

    /**
     * 从SFTP服务器上下载文件
     *
     * @return
     * @throws BossException
     */
    public static File downFileFromSFTP(final String filePath) throws BossException {
        // 创建临时目录，用来存放下载的文件
        StringBuilder tempFilePath = new StringBuilder(System.getProperty("user.dir")).append(File.separator).append("temp");
        FileUtils.isDir(tempFilePath.toString());
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        String tempPath = filePath.substring(0, filePath.lastIndexOf("/") + 1);

        // 创建临时返回文件
        String saveFile = tempFilePath + "/" + fileName;
        File returnFile = null;

        Sftp sftp = new Sftp();
        ChannelSftp channelSftp = null;
        try {
            returnFile = new File(saveFile);
            channelSftp = sftp.connect(SFTP_IP, SFTP_PORT, SFTP_USER, SFTP_PWD);
            Sftp.download(tempPath, fileName, saveFile, channelSftp);
        } catch (Exception e) {
            logger.error("==>对账文件下载失败：", e);
            throw new BossException("下载失败");
        } finally {
            if (channelSftp != null) {
                Sftp.sftpClose(channelSftp);
            }
        }
        return returnFile;
    }

}
