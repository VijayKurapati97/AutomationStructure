package com.finvisage.pages;

import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.finvisage.utils.XpathUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class NewFXEuropeanOptionPage extends BasePage{
    private static final By underlying=By.xpath("//select[@id='pricing_object_pricing_data_asset_id']/following-sibling ::div");
    private final By btnMarketData=By.xpath("//li[@id='market-data']");
    private final By marketDataTable=By.xpath("//table[@id='swap_curve']");
    private final By btnNext =By.xpath("//button[text()='Next']");
    private final By tenure=By.xpath("//input[@id='pricing_object_pricing_data_tenure_val']");
    private final By direction=By.xpath("//select[@id='option-european']/following-sibling::div/div[1]");
    private final By strike1=By.xpath("//input[@id='strike1']");
    private final By notionalCcyDropdown=By.xpath("//select[@id='pricing_object_pricing_data_notional_currency_id']/following-sibling::div/div[1]");
    private final By notionalTextbox=By.xpath("//input[@id='pricing_object_pricing_data_notional']");
    private final By btnPrice=By.xpath("//a[@data-prefix='pricing_object']");
    private final By payoffGraph=By.xpath("//div[@id='pay-off-graph-block']");
    private final By linkDefferWithPremium=By.xpath("//a[@id='deferred_amortization_link']");
    private final By numLegs=By.xpath("//input[@id='pricing_object_pricing_data_deferred_amortization_number_of_trades']");
    private final By discountingRate=By.xpath("//input[@id='pricing_object_pricing_data_deferred_amortization_discounting_rate']");
    private final By setSchedule=By.xpath("//select[@id='pricing_object_pricing_data_deferred_amortization_recurrence_recurrence_rule']");
    private final By schedule= By.xpath("//select[@id='rs_frequency']");
    private final By btnOK=By.xpath("//input[@value='OK']");
    private final By generateLegs = By.xpath("//a[text()=' Generate Legs']");
    private final By btnCalculate=By.xpath("//a[@id='calculate_deferment_rate']");
    private final By deffermentCashFlowTable=By.xpath("//div[@id='cashflow_table']");
    private final By deffermentRate=By.xpath("//h5[@id='deferment_rate_output']");
    private final By checkBox=By.xpath("//label[normalize-space()='Seek strike for a target premium']");
    private final By premiumMid=By.xpath("//input[@id='strike_premium']");
    private final By byChangingDropdown=By.xpath("//select[@id='by_changing']/following-sibling::div/div[1]");
    private final By btnSeekStrike=By.xpath("//a[normalize-space()='Seek strike']");
    private final By btnSavePrice=By.xpath("//a[@id='save_price_button']");
    private final By ForwardRate=By.xpath("//input[@id='pricing_object_pricing_data_forward_rate']");
    private final By MaturityDate=By.id("pricing_object_pricing_data_maturity_date");
    private final By volatility =By.id("#tab_3");
    private final By impliedVol=By.xpath("//input[@id='pricing_object_pricing_data_implied_volatility1']/parent::div");
    private final By deffermentTotal=By.xpath("//td[normalize-space()='Total:-']/following-sibling ::td");

    public NewFXEuropeanOptionPage clickUnderlying(){
        clickk(underlying, WaitStrategy.CLICKABLE,"Underlying");
        return this;
    }
    public NewFXEuropeanOptionPage selectUnderlying(String text){
        String underlyingValues = "//div[text()='%replace%']";
        String newxpath= XpathUtils.getXpath(underlyingValues,text);
        clickk(By.xpath(newxpath),WaitStrategy.CLICKABLE,text);
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        return this;
    }
    public NewFXEuropeanOptionPage clickMarketData(){
        jsClick(btnMarketData,WaitStrategy.CLICKABLE,"Market Data");
        return this;
    }
    public NewFXEuropeanOptionPage marketDataIsDisplayed(){
        isDisplayed(marketDataTable,WaitStrategy.VISIBLE,"MarketDataTable");
        return this;
    }
    public NewFXEuropeanOptionPage clickNextButton(){
        clickk(btnNext,WaitStrategy.CLICKABLE,"NextButton");
        return this;
    }
    public NewFXEuropeanOptionPage enterTenure(String Tenure){
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        sendText(tenure,Tenure,WaitStrategy.PRESENCE,"Tenure box");
        DriverManager.getDriver().findElement(By.xpath("//input[@id='equivalent_notional_ccy']")).click();
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        return this;
    }
    public NewFXEuropeanOptionPage clickDirection(){
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        jsClick(direction,WaitStrategy.CLICKABLE,"direction dropdown");
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        return this;
    }
    public NewFXEuropeanOptionPage selectDirectionValue(String value){
        String directionValue = "//div[text()='%replace%']";
        String newXpath=XpathUtils.getXpath(directionValue,value);
        jsClick(By.xpath(newXpath),WaitStrategy.CLICKABLE,value);
        return this;
    }
    public NewFXEuropeanOptionPage enterStrike(String strike){
        sendText(strike1,strike,WaitStrategy.PRESENCE,"Strike");
        for(int i=1;i<=strike.length()-2;i++){
            DriverManager.getDriver().findElement(strike1).sendKeys(Keys.ARROW_LEFT);
            if(i>=5) break;
        }
        DriverManager.getDriver().findElement(strike1).sendKeys(Keys.BACK_SPACE);
        return this;
    }

    public NewFXEuropeanOptionPage clickNotionalCcy(){
        jsClick(notionalCcyDropdown,WaitStrategy.CLICKABLE,"Notional Ccy Dropdown");
        return this;
    }
    public NewFXEuropeanOptionPage selectNotionalCcyValue(String value){
        String notionalCcyValue = "//div[text()='%replace%']";
        String newxpath= XpathUtils.getXpath(notionalCcyValue,value);
        jsClick(By.xpath(newxpath),WaitStrategy.CLICKABLE,value);
        return this;
    }
    public NewFXEuropeanOptionPage enterNotional(String notional){
        sendText(notionalTextbox,notional,WaitStrategy.PRESENCE,"notional textbox ");
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        DriverManager.getDriver().findElement(notionalTextbox).sendKeys(Keys.DELETE);
        clickk(By.xpath("//input[@id='equivalent_notional_ccy']"),WaitStrategy.CLICKABLE,"notional2");
        return this;
    }
    public NewFXEuropeanOptionPage clickPricebutton(){
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        jsClick(btnPrice,WaitStrategy.CLICKABLE,"Price button");
        return this;
    }
    public String[] priceSectionDisplayed(){
        final String[] value = new String[5];
        if(isDisplayed(By.xpath("//table[@id='pricing_output_table']/tbody"),WaitStrategy.VISIBLE,"Price table")) {
            IntStream.rangeClosed(1, 5).forEach(i -> {
                String priceSection = "//table[@id='pricing_output_table']/tbody/tr[%replace%]/td[2]";
                String newXpath = XpathUtils.getXpath(priceSection, String.valueOf(i));
                value[i-1] = getText(By.xpath(newXpath), WaitStrategy.VISIBLE, "Pricer");
            });
        }
        return value;
    }
    public NewFXEuropeanOptionPage graphIsDisplayed(){
        isDisplayed(payoffGraph,WaitStrategy.VISIBLE,"payoff graph");
        return this;
    }
    public NewFXEuropeanOptionPage clickDefferWithpPremium()  {
        JavascriptExecutor js = (JavascriptExecutor)DriverManager.getDriver();
        js.executeScript("window.scrollBy(0,-350)", "");
        jsClick(linkDefferWithPremium,WaitStrategy.CLICKABLE,"Deffer with Premium link");
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        return this;
    }
    public NewFXEuropeanOptionPage clickDefferedPremuim(){
        clickk(By.xpath("//h5[text()='Deferred premium']"),WaitStrategy.CLICKABLE,"Deffered Premium Section");
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);

        return this;
    }
    public NewFXEuropeanOptionPage enterNumberOfLegs(String number){
        sendText(numLegs,number,WaitStrategy.PRESENCE,"Number of legs");
        return this;
    }
    public NewFXEuropeanOptionPage enterDiscountingRate(String Rate){
        sendText(discountingRate,Rate,WaitStrategy.PRESENCE,"Discounting Rate");
        DriverManager.getDriver().findElement(discountingRate).sendKeys(Keys.ARROW_LEFT);
        DriverManager.getDriver().findElement(discountingRate).sendKeys(Keys.ARROW_LEFT);
        DriverManager.getDriver().findElement(discountingRate).sendKeys(Keys.BACK_SPACE);
        return this;
    }
    public NewFXEuropeanOptionPage selectSetSchedule(){
        selectDropdown(DriverManager.getDriver().findElement(setSchedule),"Set schedule...");
        return this;
    }
    public NewFXEuropeanOptionPage selectSchedule(String shd){
        selectDropdown(DriverManager.getDriver().findElement(schedule),shd);
        return this;
    }
    public NewFXEuropeanOptionPage clickOkSchedule(){
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
            // clickk(btnOK,WaitStrategy.CLICKABLE," Ok Button");


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
    public NewFXEuropeanOptionPage clickGenerateLegs(){
        jsClick(generateLegs,WaitStrategy.CLICKABLE,"Generate legs");
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        return this;
    }

    public NewFXEuropeanOptionPage clickCalculate(){
        JavascriptExecutor js = (JavascriptExecutor)DriverManager.getDriver();
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        jsClick(btnCalculate,WaitStrategy.CLICKABLE,"Calculate button");
        // clickk(btnCalculate,WaitStrategy.CLICKABLE,"Calculate button");
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        return this;
    }
    public NewFXEuropeanOptionPage getDeffermentRate(){
        if(isDisplayed(deffermentCashFlowTable,WaitStrategy.VISIBLE,"Defferment Cash Flow Table")&&
                isDisplayed(deffermentRate,WaitStrategy.VISIBLE,"DeffermentRate")){
            getText(deffermentRate,WaitStrategy.PRESENCE,"Defferment Rate");
        }
        return this;
    }
    public NewFXEuropeanOptionPage clickCheckBox(){
        if(!isSelected(checkBox,WaitStrategy.VISIBLE,"no check box")) {
            JavascriptExecutor js = (JavascriptExecutor)DriverManager.getDriver();
            js.executeScript("window.scrollBy(0,-250)", "");
            jsClick(checkBox, WaitStrategy.CLICKABLE, "Check box");
        }
        return this;
    }
    public NewFXEuropeanOptionPage enterPremium(String premium){
        sendText(premiumMid,premium,WaitStrategy.PRESENCE,"Premiun(mid)");
        DriverManager.getDriver().findElement(premiumMid).sendKeys(Keys.DELETE);
        return this;
    }
    public NewFXEuropeanOptionPage clickByChangingDropdown(){
        clickk(byChangingDropdown,WaitStrategy.CLICKABLE,"by changing dropdown");
        return this;
    }
    public NewFXEuropeanOptionPage byChangingStrike(String value){
        String directionValue = "//div[text()='%replace%']";
        String newXpath=XpathUtils.getXpath(directionValue,value);
        clickk(By.xpath(newXpath),WaitStrategy.CLICKABLE,value);
        return this;
    }
    public NewFXEuropeanOptionPage clickSeekStrike(){
        clickk(btnSeekStrike,WaitStrategy.CLICKABLE,"Seek Strike Button");
        String newXpath="//table[@id='pricing_output_table']/tbody/tr[1]/td[2]";
        isDisplayed(By.xpath(newXpath),WaitStrategy.VISIBLE,"new Strike");
        getText(By.xpath(newXpath),WaitStrategy.VISIBLE,"new Strike");
        return this;
    }
    public StructureDetailsPage clickSavePrice(){
        clickk(btnSavePrice,WaitStrategy.CLICKABLE,"Save Price");
        return new StructureDetailsPage();
    }
    public String getForwardRate(){
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        return js.executeScript("return document.getElementById('pricing_object_pricing_data_forward_rate').value").toString();
    }
    public String getImpliedvolatility(){
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        return js.executeScript("return document.getElementById('pricing_object_pricing_data_implied_volatility1').value").toString();
    }
    public String getNotional2(){
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        return js.executeScript("return document.getElementById('equivalent_notional').value").toString();
    }
    public void clearTenure(){
        DriverManager.getDriver().findElement(tenure).clear();
    }
    public void clearmaturityDate(){
        DriverManager.getDriver().findElement(MaturityDate).clear();
    }
    public NewFXEuropeanOptionPage enterMaturityDate(String Date){
        sendText(MaturityDate,Date,WaitStrategy.PRESENCE,"Maturity Date");
        DriverManager.getDriver().findElement(MaturityDate).sendKeys(Keys.ENTER);
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        return this;
    }
    public void clickVolatility(){
        clickk(volatility,WaitStrategy.CLICKABLE,"volatility in Market data");
    }
    public String getATMVolatilityDate(int index){
        String date = "(//tbody)[12]/tr[%replace%]/td";
        String newXpath=XpathUtils.getXpath(date,Integer.toString(index));
        return getAttribute(By.xpath(newXpath),WaitStrategy.VISIBLE,"key");
    }
    public String getATMvolatilityMid(int index){
        String ATMvolatilityMid = "(//tbody)[12]/tr[%replace%]/td[3]";
        String newXpath=XpathUtils.getXpath(ATMvolatilityMid,Integer.toString(index));
        return getText(By.xpath(newXpath),WaitStrategy.VISIBLE,"MarketData ForwardRate");
    }
    public void clearStrike(){
        DriverManager.getDriver().findElement(strike1).clear();
    }
    public NewFXEuropeanOptionPage clickImpledVolatility(){
        Actions act = new Actions(DriverManager.getDriver());
        act.moveToElement(DriverManager.getDriver().findElement(impliedVol)).doubleClick().perform();
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
return this;
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
    public String getStrikeValue(){
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        return js.executeScript("return document.getElementById('strike1').value").toString();
    }

    public String getDeffermentTotal(){
        return getText(deffermentTotal,WaitStrategy.PRESENCE,"Defferment total");
    }
}
