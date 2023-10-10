package com.finvisage.frmPages;

import com.finvisage.enums.WaitStrategy;
import org.openqa.selenium.By;

public class DerivativeStructuresPage extends BasePageFRM {
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
    private static final By btnSwaps=By.xpath("//a[@id='fx-swaps']");
    private static final By btnVanillaFixedFloat=By.xpath("(//div[text()='Fixed-Float'])[2]");
    private static final By btnCrossCurrencyFixedFloat=By.xpath("(//div[text()='Fixed-Float'])[3]");
    private static final By btnCrossCurrencyFloatFixed=By.xpath("//div[text()='Float-Fixed']");
    private static final By btnCrossCurrencyFixedFixed=By.xpath("(//div[text()='Fixed-Fixed'])[2]");


    public void clickFXForward(){
        clickk(btnForward, WaitStrategy.CLICKABLE,"Forward button ");
        new NewFXForwardPage();
    }
    public void clickForwardStrip(){
        clickk(btnForwardStrip,WaitStrategy.CLICKABLE,"ForwardStrip button");
        new NewFXForwardStripPage();
    }
    public void clickEuropianOption(){
        clickk(btnEuropeanOption,WaitStrategy.CLICKABLE,"Europian option button");
        new NewFXEuropeanOptionPage();
    }
    public void clickOptionSpread(){
        clickk(btnOptionSpread,WaitStrategy.CLICKABLE," Option Spread button");
        new NewFXOptionSpreadPage();
    }
    public NewFXCollorPage clickCollar(){
        clickk(btnCollar,WaitStrategy.CLICKABLE,"Collor button");
        return new NewFXCollorPage();
    }
    public void clickThreeWay(){
        clickk(btnThreeWay,WaitStrategy.CLICKABLE,"ThreeWay button");
        new NewFXThreeWayPage();
    }
    public void clickAbsoluteKO(){
        clickk(btnAbsoluteKO,WaitStrategy.CLICKABLE,"TARF-Absolute-KO");
        new NewFXTarfAbsolutePage();
    }
    public void clickPointsKO(){
        clickk(btnPointsKO,WaitStrategy.CLICKABLE,"TARF-points-KO");
        new NewFXTarfPointsPage();
    }
    public void clickLegsKO(){
        clickk(btnLegsKO,WaitStrategy.CLICKABLE,"TARF-Legs-KO");
        new NewFXTarfLegsPage();
    }
    public void clickEKI(){
        clickk(btnEKI,WaitStrategy.CLICKABLE,"Digital EKI");
        new NEWFXDigidtalEKIPage();
    }
    public void clickEKO(){
        clickk(btnEKO,WaitStrategy.CLICKABLE,"Digital EKO");
        new NEWFXDigitalEKOPage();
    }
    public DerivativeStructuresPage clickSwaps(){
        jsClick(btnSwaps,WaitStrategy.CLICKABLE,"Swaps");
        return this;
    }
    public void clickVanillaFixedFloat(){
        clickk(btnVanillaFixedFloat,WaitStrategy.CLICKABLE,"Vanilla fixed float");
        new NewVanillaFixedFloatSwapPage();
    }
    public void clickCrossCurrencyFixedFloat(){
        clickk(btnCrossCurrencyFixedFloat,WaitStrategy.CLICKABLE,"Cross Currency fixed float");
        new NewCCSFixedFloatPage();
    }
    public void clickCrossCurrencyFloatFixed(){
        clickk(btnCrossCurrencyFloatFixed,WaitStrategy.CLICKABLE,"Cross Currency float fixed");
        new NewCCSFloatFixedPage();
    }
    public NewCCSFloatFixedPage clickCrossCurrencyFixedFixed(){
        clickk(btnCrossCurrencyFixedFixed,WaitStrategy.CLICKABLE,"Cross Currency fixed fixed");
        return new NewCCSFloatFixedPage();
    }
}
