package com.finvisage.tests;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.finvisage.drivers.Drivers;

public class BaseTest {

	protected BaseTest() {

	}

	@BeforeMethod
	protected void setUP(Method me) throws Exception
	{ 
		
		Drivers.initDriver();
		
		
	}

	@AfterMethod
	protected void tearDown(ITestResult result) {
		
		Drivers.quitDriver();
		
	}
}
