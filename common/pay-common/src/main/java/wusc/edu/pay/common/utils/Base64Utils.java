package wusc.edu.pay.common.utils;

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

}
