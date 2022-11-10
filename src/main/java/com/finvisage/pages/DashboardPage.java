package com.finvisage.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import com.finvisage.enums.WaitStrategy;

public class DashboardPage extends BasePage {
	private  static Logger logger = LogManager.getLogger(DashboardPage.class);
	private final By btnLogOut = By.xpath("//i[@class='far fa-sign-out far far fa-sign-out']");
	private final By link_DerivativePricer= By.xpath("//a[@data-original-title='Derivative Pricer']");
	private final By hamburgermenu = By.xpath("//i[@class='fa fa-bars']");

	public LogInPage clickLogOut() {
		clickk(btnLogOut,WaitStrategy.CLICKABLE,"LogOut button");
		logger.info("Logout button clicked");
		return new LogInPage();
	}
	public StructuresPage clickDerivativePricer(){
		if(isDisplayed(hamburgermenu,WaitStrategy.VISIBLE,"Hamburger menu")){
			clickk(hamburgermenu,WaitStrategy.CLICKABLE,"Hamburger menu");
		}
		clickk(link_DerivativePricer,WaitStrategy.CLICKABLE,"Derivative pricer");
		return new StructuresPage();
	}

}
