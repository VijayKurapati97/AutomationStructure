package com.finvisage.frmPages;

import org.openqa.selenium.By;

import com.finvisage.enums.WaitStrategy;

public class FRMDashboardPage extends BasePageFRM {
	private final By btnLogOut = By.xpath("//i[@class='far fa-sign-out far far fa-sign-out']");
	private final By link_DerivativePricer= By.xpath("//a[@data-original-title='Derivative Pricer']");
	private final By link_MarketData=By.xpath("//a[normalize-space()='Market Data']");
	private final By link_RateCurves=By.xpath("//a[normalize-space()='Rate curves']");

	public FRMLogInPage clickLogOut() {
		clickk(btnLogOut,WaitStrategy.CLICKABLE,"LogOut button");
		return new FRMLogInPage();
	}
	public StructuresPage clickDerivativePricer(){
		jsClick(link_DerivativePricer, WaitStrategy.CLICKABLE, "Derivative pricer");
		return new StructuresPage();
	}
	public FRMDashboardPage clickMarketData(){
		jsClick(link_MarketData,WaitStrategy.CLICKABLE,"Market Data");
		return this;
	}
	public MarketDataRateCurves clickRateCurves(){
		clickk(link_RateCurves,WaitStrategy.CLICKABLE,"Rate Curves");
		return new MarketDataRateCurves();
	}
}
