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

public class NewECBPage extends BasePageLiability{
    private final By externalID = By.id("ecb_external_id");
    private final By ledgerID = By.id("ecb_ledger_id");
    private final By ECBType = By.xpath("//select[@id='ecb_ecb_type']/following-sibling::div/div[1]");
    private final By rocNumber = By.id("ecb_roc_reference_number");
    private final By entity = By.xpath("//select[@id='liability_entity_select']/following-sibling::div/div[1]");
    private final By counterparty = By.xpath("//select[@id='liability_counterparty_select']/following-sibling::div/div[1]");
    private final By sanctionDate = By.id("ecb_start_date");
    private final By endDate = By.id("ecb_end_date");
    private final By ecbAmount =By.id("ecb_umbrella_amount");
    private final By currency = By.xpath("//select[@id='liability_currency_select']//following-sibling::div/div[1]");
    private final By covenantDefaultIR=By.xpath("//input[@id='ecb_covenant_default_interest_rate']");
    private final By paymentDefaultIR=By.xpath("//input[@id='ecb_payment_default_interest_rate']");
    private final By arranger=By.xpath("//select[@id='ecb_arranger_id']/following-sibling::div/div[1]");
    private final By primarySecurity=By.xpath("//select[@id='ecb_cover_detail_attributes_primary_security']/following-sibling::div/div[1]");
    private final By secondarySecurity = By.xpath("//select[@id='ecb_cover_detail_attributes_secondary_security']/following-sibling::div/div[1]");
    private final By personalGuarantee =By.xpath("//select[@id='ecb_cover_detail_attributes_personal_guarantee']/following-sibling::div/div[1]");
    private final By corporateGuarantee =By.xpath("//select[@id='ecb_cover_detail_attributes_corporate_guarantee']/following-sibling::div/div[1]");
    private final By primarySecurityValue=By.id("ecb_cover_detail_attributes_primary_security_value");
    private final By secondarySecurityValue=By.id("ecb_cover_detail_attributes_secondary_security_value");
    private final By personalGuaranteeValue =By.id("ecb_cover_detail_attributes_personal_guarantee_value");
    private final By corporateGuaranteeValue =By.id("ecb_cover_detail_attributes_corporate_guarantee_value");
    private final By trustee = By.id("ecb_security_trustee");
    private final By additionalInfo=By.id("ecb_security_information");
    private final By btn_create =By.xpath("//input[@type='submit']");


    public NewECBPage selectLoanFacilityType(String text) {
        jsClick(ECBType, WaitStrategy.CLICKABLE, "ECB type");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String lfType = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(lfType, text);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewECBPage enterExternalID(int count) {
        String randomExternalID = generateRandomID(count,"ECB");
        sendText(externalID, String.valueOf(randomExternalID), WaitStrategy.PRESENCE, "External ID");
        return this;
    }

    public NewECBPage enterLedgerID(int count) {
        String randomID = generateRandomID(count,"ECB");
        sendText(ledgerID, String.valueOf(randomID), WaitStrategy.PRESENCE, "ledger ID");
        return this;
    }

    public NewECBPage enterROC(int count) {
        String randomID = generateRandomID(count,"ECB");
        sendText(rocNumber, String.valueOf(randomID), WaitStrategy.PRESENCE, "ROC Number");
        return this;
    }

    public NewECBPage selectEntity(String text) {
        clickk(entity, WaitStrategy.CLICKABLE, "Entity");
        String lfType = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(lfType, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewECBPage selectCounterparty(String text) {
        jsClick(counterparty, WaitStrategy.CLICKABLE, "counterparty");
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        String lfType = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(lfType, text);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewECBPage selectBorrowingCurrency(String text) {
        clickk(currency, WaitStrategy.CLICKABLE, "currency");
        String value = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(value, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewECBPage enterSanctionDate(String text){
        clearDate(sanctionDate).
                sendText(sanctionDate,text,WaitStrategy.PRESENCE,"sanction date");
        return this;
    }
    public NewECBPage enterEndDate(String text){
        clearDate(endDate).sendText(endDate,text,WaitStrategy.PRESENCE,"end date");
        return this;
    }
    public NewECBPage enterFacilityAmount(String text){
        clickk(ecbAmount,WaitStrategy.CLICKABLE,"Facility Amount textbox");
        sendText(ecbAmount,text,WaitStrategy.PRESENCE,"Facility Amount");
        return this;
    }
    public NewECBPage covenantDefaultIR(String text){
        jsClick(covenantDefaultIR,WaitStrategy.CLICKABLE,"Covenant Default IR");
        sendText(covenantDefaultIR,text,WaitStrategy.PRESENCE,"Covenant Default Interest Rate %");
        return this;
    }
    public NewECBPage paymentDefaultIR(String text){
        jsClick(paymentDefaultIR,WaitStrategy.CLICKABLE,"payment Default IR");
        sendText(paymentDefaultIR,text,WaitStrategy.PRESENCE,"payment Default Interest Rate %");
        return this;
    }
    public NewECBPage selectArranger(String text) {
        jsClick(arranger, WaitStrategy.CLICKABLE, "Arranger");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public NewECBPage primarySecurityDetails(){
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

    public NewECBPage secondarySecurityDetails(){
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
    public NewECBPage personalGuaranteeDetails(){
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
    public NewECBPage corporateGuaranteeDetails( ){
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

    public NewECBPage enterTrustee(String text){
        scrollIntoView(trustee);
        sendText(trustee,text,WaitStrategy.PRESENCE,"Trustee");
        return this;
    }
    public NewECBPage enterAdditionalInfo(String text){
        sendText(additionalInfo,text,WaitStrategy.PRESENCE,"Additional Info");
        return this;
    }
    public ECBPage clickOnCreate(){
        scrollIntoView(btn_create);
        clickk(btn_create,WaitStrategy.CLICKABLE,"Create button");
        return new ECBPage();
    }

}
