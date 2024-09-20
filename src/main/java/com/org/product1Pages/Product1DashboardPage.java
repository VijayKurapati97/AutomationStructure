package com.org.product1Pages;

import com.org.enums.WaitStrategy;
import org.openqa.selenium.By;

public class Product1DashboardPage extends BasePageProduct1 {
    private final By btnLogOut = By.xpath("//tag[@id='logout-Button']");

    public Product1LogInPage clickLogOut() {
        clickk(btnLogOut, WaitStrategy.CLICKABLE, "LogOut button");
        return new Product1LogInPage();
    }

}
