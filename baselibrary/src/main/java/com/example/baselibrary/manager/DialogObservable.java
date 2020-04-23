package com.example.baselibrary.manager;

import android.app.AlertDialog;
import android.content.DialogInterface;

import com.example.baselibrary.utils.SPUtils;
import com.example.baselibrary.utils.TimeUtils;
import com.example.baselibrary.utils.Util;

/**
 * Created by zyh
 * on 2020/4/23
 */
public class DialogObservable extends BaseDialogObservable {
    private String TAG = "DialogObservable";

    public DialogObservable(int priority) {
        super(priority);
    }

    @Override
    public void showDialog(DialogData value) {
        if (null != mActivity) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
            builder.setMessage(value.msg);
            builder.setTitle("活动弹框");
            builder.setPositiveButton("确定" + mPriority, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
            SPUtils.put(TAG + mPriority, TimeUtils.getFormatDate1());
        }
    }

    public boolean canShow() {
        String KEY = TAG + mPriority;
        if (Util.isGreaterThanDaysTime(KEY, 1)) {
            return true;
        }
        return false;

    }
}
