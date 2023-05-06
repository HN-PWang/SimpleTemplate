package com.example.common.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.common.R;

/**
 * @auther: pengwang
 * @date: 2023/5/5
 * @description:
 */
public class LoadingDialog extends Dialog {

    public LoadingDialog(@NonNull Context context, String msg, boolean canHide) {
        super(context, R.style.LoadingDialog);
        setContentView(R.layout.dialog_loading_layout);

        Window window = getWindow();
        if (window == null)
            return;

        window.setGravity(Gravity.CENTER);

        WindowManager.LayoutParams params = window.getAttributes();

        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);

        setCancelable(canHide);
        setCanceledOnTouchOutside(canHide);

        TextView tvMsg = findViewById(R.id.tv_loading_msg);
        tvMsg.setText(msg);
    }

}
