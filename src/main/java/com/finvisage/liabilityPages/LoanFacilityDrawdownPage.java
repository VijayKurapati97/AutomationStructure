package com.finvisage.liabilityPages;

import com.finvisage.constants.FrameworkConstants;
import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.finvisage.factory.ExplicitWaitFactory;
import com.finvisage.utils.CommonUtils;
import com.finvisage.utils.XpathUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.*;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class LoanFacilityDrawdownPage extends BasePageLiability {
    private final By principalScheduleOptions = By.xpath("//section[@class='my-5']//div[2]/a/i");
    private final By equatedSchedule1 = By.xpath("(//a[text()='Add Equated Schedule'])[1]");
    private final By equatedSchedule2 = By.xpath("(//a[text()='Add Equated Schedule'])[2]");
    private final By ad_HocSchedule1 = By.xpath("(//a[text()='Add Ad-Hoc Schedule'])[1]");
    private final By ad_HocSchedule2 = By.xpath("(//a[text()='Add Ad-Hoc Schedule'])[2]");
    private final By uploadSchedule1 = By.xpath("(//a[text()='Upload Schedule'])[1]");
    private final By uploadSchedule2 = By.xpath("(//a[text()='Upload Schedule'])[2]");
    private final By principalPayout = By.xpath("//select[@id='repayment_schedule_equated_property" +
            "_principal_payout']/following-sibling::div/div[1]");
    private final By principalPaymentDay = By.xpath("//select[@id='repayment_schedule_equated_property_principal_" +
            "payment_day']/following-sibling::div/div[1]");
    private final By principalPaymentConvension = By.xpath("//select[@id='repayment_schedule_equated_property_payment_convention']/" +
            "following-sibling::div/div[1]");
    private final By interestPaymentConvension = By.xpath("//select[@id='repayment_schedule_equated_property_payment_convention']/" +
            "following-sibling::div/div[1]");

    private final By IRFrequency = By.xpath("//select[@id='repayment_schedule_equated_property_interest_frequency']/" +
            "following-sibling::div/div[1]");
    private final By compoundingFrequency = By.xpath("//select[@id='repayment_schedule_equated_property_compounding_frequency']/" +
            "following-sibling::div/div[1]");

    private final By IRPaymentDay = By.xpath("//select[@id='repayment_schedule_equated_property_interest_payment_day']/" +
            "following-sibling::div/div[1]");
    private final By NetAmount = By.xpath("//input[@id='repayment_schedule_value_repayment_amount']");
    private final By principalRoundingMode = By.xpath("//select[@id='repayment_schedule_equated_property_rounding_mode']/following-sibling::div/div[1]");
    private final By TDSRoundingMode = By.xpath("//select[@id='repayment_schedule_equated_property_tds_rounding_mode']/following-sibling::div/div[1]");
    private final By btn_preview = By.xpath("//button[text()='Cancel']/following-sibling::input");
    private final By btn_CallSchedulePreview = By.xpath("(//button[text()='Cancel']/following-sibling::input)[2]");
    private final By btn_generateSchedule = By.xpath("//input[@value='Generate Schedule']");
    private final By ad_HocValueDate = By.xpath("//input[@id='repayment_schedule_value_value_end_date']");
    private final By ad_HocPaymentDate = By.xpath("//input[@id='repayment_schedule_value_repayment_date']");
    private final By principalScheduleForm = By.xpath("//form[@id='new_repayment_schedule_value']");
    private final By btn_Submit = By.xpath("//input[@type='submit']");
    private final By callDate = By.xpath("//input[@id='put_call_schedule_value_put_call_date']");
    private final By interestScheduleOptions = By.xpath("//p[text()='Interest Payment Schedule']//parent::div/div[2]/a");
    private final By unallocatedPrincipal = By.xpath("//span[contains(text(),'Unallocated Principal')]" +
            "//following-sibling::span");
    private final By totalInterest = By.xpath("//span[contains(text(),'Total Interest:')]//following-sibling::span");
    private final By deactivateSchedule1 = By.xpath("(//a[text()='Deactivate Schedule'])[1]");
    private final By deactivateSchedule2 = By.xpath("(//a[text()='Deactivate Schedule'])[2]");
    private final By refresh = By.xpath("//a[@data-original-title='Refresh']");
    private final By uploadRefresh = By.xpath("//a[text()='Cancel']/following-sibling::a");
    private final By paymentType = By.xpath("//select[@id='actual_payment_type_select']//following-sibling::div/div[1]");
    private final By paymentNotes = By.id("actual_repayment_schedule_value_notes");
    private final By prepaymentsNotes = By.id("actual_against_penalty_notes");
    private final By feeTypee = By.xpath("//select[@id='fee_type_select']//following-sibling::div/div[1]");
    private final By amountTypee = By.xpath("//select[@id='fee_amount_type']//following-sibling::div/div[1]");
    private final By prepaymentOutstandingAmount = By.xpath("//form[@id='new_actual_against_penalty']" +
            "/div[2]//div[4]/p");
    private final By btn_BeginImport = By.xpath("//a[text()='Begin Import]");
    private final By btn_DeletePayment = By.xpath("//a[@title='Delete']");
    private final By liability_upload_name = By.id("liability_upload_name");
    private final By btn_ClickToUpload = By.id("generic_dropzone");
    private final By dropzone = By.xpath("//form[@id='cashflow_attachments']/div[3]");
    private final By analyticsTab = By.xpath("//a[text()='Analytics']");
    private final By callSchedulesTab = By.id("put-call-schedules-tab");
    private final By feeTab = By.xpath("//a[@id='fees-details-tab']/i");
    private final By feeValue = By.id("fee_value");
    private final By feeRemarks = By.id("fee_remarks");
    private final By cancelFee = By.xpath("//table[@id='fees_container']/tbody/tr/td[15]/a/i");
    private final By btn_AddFee = By.xpath("//a[text()='Add Fee']");
    private final By xirrValue = By.xpath("//div[@id='xirr']/a/h5");
    private final By eirValue = By.xpath("//div[@id='eir']/a/h5");
    private final By prepayemntAgainstPenaltyAmount = By.id("actual_against_penalty_repayment_amount");
    private final By equatedCallSchedule = By.xpath("(//a[text()='Add Equated Schedule'])[3]");
    private final By ad_HocCallSchedule = By.xpath("(//a[text()='Add Ad-Hoc Schedule'])[3]");
    private final By deactivateCallSchedule = By.xpath("//a[@data-original-title='Deactivate']");
    private final By callFrequency = By.xpath("//select[@id='put_call_schedule_equated_property_put_call_frequency']//following-sibling::div/div[1]");
    private final By callDay = By.xpath("//select[@id='put_call_schedule_equated_property_put_call_exercise_day']//following-sibling::div/div[1]");
    private final By drawdownEndDate = By.xpath("//div[@id='loan_facility_drawdown_details']" +
            "//section/div/div/section/div/div[2]/ul/li[5]/div/div[2]/span");
    private final By btn_callScheduleOptions = By.xpath("//p[text()='Call Schedules:']//parent::div/div[2]/a");
    private final By btn_makePrepaymentsOptions = By.xpath(("//p[text()='Prepayments']//parent::div/div[2]/a"));
    private final By makePrepayment = By.xpath("//a[@title='Make Prepayment']");
    private final By prepaymentPaymentDate = By.id("prepayment_payment_date");
    private final By prepaymentValueDate = By.id("prepayment_prepayment_date");
    private final By prepaymentAmount = By.id("prepayment_prepayment_amount");
    private final By prepaymentPenaltyDate = By.id("prepayment_prepayment_penalty_date");
    private final By btn_close = By.xpath("//button[text()='Close']");
    private final By prepyementPenaltyAmount = By.xpath("(//tbody)[4]/tr/td[6]");
    private final By drawdownAttachedDocumentsTab = By.xpath("//a[@id='attachments-details-tab']");
    private final By Btn_uploadDocs = By.xpath("//a[text()='Upload Documents']");
    private final By uploadDate = By.id("upload_date");
    private final By removeFile = By.xpath("//a[text()='Remove file']");
    private final By lienDetails = By.id("lien-details-tab");
    private final By btn_addFD = By.xpath("(//a[@title='Add'])[1]");
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
    final String[] drwdownAmount = {"400000", "1000000", "300000", "500000", "600000", "700000", "800000", "900000"};
    final String[] spread = {"12", "13", "14", "15", "19", "20", "11", "8", "17"};

    public LoanFacilityDrawdownPage() {
    }


    public LoanFacilityDrawdownPage clickPrincipalScheduleOptions() {
        scrollIntoView(principalScheduleOptions);
        clickk(principalScheduleOptions, WaitStrategy.CLICKABLE, "Principal Schedule Options");
        return this;
    }

    public LoanFacilityDrawdownPage clickInterestScheduleOptions() {
        scrollIntoView(interestScheduleOptions);
        clickk(interestScheduleOptions, WaitStrategy.CLICKABLE, "Interest Schedule Options");
        return this;
    }

    public LoanFacilityDrawdownPage selectAddEquatedPrincipalSchedule() {
        clickk(equatedSchedule1, WaitStrategy.CLICKABLE, "Equated schedule");
        return this;

    }

    public LoanFacilityDrawdownPage selectAddEquatedInterestSchedule() {
        clickk(equatedSchedule2, WaitStrategy.CLICKABLE, "Equated schedule");
        return this;

    }

    public LoanFacilityDrawdownPage selectIRFrequency(String value) {
        clickk(IRFrequency, WaitStrategy.CLICKABLE, "Interest Frequency");
        String freq = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(freq, value);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, value);
        return this;
    }

    public LoanFacilityDrawdownPage IRPaymentDay(String value) {
        clickk(IRPaymentDay, WaitStrategy.CLICKABLE, "Interest payment day");
        String freq = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(freq, value);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, value);
        return this;
    }

    public LoanFacilityDrawdownPage selectCompoundingFrequency(String value) {
        clickk(compoundingFrequency, WaitStrategy.CLICKABLE, "compounding Frequency");
        if (getText(IRFrequency, WaitStrategy.VISIBLE, "compounding Frequency").equalsIgnoreCase(value)) {
            String freq = "(//div[text()='%replace%'])[3]";
            String newxpath = XpathUtils.getXpath(freq, value);
            clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, value);
        } else {
            String freq = "(//div[text()='%replace%'])[2]";
            String newxpath = XpathUtils.getXpath(freq, value);
            clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, value);
        }

        return this;
    }

    public LoanFacilityDrawdownPage selectAd_HocPrincipalSchedule() {
        scrollIntoView(principalScheduleOptions);
        clickk(principalScheduleOptions, WaitStrategy.CLICKABLE, "Principal schedule options button");
        clickk(ad_HocSchedule1, WaitStrategy.CLICKABLE, "Ad-Hoc schedule");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        isDisplayed(principalScheduleForm, "Principal schedule form");
        return this;

    }

    public LoanFacilityDrawdownPage selectAd_HocInterestSchedule() {
        scrollIntoView(interestScheduleOptions);
        clickk(interestScheduleOptions, WaitStrategy.CLICKABLE, "Interest schedule options button");
        clickk(ad_HocSchedule2, WaitStrategy.CLICKABLE, "Ad-Hoc schedule");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        isDisplayed(principalScheduleForm, "Interest schedule form");
        return this;

    }

    public LoanFacilityDrawdownPage selectPrincipalPayout(String value) {
        clickk(principalPayout, WaitStrategy.CLICKABLE, "Principal Payout");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String payout = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(payout, value);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, value);
        return this;
    }

    public LoanFacilityDrawdownPage selectPrincipalPayemntDay(String day) {
        clickk(principalPaymentDay, WaitStrategy.CLICKABLE, "Principal payment day");
        String pday = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(pday, day);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, day);
        return this;
    }

    public LoanFacilityDrawdownPage selectPrincipalPaymentConvention(String value) {
        clickk(principalPaymentConvension, WaitStrategy.CLICKABLE, "Principal payment convention");
        String pc = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(pc, value);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, value);
        return this;
    }

    public LoanFacilityDrawdownPage selectInterestPaymentConvention(String value) {
        clickk(interestPaymentConvension, WaitStrategy.CLICKABLE, "interest payment convention");
        String pc = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(pc, value);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, value);
        return this;
    }

    public LoanFacilityDrawdownPage selectInterestRounding(String mode) {
        jsClick(principalRoundingMode, WaitStrategy.CLICKABLE, "Interest Rounding Mode");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String rounding = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(rounding, mode);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, mode);
        return this;

    }

    public LoanFacilityDrawdownPage TDSRounding(String mode) {
        jsClick(TDSRoundingMode, WaitStrategy.CLICKABLE, "TDS Rounding Mode");
        if (getText(principalRoundingMode, WaitStrategy.VISIBLE, "Interest Rounding").equalsIgnoreCase(mode)) {
            String rounding = "(//div[text()='%replace%'])[3]";
            String newxpath = XpathUtils.getXpath(rounding, mode);
            clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, mode);
        } else {
            String rounding = "(//div[text()='%replace%'])[2]";
            String newxpath = XpathUtils.getXpath(rounding, mode);
            clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, mode);
        }

        return this;
    }

    public LoanFacilityDrawdownPage selectPrincipalRounding(String mode) {
        jsClick(principalRoundingMode, WaitStrategy.CLICKABLE, "Principal Rounding Mode");
        String rounding = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(rounding, mode);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, mode);
        return this;
    }

    public LoanFacilityDrawdownPage clickOnPreview() {
        scrollIntoView(btn_preview);
        jsClick(btn_preview, WaitStrategy.CLICKABLE, "Preview");
        return this;
    }

    public LoanFacilityDrawdownPage clickOnGenerateSchedule() {
        clickk(btn_generateSchedule, WaitStrategy.CLICKABLE, "Generate Schedule");
     //   Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        return this;
    }

    public LoanFacilityDrawdownPage selectValueDate(String text) {
        clearDate(ad_HocValueDate).
                actionSendkeys(text);
        return this;
    }

    public LoanFacilityDrawdownPage clickPrinicpalUploadSchedule() {
        clickk(uploadSchedule1, WaitStrategy.CLICKABLE, "Principal Schedule template");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        return this;
    }

    public LoanFacilityDrawdownPage clickInterestUploadSchedule() {
        clickk(uploadSchedule2, WaitStrategy.CLICKABLE, "Interest Schedule template");
        return this;
    }

    public LoanFacilityDrawdownPage selectPaymentDate(String text) {
        jsClick(ad_HocPaymentDate, "payment date");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        clearDate(ad_HocPaymentDate);
        actionSendkeys(text);
        return this;
    }

    public LoanFacilityDrawdownPage clickSubmit() {
        scrollIntoView(btn_Submit);
        jsClick(btn_Submit, WaitStrategy.CLICKABLE, "Submit");
        return this;
    }

    public String getUnallcatedPrincipal() {
        return getText(unallocatedPrincipal, WaitStrategy.VISIBLE, "unallocated principal");
    }

    public String getTotalInterest() {
        return getText(totalInterest, WaitStrategy.VISIBLE, "Total interest");
    }

    public LoanFacilityDrawdownPage checkUnallocatedPrincipal() {
        scrollIntoView(unallocatedPrincipal);
        for (int i = 0; i < 5; i++) {
            if (getUnallcatedPrincipal().equals("0.00")) {
                break;
            } else {
                Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);
                DriverManager.getDriver().navigate().refresh();
            }
        }
        return this;
    }

    public LoanFacilityDrawdownPage clickDeactivate1() {
        for (int i = 0; i < 10; i++) {
            try {
                doubleClick(deactivateSchedule1);
                Alert al = DriverManager.getDriver().switchTo().alert();
                al.accept();
                break;
            } catch (NoAlertPresentException e) {
                Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            }

        }
        return this;
    }

    public LoanFacilityDrawdownPage clickDeactivate2() {
        for (int i = 0; i < 10; i++) {
            try {
                doubleClick(deactivateSchedule2);
                Alert al = DriverManager.getDriver().switchTo().alert();
                al.accept();
                break;
            } catch (NoAlertPresentException e) {
                Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            }

        }
        return this;
    }

    public LoanFacilityDrawdownPage checkIRscheduleGenerated() {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        for (int i = 0; i <= 5; i++) {
            if (!isDisplayed(refresh, "refresh")) {
                break;
            } else {
                DriverManager.getDriver().navigate().refresh();
                Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
            }
        }
        return this;
    }

    public LoanFacilityDrawdownPage checkUploadisCompleted() {
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        scrollIntoView(uploadRefresh);
        for (int i = 0; i <= 3; i++) {
            if (!isDisplayed(uploadRefresh, "refresh")) {
                break;
            } else {
                clickk(uploadRefresh, WaitStrategy.PRESENCE, "Upload refresh");
                Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
            }
        }
        return this;
    }

    public LoanFacilityDrawdownPage uploadPrincipalSchedule() throws AWTException {
        clickk(btn_ClickToUpload, WaitStrategy.CLICKABLE, "upload file");
        StringSelection stringSelection = new StringSelection(FrameworkConstants.getUploadPrincipalFilePath());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        pasteAndEnter();
        return this;
    }

    public LoanFacilityDrawdownPage uploadInterestSchedule() throws AWTException {
        clickk(btn_ClickToUpload, WaitStrategy.CLICKABLE, "upload filed");
        StringSelection stringSelection = new StringSelection(FrameworkConstants.getUploadInterestFilePath());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        pasteAndEnter();
        return this;
    }

    public LoanFacilityDrawdownPage enterLiability_upload_name() {
        clickk(liability_upload_name, WaitStrategy.CLICKABLE, "liability upload name");
        sendText(liability_upload_name, "Schedule", WaitStrategy.PRESENCE, "liability upload name");
        clickk(btn_Submit, WaitStrategy.CLICKABLE, "proceed button");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        scrollIntoView(btn_Submit);
        clickk(btn_Submit, WaitStrategy.CLICKABLE, "proceed button");
        return this;
    }

    public LoanFacilityDrawdownPage clickBeginImport() {
        clickk(btn_BeginImport, WaitStrategy.CLICKABLE, "Begin Import");
        return this;
    }

    public String[] getPrincipalScheduleStatus() {
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        String[] li = new String[list.size()];
        IntStream.rangeClosed(1, list.size()).forEachOrdered(i -> {
            String status = "(//tbody)[6]/tr[%replace%]/td[4]";
            String newxpath = XpathUtils.getXpath(status, String.valueOf(i));
            scrollIntoView(By.xpath(newxpath));
            scrollHorizontally(By.xpath(newxpath));
            Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
            li[i - 1] = getText(By.xpath(newxpath), WaitStrategy.VISIBLE, " - Principal Schedule Status");
        });


        return li;
    }

    public String[] getInterestScheduleStatus() {
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[7]/tr"));
        String[] li = new String[list.size()];
        IntStream.rangeClosed(1, list.size()).forEachOrdered(i -> {
            String status = "(//tbody)[7]/tr[%replace%]/td[4]";
            String newxpath = XpathUtils.getXpath(status, String.valueOf(i));
            scrollIntoView(By.xpath(newxpath));
            scrollHorizontally(By.xpath(newxpath));
            li[i - 1] = getText(By.xpath(newxpath), WaitStrategy.VISIBLE, " -Interest Schedule Status");
        });


        return li;
    }

    public String[] getprepaymentsStatus() {
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[4]/tr"));
        String[] li = new String[list.size()];
        IntStream.rangeClosed(1, list.size()).forEachOrdered(i -> {
            String status = "(//tbody)[4]/tr[%replace%]/td[5]";
            String newxpath = XpathUtils.getXpath(status, String.valueOf(i));
            scrollIntoView(By.xpath(newxpath));
            scrollHorizontally(By.xpath(newxpath));
            li[i - 1] = getText(By.xpath(newxpath), WaitStrategy.VISIBLE, " -prepayments Status");
        });


        return li;
    }

    public void make_Principal_Payments(String type) {
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        IntStream.rangeClosed(1, list.size()).forEachOrdered(i -> {
            String makepayment = "(//tbody)[6]/tr[%replace%]/td[10]/a[2]";
            String newxpath = XpathUtils.getXpath(makepayment, String.valueOf(i));
            scrollIntoView(By.xpath(newxpath));
            scrollHorizontally(By.xpath(newxpath));
            clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, "Make Payment");
            clickk(paymentType, WaitStrategy.CLICKABLE, "Actual Payment Type");
            String rounding = "//div[text()='%replace%']";
            String newxpath2 = XpathUtils.getXpath(rounding, type);
            clickk(By.xpath(newxpath2), WaitStrategy.CLICKABLE, type);
            sendText(paymentNotes, "NA", WaitStrategy.PRESENCE, "Notes");
            jsClick(btn_Submit, WaitStrategy.CLICKABLE, "Submit");
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        });
    }

    public void delete_Principal_Schedules() {
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        IntStream.rangeClosed(1, list.size()).forEachOrdered(i -> {
            String makepayment = "(//tbody)[6]/tr[%replace%]/td[10]/a[3]";
            String newxpath = XpathUtils.getXpath(makepayment, String.valueOf(i));
            scrollIntoView(By.xpath(newxpath));
            scrollHorizontally(By.xpath(newxpath));
            clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, "view Payment");
            for (int j = 0; j < 3; j++) {
                try {
                    Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
                    doubleClick(btn_DeletePayment);
                    Alert al = DriverManager.getDriver().switchTo().alert();
                    al.accept();
                    break;
                } catch (NoAlertPresentException e) {
                    Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
                }

            }
        });

    }

    public void delete_Interest_Payments() {
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[7]/tr"));
        IntStream.rangeClosed(1, list.size()).forEachOrdered(i -> {
            String makepayment = "(//tbody)[7]/tr[%replace%]/td[10]/a[3]";
            String newxpath = XpathUtils.getXpath(makepayment, String.valueOf(i));
            scrollIntoView(By.xpath(newxpath));
            scrollHorizontally(By.xpath(newxpath));
            clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, "view Payment");
            for (int j = 0; j < 3; j++) {
                try {
                    Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
                    doubleClick(btn_DeletePayment);
                    Alert al = DriverManager.getDriver().switchTo().alert();
                    al.accept();
                    break;
                } catch (NoAlertPresentException e) {
                    Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
                }
            }
        });
    }

    public void delete_prepayments() {
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[4]/tr"));
        IntStream.rangeClosed(1, list.size()).forEachOrdered(i -> {
            String makepayment = "(//tbody)[4]/tr[%replace%]/td[13]/a[2]";
            String newxpath = XpathUtils.getXpath(makepayment, String.valueOf(i));
            scrollIntoView(By.xpath(newxpath));
            scrollHorizontally(By.xpath(newxpath));
            clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, "view Payment");
            for (int j = 0; j < 3; j++) {
                try {
                    Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
                    doubleClick(btn_DeletePayment);
                    Alert al = DriverManager.getDriver().switchTo().alert();
                    al.accept();
                    break;
                } catch (NoAlertPresentException e) {
                    Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
                }

            }
        });

    }

    public LoanFacilityDrawdownPage edit_Principal_Schedules() {
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        IntStream.rangeClosed(1, list.size()).forEachOrdered(i -> {
            String makepayment = "(//tbody)[6]/tr[%replace%]/td[10]/a[1]";
            String newxpath = XpathUtils.getXpath(makepayment, String.valueOf(i));
            scrollIntoView(By.xpath(newxpath));
            scrollHorizontally(By.xpath(newxpath));
            clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, "Edit payment");
            String value = getAttribute(NetAmount, "value", WaitStrategy.VISIBLE, "Net Amount");
            double amount = Double.parseDouble(value) - 1;
            clearDate(NetAmount).sendText(NetAmount, String.valueOf(amount), WaitStrategy.PRESENCE, "amount");
            jsClick(btn_Submit, WaitStrategy.CLICKABLE, "Submit");
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
            DriverManager.getDriver().navigate().refresh();
        });
        return this;
    }

    public LoanFacilityDrawdownPage edit_Interest_Schedules() {
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[7]/tr"));
        IntStream.rangeClosed(1, list.size()).forEachOrdered(i -> {
            String makepayment = "(//tbody)[7]/tr[%replace%]/td[11]/a[1]";
            String newxpath = XpathUtils.getXpath(makepayment, String.valueOf(i));
            scrollIntoView(By.xpath(newxpath));
            scrollHorizontally(By.xpath(newxpath));
            clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, "Edit payment");
            String valueDate = getAttribute(ad_HocValueDate, "value", WaitStrategy.VISIBLE, "value date");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(valueDate, formatter);
            LocalDate futureDate = date.minusDays(2);
            clearDate(ad_HocValueDate).sendText(ad_HocValueDate, futureDate.format(formatter), WaitStrategy.PRESENCE, "value date");
            clickk(btn_Submit, WaitStrategy.CLICKABLE, "submit");
            Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
            DriverManager.getDriver().navigate().refresh();

        });
        return this;
    }

    public void make_Interest_Payments(String type) {
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[7]/tr"));
        IntStream.rangeClosed(1, list.size()).forEachOrdered(i -> {
            String makepayment = "(//tbody)[7]/tr[%replace%]/td[10]/a[2]";
            String newxpath = XpathUtils.getXpath(makepayment, String.valueOf(i));
            scrollIntoView(By.xpath(newxpath));
            scrollHorizontally(By.xpath(newxpath));
            clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, "Make Payment");
            clickk(paymentType, WaitStrategy.CLICKABLE, "Actual Payment Type");
            String rounding = "//div[text()='%replace%']";
            String newxpath2 = XpathUtils.getXpath(rounding, type);
            clickk(By.xpath(newxpath2), WaitStrategy.CLICKABLE, type);
            sendText(paymentNotes, "NA", WaitStrategy.PRESENCE, "Notes");
            jsClick(btn_Submit, WaitStrategy.CLICKABLE, "Submit");
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        });

    }

    public void make_prepayments_Payments() {
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[4]/tr"));
        IntStream.rangeClosed(1, list.size()).forEachOrdered(i -> {
            String makepayment = "(//tbody)[4]/tr[%replace%]/td[13]/a[1]";
            String newxpath = XpathUtils.getXpath(makepayment, String.valueOf(i));
            scrollIntoView(By.xpath(newxpath));
            scrollHorizontally(By.xpath(newxpath));
            clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, "Make Payment");
            String amount = getText(prepaymentOutstandingAmount, WaitStrategy.VISIBLE, "Outstanding amount");
            sendText(prepayemntAgainstPenaltyAmount, amount, WaitStrategy.PRESENCE, "Amount");
            sendText(prepaymentsNotes, "NA", WaitStrategy.PRESENCE, "Notes");
            jsClick(btn_Submit, WaitStrategy.CLICKABLE, "Submit");
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        });

    }

    public LoanFacilityDrawdownPage create_New_Drawdown() {
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.clickOptions().clickAddDrawdown().enterDrawdownExternalID(10)
                .selectPrepayemntsPenalty("12")
                .enterDrwadownLedgerID(8).selectPut_Call("Call")
                .enterLoanAcnt("Loan ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPayementAcnt("NACH auto debit").clickNewDisbursement()
                .enterDisAmount(drwdownAmount[(int) (Math.random() * drwdownAmount.length)])
                .selectDisbursementType("Standard").clickNewIrSlab()
                .selectIRType("Floating").enterSpread(spread[(int) (Math.random() * spread.length)])
                .clickNewTDS().enterTDS("30").enterAdditionalInfo("NA").clickCreate();
        return this;
    }

    public LoanFacilityDrawdownPage generate_LF_Equated_Principal_Schedule() {
        clickPrincipalScheduleOptions()
                .selectAddEquatedPrincipalSchedule()
                .selectPrincipalPayout("Annually").selectPrincipalPayemntDay("On 2nd")
                .selectPrincipalPaymentConvention("FOLW").
                selectPrincipalRounding("NONE").clickOnPreview().clickOnGenerateSchedule()
                .checkUnallocatedPrincipal();
        return this;
    }

    public LoanFacilityDrawdownPage generate_LF_Equated_Interest_Schedule() {
        clickInterestScheduleOptions()
                .selectAddEquatedInterestSchedule()
                .selectIRFrequency("Annually").selectCompoundingFrequency("Monthly")
                .IRPaymentDay("On 3rd").selectInterestPaymentConvention("FOLW")
                .selectInterestRounding("NONE").TDSRounding("NONE")
                .clickOnPreview().clickOnGenerateSchedule()
                .checkIRscheduleGenerated();

        return this;
    }

    public String getEndDate() {
        return getText(drawdownEndDate, WaitStrategy.VISIBLE, "End Date");
    }

    public LoanFacilityDrawdownPage generate_LF_AdHoc_Principal_Schedule(String valueDate) {
        selectAd_HocPrincipalSchedule()
                .selectValueDate(valueDate)
                .clickSubmit().checkUnallocatedPrincipal();
        return this;
    }

    public LoanFacilityDrawdownPage generate_LF_AdHoc_Interest_Schedule() {
        selectAd_HocInterestSchedule()
                .selectValueDate(getText(drawdownEndDate, WaitStrategy.VISIBLE, "End Date"))
                .clickSubmit();
        return this;
    }

    public LoanFacilityDrawdownPage clickAnalyticsTab() {
        clickk(analyticsTab, WaitStrategy.CLICKABLE, "Analytics tab");
        return this;
    }

    public LoanFacilityDrawdownPage checkXirrEirValues() {
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
        return this;
    }

    public String getXirrValue() {
        return getAnalyticsValue(getText(xirrValue, WaitStrategy.VISIBLE, "Xirr value"));
    }

    public String getEirValue() {
        return getAnalyticsValue(getText(eirValue, WaitStrategy.VISIBLE, "Eir value"));
    }

    public LoanFacilityDrawdownPage clickCallSchedulesTab() {
        jsClick(callSchedulesTab, "Call Schedules Tab");
        return this;
    }

    public LoanFacilityDrawdownPage clickFeetab() {
        jsClick(feeTab, WaitStrategy.CLICKABLE, " Fee Tab");
        return this;
    }

    public LoanFacilityDrawdownPage clickCallSchedulesOptions() {
        clickk(btn_callScheduleOptions,WaitStrategy.CLICKABLE, "Call schedules Option button");
        return this;
    }

    public LoanFacilityDrawdownPage clickEquatedCallSchedule() {
        clickk(equatedCallSchedule, WaitStrategy.CLICKABLE, " Equated Call Schedule");
        return this;
    }

    public LoanFacilityDrawdownPage clickAdHocCallSchedule() {
        clickk(ad_HocCallSchedule, WaitStrategy.CLICKABLE, " Ad-Hoc Call Schedule");
        return this;
    }

    public LoanFacilityDrawdownPage selectCallFrequency(String frequency) {
        clickk(callFrequency, WaitStrategy.CLICKABLE, "Call Frequency");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String rounding = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(rounding, frequency);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, frequency);
        return this;

    }

    public LoanFacilityDrawdownPage selectCallExerciseDay(String day) {
        clickk(callDay, WaitStrategy.CLICKABLE, "Call Exercise Day");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String rounding = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(rounding, day);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, day);
        return this;
    }

    public LoanFacilityDrawdownPage clickCallSchedulePreview() {
        clickk(btn_preview, WaitStrategy.CLICKABLE, "preview button");
        return this;
    }

    public LoanFacilityDrawdownPage enterCallDate(String text) {
        jsClick(callDate,WaitStrategy.CLICKABLE,"Date filed");
        clearDate(callDate, WaitStrategy.PRESENCE).
                sendText(callDate, text, WaitStrategy.PRESENCE, " Date");
        DriverManager.getDriver().findElement(callDate).sendKeys(Keys.ENTER);
        return this;
    }

    public LoanFacilityDrawdownPage clickdeactivate_callSchedule() {
        for (int i = 0; i < 10; i++) {
            try {
                doubleClick(deactivateCallSchedule);
                Alert al = DriverManager.getDriver().switchTo().alert();
                al.accept();
                break;
            } catch (NoAlertPresentException e) {
                Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            }

        }
        return this;
    }

    public LoanFacilityDrawdownPage click_PrepayemntOptions() {
        jsClick(btn_makePrepaymentsOptions, "Prepayment Options button");
        return this;
    }

    public LoanFacilityDrawdownPage select_MakePrepayemnts() {
        jsClick(makePrepayment, "Prepayment");
        return this;
    }

    public LoanFacilityDrawdownPage enterPrepaymentPayementDate(String text) {
        clickk(prepaymentPaymentDate, WaitStrategy.CLICKABLE, "payment date");
        clearDate(prepaymentPaymentDate)
                .sendText(prepaymentPaymentDate, text, WaitStrategy.PRESENCE, "Prepayment payment date");
        return this;
    }

    public LoanFacilityDrawdownPage enterPrepaymentValueDate(String text) {
        clickk(prepaymentValueDate, WaitStrategy.CLICKABLE, "value date");
        clearDate(prepaymentValueDate)
                .sendText(prepaymentValueDate, text, WaitStrategy.PRESENCE, "Prepayment value date");
        return this;
    }

    public LoanFacilityDrawdownPage enterPrepaymentPenltyDate(String text) {
        clickk(prepaymentPenaltyDate, WaitStrategy.CLICKABLE, "penalty date");
        clearDate(prepaymentPenaltyDate)
                .sendText(prepaymentPenaltyDate, text, WaitStrategy.PRESENCE, "Prepayment penalty date");
        return this;
    }

    public LoanFacilityDrawdownPage enterPrepaymentAmount(String text) {
        WebElement ele = DriverManager.getDriver().findElement(prepaymentAmount);
        ele.clear();
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        sendText(prepaymentAmount, text, WaitStrategy.PRESENCE, "Prepayment Amount");
        return this;
    }

    public double getPrepaymentPenalty() {
        double dou = CommonUtils.stringToDouble(getText(prepyementPenaltyAmount, WaitStrategy.VISIBLE, "penalty amount"));

        return dou;
    }

    public LoanFacilityDrawdownPage make_prepayments() {
        click_PrepayemntOptions().select_MakePrepayemnts()
                .enterPrepaymentPayementDate("21/09/2027")
                .enterPrepaymentValueDate("21/09/2027")
                .enterPrepaymentAmount("20000")
                .clickSubmit();
        return this;
    }

    public LoanFacilityDrawdownPage clickAddFee() {
        jsClick(btn_AddFee, WaitStrategy.CLICKABLE, " Add Fee button");
        return this;
    }

    public LoanFacilityDrawdownPage feeType(String feeType) {
        jsClick(feeTypee, WaitStrategy.CLICKABLE, "Fee Type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, feeType);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, feeType);
        return this;
    }

    public LoanFacilityDrawdownPage amountType(String amountType) {
        jsClick(amountTypee, WaitStrategy.CLICKABLE, "Amount Type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, amountType);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, amountType);
        return this;
    }

    public LoanFacilityDrawdownPage enterFeeValue(String feevalue) {
        WebElement ele = DriverManager.getDriver().findElement(feeValue);
        ele.clear();
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        sendText(feeValue, feevalue, WaitStrategy.PRESENCE, "Fee value");
        return this;
    }

    public LoanFacilityDrawdownPage enterRemarks(String remarks) {
        sendText(feeRemarks, remarks, WaitStrategy.PRESENCE, "Fee Remarks");
        return this;
    }

    public String getFeeStatus(String var) {
        String ar = "//table[@id='fees_container']/tbody/tr/td[%replace%]";
        String newxpath = XpathUtils.getXpath(ar, var);
        return getText(By.xpath(newxpath), WaitStrategy.VISIBLE, "Fee Status");
    }

    public LoanFacilityDrawdownPage cancelFee() {
        for (int i = 0; i < 10; i++) {
            try {
                doubleClick(cancelFee);
                Alert al = DriverManager.getDriver().switchTo().alert();
                al.accept();
                break;
            } catch (NoAlertPresentException e) {
                Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            }

        }
        return this;
    }

    public LoanFacilityDrawdownPage clickAttchedDocTab() {
        clickk(drawdownAttachedDocumentsTab, WaitStrategy.CLICKABLE, "Attached Documents tab");
        return this;

    }

    public LoanFacilityDrawdownPage clickUploadDocuments() {
        jsClick(Btn_uploadDocs, "Upload Documents button");
        return this;
    }

    public LoanFacilityDrawdownPage enterUploadDate(String date) {
        clearDate(uploadDate, WaitStrategy.PRESENCE);
        sendText(uploadDate, date, WaitStrategy.PRESENCE, "Upload Date");
        return this;

    }

    public LoanFacilityDrawdownPage uploadAttchedDoc() throws AWTException {
        clickk(dropzone, WaitStrategy.CLICKABLE, "upload file zone");

        StringSelection stringSelection = new StringSelection(FrameworkConstants.getUploadAttachedDocFilePath());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        pasteAndEnter();
        return this;
    }

    public LoanFacilityDrawdownPage clickClose() {
        clickk(btn_close, WaitStrategy.CLICKABLE, "Close button");
        return this;

    }

    public LoanFacilityDrawdownPage clickLienDetails() {
        clickk(lienDetails, WaitStrategy.CLICKABLE, "Lien Details Tab");
        return this;
    }

    public LoanFacilityDrawdownPage clickAddToLienFD() {
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, btn_addFD);
        scrollIntoView(btn_addFD);
        clickk(btn_addFD, WaitStrategy.CLICKABLE, " Add Button ");
        return this;
    }

    public LoanFacilityDrawdownPage clickOnCreate() {
        clickk(btn_Submit, WaitStrategy.CLICKABLE, " Create button");
        return this;
    }

    public int getLiedFDNum() {
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, By.xpath("(//tbody)[4]"));
        return DriverManager.getDriver().findElements(By.xpath("(//tbody)[4]/tr")).size();
    }
    public int getAttachedDocSize(){
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, By.xpath("(//tbody)[7]"));
        return DriverManager.getDriver().findElements(By.xpath("(//tbody)[7]/tr")).size();
    }

    public LoanFacilityDrawdownPage clickCovenantsTab(){
        clickk(covenantsTab,WaitStrategy.CLICKABLE,"Covenants Tab");
        return this;
    }
    public LoanFacilityDrawdownPage clickAddCovenants(){
        clickk(btn_addCovenants,WaitStrategy.CLICKABLE,"Add Covenants Button");
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        return this;
    }
    public LoanFacilityDrawdownPage covenantTemplate(String tem) {
        jsClick(covenants_Template, WaitStrategy.CLICKABLE, "Covenant Template");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, tem);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, tem);
        return this;
    }

    public LoanFacilityDrawdownPage enterAccountablePerson(String text){
        sendText(accountablePerson,text,WaitStrategy.PRESENCE,"Accountable Person");
        return this;
    }
    public LoanFacilityDrawdownPage enterThirdParty(String text){
        sendText(thirdParty,text,WaitStrategy.PRESENCE,"ThirdParty");
        return this;
    }
    public LoanFacilityDrawdownPage enterTargetValue(String text){
        WebElement ele = DriverManager.getDriver().findElement(targetValue);
        ele.clear();
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        sendText(targetValue,text,WaitStrategy.PRESENCE,"Target value");
        return this;
    }
    public LoanFacilityDrawdownPage enterTargetDate(String text){
       clearDate(targetDate,WaitStrategy.PRESENCE)
               .sendText(targetDate,text,WaitStrategy.PRESENCE,"Target Date");
        return this;
    }
    public LoanFacilityDrawdownPage enterOffset(String text){
        jsClick(offsetDays,"Offset Days");
        sendText(offsetDays,text,WaitStrategy.PRESENCE,"Offset Days");
        return this;
    }
    public LoanFacilityDrawdownPage enterCovenantEndDate(String text){
        jsClick(covenantsRemainderEndDate," End Date");
        clearDate(covenantsRemainderEndDate,WaitStrategy.PRESENCE)
                .sendText(covenantsRemainderEndDate,text,WaitStrategy.PRESENCE,"End Date");
        return this;
    }
    public LoanFacilityDrawdownPage selectMappingConditions(String text){
        jsClick(covenants_MappingConditions, WaitStrategy.CLICKABLE, "Covenant Mapping conditions");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public LoanFacilityDrawdownPage selectCovenantsEntity(String text){
        jsClick(covenantsEntity, WaitStrategy.CLICKABLE, "Covenant Entity");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public LoanFacilityDrawdownPage selectRatioName(String text){
        jsClick(covenants_RatioName, WaitStrategy.CLICKABLE, "Covenant Ratio Name");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public LoanFacilityDrawdownPage enterThresholdPercentage(String text){
        WebElement ele = DriverManager.getDriver().findElement(covenantThresholdLimit);
        ele.clear();
        ele.sendKeys(Keys.BACK_SPACE);
        ele.sendKeys(Keys.BACK_SPACE);
        ele.sendKeys(Keys.BACK_SPACE);
        sendText(covenantThresholdLimit,text,WaitStrategy.PRESENCE,"Threshold Limit");
        return this;
    }
    public int getCovenatsSize() {
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

}
