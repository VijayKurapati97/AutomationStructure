package com.finvisage.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.finvisage.factory.ExplicitWaitFactory;
import com.finvisage.reports.ExtentLogger;

public class BasePage {
	private  static Logger logger = LogManager.getLogger(BasePage.class);
	protected void clickk(By by , WaitStrategy wait,String elementname) {
		ExplicitWaitFactory.performExplicitWait(wait, by).click();
		try {
			ExtentLogger.pass(elementname+" clicked", true);
			logger.info(elementname+" is clicked");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void sendText(By by ,String value ,WaitStrategy wait,String elementname) {
		ExplicitWaitFactory.performExplicitWait(wait, by).sendKeys(value);
		try {
			ExtentLogger.pass(value+" is Entered in"+elementname,true);
			logger.info(value+" is Entered in "+elementname);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String getPageTitle() {

		return DriverManager.getDriver().getTitle();
	}

	protected boolean isDisplayed(By by, WaitStrategy wait, String elementName) {
		ExplicitWaitFactory.performExplicitWait(wait, by).isDisplayed();

		try {
			ExtentLogger.pass(elementName +" is Displayed" , true);
			logger.info(elementName + "is displayed");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	protected String getText(By by, WaitStrategy wait, String elementName){
		String value=ExplicitWaitFactory.performExplicitWait(wait , by).getText();
		try {
			ExtentLogger.pass(value+" is "+elementName+" value ",true);
		    logger.info(value +" got from "+elementName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}


}
