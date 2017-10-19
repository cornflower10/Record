package com.record.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xiejingbao on 2017/9/3.
 */

public class TimeUtils {
    public static String currentTimeMillis(){
      return   String.valueOf(System.currentTimeMillis());
    }

    /**
     * 毫秒数转换为指定格式的日期
     * @param
     * @param millSec
     * @return
     */
    public static String transferLongToDate(Long millSec){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date= new Date(millSec);
        return sdf.format(date);
    }


    /**
     * 毫秒数转换为指定格式的日期
     * @param
     * @param millSec
     * @return
     */
    public static String LongToDate(Long millSec){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date= new Date(millSec);
        return sdf.format(date);
    }
}
