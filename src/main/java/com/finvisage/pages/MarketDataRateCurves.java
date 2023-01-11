package com.finvisage.pages;

import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.finvisage.utils.XpathUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MarketDataRateCurves extends BasePage {
    private final By underlyingType= By.xpath("//div[@class='select asset-type-select active']/div/child::div[1]");
    private final By Underlying=By.xpath("//select[@id='asset-id-select']/following-sibling ::div");
    private final By ScheduleTime=By.xpath("//select[@id='market-time-select']/following-sibling ::div");
    private final By timeValue=By.xpath("(//div)[86]/child::div");
    private final By link_DerivativePricer= By.xpath("//a[@data-original-title='Derivative Pricer']");
    private final By SpotRate=By.id("spot_rate");
    private final By btn_Save=By.xpath("//a[@id='forward_curve_save_btn']/i");

    public MarketDataRateCurves selectunderlyingType(String text){
        clickk(underlyingType, WaitStrategy.CLICKABLE,"underlying type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String underlyingType = "//div[text()='%replace%']";
        String newxpath= XpathUtils.getXpath(underlyingType,text);
        clickk(By.xpath(newxpath),WaitStrategy.CLICKABLE,text);
        return this;
    }
    public MarketDataRateCurves selectUnderlying(String text){
        clickk(Underlying,WaitStrategy.CLICKABLE,"Underlying");
        String underlying = "//div[text()='%replace%']";
        String newxpath= XpathUtils.getXpath(underlying,text);
        clickk(By.xpath(newxpath),WaitStrategy.CLICKABLE,text);
        return this;
    }
    public MarketDataRateCurves selectScheduleTime() {
        clickk(ScheduleTime, WaitStrategy.CLICKABLE, "schedule time");
        List<WebElement> list = DriverManager.getDriver().findElements(timeValue);
        Actions act = new Actions(DriverManager.getDriver());
        act.doubleClick(list.get(list.size() - 1)).perform();
        return this;
    }
    public String getDate(int index){
        String date = "//tbody[@id='swap-points-data']/tr[%replace%]/td";
        String newXpath=XpathUtils.getXpath(date,Integer.toString(index));
     return getAttribute(By.xpath(newXpath),WaitStrategy.VISIBLE,"key");
    }
    public String getForwardRateMid(int index){
        String Rate = "//tbody[@id='swap-points-data']/tr[%replace%]/td[3]";
        String newXpath=XpathUtils.getXpath(Rate,Integer.toString(index));
        return getText(By.xpath(newXpath),WaitStrategy.VISIBLE,"MarketData ForwardRate");

    }
    public StructuresPage clickDerivativePricer(){
        jsClick(link_DerivativePricer, WaitStrategy.CLICKABLE, "Derivative pricer");
        return new StructuresPage();
    }
    public String getForwardRateAsk(int index){
        String Rate = "//tbody[@id='swap-points-data']/tr[%replace%]/td[4]";
        String newXpath=XpathUtils.getXpath(Rate,Integer.toString(index));
        return getText(By.xpath(newXpath),WaitStrategy.VISIBLE,"MarketData ForwardRate");

    }
    public String getForwardRateBid(int index){
        String Rate = "//tbody[@id='swap-points-data']/tr[%replace%]/td[2]";
        String newXpath=XpathUtils.getXpath(Rate,Integer.toString(index));
        return getText(By.xpath(newXpath),WaitStrategy.VISIBLE,"MarketData ForwardRate");

    }
    public String getMarketDataSpotRate(){
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        return js.executeScript("return document.getElementById('spot_rate').value").toString();
    }
    public void clearSpotRate(){
        DriverManager.getDriver().findElement(SpotRate).clear();
    }
    public MarketDataRateCurves enterSpotRate(String rate){
        sendText(SpotRate,rate,WaitStrategy.PRESENCE,"Spot Rate");
        DriverManager.getDriver().findElement(SpotRate).sendKeys(Keys.ENTER);
        return this;
    }
    public MarketDataRateCurves clickSaveButton(){
        jsClick(btn_Save,WaitStrategy.CLICKABLE,"Save Button");
        return this;
    }
}
