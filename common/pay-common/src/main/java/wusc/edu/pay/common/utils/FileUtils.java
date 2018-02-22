package wusc.edu.pay.common.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.channels.FileChannel;

/**
 * 文件工具类
 *
 * @author Peter
 */
public class FileUtils {

    /**
     * 对于内容为非中文的大文件读取，可根据该方法读取文件制定位置的字符串
     *
     * @param filePath
     *         文件路径
     * @param beginIndex
     *         开始字符位置
     * @param ByteLength
     *         读取字符长度
     * @return 读取的字符串
     * @throws IOException
     */
    public static String getStringFromFile(String filePath, int beginIndex, int ByteLength) throws IOException {
        String returnStr = null;
        RandomAccessFile randomFile = new RandomAccessFile(filePath, "r");
        randomFile.seek(beginIndex);

        // 每次读取多少次
        byte[] bytes = new byte[ByteLength];
        while ((randomFile.read(bytes)) != -1) {
            returnStr = new String(bytes);
            break;
        }
        randomFile.close();
        return returnStr;
    }

    /**
     * 传入文件夹路径，该方法能够实现创建整个路径
     *
     * @param path
     *         文件夹路径，不包含文件名称及后缀名
     */
    public static void isDir(String path) {
        String[] paths = path.split("/");
        String filePath = "";
        for (int i = 0; i < paths.length; i++) {
            if (i == 0) {
                filePath = paths[0];
            } else {
                filePath += "/" + paths[i];
            }
            creatDir(filePath);
        }
    }

    /**
     * 该方法用来判断文件夹是否存在，如果不存在则创建，存在则什么都不做
     *
     * @param filePath
     */
    public static void creatDir(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    /**
     * 向文件末尾添加内容
     *
     * @param filename
     * @param content
     */
    public static void addContentToEndFile(String filename, String content) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(filename, true));
            out.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void fileCopy(File in, File out) throws IOException {
        FileChannel inChannel = new FileInputStream(in).getChannel();
        FileChannel outChannel = new FileOutputStream(out).getChannel();
        try {
            // inChannel.transferTo(0, inChannel.size(), outChannel);
            // original -- apparently has trouble copying large files on Windows

            // magic number for Windows, 64Mb - 32Kb)
            int maxCount = (64 * 1024 * 1024) - (32 * 1024);
            long size = inChannel.size();
            long position = 0;
            while (position < size) {
                position += inChannel.transferTo(position, maxCount, outChannel);
            }
        } finally {
            if (inChannel != null) {
                inChannel.close();
            }
            if (outChannel != null) {
                outChannel.close();
            }
        }
    }

    public static void createThumbnail(String filename, int thumbWidth, int thumbHeight, int quality, String outFilename)
            throws InterruptedException, FileNotFoundException, IOException {
        // load image from filename
        Image image = Toolkit.getDefaultToolkit().getImage(filename);
        MediaTracker mediaTracker = new MediaTracker(new Container());
        mediaTracker.addImage(image, 0);
        mediaTracker.waitForID(0);
        // use this to test for errors at this point: System.out.println(mediaTracker.isErrorAny());

        // determine thumbnail size from WIDTH and HEIGHT
        double thumbRatio = (double) thumbWidth / (double) thumbHeight;
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        double imageRatio = (double) imageWidth / (double) imageHeight;
        if (thumbRatio < imageRatio) {
            thumbHeight = (int) (thumbWidth / imageRatio);
        } else {
            thumbWidth = (int) (thumbHeight * imageRatio);
        }

        // draw original image to thumbnail image object and
        // scale it to the new size on-the-fly
        BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = thumbImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);
        saveImage(thumbImage, outFilename);
    }

    /**
     * save thumbnail image to outFilename <br/>
     * <code>
     * BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFilename)); <br/>
     * JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out); <br/>
     * JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage); <br/>
     * quality = Math.max(0, Math.min(quality, 100)); <br/>
     * param.setQuality((float) quality / 100.0f, false); <br/>
     * encoder.setJPEGEncodeParam(param); <br/>
     * encoder.encode(thumbImage); <br/>
     * out.close(); <br/>
     * </code>
     *
     * @param dstImage
     * @param outFilename
     * @throws IOException
     */
    public static void saveImage(BufferedImage dstImage, String outFilename) throws IOException {
        String formatName = outFilename.substring(outFilename.lastIndexOf(".") + 1);
        ImageIO.write(dstImage, formatName, new File(outFilename));
    }

    /**
     * 删除目录和目录中的所有文件
     *
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File file1 : files) {
                    deleteFile(file1);
                }
            }
        }
        file.delete();
    }

}
