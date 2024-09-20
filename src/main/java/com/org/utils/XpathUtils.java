package com.org.utils;

public class XpathUtils {
    public static String getXpath(String xpath,String value){
        return xpath.replace("%replace%",value);
    }

}
