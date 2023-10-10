package com.finvisage.frmPages;

import com.finvisage.enums.WaitStrategy;
import org.openqa.selenium.By;

public class StructuresPage extends BasePageFRM {
    private final By New_Price = By.xpath("//i[@class='far fa-plus']");

    public DerivativeStructuresPage clickNewPrice(){
       if(isDisplayed(New_Price,WaitStrategy.VISIBLE,"New Price Button")){
        jsClick(New_Price, WaitStrategy.CLICKABLE,"NewPrice button");}
        return new DerivativeStructuresPage();
    }

}
