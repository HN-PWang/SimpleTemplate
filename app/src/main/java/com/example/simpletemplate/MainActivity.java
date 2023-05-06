package com.example.simpletemplate;

import android.content.Intent;
import android.view.View;

import com.example.basemodel.base.BaseModelActivity;
import com.example.simpletemplate.activity.testActivity.TestActivity;

public class MainActivity extends BaseModelActivity {

    @Override
    public int getContentViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        findViewById(R.id.tv_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoading();
            }
        });
    }

    @Override
    public void initLogic() {
        startActivity(new Intent(this, TestActivity.class));
    }

}