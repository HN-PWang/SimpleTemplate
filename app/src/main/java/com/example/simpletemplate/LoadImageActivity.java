package com.example.simpletemplate;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.basemodel.base.BaseModelActivity;

public class LoadImageActivity extends BaseModelActivity {

    private ImageView imageView;

    @Override
    public int getContentViewLayout() {
        return R.layout.activity_load_image;
    }

    @Override
    public void initView() {
        imageView = findViewById(R.id.iv_image);
    }

    @Override
    public void initLogic() {
        Glide.with(this)
                .load("https://pic.rmb.bdstatic.com/bjh/news/dfc8c7b3aab3160325143a1336a215a4.jpeg").into(imageView);
    }
}