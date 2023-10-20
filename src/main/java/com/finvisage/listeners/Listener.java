package com.finvisage.listeners;

import java.util.Arrays;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.finvisage.reports.ExtentLogger;
import com.finvisage.reports.ExtentReport;


public class Listener implements ITestListener,ISuiteListener {



	@Override
	public void onStart(ISuite suite) {
		try {
			ExtentReport.initReports();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onFinish(ISuite suite) {

		try {
			ExtentReport.flushReports();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	@Override
	public void onTestStart(ITestResult result) {
		ExtentReport.createTest(result.getMethod().getMethodName());


	}
	@Override
	public void onTestSuccess(ITestResult result) {
		try {
			ExtentLogger.pass(result.getMethod().getMethodName()+ " is passed",true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onTestFailure(ITestResult result) {
		try {
			ExtentLogger.fail(result.getMethod().getMethodName()+ " is failed",true);
			ExtentLogger.fail(result.getThrowable().toString());
			//To get stackTrace in Reports
			ExtentLogger.fail(Arrays.toString(result.getThrowable().getStackTrace()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onTestSkipped(ITestResult result) {

		try {
			ExtentLogger.skip(result.getMethod().getMethodName()+ " is skipped",true);
			ExtentLogger.skip(result.getMethod().getMethodName()+ " is skipped");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}
	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}
	@Override
	public void onStart(ITestContext context) {

	}
	@Override
	public void onFinish(ITestContext context) {

	}






}
