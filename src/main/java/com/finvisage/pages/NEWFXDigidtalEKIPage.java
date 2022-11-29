package com.finvisage.pages;

import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.finvisage.utils.XpathUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class NEWFXDigidtalEKIPage extends BasePage{
    private static final By underlying=By.xpath("//select[@id='pricing_object_pricing_data_asset_id']/following-sibling ::div");
    private final By btnMarketData=By.xpath("//li[@id='market-data']");
    private final By marketDataTable=By.xpath("//table[@id='swap_curve']");
    private final By btnNext =By.xpath("//button[text()='Next']");
    private final By tenure=By.xpath("//input[@id='pricing_object_pricing_data_tenure_val']");
    private final By direction=By.xpath("//select[@id='option-european']/following-sibling::div/div[1]");
    private final By strike1=By.xpath("//input[@id='strike1']");
    private final By barrier=By.xpath("//input[@id='pricing_object_pricing_data_knockout']");
    private final By notionalCcyDropdown=By.xpath("//select[@id='pricing_object_pricing_data_notional_currency_id']/following-sibling::div/div[1]");
    private final By notionalTextbox=By.xpath("//input[@id='pricing_object_pricing_data_notional']");

    private final By btnPrice=By.xpath("//a[@data-prefix='pricing_object']");
    private final By payoffGraph=By.xpath("//div[@id='pay-off-graph-block']");
    private final By linkDefferWithPremium=By.xpath("//i[@class='fa fa-fw fa-link']");
    private final By defferedPremium=By.xpath("//div[@id='deferred_amortization_form_container']");
    private final By numLegs=By.xpath("//input[@id='pricing_object_pricing_data_deferred_amortization_number_of_trades']");
    private final By discountingRate=By.xpath("//input[@id='pricing_object_pricing_data_deferred_amortization_discounting_rate']");
    private final By setSchedule=By.xpath("//select[@id='pricing_object_pricing_data_deferred_amortization_recurrence_recurrence_rule']");
    private final By schedule= By.xpath("//select[@id='rs_frequency']");
    private final By btnOK=By.xpath("//input[@value='OK']");
    private final By generateLegs = By.xpath("//a[text()=' Generate Legs']");
    private final By btnCalculate=By.xpath("//a[@id='calculate_deferment_rate']");
    private final By deffermentCashFlowTable=By.xpath("//div[@id='cashflow_table']");
    private final By deffermentRate=By.xpath("//h5[@id='deferment_rate_output']");
    private final By btnSavePrice=By.xpath("//a[@id='save_price_button']");
    private final By paymentSchedule=By.xpath("//div[@id='ht_27ca3c1200ee0eb6']");


    public NEWFXDigidtalEKIPage clickUnderlying(){
        clickk(underlying, WaitStrategy.CLICKABLE,"Underlying");
        return this;
    }
    public NEWFXDigidtalEKIPage selectUnderlying(String text){
        String underlyingValues = "//div[text()='%replace%']";
        String newxpath= XpathUtils.getXpath(underlyingValues,text);
        clickk(By.xpath(newxpath),WaitStrategy.CLICKABLE,text);
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        return this;
    }
    public NEWFXDigidtalEKIPage clickMarketData(){
        clickk(btnMarketData,WaitStrategy.CLICKABLE,"Market Data");
        return this;
    }
    public NEWFXDigidtalEKIPage marketDataIsDisplayed(){
        isDisplayed(marketDataTable,WaitStrategy.VISIBLE,"MarketDataTable");
        return this;
    }
    public NEWFXDigidtalEKIPage clickNextButton(){
        clickk(btnNext,WaitStrategy.CLICKABLE,"NextButton");
        return this;
    }
    public NEWFXDigidtalEKIPage enterTenure(String Tenure){
        sendText(tenure,Tenure,WaitStrategy.PRESENCE,"Tenure box");
        return this;
    }
    public NEWFXDigidtalEKIPage clickDirection(){
        jsClick(direction,WaitStrategy.CLICKABLE,"direction dropdown");
        if(!isDisplayed(By.xpath("(//div[@class='selectize-dropdown-content'])[2]"), WaitStrategy.VISIBLE, "dropdown values")){
            clickk(direction,WaitStrategy.CLICKABLE,"direction dropdown");
        }
        return this;
    }
    public NEWFXDigidtalEKIPage selectDirectionValue(String value){
        String directionValue = "//div[text()='%replace%']";
        String newXpath=XpathUtils.getXpath(directionValue,value);
        jsClick(By.xpath(newXpath),WaitStrategy.CLICKABLE,value);
        return this;
    }
    public NEWFXDigidtalEKIPage enterStrike(String strike){
        sendText(strike1,strike,WaitStrategy.PRESENCE,"Strike");
        DriverManager.getDriver().findElement(strike1).sendKeys(Keys.ARROW_LEFT);
        DriverManager.getDriver().findElement(strike1).sendKeys(Keys.ARROW_LEFT);
        DriverManager.getDriver().findElement(strike1).sendKeys(Keys.BACK_SPACE);
        return this;
    }
    public NEWFXDigidtalEKIPage enterBarrier(String barrierr){
        sendText(barrier,barrierr,WaitStrategy.PRESENCE,"Barrier");
        DriverManager.getDriver().findElement(barrier).sendKeys(Keys.ARROW_LEFT);
        DriverManager.getDriver().findElement(barrier).sendKeys(Keys.ARROW_LEFT);
        DriverManager.getDriver().findElement(barrier).sendKeys(Keys.BACK_SPACE);
        return this;
    }

    public NEWFXDigidtalEKIPage clickNotionalCcy(){
        clickk(notionalCcyDropdown,WaitStrategy.CLICKABLE,"Notional Ccy Dropdown");
        return this;
    }
    public NEWFXDigidtalEKIPage selectNotionalCcyValue(String value){
        String notionalCcyValue = "//div[text()='%replace%']";
        String newxpath= XpathUtils.getXpath(notionalCcyValue,value);
        clickk(By.xpath(newxpath),WaitStrategy.CLICKABLE,value);
        return this;
    }
    public NEWFXDigidtalEKIPage enterNotional(String notional){
        sendText(notionalTextbox,notional,WaitStrategy.PRESENCE,"notional textbox ");
        return this;
    }
    public NEWFXDigidtalEKIPage clickPricebutton(){
        clickk(btnPrice,WaitStrategy.CLICKABLE,"Price button");
        return this;
    }
    public NEWFXDigidtalEKIPage priceSectionDisplayed(){

        if(isDisplayed(By.xpath("//table[@id='pricing_output_table']/tbody"),WaitStrategy.VISIBLE,"Price table")) {
            IntStream.rangeClosed(1, 5).forEach(i -> {
                String priceSection = "//table[@id='pricing_output_table']/tbody/tr[%replace%]/td[2]";
                String newXpath = XpathUtils.getXpath(priceSection, String.valueOf(i));
                getText(By.xpath(newXpath), WaitStrategy.VISIBLE, "Pricer");
            });
        }
        return this;
    }
    public NEWFXDigidtalEKIPage graphIsDisplayed(){
        isDisplayed(payoffGraph,WaitStrategy.VISIBLE,"payoff graph");
        return this;
    }
    public NEWFXDigidtalEKIPage clickDefferWithpPremium()  {
        isDisplayed(linkDefferWithPremium,WaitStrategy.VISIBLE,"Deffer with Premium link");
        JavascriptExecutor js = (JavascriptExecutor)DriverManager.getDriver();
        js.executeScript("window.scrollBy(0,-450)", "");
        jsClick(linkDefferWithPremium,WaitStrategy.CLICKABLE,"Deffer with Premium link");
        return this;
    }
    public NEWFXDigidtalEKIPage clickDefferedPremuim(){
        if( isDisplayed(defferedPremium,WaitStrategy.VISIBLE,"Deffered Premium Section")){
            clickk(By.xpath("//h5[text()='Deferred premium']"),WaitStrategy.CLICKABLE,"Deffered Premium Section");
        }
        return this;
    }
    public NEWFXDigidtalEKIPage enterNumberOfLegs(String number){
        sendText(numLegs,number,WaitStrategy.PRESENCE,"Number of legs");
        return this;
    }
    public NEWFXDigidtalEKIPage enterDiscountingRate(String Rate){
        sendText(discountingRate,Rate,WaitStrategy.PRESENCE,"Discounting Rate");
        DriverManager.getDriver().findElement(discountingRate).sendKeys(Keys.ARROW_LEFT);
        DriverManager.getDriver().findElement(discountingRate).sendKeys(Keys.ARROW_LEFT);
        DriverManager.getDriver().findElement(discountingRate).sendKeys(Keys.BACK_SPACE);
        return this;
    }
    public NEWFXDigidtalEKIPage selectSetSchedule(){
        selectDropdown(DriverManager.getDriver().findElement(setSchedule),"Set schedule...",WaitStrategy.VISIBLE);
        return this;
    }
    public NEWFXDigidtalEKIPage selectSchedule(String shd){
        selectDropdown(DriverManager.getDriver().findElement(schedule),shd,WaitStrategy.VISIBLE);
        return this;
    }
    public NEWFXDigidtalEKIPage clickOkSchedule(){
        if(isSelected(By.xpath("//select[@id='rs_frequency']/child::option[1]"), WaitStrategy.VISIBLE, "daily shedule") ||
                isSelected(By.xpath("//select[@id='rs_frequency']/child::option[4]"), WaitStrategy.VISIBLE, "yearly schedule")){
            jsClick(btnOK,WaitStrategy.CLICKABLE,"OK Button");
            // clickk(btnOK,WaitStrategy.CLICKABLE," Ok Button");
            Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        }else if(isSelected(By.xpath("//select[@id='rs_frequency']/child::option[2]"), WaitStrategy.VISIBLE, "Weekly shedule")){

            String random=String.valueOf((int)(Math.random()*(7-0.9+1)+0.9));
            String weeklyRandom = "//div[@class='day_holder']/child::a[%replace%]";
            String newXpath= XpathUtils.getXpath(weeklyRandom,random);
            clickk(By.xpath(newXpath),WaitStrategy.CLICKABLE," Random day in week");
            Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
            jsClick(btnOK,WaitStrategy.CLICKABLE,"OK Button");

        }else if(isSelected(By.xpath("//select[@id='rs_frequency']/child::option[3]"), WaitStrategy.VISIBLE, "Monthly schedule")){
            String random=String.valueOf((int)(Math.random()*(31-1+1)+1));
            String monthlyRandom = "//p[@class='rs_calendar_day']/child::a[%replace%]";
            String newXpath= XpathUtils.getXpath(monthlyRandom,random);
            clickk(By.xpath(newXpath),WaitStrategy.CLICKABLE,"Random day in month");
            Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
            jsClick(btnOK,WaitStrategy.CLICKABLE,"Ok Button");
        }
        return this;
    }
    public NEWFXDigidtalEKIPage clickGenerateLegs(){
        jsClick(generateLegs,WaitStrategy.CLICKABLE,"Generate legs");
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        return this;
    }
    public NEWFXDigidtalEKIPage paymentScheduleIsDisplayed(){
        isDisplayed(paymentSchedule,WaitStrategy.VISIBLE,"Payment Schedule Table");
        return this;
    }
    public NEWFXDigidtalEKIPage clickCalculate(){
        JavascriptExecutor js = (JavascriptExecutor)DriverManager.getDriver();
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        jsClick(btnCalculate,WaitStrategy.CLICKABLE,"Calculate button");
        // clickk(btnCalculate,WaitStrategy.CLICKABLE,"Calculate button");
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        return this;
    }
    public NEWFXDigidtalEKIPage getDeffermentRate(){
        if(isDisplayed(deffermentCashFlowTable,WaitStrategy.VISIBLE,"Defferment Cash Flow Table")&&
                isDisplayed(deffermentRate,WaitStrategy.VISIBLE,"DeffermentRate")){
            getText(deffermentRate,WaitStrategy.PRESENCE,"Defferment Rate");
        }
        return this;
    }

    public StructureDetailsPage clickSavePrice(){
        jsClick(btnSavePrice,WaitStrategy.CLICKABLE,"Save Price");
        return new StructureDetailsPage();
    }
}
