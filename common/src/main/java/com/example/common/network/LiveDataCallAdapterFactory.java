package com.example.common.network;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * @auther: pengwang
 * @date: 2023/4/19
 * @description:
 */
public class LiveDataCallAdapterFactory extends CallAdapter.Factory {

    public static LiveDataCallAdapterFactory create() {
        return new LiveDataCallAdapterFactory();
    }

    @Override
    public CallAdapter<?, ?> get(@NonNull Type returnType, @NonNull Annotation[] annotations
            , @NonNull Retrofit retrofit) {
        Class<?> rawType = getRawType(returnType);
        if (rawType != LiveData.class && rawType != MutableLiveData.class
                && rawType != BindCallLiveData.class) {
            throw new IllegalStateException(
                    "RawType must be LiveData");
        }

        boolean isResponse = false;
        boolean isBody = false;
        Type responseType;

        if (!(returnType instanceof ParameterizedType)) {
            throw new IllegalStateException(
                    "Observable return type must be parameterized"
                            + " as "
                            + "Observable"
                            + "<Foo> or "
                            + "Observable"
                            + "<? extends Foo>");
        }

        Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
        Class<?> rawObservableType = getRawType(observableType);
        if (rawObservableType == Response.class) {
            if (!(observableType instanceof ParameterizedType)) {
                throw new IllegalStateException(
                        "Response must be parameterized" + " as Response<Foo> or Response<? extends Foo>");
            }
            responseType = getParameterUpperBound(0, (ParameterizedType) observableType);
            isResponse = true;
        } else {
            responseType = observableType;
            isBody = true;
        }

        return new LiveDataCallAdapter<>(responseType, isResponse, isBody);
    }
}
