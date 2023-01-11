package com.finvisage.constants;

import java.time.Duration;

import com.finvisage.utils.CommonUtils;


public final class FrameworkConstants {

	private FrameworkConstants(){}

	private static final String CONFIGFILEPATH=System.getProperty("user.dir")+"/src/test/resources/config/config.properties";
	private static final Duration EXPLICITWAIT =Duration.ofSeconds(60);
	private static final String EXTENTREPORTPATH=System.getProperty("user.dir")+"/Extent-reports/reports/";
	private static final String CSVPATH=System.getProperty("user.dir")+"/src/test/resources/TestData/";
private static final String EMAIL="vijaykurapati@apexft.com";
private static final String PASSWORD="Vijayk@97";


	public static String getExtentReportPath() {
		return EXTENTREPORTPATH+"/"+ CommonUtils.getCurrentDate()+" index.html";
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

}
