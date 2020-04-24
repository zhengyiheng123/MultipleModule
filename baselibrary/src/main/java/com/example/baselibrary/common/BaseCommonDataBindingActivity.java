package com.example.baselibrary.common;

import android.content.Intent;
import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;

import com.example.baselibrary.R;
import com.example.baselibrary.base.BaseDataBindingActivity;
import com.example.baselibrary.base.BasePresenter;
import com.example.baselibrary.utils.PromptManager;
import com.example.baselibrary.view.RiceCakeLoading;
import com.example.baselibrary.view.YMLToolbar;



/**
 * Created by yuepeng on 2017/12/12.
 */

public abstract class BaseCommonDataBindingActivity<B extends ViewDataBinding, T extends BasePresenter> extends BaseDataBindingActivity<B, T> {
    protected YMLToolbar ymlToolbar;
    private RiceCakeLoading riceCakeLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isApplyKitKatTranslucency()) {
            setSystemBarTintDrawable(getResources().getDrawable(defaultThemeId()));
            ((FrameLayout) getWindow().getDecorView().findViewById(android.R.id.content)).getChildAt(0).setFitsSystemWindows(true);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (riceCakeLoading != null) {
            riceCakeLoading.dismiss();
            riceCakeLoading = null;
        }
        super.onDestroy();
    }

    protected boolean isApplyKitKatTranslucency() {
        return true;
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    public int defaultThemeId() {
        return R.drawable.shape_black;
    }

    public void initToolBar(String title) {
        ymlToolbar = findViewById(R.id.toolbar);
        ymlToolbar.setTitle(title);
        ymlToolbar.getLeftImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
    }

    public void initToolBar(int stringId) {
        initToolBar(getString(stringId));
    }

    public void showLoadingDialog(String msg) {
        if (riceCakeLoading == null) {
            riceCakeLoading = new RiceCakeLoading(this);
            riceCakeLoading.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        }
        if (!riceCakeLoading.isShowing() && !this.isFinishing()) {
            riceCakeLoading.show();
            riceCakeLoading.showText(msg);
            riceCakeLoading.getWindow().setGravity(Gravity.CENTER);
        }

    }

    public void showLoadingDialog() {
        showLoadingDialog("");
    }

    public void dismissLoadingDialog() {
        if (riceCakeLoading != null && riceCakeLoading.isShowing()) {
            riceCakeLoading.dismiss();
            riceCakeLoading = null;
        }
    }

    @Override
    public void showLoading() {
        toggleShowLoading();
    }

    @Override
    public void dissLoading() {
        toggleRestore();
    }

    @Override
    public void showDialogLoading() {
        showLoadingDialog("");
    }

    @Override
    public void dissDialogLoading() {
        dismissLoadingDialog();
    }

    @Override
    public void showNetError() {
        toggleNetworkError(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.onAttached();
            }
        });
    }

    @Override
    public void toast(String text) {
        showToast(text);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void showRightImageToast(String text) {
        PromptManager.showSuccessImageToast(mContext, text);
    }

    @Override
    public void showWrongImageToast(String text) {
        PromptManager.showWarnImageToast(mContext, text);
    }

}
