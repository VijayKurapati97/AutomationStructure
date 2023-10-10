package com.finvisage.liabilityPages;

import com.finvisage.enums.WaitStrategy;
import org.openqa.selenium.By;

public class LiabilityDashboardsPage extends BasePageLiability{
    private final By link_Liability = By.xpath("//a[normalize-space()='Liability']");
    private final By link_LoanFacility =By.xpath("//span[normalize-space()='Loan Facility']");
    public LiabilityDashboardsPage clickLiability() {
        clickk(link_Liability, WaitStrategy.CLICKABLE,"Liability");
        return this;
    }
    public LoanFacilityBlotterPage clickLoanFacility(){
        clickk(link_LoanFacility,WaitStrategy.CLICKABLE,"Loan Facility");
        return new LoanFacilityBlotterPage();
    }
}

