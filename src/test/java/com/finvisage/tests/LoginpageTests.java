package com.finvisage.tests;

import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.finvisage.pages.LogInPage;
import com.finvisage.utils.DataProviderUtils;

public final class LoginpageTests extends BaseTest {

	private LoginpageTests() {

	}

	@Test(dataProvider="getData",dataProviderClass=DataProviderUtils.class)
	public void loginLogoutTest(Map<String,String> data) {
		LogInPage lp=new LogInPage(); 
		lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn().clickLogOut();
		
		
	}

	@Test(dataProvider="getData",dataProviderClass=DataProviderUtils.class)
	public void loginTest(Map<String,String> data) {
		LogInPage lp=new LogInPage(); 
		lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn().clickLogOut();
	}
	
	
}
