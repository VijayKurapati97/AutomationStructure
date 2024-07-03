package com.finvisage.liabilityPages;

import com.finvisage.enums.WaitStrategy;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class LiabilityDashboardsPage extends BasePageLiability{
    private final By link_Liability = By.xpath("//a[normalize-space()='Liability']");
    private final By link_LoanFacility =By.xpath("//span[normalize-space()='Loan Facility']");
    private final By link_Assets = By.xpath("//a[@data-original-title='Asset']");
    private final By link_FixedDeposit = By.xpath("//span[text()='Fixed Deposit']");
    private final By link_SubDebtLoanFacility =By.xpath("(//a[@data-original-title='Loan Facility'])[2]/span[2]");
    private final By link_ECB =By.xpath("//a[@data-original-title='Ecb']/span[2]");
    private final By link_ShortTermLoan =By.xpath("//a[@data-original-title='Short Term Loan']/span[2]");
    private final By link_WorkingCapitalLoan =By.xpath("//a[@data-original-title='Working Capital Loan']/span[2]");
    private final By link_ncdStandalone =By.xpath("//a[@data-original-title='Private Placement - Standalone Structure']/span[2]");
    private final By link_SubDebtNCD =By.xpath("//a[@data-original-title='NCD']/span[2]");
    private final By link_ShelfTrancheStructure =By.xpath("//a[@data-original-title='Private Placement - Shelf Tranche Structure']/span[2]");
    private final By link_SubDebt_ShelfTrancheStructure =By.xpath("//a[@data-original-title='NCD Shelf Tranche']/span[2]");
    private final By link_Commercial_paper =By.xpath("//a[@data-original-title='Commercial Paper']/span[2]");
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
    public SDLFBlotterPage clickSubDebtLF(){
        scrollIntoView(link_SubDebtLoanFacility);
        clickk(link_SubDebtLoanFacility,WaitStrategy.CLICKABLE,"SubDebt Loan Facility");
        return new SDLFBlotterPage();
    }
    public ECBBlotterPage clickECB(){
        clickk(link_ECB,WaitStrategy.CLICKABLE,"ECB");
        return new ECBBlotterPage();
    }
    public STLBlotterPage clickSTL(){
        scrollIntoView(link_ShortTermLoan);
        clickk(link_ShortTermLoan,WaitStrategy.CLICKABLE,"Short term loan");
        return new STLBlotterPage();
    }
    public WCLBlotterPage clickWCL(){
        scrollIntoView(link_WorkingCapitalLoan);
        clickk(link_WorkingCapitalLoan,WaitStrategy.CLICKABLE,"Working Capital Loan");
        return new WCLBlotterPage();
    }
    public StandaloneBlotterPage clickStandaloneStructure(){
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        scrollIntoView(link_ncdStandalone);
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        doubleClick(link_ncdStandalone);
        return new StandaloneBlotterPage();
    }
    public SubDebtNCDBlotterPage clickSubDebtNCD(){
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        scrollIntoView(link_SubDebtNCD);
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        doubleClick(link_SubDebtNCD);
        return new SubDebtNCDBlotterPage();
    }
    public ShelfTrancheBlotter clickShelfTrancheStructure(){
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        scrollIntoView(link_ShelfTrancheStructure);
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        doubleClick(link_ShelfTrancheStructure);
        return new ShelfTrancheBlotter();
    }
    public SubDebtSDDBlotter clickSubDebtShelfTrancheStructure(){
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        scrollIntoView(link_SubDebt_ShelfTrancheStructure);
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        doubleClick(link_SubDebt_ShelfTrancheStructure);
        return new SubDebtSDDBlotter();
    }
    public CPBlotterPage clickCommercialPaper(){
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        scrollIntoView(link_Commercial_paper);
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        doubleClick(link_Commercial_paper);
        return new CPBlotterPage();
    }
}

