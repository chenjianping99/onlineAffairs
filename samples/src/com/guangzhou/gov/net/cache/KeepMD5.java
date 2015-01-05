package com.guangzhou.gov.net.cache;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * 
* @ClassName: KeepMD5 
* @Description: 对缓存的用户进行加密处理
* @author chenjianping
* @date 2014-11-13 
*
 */
public class KeepMD5 {

    // =====================md5======================================
    public static String getMD5Hex(String str)
    {
        byte[] data = getMD5(str.getBytes());
        if (data != null) {
            BigInteger bi = new BigInteger(data).abs();
            return bi.toString(36);
        }
        return str;
    }


    private static byte[] getMD5(byte[] data)
    {

        MessageDigest digest;
        try {
            digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(data);
            byte[] hash = digest.digest();
            return hash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;

    }



    // ----------------------------加密，解密---------------------------------------

    private static final String PASSWORD_CRYPT_KEY = "com.guangzhou.gov";
    /** 加密算法,可用 DES,DESede,Blowfish. */
    private final static String ALGORITHM = "DES";

    /**
     * 对数据进行DES加密.
     * 
     * @param data
     * @return string
     * @throws Exception
     */
    public final static String decrypt(String data) throws Exception
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
    public final static String encrypt(String data) throws Exception
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
}
