package com.example.simpletemplate.netService;

import com.example.common.network.ApiData;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

/**
 * @auther: pengwang
 * @date: 2023/4/19
 * @description:
 */
public interface TestModelService {

//    @GET("getUserInfo")
//    LiveData<Bean> getUserInfoByLiveData();

    @Headers("Content-Type: application/json")
    @GET("getUserInfo")
    Observable<ApiData<Object>> getUserInfoByLiveData(@QueryMap Map<String, String> commonParams);

}
