package com.example.simpletemplate;

import android.content.Intent;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.example.basemodel.base.BaseModelActivity;
import com.example.simpletemplate.databinding.ActivityMainBinding;

public class MainActivity extends BaseModelActivity {

    private ActivityMainBinding vb;

    @Override
    public int getContentViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        vb = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    public void initLogic() {
        vb.tvImage.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, LoadImageActivity.class)));
        vb.tvRequest.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, RequestNetActivity.class)));
        vb.tvPhoto.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SelectPhotoActivity.class)));
    }

}