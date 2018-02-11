package com.tangzq.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tangzhiqiang
 */
public class DateUtils {

    static final int SECONDS_IN_A_YEAR=60*60*24*365;
    static final int SECONDS_IN_A_MONTH=60*60*24*30;
    static final int SECONDS_IN_A_WEEK=60*60*24*7;
    static final int SECONDS_IN_A_DAY=60*60*24;
    static final int SECONDS_IN_A_HOUR=60*60;
    static final int SECONDS_IN_A_MINUTE=60;

    static final String TXT_YEAR_AGAO="年前";
    static final String TXT_MONTH_AGAO="个月前";
    static final String TXT_WEEK_AGAO="周前";
    static final String TXT_DAY_AGAO="天前";
    static final String TXT_HOUR_AGAO="小时前";
    static final String TXT_MINUTE_AGAO="分钟前";
    static final String TXT_JUST_NOW="刚刚";

    public static final String NORMAL_FORMAT="yyyy-MM-dd HH:mm:ss";


    /**
     * 获取指定时间离当前时间相差多少分，小时，天，周，月，年
     * @param d
     * @return
     */
    public static String getFriendlyTime(Date d){
        long delta = (System.currentTimeMillis()-d.getTime())/1000;
        if(delta<=0){return new SimpleDateFormat(NORMAL_FORMAT).format(d);}
        if(delta/(SECONDS_IN_A_YEAR) > 0) {return delta/(SECONDS_IN_A_YEAR) +TXT_YEAR_AGAO;}
        if(delta/(SECONDS_IN_A_MONTH) > 0) {return delta/(SECONDS_IN_A_MONTH) +TXT_MONTH_AGAO;}
        if(delta/(SECONDS_IN_A_WEEK) > 0){return delta/(SECONDS_IN_A_WEEK) +TXT_WEEK_AGAO;}
        if(delta/(SECONDS_IN_A_DAY) > 0) {return delta/(SECONDS_IN_A_DAY) +TXT_DAY_AGAO;}
        if(delta/(SECONDS_IN_A_HOUR) > 0){return delta/(SECONDS_IN_A_HOUR) +TXT_HOUR_AGAO;}
        if(delta/(SECONDS_IN_A_MINUTE) > 0){return delta/(SECONDS_IN_A_MINUTE) +TXT_MINUTE_AGAO;}
        return TXT_JUST_NOW;
    }
}
