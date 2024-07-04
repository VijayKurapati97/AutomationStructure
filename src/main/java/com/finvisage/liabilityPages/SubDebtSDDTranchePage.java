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
import java.util.stream.IntStream;

public class SubDebtSDDTranchePage extends BasePageLiability{
    private final By hamburgerMenu = By.xpath("//section[@id='fixed-buttons']/div/a/i");
    private final By btn_Edit = By.xpath("//a[@data-original-title='Edit']/i");
    private final By continuee = By.xpath("//button[text()='Continue']");
    private final By drawdownAttachedDocumentsTab = By.xpath("//a[@id='attachments-details-tab']");
    private final By Btn_uploadDocs = By.xpath("//a[text()='Upload Documents']");
    private final By dropzone = By.xpath("//form[@id='cashflow_attachments']/div[3]");
    private final By close = By.xpath("//a[@data-original-title='Close']");
    private final By closeButton = By.xpath("//button[text()='Close']");
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
    private final By loanExternal_ID = By.xpath("//p[text()='External ID']//parent::div//parent::div//following-sibling::div/div/h5");
    private final By btn_Delete = By.xpath("//a[@data-original-title='Delete']");
    private final By investorsTab = By.id("allotted-investors-tab");
    private final By benposScheduleOptions = By.xpath("//p[text()='Benpos Schedule']//parent::div/div[2]/a/i");
    private final By benposEquatedSchedule = By.xpath("//p[text()='Benpos Schedule']//parent::div/div[2]/div/a");
    private final By rta_Alert_Offset = By.id("rta_alert_offset_days");
    private final By rta_intimation_offset = By.id("rta_intimation_offset");
    private final By payment_alert_offset = By.id("payment_alert_offset");
    private final By principalScheduleOptions = By.xpath("//p[text()='Principal Repayment Schedule']//parent::div/div[2]/a/i");
    private final By equatedSchedule1 = By.xpath("(//a[text()='Add Equated Schedule'])[1]");
    private final By principalPayout = By.xpath("//select[@id='ncd_private_repayment_schedule_equated_property_principal_payout']" +
            "//following-sibling::div/div[1]");
    private final By principalRoundingMode = By.xpath("//select[@id='ncd_private_repayment_schedule_equated_property_rounding_mode']" +
            "//following-sibling::div/div[1]");
    private final By principalPaymentDay = By.xpath("//select[@id='ncd_private_repayment_schedule_equated_property_principal_payment_day']" +
            "//following-sibling::div/div[1]");
    private final By principalPaymentConvension = By.xpath("//select[@id='ncd_private_repayment_schedule_equated_property_payment_convention']" +
            "//following-sibling::div/div[1]");
    private final By btn_preview = By.xpath("//button[text()='Cancel']/following-sibling::input");
    private final By btn_generateSchedule = By.xpath("//input[@value='Generate Schedule']");
    private final By unallocatedPrincipal = By.xpath("//span[contains(text(),'Unallocated Principal')]" +
            "//following-sibling::span");
    private final By paymentType = By.xpath("//select[@id='ncd_private_actual_repayment_schedule_value_actual_payment_type']" +
            "//following-sibling::div/div[1]");
    private final By paymentNotes = By.id("ncd_private_actual_repayment_schedule_value_notes");


    public SubDebtSDDTranchePage clickHamburger() {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        moveToElement(DriverManager.getDriver().findElement(hamburgerMenu), "HamburgerMenu");
        return this;
    }

    public SubDebtSDDTranchePage clickEdit() {
        clickk(btn_Edit, WaitStrategy.CLICKABLE, "Edit icon");
        return this;
    }

    public NewSubDebtSDDTranchePage clickContinue() {
        jsClick(continuee, "Continue button");
        return new NewSubDebtSDDTranchePage();
    }

    public SubDebtSDDTranchePage clickAttachedDocTab() {
        clickk(drawdownAttachedDocumentsTab, WaitStrategy.CLICKABLE, "Attached Documents tab");
        return this;

    }

    public SubDebtSDDTranchePage clickUploadDocuments() {
        jsClick(Btn_uploadDocs, "Upload Documents button");
        return this;
    }

    public SubDebtSDDTranchePage uploadAttachedDoc() throws AWTException {
        clickk(dropzone, WaitStrategy.CLICKABLE, "upload file zone");
        StringSelection stringSelection = new StringSelection(FrameworkConstants.getUploadAttachedDocFilePath());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        pasteAndEnter();
        return this;
    }

    public SubDebtSDDTranchePage clickClose() {
        clickk(close, WaitStrategy.CLICKABLE, "Close button");
        return this;

    }
    public void clickToClose() {
        clickk(closeButton, WaitStrategy.CLICKABLE, "Close button");

    }

    public int getAttachedDocSize() {
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, By.xpath("(//tbody)[7]"));
        return DriverManager.getDriver().findElements(By.xpath("(//tbody)[7]/tr")).size();
    }

    public SubDebtSDDTranchePage clickCreditRatingTab() {
        clickk(creditRatingTab, WaitStrategy.CLICKABLE, "Credit Rating Tab");
        return this;
    }

    public SubDebtSDDTranchePage clickAddCreditRating() {
        clickk(btn_addCreditRating, WaitStrategy.CLICKABLE, "Add Credit Rating");
        return this;
    }

    public SubDebtSDDTranchePage selectRatingAgency(String text) {
        jsClick(ratingAgency, WaitStrategy.CLICKABLE, "Rating Agency");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public SubDebtSDDTranchePage selectRatingSymbol(String text) {
        jsClick(ratingSymbol, WaitStrategy.CLICKABLE, "Rating symbol");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public SubDebtSDDTranchePage selectOutlook(String text) {
        jsClick(outlook, WaitStrategy.CLICKABLE, "outlook");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public SubDebtSDDTranchePage enterCreditRatingDate(String date) {
        clearDate(creditRatingDate).
                sendText(creditRatingDate, date, WaitStrategy.PRESENCE, "creditRating date");
        return this;
    }

    public SubDebtSDDTranchePage enterRatedAmount(String amount) {
        clickk(ratedAmount, WaitStrategy.CLICKABLE, "Rated Amount");
        sendText(ratedAmount, amount, WaitStrategy.PRESENCE, "Rated amount");
        return this;
    }

    public SubDebtSDDTranchePage enterRatingDefinition() {
        sendText(ratingDefinition, "Automated Rating", WaitStrategy.PRESENCE, "Rating Definition");
        return this;
    }

    public void clickCreate() {
        clickk(btn_create, WaitStrategy.CLICKABLE, "Create button");
    }

    public int getCreditRatingSize() {
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, By.xpath("(//tbody)[9]"));
        return DriverManager.getDriver().findElements(By.xpath("(//tbody)[9]/tr")).size();
    }

    public SubDebtSDDTranchePage clickFeetab() {
        clickk(feeTab, WaitStrategy.CLICKABLE, " Fee Tab");
        return this;
    }

    public SubDebtSDDTranchePage clickAddFee() {
        jsClick(btn_AddFee, WaitStrategy.CLICKABLE, " Add Fee button");
        return this;
    }

    public SubDebtSDDTranchePage feeType(String feeType) {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        clickk(feeTypee, WaitStrategy.CLICKABLE, "Fee Type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, feeType);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, feeType);
        return this;
    }

    public SubDebtSDDTranchePage amountType(String amountType) {
        jsClick(amountTypee, WaitStrategy.CLICKABLE, "Amount Type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, amountType);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, amountType);
        return this;
    }

    public SubDebtSDDTranchePage enterFeeValue(String feevalue) {
        WebElement ele = DriverManager.getDriver().findElement(feeValue);
        ele.clear();
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        sendText(feeValue, feevalue, WaitStrategy.PRESENCE, "Fee value");
        return this;
    }

    public SubDebtSDDTranchePage enterRemarks(String remarks) {
        sendText(feeRemarks, remarks, WaitStrategy.PRESENCE, "Fee Remarks");
        return this;
    }

    public String getFeeStatus(String var) {
        String ar = "//table[@id='fees_container']/tbody/tr/td[%replace%]";
        String newxpath = XpathUtils.getXpath(ar, var);
        return getText(By.xpath(newxpath), WaitStrategy.VISIBLE, "Fee Status");
    }

    public SubDebtSDDTranchePage cancelFee() {
        try {
            doubleClick(cancelFee);
            Alert al = DriverManager.getDriver().switchTo().alert();
            al.accept();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public SubDebtSDDTranchePage clickCovenantsTab() {
        clickk(covenantsTab, WaitStrategy.CLICKABLE, "Covenants Tab");
        return this;
    }

    public SubDebtSDDTranchePage clickAddCovenants() {
        clickk(btn_addCovenants, WaitStrategy.CLICKABLE, "Add Covenants Button");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        return this;
    }

    public SubDebtSDDTranchePage covenantTemplate(String tem) {
        jsClick(covenants_Template, WaitStrategy.CLICKABLE, "Covenant Template");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, tem);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, tem);
        return this;
    }

    public SubDebtSDDTranchePage enterAccountablePerson(String text) {
        sendText(accountablePerson, text, WaitStrategy.PRESENCE, "Accountable Person");
        return this;
    }

    public SubDebtSDDTranchePage enterThirdParty(String text) {
        sendText(thirdParty, text, WaitStrategy.PRESENCE, "ThirdParty");
        return this;
    }

    public SubDebtSDDTranchePage enterTargetValue(String text) {
        WebElement ele = DriverManager.getDriver().findElement(targetValue);
        ele.clear();
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        sendText(targetValue, text, WaitStrategy.PRESENCE, "Target value");
        return this;
    }

    public SubDebtSDDTranchePage enterTargetDate(String text) {
        clearDate(targetDate, WaitStrategy.PRESENCE)
                .sendText(targetDate, text, WaitStrategy.PRESENCE, "Target Date");
        return this;
    }

    public SubDebtSDDTranchePage enterOffset(String text) {
        jsClick(offsetDays, "Offset Days");
        sendText(offsetDays, text, WaitStrategy.PRESENCE, "Offset Days");
        return this;
    }

    public SubDebtSDDTranchePage enterCovenantEndDate(String text) {
        jsClick(covenantsRemainderEndDate, " End Date");
        clearDate(covenantsRemainderEndDate, WaitStrategy.PRESENCE)
                .sendText(covenantsRemainderEndDate, text, WaitStrategy.PRESENCE, "End Date");
        return this;
    }

    public SubDebtSDDTranchePage selectMappingConditions(String text) {
        jsClick(covenants_MappingConditions, WaitStrategy.CLICKABLE, "Covenant Mapping conditions");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public SubDebtSDDTranchePage selectCovenantsEntity(String text) {
        jsClick(covenantsEntity, WaitStrategy.CLICKABLE, "Covenant Entity");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public SubDebtSDDTranchePage selectRatioName(String text) {
        jsClick(covenants_RatioName, WaitStrategy.CLICKABLE, "Covenant Ratio Name");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public SubDebtSDDTranchePage enterThresholdPercentage(String text) {
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
            if (!isDisplayed(By.xpath("(//tbody)[9]"), "Covenants table")) {
                Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
                DriverManager.getDriver().navigate().refresh();
            } else {

                break;
            }
        }
        return DriverManager.getDriver().findElements(By.xpath("(//tbody)[9]/tr")).size();
    }

    public SubDebtSDDTranchePage enterCloseNotes(String value) {
        sendText(close_note, value, WaitStrategy.VISIBLE, "Close Notes");
        return this;
    }


    public void clickSubmitToClose() {
        clickk(btn_Close_submit, WaitStrategy.CLICKABLE, "submit button");
    }

    public String getTrancheExrnlID() {
        return getText(loanExternal_ID, WaitStrategy.VISIBLE, "External ID");
    }

    public SubDebtSDDPage clickDeleteIcon() {
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
        return new SubDebtSDDPage();
    }

    public SubDebtSDDTranchePage clickInvestorsTab() {
        clickk(investorsTab, WaitStrategy.CLICKABLE, "Investors Tab");
        return this;
    }

    public SubDebtSDDTranchePage clickBenposOptions() {
        clickk(benposScheduleOptions, WaitStrategy.CLICKABLE, "Benpos Options");
        return this;
    }

    public SubDebtSDDTranchePage selectAddEquatedbenposSchedule() {
        clickk(benposEquatedSchedule, WaitStrategy.CLICKABLE, " Equated Schedule");
        return this;
    }

    public SubDebtSDDTranchePage enterRTAAlertOffset(String value) {
        clickk(rta_Alert_Offset, WaitStrategy.CLICKABLE, "RTA Alert Offset");
        sendText(rta_Alert_Offset, value, WaitStrategy.PRESENCE, "RTA Alert Offset");
        return this;
    }

    public SubDebtSDDTranchePage enterRTAIntimationOffset(String value) {
        clickk(rta_intimation_offset, WaitStrategy.CLICKABLE, "RTA intimation Offset");
        sendText(rta_intimation_offset, value, WaitStrategy.PRESENCE, "RTA intimation Offset");
        return this;
    }

    public SubDebtSDDTranchePage enterPaymentAlertOffset(String value) {
        clickk(payment_alert_offset, WaitStrategy.CLICKABLE, "Payment Alert Offset");
        sendText(payment_alert_offset, value, WaitStrategy.PRESENCE, "Payment Alert Offset");
        return this;
    }

    public int getBenposScheduleSize() {
        for (int i = 0; i < 5; i++) {
            clickInvestorsTab();
            if (!isDisplayed(By.xpath("(//tbody)[10]"), "Benpos table")) {
                Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
                DriverManager.getDriver().navigate().refresh();
            } else {

                break;
            }
        }
        return DriverManager.getDriver().findElements(By.xpath("(//tbody)[10]/tr")).size();
    }


    public SubDebtSDDTranchePage create_SDD_Tranche_nonZeroCoupon() {

        ShelfTrancheStructurePage sf = new ShelfTrancheStructurePage();
        sf.clickOptions().clickAddTranche().enterExternalID(7)
                .enterLedgerID(7).enterIssueOpenDate("10/02/2022")
                .enterIssueCloseDate("10/02/2022").enterAllotmentDate("10/02/2022")
                .enterIssuePrice("10000000").enterNumberOfUnits("24")
                .enterTrancheIssueLimit("1000000000")
                .enterOverSubscription("1000000")
                .selectDaysInYeartype("Constant days")
                .selectOperatingAccount("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectSettlementBankAcnt("AUTO_SETTLE_BA").clickNewIrSlab().selectIRType("Floating")
                .enterSpread("8")
                .enterTrancheMaturityDate("15/01/2025").enterTrancheMaturityDate("13/02/2025")
                .clickNewTDS().enterTDS("12").clickCreate();
        return this;
    }

    public SubDebtSDDTranchePage clickPrincipalScheduleOptions() {
        scrollIntoView(principalScheduleOptions);
        clickk(principalScheduleOptions, WaitStrategy.CLICKABLE, "Principal Schedule Options");
        return this;
    }

    public SubDebtSDDTranchePage selectAddEquatedPrincipalSchedule() {
        clickk(equatedSchedule1, WaitStrategy.CLICKABLE, "Equated schedule");
        return this;

    }

    public SubDebtSDDTranchePage selectPrincipalPayout(String value) {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        clickk(principalPayout, WaitStrategy.CLICKABLE, "Principal Payout");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String payout = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(payout, value);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, value);
        return this;
    }

    public SubDebtSDDTranchePage selectPrincipalPaymentDay(String day) {
        clickk(principalPaymentDay, WaitStrategy.CLICKABLE, "Principal payment day");
        String pday = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(pday, day);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, day);
        return this;
    }

    public SubDebtSDDTranchePage selectPrincipalPaymentConvention(String value) {
        clickk(principalPaymentConvension, WaitStrategy.CLICKABLE, "Principal payment convention");
        String pc = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(pc, value);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, value);
        return this;
    }

    public SubDebtSDDTranchePage selectPrincipalRounding(String mode) {
        jsClick(principalRoundingMode, WaitStrategy.CLICKABLE, "Principal Rounding Mode");
        String rounding = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(rounding, mode);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, mode);
        return this;
    }

    public SubDebtSDDTranchePage clickOnPreview() {
        scrollIntoView(btn_preview);
        jsClick(btn_preview, WaitStrategy.CLICKABLE, "Preview");
        return this;
    }

    public SubDebtSDDTranchePage clickOnGenerateSchedule() {
        clickk(btn_generateSchedule, WaitStrategy.CLICKABLE, "Generate Schedule");
        return this;
    }

    public String getUnallocatedPrincipal() {
        return getText(unallocatedPrincipal, WaitStrategy.VISIBLE, "unallocated principal");
    }

    public SubDebtSDDTranchePage checkUnallocatedPrincipal() {
        scrollIntoView(unallocatedPrincipal);
        for (int i = 0; i < 5; i++) {
            if (getUnallocatedPrincipal().equals("0.00")) {
                break;
            } else {
                Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);
                DriverManager.getDriver().navigate().refresh();
            }
        }
        return this;
    }

    public void make_Principal_Payments(String type) {
        java.util.List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        IntStream.rangeClosed(1, list.size()).forEachOrdered(i -> {
            String makepayment = "(//tbody)[6]/tr[1]/td[9]/a[2]";
            String newxpath = XpathUtils.getXpath(makepayment, String.valueOf(i));
            scrollIntoView(By.xpath(newxpath));
            scrollHorizontally(By.xpath(newxpath));
            clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, "Make Payment");
            Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
            clickk(paymentType, WaitStrategy.CLICKABLE, "Actual Payment Type");
            String rounding = "//div[text()='%replace%']";
            String newxpath2 = XpathUtils.getXpath(rounding, type);
            clickk(By.xpath(newxpath2), WaitStrategy.CLICKABLE, type);
            sendText(paymentNotes, "NA", WaitStrategy.PRESENCE, "Notes");
            jsClick(btn_create, WaitStrategy.CLICKABLE, "Submit");
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        });
    }
}
