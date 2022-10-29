package com.finvisage.pages;

import org.openqa.selenium.By;

import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.finvisage.factory.ExplicitWaitFactory;
import com.finvisage.reports.ExtentLogger;

public class BasePage {

	protected void clickk(By by , WaitStrategy wait,String elementname) {
		ExplicitWaitFactory.performExplicitWait(wait, by).click();
		try {
			ExtentLogger.pass(elementname+" clicked", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void sendText(By by ,String value ,WaitStrategy wait,String elementname) {
		ExplicitWaitFactory.performExplicitWait(wait, by).sendKeys(value);
		try {
			ExtentLogger.pass(value+" is Entered in"+elementname,true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String getPageTitle() {
		return DriverManager.getDriver().getTitle();
	}


}
