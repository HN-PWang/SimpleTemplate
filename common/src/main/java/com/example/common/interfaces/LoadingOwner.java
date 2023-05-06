package com.example.common.interfaces;

/**
 * @auther: pengwang
 * @date: 2023/4/27
 * @description:
 */
public interface LoadingOwner {

    void showLoading(String content, boolean canHide);

    void showLoading(String content);

    void showLoading();

    void hideLoading();

}
