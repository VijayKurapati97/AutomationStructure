package com.finvisage.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.finvisage.enums.WaitStrategy;

public final class LogInPage extends BasePage {
	private  static Logger logger = LogManager.getLogger(LogInPage.class);
	private final By UserName =By.id("email-input");
	private final By Password = By.xpath("//input[@id='password-input']");
	private final By btnLogIn =By.xpath("//*[text()='Log in']");

	public LogInPage enterUserName(String Username) {
		sendText(UserName,Username,WaitStrategy.PRESENCE,"Email box");
		logger.info(Username +" EmailEntered");
		return this;
	}

	public LogInPage enterPassword(String Passwordd) {
		sendText(Password,Passwordd,WaitStrategy.PRESENCE,"Password box");
		logger.info(Passwordd+" Entered as Password");
		return this;
	}

	public DashboardPage clickLogIn() {
		clickk(btnLogIn,WaitStrategy.CLICKABLE,"LogIn button");
		logger.info("Login button is clicked");
		return new DashboardPage();
	}
	public String getTitle() {
		return getPageTitle();
	}
}
