package com.example.module_mine.mine;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.baselibrary.base.BasePresenter;
import com.example.baselibrary.common.BaseCommonDataBindingFragment;
import com.example.module_mine.R;
import com.example.module_mine.adapter.MessageAdapter;
import com.example.module_mine.databinding.FragmentMineBinding;
import com.example.module_route.MineRoutePath;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * Created by zyh
 * on 2020/4/19
 */
@Route(path = MineRoutePath.MINE_FRAGMENT)
public class MineFragment extends BaseCommonDataBindingFragment<FragmentMineBinding, BasePresenter> {
    MessageAdapter mAdapter;
    private List<String> mData = new ArrayList<>();

    {
        mData.add("测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试");
        mData.add("测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试");
        mData.add("测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试");
        mData.add("测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试");
        mData.add("测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试");
        mData.add("测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试");
        mData.add("测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试");
        mData.add("测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试");
        mData.add("测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试");
        mData.add("测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试");
        mData.add("测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试");
        mData.add("测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试");
    }

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
        mViewBinding.recycleview.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new MessageAdapter(mData);
        mViewBinding.recycleview.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}
