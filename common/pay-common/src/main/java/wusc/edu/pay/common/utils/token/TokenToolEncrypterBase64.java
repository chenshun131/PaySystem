package wusc.edu.pay.common.utils.token;

import wusc.edu.pay.common.utils.Base64Utils;

/**
 * ClassName: TokenToolEncrypter <br/>
 * Function: 一个Token加密与解密工具 ，不限于Token范围 <br/>
 * date: 2014-8-5 下午8:49:52 <br/>
 * <per> 使用时，结合 TokenProductFactory 生成 平台Token 通过解密，识别Token来源。 </per>
 *
 * @author laich
 */
public class TokenToolEncrypterBase64 implements TokenBaseInter {

    /**
     * 对字符串进行加密
     *
     * @param str
     * @return
     */
    @Override
    public String encrypt(String str) {
        return Base64Utils.encryptBASE64(str.getBytes()).replace("=", "_");
    }

    /**
     * 对加密的信息进行解密
     *
     * @param str
     * @return
     */
    @Override
    public String decrypt(String str) {
        byte[] temp = Base64Utils.decryptBASE64(str.replace("_", "="));
        return new String(temp);
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
                sb.append(pramater).append("-");
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
