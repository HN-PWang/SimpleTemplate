package com.example.basemodel.base;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;

/**
 * @auther: pengwang
 * @date: 2023/5/5
 * @description:
 */
public class BasePresenter {

    protected LifecycleOwner lifecycleOwner;

    protected ViewModel viewModel;

    public BasePresenter(LifecycleOwner lifecycleOwner, ViewModel vm) {
        this.lifecycleOwner = lifecycleOwner;
        this.viewModel = vm;
    }

}
