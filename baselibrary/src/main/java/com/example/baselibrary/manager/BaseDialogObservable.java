package com.example.baselibrary.manager;

import android.app.Activity;


import java.lang.Comparable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by zyh
 * on 2020/4/23
 */
public abstract class BaseDialogObservable implements Comparable<DialogObservable> {
    protected int mPriority;
    protected Observable<DialogData> mObservable;
    protected Activity mActivity;

    public BaseDialogObservable(int priority) {
        mPriority = priority;
        mObservable = createObservable();
    }

    protected abstract Observable<DialogData> createObservable();

    @Override
    public int compareTo(DialogObservable o) {
        return o.mPriority - mPriority;
    }

    public void setActivity(Activity activity) {
        mActivity = activity;
    }

    /**
     * 订阅
     */
    public void subscribe() {
        if (null != mObservable) {
            mObservable.subscribe(observer);
        }
    }

    private Observer<DialogData> observer = new Observer<DialogData>() {
        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onNext(DialogData value) {

            showDialog(value);
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {

        }
    };

    public abstract void showDialog(DialogData data);
}
