package com.example.multiplemodule;

import androidx.databinding.DataBindingUtil;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.WindowManager;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_route.MainRoutePath;
import com.example.multiplemodule.databinding.ActivitySpalshBinding;

/**
 * Created by zyh
 * on 2020/4/19
 */
public class SplashActivity extends AppCompatActivity {

    private ActivitySpalshBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_spalsh);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_spalsh);
        mBinding.tvText.postDelayed(new Runnable() {
            @Override
            public void run() {
                ARouter.getInstance().build(MainRoutePath.MAIN_ACTIVITY).navigation();
                finish();
            }
        }, 1500);
    }
}
