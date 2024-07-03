package com.finvisage.liabilityPages;

import com.finvisage.enums.WaitStrategy;
import com.finvisage.utils.XpathUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class NewSDDTranchePage extends BasePageLiability{
    private final By externalID= By.id("ncd_sdd_tranche_external_id");
    private final By ledgerId=By.id("ncd_sdd_tranche_ledger_id");
    private final By issueOpenDate =By.id("ncd_sdd_tranche_ncd_private_tranche_config_attributes_issue_open_date");
    private final By issueCloseDate=By.id("ncd_sdd_tranche_ncd_private_tranche_config_attributes_issue_close_date");
    private final By allotmentDate=By.id("ncd_sdd_tranche_ncd_private_tranche_config_attributes_allotment_date");
    private final By issuePrice=By.id("ncd_sdd_tranche_ncd_private_tranche_config_attributes_issue_price");
    private final By numOfUnits=By.id("ncd_sdd_tranche_ncd_private_tranche_config_attributes_number_of_units");
    private final By trancheIssueLimit=By.xpath("//input[@id='ncd_sdd_tranche_ncd_private_tranche_config_attributes_tranche_issue_limit']");
    private final By overSubscription =By.id("ncd_sdd_tranche_ncd_private_tranche_config_attributes_oversubscription");
    private final By daysInYearType=By.xpath("//select[@id='days_in_a_year_type_select']//following-sibling::div/div[1]");
    private final By daysinYearNumber = By.id("days_in_a_year_number");
    private final By primarySecurity=By.xpath("//select[@id='ncd_sdd_tranche_ncd_private_tranche_config_attributes_cover_detail_attributes_primary_security']/following-sibling::div/div[1]");
    private final By secondarySecurity = By.xpath("//select[@id='ncd_sdd_tranche_ncd_private_tranche_config_attributes_cover_detail_attributes_secondary_security']/following-sibling::div/div[1]");
    private final By personalGuarantee =By.xpath("//select[@id='ncd_sdd_tranche_ncd_private_tranche_config_attributes_cover_detail_attributes_personal_guarantee']/following-sibling::div/div[1]");
    private final By corporateGuarantee =By.xpath("//select[@id='ncd_sdd_tranche_ncd_private_tranche_config_attributes_cover_detail_attributes_corporate_guarantee']/following-sibling::div/div[1]");
    private final By primarySecurityValue=By.xpath("//input[@id='ncd_sdd_tranche_ncd_private_tranche_config_attributes_cover_detail_attributes_primary_security_value']//parent::div");
    private final By secondarySecurityValue=By.id("ncd_sdd_tranche_ncd_private_tranche_config_attributes_cover_detail_attributes_secondary_security_value");
    private final By personalGuaranteeValue =By.id("ncd_sdd_tranche_ncd_private_tranche_config_attributes_cover_detail_attributes_personal_guarantee_value");
    private final By corporateGuaranteeValue =By.id("ncd_sdd_tranche_ncd_private_tranche_config_attributes_cover_detail_attributes_corporate_guarantee_value");
    private final By register =By.xpath("//select[@id='ncd_sdd_tranche_ncd_private_tranche_config_attributes_registrar_id']/following-sibling::div/div[1]");
    private final By trustee=By.xpath("//select[@id='ncd_sdd_tranche_ncd_private_tranche_config_attributes_trustee_id']/following-sibling::div/div[1]");
    private final By depository=By.xpath("//select[@id='ncd_sdd_tranche_ncd_private_tranche_config_attributes_depository_ids']/following-sibling::div/div[1]");
    private final By leadManager=By.xpath("//select[@id='ncd_sdd_tranche_ncd_private_tranche_config_attributes_lead_manager_ids']/following-sibling::div/div[1]");
    private final By operatingAccount=By.xpath("//select[@id='liability_bank_account_select']/following-sibling::div/div[1]");
    private final By settlementBankAccount=By.xpath("//select[@id='ncd_sdd_tranche_ncd_private_tranche_config_attributes_settlement_bank_account_ids']/following-sibling::div/div[1]");
    private final By newInterestSlab = By.xpath("//a[text()='New Effective Interest Slab']");
    private final By interestType = By.xpath("//label[text()='Interest Type *']//parent::div/div/div[1]");
    private final By interestBenchmark = By.xpath("//label[text()='Interest Benchmark *']//parent::div/div/div[1]");
    private final By interestSpread = By.xpath("(//input[@data-inputmask-digits='4'])[1]");
    private final By newTDS = By.xpath("//a[text()='New TDS Slab']");
    private final By TDS = By.xpath("//label[text()='TDS *']//parent:: div/input");
    private final By trancheMaturityDate =By.id("ncd_sdd_tranche_ncd_private_transaction_config_attributes_maturity_date");
    private final By btn_create=By.xpath("//input[@type='submit']");


    public NewSDDTranchePage enterExternalID(int count) {
        String randomExternalID = generateRandomID(count,"NCD");
        sendText(externalID, String.valueOf(randomExternalID), WaitStrategy.PRESENCE, "External ID");
        return this;
    }
    public NewSDDTranchePage enterLedgerID(int count) {
        String randomID = generateRandomID(count,"NCD");
        sendText(ledgerId, String.valueOf(randomID), WaitStrategy.PRESENCE, "ledger ID");
        return this;
    }
    public NewSDDTranchePage enterIssueOpenDate(String date){
        clearDate(issueOpenDate).
                sendText(issueOpenDate,date,WaitStrategy.PRESENCE,"Issue Open date");
        return this;
    }
    public NewSDDTranchePage enterIssueCloseDate(String date){
        clearDate(issueCloseDate).
                sendText(issueCloseDate,date,WaitStrategy.PRESENCE,"Issue Open date");
        return this;
    }
    public NewSDDTranchePage enterAllotmentDate(String date){
        clearDate(allotmentDate).
                sendText(allotmentDate,date,WaitStrategy.PRESENCE,"Allotment date");
        return this;
    }
    public NewSDDTranchePage enterIssuePrice(String value) {
        clickk(issuePrice,WaitStrategy.CLICKABLE,"Issue Price");
        sendText(issuePrice,value, WaitStrategy.PRESENCE, "Issue Price");
        return this;
    }
    public NewSDDTranchePage enterNumberOfUnits(String value) {
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        clickk(numOfUnits,WaitStrategy.CLICKABLE,"Number of Units");
        sendText(numOfUnits,value, WaitStrategy.PRESENCE, "Number of Units");
        return this;
    }
    public NewSDDTranchePage enterTrancheIssueLimit(String value) {
        doubleClick(trancheIssueLimit);
        actionSendkeys(value);
        return this;
    }

    public NewSDDTranchePage enterOverSubscription(String value) {
        doubleClick(overSubscription);
        actionSendkeys(value);
        return this;
    }
    public NewSDDTranchePage selectDaysInYeartype(String text) {
        jsClick(daysInYearType, WaitStrategy.CLICKABLE, "days in a year type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        if (getText(daysInYearType, WaitStrategy.VISIBLE, "Days in a year type").equalsIgnoreCase("Constant Days")) {
            Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
            doubleClick(daysinYearNumber);
            actionSendkeys("365");
        }
        return this;
    }
    public NewSDDTranchePage primarySecurityDetails(){
        HashMap<String,String> map=coverdetails();
        for(Map.Entry<String,String> entry: map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            jsClick(primarySecurity, WaitStrategy.CLICKABLE, "primary security");
            actionSendkeys(key);
            Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
            doubleClick(primarySecurityValue);
           // jsClick(primarySecurityValue, WaitStrategy.CLICKABLE, "primary security value textbox");
            actionSendkeys(value);

            Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        }
        return this;
    }

    public NewSDDTranchePage secondarySecurityDetails(){
        HashMap<String,String> map=coverdetails();
        for(Map.Entry<String,String> entry: map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            jsClick(secondarySecurity, WaitStrategy.CLICKABLE, "secondary security");
            actionSendkeys(key);
            Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
            doubleClick(secondarySecurityValue);
            actionSendkeys(value);

            Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        }
        return this;
    }
    public NewSDDTranchePage personalGuaranteeDetails(){
        HashMap<String,String> map=coverdetails();
        for(Map.Entry<String,String> entry: map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            clickk(personalGuarantee, WaitStrategy.CLICKABLE, "personal guarantee");
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
            actionSendkeys(key);
            doubleClick(personalGuaranteeValue);
            actionSendkeys(value);
            Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        }
        return this;
    }
    public NewSDDTranchePage corporateGuaranteeDetails( ){
        HashMap<String,String> map=coverdetails();
        for(Map.Entry<String,String> entry: map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            clickk(corporateGuarantee, WaitStrategy.CLICKABLE, "corporate guarantee");
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
            actionSendkeys(key);
            doubleClick(corporateGuaranteeValue);
            actionSendkeys(value);
            Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        }
        return this;
    }
    public NewSDDTranchePage selectRegistrar(String text) {
        jsClick(register, WaitStrategy.CLICKABLE, "Registar");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewSDDTranchePage selectTrustee(String text) {
        jsClick(trustee, WaitStrategy.CLICKABLE, "trustee");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewSDDTranchePage selectDepositories(String text) {
        jsClick(depository, WaitStrategy.CLICKABLE, "Depository");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewSDDTranchePage selectLeadManager(String text) {
        jsClick(leadManager, WaitStrategy.CLICKABLE, "Lead Manager");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewSDDTranchePage selectOperatingAccount(String text) {
        jsClick(operatingAccount, WaitStrategy.CLICKABLE, "Operating Account");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewSDDTranchePage selectSettlementBankAcnt(String text) {
        jsClick(settlementBankAccount, WaitStrategy.CLICKABLE, "Settlement Bank Account");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewSDDTranchePage clickNewIrSlab() {
        clickk(newInterestSlab, WaitStrategy.CLICKABLE, "new IR slab");
        return this;
    }

    public NewSDDTranchePage selectIRType(String text) {
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


    public NewSDDTranchePage enterSpread(String value) {
        scrollIntoView(interestSpread);
        clickk(interestSpread, WaitStrategy.CLICKABLE, "Interest Spread");
        sendText(interestSpread, value, WaitStrategy.VISIBLE, "Interest Spread");
        return this;
    }
    public NewSDDTranchePage clickNewTDS() {
        jsClick(newTDS, WaitStrategy.CLICKABLE, "New TDS");
        return this;
    }

    public NewSDDTranchePage enterTDS(String tds) {
        clickk(TDS, WaitStrategy.CLICKABLE, "TDS");
        sendText(TDS,tds,WaitStrategy.PRESENCE,"tds");

        return this;
    }
    public NewSDDTranchePage enterTrancheMaturityDate(String date){
        clearDate(trancheMaturityDate).sendText(trancheMaturityDate,date,WaitStrategy.PRESENCE,"tranche Maturity Date");
        return this;
    }
    public SDDTranchePage clickCreate(){
        clickk(btn_create,WaitStrategy.CLICKABLE,"Create button");
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        return new SDDTranchePage();
    }
}
