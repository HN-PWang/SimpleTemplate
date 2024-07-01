package com.example.simpletemplate;

import android.widget.TextView;

import com.alibaba.fastjson.JSONObject;
import com.example.basemodel.base.BaseModelActivity;
import com.example.common.network.ApiData;
import com.example.common.network.NetState;
import com.example.common.network.RetrofitManager;
import com.example.common.utils.toast.ToastUtils;
import com.example.simpletemplate.netService.TestModelService;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RequestNetActivity extends BaseModelActivity {

    private TextView tvNetInfo;

    private final CompositeDisposable disposable = new CompositeDisposable();

    @Override
    public int getContentViewLayout() {
        return R.layout.activity_request_net;
    }

    @Override
    public void initView() {
        tvNetInfo = findViewById(R.id.tv_net_info);
    }

    @Override
    public void initLogic() {

        reqNet();
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
                        tvNetInfo.setText(JSONObject.toJSONString(data));
                        if (data != null && data.code == NetState.SUCCESS) {
                            //
                        } else {
                            ToastUtils.show("com.ld.lib.R.string.str_network_anomaly");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        ToastUtils.show(throwable.getMessage());
                        tvNetInfo.setText(throwable.getMessage());
                    }
                }));
    }
}