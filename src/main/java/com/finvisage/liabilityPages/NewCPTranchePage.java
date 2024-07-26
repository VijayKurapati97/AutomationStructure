package com.finvisage.liabilityPages;

import com.finvisage.enums.WaitStrategy;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class NewCPTranchePage extends BasePageLiability{
    private final By externalID= By.id("commercial_paper_tranche_external_id");
    private final By ledgerId=By.id("commercial_paper_tranche_ledger_id");
    private final By dealDate =By.id("commercial_paper_tranche_deal_date");
    private final By valueDate=By.id("commercial_paper_tranche_value_date");
    private final By numOfUnits=By.id("commercial_paper_tranche_total_number_of_units");
    private final By discountRate=By.id("commercial_paper_tranche_discount_rate");
    private final By investmentAmount=By.id("commercial_paper_tranche_investment_amount");
    private final By initialPaymentDate=By.id("commercial_paper_tranche_initial_payment_date");
    private final By finalPaymentDate=By.id("commercial_paper_tranche_final_payment_date");
    private final By primarySecurity=By.xpath("//select[@id='commercial_paper_tranche_cover_detail_attributes_primary_security']/following-sibling::div/div[1]");
    private final By secondarySecurity = By.xpath("//select[@id='commercial_paper_tranche_cover_detail_attributes_secondary_security']/following-sibling::div/div[1]");
    private final By personalGuarantee =By.xpath("//select[@id='commercial_paper_tranche_cover_detail_attributes_personal_guarantee']/following-sibling::div/div[1]");
    private final By corporateGuarantee =By.xpath("//select[@id='commercial_paper_tranche_cover_detail_attributes_corporate_guarantee']/following-sibling::div/div[1]");
    private final By primarySecurityValue=By.xpath("//input[@id='commercial_paper_tranche_cover_detail_attributes_primary_security_value']");
    private final By secondarySecurityValue=By.id("commercial_paper_tranche_cover_detail_attributes_secondary_security_value");
    private final By personalGuaranteeValue =By.id("commercial_paper_tranche_cover_detail_attributes_personal_guarantee_value");
    private final By corporateGuaranteeValue =By.id("commercial_paper_tranche_cover_detail_attributes_corporate_guarantee_value");
    private final By notes=By.id("commercial_paper_tranche_additional_info");
    private final By trustee =By.id("commercial_paper_tranche_security_trustee");
    private final By btn_create=By.xpath("//input[@type='submit']");

    public NewCPTranchePage enterExternalID(int count) {
        String randomExternalID = generateRandomID(count,"NCD");
        sendText(externalID, String.valueOf(randomExternalID), WaitStrategy.PRESENCE, "External ID");
        return this;
    }
    public NewCPTranchePage enterLedgerID(int count) {
        String randomID = generateRandomID(count,"NCD");
        sendText(ledgerId, String.valueOf(randomID), WaitStrategy.PRESENCE, "ledger ID");
        return this;
    }
    public NewCPTranchePage enterDealDate(String date){
        clearDate(dealDate).
                sendText(dealDate,date,WaitStrategy.PRESENCE,"deal date");
        return this;
    }
    public NewCPTranchePage enterValueDate(String date){
        clearDate(valueDate).
                sendText(valueDate,date,WaitStrategy.PRESENCE,"value date");
        return this;
    }

    public NewCPTranchePage enterNumberOfUnits(String value) {
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        clickk(numOfUnits,WaitStrategy.CLICKABLE,"Number of Units");
        sendText(numOfUnits,value, WaitStrategy.PRESENCE, "Number of Units");
        return this;
    }
    public NewCPTranchePage enterInvestmentAmount(String value){
        sendText(investmentAmount,value,WaitStrategy.PRESENCE,"Investment amount");
        return this;
    }
    public NewCPTranchePage enterDiscountRate(String value) {
        doubleClick(discountRate);
       actionSendkeys(value);
        return this;
    }
    public NewCPTranchePage enterInitialPaymentDate(String date){
        clearDate(initialPaymentDate).
                sendText(initialPaymentDate,date,WaitStrategy.PRESENCE,"initial payment date");
        return this;
    }
    public NewCPTranchePage enterFinalPaymentDate(String date){
        clearDate(finalPaymentDate).
                sendText(finalPaymentDate,date,WaitStrategy.PRESENCE,"final payment date");
        return this;
    }
    public NewCPTranchePage primarySecurityDetails(){
        HashMap<String,String> map= coverDetails();
        for(Map.Entry<String,String> entry: map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            jsClick(primarySecurity, WaitStrategy.CLICKABLE, "primary security");
            actionSendkeys(key);
            Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
            doubleClick(primarySecurityValue);
            actionSendkeys(value);
            Uninterruptibles.sleepUninterruptibly(1,TimeUnit.SECONDS);
        }
        return this;
    }

    public NewCPTranchePage secondarySecurityDetails(){
        HashMap<String,String> map= coverDetails();
        for(Map.Entry<String,String> entry: map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            jsClick(secondarySecurity, WaitStrategy.CLICKABLE, "secondary security");
            actionSendkeys(key);
            Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
            doubleClick(secondarySecurityValue);
            actionSendkeys(value);
            Uninterruptibles.sleepUninterruptibly(1,TimeUnit.SECONDS);
        }
        return this;
    }
    public NewCPTranchePage personalGuaranteeDetails(){
        HashMap<String,String> map= coverDetails();
        for(Map.Entry<String,String> entry: map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            clickk(personalGuarantee, WaitStrategy.CLICKABLE, "personal guarantee");
            Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
            actionSendkeys(key);
            doubleClick(personalGuaranteeValue);
            actionSendkeys(value);
            Uninterruptibles.sleepUninterruptibly(1,TimeUnit.SECONDS);
        }
        return this;
    }
    public NewCPTranchePage corporateGuaranteeDetails( ){
        HashMap<String,String> map= coverDetails();
        for(Map.Entry<String,String> entry: map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            clickk(corporateGuarantee, WaitStrategy.CLICKABLE, "corporate guarantee");
            Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
            actionSendkeys(key);
            doubleClick(corporateGuaranteeValue);
            actionSendkeys(value);
            Uninterruptibles.sleepUninterruptibly(1,TimeUnit.SECONDS);
        }
        return this;
    }
    public NewCPTranchePage enterTrustee(String value){
        sendText(trustee,value,WaitStrategy.PRESENCE,"security trustee");
        return this;
    }
    public NewCPTranchePage enterAdditionalInfo(String value){
        sendText(notes,value,WaitStrategy.PRESENCE,"Additional Info");
        return this;
    }
    public CPTranchePage clickCreate(){
        clickk(btn_create,WaitStrategy.CLICKABLE,"Create button");
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        return new CPTranchePage();
    }
}
