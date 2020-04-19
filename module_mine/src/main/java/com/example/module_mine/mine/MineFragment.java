package com.example.module_mine.mine;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.baselibrary.base.BasePresenter;
import com.example.baselibrary.common.BaseCommonDataBindingFragment;
import com.example.module_mine.R;
import com.example.module_mine.databinding.FragmentMineBinding;
import com.example.module_route.MineRoutePath;

/**
 * Created by zyh
 * on 2020/4/19
 */
@Route(path = MineRoutePath.MINE_FRAGMENT)
public class MineFragment extends BaseCommonDataBindingFragment<FragmentMineBinding, BasePresenter> {
    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_mine;
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
