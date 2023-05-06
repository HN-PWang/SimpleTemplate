package com.example.common.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.common.impl.LoadingOwnerImpl;
import com.example.common.interfaces.LoadingOwner;
import com.example.common.interfaces.PageCanonical;

/**
 * @auther: pengwang
 * @date: 2023/4/27
 * @description:
 */
public abstract class BaseFragment extends Fragment implements LoadingOwner, PageCanonical {

    private LoadingOwner mLoadingOwner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLoadingOwner = new LoadingOwnerImpl(getContext(), this);
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
