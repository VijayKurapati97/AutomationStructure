package com.org.tests;

import java.util.Map;

import com.org.product1Pages.Product1LogInPage;
import org.testng.annotations.Test;

public final class Product1Tests extends BaseTest {

	private Product1Tests() {

	}

	@Test()
	public void loginTest(Map<String,String> data) {
		Product1LogInPage lp=new Product1LogInPage();
		lp.enterUserName(data.get("Email"))
				.enterPassword(data.get("Password"))
				.clickLogIn().clickLogOut();

	}

	@Test()
	public void loginTest2(Map<String,String> data ) {
		Product1LogInPage lp=new Product1LogInPage();
		lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn().clickLogOut();
	}





}
