package wusc.edu.pay.common.utils;

import java.nio.file.Paths;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

/**
 * User: chenshun131 <p />
 * Time: 18/2/13 22:32  <p />
 * Version: V1.0  <p />
 * Description:  <p />
 */
public class Base64Utils {

    private static String BASE64_IMG = "data:image/png;base64,";

    /**
     * BASE64Encoder 加密，从JKD 9开始rt.jar包已废除不能使用 sun.misc.BASE64Encoder，从JDK 1.8开始使用 java.util.Base64.Encoder <br/>
     * <code>
     * // 不能使用以下代码 <br/>
     * BASE64Encoder encoder = new BASE64Encoder(); <br/>
     * String encode = encoder.encode(data);
     * </code>
     *
     * @param data
     *         要加密的数据
     * @return 加密后的字符串
     */
    public static String encryptBASE64(byte[] data) {
        Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(data);
    }

    /**
     * 将图片转换成 Base64 格式的图片
     *
     * @param data
     * @return
     */
    public static String getBase64Img(byte[] data) {
        return BASE64_IMG + encryptBASE64(data);
    }

    /**
     * BASE64Decoder 解密，从JKD 9开始rt.jar包已废除不能使用 sun.misc.BASE64Decoder，从JDK 1.8开始使用java.util.Base64.Decoder <br/>
     * <code>
     * // 不能使用以下代码 <br/>
     * BASE64Decoder decoder = new BASE64Decoder(); <br/>
     * byte[] buffer = decoder.decodeBuffer(data);
     * </code>
     *
     * @param data
     *         要解密的字符串
     * @return 解密后的byte[]
     */
    public static byte[] decryptBASE64(String data) {
        Decoder decoder = Base64.getDecoder();
        return decoder.decode(data);
    }

    /**
     * <pre>
     * <!DOCTYPE html>
     * <html>
     * <head lang="zh-CN">
     *     <meta charset="UTF-8">
     *     <meta http-equiv="X-UA-Compatible" content="IE=edge">
     *     <meta name="viewport" content="width=device-width">
     *     <title>测试Base64编码 - 2gua</title>
     *     <style type="text/css">
     *         #thisImage {
     *             margin: 20px auto;
     *             -webkit-border-radius: 50%;
     *             border-radius: 50%;
     *             background: url() no-repeat center center;
     *             background-size: contain;
     *             border: 8px solid #EDEDED;
     *             width: 128px;
     *             height: 128px;
     *         }
     *     </style>
     * </head>
     * <body>
     *     <div id="thisImage"></div>
     * </body>
     * </html>
     * </pre>
     *
     * @param args
     */
    public static void main(String[] args) {
        // 文件读取
        System.out.print(getBase64Img(FileUtils.readFile(Paths.get("/Users/mew/Desktop/AllMyFile/Images/brand1.png"))));
        // 将打印的数据放入 background: url() no-repeat center center; 中的圆括号中即可显示图片
    }

}
