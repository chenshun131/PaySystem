package wusc.edu.pay.common.utils.token;

/**
 * className：TokenBaseInter
 * Function：
 * date: 2014-9-23-下午10:23:39
 *
 * @author laich
 */
public interface TokenBaseInter {

    String key = "gzzyzz";

    /**
     * 解密
     *
     * @param str
     * @return
     */
    String decrypt(String str);

    /**
     * 加密
     *
     * @param str
     * @return
     */
    String encrypt(String str);

    /**
     * 放入各种定制的参数，生产Token
     *
     * @param pramaters
     * @return
     */
    String productToken(String[] pramaters);

    /**
     * 放入各种定制的参数，生产Token
     *
     * @param userNo
     * @return
     */
    String productToken(String pix, String userNo);

}
