package com.finvisage.liabilityPages;

import com.finvisage.constants.FrameworkConstants;
import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.finvisage.factory.ExplicitWaitFactory;
import com.finvisage.utils.XpathUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.*;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.concurrent.TimeUnit;

public class CPTranchePage extends BasePageLiability {
    private final By hamburgerMenu = By.xpath("//section[@id='fixed-buttons']/div/a/i");
    private final By btn_Edit = By.xpath("//a[@data-original-title='Edit']/i");
    private final By continuee = By.xpath("//button[text()='Continue']");
    private final By drawdownAttachedDocumentsTab = By.xpath("//a[@id='attachments-details-tab']");
    private final By Btn_uploadDocs = By.xpath("//a[text()='Upload Documents']");
    private final By dropzone = By.xpath("//form[@id='cashflow_attachments']/div[3]");
    private final By closeButton = By.xpath("//button[text()='Close']");
    private final By close = By.xpath("//a[@data-original-title='Close']");
    private final By creditRatingTab = By.id("credit-ratings-tab");
    private final By btn_addCreditRating = By.xpath("//a[text()='Add Credit Rating']");
    private final By ratingAgency = By.xpath("//select[@id='instrument_rating_rating_agency_id']/following-sibling::div/div[1]");
    private final By ratingSymbol = By.xpath("//select[@id='instrument_rating_rating_id']/following-sibling::div/div[1]");
    private final By outlook = By.xpath("//select[@id='instrument_rating_outlook']/following-sibling::div/div[1]");
    private final By creditRatingDate = By.xpath("//input[@id='instrument_rating_credit_rating_letter_date']");
    private final By ratedAmount = By.id("instrument_rating_amount_rated");
    private final By ratingDefinition = By.xpath("//textarea[@id='instrument_rating_rating_definition']");
    private final By btn_create = By.xpath("//input[@type='submit']");
    private final By feeTab = By.xpath("//a[@id='fees-details-tab']/i");
    private final By btn_AddFee = By.xpath("//a[text()='Add Fee']");
    private final By feeTypee = By.xpath("//select[@id='fee_type_select']//following-sibling::div/div[1]");
    private final By amountTypee = By.xpath("//select[@id='fee_amount_type']//following-sibling::div/div[1]");
    private final By feeValue = By.id("fee_value");
    private final By feeRemarks = By.id("fee_remarks");
    private final By cancelFee = By.xpath("//table[@id='fees_container']/tbody/tr/td[15]/a/i");
    private final By covenantsTab = By.xpath("//a[@id='covenants-tab']");
    private final By btn_addCovenants = By.xpath("//a[text()='Add Covenant']");
    private final By covenants_Template = By.xpath("//select[@id='covenant_covenant_template_id']//following-sibling::div/div[1]");
    private final By accountablePerson = By.id("covenant_accountable_person");
    private final By thirdParty = By.id("covenant_third_party");
    private final By targetValue = By.xpath("//input[@id='covenant_target_value2']");
    private final By targetDate = By.xpath("//input[@id='covenant_target_date']");
    private final By offsetDays = By.id("covenant_reminder_schedule_attributes_offset_days");
    private final By covenantsRemainderEndDate = By.xpath("//input[@id='covenant_reminder_schedule_attributes_end_date']");
    private final By covenants_MappingConditions = By.xpath("//select[@id='covenant_eval_type']//following-sibling::div/div[1]");
    private final By covenantsEntity = By.xpath("//select[@id='covenant_entity_id']//following-sibling::div/div[1]");
    private final By covenants_RatioName = By.xpath("//select[@id='covenant_covenant_ratio_id']//following-sibling::div/div[1]");
    private final By covenantThresholdLimit = By.id("covenant_threshold_limit");
    private final By close_note = By.xpath("//textarea[@id='loan_closure_closing_notes']");
    private final By btn_Close_submit = By.xpath("//input[@name='commit']");
    private final By analyticsTab = By.xpath("//a[text()='Analytics']");
    private final By xirrValue = By.xpath("//div[@id='xirr']/a/h5");
    private final By eirValue = By.xpath("//div[@id='eir']/a/h5");
    private final By loanExternal_ID = By.xpath("//p[text()='External ID']//parent::div//parent::div//following-sibling::div/div/h5");
    private final By btn_Delete = By.xpath("//a[@data-original-title='Delete']");

    public CPTranchePage clickHamburger() {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        moveToElement(DriverManager.getDriver().findElement(hamburgerMenu), "HamburgerMenu");
        return this;
    }

    public CPTranchePage clickEdit() {
        clickk(btn_Edit, WaitStrategy.CLICKABLE, "Edit icon");
        return this;
    }

    public NewCPTranchePage clickContinue() {
        jsClick(continuee, "Continue button");
        return new NewCPTranchePage();
    }
    public CPTranchePage clickAttachedDocTab() {
        clickk(drawdownAttachedDocumentsTab, WaitStrategy.CLICKABLE, "Attached Documents tab");
        return this;

    }

    public CPTranchePage clickUploadDocuments() {
        jsClick(Btn_uploadDocs, "Upload Documents button");
        return this;
    }

    public CPTranchePage uploadAttachedDoc() throws AWTException {
        clickk(dropzone, WaitStrategy.CLICKABLE, "upload file zone");

        StringSelection stringSelection = new StringSelection(FrameworkConstants.getUploadAttachedDocFilePath());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        pasteAndEnter();
        return this;
    }

    public CPTranchePage clickClose() {
        clickk(close, WaitStrategy.CLICKABLE, "Close button");
        return this;

    }
    public void clickToClose() {
        clickk(closeButton, WaitStrategy.CLICKABLE, "Close button");

    }
    public int getAttachedDocSize() {
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, By.xpath("(//tbody)[3]"));
        return DriverManager.getDriver().findElements(By.xpath("(//tbody)[3]/tr")).size();
    }
    public CPTranchePage clickCreditRatingTab() {
        clickk(creditRatingTab, WaitStrategy.CLICKABLE, "Credit Rating Tab");
        return this;
    }

    public CPTranchePage clickAddCreditRating() {
        clickk(btn_addCreditRating, WaitStrategy.CLICKABLE, "Add Credit Rating");
        return this;
    }

    public CPTranchePage selectRatingAgency(String text) {
        jsClick(ratingAgency, WaitStrategy.CLICKABLE, "Rating Agency");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public CPTranchePage selectRatingSymbol(String text) {
        jsClick(ratingSymbol, WaitStrategy.CLICKABLE, "Rating symbol");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public CPTranchePage selectOutlook(String text) {
        jsClick(outlook, WaitStrategy.CLICKABLE, "outlook");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public CPTranchePage enterCreditRatingDate(String date) {
        clearDate(creditRatingDate).
                sendText(creditRatingDate, date, WaitStrategy.PRESENCE, "creditRating date");
        return this;
    }

    public CPTranchePage enterRatedAmount(String amount) {
        clickk(ratedAmount, WaitStrategy.CLICKABLE, "Rated Amount");
        sendText(ratedAmount, amount, WaitStrategy.PRESENCE, "Rated amount");
        return this;
    }

    public CPTranchePage enterRatingDefinition() {
        sendText(ratingDefinition, "Automated Rating", WaitStrategy.PRESENCE, "Rating Definition");
        return this;
    }

    public void clickCreate() {
        clickk(btn_create, WaitStrategy.CLICKABLE, "Create button");
    }

    public int getCreditRatingSize() {
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, By.xpath("(//tbody)[4]"));
        return DriverManager.getDriver().findElements(By.xpath("(//tbody)[4]/tr")).size();
    }
    public CPTranchePage clickFeetab() {
        clickk(feeTab, WaitStrategy.CLICKABLE, " Fee Tab");
        return this;
    }

    public CPTranchePage clickAddFee() {
        jsClick(btn_AddFee, WaitStrategy.CLICKABLE, " Add Fee button");
        return this;
    }

    public CPTranchePage feeType(String feeType) {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        clickk(feeTypee, WaitStrategy.CLICKABLE, "Fee Type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, feeType);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, feeType);
        return this;
    }

    public CPTranchePage amountType(String amountType) {
        jsClick(amountTypee, WaitStrategy.CLICKABLE, "Amount Type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, amountType);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, amountType);
        return this;
    }

    public CPTranchePage enterFeeValue(String feevalue) {
        WebElement ele = DriverManager.getDriver().findElement(feeValue);
        ele.clear();
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        sendText(feeValue, feevalue, WaitStrategy.PRESENCE, "Fee value");
        return this;
    }

    public CPTranchePage enterRemarks(String remarks) {
        sendText(feeRemarks, remarks, WaitStrategy.PRESENCE, "Fee Remarks");
        return this;
    }

    public String getFeeStatus(String var) {
        String ar = "//table[@id='fees_container']/tbody/tr/td[%replace%]";
        String newxpath = XpathUtils.getXpath(ar, var);
        return getText(By.xpath(newxpath), WaitStrategy.VISIBLE, "Fee Status");
    }

    public CPTranchePage cancelFee() {
        try {
            doubleClick(cancelFee);
            Alert al = DriverManager.getDriver().switchTo().alert();
            al.accept();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public CPTranchePage clickCovenantsTab() {
        clickk(covenantsTab, WaitStrategy.CLICKABLE, "Covenants Tab");
        return this;
    }

    public CPTranchePage clickAddCovenants() {
        clickk(btn_addCovenants, WaitStrategy.CLICKABLE, "Add Covenants Button");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        return this;
    }

    public CPTranchePage covenantTemplate(String tem) {
        jsClick(covenants_Template, WaitStrategy.CLICKABLE, "Covenant Template");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, tem);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, tem);
        return this;
    }

    public CPTranchePage enterAccountablePerson(String text) {
        sendText(accountablePerson, text, WaitStrategy.PRESENCE, "Accountable Person");
        return this;
    }

    public CPTranchePage enterThirdParty(String text) {
        sendText(thirdParty, text, WaitStrategy.PRESENCE, "ThirdParty");
        return this;
    }

    public CPTranchePage enterTargetValue(String text) {
        WebElement ele = DriverManager.getDriver().findElement(targetValue);
        ele.clear();
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        sendText(targetValue, text, WaitStrategy.PRESENCE, "Target value");
        return this;
    }

    public CPTranchePage enterTargetDate(String text) {
        clearDate(targetDate, WaitStrategy.PRESENCE)
                .sendText(targetDate, text, WaitStrategy.PRESENCE, "Target Date");
        return this;
    }

    public CPTranchePage enterOffset(String text) {
        jsClick(offsetDays, "Offset Days");
        sendText(offsetDays, text, WaitStrategy.PRESENCE, "Offset Days");
        return this;
    }

    public CPTranchePage enterCovenantEndDate(String text) {
        jsClick(covenantsRemainderEndDate, " End Date");
        clearDate(covenantsRemainderEndDate, WaitStrategy.PRESENCE)
                .sendText(covenantsRemainderEndDate, text, WaitStrategy.PRESENCE, "End Date");
        return this;
    }

    public CPTranchePage selectMappingConditions(String text) {
        jsClick(covenants_MappingConditions, WaitStrategy.CLICKABLE, "Covenant Mapping conditions");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public CPTranchePage selectCovenantsEntity(String text) {
        jsClick(covenantsEntity, WaitStrategy.CLICKABLE, "Covenant Entity");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public CPTranchePage selectRatioName(String text) {
        jsClick(covenants_RatioName, WaitStrategy.CLICKABLE, "Covenant Ratio Name");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public CPTranchePage enterThresholdPercentage(String text) {
        WebElement ele = DriverManager.getDriver().findElement(covenantThresholdLimit);
        ele.clear();
        ele.sendKeys(Keys.BACK_SPACE);
        ele.sendKeys(Keys.BACK_SPACE);
        ele.sendKeys(Keys.BACK_SPACE);
        sendText(covenantThresholdLimit, text, WaitStrategy.PRESENCE, "Threshold Limit");
        return this;
    }

    public int getCovenantsSize() {
        for (int i = 0; i < 5; i++) {
            clickCovenantsTab();
            if (!isDisplayed(By.xpath("(//tbody)[5]"), "Covenants table")) {
                Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
                DriverManager.getDriver().navigate().refresh();
            } else {

                break;
            }
        }
        return DriverManager.getDriver().findElements(By.xpath("(//tbody)[5]/tr")).size();
    }

    public CPTranchePage enterCloseNotes(String value) {
        sendText(close_note, value, WaitStrategy.VISIBLE, "Close Notes");
        return this;
    }


    public void clickSubmitToClose() {
        clickk(btn_Close_submit, WaitStrategy.CLICKABLE, "submit button");
    }

    public CPTranchePage clickAnalyticsTab() {
        clickk(analyticsTab, WaitStrategy.CLICKABLE, "Analytics tab");
        return this;
    }

    public void checkXirrEirValues() {
        for (int i = 0; i < 5; i++) {
            if (!getText(xirrValue, WaitStrategy.VISIBLE, "Xirr value").contains("XIRR : __.__%")
                    && !getText(eirValue, WaitStrategy.VISIBLE, "Eir value").contains("EIR : __.__%")) {
                break;
            } else {
                DriverManager.getDriver().navigate().refresh();
                clickAnalyticsTab();
                Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
            }
        }
    }

    public String getXirrValue() {
        return getAnalyticsValue(getText(xirrValue, WaitStrategy.VISIBLE, "Xirr value"));
    }

    public String getEirValue() {
        return getAnalyticsValue(getText(eirValue, WaitStrategy.VISIBLE, "Eir value"));
    }

    public String getTrancheExrnlID() {
        return getText(loanExternal_ID, WaitStrategy.VISIBLE, "External ID");
    }
    public CommercialPaperPage clickDeleteIcon() {
        for (int i = 0; i < 10; i++) {
            try {
                doubleClick(btn_Delete);
                Alert al = DriverManager.getDriver().switchTo().alert();
                al.accept();
                break;
            } catch (NoAlertPresentException e) {
                Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            }

        }
        return new CommercialPaperPage();
    }
    public CPTranchePage create_CP_Tranche() {
        CommercialPaperPage cp = new CommercialPaperPage();
        cp.clickOptions().clickAddTranche()
                .enterExternalID(6).enterLedgerID(6)
                .enterDealDate("22/05/2022").enterValueDate("27/05/2022")
                .enterNumberOfUnits("45")
                .enterInvestmentAmount("20000000").enterDiscountRate("15")
                .enterInitialPaymentDate("30/06/2022").enterFinalPaymentDate("30/06/2025")
                .enterAdditionalInfo("Tranche created").clickCreate();
        return this;
    }
}
