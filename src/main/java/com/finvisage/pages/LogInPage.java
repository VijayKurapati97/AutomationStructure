package com.finvisage.pages;

import org.openqa.selenium.By;

import com.finvisage.enums.WaitStrategy;

public final class LogInPage extends BasePage {

	private final By UserName =By.id("email-input");
	private final By Password = By.xpath("//input[@id='password-input']");
	private final By btnLogIn =By.xpath("//*[text()='Log in']");

	public LogInPage enterUserName(String Username) {
		sendText(UserName,Username,WaitStrategy.PRESENCE,"Email box");
		return this;
	}

	public LogInPage enterPassword(String Passwordd) {
		sendText(Password,Passwordd,WaitStrategy.PRESENCE,"Password box");
		return this;
	}

	public DashboardPage clickLogIn() {
		clickk(btnLogIn,WaitStrategy.CLICKABLE,"LogIn button");
		return new DashboardPage();
	}
	public String getTitle() {
		return getPageTitle();
	}
}
