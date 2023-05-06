package com.example.common.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.common.R;

/**
 * @auther: pengwang
 * @date: 2023/5/6
 * @description:
 */
public class MessageDialog extends Dialog {

    public MessageDialog(@NonNull Context context, String title, String message) {
        super(context, R.style.StandardDialog);
        setContentView(R.layout.dialog_message_layout);

        Window window = getWindow();
        if (window == null)
            return;

        window.setGravity(Gravity.CENTER);

        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);

        setCancelable(true);
        setCanceledOnTouchOutside(true);

        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvMessage = findViewById(R.id.tv_message);
        TextView tvCancel = findViewById(R.id.tv_cancel);
        TextView tvConfirm = findViewById(R.id.tv_confirm);

        tvTitle.setText(title);
        tvMessage.setText(message);

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

}
