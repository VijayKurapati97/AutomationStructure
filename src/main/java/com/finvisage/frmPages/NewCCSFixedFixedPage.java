package com.finvisage.frmPages;

import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.finvisage.utils.XpathUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class NewCCSFixedFixedPage extends BasePageFRM {
    private final By underlying =By.xpath("(//div[@class='selectize-input items not-full has-options'])[1]");
    private final By marketData=By.xpath("//li[@id='market-data']");
    private final By discountingRateCurve1=By.xpath("//div[@id='discounting_rate_curve1_market_hot_container_discounting_curve_leg_1']");
    private final By discountingRateCurve2=By.xpath("//div[@id='discounting_rate_curve_market_hot_container_discounting_curve_leg_2']");
    private final By discountingCurveLeg2=By.xpath("//a[@id='#tab_1']");
    private final By zeroRateCurve=By.xpath("//table[@id='discounting_zero_rate_curve1']");
    private final By btnNext=By.xpath("//button[text()='Next']");
    private final By tenor=By.xpath("//input[@id='years']");
    private final By paymentFreq=By.xpath("(//select[@id='payment_frequency_leg3']/following-sibling::div/div)[1]");
    private final By paymentFreqmenu=By.xpath("(//select[@id='payment_frequency_leg3']/following-sibling::div/div)[2]");
    private final By price=By.xpath("//i[@class='far fa-play mr-1']/parent::a");
    private final By priceTable=By.xpath("//table[@id='pricing_output_table']");
    private final By fixedCashflow = By.xpath("(//div[@class='table-responsive'])[1]");
    private final By floatingCashflow = By.xpath("(//div[@class='table-responsive'])[2]");
    private final By notional=By.xpath("//input[@id='pricing_object_pricing_data_notional']");
    private final By valuationCcy=By.xpath("//select[@id='valuation_currency_select']//following-sibling::div/div[1]");

    public NewCCSFixedFixedPage selectUnderlying(String value){
        clickk(underlying, WaitStrategy.CLICKABLE,"Underlying dropdown");
        String underlyingValue = "//div[text()='%replace%']";
        String newXpath= XpathUtils.getXpath(underlyingValue,value);
        clickk(By.xpath(newXpath),WaitStrategy.CLICKABLE,value);
        return this;
    }

    public NewCCSFixedFixedPage selectValuationCcy(String value){
        jsClick(valuationCcy, WaitStrategy.CLICKABLE,"valuation Ccy dropdown");
        String valuationCcy = "(//div[text()='%replace%'])[1]";
        String newXpath= XpathUtils.getXpath(valuationCcy,value);
        jsClick(By.xpath(newXpath),WaitStrategy.CLICKABLE,value);
        return this;
    }
    public NewCCSFixedFixedPage clickMarketData(){
        clickk(marketData,WaitStrategy.CLICKABLE,"Market data");
        return this;
    }
    public NewCCSFixedFixedPage marketDataIsDisplayed(){
        isDisplayed(discountingRateCurve1,WaitStrategy.VISIBLE,"Discounting Curve 1");
        isDisplayed(zeroRateCurve,WaitStrategy.VISIBLE,"Zero Rate Curve");
        jsClick(discountingCurveLeg2,WaitStrategy.CLICKABLE,"discounting Curve Leg2");
        isDisplayed(discountingRateCurve2,WaitStrategy.VISIBLE,"Discounting Curve 2");
        return this;
    }
    public NewCCSFixedFixedPage clickNextButton(){
        clickk(btnNext,WaitStrategy.CLICKABLE,"Next Button");
        return this;
    }
    public NewCCSFixedFixedPage enterTenor(String tenorr){
        sendText(tenor,tenorr,WaitStrategy.PRESENCE,"Tenor");
        return this;
    }
    public NewCCSFixedFixedPage selectPaymentFrequency(String freq){
        jsClick(paymentFreq,WaitStrategy.CLICKABLE,"Payment frequency dropdown");
        if(isDisplayed(paymentFreqmenu,WaitStrategy.VISIBLE,"payment freq menu")) {
            String paymentFreqValue = "(//div[text()='%replace%'])[1]";
            String newxpath = XpathUtils.getXpath(paymentFreqValue, freq);
            jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, freq);
        }
        return this;
    }
    public NewCCSFixedFixedPage clickPrice(){
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        jsClick(price,WaitStrategy.CLICKABLE,"Price button");
        return this;
    }
    public boolean priceSectionIsDisplayed(){
        return isDisplayed(priceTable,WaitStrategy.VISIBLE,"Price section");
    }
    public boolean fixedCashFlowTablesDisplayed(){
        return isDisplayed(fixedCashflow,WaitStrategy.VISIBLE,"Fixed cashflow table");
    }
    public boolean floatingCashFlowTableIsDisplayed(){
        return isDisplayed(floatingCashflow,WaitStrategy.VISIBLE,"Floating cashflow");
    }
    public int getLastCellNum(){
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        List<WebElement> list= DriverManager.getDriver().findElements(By.xpath("//div[@id='leg3_schedules']" +
                "//div[@class='ht_master handsontable']//div[@class='wtSpreader']/table/tbody/tr"));
        return  list.size();

    }
    public String getNotional(){
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        return js.executeScript("return document.getElementById('pricing_object_pricing_data_notional').value").toString();
    }
    public String getPrincipalOutstanding(String value){
        String principal="//div[@id='leg3_schedules']//div[@class='ht_master handsontable']" +
                "//div[@class='wtSpreader']/table/tbody/tr[%replace%]/td[3]";
        String newXpath=XpathUtils.getXpath(principal,value);
        return getText(By.xpath(newXpath),WaitStrategy.VISIBLE,"ouptstanding principal");
    }
}
