package com.finvisage.liabilityPages;

import com.finvisage.constants.FrameworkConstants;
import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.finvisage.factory.ExplicitWaitFactory;
import com.finvisage.utils.CommonUtils;
import com.finvisage.utils.XpathUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class SDLFDrawdownPage extends BasePageLiability {
    final String[] drawdownAmount = {"400000", "1000000", "300000", "500000", "600000", "700000", "800000", "900000"};
    final String[] spread = {"12", "13", "14", "15", "19", "20", "11", "8", "17"};
    private final By hamburgerMenu = By.xpath("//section[@id='fixed-buttons']/div/a/i");
    private final By btn_Edit = By.xpath("//a[@data-original-title='Edit']/i");
    private final By continuee = By.xpath("//button[text()='Continue']");
    private final By repaymentScheduleOptions = By.xpath("//p[text()='Repayment Schedule']//parent::div/div[2]/a/i");
    private final By addPrincipalEquatedSchedule = By.xpath("(//a[text()='Add Principal Equated Schedule'])[1]");
    private final By uploadPrincipalSchedule = By.xpath("//a[text()='Upload Principal Schedule']");
    private final By uploadInterestSchedule = By.xpath("//a[text()='Upload Interest Schedule']");
    private final By principalPayout = By.xpath("//select[@id='repayment_schedule_equated_property" +
            "_principal_payout']/following-sibling::div/div[1]");
    private final By principalRoundingMode = By.xpath("//select[@id='repayment_schedule_equated_property_rounding_mode']/following-sibling::div/div[1]");
    private final By principalPaymentDay = By.xpath("//select[@id='repayment_schedule_equated_property_principal_" +
            "payment_day']/following-sibling::div/div[1]");
    private final By principalPaymentConvension = By.xpath("//select[@id='repayment_schedule_equated_property_payment_convention']/" +
            "following-sibling::div/div[1]");
    private final By btn_preview = By.xpath("//button[text()='Cancel']/following-sibling::input");
    private final By btn_generateSchedule = By.xpath("//input[@value='Generate Schedule']");
    private final By unallocatedPrincipal = By.xpath("//span[contains(text(),'Unallocated Principal')]" +
            "//following-sibling::span");
    private final By totalInterest = By.xpath("//span[contains(text(),'Total Interest')]//following-sibling::span");
    private final By add_AdHoc_PrincipalSchedule = By.xpath("(//a[text()='Add Principal Ad-Hoc Schedule'])[1]");
    private final By principalScheduleForm = By.xpath("//form[@id='new_repayment_schedule_value']");
    private final By ad_HocValueDate = By.xpath("//input[@id='repayment_schedule_value_value_end_date']");
    private final By ad_HocPaymentDate = By.xpath("//input[@id='repayment_schedule_value_repayment_date']");
    private final By btn_Submit = By.xpath("//input[@type='submit']");
    private final By btn_Fee_Submit = By.xpath("(//input[@type='submit'])[1]");
    private final By adHoc_Schedule_Submit=By.xpath("(//input[@type='submit'])[3]");
    private final By btn_Disbursement_Submit=By.xpath("(//input[@type='submit'])[3]");
    private final By makePayments_Submit=By.xpath("(//input[@type='submit'])[3]");
    private final By uploadSchedule_Submit=By.xpath("(//input[@type='submit'])[3]");
    private final By btn_EditInterestSchedule = By.xpath("//a[text()='Edit Interest Repayment Schedule Value']");
    private final By btn_ViewPrincipalSchedule = By.xpath("//a[text()='View Principal Repayment Schedule Value']");
    private final By btn_ViewInterestSchedule = By.xpath("//a[text()='View Interest Repayment Schedule Value']");
    private final By repaymentDropZone = By.id("generic_dropzone");
    private final By liability_upload_name = By.id("liability_upload_name");
    private final By uploadRefresh = By.xpath("//a[text()='Cancel']/following-sibling::a");
    private final By btn_BeginImport = By.xpath("//a[text()='Begin Import]");
    private final By drawdownEndDate = By.xpath("//div[@id='loan_facility_drawdown_details']" +
            "//section/div/div/section/div/div[2]/ul/li[5]/div/div[2]/span");
    private final By drawdownExtId =By.xpath("//div[@id='loan_facility_drawdown_details']" +
            "//section/div/div/section/div/div[2]/ul/li[2]/div/div[2]/span");
    private final By NetAmount = By.xpath("//input[@id='repayment_schedule_value_repayment_amount']");
    private final By paymentNotes = By.id("actual_repayment_schedule_value_notes");
    private final By paymentType = By.xpath("//select[@id='actual_payment_type_select']//following-sibling::div/div[1]");
    private final By liability_bank_account = By.xpath("//select[@id='liability_bank_account_select']//following-sibling::div/div[1]");
    private final By payment_account = By.xpath("//select[@id='loan_facility_dropdown_payment_account_option']//following-sibling::div/div[1]");
    private final By through_account = By.xpath("//select[@id='loan_facility_dropdown_through_account_id']//following-sibling::div/div[1]");
    private final By counterparty_account = By.xpath("//select[@id='loan_facility_dropdown_counterparty_bank_account_id']//following-sibling::div/div[1]");
    private final By btn_DeletePayment = By.xpath("//a[@title='Delete']");
    private final By addEquatedIRSchedule = By.xpath("//a[text()='Add Interest Equated Schedule']");
    private final By IRFrequency = By.xpath("//select[@id='repayment_schedule_equated_property_interest_frequency']/" +
            "following-sibling::div/div[1]");
    private final By compoundingFrequency = By.xpath("//select[@id='repayment_schedule_equated_property_compounding_frequency']/" +
            "following-sibling::div/div[1]");
    private final By IRPaymentDay = By.xpath("//select[@id='repayment_schedule_equated_property_interest_payment_day']/" +
            "following-sibling::div/div[1]");
    private final By interestPaymentConvension = By.xpath("//select[@id='repayment_schedule_equated_property_payment_convention']/" +
            "following-sibling::div/div[1]");
    private final By TDSRoundingMode = By.xpath("//select[@id='repayment_schedule_equated_property_tds_rounding_mode']/following-sibling::div/div[1]");
    private final By deactivate_RepaymentSchedules = By.xpath("//a[text()='Deactivate Schedule']");
    private final By add_adHoc_IRSchedule = By.xpath("//a[text()='Add Interest Ad-Hoc Schedule']");
    private final By btn_makePrepaymentsOptions = By.xpath(("//p[text()='Prepayments']//parent::div/div[2]/a"));
    private final By prepaymentPaymentDate = By.id("prepayment_payment_date");
    private final By prepaymentValueDate = By.id("prepayment_prepayment_date");
    private final By prepaymentAmount = By.id("prepayment_prepayment_amount");
    private final By makePrepayment = By.xpath("//a[@title='Make Prepayment']");
    private final By prepaymentPenaltyAmount = By.xpath("(//tbody)[4]/tr/td[6]");
    private final By prepaymentOutstandingAmount = By.xpath("//form[@id='new_actual_against_penalty']" +
            "/div[2]//div[4]/p");
    private final By prepaymentAgainstPenaltyAmount = By.id("actual_against_penalty_repayment_amount");
    private final By prepaymentsNotes = By.id("actual_against_penalty_notes");
    private final By drawdownAttachedDocumentsTab = By.xpath("//a[@id='attachments-details-tab']");
    private final By Btn_uploadDocs = By.xpath("//a[text()='Upload Documents']");
    private final By dropzone = By.xpath("//form[@id='cashflow_attachments']/div[3]");
    private final By btn_close = By.xpath("//button[text()='Close']");
    private final By deactivatedSchedulesTab = By.xpath("//a[@id='deactivated-repayment-schedules-tab']");
    private final By deactivatedSchedules = By.xpath("(//div[@id='deactivatedAccordion'])[1]/div/div/a");
    private final By deactivatedPrincipalSchedule = By.xpath("//p[text()='Principal Repayment Schedule'] ");
    private final By deactivatedInterestSchedule = By.xpath(" //p[text()='Interest Payment Schedule']");
    private final By callSchedulesTab = By.id("put-call-schedules-tab");
    private final By btn_callScheduleOptions = By.xpath("//p[text()='Call Schedules:']//parent::div/div[2]/a");
    private final By equatedCallSchedule = By.xpath("//a[text()='Add Equated Schedule']");
    private final By ad_HocCallSchedule = By.xpath("//a[text()='Add Ad-Hoc Schedule']");
    private final By deactivateCallSchedule = By.xpath("//a[@data-original-title='Deactivate']");
    private final By callFrequency = By.xpath("//select[@id='put_call_schedule_equated_property_put_call_frequency']//following-sibling::div/div[1]");
    private final By callDay = By.xpath("//select[@id='put_call_schedule_equated_property_put_call_exercise_day']//following-sibling::div/div[1]");
    private final By callDate = By.xpath("//input[@id='put_call_schedule_value_put_call_date']");
    private final By feeTab = By.xpath("//a[@id='fees-details-tab']/i");
    private final By btn_AddFee = By.xpath("//a[text()='Add Fee']");
    private final By feeTypee = By.xpath("//select[@id='fee_type_select']//following-sibling::div/div[1]");
    private final By amountTypee = By.xpath("//select[@id='fee_amount_type']//following-sibling::div/div[1]");
    private final By feeValue = By.id("fee_value");
    private final By feeRemarks = By.id("fee_remarks");
    private final By cancelFee = By.xpath("//table[@id='fees_container']/tbody/tr/td[17]/a/i");
    private final By xirrValue = By.xpath("//div[@id='xirr']/a/h5");
    private final By analyticsTab = By.xpath("//a[text()='Analytics']");
    private final By eirValue = By.xpath("//div[@id='eir']/a/h5");
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
    private final By lienDetails = By.id("lien-details-tab");
    private final By btn_addFD = By.xpath("(//a[@title='Add'])[1]");
    private final By btn_Close = By.xpath("//a[@data-original-title='Close']");
    private final By btn_Close_submit = By.xpath("//input[@name='commit']");
    private final By close_note = By.xpath("//textarea[@id='loan_closure_closing_notes']");
    private final By closureDoctab = By.id("loan-closure-details-tab");
    private final By btn_deleteDrawdown = By.xpath("//section[@id='fixed-buttons']/div/ul/li[3]/a");
    private final By btn_EditPrincipalSchedule = By.xpath("//a[text()='Edit Principal Repayment Schedule Value']");
    private final By repaymentValueDate = By.id("repayment_schedule_value_value_end_date");
    private final By repaymentPaymentDate = By.id("repayment_schedule_value_repayment_date");
    private final By useForXIRR = By.xpath("//input[@id='fee_use_for_xirr']//parent::div/label");
    private final By useForEIR = By.xpath("//input[@id='fee_use_for_eir']//parent::div/label");
    private final By securitization_tab = By.id("lfd-lien-details-tab");
    private final By lienFD_Options = By.xpath("(//a[@data-toggle='dropdown'])[6]");
    private final By availableFDs = By.xpath("//a[text()='Lien Available Fixed Deposits']");
    private final By FD_checkBox = By.xpath("(//tbody)[12]/tr[1]/td[1]/div/label");
    private final By FD_submit = By.xpath("(//input[@type='submit'])[1]");
    private final By loanFacilityROCLink = By.xpath("//li[text()='Liability']//following-sibling::li[2]/a");
    private final By drawdown_outstanding_value = By.id("drawdown_outstanding_value");
    private final By actual_payments_sum = By.id("actual_payments_sum");
    private final By addEmiRepaymentSchedule = By.xpath("(//a[text()='Add Emi Repayment Schedule'])");
    private final By fix = By.xpath("//select[@id='emi_schedule_equated_property_fix']//following-sibling::div/div[1]");
    private final By emiPaymentFrequency = By.xpath("//select[@id='emi_schedule_equated_property_payment_frequency']" +
            "/following-sibling::div/div[1]");
    private final By emiAmount = By.id("emi_schedule_equated_property_emi_amount");
    private final By emiPaymentDay = By.xpath("//select[@id='emi_schedule_equated_property_payment_day']" +
            "/following-sibling::div/div[1]");
    private final By emiPaymentConvension = By.xpath("//select[@id='emi_schedule_equated_property_payment_convention']" +
            "/following-sibling::div/div[1]");
    private final By emiRoundingMode = By.xpath("//select[@id='emi_schedule_equated_property_rounding_mode']" +
            "/following-sibling::div/div[1]");
    private final By emiTDSRoundingMode = By.xpath("//select[@id='emi_schedule_equated_property_tds_rounding_mode']" +
            "/following-sibling::div/div[1]");
    private final By interestCalculatedOn = By.xpath("//select[@id='repayment_schedule_equated_property_interest_calculated_on']" +
            "//following-sibling::div/div[1]");
    private final By dayCountConvention = By.xpath("//select[@id='repayment_schedule_equated_property_day_count_convention']//following-sibling::div/div[1]");
    private final By useFirstDay = By.xpath("//label[text()='Use first day for calculation']");
    private final By useLastDay = By.xpath("//label[text()='Use last day for calculation']");
    private final By daysInYearType = By.xpath("//select[@id='days_in_a_year_type_select']/" +
            "following-sibling::div/div[1]");
    private final By daysInYearNumber = By.id("days_in_a_year_number");
    private final By disbursementOptions = By.xpath("//p[text()='Disbursements']//parent::div/div[2]/a/i");
    private final By addDisbursement = By.xpath("(//a[text()='Add Disbursement'])");
    private final By disbursement_value_date = By.xpath("//input[@id='disbursement_value_date']");
    private final By disbursement_transaction_date = By.xpath("//input[@id='disbursement_transaction_date']");
    private final By disbursement_amount = By.id("disbursement_amount");
    private final By disbursementType = By.xpath("//select[@id='disbursement_disbursement_type']//following-sibling::div/div[1]");
    private final By disbursement_remarks = By.id("disbursement_remarks");
    private final By useRestOptions = By.xpath("//p[text()='Reset Dates']//parent::div/div[2]/a/i");
    private final By borrowing_reset_schedule_frequency = By.xpath("//select[@id='borrowing_reset_schedule_frequency']//following-sibling::div/div[1]");
    private final By borrowing_reset_schedule_reset_day = By.xpath("//select[@id='borrowing_reset_schedule_reset_day']//following-sibling::div/div[1]");
    private final By addSchedule = By.xpath("//a[text()='Add schedule']");
    private final By previewResetScheduleDates = By.id("previewResetScheduleDates");



    public SDLFDrawdownPage moveToHamburgerMenu() {
        moveToElement(DriverManager.getDriver().findElement(hamburgerMenu), "HamburgerMenu");
        return this;
    }

    public SDLFDrawdownPage clickEdit() {
        clickk(btn_Edit, WaitStrategy.CLICKABLE, "Edit icon");
        return this;
    }
    public SDLFDrawdownPage editNetAmount(int amount) {
        String value = getAttribute(NetAmount, "value", WaitStrategy.VISIBLE, "Net Amount");
        double finalAmount = Double.parseDouble(value) - amount;
        clearDate(NetAmount).sendText(NetAmount, String.valueOf(finalAmount), WaitStrategy.PRESENCE, "amount");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        return this;
    }

    public NewSDLFDrawdownPage clickContinue() {
        jsClick(continuee, WaitStrategy.CLICKABLE, "Continue button");
        return new NewSDLFDrawdownPage();
    }

    public SDLFDrawdownPage clickRepaymentScheduleOptions() {
        scrollIntoView(repaymentScheduleOptions);
        clickk(repaymentScheduleOptions, WaitStrategy.CLICKABLE, "Repayment Schedule Options");
        return this;
    }

    public SDLFDrawdownPage selectAddEquatedPrincipalSchedule() {
        clickk(addPrincipalEquatedSchedule, WaitStrategy.CLICKABLE, "Equated schedule");
        return this;

    }
    public SDLFDrawdownPage clickUploadPrincipalSchedule() {
        clickk(uploadPrincipalSchedule, WaitStrategy.CLICKABLE, "Principal Schedule template");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        return this;
    }

    public SDLFDrawdownPage clickUploadInterestSchedule() {
        clickk(uploadInterestSchedule, WaitStrategy.CLICKABLE, "Interest Schedule template");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        return this;
    }

    public SDLFDrawdownPage selectPrincipalPayout(String value) {
        clickk(principalPayout, WaitStrategy.CLICKABLE, "Principal Payout");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String payout = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(payout, value);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, value);
        return this;
    }

    public SDLFDrawdownPage selectPrincipalPaymentDay(String day) {
        clickk(principalPaymentDay, WaitStrategy.CLICKABLE, "Principal payment day");
        String pday = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(pday, day);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, day);
        return this;
    }

    public SDLFDrawdownPage selectPrincipalPaymentConvention(String value) {
        clickk(principalPaymentConvension, WaitStrategy.CLICKABLE, "Principal payment convention");
        String pc = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(pc, value);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, value);
        return this;
    }

    public SDLFDrawdownPage selectPrincipalRounding(String mode) {
        jsClick(principalRoundingMode, WaitStrategy.CLICKABLE, "Principal Rounding Mode");
        String rounding = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(rounding, mode);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, mode);
        return this;
    }

    public SDLFDrawdownPage clickOnPreview() {
        scrollIntoView(btn_preview);
        jsClick(btn_preview, WaitStrategy.CLICKABLE, "Preview");
        return this;
    }

    public SDLFDrawdownPage clickOnGenerateSchedule() {
        clickk(btn_generateSchedule, WaitStrategy.CLICKABLE, "Generate Schedule");
        return this;
    }

    public String getUnallocatedPrincipal() {
        return getText(unallocatedPrincipal, WaitStrategy.VISIBLE, "unallocated principal");
    }

    public String getTotalInterest() {
        return getText(totalInterest, WaitStrategy.VISIBLE, "Total Interest");
    }

    public SDLFDrawdownPage checkUnallocatedPrincipal() {
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

    public void checkTotalInterest() {
        scrollIntoView(totalInterest);
        for (int i = 0; i < 5; i++) {
            if (!getTotalInterest().equals("0.00")) {
                break;
            } else {
                Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);
                DriverManager.getDriver().navigate().refresh();
            }
        }
    }
    public void checkIRScheduleGenerated() {
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        IntStream.rangeClosed(1, list.size()).filter(i -> getText(By.xpath("(//tbody)[6]/tr[" + i + "]/td[6]"), WaitStrategy.VISIBLE, "Interest Accrual").contains("0.00")).forEach(i -> {
            Uninterruptibles.sleepUninterruptibly(15, TimeUnit.SECONDS);
            DriverManager.getDriver().navigate().refresh();
        });
    }

    public SDLFDrawdownPage clickEditRepayment(int num) {
        String makepayment = "(//tbody)[6]/tr[%replace%]/td[16]/a[1]";
        String newxpath = XpathUtils.getXpath(makepayment, String.valueOf(num));
        scrollIntoView(By.xpath(newxpath));
        scrollHorizontally(By.xpath(newxpath));
        if (isEnabled(By.xpath(newxpath), "Edit payment icon")) {
            jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, "Edit Payment");
        }
        return this;

    }
    public SDLFDrawdownPage clickEditPrincipalSchedule() {
        jsClick(btn_EditPrincipalSchedule, WaitStrategy.CLICKABLE, "Edit principal schedule button");
        return this;
    }
    public String getRepaymentScheduleStatus(int num) {
        String status = "(//tbody)[6]/tr[%replace%]/td[10]";
        String newxpath = XpathUtils.getXpath(status, String.valueOf(num));
        scrollIntoView(By.xpath(newxpath));
        scrollHorizontally(By.xpath(newxpath));
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        return getText(By.xpath(newxpath), WaitStrategy.VISIBLE, " - Repayment Schedule Status");

    }

    public SDLFDrawdownPage selectAd_HocPrincipalSchedule() {
        scrollIntoView(repaymentScheduleOptions);
        clickk(add_AdHoc_PrincipalSchedule, WaitStrategy.CLICKABLE, "Ad-Hoc schedule");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        isDisplayed(principalScheduleForm, "Principal schedule form");
        return this;

    }

    public SDLFDrawdownPage selectValueDate(String text) {
        clearDate(ad_HocValueDate).
                actionSendkeys(text);
        DriverManager.getDriver().findElement(ad_HocValueDate).sendKeys(Keys.ENTER);
        return this;
    }

    public SDLFDrawdownPage selectPaymentDate(String text) {
        jsClick(ad_HocPaymentDate, "payment date");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        clearDate(ad_HocPaymentDate);
        actionSendkeys(text);
        return this;
    }

    public SDLFDrawdownPage clickSubmit() {
        scrollIntoView(btn_Submit);
        jsClick(btn_Submit, WaitStrategy.CLICKABLE, "Submit");
        return this;
    }
    public void click_Fee_Submit() {
        scrollIntoView(btn_Fee_Submit);
        jsClick(btn_Fee_Submit, WaitStrategy.CLICKABLE, "Submit");
    }
    public void click_Disbursement_Submit() {
        scrollIntoView(btn_Disbursement_Submit);
        jsClick(btn_Disbursement_Submit, WaitStrategy.CLICKABLE, "Disbursement Submit");
    }
    public SDLFDrawdownPage click_Ad_Hoc_ScheduleSubmit(){
        clickk(adHoc_Schedule_Submit,WaitStrategy.CLICKABLE,"Ad hoc schedule submit button");
        return this;
    }
    public SDLFDrawdownPage click_MakePayment_Submit(){
        jsClick(makePayments_Submit,WaitStrategy.CLICKABLE,"Submit button");
        return this;
    }

    public String getInterestNetAmount(int num) {
        DriverManager.getDriver().navigate().refresh();
        return getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[9]"), WaitStrategy.VISIBLE, "Net Interest Amount");
    }

    public SDLFDrawdownPage clickEditInterestSchedule() {
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        clickk(btn_EditInterestSchedule, WaitStrategy.CLICKABLE, "Edit interest schedule button");
        return this;
    }
    public SDLFDrawdownPage clickViewPayments(int num) {
        String makepayment = "(//tbody)[6]/tr[%replace%]/td[16]/a[3]";
        String newxpath = XpathUtils.getXpath(makepayment, String.valueOf(num));
        scrollIntoView(By.xpath(newxpath));
        scrollHorizontally(By.xpath(newxpath));
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, "view Payment");
        return this;
    }
    public SDLFDrawdownPage clickViewPrincipalSchedule() {
        clickk(btn_ViewPrincipalSchedule, WaitStrategy.CLICKABLE, "View principal schedule button");
        return this;
    }

    public SDLFDrawdownPage clickViewInterestSchedule() {
        isEnabled(btn_ViewInterestSchedule, "View Interest schedule button");
        clickk(btn_ViewInterestSchedule, WaitStrategy.CLICKABLE, "View Interest schedule button");
        return this;
    }


    public SDLFDrawdownPage uploadPrincipalSchedule() {
        try {
            Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
            WebElement dropzoneElement = wait.until(ExpectedConditions.elementToBeClickable(repaymentDropZone));
            dropzoneElement.click();
            WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='file']")));
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].style.display = 'block';", fileInput);
            String filePath = FrameworkConstants.get_SDLF_UploadPrincipalFilePath();
            File file = new File(filePath);
            if (file.exists()) {
                fileInput.sendKeys(filePath);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return this;
    }

    public SDLFDrawdownPage enterLiability_upload_name() {
        clickk(liability_upload_name, WaitStrategy.CLICKABLE, "liability upload name");
        sendText(liability_upload_name, "Schedule", WaitStrategy.PRESENCE, "liability upload name");
        clickk(uploadSchedule_Submit, WaitStrategy.CLICKABLE, "proceed button");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        scrollIntoView(uploadSchedule_Submit);
        clickk(uploadSchedule_Submit, WaitStrategy.CLICKABLE, "proceed button");
        return this;
    }

    public SDLFDrawdownPage checkUploadIsCompleted() {
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        for (int i = 0; i <= 5; i++) {
            if (!isDisplayed(uploadRefresh, "refresh")) {
                break;
            } else {
                clickk(uploadRefresh, WaitStrategy.PRESENCE, "Upload refresh");
                Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);
            }
        }
        return this;
    }

    public SDLFDrawdownPage clickBeginImport() {
        clickk(btn_BeginImport, WaitStrategy.CLICKABLE, "Begin Import");
        return this;
    }

    public String getDrawdownEndDate() {
        return getText(drawdownEndDate, WaitStrategy.VISIBLE, "End Date");
    }

    public SDLFDrawdownPage generate_LF_AdHoc_Principal_Schedule(String valueDate) {
       clickRepaymentScheduleOptions().selectAd_HocPrincipalSchedule()
                .selectValueDate(valueDate)
                .click_Ad_Hoc_ScheduleSubmit().checkUnallocatedPrincipal();
        return this;
    }


    public SDLFDrawdownPage generate_LF_Equated_Principal_Schedule() {
        clickRepaymentScheduleOptions()
                .selectAddEquatedPrincipalSchedule()
                .selectPrincipalPayout("Annually").selectPrincipalPaymentDay("On 2nd")
                .selectPrincipalPaymentConvention("FOLW").
                selectPrincipalRounding("NONE").clickOnPreview().clickOnGenerateSchedule()
                .checkUnallocatedPrincipal();
        return this;
    }
    public SDLFDrawdownPage generate_LF_Equated_Principal_Schedule(String day, String paymentFrequency, String convention) {
        clickRepaymentScheduleOptions()
                .selectAddEquatedPrincipalSchedule()
                .selectPrincipalPayout(paymentFrequency).selectPrincipalPaymentDay(day)
                .selectPrincipalPaymentConvention(convention).
                selectPrincipalRounding("NONE").clickOnPreview().clickOnGenerateSchedule()
                .checkUnallocatedPrincipal();
        return this;
    }
    public SDLFDrawdownPage generate_LF_Equated_Principal_Schedule(String day) {
        clickRepaymentScheduleOptions()
                .selectAddEquatedPrincipalSchedule()
                .selectPrincipalPayout("Annually").selectPrincipalPaymentDay(day)
                .selectPrincipalPaymentConvention("FOLW").
                selectPrincipalRounding("NONE").clickOnPreview().clickOnGenerateSchedule()
                .checkUnallocatedPrincipal();
        return this;
    }

    public SDLFDrawdownPage clickMakePayments(int num) {
        String makepayment = "(//tbody)[6]/tr[%replace%]/td[16]/a[2]";
        String newxpath = XpathUtils.getXpath(makepayment, String.valueOf(num));
        scrollIntoView(By.xpath(newxpath));
        scrollHorizontally(By.xpath(newxpath));
        if (isEnabled(By.xpath(newxpath), "make payment icon")) {
            jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, "Make Payment");
        }
        return this;
    }
    public SDLFDrawdownPage selectUseFirstDayForCalculation() {
        clickk(useFirstDay, WaitStrategy.CLICKABLE, "Use First day for calculation check box");
        return this;
    }
    public SDLFDrawdownPage selectUseLastDayForCalculation() {
        clickk(useLastDay, WaitStrategy.CLICKABLE, "Use Last day for calculation check box");
        return this;
    }

    public SDLFDrawdownPage selectPaymentType(String type) {
        clickk(paymentType, WaitStrategy.CLICKABLE, "Payment Type");
        actionSendkeys(type);
        return this;
    }

    public void clickDeletePayment() {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        for (int j = 0; j < 3; j++) {
            try {
                scrollIntoView(btn_DeletePayment);
                scrollHorizontally(btn_DeletePayment);
                isDisplayed(btn_DeletePayment, "Delete button");
                doubleClick(btn_DeletePayment);
                Alert al = DriverManager.getDriver().switchTo().alert();
                al.accept();
                break;
            } catch (NoAlertPresentException e) {
                Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            }
        }
    }
    public SDLFDrawdownPage selectOperatingAccount() {
        clickk(liability_bank_account, WaitStrategy.CLICKABLE, "Operating account");
        String acnt = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(acnt, "BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)");
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, "Operating account");
        return this;
    }

    public SDLFDrawdownPage selectPaymentAccount(String payAcnt) {
        clickk(payment_account, WaitStrategy.CLICKABLE, "Payment account");
        actionSendkeys(payAcnt);
        if (getText(payment_account, WaitStrategy.VISIBLE, "payment account")
                .equalsIgnoreCase("Through Account")) {
            clickk(through_account, WaitStrategy.CLICKABLE, "Through account");
            actionSendkeys("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)");
        } else if (getText(payment_account, WaitStrategy.VISIBLE, "payment account")
                .equalsIgnoreCase("Counterparty Bank Account")) {
            clickk(counterparty_account, WaitStrategy.CLICKABLE, "counterparty bank account");
            actionSendkeys("AUTO1234 (INR) (AUTOMATION_PARTY)");
        }
        return this;
    }

    public SDLFDrawdownPage enterNotes(String text) {
        sendText(paymentNotes, text, WaitStrategy.PRESENCE, "Notes");
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
            Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
            li[i - 1] = getText(By.xpath(newxpath), WaitStrategy.VISIBLE, " - Principal Schedule Status");
        });


        return li;
    }
    public SDLFDrawdownPage generate_LF_Equated_Interest_Schedule() {
        clickRepaymentScheduleOptions()
                .selectAddEquatedInterestSchedule()
                .selectIRFrequency("Annually")
                .IRPaymentDay("On 3rd").selectInterestPaymentConvention("FOLW")
                .selectInterestRounding("NONE").TDSRounding("NONE")
                .clickOnPreview().clickOnGenerateSchedule()
                .checkTotalInterest();

        return this;
    }
    public SDLFDrawdownPage generate_LF_Equated_Interest_Schedule(String day) {
        clickRepaymentScheduleOptions()
                .selectAddEquatedInterestSchedule()
                .selectIRFrequency("Annually")
                .IRPaymentDay(day).selectInterestPaymentConvention("FOLW")
                .selectInterestRounding("NONE").TDSRounding("NONE")
                .clickOnPreview().clickOnGenerateSchedule()
                .checkTotalInterest();

        return this;
    }

    public SDLFDrawdownPage generate_LF_Equated_Interest_Schedule(String day, String frequency, String convention) {
        clickRepaymentScheduleOptions()
                .selectAddEquatedInterestSchedule()
                .selectIRFrequency(frequency)
                .IRPaymentDay(day).selectInterestPaymentConvention(convention)
                .selectInterestRounding("NONE").TDSRounding("NONE")
                .clickOnPreview().clickOnGenerateSchedule()
                .checkTotalInterest();

        return this;
    }
    public SDLFDrawdownPage selectAddEquatedInterestSchedule() {
        clickk(addEquatedIRSchedule, WaitStrategy.CLICKABLE, "Equated schedule");
        return this;

    }

    public SDLFDrawdownPage selectIRFrequency(String value) {
        clickk(IRFrequency, WaitStrategy.CLICKABLE, "Interest Frequency");
        String freq = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(freq, value);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, value);
        return this;
    }

    public SDLFDrawdownPage IRPaymentDay(String value) {
        clickk(IRPaymentDay, WaitStrategy.CLICKABLE, "Interest payment day");
        String freq = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(freq, value);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, value);
        return this;
    }

    public SDLFDrawdownPage selectCompoundingFrequency(String value) {
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

    public SDLFDrawdownPage editRepaymentScheduleDates(int incrementValueDate, int incrementPaymentDate) {
        String valuedate = getAttribute(repaymentValueDate, "value", WaitStrategy.VISIBLE, "value date");
        String paydate = getAttribute(repaymentPaymentDate, "value", WaitStrategy.VISIBLE, "payment date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date1 = LocalDate.parse(valuedate, formatter);
        LocalDate date2 = LocalDate.parse(paydate, formatter);
        LocalDate incrementedDate1 = date1.plusDays(incrementValueDate);
        LocalDate incrementedDate2 = date2.plusDays(incrementPaymentDate);
        String resultDateString1 = incrementedDate1.format(formatter);
        String resultDateString2 = incrementedDate2.format(formatter);
        clearDate(repaymentValueDate).sendText(repaymentValueDate, resultDateString1, WaitStrategy.PRESENCE, "value date");
        clearDate(repaymentPaymentDate).sendText(repaymentPaymentDate, resultDateString2, WaitStrategy.PRESENCE, "payment date");
        return this;
    }

    public SDLFDrawdownPage selectInterestPaymentConvention(String value) {
        scrollIntoView(interestPaymentConvension);
        jsClick(interestPaymentConvension, WaitStrategy.CLICKABLE, "interest payment convention");
        String pc = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(pc, value);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, value);
        return this;
    }

    public SDLFDrawdownPage selectInterestRounding(String mode) {
        jsClick(principalRoundingMode, WaitStrategy.CLICKABLE, "Interest Rounding Mode");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String rounding = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(rounding, mode);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, mode);
        return this;

    }

    public SDLFDrawdownPage TDSRounding(String mode) {
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

    public SDLFDrawdownPage deactivate_Repayment_Schedule() {
        for (int i = 0; i < 10; i++) {
            try {
                doubleClick(deactivate_RepaymentSchedules);
                Alert al = DriverManager.getDriver().switchTo().alert();
                al.accept();
                break;
            } catch (NoAlertPresentException e) {
                Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            }

        }
        return this;
    }

    public SDLFDrawdownPage selectAd_HocInterestSchedule() {
        clickk(add_adHoc_IRSchedule, WaitStrategy.CLICKABLE, "Ad-Hoc schedule");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        isDisplayed(principalScheduleForm, "Interest schedule form");
        return this;

    }

    public SDLFDrawdownPage uploadInterestSchedule() {
        try {
            Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
            WebElement dropzoneElement = wait.until(ExpectedConditions.elementToBeClickable(repaymentDropZone));
            dropzoneElement.click();
            WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='file']")));
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].style.display = 'block';", fileInput);
            String filePath = FrameworkConstants.get_SDLF_UploadInterestFilePath();
            File file = new File(filePath);
            if (file.exists()) {
                fileInput.sendKeys(filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this;
    }

    public SDLFDrawdownPage selectUseForXirr() {
        moveToElement(DriverManager.getDriver().findElement(useForXIRR), "Use for Xirr check box");
        clickk(useForXIRR, WaitStrategy.CLICKABLE, "Use for Xirr check box");
        return this;
    }

    public SDLFDrawdownPage selectUseForEir() {
        moveToElement(DriverManager.getDriver().findElement(useForEIR), "Use for Eir check box");
        clickk(useForEIR, WaitStrategy.CLICKABLE, "Use for EIR check box");
        return this;
    }


    public SDLFDrawdownPage generate_LF_AdHoc_Interest_Schedule() {
        clickRepaymentScheduleOptions().selectAd_HocInterestSchedule()
                .selectValueDate(getText(drawdownEndDate, WaitStrategy.VISIBLE, "End Date"))
                .click_Ad_Hoc_ScheduleSubmit();
        return this;
    }
    public SDLFDrawdownPage generate_LF_AdHoc_Interest_Schedule(String valueDate, String PaymentDate) {
      clickRepaymentScheduleOptions(). selectAd_HocInterestSchedule()
                .selectValueDate(valueDate)
                .selectPaymentDate(PaymentDate)
                .click_Ad_Hoc_ScheduleSubmit().checkIRScheduleGenerated();
        return this;
    }


    public SDLFDrawdownPage click_PrepaymentOptions() {
        jsClick(btn_makePrepaymentsOptions, "Prepayment Options button");
        return this;
    }

    public SDLFDrawdownPage select_MakePrepayments() {
        jsClick(makePrepayment, "Prepayment");
        return this;
    }

    public SDLFDrawdownPage enterPrepaymentPaymentDate(String text) {
        clickk(prepaymentPaymentDate, WaitStrategy.CLICKABLE, "payment date");
        clearDate(prepaymentPaymentDate)
                .sendText(prepaymentPaymentDate, text, WaitStrategy.PRESENCE, "Prepayment payment date");
        return this;
    }

    public SDLFDrawdownPage enterPrepaymentValueDate(String text) {
        clickk(prepaymentValueDate, WaitStrategy.CLICKABLE, "value date");
        clearDate(prepaymentValueDate)
                .sendText(prepaymentValueDate, text, WaitStrategy.PRESENCE, "Prepayment value date");
        return this;
    }


    public SDLFDrawdownPage enterPrepaymentAmount(String text) {
        WebElement ele = DriverManager.getDriver().findElement(prepaymentAmount);
        ele.clear();
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        sendText(prepaymentAmount, text, WaitStrategy.PRESENCE, "Prepayment Amount");
        return this;
    }

    public double getPrepaymentPenalty() {

        return CommonUtils.stringToDouble(getText(prepaymentPenaltyAmount, WaitStrategy.VISIBLE, "penalty amount"));
    }

    public SDLFDrawdownPage make_prepayments() {
        click_PrepaymentOptions().select_MakePrepayments()
                .enterPrepaymentPaymentDate("21/09/2027")
                .enterPrepaymentValueDate("21/09/2027")
                .enterPrepaymentAmount("20000")
                .click_MakePayment_Submit();
        return this;
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
            sendText(prepaymentAgainstPenaltyAmount, amount, WaitStrategy.PRESENCE, "Amount");
            sendText(prepaymentsNotes, "NA", WaitStrategy.PRESENCE, "Notes");
            jsClick(makePayments_Submit, WaitStrategy.CLICKABLE, "Make payment Submit button");
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        });

    }

    public String[] getPrepaymentsStatus() {
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

    public SDLFDrawdownPage clickAttachedDocTab() {
        clickk(drawdownAttachedDocumentsTab, WaitStrategy.CLICKABLE, "Attached Documents tab");
        return this;

    }

    public SDLFDrawdownPage clickUploadDocuments() {
        jsClick(Btn_uploadDocs, "Upload Documents button");
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        return this;
    }
    public SDLFDrawdownPage uploadFile() {
        try {
            Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
            WebElement dropzoneElement = wait.until(ExpectedConditions.elementToBeClickable(dropzone));
            dropzoneElement.click();
            WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='file']")));
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].style.display = 'block';", fileInput);
            String filePath = FrameworkConstants.getUploadAttachedDocFilePath();
            File file = new File(filePath);
            if (file.exists()) {
                fileInput.sendKeys(filePath);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return this;
    }

    public SDLFDrawdownPage clickClose() {
        clickk(btn_close, WaitStrategy.CLICKABLE, "Close button");
        return this;

    }
    public void clickClose_AttachedDocs() {
        clickk(btn_close, WaitStrategy.CLICKABLE, "close button");
    }
    public String getAttachedDocFileName() {
        for (int i = 0; i < 5; i++) {
            clickAttachedDocTab();
            if (!isDisplayed(By.xpath("(//tbody)[7]"), "Attached doc table")) {
                Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
                DriverManager.getDriver().navigate().refresh();
            } else {
                break;
            }
        }
        return getText(By.xpath("(//tbody)[7]/tr[1]/td[3]"), WaitStrategy.VISIBLE, "File name");
    }


    public SDLFDrawdownPage clickDeactivatedSchedulesTab() {
        clickk(deactivatedSchedulesTab, WaitStrategy.CLICKABLE, " Deactivated schedules tab");
        return this;
    }

    public boolean checkDeactivatedSchedule() {
        clickk(deactivatedSchedules, WaitStrategy.CLICKABLE, "Schedules");
        return isDisplayed(deactivatedPrincipalSchedule, "Deactivated Principal Schedule") ||
                isDisplayed(deactivatedInterestSchedule, "Deactivated Interest schedule");
    }

    public SDLFDrawdownPage clickCallSchedulesTab() {
        jsClick(callSchedulesTab, "Call Schedules Tab");
        return this;
    }

    public SDLFDrawdownPage clickCallSchedulesOptions() {
        clickk(btn_callScheduleOptions, WaitStrategy.CLICKABLE, "Call schedules Option button");
        return this;
    }

    public SDLFDrawdownPage clickEquatedCallSchedule() {
        clickk(equatedCallSchedule, WaitStrategy.CLICKABLE, " Equated Call Schedule");
        return this;
    }

    public SDLFDrawdownPage clickAdHocCallSchedule() {
        clickk(ad_HocCallSchedule, WaitStrategy.CLICKABLE, " Ad-Hoc Call Schedule");
        return this;
    }

    public SDLFDrawdownPage selectCallFrequency(String frequency) {
        clickk(callFrequency, WaitStrategy.CLICKABLE, "Call Frequency");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String rounding = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(rounding, frequency);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, frequency);
        return this;

    }

    public SDLFDrawdownPage selectCallExerciseDay(String day) {
        clickk(callDay, WaitStrategy.CLICKABLE, "Call Exercise Day");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String rounding = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(rounding, day);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, day);
        return this;
    }

    public SDLFDrawdownPage clickCallSchedulePreview() {
        clickk(btn_preview, WaitStrategy.CLICKABLE, "preview button");
        return this;
    }

    public SDLFDrawdownPage enterCallDate(String text) {
        jsClick(callDate, WaitStrategy.CLICKABLE, "Date filed");
        clearDate(callDate, WaitStrategy.PRESENCE).
                sendText(callDate, text, WaitStrategy.PRESENCE, " Date");
        DriverManager.getDriver().findElement(callDate).sendKeys(Keys.ENTER);
        return this;
    }

    public void clickDeactivate_callSchedule() {
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
    }

    public SDLFDrawdownPage clickFeetab() {
        jsClick(feeTab, WaitStrategy.CLICKABLE, " Fee Tab");
        return this;
    }

    public SDLFDrawdownPage clickAddFee() {
        jsClick(btn_AddFee, WaitStrategy.CLICKABLE, " Add Fee button");
        return this;
    }

    public SDLFDrawdownPage feeType(String feeType) {
        jsClick(feeTypee, WaitStrategy.CLICKABLE, "Fee Type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, feeType);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, feeType);
        return this;
    }

    public SDLFDrawdownPage amountType(String amountType) {
        jsClick(amountTypee, WaitStrategy.CLICKABLE, "Amount Type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, amountType);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, amountType);
        return this;
    }

    public SDLFDrawdownPage enterFeeValue(String feevalue) {
        WebElement ele = DriverManager.getDriver().findElement(feeValue);
        ele.clear();
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        sendText(feeValue, feevalue, WaitStrategy.PRESENCE, "Fee value");
        return this;
    }

    public SDLFDrawdownPage enterRemarks(String remarks) {
        sendText(feeRemarks, remarks, WaitStrategy.PRESENCE, "Fee Remarks");
        return this;
    }

    public String getFeeStatus(String var) {
        String ar = "//table[@id='fees_container']/tbody/tr/td[%replace%]";
        String newxpath = XpathUtils.getXpath(ar, var);
        return getText(By.xpath(newxpath), WaitStrategy.VISIBLE, "Fee Status");
    }

    public SDLFDrawdownPage cancelFee() {
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

    public SDLFDrawdownPage clickAnalyticsTab() {
        jsClick(analyticsTab, WaitStrategy.CLICKABLE, "Analytics tab");
        return this;
    }

    public void checkXirrEirValues() {
        for (int i = 0; i < 7; i++) {
            if (!getText(xirrValue, WaitStrategy.VISIBLE, "Xirr value").contains("XIRR : __.__%")
                    && !getText(eirValue, WaitStrategy.VISIBLE, "Eir value").contains("EIR : __.__%")) {
                break;
            } else {
                DriverManager.getDriver().navigate().refresh();
                clickAnalyticsTab();
                Uninterruptibles.sleepUninterruptibly(10, TimeUnit.SECONDS);
            }
        }
    }

    public String getXirrValue() {
        return getAnalyticsValue(getText(xirrValue, WaitStrategy.VISIBLE, "Xirr value"));
    }

    public String getEirValue() {
        return getAnalyticsValue(getText(eirValue, WaitStrategy.VISIBLE, "Eir value"));
    }

    public SDLFDrawdownPage clickCovenantsTab() {
        clickk(covenantsTab, WaitStrategy.CLICKABLE, "Covenants Tab");
        return this;
    }

    public SDLFDrawdownPage clickAddCovenants() {
        clickk(btn_addCovenants, WaitStrategy.CLICKABLE, "Add Covenants Button");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        return this;
    }

    public SDLFDrawdownPage covenantTemplate(String tem) {
        jsClick(covenants_Template, WaitStrategy.CLICKABLE, "Covenant Template");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, tem);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, tem);
        return this;
    }

    public SDLFDrawdownPage enterAccountablePerson(String text) {
        sendText(accountablePerson, text, WaitStrategy.PRESENCE, "Accountable Person");
        return this;
    }

    public SDLFDrawdownPage enterThirdParty(String text) {
        sendText(thirdParty, text, WaitStrategy.PRESENCE, "ThirdParty");
        return this;
    }

    public SDLFDrawdownPage enterTargetValue(String text) {
        WebElement ele = DriverManager.getDriver().findElement(targetValue);
        ele.clear();
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        sendText(targetValue, text, WaitStrategy.PRESENCE, "Target value");
        return this;
    }

    public SDLFDrawdownPage enterTargetDate(String text) {
        clearDate(targetDate, WaitStrategy.PRESENCE)
                .sendText(targetDate, text, WaitStrategy.PRESENCE, "Target Date");
        return this;
    }

    public SDLFDrawdownPage enterOffset(String text) {
        jsClick(offsetDays, "Offset Days");
        sendText(offsetDays, text, WaitStrategy.PRESENCE, "Offset Days");
        return this;
    }

    public SDLFDrawdownPage enterCovenantEndDate(String text) {
        jsClick(covenantsRemainderEndDate, " End Date");
        clearDate(covenantsRemainderEndDate, WaitStrategy.PRESENCE)
                .sendText(covenantsRemainderEndDate, text, WaitStrategy.PRESENCE, "End Date");
        return this;
    }

    public SDLFDrawdownPage selectMappingConditions(String text) {
        jsClick(covenants_MappingConditions, WaitStrategy.CLICKABLE, "Covenant Mapping conditions");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public SDLFDrawdownPage selectCovenantsEntity(String text) {
        jsClick(covenantsEntity, WaitStrategy.CLICKABLE, "Covenant Entity");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public SDLFDrawdownPage selectRatioName(String text) {
        jsClick(covenants_RatioName, WaitStrategy.CLICKABLE, "Covenant Ratio Name");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public SDLFDrawdownPage enterThresholdPercentage(String text) {
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
            if (!isDisplayed(By.xpath("(//tbody)[8]"), "Covenants table")) {
                Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
                DriverManager.getDriver().navigate().refresh();
            } else {

                break;
            }
        }
        return DriverManager.getDriver().findElements(By.xpath("(//tbody)[8]/tr")).size();
    }

    public SDLFDrawdownPage clickOnCreate() {
        clickk(btn_Submit, WaitStrategy.CLICKABLE, " Create button");
        return this;
    }

    public SDLFDrawdownPage clickLienDetails() {
        clickk(lienDetails, WaitStrategy.CLICKABLE, "Lien Details Tab");
        return this;
    }

    public SDLFDrawdownPage clickAddToLienFD() {
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, btn_addFD);
        scrollIntoView(btn_addFD);
        clickk(btn_addFD, WaitStrategy.CLICKABLE, " Add Button ");
        return this;
    }

    public int getLiedFDNum() {
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, By.xpath("(//tbody)[4]"));
        return DriverManager.getDriver().findElements(By.xpath("(//tbody)[4]/tr")).size();
    }

    public SDLFDrawdownPage clickCloseDrawdown() {
        clickk(btn_Close, WaitStrategy.CLICKABLE, "Close button");
        return this;
    }

    public SDLFDrawdownPage enterCloseNotes(String value) {
        sendText(close_note, value, WaitStrategy.VISIBLE, "Close Notes");
        return this;
    }

    public SDLFDrawdownPage click_Securitization() {
        clickk(securitization_tab, WaitStrategy.CLICKABLE, "Lien Details Tab");
        return this;
    }

    public SDLFDrawdownPage click_LienFD_Options() {
        clickk(lienFD_Options, WaitStrategy.CLICKABLE, "Lien FD options");
        return this;
    }

    public SDLFDrawdownPage selectAvailableFDs() {
        clickk(availableFDs, WaitStrategy.CLICKABLE, "Available FDs");
        return this;
    }

    public SDLFDrawdownPage selectFDs() {
        clickk(FD_checkBox, WaitStrategy.CLICKABLE, " FDs checkbox");
        return this;
    }

    public void click_FD_Submit() {
        scrollIntoView(FD_submit);
        for (int i = 0; i < 10; i++) {
            try {
                doubleClick(FD_submit);
                Alert al = DriverManager.getDriver().switchTo().alert();
                al.accept();
                break;
            } catch (NoAlertPresentException e) {
                Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            }

        }
    }
    public void clickSubmitToClose() {
        clickk(btn_Close_submit, WaitStrategy.CLICKABLE, "submit button");
    }

    public boolean closureDocumentsTabDisplayed() {
        return isDisplayed(closureDoctab, WaitStrategy.VISIBLE, "Closure documents tab");
    }

    public SDLFPage clickDeleteDrawdown() {
        for (int i = 0; i < 10; i++) {
            try {
                doubleClick(btn_deleteDrawdown);
                Alert al = DriverManager.getDriver().switchTo().alert();
                al.accept();
                break;
            } catch (NoAlertPresentException e) {
                Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            }

        }
        return new SDLFPage();
    }
    public String getDrawdownExtId(){
      return  getText(drawdownExtId,WaitStrategy.PRESENCE,"Drawdown external id");
    }

    public SDLFDrawdownPage create_New_SDLFDrawdown() {
        SDLFPage lf = new SDLFPage();
        lf.clickOptions().clickAddDrawdown().enterDrawdownExternalID(10)
                .selectPrepaymentsPenalty("12")
                .enterDrawdownLedgerID(8).selectPut_Call("Call")
                .enterLoanAcnt("Loan ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPaymentAcnt("NACH auto debit").clickNewDisbursement()
                .enterDisAmount(drawdownAmount[(int) (Math.random() * drawdownAmount.length)])
                .selectDisbursementType("Standard").clickNewIrSlab()
                .selectIRType("Floating").enterSpread(spread[(int) (Math.random() * spread.length)])
                .clickNewTDS().enterTDS("30").enterAdditionalInfo("NA").clickCreate();
        return this;
    }

    public SDLFPage clickLoanFacilityLink() {
        clickk(loanFacilityROCLink, WaitStrategy.CLICKABLE, " Loan Facility link");
        return new SDLFPage();
    }

    public double getSettledAmount(int num) {
        String[] ar = getText(By.xpath("(//tbody)[6]/tr[" + num + "]" +
                "/td[13]"), WaitStrategy.VISIBLE, "Principal amount").split(",");
        String principal = String.join("", ar);
        return Double.parseDouble(principal);
    }

    public double getPrincipalRepaidSum() {
        String[] principal = getAttribute(actual_payments_sum, "value", WaitStrategy.PRESENCE,
                "Principal repaid").split(",");
        return Double.parseDouble(String.join("", principal));

    }

    public double getPrincipalDrawn() {
        String[] ar = getText(By.xpath("//span[text()='Principal:']" +
                        "//parent::div//following-sibling::div/span"), WaitStrategy.VISIBLE,
                "Principal amount").split(",");
        String principal = String.join("", ar);
        return Double.parseDouble(principal);
    }

    public double getPrincipalOutstanding() {
        String principalOutstanding = getAttribute(drawdown_outstanding_value, "value",
                WaitStrategy.PRESENCE, "Principal outstanding");
        return Double.parseDouble(principalOutstanding);
    }

    public SDLFDrawdownPage selectAddEmiRepaymentSchedule() {
        clickk(addEmiRepaymentSchedule, WaitStrategy.CLICKABLE, "EMI Repayment schedule");
        return this;

    }
    public SDLFDrawdownPage selectFix(String value) {
        clickk(fix, WaitStrategy.CLICKABLE, "fix");
        String freq = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(freq, value);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, value);
        return this;
    }
    public SDLFDrawdownPage selectEmiPaymentFrequency(String value) {
        clickk(emiPaymentFrequency, WaitStrategy.CLICKABLE, "Payment Frequency");
        String freq = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(freq, value);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, value);
        return this;
    }
    public SDLFDrawdownPage enterEmiAmount(String EmiAmount) {
        clearDate(emiAmount).sendText(emiAmount, EmiAmount, WaitStrategy.PRESENCE, "amount");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        return this;
    }
    public SDLFDrawdownPage selectEmiPaymentDay(String day) {
        clickk(emiPaymentDay, WaitStrategy.CLICKABLE, "Emi payment day");
        String pday = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(pday, day);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, day);
        return this;
    }

    public SDLFDrawdownPage selectEmiPaymentConvention(String value) {
        clickk(emiPaymentConvension, WaitStrategy.CLICKABLE, "Emi payment convention");
        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        String pc = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(pc, value);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, value);
        return this;
    }
    public SDLFDrawdownPage emiInterestRounding(String mode) {
        clickk(emiRoundingMode, WaitStrategy.CLICKABLE, "Emi Interest Rounding Mode");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String rounding = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(rounding, mode);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, mode);
        return this;

    }

    public SDLFDrawdownPage emiTDSRounding(String mode) {
        scrollIntoView(emiTDSRoundingMode, WaitStrategy.VISIBLE);
        clickk(emiTDSRoundingMode, WaitStrategy.CLICKABLE, "Emi TDS Rounding Mode");
        if (getText(emiRoundingMode, WaitStrategy.VISIBLE, "Emi Interest Rounding").equalsIgnoreCase(mode)) {
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
    public double getOpeningPrincipal(int num) {
        scrollIntoView(By.xpath("(//tbody)[6]/tr[" + num + "]/td[4]"));
        String[] str = getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[4]")
                , WaitStrategy.VISIBLE, " Opening Principal").split(",");
        return Double.parseDouble(String.join("", str));
    }

    public double getInterestRate() {
        scrollIntoView(By.xpath("(//tbody)[1]/tr[1]/td[5]"));
        scrollHorizontally(By.xpath("(//tbody)[1]/tr[1]/td[5]"));
        String[] ir = getText(By.xpath("(//tbody)[1]/tr[1]/td[5]"), WaitStrategy.VISIBLE, "Interest rate").split("%");
        return Double.parseDouble(ir[0]) / 100;
    }
    public boolean isLeapYear(int year) {
        return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
    }

    public Object calculateInterestAmount(int num) {

        if (!getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[10]"), WaitStrategy.VISIBLE, "status").equals("Not Applicable")) {
            return getOpeningPrincipal(num) * getInterestRate() * getNumOfDays(num);
        }
        return null;
    }



    public double getNumOfDays(int num) {
        String sd = getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[2]"), WaitStrategy.VISIBLE, "start date");
        String ed = getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[3]"), WaitStrategy.VISIBLE, "end Date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (isLeapYear(LocalDate.parse(sd, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear())
                && isLeapYear(LocalDate.parse(ed, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear()) &&
                !getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[10]"), WaitStrategy.VISIBLE, "status").equals("Not Applicable")) {
            return (double) ChronoUnit.DAYS.between(LocalDate.parse(sd, formatter), LocalDate.parse(ed, formatter)) / 366;


        } else if (isLeapYear(LocalDate.parse(sd, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear())
                && isLeapYear(LocalDate.parse(ed, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear()) &&
                getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[10]"), WaitStrategy.VISIBLE, "status").equals("Not Applicable")) {
            String sdd = getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[2]"), WaitStrategy.VISIBLE, "start date");
            long firstPart = ChronoUnit.DAYS.between(LocalDate.parse(sdd, formatter), LocalDate.of(LocalDate.parse(sdd, formatter).getYear()
                    , 12, 31));
            double firstDate = (double) (firstPart + 1) / 365;
            long secPart = ChronoUnit.DAYS.between(LocalDate.of(LocalDate.parse(ed, formatter).getYear(), 1, 1),
                    LocalDate.parse(ed, formatter));
            double secondDate = (double) secPart / 366;
            return firstDate + secondDate;

        } else if (!isLeapYear(LocalDate.parse(sd, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear())
                && !isLeapYear(LocalDate.parse(ed, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear()) &&
                getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[10]"), WaitStrategy.VISIBLE, "status").equals("Not Applicable")) {
            String sdd = getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[2]"), WaitStrategy.VISIBLE, "start date");
            long firstPart = ChronoUnit.DAYS.between(LocalDate.parse(sdd, formatter), LocalDate.of(LocalDate.parse(sdd, formatter).getYear()
                    , 12, 31));
            double firstDate = (double) (firstPart + 1) / 366;
            long secPart = ChronoUnit.DAYS.between(LocalDate.of(LocalDate.parse(ed, formatter).getYear(), 1, 1),
                    LocalDate.parse(ed, formatter));
            double secondDate = (double) secPart / 365;
            return firstDate + secondDate;
        }
        return (double) ChronoUnit.DAYS.between(LocalDate.parse(sd, formatter), LocalDate.parse(ed, formatter)) / 365;
    }

    public SDLFDrawdownPage selectDayCountConvention(String value) {
        scrollIntoView(dayCountConvention);
        jsClick(dayCountConvention, WaitStrategy.CLICKABLE, "Day count convention");
        String freq = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(freq, value);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, value);
        return this;
    }
    public SDLFDrawdownPage selectConstantDays() {
        clickk(daysInYearType, WaitStrategy.CLICKABLE, "days in a year type");
        clickk(By.xpath("//div[text()='Constant days']"), WaitStrategy.CLICKABLE, "constant days");
        return this;
    }
    public SDLFDrawdownPage selectActual_days_as_per_financial_year() {
        clickk(daysInYearType, WaitStrategy.CLICKABLE, "days in a year type");
        clickk(By.xpath("//div[text()='Actual days as per financial year']"), WaitStrategy.CLICKABLE, "Actual days as per financial year");
        return this;
    }

    public SDLFDrawdownPage enterDaysInaYear(String days) {
        clickk(daysInYearNumber, WaitStrategy.CLICKABLE, "days in a year");
        sendText(daysInYearNumber, days, WaitStrategy.PRESENCE, "number of days");
        return this;
    }
    public SDLFDrawdownPage selectInterestCalculatedOnClosingBalance() {
        clickk(interestCalculatedOn, WaitStrategy.CLICKABLE, "Interest Calculated On field");
        String value = "//div[text()='Closing Principal']";
        clickk(By.xpath(value), WaitStrategy.CLICKABLE, "Closing principal");
        return this;
    }

    public Object calculateInterestAmountForSucceeding(int num) {

        if (!getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[10]"), WaitStrategy.VISIBLE, "status").equals("Not Applicable")) {
            return getOpeningPrincipal(num) * getInterestRate() * getSucceedingNumOfDays(num);
        }
        return null;
    }

    public double getSucceedingNumOfDays(int num) {
        String sd = getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[2]"), WaitStrategy.VISIBLE, "start date");
        String ed = getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[3]"), WaitStrategy.VISIBLE, "end Date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (isLeapYear(LocalDate.parse(sd, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear())
                && isLeapYear(LocalDate.parse(ed, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear()) &&
                !getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[10]"), WaitStrategy.VISIBLE, "status").equals("Not Applicable")) {
            return (double) ChronoUnit.DAYS.between(LocalDate.parse(sd, formatter), LocalDate.parse(ed, formatter)) / 366;


        } else if (!isLeapYear(LocalDate.parse(sd, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear())
                && isLeapYear(LocalDate.parse(ed, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear()) &&
                getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[10]"), WaitStrategy.VISIBLE, "status").equals("Not Applicable")) {
            String sdd = getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[2]"), WaitStrategy.VISIBLE, "start date");
            long firstPart = ChronoUnit.DAYS.between(LocalDate.parse(sdd, formatter), LocalDate.of(LocalDate.parse(sdd, formatter).getYear()
                    , 12, 31));
            double firstDate = (double) (firstPart) / 365;
            long secPart = ChronoUnit.DAYS.between(LocalDate.of(LocalDate.parse(ed, formatter).getYear(), 1, 1),
                    LocalDate.parse(ed, formatter)) + 1;
            double secondDate = (double) secPart / 366;
            return firstDate + secondDate;

        } else if (isLeapYear(LocalDate.parse(sd, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear())
                && !isLeapYear(LocalDate.parse(ed, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear())&&
                getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[10]"), WaitStrategy.VISIBLE, "status").equals("Not Applicable")) {
            String sdd = getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[2]"), WaitStrategy.VISIBLE, "start date");
            long firstPart = ChronoUnit.DAYS.between(LocalDate.parse(sdd, formatter), LocalDate.of(LocalDate.parse(sdd, formatter).getYear()
                    , 12, 31));
            double firstDate = (double) (firstPart) / 366;
            long secPart = ChronoUnit.DAYS.between(LocalDate.of(LocalDate.parse(ed, formatter).getYear(), 1, 1),
                    LocalDate.parse(ed, formatter)) + 1;
            double secondDate = (double) secPart / 365;
            return firstDate + secondDate;
        }
        return (double) ChronoUnit.DAYS.between(LocalDate.parse(sd, formatter), LocalDate.parse(ed, formatter)) / 365;
    }

    public Object calculateInterestAmountForFirstDayCalculation(int num) {

        if (!getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[10]"), WaitStrategy.VISIBLE, "status").equals("Not Applicable")) {
            return getOpeningPrincipal(num) * getInterestRate() * getNumOfDaysForUseFirstDayCalculation(num);
        }
        return null;
    }

    public double getNumOfDaysForUseFirstDayCalculation(int num) {
        String sd = getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[2]"), WaitStrategy.VISIBLE, "start date");
        String ed = getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[3]"), WaitStrategy.VISIBLE, "end Date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (isLeapYear(LocalDate.parse(sd, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear())
                && isLeapYear(LocalDate.parse(ed, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear()) &&
                !getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[10]"), WaitStrategy.VISIBLE, "status").equals("Not Applicable")) {
            return (double) ChronoUnit.DAYS.between(LocalDate.parse(sd, formatter), LocalDate.parse(ed, formatter)) / 366;


        } else if (isLeapYear(LocalDate.parse(sd, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear())
                && isLeapYear(LocalDate.parse(ed, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear()) &&
                getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[10]"), WaitStrategy.VISIBLE, "status").equals("Not Applicable")) {
            String sdd = getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[2]"), WaitStrategy.VISIBLE, "start date");
            long firstPart = ChronoUnit.DAYS.between(LocalDate.parse(sdd, formatter), LocalDate.of(LocalDate.parse(sdd, formatter).getYear()
                    , 12, 31));
            double firstDate = (double) (firstPart + 1) / 365;
            long secPart = ChronoUnit.DAYS.between(LocalDate.of(LocalDate.parse(ed, formatter).getYear(), 1, 1),
                    LocalDate.parse(ed, formatter));
            double secondDate = (double) secPart / 366;
            return firstDate + secondDate;

        } else if (!isLeapYear(LocalDate.parse(sd, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear())
                && !isLeapYear(LocalDate.parse(ed, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear())) {
            if(num==1){
                return ((double) ChronoUnit.DAYS.between(LocalDate.parse(sd, formatter), LocalDate.parse(ed, formatter)) + 1) / 365;

            }else if (getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[10]"), WaitStrategy.VISIBLE, "status").equalsIgnoreCase("Not Applicable")) {
                String sdd = getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[2]"), WaitStrategy.VISIBLE, "start date");
                long firstPart = ChronoUnit.DAYS.between(LocalDate.parse(sdd, formatter), LocalDate.of(LocalDate.parse(sdd, formatter).getYear()
                        , 12, 31));
                double firstDate = (double) (firstPart + 1) / 366;
                long secPart = ChronoUnit.DAYS.between(LocalDate.of(LocalDate.parse(ed, formatter).getYear(), 1, 1),
                        LocalDate.parse(ed, formatter));
                double secondDate = (double) secPart / 365;
                return firstDate + secondDate;
            }
        }
        return ((double) ChronoUnit.DAYS.between(LocalDate.parse(sd, formatter), LocalDate.parse(ed, formatter))) / 365;
    }

    public Object calculateInterestAmountForLastDayCalculation(int num, int size) {

        if (!getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[10]"), WaitStrategy.VISIBLE, "status").equals("Not Applicable")) {
            return getOpeningPrincipal(num) * getInterestRate() * getNumOfDaysForUseLastDayCalculation(num, size);
        }
        return null;
    }

    public double getNumOfDaysForUseLastDayCalculation(int num, int size) {
        String sd = getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[2]"), WaitStrategy.VISIBLE, "start date");
        String ed = getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[3]"), WaitStrategy.VISIBLE, "end Date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (isLeapYear(LocalDate.parse(sd, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear())
                && isLeapYear(LocalDate.parse(ed, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear()) &&
                !getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[10]"), WaitStrategy.VISIBLE, "status").equals("Not Applicable")) {
            if(num==size){
                return ((double) ChronoUnit.DAYS.between(LocalDate.parse(sd, formatter), LocalDate.parse(ed, formatter))+1) / 366;
            }
            return (double) ChronoUnit.DAYS.between(LocalDate.parse(sd, formatter), LocalDate.parse(ed, formatter)) / 366;

        } else if (isLeapYear(LocalDate.parse(sd, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear())
                && isLeapYear(LocalDate.parse(ed, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear()) &&
                getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[10]"), WaitStrategy.VISIBLE, "status").equals("Not Applicable")) {
            String sdd = getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[2]"), WaitStrategy.VISIBLE, "start date");
            long firstPart = ChronoUnit.DAYS.between(LocalDate.parse(sdd, formatter), LocalDate.of(LocalDate.parse(sdd, formatter).getYear()
                    , 12, 31));
            double firstDate = (double) (firstPart + 1) / 365;
            long secPart = ChronoUnit.DAYS.between(LocalDate.of(LocalDate.parse(ed, formatter).getYear(), 1, 1),
                    LocalDate.parse(ed, formatter));
            double secondDate = (double) secPart / 366;
            return firstDate + secondDate;

        } else if (!isLeapYear(LocalDate.parse(sd, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear())
                && !isLeapYear(LocalDate.parse(ed, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear())) {
            if(!(num ==1)&&getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[10]"), WaitStrategy.VISIBLE, "status").equalsIgnoreCase("Not Applicable")) {
                String sdd = getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[2]"), WaitStrategy.VISIBLE, "start date");
                long firstPart = ChronoUnit.DAYS.between(LocalDate.parse(sdd, formatter), LocalDate.of(LocalDate.parse(sdd, formatter).getYear()
                        , 12, 31));
                double firstDate = (double) (firstPart + 1) / 366;
                long secPart = ChronoUnit.DAYS.between(LocalDate.of(LocalDate.parse(ed, formatter).getYear(), 1, 1),
                        LocalDate.parse(ed, formatter));
                double secondDate = (double) secPart / 365;
                return firstDate + secondDate;
            }
        }
        return (double) ChronoUnit.DAYS.between(LocalDate.parse(sd, formatter), LocalDate.parse(ed, formatter)) / 365;
    }

    public Object calculateInterestAmountForClosingBalance(int num) {

        if (!getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[10]"), WaitStrategy.VISIBLE, "status").equals("Not Applicable")) {
            return interestForClosingBal(num);
        }
        return null;
    }

    public double interestForClosingBal(int num) {
        String sd = getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[2]"), WaitStrategy.VISIBLE, "start date");
        String ed = getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[3]"), WaitStrategy.VISIBLE, "end Date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (isLeapYear(LocalDate.parse(sd, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear())
                && isLeapYear(LocalDate.parse(ed, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear()) &&
                !getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[10]"), WaitStrategy.VISIBLE, "status").equals("Not Applicable")) {
            double IR1 = getOpeningPrincipal(num) * getInterestRate() * ((double) ChronoUnit.DAYS.between(LocalDate.parse(sd, formatter), LocalDate.parse(ed, formatter)) - 1) / 366;
            double IR2 = (getClosingPrincipal(num) * getInterestRate() * 1) / 366;
            return IR1 + IR2;
        } else if (!isLeapYear(LocalDate.parse(sd, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear())
                && isLeapYear(LocalDate.parse(ed, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear()) &&
                getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[10]"), WaitStrategy.VISIBLE, "status").equals("Not Applicable")) {
            String sdd = getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[2]"), WaitStrategy.VISIBLE, "start date");
            long firstPart = ChronoUnit.DAYS.between(LocalDate.parse(sdd, formatter), LocalDate.of(LocalDate.parse(sdd, formatter).getYear()
                    , 12, 31));
            double IR1 = getOpeningPrincipal(num) * getInterestRate() * (double) (firstPart) / 365;
            long secPart = ChronoUnit.DAYS.between(LocalDate.of(LocalDate.parse(ed, formatter).getYear(), 1, 1),
                    LocalDate.parse(ed, formatter));
            double IR2 = getOpeningPrincipal(num) * getInterestRate() * (double) secPart / 366;
            double IR3 = getClosingPrincipal(num) * getInterestRate() / 366;
            return IR1 + IR2 + IR3;

        } else if (isLeapYear(LocalDate.parse(sd, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear())
                && !isLeapYear(LocalDate.parse(ed, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear()) &&
                getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[10]"), WaitStrategy.VISIBLE, "status").equals("Not Applicable")) {
            String sdd = getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[2]"), WaitStrategy.VISIBLE, "start date");
            long firstPart = ChronoUnit.DAYS.between(LocalDate.parse(sdd, formatter), LocalDate.of(LocalDate.parse(sdd, formatter).getYear()
                    , 12, 31));
            double IR1 = getOpeningPrincipal(num) * getInterestRate() * (double) (firstPart) / 366;
            long secPart = ChronoUnit.DAYS.between(LocalDate.of(LocalDate.parse(ed, formatter).getYear(), 1, 1),
                    LocalDate.parse(ed, formatter));
            double IR2 = getOpeningPrincipal(num) * getInterestRate() * (double) secPart / 365;
            double IR3 = getClosingPrincipal(num) * getInterestRate() * 1 / 365;
            return IR1 + IR2 + IR3;
        }
        double IR1 = getOpeningPrincipal(num) * getInterestRate() * ((double) ChronoUnit.DAYS.between(LocalDate.parse(sd, formatter), LocalDate.parse(ed, formatter)) - 1) / 365;
        double IR2 = getClosingPrincipal(num) * getInterestRate() / 365;
        return IR1 + IR2;
    }
    public double getClosingPrincipal(int num) {
        scrollIntoView(By.xpath("(//tbody)[6]/tr[" + num + "]/td[15]"));
        String[] bal = getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[15]")
                , WaitStrategy.VISIBLE, "Closing Principal").split(",");
        return Double.parseDouble(String.join("", bal));
    }
    public SDLFDrawdownPage selectThirtyBy360() {
        clickk(daysInYearType, WaitStrategy.CLICKABLE, "days in a year type");
        clickk(By.xpath("//div[text()='Thirty by 360']"), WaitStrategy.CLICKABLE, "30/360");
        return this;
    }

    public Object calculateInterestAmountForConstantDays(int num, int days) {

        if (!getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[10]"), WaitStrategy.VISIBLE, "status").equals("Not Applicable")) {
            return getOpeningPrincipal(num) * getInterestRate() * getNumOfDaysForConstantDays(num, days);
        }
        return null;
    }
    public double getNumOfDaysForConstantDays(int num, int days) {
        String sd = getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[2]"), WaitStrategy.VISIBLE, "start date");
        String ed = getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[3]"), WaitStrategy.VISIBLE, "end Date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (isLeapYear(LocalDate.parse(sd, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear())
                && isLeapYear(LocalDate.parse(ed, DateTimeFormatter.ofPattern("dd/MM/yyyy")).getYear()) &&
                getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[10]"), WaitStrategy.VISIBLE, "status").equals("Not Applicable")) {
            String sdd = getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[2]"), WaitStrategy.VISIBLE, "start date");
            long firstPart = ChronoUnit.DAYS.between(LocalDate.parse(sdd, formatter), LocalDate.of(LocalDate.parse(sdd, formatter).getYear()
                    , 12, 31));
            double firstDate = (double) (firstPart + 1) / days;
            long secPart = ChronoUnit.DAYS.between(LocalDate.of(LocalDate.parse(ed, formatter).getYear(), 1, 1),
                    LocalDate.parse(ed, formatter));
            double secondDate = (double) secPart / days;
            return firstDate + secondDate;

        }
        return (double) ChronoUnit.DAYS.between(LocalDate.parse(sd, formatter), LocalDate.parse(ed, formatter)) / days;
    }

    public Object calculateInterestAmountForThirtyBy360(int num) {

        if (!getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[10]"), WaitStrategy.VISIBLE, "status").equals("Not Applicable")) {
            return getOpeningPrincipal(num) * getInterestRate() * getNumOfDaysForThirtyBy360(num);
        }
        return null;
    }

    public double getNumOfDaysForThirtyBy360(int num) {
        String sd = getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[2]"), WaitStrategy.VISIBLE, "start date");
        String ed = getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[3]"), WaitStrategy.VISIBLE, "end Date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(sd, formatter);
        LocalDate endDate = LocalDate.parse(ed, formatter);

        long daysBetween = 0;
        LocalDate tempDate = startDate;
        while (tempDate.isBefore(endDate)) {
            int daysInMonth = 0;
            if (tempDate.lengthOfMonth() == 31) {
                daysInMonth = 30;
            } else {
                daysInMonth = tempDate.lengthOfMonth();
            }
            int daysInCurrentMonth = daysInMonth - tempDate.getDayOfMonth() + 1;
            daysBetween += daysInCurrentMonth;
            tempDate = tempDate.plusMonths(1).withDayOfMonth(1);
        }
        daysBetween += endDate.getDayOfMonth() - 1;
        int startYear = startDate.getYear();
        int endYear = endDate.getYear();
        for (int year = startYear; year <= endYear; year++) {
            LocalDate februaryFirst = LocalDate.of(year, Month.FEBRUARY, 1);
            LocalDate februaryLast = LocalDate.of(year, Month.FEBRUARY, 28);  // 28th is safe, 29th is a leap year case
            if ((startDate.isBefore(februaryFirst) || startDate.isEqual(februaryFirst)) &&
                    (endDate.isAfter(februaryLast) || endDate.isEqual(februaryLast))) {
                if (isLeapYear(startYear) && isLeapYear(endYear)) {
                    return (double) (daysBetween - 29) / 360;
                } else {
                    return (double) (daysBetween - 28) / 360;
                }
            }
        }
        return (double) (daysBetween - 30) / 360;
    }
    public Object calculateInterestAmountForActual_days_as_per_financial_year(int num) {

        if (!getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[10]"), WaitStrategy.VISIBLE, "status").equals("Not Applicable")) {
            return getOpeningPrincipal(num) * getInterestRate() * getNumOfDaysForFinancialYear(num);
        }
        return null;
    }

    public double getNumOfDaysForFinancialYear(int num) {
        String sd = getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[2]"), WaitStrategy.VISIBLE, "start date");
        String ed = getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[3]"), WaitStrategy.VISIBLE, "end Date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        if (isLeapFinancialYear(sd) && isLeapFinancialYear(ed)) {
            return (double) ChronoUnit.DAYS.between(LocalDate.parse(sd, formatter), LocalDate.parse(ed, formatter)) / 366;
        } else if (!isLeapFinancialYear(sd)
                && isLeapFinancialYear(ed)) {
            long firstPart = ChronoUnit.DAYS.between(LocalDate.parse(sd, formatter), LocalDate.of(LocalDate.parse(sd, formatter).getYear()
                    , 3, 31));
            double firstDate = (double) (firstPart + 1) / 365;
            long secPart = ChronoUnit.DAYS.between(LocalDate.of(LocalDate.parse(ed, formatter).getYear(), 4, 1),
                    LocalDate.parse(ed, formatter));
            double secondDate = (double) secPart / 366;
            return firstDate + secondDate;
        } else if (isLeapFinancialYear(sd) && !isLeapFinancialYear(ed)) {
            long firstPart = ChronoUnit.DAYS.between(LocalDate.parse(sd, formatter), LocalDate.of(LocalDate.parse(sd, formatter).getYear()
                    , 3, 31));
            double firstDate = (double) (firstPart + 1) / 366;
            long secPart = ChronoUnit.DAYS.between(LocalDate.of(LocalDate.parse(ed, formatter).getYear(), 4, 1),
                    LocalDate.parse(ed, formatter));
            double secondDate = (double) secPart / 365;
            return firstDate + secondDate;
        } else {
            return (double) ChronoUnit.DAYS.between(LocalDate.parse(sd, formatter), LocalDate.parse(ed, formatter)) / 365;
        }
    }
    public static boolean isLeapFinancialYear(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(dateStr, formatter);

        int year = date.getYear();

        if ((year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))) {
            LocalDate financialYearStart = LocalDate.of(year, 1, 1);
            LocalDate financialYearEnd = LocalDate.of(year, 3, 31);
            return !date.isBefore(financialYearStart) && !date.isAfter(financialYearEnd);
        } else if ((year + 1) % 4 == 0) {
            LocalDate financialYearStart = LocalDate.of(year, 4, 1);
            LocalDate financialYearEnd = LocalDate.of(year, 12, 31);
            return !date.isBefore(financialYearStart) && !date.isAfter(financialYearEnd);
        }
        return false;
    }

    public SDLFDrawdownPage selectDisOptions() {
        scrollIntoView(disbursementOptions);
        clickk(disbursementOptions, WaitStrategy.CLICKABLE, "Disbursement Options");
        return this;
    }
    public SDLFDrawdownPage clickAddDisbursement() {
        clickk(addDisbursement, WaitStrategy.CLICKABLE, "Add Disbursement");
        return this;
    }

    public SDLFDrawdownPage enterDisbursementValueDate(String text) {
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        clearDate(disbursement_value_date, WaitStrategy.PRESENCE)
                .actionSendkeys(text);
        return this;
    }

    public SDLFDrawdownPage enterDisbursementTransactionDate(String text) {
        clearDate(disbursement_transaction_date, WaitStrategy.PRESENCE)
                .actionSendkeys(text);


        return this;
    }

    public SDLFDrawdownPage enterDisAmount(String value) {
        clickk(disbursement_amount, WaitStrategy.CLICKABLE, "disbursement amount");
        sendText(disbursement_amount, value, WaitStrategy.PRESENCE, "disbursement amount");
        return this;
    }

    public SDLFDrawdownPage enterDisbursementNotes(String text) {
        sendText(disbursement_remarks, text, WaitStrategy.PRESENCE, "remarks");
        return this;
    }
    public SDLFDrawdownPage selectDisbursementType(String text) {
        scrollIntoView(disbursementType);
        clickk(disbursementType, WaitStrategy.CLICKABLE, "Disbursement Type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public SDLFDrawdownPage clickEditDisbursement() {
        jsClick(By.xpath("(//tbody)[3]/tr/td[6]/a[1]"), WaitStrategy.CLICKABLE, "Edit Icon");
        return this;
    }

    public void clickDeleteDisbursement() {
        for (int i = 0; i < 10; i++) {
            try {
                scrollIntoView(By.xpath("(//tbody)[3]/tr/td[6]/a[2]"));
                doubleClick(By.xpath("(//tbody)[3]/tr/td[6]/a[2]"));
                Alert al = DriverManager.getDriver().switchTo().alert();
                al.accept();
                break;
            } catch (NoAlertPresentException e) {
                Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            }

        }
    }

    public String getDisbursementValueDate() {
        return getText(By.xpath("(//tbody)[3]/tr/td[1]"), WaitStrategy.VISIBLE, "value date");
    }

    public SDLFDrawdownPage clickUseRestOptions(){
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        jsClick(useRestOptions,WaitStrategy.CLICKABLE,"Use Reset Options");
        return this;
    }

    public SDLFDrawdownPage selectFrequency(String text){
        clickk(borrowing_reset_schedule_frequency, WaitStrategy.CLICKABLE, "Frequency");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public SDLFDrawdownPage selectResetDay(String text){
        clickk(borrowing_reset_schedule_reset_day, WaitStrategy.CLICKABLE, "Reset Day");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }
    public SDLFDrawdownPage clickAddSchedule(){
        clickk(addSchedule,WaitStrategy.CLICKABLE,"Add Schedule");
        return this;
    }
    public SDLFDrawdownPage clickOnPreviewButton() {
        scrollIntoView(previewResetScheduleDates);
        clickk(previewResetScheduleDates, WaitStrategy.CLICKABLE, "Preview");
        return this;
    }

    public Object calculateInterestAmountForResetDates(int num) {

        if (!getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[10]"), WaitStrategy.VISIBLE, "status").equals("Not Applicable")) {
            return getOpeningPrincipal(num) * getInterestRateForRestDates(num) * getNumOfDays(num);
        }
        return null;
    }
    public double getInterestRateForRestDates(int num) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String date;
        LocalDate date1;

        if (getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[10]"), WaitStrategy.VISIBLE, "Start Date").equalsIgnoreCase("Not Applicable")) {
            date = getText(By.xpath("(//tbody)[6]/tr[" + (num - 1) + "]/td[2]"), WaitStrategy.VISIBLE, "Start Date");
        } else {
            date = getText(By.xpath("(//tbody)[6]/tr[" + num + "]/td[2]"), WaitStrategy.VISIBLE, "Start Date");
        }

        date1 = LocalDate.parse(date, formatter);
        List<LocalDate> sortedKeys = new ArrayList<>(getResetDatesAndValues().keySet());
        Collections.sort(sortedKeys);

        for (int i = 0; i < sortedKeys.size() - 1; i++) {
            LocalDate lowerBound = sortedKeys.get(i);
            LocalDate upperBound = sortedKeys.get(i + 1);
            if (!date1.isBefore(lowerBound) && date1.isBefore(upperBound)) {
                return getResetDatesAndValues().get(lowerBound);
            }
        }

        LocalDate lastKey = sortedKeys.get(sortedKeys.size() - 1);
        if (!date1.isBefore(lastKey)) {
            return getResetDatesAndValues().get(lastKey);
        }

        return 0.0;
    }
    public HashMap<LocalDate, Double> getResetDatesAndValues() {
        HashMap<LocalDate, Double> map = new HashMap<>();
        List<WebElement> li = DriverManager.getDriver().findElements(By.xpath("(//tbody)[5]/tr"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String sd = getText(By.xpath("(//tbody)[1]/tr/td[1]"), WaitStrategy.VISIBLE, "start date");
        String[] irr = getText(By.xpath("(//tbody)[1]/tr/td[5]"), WaitStrategy.VISIBLE, "Interest").split("%");
        map.put(LocalDate.parse(sd, formatter), Double.parseDouble(irr[0]) / 100);
        for (int i = 1; i <= li.size(); i++) {
            String date = getText(By.xpath("(//tbody)[5]/tr[" + i + "]/td[2]"), WaitStrategy.VISIBLE, "start date");
            String[] ir = getText(By.xpath("(//tbody)[5]/tr[" + i + "]/td[3]"), WaitStrategy.VISIBLE, "Interest").split("%");
            map.put(LocalDate.parse(date, formatter), Double.parseDouble(ir[0]) / 100);
        }
        return map;
    }
}
