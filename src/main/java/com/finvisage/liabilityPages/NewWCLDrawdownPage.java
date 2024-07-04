package com.finvisage.liabilityPages;
import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.finvisage.utils.XpathUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.*;

import java.util.concurrent.TimeUnit;

public class NewWCLDrawdownPage extends BasePageLiability {
    private final By drawdownExternalID = By.xpath("//input[@id='borrowing_drawdown_external_id']");
    private final By drawdownLedgerID = By.xpath("//input[@id='borrowing_drawdown_ledger_id']");
    private final By drawdownPrincipal = By.id("borrowing_drawdown_principal");
    private final By drawdownValueDate = By.id("borrowing_drawdown_start_date");
    private final By getDrawdownEndDate = By.id("borrowing_drawdown_end_date");
    private final By put_call = By.xpath("//select[@id='borrowing_drawdown_put_call']//following-sibling::div/div[1]");
    private final By loanAccount = By.id("borrowing_drawdown_loan_account_number");
    private final By operatingAccount = By.xpath("//select[@id='liability_bank_account_select']" +
            "/following-sibling::div/div[1]");
    private final By paymentAccount = By.xpath("//select[@id='borrowing_drawdown_payment_account_option']" +
            "/following-sibling::div/div[1]");

    private final By daysInYearType=By.xpath("//select[@id='days_in_a_year_type_select']//following-sibling::div/div[1]");
    private final By daysinYearNumber = By.id("days_in_a_year_number");
    private final By newInterestSlab = By.xpath("//a[text()='New Effective Interest Slab']");
    private final By interestType = By.xpath("//label[text()='Interest Type *']//parent::div/div/div[1]");
    private final By interestBenchmark = By.xpath("//label[text()='Interest Benchmark *']//parent::div/div/div");
    private final By interestSpread = By.xpath("//input[@data-inputmask-digits='4']");
    private final By newTDS = By.xpath("//a[text()='New TDS Slab']");
    private final By TDS = By.xpath("//label[text()='TDS *']//parent:: div/input");
    private final By newPrepaymentSlab = By.xpath("//a[text()='New Prepayment Penalty Slab']");
    private final By prepaymentPenalty = By.xpath("//label[text()='Penalty *']//parent:: div/input");
    private final By additionalInfo = By.xpath("//label[text()=' Additional Info *']//parent:: div/textarea");
    private final By btnCreate = By.xpath("//input[@name='commit']");


    public NewWCLDrawdownPage enterDrawdownExternalID(int count) {
        String randomID = generateRandomID(count, "WCL-Drawdown");
        sendText(drawdownExternalID, String.valueOf(randomID), WaitStrategy.PRESENCE, "External ID");
        return this;
    }

    public NewWCLDrawdownPage enterDrawdownLedgerID(int count) {
        String randomID = generateRandomID(count, "WCL-Drawdown");
        sendText(drawdownLedgerID, String.valueOf(randomID), WaitStrategy.PRESENCE, "Drawdown-ledger ID");
        return this;
    }

    public NewWCLDrawdownPage enterDrawdownPrincipal(String value) {
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        clickk(drawdownPrincipal,WaitStrategy.CLICKABLE,"Drawdown Principal");
        sendText(drawdownPrincipal, value, WaitStrategy.PRESENCE, "Drawdown-principal");
        return this;
    }

    public NewWCLDrawdownPage enterDrawdownValueDate(String text) {
        clearDate(drawdownValueDate).
                sendText(drawdownValueDate, text, WaitStrategy.PRESENCE, "sanction date");
        return this;
    }

    public NewWCLDrawdownPage enterDrawdownEndDate(String text) {
        clearDate(getDrawdownEndDate).
                sendText(getDrawdownEndDate, text, WaitStrategy.PRESENCE, "end date");
        return this;
    }
    public NewWCLDrawdownPage clickNewPrepayments() {
        clickk(newPrepaymentSlab, WaitStrategy.CLICKABLE, "New prepayments slab");
        return this;
    }

    public NewWCLDrawdownPage selectPrepaymentsPenalty(String penaltyValue) {
        jsClick(prepaymentPenalty,WaitStrategy.CLICKABLE, "penalty");
        WebElement ele = DriverManager.getDriver().findElement(prepaymentPenalty);
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        sendText(prepaymentPenalty, penaltyValue, WaitStrategy.PRESENCE, "penalty");

        return this;
    }

    public NewWCLDrawdownPage enterLoanAcnt(String value) {
        sendText(loanAccount, value, WaitStrategy.PRESENCE, "Loan Account");
        return this;
    }

    public NewWCLDrawdownPage selectPaymentAcnt(String text) {
        jsClick(paymentAccount, WaitStrategy.CLICKABLE, "Payment Account");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewWCLDrawdownPage selectOperatingAcnt(String text) {
        jsClick(operatingAccount, WaitStrategy.CLICKABLE, "Operating Account");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewWCLDrawdownPage selectDaysInYeartype(String text) {
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


    public NewWCLDrawdownPage clickNewIrSlab() {
        clickk(newInterestSlab, WaitStrategy.CLICKABLE, "new IR slab");
        return this;
    }

    public NewWCLDrawdownPage selectIRType(String text) {
        jsClick(interestType, WaitStrategy.CLICKABLE, "IR type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        if (getText(interestType, WaitStrategy.VISIBLE, "Interest Type").equalsIgnoreCase("floating")) {
            Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
            jsClick(interestBenchmark, WaitStrategy.CLICKABLE, "Benchmark");
            jsClick(By.xpath("//div[text()='AUTOMATION3 - 5years (06/02/2019) (6.0%)']"),
                    "Benchmark value");
        }
        return this;
    }


    public NewWCLDrawdownPage enterSpread(String value) {
        scrollIntoView(interestSpread);
        clickk(interestSpread, WaitStrategy.CLICKABLE, "Interest Spread");
        sendText(interestSpread, value, WaitStrategy.VISIBLE, "Interest Spread");
        return this;
    }

    public NewWCLDrawdownPage selectPut_Call(String value) {
        scrollIntoView(put_call);
        clickk(put_call, WaitStrategy.CLICKABLE, "put/call");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, value);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, value);
        return this;
    }

    public NewWCLDrawdownPage clickNewTDS() {
        jsClick(newTDS, WaitStrategy.CLICKABLE, "New TDS");
        return this;
    }

    public NewWCLDrawdownPage enterTDS(String tds) {
        clickk(TDS, WaitStrategy.CLICKABLE, "TDS");
        actionSendkeys(tds);

        return this;
    }

    public NewWCLDrawdownPage enterAdditionalInfo(String text) {
        sendText(additionalInfo, text, WaitStrategy.PRESENCE, "Additional Info");
        return this;
    }

    public WCLDrawdownPage clickCreate() {
        try {
            doubleClick(btnCreate);
            Alert al = DriverManager.getDriver().switchTo().alert();
            al.accept();
            al.accept();
        } catch (NoAlertPresentException e) {
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        }

        return new WCLDrawdownPage();
    }
}
