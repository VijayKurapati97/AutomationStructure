package com.finvisage.tests;
import com.finvisage.drivers.DriverManager;
import com.finvisage.pages.*;
import com.finvisage.reports.ExtentManager;
import com.finvisage.utils.CommonUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Map;

public class DerivativePricerTests extends BaseTest{

    private DerivativePricerTests(){}

    @Test(groups = {"smoke","regression"})
    public void FX_ForwardTest(Map<String,String> data){

        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");

        LogInPage lp= new LogInPage();
        lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage dp=new DashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickFXForward();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Forward Price")
                .isNotEmpty()
                .isNotNull();

        NewFXForwardPage Fp= new NewFXForwardPage();
        Fp.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton().enterTenure(data.get("Tenure")).
                forwardRateDisplayed().clickDirection().selectDirectionValue(data.get("Direction"))
                .clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy")).
                enterNotional(data.get("Notional")).clickPrice().getPayoffGraph();

        String[] Str=   Fp. getEquivalentNotional();
        BigDecimal EquivalentNotional= CommonUtils.StringToInt(Str,0,0);
        Assertions.assertThat(EquivalentNotional)
                .isPositive()
                .isNotZero()
                .isNotNull()
                .isGreaterThan(BigDecimal.valueOf(1));
        System.out.println(EquivalentNotional);
        BigDecimal forwardRate=CommonUtils.StringToInt(Str,1,0);
        Assertions.assertThat(forwardRate).isNotNull()
                .isNotZero()
                .isPositive()
                .isGreaterThan(BigDecimal.valueOf(1));
        Fp.clickSavePrice();
    }



    @Test(groups = {"smoke","regression"})
    public void FX_ForwardStripTest(Map<String,String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");

        LogInPage lp=new LogInPage();
        lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        DashboardPage dp=new DashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickForwardStrip();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Forward Strip Price")
                .isNotEmpty()
                .isNotNull();

        NewFXForwardStripPage Fs=new NewFXForwardStripPage();
        Fs.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataIsDisplayed().clickNextButton().clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .clickDirection().selectDirectionValue(data.get("Direction")).enterNumLegs(data.get("NumberOfLegs"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().
                enterNotional(data.get("NotionalIndex")).clickPrice();

        String Str1= Fs.getWeightedAvgForward();
        String[] En  = Str1.split(" ");
        Double WeightedAvgForward = Double.parseDouble(En[0]);
        Assertions.assertThat(WeightedAvgForward)
                .isPositive()
                .isNotZero()
                .isNotNull()
                .isGreaterThan(1);
        Fs.clickSavePrice();
    }

    @Test(groups = {"smoke","regression"})
    public void FX_EuropeanOptionTest(Map<String,String> data){

        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");

        LogInPage lp= new LogInPage();
        lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();

        DashboardPage Dp=new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickEuropianOption();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX European Option Price")
                .isNotEmpty()
                .isNotNull();

        NewFXEuropeanOptionPage Er=new NewFXEuropeanOptionPage();
        Er.clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = Er.priceSectionDisplayed();//main
        BigDecimal Premium= CommonUtils.StringToInt(str,0,1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();
        BigDecimal ForwardRate= CommonUtils.StringToInt(str,1,0);
        Assertions.assertThat(ForwardRate).isNotNull()
                .isPositive()
                .isNotZero()
                .isGreaterThan(BigDecimal.valueOf(1));
        BigDecimal ForwardDelta= CommonUtils.StringToInt(str,2,0);
        Assertions.assertThat(ForwardDelta).isNotNull()
                .isNotZero();

        BigDecimal Vega= CommonUtils.StringToInt(str,3,0);
        Assertions.assertThat(Vega).isNotNull()
                .isNotZero();

        Er.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate()
                .getDeffermentRate().clickCheckBox().enterPremium(data.get("Premium(mid)")).clickByChangingDropdown().byChangingStrike(data.get("By_Changing"))
                .clickSeekStrike().clickSavePrice();
    }
    @Test(groups = {"smoke","regression"})
    public void FX_OptionSpreadTest(Map<String,String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp=new LogInPage();
        lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp=new DashboardPage();

        Dp.clickDerivativePricer().clickNewPrice().clickOptionSpread();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Option Spread Price")
                .isNotEmpty()
                .isNotNull();

        NewFXOptionSpreadPage Os= new NewFXOptionSpreadPage();
        Os.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickNotionalCcy().
                selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();
        String[] str = Os.priceSectionDisplayed();//main
        BigDecimal Premium= CommonUtils.StringToInt(str,0,1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        BigDecimal TotalEquivalentNotional= CommonUtils.StringToInt(str,1,0);
        Assertions.assertThat(TotalEquivalentNotional).isNotNull()
                .isPositive()
                .isNotZero()
                .isGreaterThan(BigDecimal.valueOf(1));

        BigDecimal ForwardDelta= CommonUtils.StringToInt(str,2,0);
        Assertions.assertThat(ForwardDelta).isNotNull()
                .isNotZero();

        BigDecimal Vega= CommonUtils.StringToInt(str,3,0);
        Assertions.assertThat(Vega).isNotNull()
                .isNotZero();
        Os.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule() .selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate()
                .getDeffermentRate().clickCheckBox().enterPremium(data.get("Premium(mid)")).clickByChangingDropdown().byChangingStrike(data.get("By_Changing"))
                .clickSeekStrike().clickSavePrice();
    }
    @Test(groups = {"smoke","regression"})
    public void FX_CollorTest(Map<String,String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp=new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickCollar();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Collar Price")
                .isNotEmpty()
                .isNotNull();

        NewFXCollorPage Cp=new NewFXCollorPage();
        Cp.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).clickNotionalCcy().
                selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = Cp.priceSectionDisplayed();//main
        BigDecimal Premium= CommonUtils.StringToInt(str,0,1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        BigDecimal TotalEquivalentNotional= CommonUtils.StringToInt(str,1,0);
        Assertions.assertThat(TotalEquivalentNotional).isNotNull()
                .isPositive()
                .isNotZero()
                .isGreaterThan(BigDecimal.valueOf(1));

        BigDecimal ForwardDelta= CommonUtils.StringToInt(str,2,0);
        Assertions.assertThat(ForwardDelta).isNotNull()
                .isNotZero();

        BigDecimal Vega= CommonUtils.StringToInt(str,3,0);
        Assertions.assertThat(Vega).isNotNull()
                .isNotZero();

        Cp.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule() .selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate()
                .getDeffermentRate().clickCheckBox().enterPremium(data.get("Premium(mid)")).clickByChangingDropdown().byChangingStrike(data.get("By_Changing"))
                .clickSeekStrike().clickSavePrice();

    }
    @Test(groups = {"smoke","regression"})
    public void FX_ThreeWayTest(Map<String,String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp= new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickThreeWay();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Three Way Price")
                .isNotEmpty()
                .isNotNull();

        NewFXThreeWayPage Tw=new NewFXThreeWayPage();
        Tw.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().
                selectDirectionValue(data.get("Direction"))
                .enterStrike1(data.get("Strike1")).enterStrike2(data.get("Strike2")).enterStrike3(data.get("Strike3"))
                .clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = Tw.priceSectionDisplayed();//main
        BigDecimal Premium= CommonUtils.StringToInt(str,0,1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        BigDecimal TotalEquivalentNotional= CommonUtils.StringToInt(str,1,0);
        Assertions.assertThat(TotalEquivalentNotional).isNotNull()
                .isPositive()
                .isNotZero()
                .isGreaterThan(BigDecimal.valueOf(1));

        BigDecimal ForwardDelta= CommonUtils.StringToInt(str,2,0);
        Assertions.assertThat(ForwardDelta).isNotNull()
                .isNotZero();

        BigDecimal Vega= CommonUtils.StringToInt(str,3,0);
        Assertions.assertThat(Vega).isNotNull()
                .isNotZero();

        Tw.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule() .selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate()
                .getDeffermentRate().clickCheckBox().enterPremium(data.get("Premium(mid)")).clickByChangingDropdown()
                .byChangingStrike(data.get("By_Changing"))
                .clickSeekStrike().clickSavePrice();

    }
    @Test(groups = {"smoke","regression"})
    public void FX_TarfAbsolutTest(Map<String,String>data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp=new DashboardPage();

        Dp.clickDerivativePricer().clickNewPrice().clickAbsoluteKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Tarf Absolute Price")
                .isNotEmpty()
                .isNotNull();
        NewFXTarfAbsolutePage Ab= new NewFXTarfAbsolutePage();
        Ab.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().clickDirection().selectDirectionValue(data.get("DirectionValue")).clickNotionalCcy()
                .selectNotionalCcyValue(data.get("NotionalCcyIndex")).clickKnockoutCcy().selectKnockoutCcyValue(data.get("KnockoutCcyIndex"))
                .enterKnockout(data.get("Knockout")).enterNumberOfTarfLegs(data.get("NumberOfTarfLegs")).selectTarfSetSchedule().selectTarfSchedule(data.get("TarfSchedule"))
                .clickOkTarfSchedule().clickTarfGenerateLegs().enterNotional(data.get("NumberOfTarfLegs")).enterStrikes(data.get("NumberOfTarfLegs"))
                .clickPriceButton();

        String[] str = Ab.priceSectionDisplayed();//main
        BigDecimal Premium= CommonUtils.StringToInt(str,0,1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        BigDecimal WeightedAvgForward= CommonUtils.StringToInt(str,1,0);
        Assertions.assertThat(WeightedAvgForward).isNotNull()
                .isPositive()
                .isNotZero()
                .isGreaterThan(BigDecimal.valueOf(1));


        Ab.graphIsDisplayed().clickDefferWithpPremium().clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs"))
                .enterDiscountingRate(data.get("DidcountingRate")).selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule()
                .clickGenerateLegs().clickCalculate().getDeffermentRate().clickSavePriceButton();

    }
    @Test(groups = {"smoke","regression"})
    public void FX_TarfPointsTest(Map<String,String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp=new DashboardPage();

        Dp.clickDerivativePricer().clickNewPrice().clickPointsKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Tarf Point Price")
                .isNotEmpty()
                .isNotNull();

        NewFXTarfPointsPage Tp= new NewFXTarfPointsPage();
        Tp.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().clickDirection().selectDirectionValue(data.get("DirectionValue")).clickNotionalCcy()
                .selectNotionalCcyValue(data.get("NotionalCcyValue")).enterKnockout(data.get("Knockout")).enterNumberOfTarfLegs(data.get("NumberOfTarfLegs")).
                selectTarfSetSchedule().selectTarfSchedule(data.get("TarfSchedule"))
                .clickOkTarfSchedule().clickTarfGenerateLegs().enterNotional(data.get("NumberOfTarfLegs")).enterStrikes(data.get("NumberOfTarfLegs"))
                .clickPriceButton();

        String[] str = Tp.priceSectionDisplayed();//main
        BigDecimal Premium= CommonUtils.StringToInt(str,0,1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        BigDecimal WeightedAvgForward= CommonUtils.StringToInt(str,1,0);
        Assertions.assertThat(WeightedAvgForward).isNotNull()
                .isPositive()
                .isNotZero()
                .isGreaterThan(BigDecimal.valueOf(1));

        Tp.graphIsDisplayed().clickDefferWithpPremium().clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs"))
                .enterDiscountingRate(data.get("DidcountingRate")).selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule()
                .clickGenerateLegs().clickCalculate().getDeffermentRate().clickSavePriceButton();

    }
    @Test(groups = {"smoke","regression"})
    public void FX_TarfLegsTest(Map<String,String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp=new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickLegsKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Tarf Leg Price")
                .isNotEmpty()
                .isNotNull();

        NewFXTarfLegsPage Tl=new NewFXTarfLegsPage();
        Tl.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().clickDirection().selectDirectionValue(data.get("DirectionValue")).clickNotionalCcy()
                .selectNotionalCcyValue(data.get("NotionalCcyValue")).enterKnockout(data.get("KnockoutLegs")).enterNumberOfTarfLegs(data.get("NumberOfTarfLegs")).
                selectTarfSetSchedule().selectTarfSchedule(data.get("TarfSchedule"))
                .clickOkTarfSchedule().clickTarfGenerateLegs().enterNotional(data.get("NumberOfTarfLegs")).enterStrikes(data.get("NumberOfTarfLegs"))
                .clickPriceButton();

        String[] str = Tl.priceSectionDisplayed();//main
        BigDecimal Premium= CommonUtils.StringToInt(str,0,1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        BigDecimal WeightedAvgForward= CommonUtils.StringToInt(str,1,0);
        Assertions.assertThat(WeightedAvgForward).isNotNull()
                .isPositive()
                .isNotZero()
                .isGreaterThan(BigDecimal.valueOf(1));

        Tl.graphIsDisplayed().clickDefferWithpPremium().clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs"))
                .enterDiscountingRate(data.get("DidcountingRate")).selectSetSchedule().selectSchedule(data.get("Schedule")).clickOkSchedule()
                .clickGenerateLegs().clickCalculate().getDeffermentRate().clickSavePriceButton();
    }
    @Test(groups = {"smoke","regression"})
    public void FXDigitalEKITest(Map<String,String>data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp=new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickEKI();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockin Price")
                .isNotEmpty()
                .isNotNull();

        NEWFXDigidtalEKIPage Eki=new NEWFXDigidtalEKIPage();
        Eki.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).enterBarrier(data.get("Barrier")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();
        String[] str = Eki.priceSectionDisplayed();//main
        BigDecimal Premium= CommonUtils.StringToInt(str,0,1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        BigDecimal TotalEquivalentNotional= CommonUtils.StringToInt(str,1,0);
        Assertions.assertThat(TotalEquivalentNotional).isNotNull()
                .isPositive()
                .isNotZero()
                .isGreaterThan(BigDecimal.valueOf(1));

        BigDecimal ForwardDelta= CommonUtils.StringToInt(str,2,0);
        Assertions.assertThat(ForwardDelta).isNotNull()
                .isNotZero();

        BigDecimal Vega= CommonUtils.StringToInt(str,3,0);
        Assertions.assertThat(Vega).isNotNull()
                .isNotZero();

        Eki.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule() .selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate()
                .getDeffermentRate().clickSavePrice();
    }
    @Test(groups = {"smoke","regression"})
    public void FXDigitalEKOTest(Map<String,String>data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke").assignCategory("Regression");
        LogInPage lp = new LogInPage();
        lp.enterUserName(data.get("Email")).enterPassword(data.get("Password")).clickLogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        DashboardPage Dp=new DashboardPage();
        Dp.clickDerivativePricer().clickNewPrice().clickEKO();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New FX Digital European Knockout Price")
                .isNotEmpty()
                .isNotNull();
        NEWFXDigitalEKOPage Eko=new NEWFXDigitalEKOPage();
        Eko.clickUnderlying()
                .selectUnderlying(data.get("UnderlyingValue")).clickMarketData().marketDataIsDisplayed()
                .clickNextButton().enterTenure(data.get("Tenure")).clickDirection().selectDirectionValue(data.get("Direction"))
                .enterStrike(data.get("Strike")).enterBarrier(data.get("Barrier")).clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy"))
                .enterNotional(data.get("Notional")).clickPricebutton();

        String[] str = Eko.priceSectionDisplayed();//main
        BigDecimal Premium= CommonUtils.StringToInt(str,0,1);
        Assertions.assertThat(Premium).isNotZero()
                .isNotNull()
                .isPositive();

        BigDecimal TotalEquivalentNotional= CommonUtils.StringToInt(str,1,0);
        Assertions.assertThat(TotalEquivalentNotional).isNotNull()
                .isPositive()
                .isNotZero()
                .isGreaterThan(BigDecimal.valueOf(1));

        BigDecimal ForwardDelta= CommonUtils.StringToInt(str,2,0);
        Assertions.assertThat(ForwardDelta).isNotNull()
                .isNotZero();

        BigDecimal Vega= CommonUtils.StringToInt(str,3,0);
        Assertions.assertThat(Vega).isNotNull()
                .isNotZero();

        Eko.graphIsDisplayed().clickDefferWithpPremium()
                .clickDefferedPremuim().enterNumberOfLegs(data.get("NumberOfLegs")).enterDiscountingRate(data.get("DiscountingRate"))
                .selectSetSchedule() .selectSchedule(data.get("Schedule")).clickOkSchedule().clickGenerateLegs().clickCalculate()
                .getDeffermentRate().clickSavePrice();
    }
}
