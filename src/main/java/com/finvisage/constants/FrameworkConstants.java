package com.finvisage.constants;

import java.time.Duration;

import com.finvisage.utils.CommonUtils;


public final class FrameworkConstants {

	private FrameworkConstants(){}

	private static final String CONFIGFILEPATH=System.getProperty("user.dir")+"/src/test/resources/config/config.properties";
	private static final Duration EXPLICITWAIT =Duration.ofSeconds(40);
	private static final String EXTENTREPORTPATH=System.getProperty("user.dir")+"/Extent-reports/reports/";
	private static final String EXCELPATH=System.getProperty("user.dir")+"/src/test/resources/excel/TestData.xlsx";
	private static final String RUNMANAGERSHEET="RUNMANAGER";


	public static String getRunManagerSheet(){
		return RUNMANAGERSHEET;
	}

	public static String getExtentReportPath() {
		return EXTENTREPORTPATH+"/"+ CommonUtils.getCurrentDate()+" index.html";
	}
	public static Duration getExplicitwait() {
		return EXPLICITWAIT;
	}

	public static String getConfigFilePath() {
		return CONFIGFILEPATH;
	}

	public static String getExcelpath() {
		return EXCELPATH;
	}
}
