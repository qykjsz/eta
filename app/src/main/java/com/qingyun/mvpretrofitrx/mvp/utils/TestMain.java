package com.qingyun.mvpretrofitrx.mvp.utils;

import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestMain {


    /**
     * 获取当前日期是星期几<br>
     *
     * @param date
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];

    }

    public static String getsetdata() {
        String s = "";
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        //方式一
        Date date = new Date();
        if (getWeekOfDate(date).equals("星期日")){
            return s="Sunday"+",  "+month+"月"+year+"年";
        }else if (getWeekOfDate(date).equals("星期一")){
             return s="Monday"+",  "+month+"月"+year+"年";
        }else if (getWeekOfDate(date).equals("星期二")){
             return s="Tuesday"+",  "+month+"月"+year+"年";
        }else if (getWeekOfDate(date).equals("星期三")){
             return s="Wednesday"+",  "+month+"月"+year+"年";
        }else if (getWeekOfDate(date).equals("星期四")){
             return s="Thursday"+",  "+month+"月"+year+"年";
        }else if (getWeekOfDate(date).equals("星期五")){
             return s="Friday"+",  "+month+"月"+year+"年";
        }else if (getWeekOfDate(date).equals("星期六")){
             return s="Saturday"+",  "+month+"月"+year+"年";
        }

        return s;
    }
}
