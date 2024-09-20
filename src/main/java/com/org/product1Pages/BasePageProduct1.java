package com.org.product1Pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.org.drivers.DriverManager;
import com.org.enums.WaitStrategy;
import com.org.factory.ExplicitWaitFactory;
import com.org.reports.ExtentLogger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class BasePageProduct1 {
	Actions act = new Actions(DriverManager.getDriver());
	private  static final Logger logger = LogManager.getLogger(BasePageProduct1.class);

	protected void clickk(By by, WaitStrategy wait, String elementname) {
		try {
			ExplicitWaitFactory.performExplicitWait(wait, by).click();
			ExtentLogger.pass(elementname + " clicked", true);
			logger.info(elementname + " is clicked");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void jsClick(By by, String elementname) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
			WebElement element = DriverManager.getDriver().findElement(by);
			js.executeScript("arguments[0].click();", element);
			ExtentLogger.pass(elementname + " is clicked ", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(elementname + " is clicked ");
	}

	protected void sendText(By by, String value, WaitStrategy wait, String elementname) {

		try {
			ExplicitWaitFactory.performExplicitWait(wait, by).sendKeys(value);
			ExtentLogger.pass(value + " is Entered in" + elementname, true);
			logger.info(value + " is Entered in " + elementname);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	protected boolean isDisplayed(By by, String elementName) {
		try {
			boolean value = DriverManager.getDriver().findElement(by).isDisplayed();
			ExtentLogger.pass(elementName + " is Displayed " + value, true);
			logger.info(elementName + " is displayed " + value);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	protected boolean isDisplayed(By by,WaitStrategy wait, String elementName) {
		try {
			boolean value = ExplicitWaitFactory.performExplicitWait(wait, by).isDisplayed();
			ExtentLogger.pass(elementName + " is Displayed " + value, true);
			logger.info(elementName + " is displayed " + value);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	protected String getText(By by, WaitStrategy wait, String elementName) {
		try {
			String value = ExplicitWaitFactory.performExplicitWait(wait, by).getText();
			ExtentLogger.pass(value + " is " + elementName + " value ", true);
			logger.info(value + " got from " + elementName);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	protected String getAttribute(By by,String attribute, WaitStrategy wait, String elementName) {
		try {
			String value = ExplicitWaitFactory.performExplicitWait(wait, by).getAttribute(attribute);
			ExtentLogger.pass(value + " is " + elementName + " value ", true);
			logger.info(value + " got from " + elementName);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	protected void selectDropdown(WebElement element, String text) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
		try {
			ExtentLogger.pass(text + " is selected from dropdown", true);
			logger.info(text + " is selected from dropdown");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected boolean isSelected(By by, String elementName) {
		try {
			boolean value = DriverManager.getDriver().findElement(by).isSelected();
			ExtentLogger.pass(elementName + " is Selected", true);
			logger.info(elementName + " is selected");
			return value;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	protected boolean isEnabled(By by, String elementName) {
		try {
			boolean value = ExplicitWaitFactory.performExplicitWait(WaitStrategy.VISIBLE, by).isEnabled();
			ExtentLogger.pass(elementName + " is enabled " + value, true);
			logger.info(elementName + " is enabled");
			return value;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	protected void jsClick(By by, WaitStrategy wait, String elementName) {

		try {
			JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
			WebElement element = ExplicitWaitFactory.performExplicitWait(wait, by).findElement(by);
			js.executeScript("arguments[0].click();", element);
			ExtentLogger.pass(elementName + " is clicked ", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.info(elementName + " is clicked ");

	}

	protected void actionSendkeys(String value) {
		try {
			act.sendKeys(value).sendKeys(Keys.ENTER).build().perform();
			ExtentLogger.pass(value + " is entered", true);
			logger.info(value + " is entered ");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected void doubleClick(By ele) {
		act.moveToElement(DriverManager.getDriver().findElement(ele)).doubleClick().perform();
	}


	protected void moveToElement(WebElement element, String elementName) {

		act.moveToElement(element).perform();
		try {
			ExtentLogger.pass("moved to" + elementName, true);
			logger.info("moved to" + elementName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void scrollIntoView(By by) {
		((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0]." +
				"scrollIntoView(true);", DriverManager.getDriver().findElement(by));
	}
	protected void scrollIntoView(By by,WaitStrategy wait) {
		((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0]." +
				"scrollIntoView(true);",ExplicitWaitFactory.performExplicitWait(wait, by).findElement(by));
	}
	protected void scrollHorizontally(By by){
		((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].scrollIntoView" +
						"({ behavior: 'auto', block: 'center', inline: 'center' });"
				, DriverManager.getDriver().findElement(by));
	}


}
