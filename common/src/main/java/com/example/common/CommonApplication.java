package com.example.common;

import android.app.Application;
import android.content.Context;

/**
 * @auther: pengwang
 * @date: 2023/5/6
 * @description:
 */
public class CommonApplication extends Application {

    protected static volatile Context mContext;

    public static Context getContext() {
        return mContext;
    }
}
