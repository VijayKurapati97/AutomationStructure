package com.finvisage.liabilityPages;

import com.finvisage.enums.WaitStrategy;
import com.finvisage.utils.XpathUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class NewDrawdownPage extends BasePageLiability {
    private final By drawdownExternalID = By.xpath("//label[text()=' External ID *']/parent::div/input");
    private final By drawdownLedgerID = By.xpath("//label[text()='Ledger ID']/parent::div/input");
    private final By drawdownValueDate = By.id("loan_facility_drawdown_start_date");
    private final By getDrawdownEndDate = By.id("loan_facility_drawdown_end_date");
    private final By prepayments = By.xpath("//select[@id='prepayment_allowed_select']/following-sibling:: div/div[1]");
    private final By penalty = By.id("prepayment_penalty_number");
    private final By lockInPeriod = By.id("lock_in_period_number");
    private final By loanAccount = By.xpath("//label[text()=' Loan Account *']/parent::div/input");
    private final By operatingAccount = By.xpath("//select[@id='liability_bank_account_select']" +
            "/following-sibling::div/div[1]");
    private final By paymentAccount = By.xpath("//select[@id='loan_facility_dropdown_payment_account_" +
            "option']/following-sibling::div/div[1]");
    private final By counterpartyBankAccount = By.xpath("//select[@id='loan_facility_dropdown_counterparty" +
            "_bank_account_id']/following-sibling::div/div[1]");
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

    public NewDrawdownPage enterDrawdownExternalID(int count) {
        String randomID = generateRandomID(count, "LoanFacility-Drawdown");
        sendText(drawdownExternalID, String.valueOf(randomID), WaitStrategy.PRESENCE, "External ID");
        return this;
    }

    public NewDrawdownPage enterDrwadownLedgerID(int count) {
        String randomID = generateRandomID(count, "LoanFacility-Drawdown");
        sendText(drawdownLedgerID, String.valueOf(randomID), WaitStrategy.PRESENCE, "Drawdown-ledger ID");
        return this;
    }

    public NewDrawdownPage enterDrawdownValueDate(String text) {
        clearDate(drawdownValueDate).
                sendText(drawdownValueDate, text, WaitStrategy.PRESENCE, "sanction date");
        return this;
    }

    public NewDrawdownPage enterDrawdownEndDate(String text) {
        clearDate(getDrawdownEndDate).
                sendText(getDrawdownEndDate, text, WaitStrategy.PRESENCE, "end date");
        return this;
    }

    public NewDrawdownPage selectPrepayemnts(String text,String penaltyValue) {
        jsClick(prepayments, "prepayemnts");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        if (getText(prepayments, WaitStrategy.VISIBLE, "prepayment Type").equalsIgnoreCase("yes")){
            sendText(penalty, penaltyValue, WaitStrategy.PRESENCE, "penalty");
        }
        return this;
    }
    public NewDrawdownPage selectPrepayemnts(String text) {
        jsClick(prepayments, "prepayemnts");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
        public NewDrawdownPage enterLoanAcnt(String value) {
        sendText(loanAccount, value, WaitStrategy.PRESENCE, "Loan Account");
        return this;
    }

    public NewDrawdownPage selectPayementAcnt(String text) {
        jsClick(paymentAccount, WaitStrategy.CLICKABLE, "Payement Account");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewDrawdownPage selectOperatingAcnt(String text) {
        jsClick(operatingAccount, WaitStrategy.CLICKABLE, "Operating Account");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewDrawdownPage clickNewDisbursement() {
        clickk(newDisbursement, WaitStrategy.CLICKABLE, "new Disbursement button");
        return this;
    }

   public NewDrawdownPage enterDisAmount(String value) {
        clickk(disbursementAmount,WaitStrategy.CLICKABLE,"disbursement amount");
        sendText(disbursementAmount,value,WaitStrategy.PRESENCE,"--");
        return this;
    }

    public NewDrawdownPage selectDisbursementType(String text) {
        scrollIntoView(disbursementType);
        clickk(disbursementType, WaitStrategy.CLICKABLE, "Disbursement Type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewDrawdownPage clickNewIrSlab() {
        clickk(newInterestSlab, WaitStrategy.CLICKABLE, "new IR slab");
        return this;
    }

    public NewDrawdownPage selectIRType(String text) {
        jsClick(interestType, WaitStrategy.CLICKABLE, "IR type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        if (getText(interestType, WaitStrategy.VISIBLE, "Interest Type").equalsIgnoreCase("floating")) {
            jsClick(interestBenchmark, WaitStrategy.CLICKABLE, "Benchamrk");
            Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
            jsClick(By.xpath("//div[text()='AUTOMATION_MARK_123 - 5years (05/11/2018) (12.0%)']"),
                    "Benchmark value");
        }
        return this;
    }


    public NewDrawdownPage enterSpread(String value){
        scrollIntoView(interestSpread);
        clickk(interestSpread,WaitStrategy.CLICKABLE,"Interest Spread");
        sendText(interestSpread,value,WaitStrategy.VISIBLE,"Interest Spread");
        return this;
    }

    public NewDrawdownPage clickNewTDS() {
        jsClick(newTDS, WaitStrategy.CLICKABLE, "New TDS");
        return this;
    }

    public NewDrawdownPage enterTDS(String tds) {
        clickk(TDS, WaitStrategy.CLICKABLE, "TDS");
        actionSendkeys(tds);

        return this;
    }

    public NewDrawdownPage enterAdditionalInfo(String text) {
        sendText(additionalInfo, text, WaitStrategy.PRESENCE, "Additional Info");
        return this;
    }
    public LoanFacilityDrawdownPage clickCreate(){
        clickk(btnCreate,WaitStrategy.CLICKABLE,"Create button");
        return new LoanFacilityDrawdownPage();
    }


}
