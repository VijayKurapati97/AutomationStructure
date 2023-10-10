package com.finvisage.tests;

import java.util.Map;

import com.finvisage.frmPages.FRMLogInPage;
import org.testng.annotations.Test;

public final class LoginPageTests extends BaseTest {

	private LoginPageTests() {

	}

	@Test()
	public void loginTest(Map<String,String> data) {
		FRMLogInPage lp=new FRMLogInPage();
		lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn().clickLogOut();

	}

	@Test()
	public void loginTest2(Map<String,String> data ) {
		FRMLogInPage lp=new FRMLogInPage();
		lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn().clickLogOut();
	}





}
