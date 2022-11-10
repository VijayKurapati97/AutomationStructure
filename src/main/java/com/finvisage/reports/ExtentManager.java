package com.finvisage.reports;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {

	private ExtentManager() {

	}

	private static final ThreadLocal <ExtentTest> ext=new ThreadLocal<>();

	public static ExtentTest getExtentTest() {
		return ext.get();
	}
	public static void setExtentTest(ExtentTest test) {
		ext.set(test);
	}

	static void unload() {
		ext.remove();
	}


}
