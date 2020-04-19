package com.example.baselibrary.view;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.baselibrary.R;


/**
 * Created by jwtao on 2016/8/16.
 */
public class RiceCakeLoading extends AlertDialog {

    private ImageView ivLoading;
    private TextView tvTxt;

    public RiceCakeLoading(Context context) {
        this(context, 0);
    }

    public RiceCakeLoading(Context context, int themeResId) {
        super(context, themeResId);
    }

    public RiceCakeLoading(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_rice_cake_loading);
        ivLoading = findViewById(R.id.iv_loading);
        tvTxt = findViewById(R.id.tv_loading);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void show() {
        super.show();
        if (ivLoading == null) {
            ivLoading = findViewById(R.id.iv_loading);
        }
        AnimationDrawable animationDrawable = (AnimationDrawable) ivLoading.getDrawable();
        animationDrawable.start();
    }

    @Override
    public void dismiss() {
        if (ivLoading != null && ivLoading.getDrawable() != null) {
            AnimationDrawable animationDrawable = (AnimationDrawable) ivLoading.getDrawable();
            animationDrawable.stop();
        }
        super.dismiss();
    }

    public void showText(String msg) {
        if (TextUtils.isEmpty(msg)) {
            tvTxt.setVisibility(View.GONE);
        } else {
            tvTxt.setText(msg);
            tvTxt.setVisibility(View.VISIBLE);
        }
    }
}
