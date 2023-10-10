package com.finvisage.frmPages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.finvisage.factory.ExplicitWaitFactory;
import com.finvisage.reports.ExtentLogger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasePageFRM {
	private  static Logger logger = LogManager.getLogger(BasePageFRM.class);

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


	protected boolean isDisplayed(By by, WaitStrategy wait, String elementName) {
		boolean value=ExplicitWaitFactory.performExplicitWait(wait, by).isDisplayed();

		try {
			ExtentLogger.pass(elementName +" is Displayed "+value , true);
			logger.info(elementName + " is displayed "+value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
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
	protected String getAttribute(By by, WaitStrategy wait, String Attribute){
		String value=ExplicitWaitFactory.performExplicitWait(wait , by).getAttribute(Attribute);
		try {
			ExtentLogger.pass(value+" is "+Attribute+" value ",true);
			logger.info(value +" got from "+Attribute);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}
	protected void selectDropdown(WebElement element,String text){
		Select select =new Select(element);
		select.selectByVisibleText(text);
		try {
			ExtentLogger.pass(text +" is selected from dropdown",true);
			logger.info(text+" is selected from dropdown");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected boolean isSelected(By by, WaitStrategy wait, String elementName) {
		boolean value=ExplicitWaitFactory.performExplicitWait(wait, by).isSelected();

		try {
			ExtentLogger.pass(elementName +" is Selected" , true);
			logger.info(elementName + " is selected");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	protected void jsClick(By by, WaitStrategy wait, String elementName){
		JavascriptExecutor js = (JavascriptExecutor)DriverManager.getDriver();
		WebElement element=ExplicitWaitFactory.performExplicitWait(wait,by).findElement(by);
		js.executeScript("arguments[0].click();", element);
		try {
			ExtentLogger.pass(elementName +" is clicked " , true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(elementName + " is clicked ");

	}


}
