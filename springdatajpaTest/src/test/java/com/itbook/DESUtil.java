package com.itbook;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DESUtil {
    /*
     * 生成密钥
     */
    public static byte[] initKey() throws Exception{
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56);
        SecretKey secretKey = keyGen.generateKey();
        return secretKey.getEncoded();
    }


    /*
     * DES 加密
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception{
        SecretKey secretKey = new SecretKeySpec(key, "DES");

        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] cipherBytes = cipher.doFinal(data);
        return cipherBytes;
    }


    /*
     * DES 解密
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception{
        SecretKey secretKey = new SecretKeySpec(key, "DES");

        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] plainBytes = cipher.doFinal(data);
        return plainBytes;
    }

    public static String bytesToHexString(byte... src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }



    public static void main(String[] args) throws Exception {
        String DATA = "123456zsl";
        //生成秘钥
        byte[] desKey = DESUtil.initKey();
        //很多第三方提供服务就是采用的对称加密，SecretKey16进制字符串提供给我们
        String key = arr2HexStr(desKey, true);
        System.out.println("沟通双方的秘钥为 : " + key);
        //加密
        byte[] key1 = hexItr2Arr(key);
        byte[] desResult = DESUtil.encrypt(DATA.getBytes(), key1);
        System.out.println(DATA + ">>>DES 加密结果>>>" + bytesToHexString(desResult));
        //解密
        byte[] desPlain = DESUtil.decrypt(desResult, key1);
        System.out.println(DATA + ">>>DES 解密结果>>>" + new String(desPlain));
    }

    /**
     * 将16进制字符串转换为byte数组
     * @param hexItr 16进制字符串
     */
    public static byte[] hexItr2Arr(String hexItr) throws DecoderException {
        return Hex.decodeHex(hexItr);
    }

    /**
     * byte数组转化为16进制字符串
     * @param arr 数组
     * @param lowerCase 转换后的字母为是否为小写 可不传默认为true
     */
    public static String arr2HexStr(byte[] arr, boolean lowerCase){
        return Hex.encodeHexString(arr, lowerCase);
    }
}
