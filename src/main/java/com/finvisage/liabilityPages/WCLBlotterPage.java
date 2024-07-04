package com.finvisage.liabilityPages;

import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import org.openqa.selenium.By;

public class WCLBlotterPage extends BasePageLiability{
    private final By hamburgerMenu = By.xpath("//section[@id='fixed-buttons']/div/a/i");
    private final By btn_Add = By.xpath("//a[@data-original-title='Add']/i");
    private final By archivedTab = By.id("archived-data-tab");
    private final By closedTab = By.id("closed-data-tab");
    private final By searchBox = By.xpath("//div[@id='DataTables_Table_1_filter']//input");
    private final By firstExtId = By.xpath("(//tbody)[2]/tr[1]/td[6]");

    public WCLBlotterPage moveToHamburgerMenu() {
        moveToElement(DriverManager.getDriver().findElement(hamburgerMenu), "HamburgerMenu");
        return this;
    }

    public NewWCLPage clickAdd() {
        jsClick(btn_Add, WaitStrategy.CLICKABLE, "Add Button");
        return new NewWCLPage();

    }

    public WCLBlotterPage clickClosedTab() {
        clickk(closedTab, WaitStrategy.CLICKABLE, "Closed tab");
        return this;
    }

    public WCLBlotterPage clickArchivedTab() {
        clickk(archivedTab, WaitStrategy.CLICKABLE, "Archived tab");
        return this;
    }

    public WCLBlotterPage searchExtId(String ID) {
        sendText(searchBox, ID, WaitStrategy.PRESENCE, "Search box");
        return this;
    }

    public String getFirstLoan() {
        return getText(firstExtId, WaitStrategy.VISIBLE, "Loan External ID");
    }
}
