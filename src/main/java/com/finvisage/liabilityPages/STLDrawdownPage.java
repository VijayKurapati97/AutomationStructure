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
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class STLDrawdownPage extends BasePageLiability{
    final String[] interestSpread = {"12", "13", "14", "15", "19", "20", "11", "8", "17"};
    private final By hamburgerMenu = By.xpath("//section[@id='fixed-buttons']/div/a/i");
    private final By btn_Edit = By.xpath("//a[@data-original-title='Edit']/i");
    private final By continuee = By.xpath("//button[text()='Continue']");
    private final By btn_makePrepaymentsOptions = By.xpath(("//p[text()='Prepayments']//parent::div/div[2]/a"));
    private final By prepaymentPaymentDate = By.id("prepayment_payment_date");
    private final By prepaymentValueDate = By.id("prepayment_prepayment_date");
    private final By prepaymentAmount = By.id("prepayment_prepayment_amount");
    private final By prepaymentPenaltyDate = By.id("prepayment_prepayment_penalty_date");
    private final By makePrepayment = By.xpath("//a[@title='Make Prepayment']");
    private final By prepyementPenaltyAmount = By.xpath("(//tbody)[4]/tr/td[6]");
    private final By prepaymentOutstandingAmount = By.xpath("//form[@id='new_actual_against_penalty']" +
            "/div[2]//div[4]/p");
    private final By prepayemntAgainstPenaltyAmount = By.id("actual_against_penalty_repayment_amount");
    private final By prepaymentsNotes = By.id("actual_against_penalty_notes");
    private final By btn_DeletePayment = By.xpath("//a[@title='Delete']");
    private final By btn_Submit = By.xpath("//input[@type='submit']");
    private final By drawdownAttachedDocumentsTab = By.xpath("//a[@id='attachments-details-tab']");
    private final By Btn_uploadDocs = By.xpath("//a[text()='Upload Documents']");
    private final By dropzone = By.xpath("//form[@id='cashflow_attachments']/div[3]");
    private final By btn_close = By.xpath("//button[text()='Close']");
    private final By callSchedulesTab = By.id("put-call-schedules-tab");
    private final By btn_callScheduleOptions = By.xpath("//p[text()='Call Schedules:']//parent::div/div[2]/a");
    private final By equatedCallSchedule = By.xpath("(//a[text()='Add Equated Schedule'])[3]");
    private final By ad_HocCallSchedule = By.xpath("(//a[text()='Add Ad-Hoc Schedule'])[3]");
    private final By deactivateCallSchedule = By.xpath("//a[@data-original-title='Deactivate']");
    private final By callFrequency = By.xpath("//select[@id='put_call_schedule_equated_property_put_call_frequency']//following-sibling::div/div[1]");
    private final By callDay = By.xpath("//select[@id='put_call_schedule_equated_property_put_call_exercise_day']//following-sibling::div/div[1]");
    private final By callDate = By.xpath("//input[@id='put_call_schedule_value_put_call_date']");
    private final By btn_preview = By.xpath("//button[text()='Cancel']/following-sibling::input");
    private final By btn_generateSchedule = By.xpath("//input[@value='Generate Schedule']");
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
    private final By lienDetails = By.id("lien-details-tab");
    private final By btn_addFD = By.xpath("(//a[@title='Add'])[1]");
    private final By drawdownEndDate = By.xpath("//div[@id='borrowing_drawdown_details']" +
            "//section/div/div/section/div/div[2]/ul/li[5]/div/div[2]/span");
    private final By drawdownExtId =By.xpath("//div[@id='borrowing_drawdown_details']" +
            "//section/div/div/section/div/div[2]/ul/li[2]/div/div[2]/span");
    private final By btn_deleteDrawdown = By.xpath("//section[@id='fixed-buttons']/div/ul/li[3]/a");




    public STLDrawdownPage moveToHamburgerMenu() {
        moveToElement(DriverManager.getDriver().findElement(hamburgerMenu), "HamburgerMenu");
        return this;
    }

    public STLDrawdownPage clickEdit() {
        clickk(btn_Edit, WaitStrategy.CLICKABLE, "Edit icon");
        return this;
    }

    public NewSTLDrawdownPage clickContinue() {
        jsClick(continuee, WaitStrategy.CLICKABLE, "Continue button");
        return new NewSTLDrawdownPage();
    }
    public STLDrawdownPage click_PrepayemntOptions() {
        jsClick(btn_makePrepaymentsOptions, "Prepayment Options button");
        return this;
    }

    public STLDrawdownPage select_MakePrepayemnts() {
        jsClick(makePrepayment, "Prepayment");
        return this;
    }

    public STLDrawdownPage enterPrepaymentPayementDate(String text) {
        clickk(prepaymentPaymentDate, WaitStrategy.CLICKABLE, "payment date");
        clearDate(prepaymentPaymentDate)
                .sendText(prepaymentPaymentDate, text, WaitStrategy.PRESENCE, "Prepayment payment date");
        return this;
    }

    public STLDrawdownPage enterPrepaymentValueDate(String text) {
        clickk(prepaymentValueDate, WaitStrategy.CLICKABLE, "value date");
        clearDate(prepaymentValueDate)
                .sendText(prepaymentValueDate, text, WaitStrategy.PRESENCE, "Prepayment value date");
        return this;
    }
    public STLDrawdownPage enterPrepaymentPenltyDate(String text) {
        clickk(prepaymentPenaltyDate, WaitStrategy.CLICKABLE, "penalty date");
        clearDate(prepaymentPenaltyDate)
                .sendText(prepaymentPenaltyDate, text, WaitStrategy.PRESENCE, "Prepayment penalty date");
        return this;
    }

    public STLDrawdownPage enterPrepaymentAmount(String text) {
        WebElement ele = DriverManager.getDriver().findElement(prepaymentAmount);
        ele.clear();
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        sendText(prepaymentAmount, text, WaitStrategy.PRESENCE, "Prepayment Amount");
        return this;
    }

    public double getPrepaymentPenalty() {

        return CommonUtils.stringToDouble(getText(prepyementPenaltyAmount, WaitStrategy.VISIBLE, "penalty amount"));
    }

    public STLDrawdownPage make_prepayments() {
        click_PrepayemntOptions().select_MakePrepayemnts()
                .enterPrepaymentPayementDate("21/09/2024")
                .enterPrepaymentValueDate("21/09/2024")
                .enterPrepaymentAmount("2000")
                .clickSubmit();
        return this;
    }

    public STLDrawdownPage make_prepayments_Payments() {
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
            Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        });
return this;
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
    public STLDrawdownPage clickSubmit() {
        scrollIntoView(btn_Submit);
        try{
            doubleClick(btn_Submit);
            Uninterruptibles.sleepUninterruptibly(5,TimeUnit.SECONDS);
            Alert al = DriverManager.getDriver().switchTo().alert();
            al.accept();
            al.accept();
        }catch (NoAlertPresentException e) {
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        }
        return this;
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
                    Alert all= DriverManager.getDriver().switchTo().alert();
                    all.accept();
                    all.accept();
                    break;
                } catch (NoAlertPresentException e) {
                    Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
                }

            }
        });

    }
    public STLDrawdownPage clickAttchedDocTab() {
        clickk(drawdownAttachedDocumentsTab, WaitStrategy.CLICKABLE, "Attached Documents tab");
        return this;

    }

    public STLDrawdownPage clickUploadDocuments() {
        jsClick(Btn_uploadDocs, "Upload Documents button");
        return this;
    }

    public STLDrawdownPage uploadAttchedDoc() throws AWTException {
        clickk(dropzone, WaitStrategy.CLICKABLE, "upload file zone");

        StringSelection stringSelection = new StringSelection(FrameworkConstants.getUploadAttachedDocFilePath());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        pasteAndEnter();
        return this;
    }

    public STLDrawdownPage clickClose() {
            try {
                doubleClick(btn_close);
                Alert al = DriverManager.getDriver().switchTo().alert();
                al.accept();
                al.accept();
            } catch (NoAlertPresentException e) {
                Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
            }


        return this;

    }

    public int getAttachedDocSize() {
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, By.xpath("(//tbody)[8]"));
        return DriverManager.getDriver().findElements(By.xpath("(//tbody)[8]/tr")).size();
    }

    public STLDrawdownPage clickCallSchedulesTab() {
        clickk(callSchedulesTab,WaitStrategy.CLICKABLE, "Call Schedules Tab");
        return this;
    }

    public STLDrawdownPage clickCallSchedulesOptions() {
        clickk(btn_callScheduleOptions, WaitStrategy.CLICKABLE, "Call schedules Option button");
        return this;
    }

    public STLDrawdownPage clickEquatedCallSchedule() {
        clickk(equatedCallSchedule, WaitStrategy.CLICKABLE, " Equated Call Schedule");
        return this;
    }

    public STLDrawdownPage clickAdHocCallSchedule() {
        clickk(ad_HocCallSchedule, WaitStrategy.CLICKABLE, " Ad-Hoc Call Schedule");
        return this;
    }

    public STLDrawdownPage selectCallFrequency(String frequency) {
        clickk(callFrequency, WaitStrategy.CLICKABLE, "Call Frequency");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String rounding = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(rounding, frequency);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, frequency);
        return this;

    }

    public STLDrawdownPage selectCallExerciseDay(String day) {
        clickk(callDay, WaitStrategy.CLICKABLE, "Call Exercise Day");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String rounding = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(rounding, day);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, day);
        return this;
    }

    public STLDrawdownPage clickCallSchedulePreview() {
        clickk(btn_preview, WaitStrategy.CLICKABLE, "preview button");
        return this;
    }

    public STLDrawdownPage enterCallDate(String text) {
        jsClick(callDate, WaitStrategy.CLICKABLE, "Date filed");
        clearDate(callDate, WaitStrategy.PRESENCE).
                sendText(callDate, text, WaitStrategy.PRESENCE, " Date");
        DriverManager.getDriver().findElement(callDate).sendKeys(Keys.ENTER);
        return this;
    }

    public void clickdeactivate_callSchedule() {
        for (int i = 0; i < 10; i++) {
            try {
                doubleClick(deactivateCallSchedule);
                Alert al = DriverManager.getDriver().switchTo().alert();
                al.accept();
                Alert all= DriverManager.getDriver().switchTo().alert();
                all.accept();
                all.accept();
                break;
            } catch (NoAlertPresentException e) {
                e.printStackTrace();
            }

        }

    }
    public STLDrawdownPage clickOnGenerateSchedule() {
        try {
            Uninterruptibles.sleepUninterruptibly(5,TimeUnit.SECONDS);
            doubleClick(btn_generateSchedule);
            Alert al = DriverManager.getDriver().switchTo().alert();
            al.accept();
            al.accept();
        } catch (NoAlertPresentException e) {
            Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
        }

        return this;
    }
    public STLDrawdownPage clickFeetab() {
        jsClick(feeTab, WaitStrategy.CLICKABLE, " Fee Tab");
        return this;
    }

    public STLDrawdownPage clickAddFee() {
        jsClick(btn_AddFee, WaitStrategy.CLICKABLE, " Add Fee button");
        return this;
    }

    public STLDrawdownPage feeType(String feeType) {
        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
        clickk(feeTypee, WaitStrategy.CLICKABLE, "Fee Type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, feeType);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, feeType);
        return this;
    }

    public STLDrawdownPage amountType(String amountType) {
        jsClick(amountTypee, WaitStrategy.CLICKABLE, "Amount Type");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, amountType);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, amountType);
        return this;
    }

    public STLDrawdownPage enterFeeValue(String feevalue) {
        WebElement ele = DriverManager.getDriver().findElement(feeValue);
        ele.clear();
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        sendText(feeValue, feevalue, WaitStrategy.PRESENCE, "Fee value");
        return this;
    }

    public STLDrawdownPage enterRemarks(String remarks) {
        sendText(feeRemarks, remarks, WaitStrategy.PRESENCE, "Fee Remarks");
        return this;
    }

    public String getFeeStatus(String var) {
        String ar = "//table[@id='fees_container']//tbody/tr/td[%replace%]";
        String newxpath = XpathUtils.getXpath(ar, var);
        return getText(By.xpath(newxpath), WaitStrategy.VISIBLE, "Fee Status");
    }

    public void cancelFee() {
            try {
                doubleClick(cancelFee);
                Alert al = DriverManager.getDriver().switchTo().alert();
                al.accept();
                Uninterruptibles.sleepUninterruptibly(5,TimeUnit.SECONDS);
                Alert all = DriverManager.getDriver().switchTo().alert();
                all.accept();
                all.accept();
            } catch (Exception e) {
                e.printStackTrace();
            }

    }
    public STLDrawdownPage clickCovenantsTab() {
        clickk(covenantsTab, WaitStrategy.CLICKABLE, "Covenants Tab");
        return this;
    }

    public STLDrawdownPage clickAddCovenants() {
        clickk(btn_addCovenants, WaitStrategy.CLICKABLE, "Add Covenants Button");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        return this;
    }

    public STLDrawdownPage covenantTemplate(String tem) {
        jsClick(covenants_Template, WaitStrategy.CLICKABLE, "Covenant Template");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, tem);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, tem);
        return this;
    }

    public STLDrawdownPage enterAccountablePerson(String text) {
        sendText(accountablePerson, text, WaitStrategy.PRESENCE, "Accountable Person");
        return this;
    }

    public STLDrawdownPage enterThirdParty(String text) {
        sendText(thirdParty, text, WaitStrategy.PRESENCE, "ThirdParty");
        return this;
    }

    public STLDrawdownPage enterTargetValue(String text) {
        WebElement ele = DriverManager.getDriver().findElement(targetValue);
        ele.clear();
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        ele.sendKeys(Keys.ARROW_LEFT);
        sendText(targetValue, text, WaitStrategy.PRESENCE, "Target value");
        return this;
    }

    public STLDrawdownPage enterTargetDate(String text) {
        clearDate(targetDate, WaitStrategy.PRESENCE)
                .sendText(targetDate, text, WaitStrategy.PRESENCE, "Target Date");
        return this;
    }

    public STLDrawdownPage enterOffset(String text) {
        jsClick(offsetDays, "Offset Days");
        sendText(offsetDays, text, WaitStrategy.PRESENCE, "Offset Days");
        return this;
    }

    public STLDrawdownPage enterCovenantEndDate(String text) {
        jsClick(covenantsRemainderEndDate, " End Date");
        clearDate(covenantsRemainderEndDate, WaitStrategy.PRESENCE)
                .sendText(covenantsRemainderEndDate, text, WaitStrategy.PRESENCE, "End Date");
        return this;
    }

    public STLDrawdownPage selectMappingConditions(String text) {
        jsClick(covenants_MappingConditions, WaitStrategy.CLICKABLE, "Covenant Mapping conditions");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public STLDrawdownPage selectCovenantsEntity(String text) {
        jsClick(covenantsEntity, WaitStrategy.CLICKABLE, "Covenant Entity");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public STLDrawdownPage selectRatioName(String text) {
        jsClick(covenants_RatioName, WaitStrategy.CLICKABLE, "Covenant Ratio Name");
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        String ar = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(ar, text);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, text);
        return this;
    }

    public STLDrawdownPage enterThresholdPercentage(String text) {
        WebElement ele = DriverManager.getDriver().findElement(covenantThresholdLimit);
        ele.clear();
        ele.sendKeys(Keys.BACK_SPACE);
        ele.sendKeys(Keys.BACK_SPACE);
        ele.sendKeys(Keys.BACK_SPACE);
        sendText(covenantThresholdLimit, text, WaitStrategy.PRESENCE, "Threshold Limit");
        return this;
    }

    public int getCovenatsSize() {
        for (int i = 0; i < 5; i++) {
            clickCovenantsTab();
            if (!isDisplayed(By.xpath("(//tbody)[9]"), "Covenants table")) {
                Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
                DriverManager.getDriver().navigate().refresh();
                Alert al= DriverManager.getDriver().switchTo().alert();
                al.accept();
                al.accept();
            } else {

                break;
            }
        }
        return DriverManager.getDriver().findElements(By.xpath("(//tbody)[9]/tr")).size();
    }
    public STLDrawdownPage clickOnCreate() {
        try{
            doubleClick(btn_Submit);
            Alert al= DriverManager.getDriver().switchTo().alert();
            al.accept();
            al.accept();
        }catch (Exception e){
            e.printStackTrace();
        }
        return this;
    }
    public STLDrawdownPage clickLienDetails() {
        clickk(lienDetails, WaitStrategy.CLICKABLE, "Lien Details Tab");
        return this;
    }

    public STLDrawdownPage clickAddToLienFD() {
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, btn_addFD);
        scrollIntoView(btn_addFD);
        clickk(btn_addFD, WaitStrategy.CLICKABLE, " Add Button ");
        return this;
    }

    public int getLiedFDNum() {
        ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE, By.xpath("(//tbody)[4]"));
        return DriverManager.getDriver().findElements(By.xpath("(//tbody)[4]/tr")).size();
    }
    public String getDrwnEndDate() {
        return getText(drawdownEndDate, WaitStrategy.VISIBLE, "End Date");
    }
    public String getDrawdownExtId(){
        return  getText(drawdownExtId,WaitStrategy.PRESENCE,"Drawdown external id");
    }
    public ShortTermLoanPage clickDeleteDrawdown() {
            try {
                doubleClick(btn_deleteDrawdown);
                Alert al = DriverManager.getDriver().switchTo().alert();
                al.accept();
            } catch (Exception e) {
                e.printStackTrace();
            }


        return new ShortTermLoanPage();
    }
    public STLDrawdownPage create_New_STLDrawdown() {
        ShortTermLoanPage st=new ShortTermLoanPage();
        st.clickOptions().clickAddDrawdown().enterDrawdownExternalID(10)
                .enterDrwadownLedgerID(8)
                .enterDrwadownPrincipal("300000")
                .selectPut_Call("Call")
                .enterLoanAcnt("Loan ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPayementAcnt("NACH auto debit").clickNewIrSlab()
                .selectIRType("Fixed").enterSpread(interestSpread[(int) (Math.random() * interestSpread.length)])
                .clickNewTDS().enterTDS("12")
                .clickNewPrepayments()
                .selectPrepayemntsPenalty("10")
                .enterAdditionalInfo("NA").clickCreate();
        return this;
    }
}
