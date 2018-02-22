package wusc.edu.pay.common.utils.rsa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wusc.edu.pay.common.config.PublicConfig;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA工具类
 */
public class RsaSign {

    private static Logger logger = LoggerFactory.getLogger(RsaSign.class);

    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    public static String sign(String content, String privateKey) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(priKey);
            signature.update(content.getBytes(PublicConfig.CHARSET_NAME));
            byte[] signed = signature.sign();
            return Base64.encode(signed);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static boolean doCheck(String content, String sign, String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decode(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initVerify(pubKey);
            signature.update(content.getBytes(PublicConfig.CHARSET_NAME));
            return signature.verify(Base64.decode(sign));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

}
