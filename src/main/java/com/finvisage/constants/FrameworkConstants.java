package com.finvisage.constants;

import com.finvisage.utils.CommonUtils;

import java.time.Duration;


public final class FrameworkConstants {

    private FrameworkConstants() {
    }

    private static final String CONFIGFILEPATH = System.getProperty("user.dir") + "/src/test/resources/config/config.properties";
    private static final Duration EXPLICITWAIT = Duration.ofSeconds(60);
    private static final String EXTENTREPORTPATH = System.getProperty("user.dir") + "/Extent-reports/reports/";
    private static final String CSVPATH = System.getProperty("user.dir") + "/src/test/resources/TestData/";
    private static final String FRMEMAIL = "--------";
    private static final String FRMPASSWORD = "---------";

    public static String[] getUser() {
        if (getUser1Availability()) {
            setUser1Availability(false);
            return user1;
        } else if (getUser2Availability()) {
            setUser2Availability(false);
            return user2;
        } else if (getUser3Availability()) {
            setUser3Availability(false);
            return user3;

        }
        return user4;
    }
public static void setUserAvailablity(String[] user){
        if(user.equals(user1)&& !getUser1Availability()){
            setUser1Availability(true);
        }else if(user.equals(user2)&& !getUser2Availability()){
            setUser2Availability(true);
        } else if (user.equals(user3)&&!getUser3Availability()) {
            setUser3Availability(true);
        }
}
    private static final String[] user1 = {"vijaykurapati", "vijayK@123"};

    public static boolean getUser1Availability() {
        return user1Availability;
    }

    public static void setUser1Availability(boolean user1Availability) {
        FrameworkConstants.user1Availability = user1Availability;
    }

    private static boolean user1Availability = true;


    private static  String[] user2 = {"Automation1", "Automation@1"};

    public static boolean getUser2Availability() {
        return user2Availability;
    }

    public static void setUser2Availability(boolean user2Availability) {
        FrameworkConstants.user2Availability = user2Availability;
    }

    private static boolean user2Availability = true;

    private static final String[] user3 = {"Automation2", "Automation@123"};

    public static boolean getUser3Availability() {
        return user3Availability;
    }

    public static void setUser3Availability(boolean user3Availability) {
        FrameworkConstants.user3Availability = user3Availability;
    }

    private static boolean user3Availability = true;

    private static String[] getUser1(){
        return user1;
    }
    private static String[] getUser2(){
        return user2;
    }
    private static String[] getUser3(){
        return user3;
    }

    private static final String[] user4 = {"44444", "33333333"};

    public static String getExtentReportPath() {
        return EXTENTREPORTPATH + "/" + CommonUtils.getCurrentDate() + " index.html";
    }

    public static Duration getExplicitwait() {
        return EXPLICITWAIT;
    }

    public static String getConfigFilePath() {
        return CONFIGFILEPATH;
    }

    public static String getCsvpath() {
        return CSVPATH;
    }

    public static String getFRMEmail() {
        return FRMEMAIL;
    }

    public static String getFRMPassword() {
        return FRMPASSWORD;
    }


}
