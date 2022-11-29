package com.finvisage.pages;

import com.finvisage.enums.WaitStrategy;
import com.finvisage.utils.XpathUtils;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;

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
    private final By equvalentNotional = By.xpath("//table[@id='pricing_output_table']/tbody/tr[1]/td[2]");
    private  final By forwardRateValue = By.xpath("//table[@id='pricing_output_table']/tbody/tr[2]/td[2]");
    private final By payoffGraph=By.xpath("//div[@id='pay-off-graph-block']");


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
        clickk(btnNext,WaitStrategy.CLICKABLE,"NextButton");
        return this;
    }
    public NewFXForwardPage enterTenure(String Tenure){
        sendText(tenure,Tenure,WaitStrategy.PRESENCE,"Tenure box");
        return this;
    }
    public NewFXForwardPage forwardRateDisplayed(){
        isDisplayed(forwardRate,WaitStrategy.VISIBLE,"ForwardRate");
        return this;
    }
    public NewFXForwardPage clickDirection(){
        clickk(direction,WaitStrategy.CLICKABLE,"direction dropdown");
        if(!isDisplayed(By.xpath("(//div[@class='selectize-dropdown-content'])[2]"), WaitStrategy.VISIBLE, "dropdown values")){
            clickk(direction,WaitStrategy.CLICKABLE,"direction dropdown");
        }
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
        return this;
    }
    public NewFXForwardPage clickPrice(){
        clickk(btnPrice,WaitStrategy.CLICKABLE,"Price button");
        return this;
    }

    public NewFXForwardPage getEquivalentNotional(){
        String value= getText(equvalentNotional,WaitStrategy.PRESENCE,"Equivalent Notional");
        Assertions.assertThat(value).isNotEmpty().isNotNull();
        return this;
    }
    public NewFXForwardPage getForwardRate(){
        String value= getText(forwardRateValue,WaitStrategy.PRESENCE,"Forward Rate");
        Assertions.assertThat(value).isNotNull().isNotEmpty();
        return this;
    }
    public NewFXForwardPage getPayoffGraph(){
        boolean value= isDisplayed(payoffGraph,WaitStrategy.PRESENCE,"Pay-off Graph");
        Assertions.assertThat(value).isTrue();
        return this;
    }
}
