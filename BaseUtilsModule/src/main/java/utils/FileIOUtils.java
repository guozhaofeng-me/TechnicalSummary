package utils;

import android.text.TextUtils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * User: Zephyr
 * Date: 2022/5/12
 * Time: 14:59
 */
public class FileIOUtils {
    private static final String LINE_SEP = System.getProperty("line.separator");
    private static int sBufferSize = 8192;

    /**
     * 将输入流写入文件
     *
     * @param file
     * @param is
     * @param append
     * @return
     */
    public static boolean writeFileFromIS(final File file, final InputStream is, final boolean append) {
        if (!createOrExitsFile(file) || is == null) return false;
        BufferedOutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(file, append));
            byte[] bytes = new byte[sBufferSize];
            int len;
            while ((len = is.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            CloseUtils.closeIO(is, outputStream); // 要将两个流都关闭
        }
    }

    /**
     * 将字节数组写入到文件
     *
     * @param file
     * @param bytes
     * @param append
     * @return
     */
    public static boolean writeFileFromBytes(final File file, final byte[] bytes, final boolean append) {
        if (bytes == null || !createOrExitsFile(file)) return false;
        BufferedOutputStream outputStream = null;
        try {
            outputStream = new BufferedOutputStream(new FileOutputStream(file, append));
            outputStream.write(bytes);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            CloseUtils.closeIO(outputStream);
        }
    }

    public static boolean writeFileFromBytesByChannel(final File file, final byte[] bytes, final boolean append, final boolean isForce) {
        if (bytes == null) return false;
        FileChannel channel = null;
        try {
            channel = new FileOutputStream(file, append).getChannel();
            channel.position(channel.size());
            channel.write(ByteBuffer.wrap(bytes));
            if (isForce) channel.force(true);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            CloseUtils.closeIO(channel);
        }
    }

    public static boolean writeFileFromBytesByMap(final File file, final byte[] bytes, final boolean append, final boolean isForce) {
        if (bytes == null) return false;
        FileChannel channel = null;
        try {
            channel = new FileOutputStream(file, append).getChannel();
            MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, channel.size(), bytes.length);
            map.put(bytes);
            if (isForce) map.force();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            CloseUtils.closeIO(channel);
        }
    }

    /**
     * 将字符串保存到文件中
     * Writer是往文件里写内容，Reader是往内存中读内容
     *
     * @param file
     * @param content
     * @param append
     * @return
     */
    public static boolean writeFileFromString(final File file, final String content, final boolean append) {
        if (file == null || TextUtils.isEmpty(content)) return false;
        if (!createOrExitsFile(file)) return false;
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file, append));
            writer.write(content);
            return true;
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        } finally {
            CloseUtils.closeIO(writer);
        }
    }

    /**
     * 将文件中的内容读取到字符串中
     *
     * @param file
     * @param charsetName
     * @return
     */
    public static String readFile2String(final File file, final String charsetName) {
        if (!isFileExists(file)) return null;
        BufferedReader reader = null;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            if (isSpace(charsetName)) {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            } else {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), charsetName));
            }
            String line;
            if ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(LINE_SEP).append(line);
                }
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            CloseUtils.closeIO(reader);
        }
    }

    /**
     * 将文件内容读到字节数组中
     *
     * @param file
     * @return
     */
    public static byte[] readFile2BytesByStream(final File file) {
        if (!isFileExists(file)) return null;
        FileInputStream fis = null;
        ByteArrayOutputStream os = null;
        try {
            fis = new FileInputStream(file);
            os = new ByteArrayOutputStream();
            byte[] b = new byte[sBufferSize];
            int len;
            while ((len = fis.read(b, 0, sBufferSize)) != -1) {
                os.write(b, 0, len);
            }
            return os.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            CloseUtils.closeIO(fis, os);
        }
    }

    public static byte[] readFile2BytesByChannel(final File file) {
        if (!isFileExists(file)) return null;
        FileChannel fc = null;
        try {
            fc = new RandomAccessFile(file, "r").getChannel();
            ByteBuffer buffer = ByteBuffer.allocate((int) fc.size());
            while (true) {
                if (!((fc.read(buffer)) > 0)) break;
            }
            return buffer.array();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            CloseUtils.closeIO(fc);
        }
    }

    public static byte[] readFile2BytesByMap(final File file) {
        if (!isFileExists(file)) return null;
        FileChannel fc = null;
        try {
            fc = new RandomAccessFile(file, "r").getChannel();
            int size = (int) fc.size();
            MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, size).load();
            byte[] result = new byte[size];
            mbb.get(result, 0, size);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            CloseUtils.closeIO(fc);
        }
    }


    /**
     * 创建或者判断是否已经存在文件
     * 逻辑顺序如下：
     * 1. 判断File是不是存在，并且判断是不是文件，是的话返回
     * 2. 如果不存在，那就要创建文件，创建之前判断路径是否存在，不存在则返回。
     * 3. 创建好路径以后，创建文件。
     *
     * @param file
     * @return
     */
    private static boolean createOrExitsFile(final File file) {
        if (file == null) return false;
        if (file.exists()) return file.isFile(); // 如果存在，判断是不是file
        if (!createOrExistsDir(file.getParentFile())) return false; // 创建路径
        try {
            return file.createNewFile(); // 创建文件
        } catch (IOException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    private static boolean createOrExistsDir(final File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    private static File getFileByPath(final String filePath) {
        return isSpace(filePath) ? null : new File(filePath);
    }

    private static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static boolean isFileExists(final File file) {
        return file != null && file.exists();
    }

}
