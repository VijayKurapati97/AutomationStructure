package com.finvisage.pages;

import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class StructuresPage extends BasePage {
    private final By New_Price = By.xpath("//i[@class='far fa-plus']");

    public DerivativeStructuresPage clickNewPrice(){
       if(isDisplayed(New_Price,WaitStrategy.VISIBLE,"New Price Button")){
        jsClick(New_Price, WaitStrategy.CLICKABLE,"NewPrice button");}
        return new DerivativeStructuresPage();
    }

}
