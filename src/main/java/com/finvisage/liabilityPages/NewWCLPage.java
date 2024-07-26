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

public class NewWCLPage extends BasePageLiability{
    private final By externalID = By.id("working_capital_loan_external_id");
    private final By ledgerID = By.id("working_capital_loan_ledger_id");
    private final By entity = By.xpath("//select[@id='liability_entity_select']/following-sibling::div/div[1]");
    private final By counterparty = By.xpath("//select[@id='liability_counterparty_select']/following-sibling::div/div[1]");
    private final By sanctionDate = By.id("borrowing_start_date");
    private final By endDate = By.id("borrowing_end_date");
    private final By principalAmount =By.id("working_capital_loan_principal");
    private final By arranger=By.xpath("//select[@id='working_capital_loan_arranger_id']/following-sibling::div/div[1]");
    private final By primarySecurity=By.xpath("//select[@id='working_capital_loan_cover_detail_attributes_primary_security']/following-sibling::div/div[1]");
    private final By secondarySecurity = By.xpath("//select[@id='working_capital_loan_cover_detail_attributes_secondary_security']/following-sibling::div/div[1]");
    private final By personalGuarantee =By.xpath("//select[@id='working_capital_loan_cover_detail_attributes_personal_guarantee']/following-sibling::div/div[1]");
    private final By corporateGuarantee =By.xpath("//select[@id='working_capital_loan_cover_detail_attributes_corporate_guarantee']/following-sibling::div/div[1]");
    private final By primarySecurityValue=By.id("working_capital_loan_cover_detail_attributes_primary_security_value");
    private final By secondarySecurityValue=By.id("working_capital_loan_cover_detail_attributes_secondary_security_value");
    private final By personalGuaranteeValue =By.id("working_capital_loan_cover_detail_attributes_personal_guarantee_value");
    private final By corporateGuaranteeValue =By.id("working_capital_loan_cover_detail_attributes_corporate_guarantee_value");
    private final By trustee = By.id("working_capital_loan_security_trustee");
    private final By additionalInfo=By.id("working_capital_loan_security_information");
    private final By btn_create =By.xpath("//input[@type='submit']");



    public NewWCLPage enterExternalID(int count) {
        String randomExternalID = generateRandomID(count,"WCL");
        sendText(externalID, String.valueOf(randomExternalID), WaitStrategy.PRESENCE, "External ID");
        return this;
    }

    public NewWCLPage enterLedgerID(int count) {
        String randomID = generateRandomID(count,"WCL");
        sendText(ledgerID, String.valueOf(randomID), WaitStrategy.PRESENCE, "ledger ID");
        return this;
    }


    public NewWCLPage selectEntity(String text) {
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        clickk(entity, WaitStrategy.CLICKABLE, "Entity");
        String lfType = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(lfType, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewWCLPage selectCounterparty(String text) {
        jsClick(counterparty, WaitStrategy.CLICKABLE, "counterparty");
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        String lfType = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(lfType, text);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewWCLPage enterSanctionDate(String text){
        clearDate(sanctionDate).
                sendText(sanctionDate,text,WaitStrategy.PRESENCE,"sanction date");
        return this;
    }
    public NewWCLPage enterEndDate(String text){
        clearDate(endDate).sendText(endDate,text,WaitStrategy.PRESENCE,"end date");
        return this;
    }
    public NewWCLPage enterFacilityAmount(String text){
        clickk(principalAmount,WaitStrategy.CLICKABLE,"Facility Amount textbox");
        sendText(principalAmount,text,WaitStrategy.PRESENCE,"Facility Amount");
        return this;
    }

    public NewWCLPage selectArranger(String text) {
        jsClick(arranger, WaitStrategy.CLICKABLE, "Arranger");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewWCLPage primarySecurityDetails(){
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

    public NewWCLPage secondarySecurityDetails(){
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
    public NewWCLPage personalGuaranteeDetails(){
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
    public NewWCLPage corporateGuaranteeDetails( ){
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

    public NewWCLPage enterTrustee(String text){
        scrollIntoView(trustee);
        sendText(trustee,text,WaitStrategy.PRESENCE,"Trustee");
        return this;
    }
    public NewWCLPage enterAdditionalInfo(String text){
        sendText(additionalInfo,text,WaitStrategy.PRESENCE,"Additional Info");
        return this;
    }
    public WCLPage clickOnCreate(){
        scrollIntoView(btn_create);
        clickk(btn_create,WaitStrategy.CLICKABLE,"Create button");
        return new WCLPage();
    }
}
