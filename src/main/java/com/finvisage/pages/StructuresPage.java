package com.finvisage.pages;

import com.finvisage.enums.WaitStrategy;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class StructuresPage extends BasePage {
    private final By New_Price = By.xpath("//i[@class='far fa-plus']");

    public DerivativeStructuresPage clickNewPrice(){
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        clickk(New_Price, WaitStrategy.CLICKABLE,"NewPrice button");
        return new DerivativeStructuresPage();
    }

}
