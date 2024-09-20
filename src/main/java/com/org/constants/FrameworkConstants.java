package com.org.constants;

import com.org.utils.CommonUtils;

import java.time.Duration;
import java.util.Arrays;


public final class FrameworkConstants {

    private FrameworkConstants() {
    }

    private static final String CONFIGFILEPATH = System.getProperty("user.dir") + "/src/test/resources/config/config.properties";
    private static final String DOWNLOADPATH=System.getProperty("user.dir") +"/Downloadss";
    private static final String UPLOAD_FILE1 =System.getProperty("user.dir")+"/FilesToUpload/file1.xlsx";
    private static final String UPLOAD_FILE2 =System.getProperty("user.dir")+"/FilesToUpload/file2.xlsx";
    private static final String UPLOAD_FILE3 =System.getProperty("user.dir")+"/FilesToUpload/file3.xlsx";
    private static final String UPLOADATTCHEDDOCFILEPATH =System.getProperty("user.dir")+"/FilesToUpload/AttachedDocuments.xlsx";
    private static final  String UPLOAD_FILE4 =System.getProperty("user.dir")+"/FilesToUpload/file4.xlsx";
    private static final  String UPLOAD_FILE5 =System.getProperty("user.dir")+"/FilesToUpload/file5.xlsx";
    private static final Duration EXPLICITWAIT = Duration.ofSeconds(60);
    private static final String EXTENTREPORTPATH = System.getProperty("user.dir") + "/Extent-reports/reports/";
    private static final String CSVPATH = System.getProperty("user.dir") + "/src/test/resources/TestData/";
    private static final String PRODUCT1MAIL = "--------";
    private static final String PRODUCT1PASSWORD = "---------";

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
public static void setUserAvailability(String[] user){
        if(Arrays.equals(user, user1) && !getUser1Availability()){
            setUser1Availability(true);
        }else if(Arrays.equals(user, user2) && !getUser2Availability()){
            setUser2Availability(true);
        } else if (Arrays.equals(user, user3) &&!getUser3Availability()) {
            setUser3Availability(true);
        }
}
    private static final String[] user1 = {"Automation1", "Autoant@123"};

    public static boolean getUser1Availability() {
        return user1Availability;
    }

    public static void setUser1Availability(boolean user1Availability) {
        FrameworkConstants.user1Availability = user1Availability;
    }

    private static boolean user1Availability = true;


    private static final String[] user2 = {"Automation2", "Autoant@123"};

    public static boolean getUser2Availability() {
        return user2Availability;
    }

    public static void setUser2Availability(boolean user2Availability) {
        FrameworkConstants.user2Availability = user2Availability;
    }

    private static boolean user2Availability = true;

    private static final String[] user3 = {"Automation3", "Autoant@8"};

    public static boolean getUser3Availability() {
        return user3Availability;
    }

    public static void setUser3Availability(boolean user3Availability) {
        FrameworkConstants.user3Availability = user3Availability;
    }

    private static boolean user3Availability = true;

    private static final String[] user4 = {"44444", "33333333"};

    public static String getExtentReportPath() {
        String path = EXTENTREPORTPATH + "/" + CommonUtils.getCurrentDate() + " index.html";
        return path.replace(" ", "_");
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
        return PRODUCT1MAIL;
    }

    public static String getFRMPassword() {
        return PRODUCT1PASSWORD;
    }
    public static String getDownloadsPath(){
        return DOWNLOADPATH;
    }
    public static String get_LF_UploadPrincipalFilePath(){
        return UPLOAD_FILE1;
    }
    public static String get_SDLF_UploadPrincipalFilePath(){
        return UPLOAD_FILE2;
    }
    public static String get_SDLF_UploadInterestFilePath(){
        return UPLOAD_FILE3;
    }
    public static String getUploadAttachedDocFilePath(){
        return UPLOADATTCHEDDOCFILEPATH;
    }
    public static String get_LF_UploadInterestFilePath() {
        return UPLOAD_FILE4;
    }
    public static String get_LF_UploadPaymentsFilePath() {
        return UPLOAD_FILE5;
    }

}
