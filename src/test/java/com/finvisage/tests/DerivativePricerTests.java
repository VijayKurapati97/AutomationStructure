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
                clickDerivativePricer().clickNewPrice().clickFX_Forward().clickUnderlying().
                selectUnderlying(data.get("UnderlyingValue")).clickMarketData().
                marketDataAvilability().clickNextButton().enterTenure(data.get("Tenure")).
                forwardRateDisplayed().clickDirection().selectDirectionValue(data.get("Direction"))
                .clickNotionalCcy().selectNotionalCcyValue(data.get("NotionalCcy")).
                enterNotional(data.get("Notional")).clickPrice().getPayoffGraph().getEquivalentNotional().getForwardRate();
    }
}
