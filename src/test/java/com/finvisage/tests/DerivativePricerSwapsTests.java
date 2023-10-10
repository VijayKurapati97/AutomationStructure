package com.finvisage.tests;

import com.finvisage.drivers.DriverManager;
import com.finvisage.frmPages.*;
import com.finvisage.reports.ExtentManager;
import com.finvisage.utils.CommonUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.within;

public class DerivativePricerSwapsTests extends BaseTest{
    private DerivativePricerSwapsTests(){}

    @Test(groups = {"smoke", "regression"})
    public void Vanilla_FixedFloatSwap_001(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression").assignCategory("Smoke");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickSwaps().clickVanillaFixedFloat();
        NewVanillaFixedFloatSwapPage vnp=new NewVanillaFixedFloatSwapPage();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New Vanilla Fixed Float Swap Price")
                .isNotEmpty()
                .isNotNull();
        vnp.selectUnderlying(data.get("UnderlyingValue")).selectStructure(data.get("Structure"))
                .clickMarketData().marketDataIsDisplayed().clickNextButton().enterTenor(data.get("Tenor"))
                .selectPaymentFrequency(data.get("PaymentFrequency")).clickPrice();

        Assertions.assertThat(true).isEqualTo(vnp.priceSectionIsDisplayed());
        Assertions.assertThat(true).isEqualTo(vnp.fixedCashFlowTablesDisplayed());
        Assertions.assertThat(true).isEqualTo(vnp.floatingCashFlowTableIsDisplayed());
    }

    @Test(groups = {"regression"})
    public void Vanilla_FixedFloatSwap_002(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickSwaps().clickVanillaFixedFloat();
        NewVanillaFixedFloatSwapPage vnp=new NewVanillaFixedFloatSwapPage();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New Vanilla Fixed Float Swap Price")
                .isNotEmpty()
                .isNotNull();
        vnp.selectUnderlying(data.get("UnderlyingValue")).selectStructure(data.get("Structure"))
                .clickMarketData().marketDataIsDisplayed().clickNextButton().enterTenor(data.get("Tenor"))
                .selectPaymentFrequency(data.get("PaymentFrequency"));
        double value = CommonUtils.stringToDouble(vnp.getNotional()) / vnp.getLastCellNum();
        for(int i=1;i<vnp.getLastCellNum();i++){
            double val = CommonUtils.stringToDouble(vnp.getPrincipalOutstanding(String.valueOf(i))) - value;
            Assertions.assertThat( CommonUtils.stringToDouble(vnp.getPrincipalOutstanding(String.valueOf(i+1))))
                    .isCloseTo(val,within(Double.valueOf("0.01")));

        }

    }

    //-----------------------------------------------------------------------------------------------------------------------
    @Test(groups = {"smoke", "regression"})
    public void CrossCurrency_FixedFloatSwap_001(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression").assignCategory("Smoke");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickSwaps().clickCrossCurrencyFixedFloat();
        NewCCSFixedFloatPage cp=new NewCCSFixedFloatPage();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New Cross Currency Fixed Float Swap Price")
                .isNotEmpty()
                .isNotNull();
        cp.selectUnderlying(data.get("UnderlyingValue")).selectStructure(data.get("Structure")).selectValuationCcy(data.get("ValuationCcy"))
                .clickMarketData().marketDataIsDisplayed().clickNextButton().enterTenor(data.get("Tenor"))
                .selectPaymentFrequency(data.get("PaymentFrequency")).clickPrice();

        Assertions.assertThat(true).isEqualTo(cp.priceSectionIsDisplayed());
        Assertions.assertThat(true).isEqualTo(cp.fixedCashFlowTablesDisplayed());
        Assertions.assertThat(true).isEqualTo(cp.floatingCashFlowTableIsDisplayed());
    }
    @Test(groups = {"regression"})
    public void CrossCurrency_FixedFloatSwap_002(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickSwaps().clickCrossCurrencyFixedFloat();
        NewCCSFixedFloatPage cp=new NewCCSFixedFloatPage();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New Cross Currency Fixed Float Swap Price")
                .isNotEmpty()
                .isNotNull();
        cp.selectUnderlying(data.get("UnderlyingValue")).selectStructure(data.get("Structure")).selectValuationCcy(data.get("ValuationCcy"))
                .clickMarketData().marketDataIsDisplayed().clickNextButton().enterTenor(data.get("Tenor"))
                .selectPaymentFrequency(data.get("PaymentFrequency"));
        double value = CommonUtils.stringToDouble(cp.getNotional()) / cp.getLastCellNum();
        for(int i=1;i<cp.getLastCellNum();i++){
            double val = CommonUtils.stringToDouble(cp.getPrincipalOutstanding(String.valueOf(i))) - value;
            Assertions.assertThat( CommonUtils.stringToDouble(cp.getPrincipalOutstanding(String.valueOf(i+1))))
                    .isCloseTo(val,within( Double.valueOf("0.01")));

        }

    }

    //----------------------------------------------------------------------------------------------------------------
    @Test(groups = {"smoke", "regression"})
    public void CrossCurrency_FloatFixedSwap_001(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression").assignCategory("Smoke");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickSwaps().clickCrossCurrencyFloatFixed();
        NewCCSFloatFixedPage cs=new NewCCSFloatFixedPage();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New Cross Currency Float Fixed Swap Price")
                .isNotEmpty()
                .isNotNull();
        cs.selectUnderlying(data.get("UnderlyingValue")).selectStructure(data.get("Structure")).selectValuationCcy(data.get("ValuationCcy"))
                .clickMarketData().marketDataIsDisplayed().clickNextButton().enterTenor(data.get("Tenor"))
                .selectPaymentFrequency(data.get("PaymentFrequency")).clickPrice();

        Assertions.assertThat(true).isEqualTo(cs.priceSectionIsDisplayed());
        Assertions.assertThat(true).isEqualTo(cs.fixedCashFlowTablesDisplayed());
        Assertions.assertThat(true).isEqualTo(cs.floatingCashFlowTableIsDisplayed());
    }
    @Test(groups = {"regression"})
    public void CrossCurrency_FloatFixedSwap_002(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickSwaps().clickCrossCurrencyFloatFixed();
        NewCCSFloatFixedPage cs=new NewCCSFloatFixedPage();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New Cross Currency Float Fixed Swap Price")
                .isNotEmpty()
                .isNotNull();
        cs.selectUnderlying(data.get("UnderlyingValue")).selectStructure(data.get("Structure")).selectValuationCcy(data.get("ValuationCcy"))
                .clickMarketData().marketDataIsDisplayed().clickNextButton().enterTenor(data.get("Tenor"))
                .selectPaymentFrequency(data.get("PaymentFrequency"));
        double value = CommonUtils.stringToDouble(cs.getNotional()) / cs.getLastCellNum();
        for(int i=1;i<cs.getLastCellNum();i++){
            double val = CommonUtils.stringToDouble(cs.getPrincipalOutstanding(String.valueOf(i))) - value;
            Assertions.assertThat( CommonUtils.stringToDouble(cs.getPrincipalOutstanding(String.valueOf(i+1))))
                    .isCloseTo(val,within( Double.valueOf("0.01")));

        }
    }
    //------------------------------------------------------------------------------------------------------------------
    @Test(groups = {"smoke", "regression"})
    public void CrossCurrency_FixedFixedSwap_001(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression").assignCategory("Smoke");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickSwaps().clickCrossCurrencyFixedFixed();
        NewCCSFixedFixedPage cf=new NewCCSFixedFixedPage();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New Cross Currency Fixed Fixed Swap Price")
                .isNotEmpty()
                .isNotNull();
        cf.selectUnderlying(data.get("UnderlyingValue")).selectValuationCcy(data.get("ValuationCcy"))
                .clickMarketData().marketDataIsDisplayed().clickNextButton().enterTenor(data.get("Tenor"))
                .selectPaymentFrequency(data.get("PaymentFrequency")).clickPrice();

        Assertions.assertThat(true).isEqualTo(cf.priceSectionIsDisplayed());
        Assertions.assertThat(true).isEqualTo(cf.fixedCashFlowTablesDisplayed());
        Assertions.assertThat(true).isEqualTo(cf.floatingCashFlowTableIsDisplayed());
    }

    @Test(groups = {"smoke", "regression"})
    public void CrossCurrency_FixedFixedSwap_002(Map<String, String> data){
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        FRMLogInPage lp = new FRMLogInPage();
        lp.LogIn();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - Dashboard")
                .isNotEmpty()
                .isNotNull();
        FRMDashboardPage dp = new FRMDashboardPage();
        dp.clickDerivativePricer().clickNewPrice().clickSwaps().clickCrossCurrencyFixedFixed();
        NewCCSFixedFixedPage cf=new NewCCSFixedFixedPage();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Finvisage - New Cross Currency Fixed Fixed Swap Price")
                .isNotEmpty()
                .isNotNull();
        cf.selectUnderlying(data.get("UnderlyingValue")).selectValuationCcy(data.get("ValuationCcy"))
                .clickMarketData().marketDataIsDisplayed().clickNextButton().enterTenor(data.get("Tenor"))
                .selectPaymentFrequency(data.get("PaymentFrequency"));

        double value = CommonUtils.stringToDouble(cf.getNotional()) / cf.getLastCellNum();
        for(int i=1;i<cf.getLastCellNum();i++) {
            double val = CommonUtils.stringToDouble(cf.getPrincipalOutstanding(String.valueOf(i))) - value;
            Assertions.assertThat(CommonUtils.stringToDouble(cf.getPrincipalOutstanding(String.valueOf(i + 1))))
                    .isCloseTo(val, within( Double.valueOf("0.01")));
        }
    }

}
