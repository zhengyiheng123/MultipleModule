package com.example.baselibrary.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@SuppressLint("SimpleDateFormat")
public class TimeUtils {
    public static final String FMT = "yyyy-MM-dd HH:mm:ss";
    public static final String _FMT = "yyyy-MM-dd HH:mm";
    public static final String YM = "yyyy-MM";
    public static final String C_FMT = "yyyy年MM月dd日HH时mm分ss秒";
    public static final String L_FMT = "yyyyMMddHHmmssSSS";
    public static final String L_DATE_FMT = "yyyyMMdd";
    public static final String DATE_FMT = "yyyy-MM-dd";
    public static final String DATE_FMT2 = "yyyy年MM月dd日";
    public static final String DATE_FMT3 = "yyyy年MM月";
    public static final String DATE_FMT4 = "yyyy年MM月dd日 HH:mm:ss";
    public static final String DATE_FMT5 = "MM月dd日 HH:mm";
    public static final String DATE_FMT6 = "MM月dd日H时";
    public static final String DATE_FMT7 = "yyyy.MM.dd日";
    public static final String HM = "HH:mm";
    public static final String H = "HH";
    public static final String MH = "M月dd日";
    public static final String _FMT_ = "yy/MM/dd HH:mm";
    public static final String MDH = "M月dd日HH时";
    public static final String MDHM="M月dd日HH:mm";
    private final static long sec = 1000;//一秒
    private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天
    private final static long month = 31 * day;// 月
    private final static long year = 12 * month;// 年
    public static final String MD = "M-dd";

    public static void main(String[] args) {
        System.out.println(getFormatDate("HH:mm:ss"));
    }

    /**
     * @param format 指定的日期格式<br>
     *               eg:<br>
     *               "yyyy-MM-dd HH:mm:ss"<br>
     *               "yyyy-MM-dd"<br>
     *               "yyyyMMddHHmmss"<br>
     *               "HH:mm:ss"<br>
     * @return
     */
    public static String getFormatDate(String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    public static String getTimeStamp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        long timeMilllis = System.currentTimeMillis();
        return simpleDateFormat.format(timeMilllis);
    }

    public static String getUnixTimeStamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    /**
     * 判断时间是不是今天
     *
     * @param date yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static boolean isToday(String date) {
        boolean isToday = true;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {

            if (TextUtils.isEmpty(date)) {
                return false;
            }

            Date parse = sf.parse(date + "");

            Date today = new Date();
            if (today.getYear() != parse.getYear()) {
                isToday = false;
            }

            if (today.getMonth() != parse.getMonth()) {
                isToday = false;
            }
            if (today.getDay() != parse.getDay()) {
                isToday = false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
            isToday = false;
        }

        return isToday;

    }

    /**
     * @param date1 yyyy-MM-dd HH:mm:ss
     * @param date2 yyyy-MM-dd HH:mm:ss
     * @return
     */

    public static boolean isSameDate(String date1, String date2) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date_1 = null;
        Date date_2 = null;
        boolean isSameDate = false;

        try {
            date_1 = sf.parse(date1);
            date_2 = sf.parse(date2);


            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date_1);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date_2);

            boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                    .get(Calendar.YEAR);
            boolean isSameMonth = isSameYear
                    && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
            isSameDate = isSameMonth
                    && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                    .get(Calendar.DAY_OF_MONTH);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return isSameDate;
    }

    /**
     * 将long类型的日期转化为需要日期格式
     *
     * @param date
     * @return
     */
    public static String getDateStringForLong(Date date, String FMT) {
        SimpleDateFormat sdf = new SimpleDateFormat(FMT);
        return sdf.format(date);
    }

    /**
     * 将long类型的日期转化为需要日期格式
     *
     * @param date
     * @return
     */
    public static String getDateStringForLongForUnix(long date, String FMT) {
        Date Datz = new Date(date * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat(FMT);
        return sdf.format(Datz);
    }

    /**
     * 将long类型的日期转化为需要日期格式
     *
     * @param date
     * @return
     */
    public static String getDateStringForLongForUnix(String date, String FMT) {
        long l = Long.parseLong(date);
        Date Datz = new Date(l * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat(FMT);
        return sdf.format(Datz);
    }

    /**
     * 将数据库中的Long类型时间转换为date
     *
     * @param date
     * @return
     */
    public static Date getDateByLong(Date date, String FMT) {
        SimpleDateFormat sf = new SimpleDateFormat(FMT, Locale.ENGLISH);
        try {
            return sf.parse(date + "");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * 获得当前日期时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getFormatDate1() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    /**
     * 获得当前日期时间
     *
     * @return yyyyMMddHHmmss
     */
    public static String getFormatDate2() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    /**
     * 将日期格式 yyyy-M-d 转化为yyyyMMdd
     *
     * @param date yyyy-M-d
     * @return yyyyMMdd
     */
    public static String getFormatDate3(String date) {

        String year = date.split("-")[0];
        String month = date.split("-")[1];
        String day = date.split("-")[2];
        if (month.length() == 1) {
            month = "0" + month;
        }
        if (day.length() == 1) {
            day = "0" + day;
        }
        return year + month + day;

    }

    /**
     * 将日期格式 yyyyMMdd 转化为 yyyy-MM-dd
     *
     * @param date yyyyMMdd
     * @return yyyy-MM-dd
     */
    public static String getFormatDate4(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);
        return year + "-" + month + "-" + day;
    }

    /**
     * 将日期格式 YYYYMMDDHHMMSS 转化为 YYYY-MM-DD HH:MM:SS
     *
     * @param date YYYYMMDDHHMMSS
     * @return YYYY-MM-DD HH:MM:SS
     */
    public static String getFormatDate5(String date) {
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);
        String hour = date.substring(8, 10);
        String min = date.substring(10, 12);
        String sec = date.substring(12, 14);
        return year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec;
    }

    /**
     * 获得当前时间
     *
     * @return HH:mm
     */
    public static String getFormatTime1() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    /**
     * 获得当前时间
     *
     * @return HH:mm:ss
     */
    public static String getFormatTime2() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    /**
     * 将时间格式 HH:mm 转化为 HHmm
     *
     * @return HHmm
     */
    public static String getFormatTime3(String time) {
        return time.replaceAll(":", "");
    }

    /**
     * 将时间格式 HHmm 转化为 HH:mm
     *
     * @param time HHmm
     * @return HH:mm
     */
    public static String getFormatTime4(String time) {
        String hour = "00";
        String min = "00";
        if (time.length() == 4) {
            hour = time.substring(0, 2);
            min = time.substring(2, 4);
        }
        return hour + ":" + min;
    }

    /**
     * 根据指定的时间戳，返回指定格式的日期时间
     * <p/>
     * 指定的日期格式<br>
     * eg:<br>
     * "yyyy-MM-dd HH:mm:ss"<br>
     * "yyyy-MM-dd"<br>
     * "yyyyMMddHHmmss"<br>
     * "HH:mm:ss"<br>
     *
     * @return
     */
    public static String getFormatTime(long time, String format) {
        String strs = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            strs = sdf.format(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strs;
    }

    /**
     * @param day 输入日期格式
     * @return 获取指定日期往前或者往后几天的日期
     * @leaveDay 差距的天数 -1 or +1
     */
    public static String getFormatLeaveDay(String day, int leaveDay) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.DATE, leaveDay);
        date = calendar.getTime();
        return simpleDateFormat.format(date);
    }

    /**
     * @param day 输入日期格式 yyyy-MM-dd
     * @return 获得前一天的日期
     */
    public static String getFormatBeforeDay(String day) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(day);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1); // 得到前一天
        date = calendar.getTime();
        return simpleDateFormat.format(date);
    }

    /**
     * @param day 输入日期格式 yyyy-MM-dd
     * @return 获得后一天的日期
     */
    public static String getFormatNextDay(String day) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(day);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, +1); // 得到后一天
        date = calendar.getTime();
        return simpleDateFormat.format(date);
    }

    /**
     * 获得输入日期的星期
     *
     * @param inputDate 需要转换的日期 yyyy-MM-dd
     * @return 星期×
     */
    public static String getWeekDay(String inputDate) {
        // String weekStrArr1[] = {"周日","周一","周二","周三","周四","周五","周六"};
        String weekStrArr1[] = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int outWeek = calendar.get(Calendar.DAY_OF_WEEK);// 返回的是1-7的整数，1为周日，2为周一，以此类推。
        return weekStrArr1[outWeek - 1];
    }

    /**
     * 检测时间是否在某个时间段内
     *
     * @param timeSlot 时间段 00：00--24：00
     * @param time     需要检测的时间 00：23
     * @return
     */
    public static boolean isInsideTime(String timeSlot, String time) {
        String startTime = timeSlot.split("--")[0];
        String endTime = timeSlot.split("--")[1];
        boolean isGreaterStart = compareTime(time, startTime);
        boolean isLessEnd = compareTime(endTime, time);
        return isGreaterStart && isLessEnd;
    }

    /**
     * 比较两个时间的大小
     *
     * @param time1 00：23
     * @param time2 00：25
     * @return time1大于等于time2 为 true,time1小于time2 为 false
     */
    public static boolean compareTime(String time1, String time2) {
        if (time1.equals("24:00") || time2.equals("00:00")) {
            return true;
        }
        if (time2.equals("24:00") || time1.equals("00:00")) {
            return false;
        }
        // DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df = new SimpleDateFormat("HH:mm");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(time1));
            c2.setTime(df.parse(time2));
        } catch (ParseException e) {
            System.err.println("格式不正确");
        }
        int result = c1.compareTo(c2);

        if (result < 0) {
            return false;
        } else if (result >= 0) {
            return true;
        }
        return true;
    }

    /**
     * 比较两个日期的大小
     *
     * @param date1 2012-5-11
     * @param date2 2012-5-11
     * @return date1大于等于date2 为 true,date1小于date2 为 false
     */
    public static int compareDate(String date1, String date2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(date1));
            c2.setTime(df.parse(date2));
        } catch (ParseException e) {
            System.err.println("格式不正确");
        }
        int result = c1.compareTo(c2);

        if (result < 0) {
            return -1;
        } else if (result >= 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 比较两个日期的大小
     *
     * @param date1 2012-5-11
     * @param date2 2012-5-11
     * @return date1大于等于date2 为 true,date1小于date2 为 false
     */
    public static int compareDate(String date1, String date2, String format) {
        DateFormat df = new SimpleDateFormat(format);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        try {
            c1.setTime(df.parse(date1));
            c2.setTime(df.parse(date2));
        } catch (ParseException e) {
            System.err.println("格式不正确");
        }
        int result = c1.compareTo(c2);

        if (result < 0) {
            return -1;
        } else if (result > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 返回文字描述的日期
     *
     * @param date
     * @return
     */
    public static String getTimeDategetTimeFormatText(Date date) {
        if (date == null) {
            return null;
        }
        long diff = date.getTime() - new Date().getTime();
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + "年";
        }
//        if (diff > month) {
//            r = (diff / month);
//            return r + "月";
//        }
        if (diff > day) {
            r = (diff / day);
            return r + "天";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "小时";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟";
        }
        return "";
    }

    public static String getTimeFormatText2(String time) {
        if (time == null) {
            return null;
        }
        long b = System.currentTimeMillis();
        long diff = b - Long.valueOf(time + "000");
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "月前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

    public static String getTimeFormatText3(Date date1, Date date2) {
        long diff = date1.getTime() - date2.getTime();
        long r = 0;
        if (diff > year) {
            r = (diff / year);
            return r + "年";
        }
//        if (diff > month) {
//            r = (diff / month);
//            return r + "月";
//        }
        if (diff > day) {
            r = (diff / day);
            return r + "天";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "小时";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟";
        }
        return "";
    }

    public static String getDistanceDays(String time) {
        if (time == null) {
            return null;
        }
        long b = System.currentTimeMillis();
        long diff = b - Long.valueOf(time);
        int r = 0;
        if (diff > day) {
            r = (int) Math.ceil(diff * 1.0 / day);
            return r + "";
        } else if (0 < diff && diff < day) {
            return "1";
        }
        return "";
    }

    public static String getDistanceDays(String startTime, String endTime) {

        long b = Long.valueOf(endTime);
        long diff = b - Long.valueOf(startTime);
        int r = 0;
        if (diff > day) {
            r = (int) Math.ceil(diff * 1.0 / day);
            return r + "";
        } else if (0 < diff && diff < day) {
            return "1";
        }
        return "";
    }

    /**
     * 与当前日期比较, 算出差值
     * 剩余n天n小时
     * n小时
     * 剩余不到1小时
     *
     * @param time
     * @return
     */
    public static String getTimeRemaining(String time) {
        if (time == null) {
            return null;
        }
        long now_time_mills = System.currentTimeMillis();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = sf.parse(time);
            long time1 = parse.getTime();
            //相差多少天
            long day = (now_time_mills - time1) / (24 * 60 * 60 * 1000);
            long hour = (now_time_mills - time1) / (60 * 60 * 1000);
            if (day > 99) {
                return "剩余大于99天";
            }
            if (0 < day && day < 99) {
                return "剩余" + day + "天" + (hour - 24) + "小时";
            }
            if (hour > 1) {
                return "剩余" + hour + "小时";
            }
            if (hour > 0) {
                return "剩余不到1小时";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
    /**
     * 求两个时间差返回：天小时分钟
     */
    public static String getTwoDHd(String time1, String time2) {

        DateFormat df = new SimpleDateFormat(FMT);
        long result = 0;
        try {
            Date date1 = df.parse(time1);
            Date date2 = df.parse(time2);
            long lessTime1 = date1.getTime();
            long lessTime2 = date2.getTime();
            result = (lessTime1 - lessTime2) / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
            return "";

        }

        if (result<=0){
            return "";
        }


        long hh = result / (60 * 60);
        long mm = (result % (60 * 60)) / 60;
        long ss = (result % (60 * 60)) % 60;
        if (hh <= 0 && mm <= 0 && ss < 0) {
            return "";
        }

        if (ss>0){//多一秒也算一小时
            mm+=1;
        }
        long dd=0;

        if (hh>=24){
            dd=hh/24;
            hh=hh%24;
        }


        String mStr="";
        if (dd>0){
            mStr+=dd+"天";
        }

        if (hh>0){
            mStr+=hh+"小时";
        }

        if (mm>0){
            mStr+=mm+"分";
        }

        return mStr;
    }
    /**
     *
     * ＜1小时时显示XX分钟后开始，不足1分钟忽略不计
     * ＜24小时的显示X小时后开始，不足1小时忽略不计
     * ＞24小时的显示X天X小时后开始，不足1小时忽略不计
     *
     */
    public static String getTwoDHd2(String time1, String time2) {

        DateFormat df = new SimpleDateFormat(FMT);
        long result = 0;
        try {
            Date date1 = df.parse(time1);
            Date date2 = df.parse(time2);
            long lessTime1 = date1.getTime();
            long lessTime2 = date2.getTime();
            result = (lessTime1 - lessTime2) / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
            return "";

        }

        if (result<=0){
            return "";
        }


        long hh = result / (60 * 60);
        long mm = (result % (60 * 60)) / 60;
        long ss = (result % (60 * 60)) % 60;
        if (hh <= 0 && mm <= 0 && ss < 0) {
            return "";
        }

//        if (ss>0){//多一秒也算一小时
//            mm+=1;
//        }
        long dd=0;

        if (hh>=24){
            dd=hh/24;
            hh=hh%24;
        }


        String mStr="";
        if (dd>0){
            mStr+=dd+"天";
        }

        if (hh>0){
            mStr+=hh+"小时";
        }

        if (dd<=0&&hh<=0&&mm>=0){
            mStr+=mm+"分";
        }

        return mStr;
    }

    public static String stringToDateSimple(String lo) {
        long time = Long.parseLong(lo);
        Date date = new Date(time * 1000);
        SimpleDateFormat sd = new SimpleDateFormat(DATE_FMT2);
        return sd.format(date);
    }

    public static String stringToDateSimple2(String lo, String format) {
        long time = Long.parseLong(lo);
        Date date = new Date(time * 1000);
        SimpleDateFormat sd = new SimpleDateFormat(format);
        return sd.format(date);
    }

    public static String stringToDateSimple3(String lo, String format) {
        long time = Long.parseLong(lo);
        Date date = new Date(time);
        SimpleDateFormat sd = new SimpleDateFormat(format);
        return sd.format(date);
    }

    //获取两个日期间隔多少天
    public static int getTwoDay(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        int day = 0;
        try {
            Date date = myFormatter.parse(sj1);
            Date mydate = myFormatter.parse(sj2);
            day = (int) ((date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000));
        } catch (Exception e) {
            return -10000;
        }
        return day;
    }

    /**
     * 求两个时间差
     */
    public static String getTwoTime(String time1, String time2) {

        DateFormat df = new SimpleDateFormat(FMT);
        long result = 0;
        try {
            Date date1 = df.parse(time1);
            Date date2 = df.parse(time2);
            long lessTime1 = date1.getTime();
            long lessTime2 = date2.getTime();
            result = (lessTime1 - lessTime2) / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long hh = result / (60 * 60);
        long mm = (result % (60 * 60)) / 60;
        long ss = (result % (60 * 60)) % 60;
        if (hh <= 0 && mm <= 0 && ss < 0) {
            return "-1";
        }
        return hh + "时" + mm + "分" + ss + "秒";
    }

    /**
     * 加多少秒的剩的时间
     */
    public static String getdataless1(String time1, long ms) {

        DateFormat df = new SimpleDateFormat(FMT);
        Date date = null;
        try {
            date = df.parse(time1);
            long lessTime = date.getTime() + ms;
            date.setTime(lessTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return df.format(date);
    }

    /*将字符串转为时间戳*/
    public static long getStringToDate(String time, String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }


    /**
     * 转换时间格式
     */
    public static String getStringToString(String time, String type1, String type2) {
        SimpleDateFormat sdf = new SimpleDateFormat(type1);
        Date date = new Date();
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat(type2);
        return sdf2.format(date);
    }


    public static String gettime(String time) {
        if (TextUtils.isEmpty(time)) {
            return "0秒";
        }
        int i = Integer.valueOf(time) / 60;
        int j = Integer.valueOf(time) % 60;
        if (j == 0 && i != 0) {
            return String.valueOf(i) + "分钟";
        } else if (i == 0 && j != 0) {
            return String.valueOf(j) + "秒";
        } else {
            return String.valueOf(i) + "分钟" + String.valueOf(j) + "秒";
        }
    }

    /**
     * 比较两个日期的大小，日期格式为yyyy-MM-dd
     *
     * @param str1 the first date
     * @param str2 the second date
     * @return true <br/>false
     */
    public static boolean isDateOneBigger(String str1, String str2) {
        boolean isBigger = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(str1);
            dt2 = sdf.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dt1.getTime() > dt2.getTime()) {
            isBigger = true;
        } else if (dt1.getTime() < dt2.getTime()) {
            isBigger = false;
        }
        return isBigger;
    }





    /**
     * 求两个时间差返回：天小时，小时分钟
     */
    public static String getTwoDH(String time1, String time2) {

        DateFormat df = new SimpleDateFormat(FMT);
        long result = 0;
        try {
            Date date1 = df.parse(time1);
            Date date2 = df.parse(time2);
            long lessTime1 = date1.getTime();
            long lessTime2 = date2.getTime();
            result = (lessTime1 - lessTime2) / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
            return "";

        }

        if (result<=0){
            return "";
        }


        long hh = result / (60 * 60);
        long mm = (result % (60 * 60)) / 60;
        long ss = (result % (60 * 60)) % 60;
        if (hh <= 0 && mm <= 0 && ss < 0) {
            return "";
        }

//        if (mm>0||ss>0){//多一秒也算一小时
//            hh+=1;
//        }
        long dd=0;

        if (hh>=24){
            dd=hh/24;
            hh=hh%24;
        }


        String mStr="";
        if (dd>0){
            mStr+=dd+"天";
        }

        if (hh>0){
            mStr+=hh+"小时";
        }

        if (dd<=0){
            mStr+=mm+"分钟";
        }
        return mStr;
    }



    /**
     * 求两个时间差返回：天小时，没有分钟
     */
    public static String getTwoDHNoM(String time1, String time2) {

        DateFormat df = new SimpleDateFormat(FMT);
        long result = 0;
        try {
            Date date1 = df.parse(time1);
            Date date2 = df.parse(time2);
            long lessTime1 = date1.getTime();
            long lessTime2 = date2.getTime();
            result = (lessTime1 - lessTime2) / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
            return "";

        }

        if (result<=0){
            return "";
        }


        long hh = result / (60 * 60);
        long mm = (result % (60 * 60)) / 60;
        long ss = (result % (60 * 60)) % 60;
        if (hh <= 0 && mm <= 0 && ss < 0) {
            return "";
        }

//        if (mm>0||ss>0){//多一秒也算一小时
//            hh+=1;
//        }
        long dd=0;

        if (hh>=24){
            dd=hh/24;
            hh=hh%24;
        }


        String mStr="";
        if (dd>0){
            mStr+=dd+" 天 ";
        }

        if (hh>0){
            mStr+=hh+" 小时";
        }

        //要求不显示分钟，最后一小时都显示1小时
        if (dd<=0&&hh<=0){
            mStr="1 小时";
        }
        return mStr;
    }



    /**
     * 根据时间戳来判断当前的时间是几天前,几分钟,刚刚
     *
     * @param long_time
     * @return
     */
    public static String getTimeStateNew(String long_time) {
        String long_by_13 = "1000000000000";
        String long_by_10 = "1000000000";
        if (Long.valueOf(long_time) / Long.valueOf(long_by_13) < 1) {
            if (Long.valueOf(long_time) / Long.valueOf(long_by_10) >= 1) {
                long_time = long_time + "000";
            }
        }
        Timestamp time = new Timestamp(Long.valueOf(long_time));
        Timestamp now = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日 HH:mm");
//    System.out.println("传递过来的时间:"+format.format(time));
//    System.out.println("现在的时间:"+format.format(now));
        long day_conver = 1000 * 60 * 60 * 24;
        long hour_conver = 1000 * 60 * 60;
        long min_conver = 1000 * 60;
        long time_conver = now.getTime() - time.getTime();
        long temp_conver;
//    System.out.println("天数:"+time_conver/day_conver);
        if ((time_conver / day_conver) < 3) {
            temp_conver = time_conver / day_conver;
            if (temp_conver <= 2 && temp_conver >= 1) {
                return temp_conver + "天前";
            } else {
                temp_conver = (time_conver / hour_conver);
                if (temp_conver >= 1) {
                    return temp_conver + "小时前";
                } else {
                    temp_conver = (time_conver / min_conver);
                    if (temp_conver >= 1) {
                        return temp_conver + "分钟前";
                    } else {
                        return "刚刚";
                    }
                }
            }
        } else {
            return format.format(time);
        }
    }

    /**
     * 获取前n天日期、后n天日期
     *
     * @param distanceDay 前几天 如获取前7天日期则传-7即可；如果后7天则传7
     * @return
     */
    public static String getOldDate(int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.i("cdsvcadsvcsda", "前7天==" + dft.format(endDate));
        return dft.format(endDate);
    }

    public static String getTimeFormat(String time) {
        if (TextUtils.isEmpty(time)) {
            return "00:00";
        }
        int i = Integer.valueOf(time) / 60;
        int j = Integer.valueOf(time) % 60;
        if (j == 0 && i != 0) {
            return (i < 10 ? "0" + i : i) + ":00";
        } else if (i == 0 && j != 0) {
            return "00:" + (j < 10 ? "0" + j : j);
        } else {
            return (i < 10 ? "0" + i : i) + ":" + (j < 10 ? "0" + j : j);
        }
    }

    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        try {
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);

            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);
            int day1 = cal1.get(Calendar.DAY_OF_YEAR);
            int day2 = cal2.get(Calendar.DAY_OF_YEAR);

            int year1 = cal1.get(Calendar.YEAR);
            int year2 = cal2.get(Calendar.YEAR);
            if (year1 != year2)   //同一年
            {
                int timeDistance = 0;
                for (int i = year1; i < year2; i++) {
                    if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                    {
                        timeDistance += 366;
                    } else    //不是闰年
                    {
                        timeDistance += 365;
                    }
                }

                return timeDistance + (day2 - day1);
            } else    //不同年
            {
                System.out.println("判断day2 - day1 : " + (day2 - day1));
                return day2 - day1;
            }
        } catch (Exception e) {
            return -1;
        }

    }

    /*
     * 将时间戳转换为时间
     */
    public static Date stampToDate(String s) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            String fDate = format.format(s);
            date = format.parse(fDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return date;
    }

    public static long differDate(String s1, String s2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        Date d2 = null;
        try {
            d2 = df.parse(s2);
            d1 = df.parse(s1);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return (d1.getTime() - d2.getTime()) / (60 * 60 * 1000 * 24);
    }

    /**
     *
     * @param time  yyyy-MM-dd HH:mm:ss
     * @param format 输出的时间格式
     * @return
     */
    public static String getTimeDate(String time, String format) {
        return TimeUtils.getFormatTime(Long.valueOf(TimeUtils.getStringToDate(time, TimeUtils.FMT)), format);
    }

    /**
     * 时间戳转换成Date
     */
    public static Date getDateByLong(long mill, String fmt) {
        //mill为你龙类型的时间戳
        Date date = new Date(mill);
        String strs = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(fmt);
            strs = sdf.format(date);
            System.out.println(strs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    //获取指定格式的时间戳
    public static String getTimeStamp(String fmt) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(fmt);
        long timeMilllis = System.currentTimeMillis();
        return simpleDateFormat.format(timeMilllis);
    }
}
