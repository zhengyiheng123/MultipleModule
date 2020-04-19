package com.example.multiplemodule;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.baselibrary.BaseApplication;

/**
 * Created by zyh
 * on 2020/4/19
 */
public class App extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        initArouter();
    }

    //Arouter
    private void initArouter() {
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
