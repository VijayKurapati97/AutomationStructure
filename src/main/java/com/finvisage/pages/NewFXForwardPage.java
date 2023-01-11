package com.finvisage.pages;

import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.finvisage.utils.XpathUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class NewFXForwardPage extends BasePage{

    private final By underlying =By.xpath("//div[@class='selectize-input items not-full has-options']");
    private final By btnMarketData=By.xpath("//li[@id='market-data']");
    private final By MarketDataTable=By.xpath("//table[@id='swap_curve']");
    private final By btnNext =By.xpath("//button[text()='Next']");
    private final By tenure=By.xpath("//input[@id='pricing_object_pricing_data_tenure_val']");
    private final By forwardRate=By.xpath("//input[@id='pricing_object_pricing_data_forward_rate']");
    private final By direction = By.xpath("//select[@id='pricing_object_pricing_data_direction']/following-sibling::div/div[1]");
    private final By notionalCcyDropdown=By.xpath("//select[@id='pricing_object_pricing_data_notional_currency_id']/following-sibling::div/div[1]");
    private final By notionalTextbox=By.id("pricing_object_pricing_data_notional");
    private final By btnPrice=By.xpath("//a[@data-internal-prefix='[pricing_data]']");
    private final By payoffGraph=By.xpath("//div[@id='pay-off-graph-block']");
    private final By btnSavePrice=By.xpath("//a[@id='save_price_button']");
    private final By MaturityDate=By.id("pricing_object_pricing_data_maturity_date");
    private final By MarketDate=By.xpath("(//div[@class='string optional disabled pricing_object_pricing_data_market_date'])[1]");

    public NewFXForwardPage clickUnderlying(){
        clickk(underlying, WaitStrategy.CLICKABLE,"Underlying dropdown");
        return this;
    }

    public NewFXForwardPage selectDirectionValue(String value){
        String directionValue = "//div[text()='%replace%']";
        String newXpath=XpathUtils.getXpath(directionValue,value);
        clickk(By.xpath(newXpath),WaitStrategy.CLICKABLE,value);
        return this;
    }
    public NewFXForwardPage selectUnderlying(String text){
        String underlyingValues = "//div[text()='%replace%']";
        String newxpath= XpathUtils.getXpath(underlyingValues,text);
        clickk(By.xpath(newxpath),WaitStrategy.CLICKABLE,text);
        return this;
    }
    public NewFXForwardPage clickMarketData(){
        jsClick(btnMarketData,WaitStrategy.CLICKABLE,"Market Data");
        return this;
    }
    public NewFXForwardPage marketDataIsDisplayed(){
        isDisplayed(MarketDataTable,WaitStrategy.VISIBLE,"MarketDataTable");
        return this;
    }
    public NewFXForwardPage clickNextButton(){
        jsClick(btnNext,WaitStrategy.CLICKABLE,"NextButton");
        return this;
    }
    public NewFXForwardPage enterTenure(String Tenure){
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        sendText(tenure,Tenure,WaitStrategy.PRESENCE,"Tenure box");
        return this;
    }
    public NewFXForwardPage forwardRateDisplayed(){
        isDisplayed(forwardRate,WaitStrategy.VISIBLE,"ForwardRate");
        return this;
    }
    public NewFXForwardPage clickDirection(){
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        clickk(direction,WaitStrategy.CLICKABLE,"direction dropdown");
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        return this;
    }
    public NewFXForwardPage clickNotionalCcy(){
        clickk(notionalCcyDropdown,WaitStrategy.CLICKABLE,"Notional Ccy Dropdown");
        return this;
    }
    public NewFXForwardPage selectNotionalCcyValue(String value){
        String notionalCcyValue = "//div[text()='%replace%']";
        String newxpath= XpathUtils.getXpath(notionalCcyValue,value);
        clickk(By.xpath(newxpath),WaitStrategy.CLICKABLE,value);
        return this;
    }
    public NewFXForwardPage enterNotional(String notional){
        sendText(notionalTextbox,notional,WaitStrategy.VISIBLE,"notional textbox ");
        DriverManager.getDriver().findElement(notionalTextbox).sendKeys(Keys.DELETE);
        return this;
    }
    public NewFXForwardPage clickPrice(){
        clickk(btnPrice,WaitStrategy.CLICKABLE,"Price button");
        return this;
    }

    public String[] getPriceSection(){
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollBy(0,-150)", "");
        final String[] value = new String[5];
        IntStream.rangeClosed(1, 2).forEach(i -> {
            String priceSection = "//table[@id='pricing_output_table']/tbody/tr[%replace%]/td[2]";
            String newXpath = XpathUtils.getXpath(priceSection, String.valueOf(i));
            value[i-1] = getText(By.xpath(newXpath), WaitStrategy.VISIBLE, "Pricer");
        });
        return value;
    }

    public boolean getPayoffGraph(){

        return isDisplayed(payoffGraph,WaitStrategy.PRESENCE,"Pay-off Graph");
    }
    public StructureDetailsPage clickSavePrice(){
        clickk(btnSavePrice,WaitStrategy.CLICKABLE,"Save Price Bytton");
        return new StructureDetailsPage();
    }
    public String getForwardRate(){
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        return js.executeScript("return document.getElementById('pricing_object_pricing_data_forward_rate').value").toString();
    }
    public NewFXForwardPage enterMaturityDate(String Date){
        sendText(MaturityDate,Date,WaitStrategy.PRESENCE,"Maturity Date");
        DriverManager.getDriver().findElement(MaturityDate).sendKeys(Keys.ENTER);
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        return this;
    }
    public void clearmaturityDate(){
        DriverManager.getDriver().findElement(MaturityDate).clear();
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
    public String getSpotDate(){
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        return js.executeScript("return document.getElementById('pricing_object_pricing_data_spot_date').value").toString();
    }

}
