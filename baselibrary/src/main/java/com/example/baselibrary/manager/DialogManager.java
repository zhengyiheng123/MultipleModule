package com.example.baselibrary.manager;

import android.app.Activity;

import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by zyh
 * on 2020/4/23
 */
public class DialogManager {
    private DialogObservable[] mDialogObservables = {new DialogObservable(1), new DialogObservable(2)
            , new DialogObservable(3), new DialogObservable(4)};


    public DialogManager() {
    }

    /**
     * 循环从队列中取出弹框对象
     */
    public void showDialog(Activity activity) {
        for (int i = 0; i < mDialogObservables.length; i++) {
            DialogObservable observable = mDialogObservables[i];
            if (observable.canShow()) {
                observable.setActivity(activity);
                observable.subscribe();
                break;
            }

        }
    }
}
