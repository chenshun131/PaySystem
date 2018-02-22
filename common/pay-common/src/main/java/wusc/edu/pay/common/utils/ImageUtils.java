package wusc.edu.pay.common.utils;

import java.io.*;

/**
 * User: mew <p />
 * Time: 17/12/21 12:30  <p />
 * Version: V1.0  <p />
 * Description: 图片操作 <p />
 */
public class ImageUtils {

    /**
     * 将Base64编码字符串转换为图片
     *
     * @param imgStr
     *         base64格式的编码字符串
     * @param path
     *         图片路径-具体到文件
     * @return
     */
    public static boolean base64Str2Image(String imgStr, String path) {
        if (imgStr == null) {
            return false;
        }
        try {
            // 解密
            byte[] b = Base64Utils.decryptBASE64(imgStr);
            // 处理特殊数据
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            OutputStream out = new FileOutputStream(path);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 根据图片地址转换为Base64编码字符串
     *
     * @param imgFile
     *         图片路径
     * @return
     */
    public static String img2Base64Str(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        return Base64Utils.encryptBASE64(data);
    }

}
