package com.example.common.widget.card;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.common.R;


/**
 * @auther: pengwang
 * @date: 2022/8/17
 * @description:
 */
public class CardConstraintLayout extends ConstraintLayout {

    private static final int[] COLOR_BACKGROUND_ATTR = {android.R.attr.colorBackground};

    private boolean mCompatPadding;

    private boolean mPreventCornerOverlap;

    /**
     * CardView requires to have a particular minimum size to draw shadows before API 21. If
     * developer also sets min width/height, they might be overridden.
     * <p>
     * CardView works around this issue by recording user given parameters and using an internal
     * method to set them.
     */
    int mUserSetMinWidth, mUserSetMinHeight;

    final Rect mContentPadding = new Rect();

    final Rect mShadowBounds = new Rect();

    public CardConstraintLayout(@NonNull Context context) {
        this(context, null);
    }

    public CardConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.cardViewStyle);
    }

    public CardConstraintLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CardConstraintLayout, defStyleAttr,
                R.style.CardView);
        ColorStateList backgroundColor;
        if (a.hasValue(R.styleable.CardConstraintLayout_cardBackgroundColor)) {
            backgroundColor = a.getColorStateList(R.styleable.CardConstraintLayout_cardBackgroundColor);
        } else {
            // There isn't one set, so we'll compute one based on the theme
            final TypedArray aa = getContext().obtainStyledAttributes(COLOR_BACKGROUND_ATTR);
            final int themeColorBackground = aa.getColor(0, 0);
            aa.recycle();

            // If the theme colorBackground is light, use our own light color, otherwise dark
            final float[] hsv = new float[3];
            Color.colorToHSV(themeColorBackground, hsv);
            backgroundColor = ColorStateList.valueOf(hsv[2] > 0.5f
                    ? getResources().getColor(R.color.cardview_light_background)
                    : getResources().getColor(R.color.cardview_dark_background));
        }
        float radius = a.getDimension(R.styleable.CardConstraintLayout_cardCornerRadius, 0);
        float elevation = a.getDimension(R.styleable.CardConstraintLayout_cardElevation, 0);
        float maxElevation = a.getDimension(R.styleable.CardConstraintLayout_cardMaxElevation, 0);
        mCompatPadding = a.getBoolean(R.styleable.CardConstraintLayout_cardUseCompatPadding, false);
        mPreventCornerOverlap = a.getBoolean(R.styleable.CardConstraintLayout_cardPreventCornerOverlap, true);
        int defaultPadding = a.getDimensionPixelSize(R.styleable.CardConstraintLayout_contentPadding, 0);
        mContentPadding.left = a.getDimensionPixelSize(R.styleable.CardConstraintLayout_contentPaddingLeft,
                defaultPadding);
        mContentPadding.top = a.getDimensionPixelSize(R.styleable.CardConstraintLayout_contentPaddingTop,
                defaultPadding);
        mContentPadding.right = a.getDimensionPixelSize(R.styleable.CardConstraintLayout_contentPaddingRight,
                defaultPadding);
        mContentPadding.bottom = a.getDimensionPixelSize(R.styleable.CardConstraintLayout_contentPaddingBottom,
                defaultPadding);
        if (elevation > maxElevation) {
            maxElevation = elevation;
        }
        mUserSetMinWidth = a.getDimensionPixelSize(R.styleable.CardConstraintLayout_android_minWidth, 0);
        mUserSetMinHeight = a.getDimensionPixelSize(R.styleable.CardConstraintLayout_android_minHeight, 0);
        a.recycle();

        initialize(mCardViewDelegate, context, backgroundColor, radius,
                elevation, maxElevation);
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        // NO OP
    }

    @Override
    public void setPaddingRelative(int start, int top, int end, int bottom) {
        // NO OP
    }

    /**
     * Returns whether CardView will add inner padding on platforms Lollipop and after.
     *
     * @return <code>true</code> if CardView adds inner padding on platforms Lollipop and after to
     * have same dimensions with platforms before Lollipop.
     */
    public boolean getUseCompatPadding() {
        return mCompatPadding;
    }

    public void initialize(CardViewDelegate cardView, Context context,
                           ColorStateList backgroundColor, float radius, float elevation, float maxElevation) {
        final RoundRectDrawable background = new RoundRectDrawable(backgroundColor, radius);
        cardView.setCardBackground(background);

        View view = cardView.getCardView();
        view.setClipToOutline(true);
        view.setElevation(elevation);
        setMaxElevation(cardView, maxElevation);
    }

    public void setRadius(CardViewDelegate cardView, float radius) {
        getCardBackground(cardView).setRadius(radius);
    }

    public void initStatic() {
    }

    public void setUseCompatPadding(boolean useCompatPadding) {
        if (mCompatPadding != useCompatPadding) {
            mCompatPadding = useCompatPadding;
            onCompatPaddingChanged(mCardViewDelegate);
        }
    }

    public void setContentPadding(@Px int left, @Px int top, @Px int right, @Px int bottom) {
        mContentPadding.set(left, top, right, bottom);
        updatePadding(mCardViewDelegate);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        switch (widthMode) {
            case MeasureSpec.EXACTLY:
            case MeasureSpec.AT_MOST:
                final int minWidth = (int) Math.ceil(getMinWidth(mCardViewDelegate));
                widthMeasureSpec = MeasureSpec.makeMeasureSpec(Math.max(minWidth,
                        MeasureSpec.getSize(widthMeasureSpec)), widthMode);
                break;
            case MeasureSpec.UNSPECIFIED:
                // Do nothing
                break;
        }

        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
            case MeasureSpec.AT_MOST:
                final int minHeight = (int) Math.ceil(getMinHeight(mCardViewDelegate));
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(Math.max(minHeight,
                        MeasureSpec.getSize(heightMeasureSpec)), heightMode);
                break;
            case MeasureSpec.UNSPECIFIED:
                // Do nothing
                break;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void setMinimumWidth(int minWidth) {
        mUserSetMinWidth = minWidth;
        super.setMinimumWidth(minWidth);
    }

    @Override
    public void setMinimumHeight(int minHeight) {
        mUserSetMinHeight = minHeight;
        super.setMinimumHeight(minHeight);
    }

    public void setCardBackgroundColor(@ColorInt int color) {
        setBackgroundColor(mCardViewDelegate, ColorStateList.valueOf(color));
    }

    public void setCardBackgroundColor(@Nullable ColorStateList color) {
        setBackgroundColor(mCardViewDelegate, color);
    }

    @NonNull
    public ColorStateList getCardBackgroundColor() {
        return getBackgroundColor(mCardViewDelegate);
    }

    public void setRadius(float radius) {
        setRadius(mCardViewDelegate, radius);
    }

    public float getRadius() {
        return getRadius(mCardViewDelegate);
    }

    public void setCardElevation(float elevation) {
        setElevation(mCardViewDelegate, elevation);
    }

    public float getCardElevation() {
        return getElevation(mCardViewDelegate);
    }

    public void setMaxCardElevation(float maxElevation) {
        setMaxElevation(mCardViewDelegate, maxElevation);
    }

    public float getMaxCardElevation() {
        return getMaxElevation(mCardViewDelegate);
    }

    public boolean getPreventCornerOverlap() {
        return mPreventCornerOverlap;
    }

    public void setPreventCornerOverlap(boolean preventCornerOverlap) {
        if (preventCornerOverlap != mPreventCornerOverlap) {
            mPreventCornerOverlap = preventCornerOverlap;
            onPreventCornerOverlapChanged(mCardViewDelegate);
        }
    }

    public void setMaxElevation(CardViewDelegate cardView, float maxElevation) {
        getCardBackground(cardView).setPadding(maxElevation,
                cardView.getUseCompatPadding(), cardView.getPreventCornerOverlap());
        updatePadding(cardView);
    }

    public float getMaxElevation(CardViewDelegate cardView) {
        return getCardBackground(cardView).getPadding();
    }

    public float getMinWidth(CardViewDelegate cardView) {
        return getRadius(cardView) * 2;
    }

    public float getMinHeight(CardViewDelegate cardView) {
        return getRadius(cardView) * 2;
    }

    public float getRadius(CardViewDelegate cardView) {
        return getCardBackground(cardView).getRadius();
    }

    public void setElevation(CardViewDelegate cardView, float elevation) {
        cardView.getCardView().setElevation(elevation);
    }

    public float getElevation(CardViewDelegate cardView) {
        return cardView.getCardView().getElevation();
    }

    public void updatePadding(CardViewDelegate cardView) {
        if (!cardView.getUseCompatPadding()) {
            cardView.setShadowPadding(0, 0, 0, 0);
            return;
        }
        float elevation = getMaxElevation(cardView);
        final float radius = getRadius(cardView);
        int hPadding = (int) Math.ceil(RoundRectDrawableWithShadow
                .calculateHorizontalPadding(elevation, radius, cardView.getPreventCornerOverlap()));
        int vPadding = (int) Math.ceil(RoundRectDrawableWithShadow
                .calculateVerticalPadding(elevation, radius, cardView.getPreventCornerOverlap()));
        cardView.setShadowPadding(hPadding, vPadding, hPadding, vPadding);
    }

    public void onCompatPaddingChanged(CardViewDelegate cardView) {
        setMaxElevation(cardView, getMaxElevation(cardView));
    }

    public void onPreventCornerOverlapChanged(CardViewDelegate cardView) {
        setMaxElevation(cardView, getMaxElevation(cardView));
    }

    public void setBackgroundColor(CardViewDelegate cardView, @Nullable ColorStateList color) {
        getCardBackground(cardView).setColor(color);
    }

    public ColorStateList getBackgroundColor(CardViewDelegate cardView) {
        return getCardBackground(cardView).getColor();
    }

    private RoundRectDrawable getCardBackground(CardViewDelegate cardView) {
        return ((RoundRectDrawable) cardView.getCardBackground());
    }

    private final CardViewDelegate mCardViewDelegate = new CardViewDelegate() {
        private Drawable mCardBackground;

        @Override
        public void setCardBackground(Drawable drawable) {
            mCardBackground = drawable;
            setBackgroundDrawable(drawable);
        }

        @Override
        public boolean getUseCompatPadding() {
            return CardConstraintLayout.this.getUseCompatPadding();
        }

        @Override
        public boolean getPreventCornerOverlap() {
            return CardConstraintLayout.this.getPreventCornerOverlap();
        }

        @Override
        public void setShadowPadding(int left, int top, int right, int bottom) {
            mShadowBounds.set(left, top, right, bottom);
            setPadding(left + mContentPadding.left, top + mContentPadding.top,
                    right + mContentPadding.right, bottom + mContentPadding.bottom);
        }

        @Override
        public void setMinWidthHeightInternal(int width, int height) {
            if (width > mUserSetMinWidth) {
                setMinimumWidth(width);
            }
            if (height > mUserSetMinHeight) {
                setMinimumHeight(height);
            }
        }

        @Override
        public Drawable getCardBackground() {
            return mCardBackground;
        }

        @Override
        public View getCardView() {
            return CardConstraintLayout.this;
        }
    };
}
