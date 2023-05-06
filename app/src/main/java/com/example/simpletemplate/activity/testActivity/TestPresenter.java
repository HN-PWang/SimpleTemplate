package com.example.simpletemplate.activity.testActivity;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.basemodel.base.BasePresenter;
import com.example.common.network.ApiData;
import com.example.common.network.RetrofitManager;
import com.example.simpletemplate.bean.TestBean;
import com.example.simpletemplate.netService.TestModelService;
import com.google.gson.Gson;

/**
 * @auther: pengwang
 * @date: 2023/5/5
 * @description:
 */
public class TestPresenter extends BasePresenter {

    private TestModel vm;

    public TestPresenter(LifecycleOwner lifecycleOwner, ViewModel viewModel) {
        super(lifecycleOwner, viewModel);
        this.vm = (TestModel) viewModel;
    }

    public void requestTest() {
        TestModelService service = RetrofitManager.create(TestModelService.class);
        service.getUserInfoByLiveData().observe(lifecycleOwner, new Observer<ApiData<TestBean>>() {
            @Override
            public void onChanged(ApiData<TestBean> apiData) {
                Log.e("PWDebug", "onChanged code = " + apiData.code);
                Log.e("PWDebug", "onChanged data = " + new Gson().toJson(apiData.data));
                Log.e("PWDebug", "onChanged msg = " + apiData.message);
            }
        });

//        BindCallLiveData<ApiData<TestBean>> liveData = service.getUserInfoByLiveData();
//        liveData.cancel();

//        vm.getTestData();
    }

}
