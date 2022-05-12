package utils;

import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
/**
 * 知识点：
 * 1. mkdir只能用来创建单层目录，mkdirs可以用来创建多级目录，内部也会调用mkdir，如果mkdir返回true则直接返回
 * 也就是说，mkdirs可以创建单层也可以创建多层目录，所以我们平时使用 **mkdirs**就行
 * 2. 注意文件流操作，异常处理，流的关闭
 */

/**
 * User: Zephyr
 * Date: 2022/5/11
 * Time: 9:38
 */
public class ZipUtils {
    public static final int DEFAULT_BUFFER_LENGTH = 4096;

    /**
     * 解压文件到指定目录
     * @param zipFilePath
     * @param outPath
     * @return
     */
    public static boolean unzipFolder(String zipFilePath, String outPath) {
        ZipInputStream zipIn = null;
        FileOutputStream out = null;
        try {
            zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
            ZipEntry zipEntry = null;
            String zeName = "";
            while ((zipEntry = zipIn.getNextEntry()) != null) {
                zeName = zipEntry.getName();
                if (zipEntry.isDirectory()) {
                    zeName = zeName.substring(0, zeName.length() - 1);
                    File file = new File(outPath + File.separator + zeName);
                    file.mkdirs();
                } else {
                    File file = new File(outPath + File.separator + zeName);
                    if (!file.exists()) {
                        file.getParentFile().mkdirs();
                        file.createNewFile();
                    }
                    out = new FileOutputStream(outPath);
                    byte[] bytes = new byte[DEFAULT_BUFFER_LENGTH];
                    int len;
                    while ((len = zipIn.read(bytes)) != -1) {
                        out.write(bytes, 0, len);
                        out.flush();
                    }
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                out.close();
                zipIn.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    public static boolean unzipFolder(String zipFilePath, String outPath, String zeName) {
        ZipInputStream zipIn = null;
        FileOutputStream out = null;
        try {
            zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
            ZipEntry zipEntry = null;
            while ((zipEntry = zipIn.getNextEntry()) != null) {
                zeName = zipEntry.getName();
                if (zipEntry.isDirectory()) {
                    zeName = zeName.substring(0, zeName.length() - 1);
                    File file = new File(outPath + File.separator + zeName);
                    file.mkdirs();
                } else {
                    File file = new File(outPath + File.separator + zeName);
                    if (!file.exists()) {
                        file.getParentFile().mkdirs();
                        file.createNewFile();
                    }
                    out = new FileOutputStream(outPath);
                    byte[] bytes = new byte[DEFAULT_BUFFER_LENGTH];
                    int len;
                    while ((len = zipIn.read(bytes)) != -1) {
                        out.write(bytes, 0, len);
                        out.flush();
                    }
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                out.close();
                zipIn.close();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

//    public static boolean zipFile(String srcPath, String zipFilePath) {
//        if (TextUtils.isEmpty(srcPath) || TextUtils.isEmpty(zipFilePath)) return false;
//        ZipOutputStream zipOutputStream;
//        try {
//            zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFilePath));
//            File file = new File(srcPath);
//            if (file.isFile()) {
//
//            } else {
//
//            }
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
}
