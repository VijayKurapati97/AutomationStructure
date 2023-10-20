package com.finvisage.liabilityPages;

import com.finvisage.enums.WaitStrategy;
import org.openqa.selenium.By;

public final class LiabiltyLogInPage extends BasePageLiability {
    private final By UserName = By.id("email-input");
    private final By Password = By.xpath("//input[@id='password-input']");
    private final By btnLogIn = By.xpath("//*[text()='Log In']");

    public LiabiltyLogInPage enterUserNameAndPassword(String[] Username) {
        sendText(UserName, Username[0], WaitStrategy.PRESENCE, "Email box");
        sendText(Password, Username[1], WaitStrategy.PRESENCE, "passworddd box");
        return this;
    }

    public LiabilityDashboardsPage clickLogIn() {
        clickk(btnLogIn, WaitStrategy.CLICKABLE, "LogIn button");
        return new LiabilityDashboardsPage();
    }

    public String[] LogIn(String []User) {
        enterUserNameAndPassword(User);
        clickLogIn();
        return User;
    }
}
