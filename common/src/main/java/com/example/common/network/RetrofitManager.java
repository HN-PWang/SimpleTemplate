package com.example.common.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @auther: pengwang
 * @date: 2023/4/19
 * @description:
 */
public class RetrofitManager {

    private static final String BASE_URL = "https://www.baidu.com/";

    private static Retrofit retrofit;

    private static Retrofit buildRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10 * 1000, TimeUnit.SECONDS);
        builder.readTimeout(10 * 1000, TimeUnit.SECONDS);
        builder.addInterceptor(new HeaderInterceptor());

        Retrofit.Builder retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build());

        return retrofit.build();
    }

    private static Retrofit getRetrofit() {
        if (retrofit != null) {
            return retrofit;
        }
        retrofit = buildRetrofit();

        return retrofit;
    }

    public static <T> T create(Class<T> cls) {
        return (T) getRetrofit().create(cls);
    }

}
