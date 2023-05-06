package com.example.common.utils.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.common.CommonApplication;
import com.example.common.R;


/**
 * 吐司工具类
 */
public class ToastUtils {

    public static void show(int res) {
        show(CommonApplication.getContext().getString(res));
    }

    public static void show(String text) {
        show(text,Toast.LENGTH_SHORT);
    }

    public static void show(String text, int duration) {
        showCustomToast(CommonApplication.getContext(), text, duration);
    }

    private static void showCustomToast(Context context, String text, int duration) {
        //不用吝惜toast的view内存占有,会很快被清理的,每次进来都新建,避免has already been added to the window manager.
        Toast mToast = new Toast(context);
        LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View toastRootView = inflate.inflate(R.layout.toast_custom_layout, null);
        mToast.setView(toastRootView);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setDuration(duration);
        if (toastRootView != null) {
            TextView textView = toastRootView.findViewById(R.id.tv_text);
            textView.setText(text);
        }
        mToast.show();
    }

}
