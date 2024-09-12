package com.finvisage.liabilityPages;

import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import org.openqa.selenium.By;

public class FixedDepositsBlotterPage extends BasePageLiability{
    private final By hamburgerMenu = By.xpath("//section[@id='fixed-buttons']/div/a/i");
    private final By btn_Add = By.xpath("//a[@data-original-title='Add Fixed Deposit']/i");

    public FixedDepositsBlotterPage moveToHamburgerMenu() {
        moveToElement(DriverManager.getDriver().findElement(hamburgerMenu), "HamburgerMenu");
        return this;
    }

    public NewFDPage clickAdd() {
        jsClick(btn_Add, WaitStrategy.CLICKABLE, "Add Button");
        return new NewFDPage();
    }

}
