package com.finvisage.reports;

import java.awt.Desktop;
import java.io.File;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.finvisage.constants.FrameworkConstants;

public final class ExtentReport {
	private ExtentReport() {
	}

	private	static ExtentReports extent;
	public static void initReports() {
		if(Objects.isNull(extent)) {
			extent=new ExtentReports();
			ExtentSparkReporter spark =new ExtentSparkReporter(FrameworkConstants.getExtentReportPath());
			extent.attachReporter(spark);
			spark.config().setTheme(Theme.DARK);
			spark.config().setDocumentTitle("Finvisage_Reports");
			spark.config().setReportName("Test report");
		}
	}
	public static void flushReports() {
		if(Objects.nonNull(extent)) {
			extent.flush();
		}
		
	}
	
	public static void createTest(String testcasename) {
		ExtentTest test=extent.createTest(testcasename);
		ExtentManager.setExtentTest(test);
	}

}
