package com.finvisage.constants;

import com.finvisage.utils.CommonUtils;

import java.time.Duration;
import java.util.Arrays;


public final class FrameworkConstants {

    private FrameworkConstants() {
    }

    private static final String CONFIGFILEPATH = System.getProperty("user.dir") + "/src/test/resources/config/config.properties";
    private static final String DOWNLOADPATH=System.getProperty("user.dir") +"\\Downloadss";
    private static final String UPLOAD_LF_PRINCIPALSCHEDULEPATH =System.getProperty("user.dir")+"\\FilesToUpload\\LF_Principal_Schedule.xlsx";
    private static final String UPLOAD_SDLF_PRINCIPALSCHEDULEPATH =System.getProperty("user.dir")+"\\FilesToUpload\\SDLF_Principal_Schedule";
    private static final String UPLOAD_SDLF_INTERESTSCHEDULEPATH =System.getProperty("user.dir")+"\\FilesToUpload\\SDLF_Interest_Schedule";
    private static final String UPLOADATTCHEDDOCFILEPATH =System.getProperty("user.dir")+"\\FilesToUpload\\AttachedDocuments.xlsx";
    private static final  String UPLOAD_LF_INTERESTSCHEDULEPATH =System.getProperty("user.dir")+"\\FilesToUpload\\LF_Interest_Schedule.xlsx";
    private static final  String UPLOAD_LF_PAYMENTS =System.getProperty("user.dir")+"\\FilesToUpload\\LF_BulkPayments.xlsx";
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
public static void setUserAvailability(String[] user){
        if(Arrays.equals(user, user1) && !getUser1Availability()){
            setUser1Availability(true);
        }else if(Arrays.equals(user, user2) && !getUser2Availability()){
            setUser2Availability(true);
        } else if (Arrays.equals(user, user3) &&!getUser3Availability()) {
            setUser3Availability(true);
        }
}
    private static final String[] user1 = {"Automation1", "Autoant@3"};

    public static boolean getUser1Availability() {
        return user1Availability;
    }

    public static void setUser1Availability(boolean user1Availability) {
        FrameworkConstants.user1Availability = user1Availability;
    }

    private static boolean user1Availability = true;


    private static final String[] user2 = {"vijaykurapati", "Vijayk@123"};

    public static boolean getUser2Availability() {
        return user2Availability;
    }

    public static void setUser2Availability(boolean user2Availability) {
        FrameworkConstants.user2Availability = user2Availability;
    }

    private static boolean user2Availability = true;

    private static final String[] user3 = {"Automation2", "Autoant@8"};

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
        return FRMEMAIL;
    }

    public static String getFRMPassword() {
        return FRMPASSWORD;
    }
    public static String getDownloadsPath(){
        return DOWNLOADPATH;
    }
    public static String get_LF_UploadPrincipalFilePath(){
        return UPLOAD_LF_PRINCIPALSCHEDULEPATH;
    }
    public static String get_SDLF_UploadPrincipalFilePath(){
        return UPLOAD_SDLF_PRINCIPALSCHEDULEPATH;
    }
    public static String get_SDLF_UploadInterestFilePath(){
        return UPLOAD_SDLF_INTERESTSCHEDULEPATH;
    }
    public static String getUploadAttachedDocFilePath(){
        return UPLOADATTCHEDDOCFILEPATH;
    }
    public static String get_LF_UploadInterestFilePath() {
        return UPLOAD_LF_INTERESTSCHEDULEPATH;
    }
    public static String get_LF_UploadPaymentsFilePath() {
        return UPLOAD_LF_PAYMENTS;
    }

}
