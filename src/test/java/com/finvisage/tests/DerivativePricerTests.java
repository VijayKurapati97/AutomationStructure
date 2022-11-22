package com.finvisage.tests;

import com.finvisage.pages.LogInPage;
import com.finvisage.reports.ExtentManager;
import org.testng.annotations.Test;

import java.util.Map;

public class DerivativePricerTests extends BaseTest{
    private DerivativePricerTests(){}

    @Test(groups = {"smoke","regression"})
    public void FX_ForwardTest(Map<String,String> data){

        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp= new LogInPage();
        lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn().
                clickDerivativePricer().clickNewPrice().clickFXForward().clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton().enterTenure(data.get("Tenure")).
                forwardRateDisplayed().clickDirection().selectDirectionValue(data.get("Direction"))
                .clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy")).
                enterNotional(data.get("Notional")).clickPrice().getPayoffGraph().getEquivalentNotional().getForwardRate();
    }
    @Test(groups = {"smoke","regression"})
    public void FX_ForwardStripTest(Map<String,String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp=new LogInPage();
        lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn().
                clickDerivativePricer().clickNewPrice().clickForwardStrip().clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton().clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .clickDirection().selectDirectionValue(data.get("Direction")).enterNumLegs(data.get("NumberOfLegs"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().
                enterNotional(data.get("NotionalIndex")).clickPrice().clickSavePrice();
    }

    @Test(groups = {"smoke","regression"})
    public void FX_EuropeanOptionTest(Map<String,String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp= new LogInPage();
        lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn()
                .clickDerivativePricer().clickNewPrice().clickEuropianOption().clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton().priceSectionDisplayed().graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate()
                .getDeffermentRate().clickCheckBox().enterPremium(data.get("Premium(mid)")).clickByChangingDropdown().byChangingStrike(data.get("By_Changing"))
                .clickSeekStrike().clickSavePrice();
    }
    @Test(groups = {"smoke","regression"})
    public void FX_OptionSpreadTest(Map<String,String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp=new LogInPage();
        lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn()
                .clickDerivativePricer().clickNewPrice().clickOptionSpread().clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickNotionalCcy().
                selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton().priceSectionDisplayed().graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule() .selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate()
                .getDeffermentRate().clickCheckBox().enterPremium(data.get("Premium(mid)")).clickByChangingDropdown().byChangingStrike(data.get("By_Changing"))
                .clickSeekStrike().clickSavePrice();
    }
    @Test(groups = {"smoke","regression"})
    public void FX_CollorTest(Map<String,String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn()
                .clickDerivativePricer().clickNewPrice().clickCollar().clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickNotionalCcy().
                selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton().priceSectionDisplayed().graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule() .selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate()
                .getDeffermentRate().clickCheckBox().enterPremium(data.get("Premium(mid)")).clickByChangingDropdown().byChangingStrike(data.get("By_Changing"))
                .clickSeekStrike().clickSavePrice();

    }
    @Test(groups = {"smoke","regression"})
    public void FX_ThreeWayTest(Map<String,String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn()
                .clickDerivativePricer().clickNewPrice().clickThreeWay().clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).enterStrike3(data.get("Strike3"))
                .clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton().priceSectionDisplayed().graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule() .selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate()
                .getDeffermentRate().clickCheckBox().enterPremium(data.get("Premium(mid)")).clickByChangingDropdown().byChangingStrike(data.get("By_Changing"))
                .clickSeekStrike().clickSavePrice();

    }
    @Test(groups = {"smoke","regression"})
    public void FX_TarfAbsolutTest(Map<String,String>data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn()
                .clickDerivativePricer().clickNewPrice().clickAbsoluteKO().clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().clickDirection().selectDirectionValue(data.get("DirectionValue")).clickNotionalCcy()
                .selectNotionalCcyValue(data.get("NotionalCcyIndex")).clickKnockoutCcy().selectKnockoutCcyValue(data.get("KnockoutCcyIndex"))
                .enterKnockout(data.get("Knockout")).enterNumberOfTarfLegs(data.get("NumberOfTarfLegs")).selectTarfSetSchedule().selectTarfSchedule(data.get("TarfSchedule"))
                .clickOkTarfSchedule().clickTarfGenerateLegs().enterNotional(data.get("NumberOfTarfLegs")).enterStrikes(data.get("NumberOfTarfLegs"))
                .clickPriceButton().priceSectionDisplayed().graphIsDisplayed().clickDefferWithpPremium().clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs"))
                .enterDiscountingRate(data.get("DidcountingRate")).selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule()
                .clickGenerateLegs().clickCalculate().getDeffermentRate().clickSavePriceButton();

    }
    @Test(groups = {"smoke","regression"})
    public void FX_TarfPointsTest(Map<String,String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn()
                .clickDerivativePricer().clickNewPrice().clickPointsKO().clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().clickDirection().selectDirectionValue(data.get("DirectionValue")).clickNotionalCcy()
                .selectNotionalCcyValue(data.get("NotionalCcyValue")).enterKnockout(data.get("Knockout")).enterNumberOfTarfLegs(data.get("NumberOfTarfLegs")).
                selectTarfSetSchedule().selectTarfSchedule(data.get("TarfSchedule"))
                .clickOkTarfSchedule().clickTarfGenerateLegs().enterNotional(data.get("NumberOfTarfLegs")).enterStrikes(data.get("NumberOfTarfLegs"))
                .clickPriceButton().priceSectionDisplayed().graphIsDisplayed().clickDefferWithpPremium().clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs"))
                .enterDiscountingRate(data.get("DidcountingRate")).selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule()
                .clickGenerateLegs().clickCalculate().getDeffermentRate().clickSavePriceButton();

    }
    @Test(groups = {"smoke","regression"})
    public void FX_TarfLegsTest(Map<String,String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn()
                .clickDerivativePricer().clickNewPrice().clickLegsKO().clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().clickDirection().selectDirectionValue(data.get("DirectionValue")).clickNotionalCcy()
                .selectNotionalCcyValue(data.get("NotionalCcyValue")).enterKnockout(data.get("KnockoutLegs")).enterNumberOfTarfLegs(data.get("NumberOfTarfLegs")).
                selectTarfSetSchedule().selectTarfSchedule(data.get("TarfSchedule"))
                .clickOkTarfSchedule().clickTarfGenerateLegs().enterNotional(data.get("NumberOfTarfLegs")).enterStrikes(data.get("NumberOfTarfLegs"))
                .clickPriceButton().priceSectionDisplayed().graphIsDisplayed().clickDefferWithpPremium().clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs"))
                .enterDiscountingRate(data.get("DidcountingRate")).selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule()
                .clickGenerateLegs().clickCalculate().getDeffermentRate().clickSavePriceButton();
    }
    @Test(groups = {"smoke","regression"})
    public void FXDigitalEKITest(Map<String,String>data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn()
                .clickDerivativePricer().clickNewPrice().clickEKI().clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).enterBarrier(data.get("Barrier")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton().priceSectionDisplayed().graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule() .selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate()
                .getDeffermentRate().clickSavePrice();
    }
    @Test(groups = {"smoke","regression"})
    public void FXDigitalEKOTest(Map<String,String>data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn()
                .clickDerivativePricer().clickNewPrice().clickEKO().clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).enterBarrier(data.get("Barrier")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton().priceSectionDisplayed().graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule() .selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate()
                .getDeffermentRate().clickSavePrice();
    }
}
