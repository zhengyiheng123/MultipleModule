package com.example.module_main.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.baselibrary.BaseApplication;
import com.example.baselibrary.base.BasePresenter;
import com.example.baselibrary.common.BaseCommonDataBindingActivity;
import com.example.baselibrary.common.BaseCommonDataBindingFragment;
import com.example.module_main.R;
import com.example.module_main.databinding.ActivityMainBinding;
import com.example.module_route.HomeRoutePath;
import com.example.module_route.MainRoutePath;
import com.example.module_route.MineRoutePath;

import java.util.ArrayList;
import java.util.List;

@Route(path = MainRoutePath.MAIN_ACTIVITY)
public class MainActivity extends BaseCommonDataBindingActivity<ActivityMainBinding, BasePresenter> {
    List<BaseCommonDataBindingFragment> fragments = new ArrayList<>();

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {
        initFragments();
        BaseApplication.getContext().getDialogManager().showDialog(MainActivity.this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tab_mine) {
            mViewBinding.viewPager.setCurrentItem(0, false);
        } else if (v.getId() == R.id.tab_home) {
            mViewBinding.viewPager.setCurrentItem(1, false);

        }
    }

    private void initFragments() {
        BaseCommonDataBindingFragment mineFragment = (BaseCommonDataBindingFragment) ARouter.getInstance().build(MineRoutePath.MINE_FRAGMENT).navigation();
        BaseCommonDataBindingFragment homeFragment = (BaseCommonDataBindingFragment) ARouter.getInstance().build(HomeRoutePath.HOME_FRAGMENT).navigation();
        fragments.add(mineFragment);
        fragments.add(homeFragment);

        mViewBinding.viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });
    }
}
