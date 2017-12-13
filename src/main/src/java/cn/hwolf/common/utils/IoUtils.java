package cn.hwolf.common.utils;

import org.springframework.core.io.Resource;

import java.io.*;

/**
 * 文件读取工具
 *
 * @author hwolf
 * @email h.wolf@qq.com
 * @date 2017/12/2.
 */
public class IoUtils {
    private static final int DEFAULT_BUFFER_SIZE = 1024;

    protected IoUtils() {
    }

    public static String readString(Resource resource) throws IOException {
        return readString(resource.getInputStream());
    }

    /**
     * 读取文件内容
     * @param is
     * @return 文件内容
     * @throws IOException
     */
    public static String readString(InputStream is) throws IOException {
        return readString(is, "UTF-8");
    }

    public static String readString(InputStream is, String encoding)
            throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        copyStream(is, baos);

        String text = new String(baos.toByteArray(), encoding);

        return text;
    }

    public static void copyFileToOutputStream(String fileLocation,
            OutputStream os) throws IOException {
        FileInputStream fis = new FileInputStream(fileLocation);
        copyStream(fis, os);
    }

    /**
     * 写出，outputStream 流用完了才关闭
     * @param is
     * @param os
     * @throws IOException
     */
    public static void copyStream(InputStream is, OutputStream os)
            throws IOException {
        if (is == null) {
            throw new IllegalArgumentException("InputStream is null");
        }

        if (os == null) {
            throw new IllegalArgumentException("OutputStream is null");
        }

        // 缓冲区
        byte[] b = new byte[DEFAULT_BUFFER_SIZE];
        int len = 0;

        try {
            // 循环读取 inputStream 中的内容
            while ((len = is.read(b, 0, DEFAULT_BUFFER_SIZE)) != -1) {
                os.write(b, 0, len);
            }
        } finally {
            is.close();
            os.flush();
        }
    }
}
