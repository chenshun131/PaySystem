package wusc.edu.pay.common.utils.token;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * ClassName: TokenToolEncrypter <br/>
 * Function: 一个Token加密与解密工具 ，不限于Token范围 <br/>
 * date: 2014-8-5 下午8:49:52 <br/>
 * <per> 使用时，结合 TokenProductFactory 生成 平台Token 通过解密，识别Token来源。 </per>
 *
 * @author laich
 */
public class TokenToolEncrypterBase64 implements TokenBaseInter {

    private static final Log logger = LogFactory.getLog(TokenToolEncrypterBase64.class);

    private static BASE64Encoder encoder = new BASE64Encoder();

    private static BASE64Decoder decoder = new BASE64Decoder();

    /**
     * 对字符串进行加密
     *
     * @param str
     * @return
     */
    @Override
    public String encrypt(String str) {
        return encrypt(str.getBytes()).replace("=", "_");
    }

    /**
     * 对数组进行加密
     *
     * @param b
     * @return
     */
    public String encrypt(byte[] b) {
        return encoder.encode(b);
    }

    /**
     * 对加密的信息进行解密
     *
     * @param str
     * @return
     */
    @Override
    public String decrypt(String str) {
        try {
            byte[] temp = decoder.decodeBuffer(str.replace("_", "="));
            return new String(temp);
        } catch (IOException e) {
            logger.error("解密[" + str + "]出错" + e);
            return null;
        }
    }

    /**
     * 对加密的信息进行解密
     *
     * @param b
     * @return
     */
    public String decrypt(byte[] b) {
        return decrypt(new String(b));
    }

    @Override
    public String productToken(String[] pramaters) {
        if (pramaters == null || pramaters.length == 0) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            for (String pramater : pramaters) {
                sb.append(pramater + "-");
            }

            // 最后加上Key值
            sb.append(key);
            return this.encrypt(sb.toString());
        }
    }

    @Override
    public String productToken(String pix, String userNo) {
        return this.encrypt(pix + "-" + userNo + "-" + System.currentTimeMillis() + "-" + key);
    }

}
