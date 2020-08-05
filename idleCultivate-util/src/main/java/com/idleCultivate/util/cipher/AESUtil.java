package com.idleCultivate.util.cipher;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AESUtil {

    private static final String AES = "AES";

    /**
     * 加密
     *
     * @param key  密钥
     * @param data 明文数据 16进制且长度为16的整数倍不足时补0
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws Exception
     */
    public static byte[] encrypt(byte[] key, byte[] data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        byte[] buff = get128Key(key);
        SecretKeySpec skeySpec = new SecretKeySpec(buff, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
        IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());//使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        return cipher.doFinal(data);
    }

    private static byte[] get128Key(byte[] key) {
        final int keyMax = 16;
        byte[] buff = new byte[keyMax];
        for (int i=0; i<keyMax; i++) {
            buff[i] = 0;
        }
        if (key.length < keyMax) {
            System.arraycopy(key, 0, buff, 0, key.length);
        }
        else if (key.length > keyMax) {
            System.arraycopy(key, 0, buff, 0, buff.length);
        }
        else {
            System.arraycopy(key, 0, buff, 0, buff.length);
        }
        return buff;
    }

    /**
     * 解密
     *
     * @param key  密钥
     * @param data 明文数据 16进制且长度为16的整数倍不足时补0
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws Exception
     */
    public static byte[] decrypt(byte[] key, byte[] data) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        byte[] buff = get128Key(key);
        SecretKeySpec skeySpec = new SecretKeySpec(buff, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        return cipher.doFinal(data);
    }

    /**
     * 解密
     * @param key 密钥 16进制且长度为32
     * @param data 密文数据 16进制且长度为16的整数倍
     * @return
     * @throws UnsupportedEncodingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public final static String decrypt(String key, String data) throws Exception {
        return new String(decrypt(key.getBytes("utf-8"), EncodeUtil.hexString2Byte(data)), "utf-8");
    }

    /**
     * 解密参数
     * @param key
     * @param data
     * @return
     * @throws Exception
     */
    public final static String [] decrypt(String key, String ... data) throws Exception {
        String[] datas = new String[data.length];
        for(int i = 0; i < data.length; i++) {
            datas[i] = decrypt(key, data[i]);
        }
        return datas;
    }

    /**
     * 解密
     * @param key 密钥 16进制且长度为32
     * @param data 明文数据 16进制且长度为16的整数倍不足时补0
     * @return
     * @throws UnsupportedEncodingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public final static String encrypt(String key, String data) throws Exception {
        return EncodeUtil.byte2HexString(encrypt(key.getBytes("utf-8"), data.getBytes("utf-8")));
    }

    /**
     * 多个参数加密
     * @param key
     * @param data
     * @return
     * @throws Exception
     */
    public final static String [] encrypt(String key, String ... data) throws Exception {
        String[] datas = new String[data.length];
        for(int i = 0; i < data.length; i++) {
            datas[i] = encrypt(key, (null == data[i] ? "" : data[i]));
        }
        return datas;
    }

    public static void main(String[] args) {
        try {
            String a = "2A610FA7D9F83EBB96B2E88FBD3A027F";
            System.out.println(decrypt("111", a));
//    		String cardNo = encrypt("111", "13426462041");
//    		System.out.println("cardNo: "+cardNo);
//    		String expirationDate = encrypt("111", "12/20");
//    		System.out.println("expirationDate: "+expirationDate);
//    		String cnv2 = encrypt("111", "274");
//    		System.out.println("cnv2:"+cnv2);
//    		String cardName = encrypt("111", "张三");
//    		System.out.println("cardName:"+cardName);
//    		String idNo = encrypt("111","510265790128303");
//    		System.out.println("idNo:"+idNo);


        } catch (Exception e) {
            e.printStackTrace();
            // TODO: handle exception
        }
    }
}
