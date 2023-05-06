package com.example.simpletemplate.activity.testActivity;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.basemodel.base.BaseModelActivity;
import com.example.common.network.ApiData;
import com.example.common.network.RetrofitManager;
import com.example.simpletemplate.R;
import com.example.simpletemplate.bean.TestBean;
import com.example.simpletemplate.databinding.ActivityTestBinding;
import com.example.simpletemplate.netService.TestModelService;

public class TestActivity extends BaseModelActivity {

    private TestPresenter mPresenter;

    private TestModel vm;

    private ActivityTestBinding vb;

    @Override
    public int getContentViewLayout() {
        return R.layout.activity_test;
    }

    @Override
    public void initView() {
        vm = new ViewModelProvider(this).get(TestModel.class);
        mPresenter = new TestPresenter(this, vm);

        vb = DataBindingUtil.setContentView(this, R.layout.activity_test);
        vb.setLifecycleOwner(this);
        vb.setVariable(0, vm);

        vb.llRootContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.requestTest();
            }
        });

    }

    @Override
    public void initLogic() {

    }
}