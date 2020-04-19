package com.example.baselibrary.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.baselibrary.R;
import com.example.baselibrary.utils.StatusBarUtils;


/**
 * Created by wujing on 15/4/30.
 */
public class YMLToolbar extends LinearLayout {
    public TextView tvLeft = null;
    public TextView tvRight = null;
    public TextView tvTitle = null;
    public Context context;
    /**
     * 新加 logo图片
     */
    public ImageView ivLogo = null;
    /**
     * 右侧图片
     */
    ImageView ivRight = null;
    /**
     * 左侧图片
     */
    ImageView ivLeft = null;
    View blankView;
    private View statusView;
    private LinearLayout title_bar;

    public YMLToolbar(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.part_title_bar, this);
        tvLeft = findViewById(R.id.tv_title_bar_left);
        tvRight = findViewById(R.id.tv_title_bar_right);
        tvTitle = findViewById(R.id.tv_part_title_name);
        ivRight = findViewById(R.id.iv_title_bar_right);
        ivLeft = findViewById(R.id.iv_title_bar_left);
        ivLogo = findViewById(R.id.iv_logo);
        title_bar = findViewById(R.id.fragment_title_bar);
        statusView = findViewById(R.id.statusView);
    }

    public void setLeftText(String text) {
        tvLeft.setText(text);
        tvLeft.setVisibility(VISIBLE);
    }

    public void showLogo(String logoPath) {
        if (TextUtils.isEmpty(logoPath)) {
            ivLogo.setVisibility(GONE);
            Log.e("log0", "hide");
        } else {
            ivLogo.setVisibility(VISIBLE);
            Log.e("log0", "show");
        }

        ivLogo.setTag(logoPath);
    }

    public boolean logoIsNeedChange(String currentPath) {
        Object object = ivLogo.getTag();
        if (object == null) return true;
        String path = (String) object;
        if (path == "") return true;
        return !currentPath.equals(path);
    }


    public View setRightText(String text) {
        tvRight.setText(text);
        tvRight.setVisibility(VISIBLE);
        return tvRight;
    }

    public void setRightTextColor(int Color) {
        tvRight.setTextColor(Color);
    }

    public void setBlankViewVisible(boolean flag) {
        if (!flag) {
            blankView.setVisibility(GONE);
        }
    }

    public ImageView getLeftImage() {
        ivLeft.setVisibility(View.VISIBLE);
        return ivLeft;
    }

    public void setLeftImage(int resId) {
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setImageResource(resId);
    }

    public void setTitleSize(int size) {
        tvTitle.setTextSize(size);
    }

    public String getTitle() {
        return tvTitle.getText() == null ? "" : tvTitle.getText().toString();
    }

    public void setTitle(String text) {
        tvTitle.setText(text);
        if(context instanceof Activity){
            ((Activity) context).setTitle(text);
        }
    }

    public void setTitleTextColor(int colorId){
        tvTitle.setTextColor(colorId);
    }

    public void setTitleBgColor(int colorId){
        title_bar.setBackgroundColor(colorId);
    }

    public ImageView getRightImage() {
        ivRight.setVisibility(View.VISIBLE);
        return ivRight;
    }

    public void setRightImage(int resid) {
        ivRight.setVisibility(View.VISIBLE);
        ivRight.setBackgroundResource(resid);
    }

    public TextView getLeftButton() {
        return tvLeft;
    }

    public TextView getRightButton() {
        return tvRight;
    }

    public TextView getRightTv() {
        tvRight.setVisibility(View.VISIBLE);
        return tvRight;
    }

    public TextView getLeftTv() {
        tvLeft.setVisibility(View.VISIBLE);
        return tvLeft;
    }

    public LinearLayout getTitleBgView() {
        return title_bar;
    }

    public TextView getTitleTV() {
        return tvTitle;
    }

    public void setStatusTransparentAble(Activity activity) {
        statusView.getLayoutParams().height = StatusBarUtils.getStatusBarHeight(activity);
    }

    public void setWhiteTheme(){
        title_bar.setBackgroundColor(Color.parseColor("#ffffff"));
        ivLeft.setImageResource(R.drawable.icon_left_black);
        tvTitle.setTextColor(Color.parseColor("#333333"));
    }

    public View getStatusView() {
        return statusView;
    }
}
