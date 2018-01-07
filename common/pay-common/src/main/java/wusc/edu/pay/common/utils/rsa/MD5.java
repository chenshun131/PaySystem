package wusc.edu.pay.common.utils.rsa;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wusc.edu.pay.common.utils.string.StringUtil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5
 */
public class MD5 {

    private static Logger logger = LoggerFactory.getLogger(MD5.class);

    /**
     * 获取MD5消息摘要
     *
     * @param data
     *         源数据
     * @return MD5消息摘要
     */
    public static byte[] getMD5(byte[] data) {
        byte[] md5 = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md5 = md.digest(data);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return md5;
    }

    /**
     * MD5字符串
     *
     * @param str
     */
    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            if (!StringUtil.isEmpty(str)) {
                messageDigest.update(str.getBytes("UTF-8"));
            }
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }

        if (messageDigest == null) {
            return "";
        }

        byte[] byteArray = messageDigest.digest();
        StringBuilder md5StrBuff = new StringBuilder();
        for (byte aByteArray : byteArray) {
            if (Integer.toHexString(0xFF & aByteArray).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & aByteArray));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & aByteArray));
            }
        }
        return md5StrBuff.toString().toUpperCase();
    }

    /**
     * MD5字符串
     */
    public static String getMD5Str(byte[] data) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(data);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        }

        byte[] byteArray = messageDigest.digest();
        StringBuilder md5StrBuff = new StringBuilder();
        for (byte aByteArray : byteArray) {
            if (Integer.toHexString(0xFF & aByteArray).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & aByteArray));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & aByteArray));
            }
        }
        return md5StrBuff.toString().toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println(MD5.getMD5Str("chenjianhua"));
        System.out.println(DigestUtils.md5Hex("chenjianhua").toUpperCase());
    }

}
