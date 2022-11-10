package com.finvisage.pages;

import com.finvisage.enums.WaitStrategy;
import org.openqa.selenium.By;

public class DerivativeStructuresPage extends BasePage {
private static final By BtnForward= By.xpath("//p[text()='Forward']");

public NewFXForwardPage clickFX_Forward(){
    clickk(BtnForward, WaitStrategy.CLICKABLE,"Forward button ");
    return new NewFXForwardPage();
}
}
