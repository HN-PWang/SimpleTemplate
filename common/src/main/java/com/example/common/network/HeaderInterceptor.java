package com.example.common.network;

import com.example.lib.utils.DataUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @auther: pengwang
 * @date: 2023/5/6
 * @description:
 */
public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Map<String, String> headers = new HashMap<>();
        //这里添加公共头部参数

        if (DataUtil.isEmpty(headers)) {
            return chain.proceed(request);
        }
        // 构建新的请求
        Request.Builder newRequestBuilder = request.newBuilder();
        // 添加公共Header
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            newRequestBuilder.addHeader(entry.getKey(), entry.getValue());
        }
        return chain.proceed(newRequestBuilder.build());
    }

}
