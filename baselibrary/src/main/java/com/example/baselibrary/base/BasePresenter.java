package com.example.baselibrary.base;


public abstract class BasePresenter<V> {

    protected V mView;

    public void setView(V v) {
        this.mView = v;
        onAttached();
    }

    public abstract void onAttached();

    protected void onDetached() {

    }

}
