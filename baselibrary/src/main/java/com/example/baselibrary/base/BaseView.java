package com.example.baselibrary.base;


public interface BaseView {
    void showLoading();

    void dissLoading();

    void showDialogLoading();

    void dissDialogLoading();

    void showNetError();

    void toast(String text);

    void showRightImageToast(String text);

    void showWrongImageToast(String text);
}
