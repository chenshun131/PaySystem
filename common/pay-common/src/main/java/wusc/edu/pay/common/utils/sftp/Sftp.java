package wusc.edu.pay.common.utils.sftp;

import com.jcraft.jsch.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wusc.edu.pay.common.utils.FileUtils;
import wusc.edu.pay.common.utils.string.StringUtil;

import java.io.*;
import java.util.Properties;
import java.util.Vector;

/**
 * ClassName: Sftp 连接类包括上传下载<br/>
 * Function: <br/>
 * date: 2014-2-24 上午10:28:10 <br/>
 *
 * @author laich.<br
       *       />
 * @修改: Wushuicheng, 2014-04-24.
 */
public class Sftp {

    private static final Logger logger = LoggerFactory.getLogger(Sftp.class);

    /**
     * 连接sftp服务器
     *
     * @param host
     *         主机
     * @param port
     *         端口
     * @param username
     *         用户名
     * @param password
     *         密码
     * @return
     */
    public ChannelSftp connect(String host, int port, String username, String password) {
        ChannelSftp sftp = null;
        try {
            JSch jsch = new JSch();
//			jsch.getSession(username, host, port);
            Session sshSession = jsch.getSession(username, host, port);
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
        } catch (Exception e) {
            logger.error("sftp connect com.chenshun.test.exception:", e);
        }
        return sftp;
    }

    /**
     * 上传文件
     *
     * @param directory
     *         上传的目录
     * @param uploadFile
     *         要上传的文件
     * @param channel
     */
    public static void upload(String directory, String uploadFile, ChannelSftp channel) {
        try {
            creatDir(directory, channel);
            channel.cd(directory);
            File file = new File(uploadFile);
            channel.put(new FileInputStream(file), file.getName());
            sftpClose(channel);
        } catch (Exception e) {
            logger.error("sftp upload com.chenshun.test.exception:", e);
        } finally {
            if (channel != null) {
                sftpClose(channel);
            }
        }
    }

    /**
     * 下载文件
     *
     * @param directory
     *         下载目录
     * @param downloadFilePath
     *         下载的文件
     * @param saveFile
     *         存在本地的路径
     * @param channel
     */
    public static void download(String directory, String downloadFilePath, String saveFile, ChannelSftp channel) {
        try {
            channel.cd(directory);
            File file = new File(saveFile);
            channel.get(downloadFilePath, new FileOutputStream(file));
        } catch (Exception e) {
            logger.error("sftp download com.chenshun.test.exception:", e);
        } finally {
            if (channel != null) {
                sftpClose(channel);
            }
        }
    }

    /**
     * 下载文件
     *
     * @param directory
     *         下载目录
     * @param downloadFile
     *         下载的文件
     * @param saveFile
     *         存在本地的路径
     * @param channelSftp
     */
    public static String downloadGetString(String directory, String downloadFile, String saveFile, ChannelSftp
            channelSftp) {
        try {
            channelSftp.cd(directory);
            File file = new File(saveFile);
            channelSftp.get(downloadFile, new FileOutputStream(file));
            return readFileByLines(file.getPath());
        } catch (Exception e) {
            logger.error("sftp downloadGetString excetpin:", e);
        }
        return null;
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String readFileByLines(String fileName) {
        StringBuilder sb = new StringBuilder();
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            // 以行为单位读取文件内容，一次读一整行
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                sb.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error("BufferedReader close com.chenshun.test.exception:", e);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 删除文件
     *
     * @param directory
     *         要删除文件所在目录
     * @param deleteFile
     *         要删除的文件
     * @param sftp
     */
    public void delete(String directory, String deleteFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 列出目录下的文件
     *
     * @param directory
     *         要列出的目录
     * @param sftp
     * @return
     * @throws SftpException
     */
    public Vector listFiles(String directory, ChannelSftp sftp) throws SftpException {
        return sftp.ls(directory);
    }

    /**
     * 根据传入的目录创建文件夹
     *
     * @param directory
     * @param sftp
     * @throws SftpException
     */
    public static void creatDir(String directory, ChannelSftp sftp) throws SftpException {
        // ChannelSftp 的 mkdir 方法只能创建相对于当前的远程目录，直接创建多级目录会抛出异常
        String[] dirArr = directory.split("/");
        StringBuilder tempStr = new StringBuilder("");
        for (int i = 1; i < dirArr.length; i++) {
            // 舍弃空路径
            if (!StringUtil.isEmpty(dirArr[i])) {
                tempStr.append("/").append(dirArr[i]);
                try {
                    sftp.cd(tempStr.toString());
                } catch (SftpException e) {
                    sftp.mkdir(tempStr.toString());
                }
            }
        }
    }

    /**
     * sftpClose:关闭Sftp <br/>
     *
     * @param channel
     */
    public static void sftpClose(ChannelSftp channel) {
        try {
            channel.getSession().disconnect();
        } catch (JSchException e) {
            logger.error("sftp disconnect com.chenshun.test.exception:", e);
        }
    }

    /***
     * 连接SFTP服务器，根据文件路径读取文件文本内容.<br/>
     *
     * @param dataFilePath
     *            SFTP保存的文件全路径
     * @return 文件内容.
     */
    public static String getFileContentFormSFTP(final ChannelSftp channelSftp, final String dataFilePath) {
        String property = System.getProperty("user.dir") + File.separator + "temp/";
        FileUtils.isDir(property);

        // 文件路径
        String directory = dataFilePath.substring(0, dataFilePath.lastIndexOf("/"));

        // 文件名称
        String downloadFile = dataFilePath.substring(dataFilePath.lastIndexOf("/") + 1);

        // 保存文件路径
        String saveFile = property + "/" + downloadFile;
        logger.info("==>从SFTP获取文件内容，源文件路径[" + dataFilePath + "], 保存本地的临时文件路径[" + saveFile + "]");
        return downloadGetString(directory, downloadFile, saveFile, channelSftp);
    }

    /**
     * 从SFTP服务器上下载文件
     *
     * @return
     */
    public static File downFileFromSFTP(ChannelSftp channelSftp, final String filePath) {
        // 创建临时目录，用来存放下载的文件
        StringBuilder tempFilePath = new StringBuilder(System.getProperty("user.dir")).append(File.separator).append
                ("temp");
        FileUtils.isDir(tempFilePath.toString());
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        String tempPath = filePath.substring(0, filePath.lastIndexOf("/") + 1);

        // 创建临时返回文件
        String saveFile = tempFilePath + "/" + fileName;
        File returnFile = new File(saveFile);
        try {
            download(tempPath, fileName, saveFile, channelSftp);
        } catch (Exception e) {
            logger.error("==>对账文件下载失败：", e);
        } finally {
            if (channelSftp != null) {
                sftpClose(channelSftp);
            }
        }
        return returnFile;
    }


    // 测试例子
    public static void main(String[] args) {
        Sftp sf = new Sftp();
        String host = "192.168.88.40";
        int port = 3210;
        String username = "gwpayfast";
        String password = "gzzyzz.com";
        String directory = "/home/gwpayfast/";

        String downloadFile = "Result.txt";
        String saveFile = "F:\\123.txt";

        String uploadFile = "E:\\PINGANBANK-NET-B2C-GZ20140523clear.txt";
        // String deleteFile = "delete.txt";
        ChannelSftp sftp = sf.connect(host, port, username, password);
        upload(directory, uploadFile, sftp);
//		sf.download(directory, downloadFile, saveFile, sftp);
        // sf.delete(directory, deleteFile, sftp);
        try {
//			sf.creatDir(directory, sftp);
            // sftp.cd(directory);
            // System.out.println("finished");
//			sf.sftpClose(sftp);
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            if (sf != null) {
                sftpClose(sftp);
            }
        }
    }

}
