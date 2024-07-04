package com.finvisage.liabilityPages;

import com.finvisage.enums.WaitStrategy;
import com.finvisage.utils.XpathUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class NewShelfTrancheStructurePage extends BasePageLiability{
    private final By issuer =By.xpath("//select[@id='asset_entity_select']//following-sibling::div/div[1]");
    private final By currency =By.xpath("//select[@id='ncd_sdd_currency_id']//following-sibling::div/div[1]");
    private final By arranger=By.xpath("//select[@id='ncd_sdd_arranger_id']/following-sibling::div/div[1]");
    private final By externalID= By.id("ncd_sdd_external_id");
    private final By ledgerId=By.id("ncd_sdd_ledger_id");
    private final By isin=By.id("ncd_sdd_isin");
    private final By maturityDate=By.id("ncd_sdd_maturity_date");
    private final By securedType=By.xpath("//select[@id='ncd_sdd_secured_type']/following-sibling::div/div[1]");
    private final By redeemableType=By.xpath("//select[@id='ncd_sdd_redeemable_type']/following-sibling::div/div[1]");
    private  final By zeroCoupon =By.xpath("//select[@id='ncd_sdd_zero_coupon_type']/following-sibling::div/div[1]");
    private final By primaryCounterparty =By.xpath("//select[@id='asset_counterparty_select']/following-sibling::div/div[1]");
    private final By ratedType=By.xpath("//select[@id='ncd_sdd_rated_type']/following-sibling::div/div[1]");
    private final By listingType=By.xpath("//select[@id='ncd_sdd_listed_type']/following-sibling::div/div[1]");
    private final By cumulativeType=By.xpath("//select[@id='ncd_sdd_cumulative_type']/following-sibling::div/div[1]");
    private final By trancheCanBeIssuedUntil =By.id("ncd_sdd_tranches_can_be_issued_until");
    private final By numOfNCDs=By.id("ncd_sdd_total_number_of_units");
    private final By boardResolutionDate=By.id("ncd_sdd_board_resolution_date");
    private final By financeResolutionDate=By.id("ncd_sdd_finance_resolution_date");
    private final By shareholdersResolutionDate=By.id("ncd_sdd_shareholders_resolution_date");
    private final By description =By.id("ncd_sdd_additional_info");
    private final By btn_create=By.xpath("//input[@type='submit']");


    public NewShelfTrancheStructurePage selectIssuer(String isssuer){
        scrollIntoView(issuer);
        clickk(issuer, WaitStrategy.CLICKABLE, "issuer");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, isssuer);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, isssuer);
        return this;
    }
    public NewShelfTrancheStructurePage selectCurrency(String Currency){
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        clickk(currency, WaitStrategy.CLICKABLE, "Currency");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, Currency);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, Currency);
        return this;
    }
    public NewShelfTrancheStructurePage selectArranger(String text) {
        jsClick(arranger, WaitStrategy.CLICKABLE, "Arranger");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewShelfTrancheStructurePage enterExternalID(int count) {
        String randomExternalID = generateRandomID(count,"NCD");
        sendText(externalID, String.valueOf(randomExternalID), WaitStrategy.PRESENCE, "External ID");
        return this;
    }
    public NewShelfTrancheStructurePage enterLedgerID(int count) {
        String randomID = generateRandomID(count,"NCD");
        sendText(ledgerId, String.valueOf(randomID), WaitStrategy.PRESENCE, "ledger ID");
        return this;
    }
    public NewShelfTrancheStructurePage enterISIN() {
        String randomISIN =generateRandomISIN();
        sendText(isin, String.valueOf(randomISIN), WaitStrategy.PRESENCE, "ISIN");
        return this;
    }
    public NewShelfTrancheStructurePage enterMaturityDate(String date){
        clearDate(maturityDate).
                sendText(maturityDate,date,WaitStrategy.PRESENCE,"maturity date");
        return this;
    }
    public NewShelfTrancheStructurePage enterTrancheCanBeIssuedUntil(String date){
        clearDate(trancheCanBeIssuedUntil).
                sendText(trancheCanBeIssuedUntil,date,WaitStrategy.PRESENCE,"tranche issue date");
        return this;
    }

    public NewShelfTrancheStructurePage selectSecuredType(String text) {
        jsClick(securedType, WaitStrategy.CLICKABLE, "secured type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewShelfTrancheStructurePage selectRedeemableType(String text) {
        jsClick(redeemableType, WaitStrategy.CLICKABLE, "Redeemable type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewShelfTrancheStructurePage selectZeroCoupon(String text) {
        jsClick(zeroCoupon, WaitStrategy.CLICKABLE, "Zero coupon");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewShelfTrancheStructurePage selectPrimaryCounterparty(String text) {
        clickk(primaryCounterparty, WaitStrategy.CLICKABLE, "Primary counterparty");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewShelfTrancheStructurePage selectRatedType(String text) {
        jsClick(ratedType, WaitStrategy.CLICKABLE, "Rated Type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewShelfTrancheStructurePage selectListingType(String text) {
        jsClick(listingType, WaitStrategy.CLICKABLE, "Listing Type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewShelfTrancheStructurePage selectCumulativeType(String text) {
        jsClick(cumulativeType, WaitStrategy.CLICKABLE, "Cumulative Type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public NewShelfTrancheStructurePage enterNumberOfUnits(String value) {
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        clickk(numOfNCDs,WaitStrategy.CLICKABLE,"Total Number of NCDs");
        sendText(numOfNCDs,value, WaitStrategy.PRESENCE, "Total Number of NCDs");
        return this;
    }
    public NewShelfTrancheStructurePage enterBoardResolutionDate(String date){
        clearDate(boardResolutionDate).
                sendText(boardResolutionDate,date,WaitStrategy.PRESENCE,"board resolution date");
        return this;
    }
    public NewShelfTrancheStructurePage enterFinanceCommitteeResolutionDate(String date){
        clearDate(financeResolutionDate).
                sendText(financeResolutionDate,date,WaitStrategy.PRESENCE,"Finance Committee Resolution Date");
        return this;
    }
    public NewShelfTrancheStructurePage enterShareholdersGeneralResolutionDate(String date){
        clearDate(shareholdersResolutionDate).
                sendText(shareholdersResolutionDate,date,WaitStrategy.PRESENCE,"Shareholder's General Resolution Date");
        return this;
    }
    public NewShelfTrancheStructurePage enterDescription(String value){
        sendText(description,value,WaitStrategy.PRESENCE,"Description");
        return this;
    }
    public ShelfTrancheStructurePage clickCreate(){
        clickk(btn_create,WaitStrategy.CLICKABLE,"Create button");
        return new ShelfTrancheStructurePage();
    }
}
