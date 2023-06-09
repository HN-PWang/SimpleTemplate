package com.example.common.widget.card;

import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * @auther: pengwang
 * @date: 2022/8/17
 * @description:
 */
public interface CardViewDelegate {
    void setCardBackground(Drawable drawable);
    Drawable getCardBackground();
    boolean getUseCompatPadding();
    boolean getPreventCornerOverlap();
    void setShadowPadding(int left, int top, int right, int bottom);
    void setMinWidthHeightInternal(int width, int height);
    View getCardView();
}
