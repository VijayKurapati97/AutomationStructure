package com.finvisage.pages;

import org.openqa.selenium.By;

import com.finvisage.enums.WaitStrategy;

public class DashboardPage extends BasePage {
private final By btnLogOut = By.xpath("/html/body/main/section/div[1]/ul/li[3]/a/i");

public LogInPage clickLogOut() {
	clickk(btnLogOut,WaitStrategy.CLICKABLE,"LogOut button");
	return new LogInPage();
}
	
}
