package com.org.product1Pages;

import com.org.constants.FrameworkConstants;
import org.openqa.selenium.By;

import com.org.enums.WaitStrategy;

public final class Product1LogInPage extends BasePageProduct1 {
	private final By UserName =By.id("email-input");
	private final By Password = By.id("password-input");
	private final By btnLogIn =By.xpath("//*[text()='Log in']");

	public Product1LogInPage enterUserName(String Username) {
		sendText(UserName,Username,WaitStrategy.PRESENCE,"Email box");
		return this;
	}

	public Product1LogInPage enterPassword(String Passwordd) {
		sendText(Password,Passwordd,WaitStrategy.PRESENCE,"Password box");
		return this;
	}

	public Product1DashboardPage clickLogIn() {
		clickk(btnLogIn,WaitStrategy.CLICKABLE,"LogIn button");
		return new Product1DashboardPage();
	}


}
