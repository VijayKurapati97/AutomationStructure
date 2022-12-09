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

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class NewFXTarfPointsPage extends BasePage{
    private static final By underlying=By.xpath("//select[@id='pricing_object_pricing_data_asset_id']/following-sibling ::div");
    private final By btnMarketData=By.xpath("//li[@id='market-data']");
    private final By marketDataTable=By.xpath("//table[@id='swap_curve']");
    private final By btnNext =By.xpath("//button[text()='Next']");
    private final By direction=By.xpath("//select[@id='pricing_object_pricing_data_direction']/following-sibling::div/div[1]");
    private final By notionalCcyDropdown=By.xpath("//select[@id='pricing_object_pricing_data_notional_currency_id']/following-sibling::div/div[1]");
    private final By knockout=By.xpath("//input[@id='pricing_object_pricing_data_knockout']");
    private final By tarfLegs=By.xpath("//input[@id='tarf_legs_count']");
    private final By setTarfSchedule=By.xpath("//select[@id='pricing_object_pricing_data_recurrence_recurrence_rule']");
    private final By tarfschedule= By.xpath("//select[@id='rs_frequency']");
    private final By btnOK1=By.xpath("//input[@value='OK']");
    private final By generateLegs1 = By.xpath("//a[text()=' Generate Legs']");
    private final By btnPrice=By.xpath("//i[@class='far fa-play mr-1']");
    private final By payoffGraph=By.xpath("//canvas[@id='pay-off-chart']");
    private final By linkDefferWithPremium=By.xpath("//a[@id='deferred_amortization_link']");
    private final By defferedPremium=By.xpath("//div[@id='deferred_amortization_form_container']");
    private final By numLegs=By.xpath("//input[@id='pricing_object_pricing_data_deferred_amortization_number_of_trades']");
    private final By discountingRate=By.xpath("//input[@id='pricing_object_pricing_data_deferred_amortization_discounting_rate']");
    private final By setSchedule=By.xpath("//select[@id='pricing_object_pricing_data_deferred_amortization_recurrence_recurrence_rule']");
    private final By schedule= By.xpath("//select[@id='rs_frequency']");
    private final By btnOK2=By.xpath("(//input[@value='OK'])[1]");
    private final By generateLegs = By.xpath("//a[@class='material-tooltip-email btn btn-sm btn-outline-grey waves-effect rounded-0 mt-3 ml-0 generate-deferred-premium-legs']");
    private final By btnCalculate=By.xpath("//a[@id='calculate_deferment_rate']");
    private final By deffermentCashFlowTable=By.xpath("//div[@id='cashflow_table']");
    private final By deffermentRate=By.xpath("//h5[@id='deferment_rate_output']");
    private final By btnSavePrice=By.xpath("//a[@id='save_price_button']");


    public NewFXTarfPointsPage clickUnderlying(){
        clickk(underlying, WaitStrategy.CLICKABLE,"Underlying");
        return this;
    }
    public NewFXTarfPointsPage selectUnderlying(String text){
        String underlyingValues = "//div[text()='%replace%']";
        String newxpath= XpathUtils.getXpath(underlyingValues,text);
        clickk(By.xpath(newxpath),WaitStrategy.CLICKABLE,text);
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        return this;
    }
    public NewFXTarfPointsPage clickMarketData(){
        clickk(btnMarketData,WaitStrategy.CLICKABLE,"Market Data");
        return this;
    }
    public NewFXTarfPointsPage marketDataIsDisplayed(){
        isDisplayed(marketDataTable,WaitStrategy.VISIBLE,"MarketDataTable");
        return this;
    }
    public NewFXTarfPointsPage clickNextButton(){
        clickk(btnNext,WaitStrategy.CLICKABLE,"NextButton");
        return this;
    }
    public NewFXTarfPointsPage clickDirection(){
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        jsClick(direction,WaitStrategy.CLICKABLE,"direction dropdown");
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        return this;
    }
    public NewFXTarfPointsPage selectDirectionValue(String value){
        String directionValue = "//div[text()='%replace%']";
        String newXpath=XpathUtils.getXpath(directionValue,value);
        clickk(By.xpath(newXpath),WaitStrategy.CLICKABLE,value);
        return this;
    }
    public NewFXTarfPointsPage clickNotionalCcy(){
        clickk(notionalCcyDropdown,WaitStrategy.CLICKABLE,"Notional Ccy Dropdown");
        return this;
    }
    public NewFXTarfPointsPage selectNotionalCcyValue(String value){
        String notionalCcyValue ="//div[text()='%replace%']";
        String newxpath= XpathUtils.getXpath(notionalCcyValue,value);
        clickk(By.xpath(newxpath),WaitStrategy.CLICKABLE,value);
        return this;
    }

    public NewFXTarfPointsPage enterKnockout(String Knockout){
        sendText(knockout,Knockout,WaitStrategy.PRESENCE,"Knockout value");
        DriverManager.getDriver().findElement(knockout).sendKeys(Keys.ARROW_RIGHT);
        DriverManager.getDriver().findElement(knockout).sendKeys(Keys.BACK_SPACE);
        return this;
    }
    public NewFXTarfPointsPage enterNumberOfTarfLegs(String number){
        sendText(tarfLegs,number,WaitStrategy.PRESENCE,"Number of legs");
        return this;
    }
    public NewFXTarfPointsPage selectTarfSetSchedule(){
        selectDropdown(DriverManager.getDriver().findElement(setTarfSchedule),"Set schedule...",WaitStrategy.VISIBLE);
        return this;
    }
    public NewFXTarfPointsPage selectTarfSchedule(String shd){
        selectDropdown(DriverManager.getDriver().findElement(tarfschedule),shd,WaitStrategy.VISIBLE);
        return this;
    }
    public NewFXTarfPointsPage clickOkTarfSchedule(){
        if(isSelected(By.xpath("//select[@id='rs_frequency']/child::option[1]"), WaitStrategy.VISIBLE, "daily shedule") ||
                isSelected(By.xpath("//select[@id='rs_frequency']/child::option[4]"), WaitStrategy.VISIBLE, "yearly schedule")){
            jsClick(btnOK1,WaitStrategy.CLICKABLE,"OK Button");
        }else if(isSelected(By.xpath("//select[@id='rs_frequency']/child::option[2]"), WaitStrategy.VISIBLE, "Weekly shedule")){

            String random=String.valueOf((int)(Math.random()*(6-0.9+1)+0.9));
            String weeklyRandom = "//div[@class='day_holder']/child::a[%replace%]";
            String newXpath= XpathUtils.getXpath(weeklyRandom,random);
            clickk(By.xpath(newXpath),WaitStrategy.CLICKABLE," Random day in week");
            Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
            jsClick(btnOK1,WaitStrategy.CLICKABLE,"OK Button");
        }else if(isSelected(By.xpath("//select[@id='rs_frequency']/child::option[3]"), WaitStrategy.VISIBLE, "Monthly schedule")){
            String random=String.valueOf((int)(Math.random()*(31-1+1)+1));
            String monthlyRandom = "//p[@class='rs_calendar_day']/child::a[%replace%]";
            String newXpath= XpathUtils.getXpath(monthlyRandom,random);
            clickk(By.xpath(newXpath),WaitStrategy.CLICKABLE,"Random day in month");
            Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
            jsClick(btnOK1,WaitStrategy.CLICKABLE,"Ok Button");
        }
        return this;
    }
    public NewFXTarfPointsPage clickTarfGenerateLegs(){
        jsClick(generateLegs1,WaitStrategy.CLICKABLE,"Generate legs");
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        return this;
    }
    public NewFXTarfPointsPage enterNotional(String value){
        int num=Integer.parseInt(value);
        Actions act= new Actions(DriverManager.getDriver());
        for(int i=1;i<=num;i++){
            String notional="//body[1]/main[1]/section[1]/div[3]/div[1]/section[1]/form[1]/div[2]/div[1]" +
                    "/section[2]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[2]/div[2]/div[1]/div[1]/div[1]/div[2]" +
                    "/div[7]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[%replace%]/td[3]";
            String newXpath = XpathUtils.getXpath(notional, String.valueOf(i));
            WebElement ele = DriverManager.getDriver().findElement(By.xpath(newXpath));
            clickk(By.xpath(newXpath), WaitStrategy.CLICKABLE, " Notional cell");
            String random = String.valueOf((long) (Math.random() * (1000000000 - 100000 + 1) + 100000));
            act.moveToElement(ele).doubleClick().sendKeys(random).build().perform();
        }
        return this;
    }
    public NewFXTarfPointsPage enterStrikes(String value){
        int num=Integer.parseInt(value);
        Actions act= new Actions(DriverManager.getDriver());
        for(int i=1;i<=num;i++){
            String notional="//body[1]/main[1]/section[1]/div[3]/div[1]/section[1]/form[1]/div[2]/div[1]" +
                    "/section[2]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[2]/div[2]/div[1]/div[1]/div[1]/div[2]" +
                    "/div[7]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/table[1]/tbody[1]/tr[%replace%]/td[4]";
            String newXpath = XpathUtils.getXpath(notional, String.valueOf(i));
            WebElement ele = DriverManager.getDriver().findElement(By.xpath(newXpath));
            clickk(By.xpath(newXpath), WaitStrategy.CLICKABLE, " Strike cell");
            String random = String.format("%2f", (Math.random() * (88 - 82 + 1) + 82));

            act.moveToElement(ele).doubleClick().sendKeys(random).build().perform();
        }
        return this;
    }
    public NewFXTarfPointsPage clickPriceButton(){
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        clickk(btnPrice,WaitStrategy.CLICKABLE,"Price button");
        return this;
    }
    public String[] priceSectionDisplayed(){
        final String[] value = new String[2];
        if(isDisplayed(By.xpath("//table[@id='pricing_output_table']/tbody"),WaitStrategy.VISIBLE,"Price table")) {
            IntStream.rangeClosed(1, 2).forEach(i -> {
                String priceSection = "//table[@id='pricing_output_table']/tbody/tr[%replace%]/td[2]";
                String newXpath = XpathUtils.getXpath(priceSection, String.valueOf(i));
                value[i-1] =getText(By.xpath(newXpath), WaitStrategy.VISIBLE, "Pricer");
            });
        }
        return value;
    }

    public NewFXTarfPointsPage graphIsDisplayed(){
        isDisplayed(payoffGraph,WaitStrategy.VISIBLE,"payoff graph");
        return this;
    }
    public NewFXTarfPointsPage clickDefferWithpPremium()  {
        JavascriptExecutor js = (JavascriptExecutor)DriverManager.getDriver();
        js.executeScript("window.scrollBy(0,-350)", "");
        jsClick(linkDefferWithPremium,WaitStrategy.CLICKABLE,"Deffer with Premium link");
        return this;
    }
    public NewFXTarfPointsPage clickDefferedPremuim(){
        if( isDisplayed(defferedPremium,WaitStrategy.VISIBLE,"Deffered Premium Section")){
            clickk(By.xpath("//h5[text()='Deferred premium']"),WaitStrategy.CLICKABLE,"Deffered Premium Section");
        }
        return this;
    }
    public NewFXTarfPointsPage enterNumberOfLegs(String number){
        sendText(numLegs,number,WaitStrategy.PRESENCE,"Number of legs");
        return this;
    }
    public NewFXTarfPointsPage enterDiscountingRate(String Rate){
        sendText(discountingRate,Rate,WaitStrategy.PRESENCE,"Discounting Rate");
        DriverManager.getDriver().findElement(discountingRate).sendKeys(Keys.ARROW_LEFT);
        DriverManager.getDriver().findElement(discountingRate).sendKeys(Keys.ARROW_LEFT);
        DriverManager.getDriver().findElement(discountingRate).sendKeys(Keys.BACK_SPACE);
        return this;
    }
    public NewFXTarfPointsPage selectSetSchedule(){
        selectDropdown(DriverManager.getDriver().findElement(setSchedule),"Set schedule...",WaitStrategy.VISIBLE);
        return this;
    }
    public NewFXTarfPointsPage selectSchedule(String shd){
        selectDropdown(DriverManager.getDriver().findElement(schedule),shd,WaitStrategy.VISIBLE);
        return this;
    }
    public NewFXTarfPointsPage clickOkSchedule(){
        if(isSelected(By.xpath("//select[@id='rs_frequency']/child::option[1]"), WaitStrategy.VISIBLE, "daily shedule") ||
                isSelected(By.xpath("//select[@id='rs_frequency']/child::option[4]"), WaitStrategy.VISIBLE, "yearly schedule")){
            jsClick(btnOK2,WaitStrategy.CLICKABLE,"OK Button");
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        }else if(isSelected(By.xpath("//select[@id='rs_frequency']/child::option[2]"), WaitStrategy.VISIBLE, "Weekly shedule"))
        {
            String random=String.valueOf((int)(Math.random()*(7-0.9+1)+0.9));
            String weeklyRandom = "//div[@class='day_holder']/child::a[%replace%]";
            String newXpath= XpathUtils.getXpath(weeklyRandom,random);
            clickk(By.xpath(newXpath),WaitStrategy.CLICKABLE," Random day in week");
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
            jsClick(btnOK2,WaitStrategy.CLICKABLE,"OK Button");

        }else if(isSelected(By.xpath("//select[@id='rs_frequency']/child::option[3]"), WaitStrategy.VISIBLE, "Monthly schedule")){
            String random=String.valueOf((int)(Math.random()*(31-1+1)+1));
            String monthlyRandom = "//p[@class='rs_calendar_day']/child::a[%replace%]";
            String newXpath= XpathUtils.getXpath(monthlyRandom,random);
            clickk(By.xpath(newXpath),WaitStrategy.CLICKABLE,"Random day in month");
            Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
            jsClick(btnOK2,WaitStrategy.CLICKABLE,"Ok Button");
        }
        return this;
    }
    public NewFXTarfPointsPage clickGenerateLegs(){
        jsClick(generateLegs,WaitStrategy.CLICKABLE,"Generate legs");
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        return this;
    }
    public NewFXTarfPointsPage clickCalculate(){
        JavascriptExecutor js = (JavascriptExecutor)DriverManager.getDriver();
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        jsClick(btnCalculate,WaitStrategy.CLICKABLE,"Calculate button");
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        return this;
    }
    public NewFXTarfPointsPage getDeffermentRate(){
        if(isDisplayed(deffermentCashFlowTable,WaitStrategy.VISIBLE,"Defferment Cash Flow Table")&&
                isDisplayed(deffermentRate,WaitStrategy.VISIBLE,"DeffermentRate")){
            getText(deffermentRate,WaitStrategy.PRESENCE,"Defferment Rate");
        }
        return this;
    }

    public StructureDetailsPage clickSavePriceButton(){
        jsClick(btnSavePrice,WaitStrategy.CLICKABLE,"Save Price Button");
        return new StructureDetailsPage();
    }
}