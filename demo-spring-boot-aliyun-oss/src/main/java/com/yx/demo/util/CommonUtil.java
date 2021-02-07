package com.yx.demo.util;

import java.util.UUID;

/**
 * @author yangxi
 * @version 1.0
 */
public class CommonUtil {

    /**
     * 生成uuid
     * @return
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-","").substring(0,32);
    }
}