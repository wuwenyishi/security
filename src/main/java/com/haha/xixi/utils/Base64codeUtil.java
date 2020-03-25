package com.haha.xixi.utils;

import java.nio.charset.StandardCharsets;

import static org.apache.commons.codec.binary.Base64.*;
import static org.apache.commons.lang3.StringUtils.*;

/**
 * @author admin
 * @Date 2020/3/25
 * @description:
 **/
public class Base64codeUtil {

    // 编码
    public static String String2Base64(String str) {

        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        byte[] codes = encodeBase64(bytes);

        return new String(codes);

    }

    // 解码
    public static String Base642String(String str) {

        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        byte[] codes = decodeBase64(bytes);

        return new String(codes);

    }

    /**
     * @Author: admin
     * @Date: 2018/9/21 11:26
     * @desc 用途:获取token时需要把username和 password编码为Base64，并增加前缀Basic
     */
    public static String getEncode(String username, String password) {
        String str = join(username, ":", password);
        str = String2Base64(str);
        return join("Basic ", str);
    }


}