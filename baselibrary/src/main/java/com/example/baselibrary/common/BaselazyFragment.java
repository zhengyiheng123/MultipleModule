package com.example.baselibrary.common;

import androidx.databinding.ViewDataBinding;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;

import com.example.baselibrary.base.BasePresenter;

/**
 * Created by zyh
 * on 2020/4/19
 */
public abstract class BaselazyFragment<B extends ViewDataBinding, T extends BasePresenter> extends BaseCommonDataBindingFragment<B, T> {
    //该页面是否准备完毕（onCreateView是否调用完毕）
    private boolean isPreared;
    //该Fragment是否执行过懒加载
    private boolean isLazyLoad;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isPreared = true;
        lazyLoad();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad();
    }


    final private void lazyLoad() {
        if (getUserVisibleHint() && isPreared && !isLazyLoad) {
            onLazyLoad();
            isLazyLoad = true;
        }
    }

    @UiThread
    public abstract void onLazyLoad();
}
