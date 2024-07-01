package com.example.simpletemplate.activity.testActivity;

import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.example.basemodel.base.BaseModelActivity;
import com.example.common.network.ApiData;
import com.example.common.network.NetState;
import com.example.common.network.RetrofitManager;
import com.example.common.utils.toast.ToastUtils;
import com.example.simpletemplate.R;
import com.example.simpletemplate.databinding.ActivityTestBinding;
import com.example.simpletemplate.netService.TestModelService;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class TestActivity extends BaseModelActivity {

    private ActivityTestBinding vb;

    private final CompositeDisposable disposable = new CompositeDisposable();

    @Override
    public int getContentViewLayout() {
        return R.layout.activity_test;
    }

    @Override
    public void initView() {

        vb = DataBindingUtil.setContentView(this, R.layout.activity_test);

        vb.llRootContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public void initLogic() {

    }

    public void reqNet() {
        Map<String, String> map = new HashMap<>();
        map.put("param", String.valueOf(1));
        TestModelService service = RetrofitManager.create(TestModelService.class);
        disposable.add(service.getUserInfoByLiveData(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ApiData<Object>>() {
                    @Override
                    public void accept(ApiData<Object> data) throws Exception {
                        if (data != null && data.code == NetState.SUCCESS) {

                        } else {
                            ToastUtils.show("com.ld.lib.R.string.str_network_anomaly");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.show(throwable.getMessage());
                    }
                }));
    }
}