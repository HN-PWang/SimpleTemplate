package com.example.simpletemplate;

import com.example.common.CommonApplication;

/**
 * @auther: pengwang
 * @date: 2023/5/6
 * @description:
 */
public class MyApplication extends CommonApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }
}
