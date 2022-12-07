package com.finvisage.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class CommonUtils {
    private CommonUtils() {

    }

    public static String getCurrentDate() {
        DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        Date dt = new Date();
		return dateformat.format(dt);
    }
    public static BigDecimal StringToInt(String[] str, int index, int index2){
        String[] ar=  str[index].split(" ");
        String[] arr=ar[index2].split(",");
        String value=String.join("",arr);
        double newValue = Double.valueOf(value).doubleValue();
        return new BigDecimal(newValue);
    }


}
