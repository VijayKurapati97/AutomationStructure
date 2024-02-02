package com.finvisage.liabilityPages;

import com.finvisage.enums.WaitStrategy;
import org.openqa.selenium.By;

public class LiabilityDashboardsPage extends BasePageLiability{
    private final By link_Liability = By.xpath("//a[normalize-space()='Liability']");
    private final By link_LoanFacility =By.xpath("//span[normalize-space()='Loan Facility']");
    private final By link_Assets = By.xpath("//a[@data-original-title='Asset']");
    private final By link_FixedDeposit = By.xpath("//span[text()='Fixed Deposit']");
    public LiabilityDashboardsPage clickLiability() {
        clickk(link_Liability, WaitStrategy.CLICKABLE,"Liability");
        return this;
    }
    public LoanFacilityBlotterPage clickLoanFacility(){
        clickk(link_LoanFacility,WaitStrategy.CLICKABLE,"Loan Facility");
        return new LoanFacilityBlotterPage();
    }
    public LiabilityDashboardsPage clickAssets() {
        scrollIntoView(link_Assets);
        clickk(link_Assets, WaitStrategy.CLICKABLE,"Asset");
        return this;
    }
    public FixedDepositsBlotterPage clickFixedDeposit() {
        clickk(link_FixedDeposit, WaitStrategy.CLICKABLE,"Fixed Deposit");
        return new FixedDepositsBlotterPage();
    }
}

