package wusc.edu.pay.core.bank.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author Peter
 * @时间 2013-11-14
 */
public class HttpsRequest {

    private static final Logger logger = LoggerFactory.getLogger(HttpsRequest.class);

    /**
     * 该方法用来发送https请求
     *
     * @param url
     *         请求URL
     * @return 请求返回的数据
     */
    public static String sendHttpsRequest(String url) throws IOException {
        InputStream in = getInputStreamByHttp(url);
        byte b[] = new byte[1024];
        int len = 0;
        //所有读取的内容都使用temp接收
        int temp = 0;

        //当没有读取完时，继续读取
        while ((temp = in.read()) != -1) {
            b[len] = (byte) temp;
            len++;
        }
        in.close();
        logger.info("sendHttpsRequest file end");
        return new String(b, 0, len, "GBK");
    }

    /**
     * 通过HTTPS方式下载对账文件
     *
     * @param filePath
     *         下载文件存放路径
     * @param url
     *         请求的Url
     * @return
     * @throws IOException
     */
    public static boolean sendHttprequestForFileDownd(String filePath, String url) throws IOException {
        boolean flag = false;
        InputStream in = getInputStreamByHttp(url);
        if (in != null) {
            BufferedInputStream inBuff = null;
            BufferedOutputStream outBuff = null;
            // 新建文件输入流并对它进行缓冲
            inBuff = new BufferedInputStream(in);
            // 新建文件输出流并对它进行缓冲
            outBuff = new BufferedOutputStream(new FileOutputStream(filePath));
            // 缓冲数组
            byte[] b = new byte[1024];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
            flag = true;
            outBuff.close();
            inBuff.close();
            in.close();
        }
        return flag;
    }

    private static InputStream getInputStreamByHttp(String Url) {
        URL url;
        InputStream in = null;
        try {
            url = new URL(Url);
            logger.info("sendHttpsRequest begin : " + Url);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            // 设置请求方法
            con.setRequestMethod("POST");
            // 设置请求超时时间，以毫秒为单位
            con.setConnectTimeout(3000);
            X509TrustManager xtm = new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                        throws CertificateException {
                }

                @Override
                public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                        throws CertificateException {
                }
            };
            TrustManager[] tm = {xtm};
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, tm, null);
            con.setSSLSocketFactory(ctx.getSocketFactory());

            // 验证主机名和服务器验证方案的匹配是可接受的
            con.setHostnameVerifier((var1, var2) -> true);
            logger.info("sendHttpsRequest end");
            in = con.getInputStream();
        } catch (Exception e) {
            logger.error("连接通讯异常", e);
        }
        return in;
    }

}
