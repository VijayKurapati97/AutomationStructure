package com.org.product2Pages;

import com.org.enums.WaitStrategy;
import org.openqa.selenium.By;

public final class Product2LogInPage extends Product2BasePage {
    private final By UserName = By.id("email-input");
    private final By Password = By.xpath("//input[@id='password-input']");
    private final By btnLogIn = By.xpath("//*[text()='Log In']");

    public void enterUserNameAndPassword(String[] Username) {
        sendText(UserName, Username[0], WaitStrategy.PRESENCE, "Email box");
        sendText(Password, Username[1], WaitStrategy.PRESENCE, "password box");
    }

    public void clickLogIn() {
        clickk(btnLogIn, WaitStrategy.CLICKABLE, "LogIn button");
    }

    public String[] LogIn(String []User) {
        enterUserNameAndPassword(User);
        clickLogIn();
        return User;
    }
}
