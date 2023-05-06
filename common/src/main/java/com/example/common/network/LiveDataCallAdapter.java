package com.example.common.network;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;

/**
 * @auther: pengwang
 * @date: 2023/4/20
 * @description:
 */
public class LiveDataCallAdapter<T> implements CallAdapter<T, LiveData<T>> {

    private Type responseType;
    private boolean isResponse;
    private boolean isBody;

    public LiveDataCallAdapter(Type responseType, boolean isResponse
            , boolean isBody) {
        this.responseType = responseType;
        this.isResponse = isResponse;
        this.isBody = isBody;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public LiveData<T> adapt(@NonNull Call<T> call) {
        return new BindCallLiveData<T>(call);
    }
}
