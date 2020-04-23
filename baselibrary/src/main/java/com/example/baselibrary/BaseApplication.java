package com.example.baselibrary;

import android.app.Application;

import com.example.baselibrary.manager.DialogManager;

/**
 * Created by zyh
 * on 2020/4/19
 */
public class BaseApplication extends Application {
    private static BaseApplication context;
    private DialogManager dialogManager;


    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        dialogManager = new DialogManager();

    }

    public DialogManager getDialogManager() {
        return dialogManager;
    }

    public static BaseApplication getContext() {
        return context;
    }
}
