package com.finvisage.pages;

import com.finvisage.enums.WaitStrategy;
import org.openqa.selenium.By;

public class DerivativeStructuresPage extends BasePage {
    private static final By btnForward= By.xpath("//p[text()='Forward']");
    private static final By btnForwardStrip =By.xpath("//p[text()='Forward Strip']");
    private static final By btnEuropeanOption=By.xpath("//p[text()='European Option']");
    private static final By btnOptionSpread=By.xpath("//p[text()='Option Spread']");
    private static final By btnCollar=By.xpath("//p[text()='Collar']");
    private static final By btnThreeWay=By.xpath("//p[text()='Three Way']");
    private static final By btnAbsoluteKO=By.xpath("//p[text()='TARF - Absolute KO']");
    private static final By btnPointsKO=By.xpath("//p[text()='TARF - Points KO']");
    private static final By btnLegsKO=By.xpath("//p[text()='TARF - Legs KO']");
    private static final By btnEKI=By.xpath("//p[text()='Digital EKI']");
    private static final By btnEKO=By.xpath("//p[text()='Digital EKO']");

    public NewFXForwardPage clickFXForward(){
        clickk(btnForward, WaitStrategy.CLICKABLE,"Forward button ");
        return new NewFXForwardPage();
    }
    public NewFXForwardStripPage clickForwardStrip(){
        clickk(btnForwardStrip,WaitStrategy.CLICKABLE,"ForwardStrip button");
        return new NewFXForwardStripPage();
    }
    public NewFXEuropeanOptionPage clickEuropianOption(){
        clickk(btnEuropeanOption,WaitStrategy.CLICKABLE,"Europian option button");
        return new NewFXEuropeanOptionPage();
    }
    public NewFXOptionSpreadPage clickOptionSpread(){
        clickk(btnOptionSpread,WaitStrategy.CLICKABLE," Option Spread button");
        return new NewFXOptionSpreadPage();
    }
    public NewFXCollorPage clickCollar(){
        clickk(btnCollar,WaitStrategy.CLICKABLE,"Collor button");
        return new NewFXCollorPage();
    }
    public NewFXThreeWayPage clickThreeWay(){
        clickk(btnThreeWay,WaitStrategy.CLICKABLE,"ThreeWay button");
        return new NewFXThreeWayPage();
    }
    public NewFXTarfAbsolutePage clickAbsoluteKO(){
        clickk(btnAbsoluteKO,WaitStrategy.CLICKABLE,"TARF-Absolute-KO");
        return new NewFXTarfAbsolutePage();
    }
    public NewFXTarfPointsPage clickPointsKO(){
        clickk(btnPointsKO,WaitStrategy.CLICKABLE,"TARF-points-KO");
        return new NewFXTarfPointsPage();
    }
    public NewFXTarfLegsPage clickLegsKO(){
        clickk(btnLegsKO,WaitStrategy.CLICKABLE,"TARF-Legs-KO");
        return new NewFXTarfLegsPage();
    }
    public NEWFXDigidtalEKIPage clickEKI(){
        clickk(btnEKI,WaitStrategy.CLICKABLE,"Digital EKI");
        return new NEWFXDigidtalEKIPage();
    }
    public NEWFXDigitalEKOPage clickEKO(){
        clickk(btnEKO,WaitStrategy.CLICKABLE,"Digital EKO");
        return new NEWFXDigitalEKOPage();
    }
}
