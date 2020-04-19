package com.example.module_home.home;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.baselibrary.base.BasePresenter;
import com.example.baselibrary.common.BaseCommonDataBindingFragment;
import com.example.module_home.R;
import com.example.module_home.databinding.FragmentHomeBinding;
import com.example.module_route.HomeRoutePath;

/**
 * Created by zyh
 * on 2020/4/19
 */
@Route(path = HomeRoutePath.HOME_FRAGMENT)
public class HomeFragment extends BaseCommonDataBindingFragment<FragmentHomeBinding, BasePresenter> {
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    public void onClick(View v) {

    }
}
