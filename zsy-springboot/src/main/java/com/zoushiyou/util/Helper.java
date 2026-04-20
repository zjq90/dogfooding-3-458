package com.zoushiyou.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class Helper {
    
    public static String getMd5Str(String password, String salt) {
        String string = password + salt;
        if (string.isEmpty()) {
            return "";
        }
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes("UTF-8"));
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
    
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }
}
