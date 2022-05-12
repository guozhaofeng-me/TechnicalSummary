package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * User: Zephyr
 * Date: 2022/5/12
 * Time: 17:16
 */
public class MD5Helper {
    private static byte[] hex = new byte[]{
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'
    };

    /**
     * 对字符串MD5加密
     *
     * @param rawString
     * @return
     */
    public static String encode(String rawString) {
        String md5String = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(rawString.getBytes());
            md5String = convertToHexString(md5.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (md5String != null) {
            return md5String.toUpperCase();
        }
        return md5String;
    }

    /**
     * 文件生成其MD5摘要
     *
     * @param file
     * @return
     */
    public static String md5File(File file) {
        FileInputStream fis = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            fis = new FileInputStream(file);
            byte[] bytes = new byte[2048];
            int len;
            while ((len = fis.read(bytes)) != -1) {
                md5.update(bytes, 0, len);
            }
            byte[] digest = md5.digest();
            return convertToHexString(digest);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            CloseUtils.closeIO(fis);
        }
    }

    public static String encodeToLowerCase(String rawString) {
        return encode(rawString).toLowerCase();
    }

    private static String convertToHexString(byte[] digests) {
        byte[] bytes = new byte[digests.length * 2];
        int index = 0;
        for (byte digest : digests) {
            bytes[index] = hex[(digest >> 4) & 0x0F];
            bytes[index + 1] = hex[digest & 0X0F];
            index += 2;
        }
        return new String(bytes);
    }
}
