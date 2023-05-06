package com.example.common.base;

import android.os.Bundle;

import androidx.activity.ComponentActivity;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.common.impl.LoadingOwnerImpl;
import com.example.common.interfaces.LoadingOwner;
import com.example.common.interfaces.PageCanonical;

/**
 * @auther: pengwang
 * @date: 2023/4/27
 * @description:
 */
public abstract class BaseActivity extends AppCompatActivity implements LoadingOwner, PageCanonical {

    private LoadingOwner mLoadingOwner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewLayout());
        mLoadingOwner = new LoadingOwnerImpl(this, this);

        initView();

        initLogic();
    }

    @Override
    public void showLoading(String content, boolean canHide) {
        mLoadingOwner.showLoading(content, canHide);
    }

    @Override
    public void showLoading(String content) {
        mLoadingOwner.showLoading(content);
    }

    @Override
    public void showLoading() {
        mLoadingOwner.showLoading();
    }

    @Override
    public void hideLoading() {
        mLoadingOwner.hideLoading();
    }

}
