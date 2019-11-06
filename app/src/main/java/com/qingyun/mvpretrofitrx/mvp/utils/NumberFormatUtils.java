package com.qingyun.mvpretrofitrx.mvp.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberFormatUtils {

    public static String format2(float price){
        DecimalFormat decimalFormat=new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String p= decimalFormat.format(price);//format 返回的是字符串
        return  p;
    }


    public static String format2Up(float price){
        BigDecimal bigDecimal = new BigDecimal(price);
        return  bigDecimal.setScale(2,BigDecimal.ROUND_UP).toString();
    }

    public static String format2Down(double price){
        BigDecimal bigDecimal = new BigDecimal(price);
        return  bigDecimal.setScale(2,BigDecimal.ROUND_DOWN).toString();
    }


    public static String format2Up(double price){
        BigDecimal bigDecimal = new BigDecimal(price);
        return  bigDecimal.setScale(2,BigDecimal.ROUND_UP).toString();
    }

    public static String format2Down(float price){
        BigDecimal bigDecimal = new BigDecimal(price);
        return  bigDecimal.setScale(2,BigDecimal.ROUND_DOWN).toString();
    }


    public static String format2(double price){
        DecimalFormat decimalFormat=new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String p= decimalFormat.format(price);//format 返回的是字符串
        return  p;
    }

    public static String format1(float price){
        DecimalFormat decimalFormat=new DecimalFormat("0.0");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String p= decimalFormat.format(price);//format 返回的是字符串
        return  p;
    }
}
