package com.finvisage.factory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.finvisage.constants.FrameworkConstants;
import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;

public class ExplicitWaitFactory {

public static WebElement performExplicitWait(WaitStrategy waitstrategy, By by) {
		
		WebElement element=null;
		if(waitstrategy == WaitStrategy.CLICKABLE) {
			
			WebDriverWait wait =new WebDriverWait(DriverManager.getDriver(),FrameworkConstants.getExplicitwait());
			element=wait.until(ExpectedConditions.elementToBeClickable(by));
			
		}else if(waitstrategy==WaitStrategy.PRESENCE) {
			
			WebDriverWait wait =new WebDriverWait(DriverManager.getDriver(),FrameworkConstants.getExplicitwait());
			element=wait.until(ExpectedConditions.presenceOfElementLocated(by));
			
			
		}else if(waitstrategy==WaitStrategy.VISIBLE) {
			
			WebDriverWait wait =new WebDriverWait(DriverManager.getDriver(),FrameworkConstants.getExplicitwait());
			element=wait.until(ExpectedConditions.visibilityOfElementLocated(by));
			
		}else if(waitstrategy==WaitStrategy.NONE) {
			
			element=DriverManager.getDriver().findElement(by);

		}
		
		return element;
		
	}
	
}
