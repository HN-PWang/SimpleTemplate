package com.example.common.network;

import androidx.annotation.Nullable;

/**
 * @auther: pengwang
 * @date: 2023/5/5
 * @description:
 */
public class ApiData<T> {

    public int code;
    public T data;
    public String message;

    public ApiData(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public static <T> ApiData<T> create(int code,@Nullable T data,String msg){
        return new ApiData<>(code, data, msg);
    }

}
