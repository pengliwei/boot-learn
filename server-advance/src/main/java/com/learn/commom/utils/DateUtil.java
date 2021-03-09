package com.learn.commom.utils;

import java.text.SimpleDateFormat;

/**
 * @description: 时间工具类
 * @author: PENGLW
 * @date: 2020/10/22
 */
public class DateUtil {

    /**
     * 时间戳转字符串
     *
     * @param timestamp 时间戳字符串
     * @return
     */
    public static String getStringDate(String timestamp) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time = Long.valueOf(timestamp);
        return format.format(time);
    }
}
