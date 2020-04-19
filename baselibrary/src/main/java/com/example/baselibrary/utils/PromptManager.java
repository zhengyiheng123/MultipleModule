package com.example.baselibrary.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.baselibrary.R;

/**
 * 提示信息的管理
 */

public class PromptManager {

    private static Toast mToast;

    public static void showCustomToast(Context context, CharSequence text) {
        if (mToast == null) {
            Context appContext = context.getApplicationContext();//mToast不会释放，避免内存leak
            mToast = new Toast(appContext);
            // 获取LayoutInflater对象
            LayoutInflater inflater = (LayoutInflater) appContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // 由layout文件创建一个View对象
            View layout = inflater.inflate(R.layout.toast_layout, null);
            mToast.setView(layout);
            mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        }
        mToast.setDuration(Toast.LENGTH_SHORT);
        TextView textView = mToast.getView().findViewById(R.id.text0);
        textView.setText(text);
        mToast.show();
    }

    public static void showWarnImageToast(Context context, CharSequence text) {
        View view = View.inflate(context, R.layout.image_toast_layout, null);
        ImageView toastIv = view.findViewById(R.id.toast_iv);
        TextView toastTv = view.findViewById(R.id.toast_tv);
        Toast toast = new Toast(context);
        toastIv.setImageResource(R.drawable.icon_prompt_warn);
        toastTv.setText(text);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        //将布局管理器添加进Toast
        toast.setView(view);
        toast.show();
    }

    public static void showSuccessImageToast(Context context, CharSequence text) {
        View view = View.inflate(context, R.layout.image_toast_layout, null);
        ImageView toastIv = view.findViewById(R.id.toast_iv);
        TextView toastTv = view.findViewById(R.id.toast_tv);
        Toast toast = new Toast(context);
        toastIv.setImageResource(R.drawable.icon_prompt_success);
        toastTv.setText(text);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        //将布局管理器添加进Toast
        toast.setView(view);
        toast.show();
    }
}
