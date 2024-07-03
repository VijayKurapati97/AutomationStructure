package com.finvisage.liabilityPages;

import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.finvisage.utils.XpathUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class NewLoanFacilityPage extends BasePageLiability {
    private final By externalID = By.id("loan_facility_external_id");
    private final By ledgerID = By.id("loan_facility_ledger_id");
    private final By loanFacilityType = By.xpath("//select[@id='loan_facility_loan_facility_type']/following-sibling::div/div[1]");
    private final By rocNumber = By.id("loan_facility_roc_reference_number");
    private final By entity = By.xpath("//select[@id='liability_entity_select']/following-sibling::div/div[1]");
    private final By counterparty = By.xpath("//select[@id='liability_counterparty_select']/following-sibling::div/div[1]");
    private final By sanctionDate = By.id("loan_facility_start_date");
    private final By endDate = By.id("loan_facility_end_date");
    private final By loanFacilityAvailableTill =By.id("loan_facility_available_till");
    private final By facilityAmount=By.id("loan_facility_umbrella_amount");
    private final By covenantDefaultIR=By.xpath("//input[@id='loan_facility_covenant_default_interest_rate']");
    private final By paymentDefaultIR=By.xpath("//input[@id='loan_facility_payment_default_interest_rate']");
    private final By arranger=By.xpath("//select[@id='loan_facility_arranger_id']/following-sibling::div/div[1]");
    private final By button_SaveAsDraft =By.id("save-as-draft");
    private final By primarySecurity=By.xpath("//select[@id='loan_facility_cover_detail_attributes_primary_security']/following-sibling::div/div[1]");
    private final By secondarySecurity = By.xpath("//select[@id='loan_facility_cover_detail_attributes_secondary_security']/following-sibling::div/div[1]");
    private final By personalGuarantee=By.xpath("//select[@id='loan_facility_cover_detail_attributes_personal_guarantee']/following-sibling::div/div[1]");
    private final By corporateGuarantee=By.xpath("//select[@id='loan_facility_cover_detail_attributes_corporate_guarantee']/following-sibling::div/div[1]");
    private final By primarySecurityValue=By.id("loan_facility_cover_detail_attributes_primary_security_value");
    private final By secondarySecurityValue=By.id("loan_facility_cover_detail_attributes_secondary_security_value");
    private final By personalGuaranteeValue=By.id("loan_facility_cover_detail_attributes_personal_guarantee_value");
    private final By corporateGuaranteeValue=By.id("loan_facility_cover_detail_attributes_corporate_guarantee_value");
    private final By trustee = By.id("loan_facility_security_trustee");
    private final By additionalInfo=By.id("loan_facility_security_information");
    private final By btn_create =By.xpath("//input[@type='submit']");

    public NewLoanFacilityPage selectLoanFacilityType(String text) {
        jsClick(loanFacilityType, WaitStrategy.CLICKABLE, "LoanFacility type");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String lfType = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(lfType, text);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewLoanFacilityPage enterExternalID(int count) {
        String randomExternalID = generateRandomID(count,"LoanFacility");
        sendText(externalID, String.valueOf(randomExternalID), WaitStrategy.PRESENCE, "External ID");
        return this;
    }
    public NewLoanFacilityPage enterExternalID(String id) {
        sendText(externalID,id, WaitStrategy.PRESENCE, "External ID");
        return this;
    }

    public NewLoanFacilityPage enterLedgerID(int count) {
        String randomID = generateRandomID(count,"LoanFacility");
        sendText(ledgerID, String.valueOf(randomID), WaitStrategy.PRESENCE, "ledger ID");
        return this;
    }

    public NewLoanFacilityPage enterROC(int count) {
        String randomID = generateRandomID(count,"LoanFacility");
        sendText(rocNumber, String.valueOf(randomID), WaitStrategy.PRESENCE, "ROC Number");
        return this;
    }

    public NewLoanFacilityPage selectEntity(String text) {
        clickk(entity, WaitStrategy.CLICKABLE, "LoanFacility type");
        String lfType = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(lfType, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewLoanFacilityPage selectCounterparty(String text) {
        jsClick(counterparty, WaitStrategy.CLICKABLE, "LoanFacility type");
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        String lfType = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(lfType, text);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewLoanFacilityPage enterSanctionDate(String text){
        clearDate(sanctionDate).
                sendText(sanctionDate,text,WaitStrategy.PRESENCE,"sanction date");
        return this;
    }
    public NewLoanFacilityPage enterEndDate(String text){
        clearDate(endDate).sendText(endDate,text,WaitStrategy.PRESENCE,"end date");
        return this;
    }
    public NewLoanFacilityPage enterLFAvailableTill(String text){
        clearDate(loanFacilityAvailableTill).sendText(loanFacilityAvailableTill,text,WaitStrategy.PRESENCE,"Loan facility available till");
        return this;
    }
    public NewLoanFacilityPage enterFacilityAmount(String text){
        clickk(facilityAmount,WaitStrategy.CLICKABLE,"Facility Amount textbox");
        sendText(facilityAmount,text,WaitStrategy.PRESENCE,"Facility Amount");
        return this;
    }
    public NewLoanFacilityPage covenantDefaultIR(String text){
        jsClick(covenantDefaultIR,WaitStrategy.CLICKABLE,"Covenant Default IR");
        sendText(covenantDefaultIR,text,WaitStrategy.PRESENCE,"Covenant Default Interest Rate %");
        return this;
    }
    public NewLoanFacilityPage paymentDefaultIR(String text){
        jsClick(paymentDefaultIR,WaitStrategy.CLICKABLE,"payment Default IR");
        sendText(paymentDefaultIR,text,WaitStrategy.PRESENCE,"payment Default Interest Rate %");
        return this;
    }
    public NewLoanFacilityPage selectArranger(String text) {
        jsClick(arranger, WaitStrategy.CLICKABLE, "Arranger");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public LoanFacilityBlotterPage clickSaveAsDraft(){
        doubleClick(button_SaveAsDraft);
        Alert al = DriverManager.getDriver().switchTo().alert();
        al.accept();
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        return new LoanFacilityBlotterPage();
    }
    public String generateExternalID(){
      return generateRandomID(7,"LoanFacility");
    }
    public NewLoanFacilityPage primarySecurityDetails(){
        HashMap<String,String> map=coverdetails();
        for(Map.Entry<String,String> entry: map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            jsClick(primarySecurity, WaitStrategy.CLICKABLE, "primary security");
            actionSendkeys(key);
            Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
            doubleClick(primarySecurityValue);
            actionSendkeys(value);

            Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        }
        return this;
    }

    public NewLoanFacilityPage secondarySecurityDetails(){
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
    public NewLoanFacilityPage personalGuaranteeDetails(){
        HashMap<String,String> map=coverdetails();
        for(Map.Entry<String,String> entry: map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            jsClick(personalGuarantee, WaitStrategy.CLICKABLE, "personal guarantee");
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
            actionSendkeys(key);
            doubleClick(personalGuaranteeValue);
            actionSendkeys(value);
            Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        }
        return this;
    }
    public NewLoanFacilityPage corporateGuaranteeDetails( ){
        HashMap<String,String> map=coverdetails();
        for(Map.Entry<String,String> entry: map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            jsClick(corporateGuarantee, WaitStrategy.CLICKABLE, "corporate guarantee");
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
            actionSendkeys(key);
            doubleClick(corporateGuaranteeValue);
            actionSendkeys(value);
            Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        }
        return this;
    }

    public NewLoanFacilityPage enterTrustee(String text){
        scrollIntoView(trustee);
        sendText(trustee,text,WaitStrategy.PRESENCE,"Trustee");
        return this;
    }
    public NewLoanFacilityPage enterAdditionalInfo(String text){
        sendText(additionalInfo,text,WaitStrategy.PRESENCE,"Additional Info");
        return this;
    }
    public LoanFacilityPage clickOnCreate(){
        scrollIntoView(btn_create);
        clickk(btn_create,WaitStrategy.CLICKABLE,"Create button");
        return new LoanFacilityPage();
    }
    public void clickOnUpdate(){
        scrollIntoView(btn_create);
        clickk(btn_create,WaitStrategy.CLICKABLE,"Update button");
        new LoanFacilityPage();
    }
}
