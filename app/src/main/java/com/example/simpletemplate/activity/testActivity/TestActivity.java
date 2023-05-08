package com.example.simpletemplate.activity.testActivity;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.basemodel.base.BaseModelActivity;
import com.example.common.utils.toast.ToastUtils;
import com.example.common.widget.MessageDialog;
import com.example.simpletemplate.R;
import com.example.simpletemplate.databinding.ActivityTestBinding;

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

        vb.tvReq1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                MessageDialog dialog = new MessageDialog(TestActivity.this,"提示消息"
//                        ,"提示消息提示消息提示消息提示消息提");
//                dialog.show();
                ToastUtils.show("显示一下 显示一下");
            }
        });

        vb.srlView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        vb.srlView.setRefreshing(false);
                    }
                }.start();

            }
        });

    }

    @Override
    public void initLogic() {

    }
}