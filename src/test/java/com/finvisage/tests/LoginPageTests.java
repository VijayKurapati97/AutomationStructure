package com.finvisage.tests;

import java.util.Map;

import org.testng.annotations.Test;

import com.finvisage.pages.LogInPage;

public final class LoginPageTests extends BaseTest {

	private LoginPageTests() {

	}

	@Test()
	public void loginTest(Map<String,String> data) {
		LogInPage lp=new LogInPage();
		lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn().clickLogOut();

	}

	@Test()
	public void loginTest2(Map<String,String> data ) {
		LogInPage lp=new LogInPage();
		lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn().clickLogOut();
	}


}
