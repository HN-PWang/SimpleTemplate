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

    public static <T> ApiData<T> success(T data) {
        return new ApiData<>(NetState.SUCCESS_CODE, data, null);
    }

    public static <T> ApiData<T> success(int code, T data, String msg) {
        return new ApiData<>(code, data, msg);
    }

    public static <T> ApiData<T> error(String msg, @Nullable T data) {
        return error(NetState.SUCCESS_CODE, msg, data);
    }

    public static <T> ApiData<T> error(int code, String msg, @Nullable T data) {
        return new ApiData<>(code, data, msg);
    }

}
