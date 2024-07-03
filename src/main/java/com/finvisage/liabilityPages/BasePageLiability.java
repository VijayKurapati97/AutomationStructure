package com.finvisage.liabilityPages;

import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.finvisage.factory.ExplicitWaitFactory;
import com.finvisage.reports.ExtentLogger;
import com.google.common.util.concurrent.Uninterruptibles;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class BasePageLiability {
    Actions act = new Actions(DriverManager.getDriver());
    private static final Logger logger = LogManager.getLogger(com.finvisage.liabilityPages.BasePageLiability.class);

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

    protected boolean isEnabled(By by, WaitStrategy wait, String elementName) {
        try {
            boolean value = ExplicitWaitFactory.performExplicitWait(wait, by).isEnabled();
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
    protected void jsDoubleClick(By by, String elementName) {

        try {
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            WebElement element = ExplicitWaitFactory.performExplicitWait(WaitStrategy.CLICKABLE, by).findElement(by);
            js.executeScript("var evt = new MouseEvent('dblclick', {bubbles: true, cancelable: true, view: window}); arguments[0].dispatchEvent(evt);", element);
            ExtentLogger.pass(elementName + " is Double clicked ", true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info(elementName + " is Double clicked ");

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

    protected String generateRandomID(int count, String liabilityName) {
        final String value = "01234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "abcdefghijklmnopqrstuvwxyz";
        return liabilityName + "-" + RandomStringUtils.random(count, value);
    }
    protected String generateRandomISIN() {
        final String value1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String value2 = "0123456789";
        return RandomStringUtils.random(2,value1)+RandomStringUtils.random(10, value2);
    }

    protected BasePageLiability clearDate(By by) {
        WebElement ele = DriverManager.getDriver().findElement(by);
        for (int i = 1; i <= 10; i++) {
            ele.sendKeys(Keys.BACK_SPACE);
        }
        return this;

    }
    protected BasePageLiability clearDate(By by,WaitStrategy wait) {
        WebElement ele = ExplicitWaitFactory.performExplicitWait(wait, by).findElement(by);
        for (int i = 1; i <= 10; i++) {
            ele.sendKeys(Keys.BACK_SPACE);
        }
        return this;

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

    protected HashMap<String, String> coverdetails() {
        String str, coverValue;
        HashMap<String, String> map = new HashMap<>();
        final String[] coverType = {"Times", "Percent", "Absolute"};
        str = coverType[(int) (Math.random() * coverType.length)];
        if (str.equals("Times")) {
            final String[] times = {"0.2", "0.4", "0.7", "0.5", "0.6", "1.8", "1.6", "2.5"};
            coverValue = times[(int) (Math.random() * times.length)];
        } else if (str.equals("Absolute")) {
            final String[] absolute = {"2000000", "5000000", "1000000", "7000000", "4000000", "250000", "3200000"};
            coverValue = absolute[(int) (Math.random() * absolute.length)];
        } else {
            final String[] percent = {"25", "22", "24", "29", "45", "50", "40"};
            coverValue = percent[(int) (Math.random() * percent.length)];
        }
        map.put(str, coverValue);
        return map;
    }

    protected String getAnalyticsValue(String value){
       String[] arr= value.split(" ");
       return arr[2];
    }

    protected void pasteAndEnter() throws AWTException {
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        robot.keyPress(KeyEvent.VK_V);
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        robot.keyRelease(KeyEvent.VK_V);
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}


