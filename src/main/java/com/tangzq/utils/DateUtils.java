package com.tangzq.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tangzhiqiang
 */
public class DateUtils {

    public static final String NORMAL_FORMAT="yyyy-MM-dd HH:mm:ss";


    /**
     * 获取指定时间离当前时间相差多少分，小时，天，周，月，年
     * @param d
     * @return
     */
    public static String getFriendlyTime(Date d){
        long delta = (System.currentTimeMillis()-d.getTime())/1000;
        if(delta<=0){return new SimpleDateFormat(NORMAL_FORMAT).format(d);}
        if(delta/(60*60*24*365) > 0) {return delta/(60*60*24*365) +"年前";}
        if(delta/(60*60*24*30) > 0) {return delta/(60*60*24*30) +"个月前";}
        if(delta/(60*60*24*7) > 0){return delta/(60*60*24*7) +"周前";}
        if(delta/(60*60*24) > 0) {return delta/(60*60*24) +"天前";}
        if(delta/(60*60) > 0){return delta/(60*60) +"小时前";}
        if(delta/(60) > 0){return delta/(60) +"分钟前";}
        return "刚刚";
    }
}
