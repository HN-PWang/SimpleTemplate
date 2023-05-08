package com.example.common.utils.sp;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.common.CommonApplication;

/**
 * @auther: pengwang
 * @date: 2023/5/8
 * @description:
 */
public class SPUtils {

    public static final String APP_SP_KEY = "_account_sp";

    private static SharedPreferences getSharedPreferences() {
        return CommonApplication.getContext().getSharedPreferences(APP_SP_KEY, Context.MODE_PRIVATE);
    }

    public static int getInt(String key, int defValue) {
        return getSharedPreferences().getInt(key, defValue);
    }

    public static int getInt(String key) {
        return getInt(key, 0);
    }

    public static void setInt(String key, int value) {
        SharedPreferences sp = getSharedPreferences();
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static String getString(String key, String defValue) {
        return getSharedPreferences().getString(key, defValue);
    }

    public static String getString(String key) {
        return getString(key, "");
    }

    public static void setString(String key, String value) {
        SharedPreferences sp = getSharedPreferences();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return getSharedPreferences().getBoolean(key, defValue);
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static void setBoolean(String key, boolean value){
        SharedPreferences sp = getSharedPreferences();
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

}
