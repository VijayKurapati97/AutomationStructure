package com.finvisage.liabilityPages;

import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.finvisage.utils.XpathUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.*;

import java.util.concurrent.TimeUnit;

public class NewLFDrawdownPage extends BasePageLiability {
    private final By drawdownExternalID = By.xpath("//label[text()=' External ID *']/parent::div/input");
    private final By drawdownLedgerID = By.xpath("//label[text()='Ledger ID']/parent::div/input");
    private final By drawdownValueDate = By.id("loan_facility_drawdown_start_date");
    private final By getDrawdownEndDate = By.id("loan_facility_drawdown_end_date");
    // private final By prepayments = By.xpath("//select[@id='prepayment_allowed_select']/following-sibling:: div/div[1]");
    private final By penalty = By.id("prepayment_penalty_number");
    //private final By lockInPeriod = By.id("lock_in_period_number");
    private final By put_call = By.xpath("//label[text()='Put/Call *']//parent::div/div/div");
    private final By loanAccount = By.xpath("//label[text()='Loan Account *']//parent::div/input");
    private final By operatingAccount = By.xpath("//select[@id='liability_bank_account_select']" +
            "/following-sibling::div/div[1]");
    private final By paymentAccount = By.xpath("//select[@id='loan_facility_dropdown_payment_account_" +
            "option']/following-sibling::div/div[1]");
    /*private final By counterpartyBankAccount = By.xpath("//select[@id='loan_facility_dropdown_counterparty" +
            "_bank_account_id']/following-sibling::div/div[1]");*/
    private final By newDisbursement = By.xpath("//a[text()='New Disbursement']");
    private final By disbursementAmount = By.xpath("//label[text()='Disbursement Amount *']//parent::div/input");

    private final By disbursementType = By.xpath("//label[text()='Disbursement Type *']//parent::div");
    private final By newInterestSlab = By.xpath("//a[text()='New Interest Rate Slab']");
    private final By interestType = By.xpath("//label[text()='Interest Type *']//parent::div/div/div");
    private final By interestBenchmark = By.xpath("//label[text()='Interest Benchmark *']//parent::div/div/div");
    private final By interestSpread = By.xpath("//input[@data-inputmask-digits='4']");
    private final By newTDS = By.xpath("//a[text()='New TDS Slab']");
    private final By TDS = By.xpath("//label[text()='TDS *']//parent:: div/input");
    private final By additionalInfo = By.xpath("//label[text()=' Additional Info *']//parent:: div/textarea");
    private final By btnCreate = By.xpath("//input[@name='commit']");
    private final By btn_SaveAsDraft = By.xpath("//a[text()='Cancel']//following-sibling::a");
    private final By btn_cancel = By.xpath("//a[text()='Cancel']");

    public NewLFDrawdownPage enterDrawdownExternalID(int count) {
        String randomID = generateRandomID(count, "LoanFacility-Drawdown");
        sendText(drawdownExternalID, String.valueOf(randomID), WaitStrategy.PRESENCE, "External ID");
        return this;
    }

    public NewLFDrawdownPage enterDrawdownLedgerID(int count) {
        String randomID = generateRandomID(count, "LoanFacility-Drawdown");
        sendText(drawdownLedgerID, String.valueOf(randomID), WaitStrategy.PRESENCE, "Drawdown-ledger ID");
        return this;
    }

    public NewLFDrawdownPage enterDrawdownValueDate(String text) {
        clearDate(drawdownValueDate).
                sendText(drawdownValueDate, text, WaitStrategy.PRESENCE, "sanction date");
        return this;
    }

    public NewLFDrawdownPage enterDrawdownEndDate(String text) {
        clearDate(getDrawdownEndDate).
                sendText(getDrawdownEndDate, text, WaitStrategy.PRESENCE, "end date");
        return this;
    }

    public NewLFDrawdownPage selectPrepaymentsPenalty(String penaltyValue) {
        jsClick(penalty, "penalty");
        WebElement ele = DriverManager.getDriver().findElement(penalty);
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        sendText(penalty, penaltyValue, WaitStrategy.PRESENCE, "penalty");

        return this;
    }

    public NewLFDrawdownPage enterLoanAcnt(String value) {
        sendText(loanAccount, value, WaitStrategy.PRESENCE, "Loan Account");
        return this;
    }

    public NewLFDrawdownPage selectPaymentAcnt(String text) {
        jsClick(paymentAccount, WaitStrategy.CLICKABLE, "Payment Account");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewLFDrawdownPage selectOperatingAcnt(String text) {
        jsClick(operatingAccount, WaitStrategy.CLICKABLE, "Operating Account");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewLFDrawdownPage clickNewDisbursement() {
        clickk(newDisbursement, WaitStrategy.CLICKABLE, "new Disbursement button");
        return this;
    }

    public NewLFDrawdownPage enterDisAmount(String value) {
        clickk(disbursementAmount, WaitStrategy.CLICKABLE, "disbursement amount");
        sendText(disbursementAmount, value, WaitStrategy.PRESENCE, "disbursement amount");
        return this;
    }

    public NewLFDrawdownPage selectDisbursementType(String text) {
        scrollIntoView(disbursementType);
        clickk(disbursementType, WaitStrategy.CLICKABLE, "Disbursement Type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewLFDrawdownPage clickNewIrSlab() {
        clickk(newInterestSlab, WaitStrategy.CLICKABLE, "new IR slab");
        return this;
    }

    public NewLFDrawdownPage selectIRType(String text) {
        jsClick(interestType, WaitStrategy.CLICKABLE, "IR type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        if (getText(interestType, WaitStrategy.VISIBLE, "Interest Type").equalsIgnoreCase("floating")) {
            Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
            jsClick(interestBenchmark, WaitStrategy.CLICKABLE, "Benchmark");
            actionSendkeys("AUTOMATION_MARK_123");
        }
        return this;
    }


    public NewLFDrawdownPage enterSpread(String value) {
        scrollIntoView(interestSpread);
        clickk(interestSpread, WaitStrategy.CLICKABLE, "Interest Spread");
        sendText(interestSpread, value, WaitStrategy.VISIBLE, "Interest Spread");
        return this;
    }

    public NewLFDrawdownPage selectPut_Call(String value) {
        scrollIntoView(put_call);
        clickk(put_call, WaitStrategy.CLICKABLE, "put/call");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, value);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, value);
        return this;
    }

    public NewLFDrawdownPage clickNewTDS() {
        jsClick(newTDS, WaitStrategy.CLICKABLE, "New TDS");
        return this;
    }

    public NewLFDrawdownPage enterTDS(String tds) {
        clickk(TDS, WaitStrategy.CLICKABLE, "TDS");
        actionSendkeys(tds);

        return this;
    }

    public NewLFDrawdownPage enterAdditionalInfo(String text) {
        sendText(additionalInfo, text, WaitStrategy.PRESENCE, "Additional Info");
        return this;
    }

    public LoanFacilityDrawdownPage clickCreate() {
        clickk(btnCreate, WaitStrategy.CLICKABLE, "Create button");
        return new LoanFacilityDrawdownPage();
    }

    //TO DO
    //clickCancel() method is added because after clicking save as draft button it's not navigating to Loan Facility page
    public NewLFDrawdownPage clickSaveAsDraft() {
        for (int i = 0; i < 10; i++) {
            clickk(btn_SaveAsDraft, WaitStrategy.CLICKABLE, "Save as draft");
            try {
                scrollIntoView(btn_SaveAsDraft);
                clickk(btn_SaveAsDraft, WaitStrategy.CLICKABLE, "Save as draft");
                Alert al = DriverManager.getDriver().switchTo().alert();
                al.accept();
                break;
            } catch (NoAlertPresentException e) {
                Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
            }
        }
        return this;
    }

    public LoanFacilityPage clickCancel() {
        clickk(btn_cancel, WaitStrategy.CLICKABLE, "cancel button");
        return new LoanFacilityPage();
    }

}
