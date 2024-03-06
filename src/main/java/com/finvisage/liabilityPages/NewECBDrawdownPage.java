package com.finvisage.liabilityPages;

import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.finvisage.utils.XpathUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class NewECBDrawdownPage extends BasePageLiability {

    private final By drawdownExternalID = By.xpath("//label[text()=' External ID *']/parent::div/input");
    private final By drawdownLedgerID = By.xpath("//label[text()='Ledger ID']/parent::div/input");
    private final By hedger = By.xpath("//select[@id='liability_counterparty_select']/following-sibling::div/div[1]");
    private final By drawdownValueDate = By.id("ecb_drawdown_start_date");
    private final By getDrawdownEndDate = By.id("ecb_drawdown_end_date");
    private final By penalty = By.id("prepayment_penalty_number");
    private final By put_call = By.xpath("//label[text()='Put/Call *']//parent::div/div/div");
    private final By loanAccount = By.xpath("//label[text()=' Loan Account *']/parent::div/input");
    private final By operatingAccount = By.xpath("//select[@id='liability_bank_account_select']" +
            "/following-sibling::div/div[1]");
    private final By paymentAccount = By.xpath("//select[@id='ecb_dropdown_payment_account_" +
            "option']/following-sibling::div/div[1]");
    private final By newDisbursement = By.xpath("//a[text()='New Disbursement']");

    private final By disbursementAmount = By.xpath("//label[text()='Disbursement Amount *']//parent::div/input");

    private final By disbursementType = By.xpath("//label[text()='Disbursement Type *']//parent::div");
    private final By newInterestSlab = By.xpath("//a[text()='New Interest Rate Slab']");
    private final By interestType = By.xpath("//label[text()='Interest Type *']//parent::div/div/div[1]");
    private final By interestBenchmark = By.xpath("//label[text()='Interest Benchmark *']//parent::div/div/div[1]");
    private final By interestSpread = By.xpath("(//input[@data-inputmask-digits='4'])[1]");
    private final By newHedgingRateSlab = By.xpath("//a[text()='New Hedging rate Slab']");
    private final By hedgingInterestType = By.xpath("(//label[text()='Interest Type *']//parent::div)[2]/div/div[1]");
    private final By hedgingBenchmark = By.xpath("(//label[text()='Interest Benchmark *']//parent::div)[2]/div/div[1]");
    private final By hedgingInterestSpread = By.xpath("(//input[@data-inputmask-digits='4'])[2]");
    private final By hedgingDaysinYearType = By.xpath("//select[@id='hedging_days_in_a_year_type_select']" +
            "/following-sibling::div/div[1]");
    private final By hedgingDaysinyearNumber = By.id("hedging_days_in_a_year_number");
    private final By conversionRate = By.xpath("//label[text()=' Conversion Rate *']//parent::div/input");
    private final By newTDS = By.xpath("//a[text()='New TDS Slab']");
    private final By TDS = By.xpath("//label[text()='TDS *']//parent:: div/input");
    private final By newWithholdingTax = By.xpath("//a[text()='New Withholding Tax']");
    private final By withholdingTax = By.xpath("//label[text()='Withholding Tax *']//parent:: div/input");
    private final By additionalInfo = By.xpath("//label[text()=' Additional Info *']//parent:: div/textarea");
    private final By btnCreate = By.xpath("//input[@name='commit']");


    public NewECBDrawdownPage enterDrawdownExternalID(int count) {
        String randomID = generateRandomID(count, "ECB-Drawdown");
        sendText(drawdownExternalID, String.valueOf(randomID), WaitStrategy.PRESENCE, "External ID");
        return this;
    }

    public NewECBDrawdownPage enterDrwadownLedgerID(int count) {
        String randomID = generateRandomID(count, "ECB-Drawdown");
        sendText(drawdownLedgerID, String.valueOf(randomID), WaitStrategy.PRESENCE, "Drawdown-ledger ID");
        return this;
    }

    public NewECBDrawdownPage selectPayementAcnt(String text) {
        jsClick(paymentAccount, WaitStrategy.CLICKABLE, "Payment Account");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }


    public NewECBDrawdownPage enterDrawdownValueDate(String text) {
        clearDate(drawdownValueDate).
                sendText(drawdownValueDate, text, WaitStrategy.PRESENCE, "sanction date");
        return this;
    }

    public NewECBDrawdownPage enterDrawdownEndDate(String text) {
        clearDate(getDrawdownEndDate).
                sendText(getDrawdownEndDate, text, WaitStrategy.PRESENCE, "end date");
        return this;
    }

    public NewECBDrawdownPage selectPrepayemntsPenalty(String penaltyValue) {
        jsClick(penalty, "penlty");
        WebElement ele = DriverManager.getDriver().findElement(penalty);
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        sendText(penalty, penaltyValue, WaitStrategy.PRESENCE, "penalty");

        return this;
    }

    public NewECBDrawdownPage enterLoanAcnt(String value) {
        sendText(loanAccount, value, WaitStrategy.PRESENCE, "Loan Account");
        return this;
    }

    public NewECBDrawdownPage selectHedger(String text) {
        jsClick(hedger, WaitStrategy.CLICKABLE, "Hedger");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewECBDrawdownPage selectHedgingDaysInYeartype(String text) {
        jsClick(hedgingDaysinYearType, WaitStrategy.CLICKABLE, "Hedging days in a year type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        if (getText(hedgingDaysinYearType, WaitStrategy.VISIBLE, "Days in a year type").equalsIgnoreCase("Constant Days")) {
            Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
            clickk(hedgingDaysinyearNumber, WaitStrategy.CLICKABLE, "Days in a year");
            sendText(hedgingDaysinyearNumber, "365", WaitStrategy.PRESENCE, "Days in a year");
        }
        return this;
    }

    public NewECBDrawdownPage selectOperatingAcnt(String text) {
        jsClick(operatingAccount, WaitStrategy.CLICKABLE, "Operating Account");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewECBDrawdownPage clickNewDisbursement() {
        clickk(newDisbursement, WaitStrategy.CLICKABLE, "new Disbursement button");
        return this;
    }

    public NewECBDrawdownPage enterDisAmount(String value) {
        clickk(disbursementAmount, WaitStrategy.CLICKABLE, "disbursement amount");
        sendText(disbursementAmount, value, WaitStrategy.PRESENCE, "--");
        return this;
    }

    public NewECBDrawdownPage selectDisbursementType(String text) {
        scrollIntoView(disbursementType);
        clickk(disbursementType, WaitStrategy.CLICKABLE, "Disbursement Type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewECBDrawdownPage clickNewIrSlab() {
        clickk(newInterestSlab, WaitStrategy.CLICKABLE, "new IR slab");
        return this;
    }

    public NewECBDrawdownPage clickHedgingslab() {
        clickk(newHedgingRateSlab, WaitStrategy.CLICKABLE, "new Hedinging Slab");
        return this;
    }

    public NewECBDrawdownPage selectIRType(String text) {
        jsClick(interestType, WaitStrategy.CLICKABLE, "IR type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        if (getText(interestType, WaitStrategy.VISIBLE, "Interest Type").equalsIgnoreCase("floating")) {
            Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
            jsClick(interestBenchmark, WaitStrategy.CLICKABLE, "Benchamrk");
            actionSendkeys("AUTOMATION3 - 5years (06/02/2019) (6.0%)");
        }
        return this;
    }

    public NewECBDrawdownPage selectHedgingIRType(String text) {
        clickk(hedgingInterestType, WaitStrategy.CLICKABLE, "Hedging IR type");
        scrollIntoView(hedgingInterestType);
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        if (getText(interestType, WaitStrategy.VISIBLE, "IR Type").equalsIgnoreCase(text)) {
            String ar = "(//div[text()='%replace%'])[3]";
            String newxpath = XpathUtils.getXpath(ar, text);
            clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        } else {
            String ar = "(//div[text()='%replace%'])[2]";
            String newxpath = XpathUtils.getXpath(ar, text);
            clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        }
        if (getText(hedgingInterestType, WaitStrategy.VISIBLE, "Hedging Interest Type").equalsIgnoreCase("floating")) {
            Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
            jsClick(hedgingBenchmark, WaitStrategy.CLICKABLE, "Benchamrk");
           actionSendkeys("AUTOMATION3 - 5years (06/02/2019) (6.0%)");
        }
        return this;
    }


    public NewECBDrawdownPage enterSpread(String value) {
        scrollIntoView(interestSpread);
        clickk(interestSpread, WaitStrategy.CLICKABLE, "Interest Spread");
        sendText(interestSpread, value, WaitStrategy.VISIBLE, "Interest Spread");
        return this;
    }

    public NewECBDrawdownPage enterHedgingSpread(String val) {
        scrollIntoView(hedgingInterestSpread);
        clickk(hedgingInterestSpread, WaitStrategy.CLICKABLE, "Hedging Interest Spread");
        sendText(hedgingInterestSpread, val, WaitStrategy.PRESENCE, "Hedging IR Spread");
        return this;
    }

    public NewECBDrawdownPage selectPut_Call(String value) {
        scrollIntoView(put_call);
        jsClick(put_call, WaitStrategy.CLICKABLE, "put/call");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, value);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, value);
        return this;
    }

    public NewECBDrawdownPage enterConversionRate(String value) {
        jsClick(conversionRate, WaitStrategy.CLICKABLE, "conversion rate");
        clearDate(conversionRate).
        sendText(conversionRate, value, WaitStrategy.PRESENCE, "Conversion Rate");
        return this;
    }

    public NewECBDrawdownPage clickNewTDS() {
        jsClick(newTDS, WaitStrategy.CLICKABLE, "New TDS");
        return this;
    }

    public NewECBDrawdownPage enterTDS(String tds) {
        clickk(TDS, WaitStrategy.CLICKABLE, "TDS");
        actionSendkeys(tds);

        return this;
    }

    public NewECBDrawdownPage clickNewWithholdingTax() {
        jsClick(newWithholdingTax, WaitStrategy.CLICKABLE, "New Withholding Tax");
        return this;
    }

    public NewECBDrawdownPage enterWithholdingTax(String tds) {
        clickk(withholdingTax, WaitStrategy.CLICKABLE, "Withholding Tax");
        actionSendkeys(tds);

        return this;
    }

    public NewECBDrawdownPage enterAdditionalInfo(String text) {
        sendText(additionalInfo, text, WaitStrategy.PRESENCE, "Additional Info");
        return this;
    }


    public ECBDrawdownPage clickCreate() {
        clickk(btnCreate, WaitStrategy.CLICKABLE, "Create button");
        return new ECBDrawdownPage();
    }
}
