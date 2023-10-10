package com.finvisage.liabilityPages;

import com.finvisage.constants.FrameworkConstants;
import com.finvisage.enums.WaitStrategy;
import org.openqa.selenium.By;

public final class LiabiltyLogInPage extends BasePageLiability {
    private final By UserName =By.id("email-input");
    private final By Password = By.xpath("//input[@id='password-input']");
    private final By btnLogIn =By.xpath("//*[text()='Log In']");

    public LiabiltyLogInPage enterUserName(String Username) {
        sendText(UserName,Username, WaitStrategy.PRESENCE,"Email box");
        return this;
    }

    public LiabiltyLogInPage enterPassword(String Passwordd) {
        sendText(Password,Passwordd,WaitStrategy.PRESENCE,"Password box");
        return this;
    }

    public LiabilityDashboardsPage clickLogIn() {
        clickk(btnLogIn,WaitStrategy.CLICKABLE,"LogIn button");
        return new LiabilityDashboardsPage();
    }

    public LiabilityDashboardsPage LogIn(){
        enterUserName(FrameworkConstants.getLiabilityUsername()).enterPassword(FrameworkConstants.getLiabilityPassword()).clickLogIn();
        return new LiabilityDashboardsPage();
    }
}
