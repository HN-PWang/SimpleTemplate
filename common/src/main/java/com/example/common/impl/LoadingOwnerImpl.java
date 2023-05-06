package com.example.common.impl;

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import com.example.common.interfaces.LoadingOwner;
import com.example.common.widget.LoadingDialog;

import java.lang.ref.WeakReference;

/**
 * @auther: pengwang
 * @date: 2023/4/28
 * @description:
 */
public class LoadingOwnerImpl implements LoadingOwner {

    private Context mContext;

    private LifecycleOwner mLifecycleOwner;

    private Dialog mLoadingDialog;

    public LoadingOwnerImpl(Context mContext, LifecycleOwner lifecycleOwner) {
        this.mContext = mContext;
        this.mLifecycleOwner = lifecycleOwner;

        mLifecycleOwner.getLifecycle().addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner source
                    , @NonNull Lifecycle.Event event) {
                if (event.equals(Lifecycle.Event.ON_DESTROY)) {
                    toDismissDialog();
                }
            }
        });
    }

    @Override
    public void showLoading(String content, boolean canHide) {
        showLoadingDialog(content, canHide);
    }

    @Override
    public void showLoading(String content) {
        showLoading(content, true);
    }

    @Override
    public void showLoading() {
        showLoading("loading...", true);
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    private synchronized void showLoadingDialog(String content, boolean canHide) {
        if (mLifecycleOwner == null)
            return;

        Lifecycle.State state = mLifecycleOwner.getLifecycle().getCurrentState();
        if (state.isAtLeast(Lifecycle.State.CREATED)) {
            if (mLoadingDialog == null) {
                mLoadingDialog = buildLoadDialog(content, canHide);
                mLoadingDialog.show();
            } else {
                if (!mLoadingDialog.isShowing()) {
                    mLoadingDialog.show();
                }
            }
        }
    }

    private synchronized void hideLoadingDialog() {
        if (mLifecycleOwner == null)
            return;

        Lifecycle.State state = mLifecycleOwner.getLifecycle().getCurrentState();
        if (state.isAtLeast(Lifecycle.State.CREATED)) {
            if (mLoadingDialog != null
                    && mLoadingDialog.isShowing()) {
                mLoadingDialog.hide();
            }
        }
    }

    private synchronized void toDismissDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    private Dialog buildLoadDialog(String content, boolean canHide) {
        Dialog dialog = new LoadingDialog(mContext, content, canHide);

        return dialog;
    }

}
