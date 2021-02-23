package com.yx.demo.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * 工具类
 */
public class CommonUtils {


    /**
     * MD5加密工具类
     * @param data
     * @return
     */
    public static String MD5(String data)  {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));
            }

            return sb.toString().toUpperCase();
        } catch (Exception exception) {
        }
        return null;

    }

    public static void main(String[] args) {
        String token = "xdclasseyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ4ZGNsYXNzIiwicm9sZXMiOiIxLDIiLCJpbWciOiJodHRwczovL3hkLXZpZGVvLXBjLWltZy5vc3MtY24tYmVpamluZy5hbGl5dW5jcy5jb20veGRjbGFzc19wcm8vZGVmYXVsdC9oZWFkX2ltZy8xMi5qcGVnIiwiaWQiOjEyODc2LCJuYW1lIjoieGlndWEzNjYiLCJpYXQiOjE2MTE2MzU4MDEsImV4cCI6MTYxMjI0MDYwMX0.vw0Tz2mZ6gQBTQPIi2EDQ7T7b6c1bIcX_L2nHqWQNRE";
        String tokenMd5_01 = MD5(token);
        String tokenMd5_02 = MD5(token);

        System.out.println(tokenMd5_01);
        System.out.println(tokenMd5_02);

        String base64_01 = new String(Base64.getEncoder().encode(token.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        String base64_02 = new String(Base64.getEncoder().encode(token.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);

        System.out.println(base64_01);
        System.out.println(base64_02);

    }

}
