package com.example.module_mine.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.module_mine.R;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by zyh
 * on 2020/4/24
 */
public class MessageAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public MessageAdapter(@Nullable List<String> data) {
        super(R.layout.item_message, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder helper, String s) {
        helper.setText(R.id.tv_content, s);
    }
}
