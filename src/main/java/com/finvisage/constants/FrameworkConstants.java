package com.finvisage.constants;

import java.time.Duration;

import com.finvisage.enums.ConfigProperties;
import com.finvisage.utils.CurrentDate;
import com.finvisage.utils.PropertyFileReader;



public final class FrameworkConstants {

	private FrameworkConstants(){}

	private static final String CONFIGFILEPATH=System.getProperty("user.dir")+"/src/test/resources/config/config.properties";
	private static final Duration EXPLICITWAIT =Duration.ofSeconds(10);
	private static final String EXTENTREPORTPATH=System.getProperty("user.dir")+"/Extent-reports/reports/";
	private static final String EXCELPATH=System.getProperty("user.dir")+"/src/test/resources/excel/TestData.xlsx";


	public static String getExtentReportPath() {

		return EXTENTREPORTPATH+"/"+CurrentDate.getCurrentDate()+"index.html";
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
