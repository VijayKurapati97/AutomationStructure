package com.finvisage.utils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public final class CommonUtils {
    private CommonUtils() {

    }

    public static String getCurrentDate() {
        DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        Date dt = new Date();
        return dateformat.format(dt);
    }
    public static BigDecimal StringArrayToInt(String[] str, int index, int index2){
        String[] ar=  str[index].split(" ");
        String[] arr=ar[index2].split(",");
        String value=String.join("",arr);
        double newValue = Double.parseDouble(value);
        return new BigDecimal(newValue);
    }
    public static long stringToint(String str){
        String [] ar=  str.split(",");
        String value=String.join("",ar);
        return Long.parseLong(value);
    }
   public static double calculateWeightedAverage(Map<Long, Double> map){
        double num = 0;
        int denom = 0;
        for (Map.Entry<Long, Double> entry : map.entrySet()) {
            num += entry.getKey() * entry.getValue();
            denom += entry.getKey();
        }

        return num / denom;
    }
}
