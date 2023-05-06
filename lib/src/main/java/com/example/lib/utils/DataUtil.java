package com.example.lib.utils;

import java.util.List;
import java.util.Map;

/**
 * @auther: pengwang
 * @date: 2023/5/5
 * @description:
 */
public class DataUtil {

    public static boolean isEmpty(List<Object> data) {
        return data == null || data.size() == 0;
    }

    public static boolean isEmpty(Map data) {
        return data == null || data.isEmpty();
    }

    public static boolean isEmpty(Object[] data) {
        return data == null || data.length == 0;
    }


}
