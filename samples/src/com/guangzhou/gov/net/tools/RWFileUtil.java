package com.guangzhou.gov.net.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.http.util.EncodingUtils;

import android.os.Environment;

/**
 * 
 * @ClassName: RWFileUtil
 * @Description: 加密读写文件
 * @author chenjianping
 * @date 2014-10-29
 * 
 */
public class RWFileUtil { // 读文件

    private static File sCacheDir;

    private static String sRes;

    private static final String sFILE_NAME = "system_config";
    private static final String sFILE_LOG = "log.txt";

    public static String readSDFile()
    {
        FileInputStream fis = null;
        try {
            File dir = getCacheDir();

            if (dir == null) {
                return null;
            }
            File file = new File(dir, sFILE_NAME);

            fis = new FileInputStream(file);

            int length = fis.available();

            byte[] buffer = new byte[length];

            fis.read(buffer);


            sRes = decrypt(EncodingUtils.getString(buffer, "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sRes;
    }

    // 写文件
    public static void writeSDFile(String write_str) throws Exception
    {
        File destDir = getCacheDir();
        if (destDir == null) {
            return;
        }
        if (!destDir.exists()) {
            destDir.mkdir();
        }

        write_str = encrypt(write_str);

        if (write_str != null) {
            File file = new File(destDir, sFILE_NAME);

            FileOutputStream fos = new FileOutputStream(file);
            try {
                byte[] bytes = write_str.getBytes();
                fos.write(bytes);
            } catch (IOException ioe) {
                throw ioe;
            } finally {
                try {
                    fos.close();
                } catch (Exception e) {
                    throw e;
                }
            }
        }
    }
    
 // 写文件
    public static void writeSDLog(String write_str) throws Exception
    {
        File destDir = getCacheDir();
        if (destDir == null) {
            return;
        }
        if (!destDir.exists()) {
            destDir.mkdir();
        }
        write_str = "\n" + write_str;
        if (write_str != null) {
            File file = new File(destDir, sFILE_LOG);

            FileOutputStream fos = new FileOutputStream(file, true);
            try {
                byte[] bytes = write_str.getBytes();
                fos.write(bytes);
            } catch (IOException ioe) {
                throw ioe;
            } finally {
                try {
                    fos.close();
                } catch (Exception e) {
                    throw e;
                }
            }
        }
    }

    public static File readLogFile()
    {
        File file = null;
        try {
            File dir = getCacheDir();

            if (dir == null) {
                return null;
            }
            file = new File(dir, sFILE_LOG);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        return file;
    }

    /**
     * mk cache dir
     * 
     * @param @return
     * @return File
     */
    private static File getCacheDir()
    {
        if (isSDCardAvailable()) {
            if (sCacheDir == null) {
                sCacheDir = new File(Environment.getExternalStorageDirectory(), ".android/config");
                sCacheDir.mkdirs();
            }
        }
        return sCacheDir;
    }

    private static final String PASSWORD_CRYPT_KEY = "com.jiubang.golock";
    /** 加密算法,可用 DES,DESede,Blowfish. */
    private final static String ALGORITHM = "DES";



    /**
     * 对数据进行DES加密.
     * 
     * @param data
     * @return string
     * @throws Exception
     */
    private final static String decrypt(String data) throws Exception
    {
        return new String(decrypt(hex2byte(data.getBytes()), PASSWORD_CRYPT_KEY.getBytes()));
    }

    /**
     * 对用DES加密过的数据进行解密.
     * 
     * @param data
     * @return string
     * @throws Exception
     */
    private final static String encrypt(String data) throws Exception
    {
        return byte2hex(encrypt(data.getBytes(), PASSWORD_CRYPT_KEY.getBytes()));
    }

    /**
     * 用指定的key对数据进行DES加密.
     * 
     * @param data
     * @param key
     * @return byte[]
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception
    {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        // 现在，获取数据并加密
        // 正式执行加密操作
        return cipher.doFinal(data);
    }

    /**
     * 用指定的key对数据进行DES解密.
     * 
     * @param data
     * @param key
     * @return byte[]
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception
    {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建一个DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        // 现在，获取数据并解密
        // 正式执行解密操作
        return cipher.doFinal(data);
    }

    private static byte[] hex2byte(byte[] b)
    {
        if ((b.length % 2) != 0) {
        	throw new IllegalArgumentException("长度不是偶数");
        }
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

    private static String byte2hex(byte[] b)
    {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = java.lang.Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    /**
     * sdcard is available
     * 
     * @param @return
     * @return boolean
     */
    private static boolean isSDCardAvailable()
    {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

}
