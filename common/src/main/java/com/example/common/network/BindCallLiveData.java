package com.example.common.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @auther: pengwang
 * @date: 2023/5/5
 * @description:
 */
public class BindCallLiveData<T> extends MutableLiveData<T> {

    private Call<T> call;

    public BindCallLiveData(Call<T> call) {
        this.call = call;
    }

    @Override
    protected void onActive() {
        Log.e("PWDebug", "onActive");
        call.enqueue(new LiveCallBack<T>(this));
    }

    public void cancel() {
        call.cancel();
    }

    public static class LiveCallBack<T> implements Callback<T> {

        private MutableLiveData<T> liveData;

        public LiveCallBack(MutableLiveData<T> liveData) {
            this.liveData = liveData;
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            liveData.postValue(response.body());
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            liveData.postValue((T) ApiData.create(NetState.NETWORK_ERROR, null, "网络异常"));
        }
    }

}
