package com.finvisage.frmPages;

import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.finvisage.utils.XpathUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class NewFXForwardStripPage extends BasePageFRM {
    private static final By underlying=By.xpath("//select[@id='pricing_object_pricing_data_asset_id']/following-sibling ::div");
    private final By btnMarketData=By.xpath("//li[@id='market-data']");
    private final By marketDataTable=By.xpath("//table[@id='swap_curve']");
    private final By btnNext =By.xpath("//button[text()='Next']");
    private final By direction = By.xpath("//select[@id='pricing_object_pricing_data_direction']/following-sibling::div/div[1]");
    private final By notionalCcyDropdown=By.xpath("//select[@id='pricing_object_pricing_data_notional_currency_id']/following-sibling::div");
    private final By numLegsTextbox=By.xpath("//input[@id='strip_number_of_trades']");
    private final By setSchedule=By.xpath("//select[@id='pricing_object_pricing_data_recurrence_recurrence_rule']");
    private final By schedule= By.xpath("//select[@id='rs_frequency']");
    private final By btnOK=By.xpath("//input[@value='OK']");
    private final By generateLegs = By.xpath("//a[text()=' Generate Legs']");
    private final By btnPrice=By.xpath("//i[@class='far fa-play mr-1']");
    private final By weightedAvgForward=By.xpath("(//td[@class='blue lighten-5'])/following-sibling::td");
    private final By btnSavePrice=By.xpath("//a[@id='save_price_button']");
    public NewFXForwardStripPage clickUnderlying(){
        clickk(underlying, WaitStrategy.CLICKABLE,"Underlying");
        return this;
    }
    public NewFXForwardStripPage selectUnderlying(String text){
        String underlyingValues = "//div[text()='%replace%']";
        String newxpath= XpathUtils.getXpath(underlyingValues,text);
        clickk(By.xpath(newxpath),WaitStrategy.CLICKABLE,text);
        return this;
    }
    public NewFXForwardStripPage clickMarketData(){
        jsClick(btnMarketData,WaitStrategy.CLICKABLE,"Market Data");
        return this;
    }
    public NewFXForwardStripPage marketDataIsDisplayed(){
        isDisplayed(marketDataTable,WaitStrategy.VISIBLE,"MarketDataTable");
        return this;
    }
    public NewFXForwardStripPage clickNextButton(){
        clickk(btnNext,WaitStrategy.CLICKABLE,"NextButton");
        return this;
    }

    public NewFXForwardStripPage clickDirection(){
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        jsClick(direction,WaitStrategy.CLICKABLE,"direction dropdown");
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        return this;
    }
    public NewFXForwardStripPage selectDirectionValue(String value){
        String directionValue = "//div[@data-value='%replace%']";
        String newXpath=XpathUtils.getXpath(directionValue,value);
        clickk(By.xpath(newXpath),WaitStrategy.CLICKABLE,value);
        return this;
    }
    public NewFXForwardStripPage clickNotionalCcy(){
        clickk(notionalCcyDropdown,WaitStrategy.CLICKABLE,"Notional Ccy Dropdown");
        return this;
    }
    public NewFXForwardStripPage selectNotionalCcyValue(String value){
        String notionalCcyValue = "//div[text()='%replace%']";
        String newxpath= XpathUtils.getXpath(notionalCcyValue,value);
        jsClick(By.xpath(newxpath),WaitStrategy.CLICKABLE,value);
        return this;
    }
    public NewFXForwardStripPage enterNumLegs(String value){
        sendText(numLegsTextbox,value,WaitStrategy.PRESENCE,"Number of Legs ");
        return this;
    }

    public NewFXForwardStripPage selectSetSchedule(){
        selectDropdown(DriverManager.getDriver().findElement(setSchedule),"Set schedule...");
        return this;
    }
    public NewFXForwardStripPage selectSchedule(String shd){
        selectDropdown(DriverManager.getDriver().findElement(schedule),shd);
        return this;
    }
    public NewFXForwardStripPage clickOkSchedule(){
        if(isSelected(By.xpath("//select[@id='rs_frequency']/child::option[1]"), WaitStrategy.VISIBLE, "daily shedule") ||
                isSelected(By.xpath("//select[@id='rs_frequency']/child::option[4]"), WaitStrategy.VISIBLE, "yearly schedule")){
            clickk(btnOK,WaitStrategy.CLICKABLE," Ok Button");
        }else if(isSelected(By.xpath("//select[@id='rs_frequency']/child::option[2]"), WaitStrategy.VISIBLE, "Weekly shedule")){

            String random=String.valueOf((int)(Math.random()*(7-0.9+1)+0.9));
            String weeklyRandom = "//div[@class='day_holder']/child::a[%replace%]";
            String newXpath= XpathUtils.getXpath(weeklyRandom,random);
            clickk(By.xpath(newXpath),WaitStrategy.CLICKABLE," Random day in week");
            Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
            clickk(btnOK,WaitStrategy.CLICKABLE," Ok Button");

        }else if(isSelected(By.xpath("//select[@id='rs_frequency']/child::option[3]"), WaitStrategy.VISIBLE, "Monthly schedule")){
            String random=String.valueOf((int)(Math.random()*(31-1+1)+1));
            String monthlyRandom = "//p[@class='rs_calendar_day']/child::a[%replace%]";
            String newXpath= XpathUtils.getXpath(monthlyRandom,random);
            clickk(By.xpath(newXpath),WaitStrategy.CLICKABLE,"Random day in month");
            Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
            clickk(btnOK,WaitStrategy.CLICKABLE," Ok Button");
        }
        return this;
    }
    public NewFXForwardStripPage clickGenerateLegs(){
        clickk(generateLegs,WaitStrategy.CLICKABLE,"Generate legs");
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        return this;
    }
    public NewFXForwardStripPage enterNotional(String value){
        int num=Integer.parseInt(value);
        Actions act= new Actions(DriverManager.getDriver());
        IntStream.rangeClosed(1, num).forEach(i -> {
            String notional = "//*[@id='forward_strip_legs']/div[1]/div/div/div/table/tbody/tr[%replace%]/td[3]";
            String newXpath = XpathUtils.getXpath(notional, String.valueOf(i));
            WebElement ele = DriverManager.getDriver().findElement(By.xpath(newXpath));
            clickk(By.xpath(newXpath), WaitStrategy.CLICKABLE, " Notional cell");
            String random = String.valueOf((long) (Math.random() * (1000000000 - 100000 + 1) + 100000));
            act.moveToElement(ele).doubleClick().sendKeys(random).build().perform();
        });
        return this;
    }
    public NewFXForwardStripPage clickPrice(){
        clickk(btnPrice,WaitStrategy.CLICKABLE,"price button");
        return this;
    }
    public String getWeightedAvgForward(){
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollBy(0,-150)", "");
        return getText(weightedAvgForward,WaitStrategy.PRESENCE,"weightedAvgForward");
    }
    public StructureDetailsPage clickSavePrice(){
        clickk(btnSavePrice,WaitStrategy.CLICKABLE,"Save Price Bytton");
        return new StructureDetailsPage();
    }
    public String getNotional(int value) {
            String notional = "//*[@id='forward_strip_legs']/div[1]/div/div/div/table/tbody/tr[%replace%]/td[3]";
            String newXpath = XpathUtils.getXpath(notional, String.valueOf(value));
           return getText(By.xpath(newXpath), WaitStrategy.VISIBLE, "Notional");
    }
    public String getForwardRate(int value){
        String notional = "//*[@id='forward_strip_legs']/div[1]/div/div/div/table/tbody/tr[%replace%]/td[4]";
        String newXpath = XpathUtils.getXpath(notional, String.valueOf(value));
        return getText(By.xpath(newXpath), WaitStrategy.VISIBLE, "Forward Rate");
    }
    public String getSpotDate(){
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        return js.executeScript("return document.getElementById('pricing_object_pricing_data_spot_date').value").toString();
    }
    public String getSoptRate(){
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        return js.executeScript("return document.getElementById('pricing_object_pricing_data_spot_rate').value").toString();
    }
    public void acceptAlert(){
        DriverManager.getDriver().switchTo().alert().accept();
    }
    public String getForwardPointsBid(int index){
        String Rate = "(//tbody)[1]/tr[%replace%]/td[2]";
        String newXpath=XpathUtils.getXpath(Rate,Integer.toString(index));
        return getText(By.xpath(newXpath),WaitStrategy.VISIBLE,"BidForwardRate");

    }
    public String getForwardPointsMid(int index){
        String Rate = "(//tbody)[1]/tr[%replace%]/td[3]";
        String newXpath=XpathUtils.getXpath(Rate,Integer.toString(index));
        return getText(By.xpath(newXpath),WaitStrategy.VISIBLE,"MidForwardRate");

    }
    public String getForwardPointsAsk(int index){
        String Rate = "(//tbody)[1]/tr[%replace%]/td[4]";
        String newXpath=XpathUtils.getXpath(Rate,Integer.toString(index));
        return getText(By.xpath(newXpath),WaitStrategy.VISIBLE,"AskForwardRate");

    }
    public String getMarketDate(){
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        return js.executeScript("return document.getElementById('pricing_object_pricing_data_market_date').value").toString();
    }
}
