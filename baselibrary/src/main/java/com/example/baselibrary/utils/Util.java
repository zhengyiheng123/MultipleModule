package com.example.baselibrary.utils;

import android.text.TextUtils;

/**
 * Created by zyh
 * on 2020/4/23
 */
public class Util {
    public static boolean isGreaterThanDaysTime(String sharedKey, int days) {

        final String currDate = TimeUtils.getFormatDate(TimeUtils.DATE_FMT);
        String lastDate = SPUtils.get(sharedKey, "");
        if (TextUtils.isEmpty(lastDate)) {//第一次
            return true;
        }
        int mDays = TimeUtils.getTwoDay(currDate, lastDate);
        return mDays >= days;
    }

}
