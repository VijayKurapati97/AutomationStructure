package com.finvisage.liabilityPages;

import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import org.openqa.selenium.By;

public class SubDebtSDDBlotter extends BasePageLiability{
    private final By hamburgerMenu = By.xpath("//section[@id='fixed-buttons']/div/a/i");
    private final By btn_Add = By.xpath("//a[@data-original-title='Add']/i");
    private final By archivedTab = By.id("archived-data-tab");
    private final By closedTab = By.id("closed-data-tab");
    private final By searchBox = By.xpath("//div[@id='DataTables_Table_1_filter']//input");
    private final By ActiveLoanSearchBox = By.xpath("//div[@id='DataTables_Table_0_filter']//input");
    private final By firstLoanId = By.xpath("(//tbody)[2]/tr[1]/td[2]");


    public SubDebtSDDBlotter moveToHamburgerMenu() {
        moveToElement(DriverManager.getDriver().findElement(hamburgerMenu), "HamburgerMenu");
        return this;
    }

    public NewSubDebtSDDPage clickAdd() {
        jsClick(btn_Add, WaitStrategy.CLICKABLE, "Add Button");
        return new NewSubDebtSDDPage();

    }

    public SubDebtSDDBlotter clickClosedTab() {
        clickk(closedTab, WaitStrategy.CLICKABLE, "Closed tab");
        return this;
    }

    public SubDebtSDDBlotter clickArchivedTab() {
        clickk(archivedTab, WaitStrategy.CLICKABLE, "Archived tab");
        return this;
    }

    public SubDebtSDDBlotter searchExtId(String ID) {
        sendText(searchBox, ID, WaitStrategy.PRESENCE, "Search box");
        return this;
    }

    public String getFirstLoan() {
        return getText(firstLoanId, WaitStrategy.VISIBLE, "Loan ID");
    }
    public void selectFirstSDD(String ID){
        sendText(ActiveLoanSearchBox, ID, WaitStrategy.PRESENCE, "Search box");
        clickk(By.xpath("(//tbody[1])/tr/td/a"),WaitStrategy.CLICKABLE,"NCDSDD");
        new SubDebtSDDPage();
    }
}