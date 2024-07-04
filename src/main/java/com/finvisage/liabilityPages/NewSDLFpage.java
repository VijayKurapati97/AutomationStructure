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

public class NewSDLFpage extends BasePageLiability{
    private final By externalID = By.id("sub_debt_loan_facility_external_id");
    private final By ledgerID = By.id("sub_debt_loan_facility_ledger_id");
    private final By loanFacilityType = By.xpath("//select[@id='sub_debt_loan_facility_loan_facility_type']/following-sibling::div/div[1]");
    private final By rocNumber = By.id("sub_debt_loan_facility_roc_reference_number");
    private final By entity = By.xpath("//select[@id='liability_entity_select']/following-sibling::div/div[1]");
    private final By counterparty = By.xpath("//select[@id='liability_counterparty_select']/following-sibling::div/div[1]");
    private final By sanctionDate = By.id("sub_debt_loan_facility_start_date");
    private final By endDate = By.id("sub_debt_loan_facility_end_date");
    private final By facilityAmount=By.id("sub_debt_loan_facility_umbrella_amount");
    private final By covenantDefaultIR=By.xpath("//input[@id='sub_debt_loan_facility_covenant_default_interest_rate']");
    private final By paymentDefaultIR=By.xpath("//input[@id='sub_debt_loan_facility_payment_default_interest_rate']");
    private final By arranger=By.xpath("//select[@id='sub_debt_loan_facility_arranger_id']/following-sibling::div/div[1]");
    private final By primarySecurity=By.xpath("//select[@id='sub_debt_loan_facility_cover_detail_attributes_primary_security']/following-sibling::div/div[1]");
    private final By secondarySecurity = By.xpath("//select[@id='sub_debt_loan_facility_cover_detail_attributes_secondary_security']/following-sibling::div/div[1]");
    private final By personalGuarantee =By.xpath("//select[@id='sub_debt_loan_facility_cover_detail_attributes_personal_guarantee']/following-sibling::div/div[1]");
    private final By corporateGuarantee =By.xpath("//select[@id='sub_debt_loan_facility_cover_detail_attributes_corporate_guarantee']/following-sibling::div/div[1]");
    private final By primarySecurityValue=By.id("sub_debt_loan_facility_cover_detail_attributes_primary_security_value");
    private final By secondarySecurityValue=By.id("sub_debt_loan_facility_cover_detail_attributes_secondary_security_value");
    private final By personalGuaranteeValue =By.id("sub_debt_loan_facility_cover_detail_attributes_personal_guarantee_value");
    private final By corporateGuaranteeValue =By.id("sub_debt_loan_facility_cover_detail_attributes_corporate_guarantee_value");
    private final By trustee = By.id("sub_debt_loan_facility_security_trustee");
    private final By additionalInfo=By.id("sub_debt_loan_facility_security_information");
    private final By btn_create =By.xpath("//input[@type='submit']");


    public NewSDLFpage selectLoanFacilityType(String text) {
        jsClick(loanFacilityType, WaitStrategy.CLICKABLE, "LoanFacility type");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String lfType = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(lfType, text);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewSDLFpage enterExternalID(int count) {
        String randomExternalID = generateRandomID(count,"SDLF");
        sendText(externalID, String.valueOf(randomExternalID), WaitStrategy.PRESENCE, "External ID");
        return this;
    }

    public NewSDLFpage enterLedgerID(int count) {
        String randomID = generateRandomID(count,"SDLF");
        sendText(ledgerID, String.valueOf(randomID), WaitStrategy.PRESENCE, "ledger ID");
        return this;
    }

    public NewSDLFpage enterROC(int count) {
        String randomID = generateRandomID(count,"SDLF");
        sendText(rocNumber, String.valueOf(randomID), WaitStrategy.PRESENCE, "ROC Number");
        return this;
    }

    public NewSDLFpage selectEntity(String text) {
        clickk(entity, WaitStrategy.CLICKABLE, "LoanFacility type");
        String lfType = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(lfType, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewSDLFpage selectCounterparty(String text) {
        jsClick(counterparty, WaitStrategy.CLICKABLE, "LoanFacility type");
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        String lfType = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(lfType, text);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewSDLFpage enterSanctionDate(String text){
        clearDate(sanctionDate).
                sendText(sanctionDate,text,WaitStrategy.PRESENCE,"sanction date");
        return this;
    }
    public NewSDLFpage enterEndDate(String text){
        clearDate(endDate).sendText(endDate,text,WaitStrategy.PRESENCE,"end date");
        return this;
    }
    public NewSDLFpage enterFacilityAmount(String text){
        clickk(facilityAmount,WaitStrategy.CLICKABLE,"Facility Amount textbox");
        sendText(facilityAmount,text,WaitStrategy.PRESENCE,"Facility Amount");
        return this;
    }
    public NewSDLFpage covenantDefaultIR(String text){
        jsClick(covenantDefaultIR,WaitStrategy.CLICKABLE,"Covenant Default IR");
        sendText(covenantDefaultIR,text,WaitStrategy.PRESENCE,"Covenant Default Interest Rate %");
        return this;
    }
    public NewSDLFpage paymentDefaultIR(String text){
        jsClick(paymentDefaultIR,WaitStrategy.CLICKABLE,"payment Default IR");
        sendText(paymentDefaultIR,text,WaitStrategy.PRESENCE,"payment Default Interest Rate %");
        return this;
    }
    public NewSDLFpage selectArranger(String text) {
        jsClick(arranger, WaitStrategy.CLICKABLE, "Arranger");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewSDLFpage primarySecurityDetails(){
        HashMap<String,String> map=coverdetails();
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

    public NewSDLFpage secondarySecurityDetails(){
        HashMap<String,String> map=coverdetails();
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
    public NewSDLFpage personalGuaranteeDetails(){
        HashMap<String,String> map=coverdetails();
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
    public NewSDLFpage corporateGuaranteeDetails( ){
        HashMap<String,String> map=coverdetails();
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

    public NewSDLFpage enterTrustee(String text){
        scrollIntoView(trustee);
        sendText(trustee,text,WaitStrategy.PRESENCE,"Trustee");
        return this;
    }
    public NewSDLFpage enterAdditionalInfo(String text){
        sendText(additionalInfo,text,WaitStrategy.PRESENCE,"Additional Info");
        return this;
    }
    public LoanFacilityPage clickOnCreate(){
        scrollIntoView(btn_create);
        clickk(btn_create,WaitStrategy.CLICKABLE,"Create button");
        return new LoanFacilityPage();
    }
}
