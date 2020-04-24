package com.example.baselibrary.common;

import androidx.databinding.ViewDataBinding;
import android.view.Gravity;
import android.view.View;

import com.example.baselibrary.R;
import com.example.baselibrary.base.BaseDataBindingFragment;
import com.example.baselibrary.base.BasePresenter;
import com.example.baselibrary.utils.PromptManager;
import com.example.baselibrary.view.RiceCakeLoading;
import com.example.baselibrary.view.YMLToolbar;


/**
 * Created by yuepeng on 2017/12/13.
 */

public abstract class BaseCommonDataBindingFragment<B extends ViewDataBinding, T extends BasePresenter> extends BaseDataBindingFragment<B, T> {
    protected YMLToolbar ymlToolbar;
    private RiceCakeLoading riceCakeLoading;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * 神策数据统计  禁止删除
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        if (riceCakeLoading != null) {
            riceCakeLoading.dismiss();
            riceCakeLoading = null;
        }
        super.onDestroy();
    }


    public void initToolBar(View view, String title) {
        ymlToolbar = view.findViewById(R.id.toolbar);
        ymlToolbar.setTitle(title);
        ymlToolbar.getLeftImage().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popBackStack();
            }
        });
    }

    public void initToolBar(View view, int stringId) {
        initToolBar(view, getString(stringId));
    }

    public void showLoadingDialog(String msg) {
        if (riceCakeLoading == null) {
            riceCakeLoading = new RiceCakeLoading(mContext);
        }
        if (!riceCakeLoading.isShowing() && !mContext.isFinishing()) {
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
    public void showRightImageToast(String text) {
        PromptManager.showSuccessImageToast(mContext, text);
    }

    @Override
    public void showWrongImageToast(String text) {
        PromptManager.showWarnImageToast(mContext, text);
    }

}
