package com.finvisage.frmPages;

import com.finvisage.constants.FrameworkConstants;
import org.openqa.selenium.By;

import com.finvisage.enums.WaitStrategy;

public final class FRMLogInPage extends BasePageFRM {
	private final By UserName =By.id("email-input");
	private final By Password = By.xpath("//input[@id='password-input']");
	private final By btnLogIn =By.xpath("//*[text()='Log in']");

	public FRMLogInPage enterUserName(String Username) {
		sendText(UserName,Username,WaitStrategy.PRESENCE,"Email box");
		return this;
	}

	public FRMLogInPage enterPassword(String Passwordd) {
		sendText(Password,Passwordd,WaitStrategy.PRESENCE,"Password box");
		return this;
	}

	public FRMDashboardPage clickLogIn() {
		clickk(btnLogIn,WaitStrategy.CLICKABLE,"LogIn button");
		return new FRMDashboardPage();
	}

	public FRMDashboardPage LogIn(){
		enterUserName(FrameworkConstants.getFRMEmail()).enterPassword(FrameworkConstants.getFRMPassword()).clickLogIn();
		return new FRMDashboardPage();
	}

}
