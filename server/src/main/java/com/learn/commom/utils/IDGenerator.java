package com.learn.commom.utils;

import java.util.UUID;

/**
 * @description:
 * @author: PENGLW
 * @date: 2020/9/24
 */
public class IDGenerator {

    private IDGenerator() {
    }

    public static String newID() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr = str.replace("-", "");
        return uuidStr;
    }

    public static void main(String[] args) {
        System.out.println(newID());
    }
}
