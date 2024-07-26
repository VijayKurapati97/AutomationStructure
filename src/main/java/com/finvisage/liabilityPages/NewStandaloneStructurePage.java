package com.finvisage.liabilityPages;

import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.finvisage.utils.XpathUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class NewStandaloneStructurePage extends BasePageLiability{
    private final By issuer =By.xpath("//select[@id='asset_entity_select']//following-sibling::div/div[1]");
    private final By currency =By.xpath("//select[@id='asset_currency_select']//following-sibling::div/div[1]");
    private final By arranger=By.xpath("//select[@id='ncd_standalone_arranger_id']/following-sibling::div/div[1]");
    private final By externalID= By.id("ncd_standalone_external_id");
    private final By ledgerId=By.id("ncd_standalone_ledger_id");
    private final By isin=By.id("ncd_standalone_isin");
    private final By maturityDate=By.id("ncd_standalone_maturity_date");
    private final By securedType=By.xpath("//select[@id='ncd_standalone_secured_type']/following-sibling::div/div[1]");
    private final By redeemableType=By.xpath("//select[@id='ncd_standalone_redeemable_type']/following-sibling::div/div[1]");
    private  final By zeroCoupon =By.xpath("//select[@id='ncd_standalone_zero_coupon_type']/following-sibling::div/div[1]");
    private final By primaryCounterparty =By.xpath("//select[@id='asset_counterparty_select']/following-sibling::div/div[1]");
    private final By ratedType=By.xpath("//select[@id='ncd_standalone_rated_type']/following-sibling::div/div[1]");
    private final By listingType=By.xpath("//select[@id='ncd_standalone_listed_type']/following-sibling::div/div[1]");
    private final By cumulativeType=By.xpath("//select[@id='ncd_standalone_cumulative_type']/following-sibling::div/div[1]");
    private final By issueOpenDate =By.id("ncd_standalone_ncd_private_tranche_config_attributes_issue_open_date");
    private final By issueCloseDate=By.id("ncd_standalone_ncd_private_tranche_config_attributes_issue_close_date");
    private final By allotmentDate=By.id("ncd_standalone_ncd_private_tranche_config_attributes_allotment_date");
    private final By issuePrice=By.id("ncd_standalone_ncd_private_tranche_config_attributes_issue_price");
    private final By numOfNCDs=By.id("ncd_standalone_ncd_private_tranche_config_attributes_number_of_units");
    private final By trancheIssueLimit=By.id("ncd_standalone_ncd_private_tranche_config_attributes_tranche_issue_limit");
      private final By overSubscription =By.id("ncd_standalone_ncd_private_tranche_config_attributes_oversubscription");
    private final By daysInYearType=By.xpath("//select[@id='days_in_a_year_type_select']//following-sibling::div/div[1]");
    private final By daysinYearNumber = By.id("days_in_a_year_number");
    private final By primarySecurity=By.xpath("//select[@id='ncd_standalone_ncd_private_tranche_config_attributes_cover_detail_attributes_primary_security']/following-sibling::div/div[1]");
    private final By secondarySecurity = By.xpath("//select[@id='ncd_standalone_ncd_private_tranche_config_attributes_cover_detail_attributes_secondary_security']/following-sibling::div/div[1]");
    private final By personalGuarantee =By.xpath("//select[@id='ncd_standalone_ncd_private_tranche_config_attributes_cover_detail_attributes_personal_guarantee']/following-sibling::div/div[1]");
    private final By corporateGuarantee =By.xpath("//select[@id='ncd_standalone_ncd_private_tranche_config_attributes_cover_detail_attributes_corporate_guarantee']/following-sibling::div/div[1]");
    private final By primarySecurityValue=By.id("ncd_standalone_ncd_private_tranche_config_attributes_cover_detail_attributes_primary_security_value");
    private final By secondarySecurityValue=By.id("ncd_standalone_ncd_private_tranche_config_attributes_cover_detail_attributes_secondary_security_value");
    private final By personalGuaranteeValue =By.id("ncd_standalone_ncd_private_tranche_config_attributes_cover_detail_attributes_personal_guarantee_value");
    private final By corporateGuaranteeValue =By.id("ncd_standalone_ncd_private_tranche_config_attributes_cover_detail_attributes_corporate_guarantee_value");
    private final By registar=By.xpath("//select[@id='ncd_standalone_ncd_private_tranche_config_attributes_registrar_id']/following-sibling::div/div[1]");
    private final By trustee=By.xpath("//select[@id='ncd_standalone_ncd_private_tranche_config_attributes_trustee_id']/following-sibling::div/div[1]");
    private final By depository=By.xpath("//select[@id='ncd_standalone_ncd_private_tranche_config_attributes_depository_ids']/following-sibling::div/div[1]");
    private final By leadManager=By.xpath("//select[@id='ncd_standalone_ncd_private_tranche_config_attributes_lead_manager_ids']/following-sibling::div/div[1]");
    private final By operatingAccount=By.xpath("//select[@id='liability_bank_account_select']/following-sibling::div/div[1]");
    private final By settlementBankAccount=By.xpath("//select[@id='ncd_standalone_ncd_private_tranche_config_attributes_settlement_bank_account_ids']/following-sibling::div/div[1]");
    private final By newInterestSlab = By.xpath("//a[text()='New Effective Interest Slab']");
    private final By interestType = By.xpath("//label[text()='Interest Type *']//parent::div/div/div[1]");
    private final By interestBenchmark = By.xpath("//label[text()='Interest Benchmark *']//parent::div/div/div[1]");
    private final By interestSpread = By.xpath("(//input[@data-inputmask-digits='4'])[1]");
    private final By newTDS = By.xpath("//a[text()='New TDS Slab']");
    private final By TDS = By.xpath("//label[text()='TDS *']//parent:: div/input");
    private final By btn_create=By.xpath("//input[@type='submit']");


    public NewStandaloneStructurePage selectIssuer(String isssuer){
        scrollIntoView(issuer);
        clickk(issuer, WaitStrategy.CLICKABLE, "issuer");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, isssuer);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, isssuer);
        return this;
    }
    public NewStandaloneStructurePage selectCurrency(String Currency){
        scrollIntoView(currency);
        clickk(currency, WaitStrategy.CLICKABLE, "Currency");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, Currency);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, Currency);
        return this;
    }
    public NewStandaloneStructurePage selectArranger(String text) {
        jsClick(arranger, WaitStrategy.CLICKABLE, "Arranger");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewStandaloneStructurePage enterExternalID(int count) {
        String randomExternalID = generateRandomID(count,"NCD");
        sendText(externalID, String.valueOf(randomExternalID), WaitStrategy.PRESENCE, "External ID");
        return this;
    }
    public NewStandaloneStructurePage enterLedgerID(int count) {
        String randomID = generateRandomID(count,"NCD");
        sendText(ledgerId, String.valueOf(randomID), WaitStrategy.PRESENCE, "ledger ID");
        return this;
    }
    public NewStandaloneStructurePage enterISIN() {
        String randomISIN =generateRandomISIN();
        sendText(isin, String.valueOf(randomISIN), WaitStrategy.PRESENCE, "ISIN");
        return this;
    }
    public NewStandaloneStructurePage enterMaturityDate(String date){
        clearDate(maturityDate).
                sendText(maturityDate,date,WaitStrategy.PRESENCE,"maturity date");
        return this;
    }
    public NewStandaloneStructurePage selectSecuredType(String text) {
        jsClick(securedType, WaitStrategy.CLICKABLE, "secured type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewStandaloneStructurePage selectRedeemableType(String text) {
        jsClick(redeemableType, WaitStrategy.CLICKABLE, "Redeemable type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewStandaloneStructurePage selectZeroCoupon(String text) {
        jsClick(zeroCoupon, WaitStrategy.CLICKABLE, "Zero coupon");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewStandaloneStructurePage selectPrimaryCounterparty(String text) {
        jsClick(primaryCounterparty, WaitStrategy.CLICKABLE, "Primary counterparty");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewStandaloneStructurePage selectRatedType(String text) {
        jsClick(ratedType, WaitStrategy.CLICKABLE, "Rated Type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewStandaloneStructurePage selectListingType(String text) {
        jsClick(listingType, WaitStrategy.CLICKABLE, "Listing Type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewStandaloneStructurePage selectCumulativeType(String text) {
        jsClick(cumulativeType, WaitStrategy.CLICKABLE, "Cumulative Type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewStandaloneStructurePage enterIssueOpenDate(String date){
        clearDate(issueOpenDate).
                sendText(issueOpenDate,date,WaitStrategy.PRESENCE,"Issue Open date");
        return this;
    }
    public NewStandaloneStructurePage enterIssueCloseDate(String date){
        clearDate(issueCloseDate).
                sendText(issueCloseDate,date,WaitStrategy.PRESENCE,"Issue Open date");
        return this;
    }
    public NewStandaloneStructurePage enterAllotmentDate(String date){
        clearDate(allotmentDate).
                sendText(allotmentDate,date,WaitStrategy.PRESENCE,"Allotment date");
        return this;
    }

    public NewStandaloneStructurePage enterIssuePrice(String value) {
        clickk(issuePrice,WaitStrategy.CLICKABLE,"Issue Price");
        sendText(issuePrice,value, WaitStrategy.PRESENCE, "Issue Price");
        return this;
    }
    public NewStandaloneStructurePage enterNumberOfUnits(String value) {
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        clickk(numOfNCDs,WaitStrategy.CLICKABLE,"Number of NCDs");
        sendText(numOfNCDs,value, WaitStrategy.PRESENCE, "Number of NCDs");
        return this;
    }
    public NewStandaloneStructurePage enterTrancheIssueLimit(String value) {
        clickk(trancheIssueLimit,WaitStrategy.CLICKABLE,"Tranche issue limit");
        sendText(trancheIssueLimit,value, WaitStrategy.PRESENCE, "Tranche issue limit");
        return this;
    }

    public NewStandaloneStructurePage enterOverSubscription(String value) {
        clickk(overSubscription,WaitStrategy.CLICKABLE,"OverSubscription");
        sendText(overSubscription,value, WaitStrategy.PRESENCE, "OverSubscription");
        return this;
    }

    public NewStandaloneStructurePage selectDaysInYeartype(String text) {
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
    public NewStandaloneStructurePage primarySecurityDetails(){
        HashMap<String,String> map= coverDetails();
        for(Map.Entry<String,String> entry: map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            clickk(primarySecurity, WaitStrategy.CLICKABLE, "primary security");
            actionSendkeys(key);
            scrollIntoView(primarySecurity);
            clickk(primarySecurityValue, WaitStrategy.CLICKABLE, "primary security value textbox");
            actionSendkeys(value);
            for(int i=0;i<4;i++){
                Uninterruptibles.sleepUninterruptibly(1,TimeUnit.SECONDS);
                DriverManager.getDriver().findElement(primarySecurityValue).sendKeys(Keys.DELETE);
            }
            Uninterruptibles.sleepUninterruptibly(10,TimeUnit.SECONDS);
        }
        return this;
    }

    public NewStandaloneStructurePage secondarySecurityDetails(){
        HashMap<String,String> map= coverDetails();
        for(Map.Entry<String,String> entry: map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            scrollIntoView(secondarySecurity);
            clickk(secondarySecurity, WaitStrategy.CLICKABLE, "secondary security");
            actionSendkeys(key);
            clickk(secondarySecurityValue, WaitStrategy.CLICKABLE, "secondary security value textbox");
            actionSendkeys(value);
            for(int i=0;i<4;i++){
                Uninterruptibles.sleepUninterruptibly(1,TimeUnit.SECONDS);
                DriverManager.getDriver().findElement(secondarySecurityValue).sendKeys(Keys.BACK_SPACE);
            }
            Uninterruptibles.sleepUninterruptibly(10,TimeUnit.SECONDS);
        }
        return this;
    }
    public NewStandaloneStructurePage personalGuaranteeDetails(){
        HashMap<String,String> map= coverDetails();
        for(Map.Entry<String,String> entry: map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            clickk(personalGuarantee, WaitStrategy.CLICKABLE, "personal guarantee");
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
            actionSendkeys(key);
            clickk(personalGuaranteeValue, WaitStrategy.CLICKABLE, "personalGuarantee textbox");
            actionSendkeys(value);
            for(int i=0;i<4;i++){
                Uninterruptibles.sleepUninterruptibly(1,TimeUnit.SECONDS);
                DriverManager.getDriver().findElement(personalGuaranteeValue).sendKeys(Keys.BACK_SPACE);
            }
            Uninterruptibles.sleepUninterruptibly(10,TimeUnit.SECONDS);
        }
        return this;
    }
    public NewStandaloneStructurePage corporateGuaranteeDetails( ){
        HashMap<String,String> map= coverDetails();
        for(Map.Entry<String,String> entry: map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            clickk(corporateGuarantee, WaitStrategy.CLICKABLE, "corporate guarantee");
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
            actionSendkeys(key);
            clickk(corporateGuaranteeValue, WaitStrategy.CLICKABLE, "corporate guarantee textbox");
            actionSendkeys(value);
            for(int i=0;i<4;i++){
                Uninterruptibles.sleepUninterruptibly(1,TimeUnit.SECONDS);
                DriverManager.getDriver().findElement(corporateGuaranteeValue).sendKeys(Keys.BACK_SPACE);
            }
            Uninterruptibles.sleepUninterruptibly(10,TimeUnit.SECONDS);
        }
        return this;
    }
    public NewStandaloneStructurePage selectRegistrar(String text) {
        jsClick(registar, WaitStrategy.CLICKABLE, "Registar");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewStandaloneStructurePage selectTrustee(String text) {
        jsClick(trustee, WaitStrategy.CLICKABLE, "trustee");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewStandaloneStructurePage selectDepositories(String text) {
        jsClick(depository, WaitStrategy.CLICKABLE, "Depository");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewStandaloneStructurePage selectLeadManager(String text) {
        jsClick(leadManager, WaitStrategy.CLICKABLE, "Lead Manager");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewStandaloneStructurePage selectOperatingAccount(String text) {
        jsClick(operatingAccount, WaitStrategy.CLICKABLE, "Operating Account");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewStandaloneStructurePage selectSettlementBankAcnt(String text) {
        jsClick(settlementBankAccount, WaitStrategy.CLICKABLE, "Settlement Bank Account");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewStandaloneStructurePage clickNewIrSlab() {
        clickk(newInterestSlab, WaitStrategy.CLICKABLE, "new IR slab");
        return this;
    }

    public NewStandaloneStructurePage selectIRType(String text) {
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


    public NewStandaloneStructurePage enterSpread(String value) {
        scrollIntoView(interestSpread);
        clickk(interestSpread, WaitStrategy.CLICKABLE, "Interest Spread");
        sendText(interestSpread, value, WaitStrategy.VISIBLE, "Interest Spread");
        return this;
    }
    public NewStandaloneStructurePage clickNewTDS() {
        jsClick(newTDS, WaitStrategy.CLICKABLE, "New TDS");
        return this;
    }

    public NewStandaloneStructurePage enterTDS(String tds) {
        clickk(TDS, WaitStrategy.CLICKABLE, "TDS");
        sendText(TDS,tds,WaitStrategy.PRESENCE,"tds");

        return this;
    }
    public StandaloneStructurePage clickCreate(){
        clickk(btn_create,WaitStrategy.CLICKABLE,"Create button");
        return new StandaloneStructurePage();
    }
}
