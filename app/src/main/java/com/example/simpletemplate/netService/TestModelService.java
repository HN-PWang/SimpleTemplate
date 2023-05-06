package com.example.simpletemplate.netService;

import androidx.lifecycle.MutableLiveData;

import com.example.common.network.ApiData;
import com.example.common.network.BindCallLiveData;
import com.example.simpletemplate.bean.TestBean;

import retrofit2.http.GET;


/**
 * @auther: pengwang
 * @date: 2023/4/19
 * @description:
 */
public interface TestModelService {

//    @GET("getUserInfo")
//    LiveData<Bean> getUserInfoByLiveData();

    @GET("getUserInfo")
    MutableLiveData<ApiData<TestBean>> getUserInfoByLiveData();

}
