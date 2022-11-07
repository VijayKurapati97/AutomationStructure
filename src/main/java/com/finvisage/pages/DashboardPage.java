package com.finvisage.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.finvisage.enums.WaitStrategy;

public class DashboardPage extends BasePage {
	private  static Logger logger = LogManager.getLogger(DashboardPage.class);
private final By btnLogOut = By.xpath("//i[@class='far fa-sign-out far far fa-sign-out']");

public LogInPage clickLogOut() {
	clickk(btnLogOut,WaitStrategy.CLICKABLE,"LogOut button");
	logger.info("Logout button clicked");
	return new LogInPage();
}
	
}
