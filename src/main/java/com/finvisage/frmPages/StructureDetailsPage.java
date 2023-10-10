package com.finvisage.frmPages;

import com.finvisage.enums.WaitStrategy;
import org.openqa.selenium.By;

public class StructureDetailsPage extends BasePageFRM {
    private static final By cashflowTable= By.xpath("(//div[@class='col-12'])[9]");
    private static final By structureDatails =By.xpath("(//div[@class='col-12 col-xl-5'])[1]");

    public void cashflowTableIsDisplayed(){
        isDisplayed(cashflowTable, WaitStrategy.VISIBLE,"Deferred Premium section");
    }
    public void structureDetailsIsDisplayed(){
        isDisplayed(structureDatails,WaitStrategy.VISIBLE,"Struecture Details section");
    }
}
