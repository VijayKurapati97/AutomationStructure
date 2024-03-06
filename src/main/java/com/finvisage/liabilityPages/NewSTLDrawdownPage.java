package com.finvisage.liabilityPages;

import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.finvisage.utils.XpathUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.*;

import java.util.concurrent.TimeUnit;

public class NewSTLDrawdownPage extends BasePageLiability{
    private final By drawdownExternalID = By.xpath("//label[text()=' External ID *']/parent::div/input");
    private final By drawdownLedgerID = By.xpath("//label[text()='Ledger ID']/parent::div/input");
    private final By drawdownPrincipal =By.id("borrowing_drawdown_principal");
    private final By drawdownValueDate = By.id("borrowing_drawdown_start_date");
    private final By getDrawdownEndDate = By.id("borrowing_drawdown_end_date");
    private final By put_call = By.xpath("//select[@id='borrowing_drawdown_put_call']" +
            "/following-sibling::div/div[1]");
    private final By loanAccount = By.xpath("//label[text()=' Loan Account *']/parent::div/input");
    private final By operatingAccount = By.xpath("//select[@id='liability_bank_account_select']" +
            "/following-sibling::div/div[1]");
    private final By paymentAccount = By.xpath("//select[@id='borrowing_drawdown_payment_account_option']" +
            "//following-sibling::div/div[1]");
    private final By daysInYearType=By.xpath("//select[@id='days_in_a_year_type_select']//following-sibling::div/div[1]");
    private final By daysinYearNumber = By.id("days_in_a_year_number");
    private final By newInterestSlab = By.xpath("//a[text()='New Effective Interest Slab']");
    private final By interestType = By.xpath("//label[text()='Interest Type *']//parent::div/div/div[1]");
    private final By interestBenchmark = By.xpath("//label[text()='Interest Benchmark *']//parent::div/div/div[1]");
    private final By interestSpread = By.xpath("//input[@data-inputmask-digits='4']");
    private final By newTDS = By.xpath("//a[text()='New TDS Slab']");
    private final By TDS = By.xpath("//label[text()='TDS *']//parent:: div/input");
    private final By newprepaymentSlab = By.xpath("//a[text()='New Prepayment Penalty Slab']");
    private final By prepayemntPenalty = By.xpath("//label[text()='Penalty *']//parent:: div/input");
    private final By additionalInfo = By.xpath("//label[text()=' Additional Info *']//parent:: div/textarea");
    private final By btnCreate = By.xpath("//input[@name='commit']");
    private final By btn_update=By.xpath("//input[@value='Update']");




    public NewSTLDrawdownPage enterDrawdownExternalID(int count) {
        String randomID = generateRandomID(count, "ShortTermLoan-Drawdown");
        sendText(drawdownExternalID, String.valueOf(randomID), WaitStrategy.PRESENCE, "External ID");
        return this;
    }

    public NewSTLDrawdownPage enterDrwadownLedgerID(int count) {
        String randomID = generateRandomID(count, "ShortTermLoan-Drawdown");
        sendText(drawdownLedgerID, String.valueOf(randomID), WaitStrategy.PRESENCE, "Drawdown-ledger ID");
        return this;
    }
    public NewSTLDrawdownPage enterDrwadownPrincipal(String value) {
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        clickk(drawdownPrincipal,WaitStrategy.CLICKABLE,"principal");
        sendText(drawdownPrincipal, value, WaitStrategy.PRESENCE, "Drawdown-principal");
        return this;
    }

    public NewSTLDrawdownPage enterDrawdownValueDate(String text) {
        clearDate(drawdownValueDate).
                sendText(drawdownValueDate, text, WaitStrategy.PRESENCE, "value date");
        return this;
    }

    public NewSTLDrawdownPage enterDrawdownEndDate(String text) {
        clearDate(getDrawdownEndDate).
                sendText(getDrawdownEndDate, text, WaitStrategy.PRESENCE, "end date");
        return this;
    }
    public NewSTLDrawdownPage selectPut_Call(String value) {
        scrollIntoView(put_call);
        clickk(put_call, WaitStrategy.CLICKABLE, "put/call");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, value);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, value);
        return this;
    }
    public NewSTLDrawdownPage enterLoanAcnt(String value) {
        sendText(loanAccount, value, WaitStrategy.PRESENCE, "Loan Account");
        return this;
    }

    public NewSTLDrawdownPage selectPayementAcnt(String text) {
        clickk(paymentAccount, WaitStrategy.CLICKABLE, "Payment Account");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewSTLDrawdownPage selectOperatingAcnt(String text) {
        jsClick(operatingAccount, WaitStrategy.CLICKABLE, "Operating Account");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewSTLDrawdownPage selectDaysInYeartype(String text) {
        jsClick(daysInYearType, WaitStrategy.CLICKABLE, "days in a year type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        if (getText(daysInYearType, WaitStrategy.VISIBLE, "Days in a year type").equalsIgnoreCase("Constant Days")) {
            Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
            clickk(daysinYearNumber, WaitStrategy.CLICKABLE, "Days in a year");
            sendText(daysinYearNumber, "365", WaitStrategy.PRESENCE, "Days in a year");
        }
        return this;
    }
    public NewSTLDrawdownPage clickNewIrSlab() {
        clickk(newInterestSlab, WaitStrategy.CLICKABLE, "new IR slab");
        return this;
    }

    public NewSTLDrawdownPage selectIRType(String text) {
        jsClick(interestType, WaitStrategy.CLICKABLE, "IR type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        if (getText(interestType, WaitStrategy.VISIBLE, "Interest Type").equalsIgnoreCase("floating")) {
            Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
            jsClick(interestBenchmark, WaitStrategy.CLICKABLE, "Benchamrk");
            jsClick(By.xpath("//div[text()='AUTOMATION3 - 5years (06/02/2019) (6.0%)']"),
                    "Benchmark value");
        }
        return this;
    }


    public NewSTLDrawdownPage enterSpread(String value) {
        scrollIntoView(interestSpread);
        clickk(interestSpread, WaitStrategy.CLICKABLE, "Interest Spread");
        sendText(interestSpread, value, WaitStrategy.VISIBLE, "Interest Spread");
        return this;
    }
    public NewSTLDrawdownPage clickNewTDS() {
        jsClick(newTDS, WaitStrategy.CLICKABLE, "New TDS");
        return this;
    }

    public NewSTLDrawdownPage enterTDS(String tds) {
        clickk(TDS, WaitStrategy.CLICKABLE, "TDS");
        actionSendkeys(tds);

        return this;
    }
    public NewSTLDrawdownPage clickNewPrepayments() {
        jsClick(newprepaymentSlab, WaitStrategy.CLICKABLE, "New prepayments slab");
        return this;
    }
    public NewSTLDrawdownPage selectPrepayemntsPenalty(String penaltyValue) {
        clickk(prepayemntPenalty,WaitStrategy.CLICKABLE, "penlty");
        WebElement ele= DriverManager.getDriver().findElement(prepayemntPenalty);
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        sendText(prepayemntPenalty, penaltyValue, WaitStrategy.PRESENCE, "penalty");

        return this;
    }
    public NewSTLDrawdownPage enterAdditionalInfo(String text) {
        sendText(additionalInfo, text, WaitStrategy.PRESENCE, "Additional Info");
        return this;
    }

    public STLDrawdownPage clickCreate() {
        // Data Table alert is displayed while creating drawdown;
        //Remove the Alert handling code
            try {
                doubleClick(btnCreate);
                Alert al = DriverManager.getDriver().switchTo().alert();
                al.accept();
                al.accept();
            } catch (NoAlertPresentException e) {
                Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            }

        return new STLDrawdownPage();
    }
    public STLDrawdownPage clickUpdate(){
        try{
            clickk(btn_update,WaitStrategy.CLICKABLE,"Update button");
            Uninterruptibles.sleepUninterruptibly(5,TimeUnit.SECONDS);
            Alert al = DriverManager.getDriver().switchTo().alert();
            al.accept();
            al.accept();
        }catch (NoAlertPresentException e) {
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        }
        return new STLDrawdownPage();
    }
}
