package com.example.simpletemplate.activity.testActivity;

import androidx.lifecycle.MutableLiveData;

import com.example.basemodel.base.BaseVM;
import com.example.common.network.ApiData;
import com.example.simpletemplate.bean.TestBean;

/**
 * @auther: pengwang
 * @date: 2023/5/5
 * @description:
 */
public class TestModel extends BaseVM {

    MutableLiveData<ApiData<TestBean>> testModel = new MutableLiveData<>();
//
//    public void getTestData() {
//        TestModelService service = RetrofitManager.create(TestModelService.class);
//        service.getUserInfoByLiveData()
//                .observeForever(new Observer<ApiData<TestBean>>() {
//                    @Override
//                    public void onChanged(ApiData<TestBean> apiData) {
//                        testModel.postValue(apiData);
//                    }
//                });
//    }

}
