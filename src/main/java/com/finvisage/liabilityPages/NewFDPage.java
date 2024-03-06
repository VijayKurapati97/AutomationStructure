package com.finvisage.liabilityPages;

import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.finvisage.utils.XpathUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.concurrent.TimeUnit;

public class NewFDPage extends BasePageLiability {
    private final By fixedDepositType = By.xpath("//select[@id='fixed_deposit_fixed_deposit_type']/following-sibling::div/div[1]");
    private final By fD_DepositNumber = By.id("fixed_deposit_external_id");
    private final By entity = By.xpath("//select[@id='asset_entity_select']/following-sibling::div/div[1]");
    private final By counterparty = By.xpath("//select[@id='asset_counterparty_select']/following-sibling::div/div[1]");
    private final By principal = By.id("fixed_deposit_principal");
    private final By onMaturity = By.xpath("//select[@id='asset_on_maturity_select']/following-sibling::div/div[1]");
    private final By debitBankAccount = By.xpath("//select[@id='asset_bank_account_select']/following-sibling::div/div[1]");
    private final By beneficiaryAccount = By.xpath("//select[@id='asset_beneficiary_account_select']/following-sibling::div/div[1]");
    private final By startDate = By.xpath("fixed_deposit_start_date");
    private final By tenoreYears = By.id("tenor_years");
    private final By tenoreMonths = By.id("tenor_months");
    private final By tenoreDays = By.id("tenor_days");
    private final By newInterestSlab = By.xpath("//a[text()='New Effective Interest Schedule']");
    private final By interestType = By.xpath("//label[text()='Interest Type *']//parent::div/div/div[1]");
    private final By interestBenchmark = By.xpath("//label[text()='Interest Benchmark *']//parent::div/div/div[1]");
    private final By interestSpread = By.xpath("//input[@data-inputmask-digits='4']");
    private final By newTDS = By.xpath("//a[text()='New TDS Schedule']");
    private final By TDS = By.xpath("//label[text()='TDS *']//parent:: div/input");
    private final By additionalInfo = By.xpath("//textarea[@id='fixed_deposit_additional_info']");
    private final By btnCreate = By.xpath("//input[@name='commit']");

    public NewFDPage selectFixedDepositType(String text) {
        jsClick(fixedDepositType, WaitStrategy.CLICKABLE, "Fixed Deposit type");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String fdType = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(fdType, text);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewFDPage enterDepositNumber(int count) {
        String randomExternalID = generateRandomID(count, "Fixed Deposit");
        sendText(fD_DepositNumber, String.valueOf(randomExternalID), WaitStrategy.PRESENCE, "Deposit Number");
        return this;
    }

    public NewFDPage selectEntity(String text) {
        clickk(entity, WaitStrategy.CLICKABLE, "Entity");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String fdType = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(fdType, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewFDPage selectCounterparty(String text) {
        jsClick(counterparty, WaitStrategy.CLICKABLE, "Counterparty");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String fdType = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(fdType, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewFDPage enterPrincipal(String text) {
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        clickk(principal, WaitStrategy.CLICKABLE, "Principal Textbox");
        sendText(principal, text, WaitStrategy.PRESENCE, "Prinipal");
        return this;
    }

    public NewFDPage selectOnMaturity(String text) {
        jsClick(onMaturity, WaitStrategy.CLICKABLE, "On Maturity");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String maturityType = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(maturityType, text);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewFDPage selectDebitBankAccount(String text) {
        jsClick(debitBankAccount, WaitStrategy.CLICKABLE, "Debit Bank Account");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String dba = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(dba, text);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewFDPage selectBeneficiaryAccount(String text) {
        jsClick(beneficiaryAccount, WaitStrategy.CLICKABLE, "Beneficiary Account");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String ba = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ba, text);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewFDPage enterStartDate(String text) {
        clearDate(startDate).
                sendText(startDate, text, WaitStrategy.PRESENCE, "FD Start date");
        return this;
    }

    public NewFDPage enterTenure(String Years) {
        scrollIntoView(tenoreYears);
        jsClick(tenoreYears,"Years");
        sendText(tenoreYears, Years, WaitStrategy.PRESENCE, "Tenure Years");
        DriverManager.getDriver().findElement(tenoreYears).sendKeys(Keys.DELETE);
        return this;
    }
    public NewFDPage clickNewIRSlab() {
        clickk(newInterestSlab, WaitStrategy.CLICKABLE, "new IR slab");
        return this;
    }
    public NewFDPage selectIRType(String text) {
        jsClick(interestType, WaitStrategy.CLICKABLE, "IR type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        if (getText(interestType, WaitStrategy.VISIBLE, "Interest Type").equalsIgnoreCase("floating")) {
            jsClick(interestBenchmark, WaitStrategy.CLICKABLE, "Benchamrk");
            Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
            jsClick(By.xpath("//div[text()='AUTOMATION_MARK_123 - 5years (05/11/2018) (7.0%)']"),
                    "Benchmark value");
        }
        return this;
    }
    public NewFDPage enterSpread(String value) {
        scrollIntoView(interestSpread);
        clickk(interestSpread, WaitStrategy.CLICKABLE, "Interest Spread");
        sendText(interestSpread, value, WaitStrategy.VISIBLE, "Interest Spread");
        return this;
    }
    public NewFDPage clickNewTDS() {
        jsClick(newTDS, WaitStrategy.CLICKABLE, "New TDS");
        return this;
    }

    public NewFDPage enterTDS(String tds) {
        clickk(TDS, WaitStrategy.CLICKABLE, "TDS");
        actionSendkeys(tds);
        return this;
    }
    public NewFDPage enterAdditionalInfo(String text) {
        sendText(additionalInfo, text, WaitStrategy.PRESENCE, "Additional Info");
        return this;
    }

    public FixedDepositPage clickCreate() {
        clickk(btnCreate, WaitStrategy.CLICKABLE, "Create button");
        return new FixedDepositPage();
    }


}
