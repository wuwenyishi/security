package com.haha.xixi.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author admin
 * @Date 2020/3/25
 * @description:
 **/
public class EncrypMD5Util {

    public static String eccrypt(String info) {
        StringBuilder ret = null;
        try {

            MessageDigest md5 = MessageDigest.getInstance("MD5");

            byte[] bytes = md5.digest(info.getBytes("utf8"));
            ret = new StringBuilder(bytes.length << 1);
            for (int i = 0; i < bytes.length; i++) {
                ret.append(Character.forDigit((bytes[i] >> 4) & 0xf, 16));
                ret.append(Character.forDigit(bytes[i] & 0xf, 16));
            }

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();

        }

        return ret.toString();
    }

}
