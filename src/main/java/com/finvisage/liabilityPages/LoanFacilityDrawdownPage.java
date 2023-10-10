package com.finvisage.liabilityPages;

import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.finvisage.utils.XpathUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class LoanFacilityDrawdownPage extends BasePageLiability {
    private final By principalScheduleOptions = By.xpath("//section[@class='my-5']//div[2]/a/i");
    private final By equatedSchedule1 = By.xpath("(//a[text()='Add Equated Schedule'])[1]");
    private final By equatedSchedule2 = By.xpath("(//a[text()='Add Equated Schedule'])[2]");
    private final By ad_HocSchedule1 = By.xpath("(//a[text()='Add Ad-Hoc Schedule'])[1]");
    private final By ad_HocSchedule2 = By.xpath("(//a[text()='Add Ad-Hoc Schedule'])[2]");
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

    private final By principalRoundingMode = By.xpath("//select[@id='repayment_schedule_equated_property_rounding_mode']/following-sibling::div/div[1]");
    private final By TDSRoundingMode = By.xpath("//select[@id='repayment_schedule_equated_property_tds_rounding_mode']/following-sibling::div/div[1]");
    private final By btn_preview = By.xpath("//button[text()='Cancel']/following-sibling::input");
    private final By btn_generateSchedule = By.xpath("//input[@value='Generate Schedule']");
    private final By ad_HocValueDate = By.xpath("//input[@id='repayment_schedule_value_value_end_date']");
    private final By ad_HocPaymentDate = By.xpath("//input[@id='repayment_schedule_value_repayment_date']");
    private final By principalScheduleForm = By.xpath("//form[@id='new_repayment_schedule_value']");
    private final By btn_Submit = By.xpath("//input[@value='Submit']");
    private final By interestScheduleOptions = By.xpath("//section[@class='mb-5']/div/div/div[2]/a");
    private final By unallocatedPrincipal = By.xpath("(//span[@class='font-italic ml-2'])[1]");
    private final By deactivateSchedule1 = By.xpath("(//a[text()='Deactivate Schedule'])[1]");
    private final By deactivateSchedule2 = By.xpath("(//a[text()='Deactivate Schedule'])[2]");
    private final By refresh = By.xpath("//a[@data-original-title='Refresh']");
    private final By paymentType = By.xpath("//select[@id='actual_payment_type_select']//following-sibling::div/div[1]");
    private final By paymentNotes = By.id("actual_repayment_schedule_value_notes");
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
        clickk(principalRoundingMode, WaitStrategy.CLICKABLE, "Interest Rounding Mode");
        String rounding = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(rounding, mode);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, mode);
        return this;

    }

    public LoanFacilityDrawdownPage TDSRounding(String mode) {
        clickk(TDSRoundingMode, WaitStrategy.CLICKABLE, "TDS Rounding Mode");
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
        return this;
    }

    public LoanFacilityDrawdownPage selectValueDate(String text) {
        clearDate(ad_HocValueDate).
                actionSendkeys(text);
        return this;
    }

    public LoanFacilityDrawdownPage selectPaymentDate(String text) {
        jsClick(ad_HocPaymentDate,"payment date");
        Uninterruptibles.sleepUninterruptibly(2,TimeUnit.SECONDS);
        clearDate(ad_HocPaymentDate);
        actionSendkeys(text);
        return this;
    }

    public LoanFacilityDrawdownPage clickSubmit() {
        scrollIntoView(btn_Submit);
        jsClick(btn_Submit, WaitStrategy.CLICKABLE, "Ad-Hoc Submit");
        return this;
    }

    public String getUnallcatedPrincipal() {
        return getText(unallocatedPrincipal, WaitStrategy.VISIBLE, "unallocated principal");
    }

    public LoanFacilityDrawdownPage checkUnallocatedPrincipal() {
        scrollIntoView(unallocatedPrincipal);
        for (int i = 0; i < 5; i++) {
            if (getUnallcatedPrincipal().equals("0.00")) {
                break;
            } else {
                DriverManager.getDriver().navigate().refresh();
                Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
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
    public String[] getPrincipalScheduleStatus(){
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[4]/tr"));
        String[] li = new String[list.size()];
        IntStream.rangeClosed(1, list.size()).forEachOrdered(i -> {
            String status = "(//tbody)[4]/tr[%replace%]/td[4]";
            String newxpath = XpathUtils.getXpath(status, String.valueOf(i));
            scrollIntoView(By.xpath(newxpath));
            li[i-1] = getText(By.xpath(newxpath), WaitStrategy.VISIBLE, " - Principal Schedule Status");
        });


        return li;
    }
    public String[] getInterestScheduleStatus(){
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[5]/tr"));
        String[] li = new String[list.size()];
        IntStream.rangeClosed(1, list.size()).forEachOrdered(i -> {
            String status = "(//tbody)[5]/tr[%replace%]/td[4]";
            String newxpath = XpathUtils.getXpath(status, String.valueOf(i));
            scrollIntoView(By.xpath(newxpath));
            li[i-1] = getText(By.xpath(newxpath), WaitStrategy.VISIBLE, " -Interest Schedule Status");
        });


        return li;
    }
    public void make_Principal_Payments(String type) {
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[4]/tr"));
        IntStream.rangeClosed(1, list.size()).forEachOrdered(i -> {
            String makepayment = "//tbody/tr[%replace%]/td[8]/a[2]";
            String newxpath = XpathUtils.getXpath(makepayment, String.valueOf(i));
            scrollIntoView(By.xpath(newxpath));
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

    public void make_Interest_Payments(String type) {
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[5]/tr"));
        System.out.println(list.size());
        IntStream.rangeClosed(1, list.size()).forEachOrdered(i -> {
            String makepayment = "//tbody/tr[%replace%]/td[10]/a[2]";
            String newxpath = XpathUtils.getXpath(makepayment, String.valueOf(i));
            scrollIntoView(By.xpath(newxpath));
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

    public LoanFacilityDrawdownPage create_New_Drawdown() {
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.clickOptions().clickAddDrawdown().enterDrawdownExternalID(10)
                .enterDrwadownLedgerID(8).enterLoanAcnt("Loan ACNT")
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
                selectPrincipalRounding("NONE").clickOnPreview().clickOnGenerateSchedule();
        return this;
    }

    public LoanFacilityDrawdownPage generate_LF_Equated_Interest_Schedule() {
        checkUnallocatedPrincipal().clickInterestScheduleOptions()
                .selectAddEquatedInterestSchedule()
                .selectIRFrequency("Annually").selectCompoundingFrequency("Monthly")
                .IRPaymentDay("On 3rd").selectInterestPaymentConvention("FOLW")
                .selectInterestRounding("NONE").TDSRounding("NONE")
                .clickOnPreview().clickOnGenerateSchedule();

        return this;
    }
}
