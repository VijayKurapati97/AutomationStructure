package com.finvisage.liabilityPages;

import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class LoanFacilityBlotterPage extends BasePageLiability {
    private final By hamburgerMenu = By.xpath("//section[@id='fixed-buttons']/div/a/i");
    private final By btn_Add = By.xpath("//a[@data-original-title='Add']/i");
    private final By archivedTab = By.id("archived-data-tab");
    private final By closedTab = By.id("closed-data-tab");
    private final By searchBox = By.xpath("//div[@id='DataTables_Table_1_filter']//input");
    private final By firstExtId = By.xpath("(//tbody)[2]/tr[1]/td[1]");
    private final By draftedLoanEditOption =By.xpath("(//tbody)[2]/tr[1]/td[11]/a[1]");
    private final By draftedLoanDeleteOption =By.xpath("(//tbody)[2]/tr[1]/td[11]/a[2]");
    private final By draftTab=By.id("drafted-data-tab");


    public LoanFacilityBlotterPage moveToHamburgerMenu() {
        moveToElement(DriverManager.getDriver().findElement(hamburgerMenu), "HamburgerMenu");
        return this;
    }

    public NewLoanFacilityPage clickAdd() {
        clickk(btn_Add, WaitStrategy.CLICKABLE, "Add Button");
        return new NewLoanFacilityPage();
    }

    public LoanFacilityBlotterPage clickClosedTab() {
        clickk(closedTab, WaitStrategy.CLICKABLE, "Closed tab");
        return this;
    }

    public LoanFacilityBlotterPage clickArchivedTab() {
        clickk(archivedTab, WaitStrategy.CLICKABLE, "Archived tab");
        return this;
    }

    public LoanFacilityBlotterPage searchExtId(String ID) {
        sendText(searchBox, ID, WaitStrategy.PRESENCE, "Search box");
        return this;
    }

    public String getFirstLoan() {
        return getText(firstExtId, WaitStrategy.VISIBLE, "Loan External ID");
    }
    public LoanFacilityBlotterPage clickDraftsTab(){
        clickk(draftTab,WaitStrategy.CLICKABLE,"Draft tab");
        return new  LoanFacilityBlotterPage();
    }
    public NewLoanFacilityPage clickDraftedLoanEdit(){
        scrollHorizontally(draftedLoanEditOption);
        jsClick(draftedLoanEditOption,WaitStrategy.CLICKABLE,"Drafted loan edit button");
        return new NewLoanFacilityPage();
    }
    public LoanFacilityBlotterPage clickDraftedLoanDelete(){
        scrollIntoView(draftedLoanDeleteOption);
        scrollHorizontally(draftedLoanDeleteOption);
        doubleClick(draftedLoanDeleteOption);
        Alert al = DriverManager.getDriver().switchTo().alert();
        al.accept();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        return this;
    }
}
