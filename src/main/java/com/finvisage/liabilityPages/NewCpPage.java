package com.finvisage.liabilityPages;

import com.finvisage.enums.WaitStrategy;
import com.finvisage.utils.XpathUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class NewCpPage extends BasePageLiability{
    private final By externalID= By.id("commercial_paper_external_id");
    private final By ledgerId=By.id("commercial_paper_ledger_id");
    private final By isin=By.id("commercial_paper_isin");
    private final By maturityDate=By.id("commercial_paper_maturity_date");
    private final By issuer =By.xpath("//select[@id='liability_entity_select']//following-sibling::div/div[1]");
    private final By rta =By.xpath("//select[@id='liability_counterparty_select']//following-sibling::div/div[1]");
    private final By ipa =By.xpath("//select[@id='commercial_paper_issuing_and_paying_agent_id']//following-sibling::div/div[1]");
    private final By currency =By.xpath("//select[@id='commercial_paper_currency_id']//following-sibling::div/div[1]");
    private final By issueCurrentAccount =By.id("commercial_paper_issue_current_account");
    private final By issueDematAccount =By.id("commercial_paper_issue_demat_account");
    private final By arranger=By.xpath("//select[@id='commercial_paper_arranger_id']/following-sibling::div/div[1]");
    private final By btn_create=By.xpath("//input[@type='submit']");


    public NewCpPage enterExternalID(int count) {
        String randomExternalID = generateRandomID(count,"CP");
        sendText(externalID, String.valueOf(randomExternalID), WaitStrategy.PRESENCE, "External ID");
        return this;
    }
    public NewCpPage enterLedgerID(int count) {
        String randomID = generateRandomID(count,"CP");
        sendText(ledgerId, String.valueOf(randomID), WaitStrategy.PRESENCE, "ledger ID");
        return this;
    }
    public NewCpPage enterISIN() {
        String randomISIN =generateRandomISIN();
        sendText(isin, String.valueOf(randomISIN), WaitStrategy.PRESENCE, "ISIN");
        return this;
    }
    public NewCpPage enterMaturityDate(String date){
        clearDate(maturityDate).
                sendText(maturityDate,date,WaitStrategy.PRESENCE,"maturity date");
        return this;
    }
    public NewCpPage selectIssuer(String issuerr){
        clickk(issuer, WaitStrategy.CLICKABLE, "issuer");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, issuerr);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, issuerr);
        return this;
    }
    public NewCpPage selectRTA(String RTA){
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        clickk(rta, WaitStrategy.CLICKABLE, "RTA");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, RTA);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, RTA);
        return this;
    }
    public NewCpPage selectIPA(String IPA){
        clickk(ipa, WaitStrategy.CLICKABLE, "IPA");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, IPA);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, IPA);
        return this;
    }
    public NewCpPage selectCurrency(String Currency){
        clickk(currency, WaitStrategy.CLICKABLE, "Currency");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, Currency);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, Currency);
        return this;
    }
    public NewCpPage enterIssueCurrentAccount(String ICA) {
        sendText(issueCurrentAccount,ICA, WaitStrategy.PRESENCE, "ICA");
        return this;
    }
    public NewCpPage enterIssueDematAccount(String IDA) {
        sendText(issueDematAccount,IDA, WaitStrategy.PRESENCE, "IDA");
        return this;
    }
    public NewCpPage selectArranger(String text) {
        jsClick(arranger, WaitStrategy.CLICKABLE, "Arranger");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar,text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public CommercialPaperPage clickCreate(){
        clickk(btn_create,WaitStrategy.CLICKABLE,"Create button");
        return new CommercialPaperPage();
    }
}
