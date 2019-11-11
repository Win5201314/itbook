package com.itbook;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MDTest {

    public static void main(String[] args) {
        String s = "123456zslgrdgr";
        jdkMD5(s);
    }

    private static void jdkMD5(String s) {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] m = messageDigest.digest(s.getBytes(StandardCharsets.UTF_8));
            System.out.println(Hex.encodeHexString(m));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
