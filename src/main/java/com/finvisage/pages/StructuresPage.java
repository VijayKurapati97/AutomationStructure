package com.finvisage.pages;

import com.finvisage.enums.WaitStrategy;
import org.openqa.selenium.By;

public class StructuresPage extends BasePage {
    private final By New_Price = By.xpath("//a[contains(@href,'/bookings/prices')]");

    public DerivativeStructuresPage clickNewPrice(){
        clickk(New_Price, WaitStrategy.CLICKABLE,"NewPrice button");
        return new DerivativeStructuresPage();
    }

}
