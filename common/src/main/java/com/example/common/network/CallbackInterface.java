package com.example.common.network;

/**
 * @auther: pengwang
 * @date: 2023/4/21
 * @description:
 */
public interface CallbackInterface<T> {

    void onExecute();

    void onSucceed(T data);

    void onFailure(String msg);

}
