package com.finvisage.liabilityPages;

import com.finvisage.drivers.DriverManager;
import com.finvisage.enums.WaitStrategy;
import com.finvisage.factory.ExplicitWaitFactory;
import com.finvisage.utils.XpathUtils;
import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FixedDepositPage extends BasePageLiability {
    private final By interestScheduleOptions = By.xpath("//p[text()='Interest Payment Schedule']//parent::div/div[2]/a");
    private final By equatedSchedule = By.xpath("(//a[text()='Add Equated Schedule'])[1]");
    private final By IRFrequency = By.xpath("//select[@id='fd_Interest_Frequency']/following-sibling::div/div[1]");
    private final By compoundingFrequency = By.xpath("//select[@id='payment_schedule_equated_property_compounding_frequency']/" +
            "following-sibling::div/div[1]");

    private final By IRPaymentDay = By.xpath("//select[@id='fd_Interest_Payment_Day']/following-sibling::div/div[1]");
    private final By TDSRoundingMode = By.xpath("//select[@id='payment_schedule_equated_property_tds_rounding_mode']/following-sibling::div/div[1]");

    private final By interestRounding = By.xpath("//select[@id='payment_schedule_equated_property_rounding_mode']/following-sibling::div/div[1]");

    private final By interestPaymentConvension = By.xpath("//select[@id='payment_schedule_equated_property_payment_convention']/" +
            "following-sibling::div/div[1]");
    private final By btn_preview = By.xpath("//button[text()='Cancel']/following-sibling::input");
    private final By btn_Submit = By.xpath("//input[@type='submit']");
    private final By btn_generateSchedule = By.xpath("//input[@value='Generate Schedule']");
    private final By refresh = By.xpath("//a[@data-original-title='Refresh']");


    public FixedDepositPage clickInterestScheduleOptions() {
        scrollIntoView(interestScheduleOptions);
        clickk(interestScheduleOptions, WaitStrategy.CLICKABLE, "Interest Schedule Options");
        return this;
    }

    public FixedDepositPage selectAddEquatedInterestSchedule() {
        clickk(equatedSchedule, WaitStrategy.CLICKABLE, "Equated schedule");
        return this;

    }

    public FixedDepositPage selectIRFrequency(String value) {
        jsClick(IRFrequency, WaitStrategy.CLICKABLE, "Interest Frequency");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String freq = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(freq, value);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, value);
        return this;
    }

    public FixedDepositPage selectCompoundingFrequency(String value) {
        clickk(compoundingFrequency, WaitStrategy.CLICKABLE, "compounding Frequency");
        if (getText(IRFrequency, WaitStrategy.VISIBLE, "interest Frequency").equalsIgnoreCase(value)) {
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

    public FixedDepositPage selectIRPaymentDay(String value) {
        clickk(IRPaymentDay, WaitStrategy.CLICKABLE, "Interest payment day");
        String freq = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(freq, value);
        clickk(By.xpath(newxpath), WaitStrategy.CLICKABLE, value);
        return this;
    }

    public FixedDepositPage selectInterestPaymentConvention(String value) {
        clickk(interestPaymentConvension, WaitStrategy.CLICKABLE, "interest payment convention");
        String pc = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(pc, value);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, value);
        return this;
    }

    public FixedDepositPage selectInterestRounding(String mode) {
        jsClick(interestRounding, WaitStrategy.CLICKABLE, "Interest Rounding Mode");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        String rounding = "//div[text()='%replace%']";
        String newxpath = XpathUtils.getXpath(rounding, mode);
        jsClick(By.xpath(newxpath), WaitStrategy.CLICKABLE, mode);
        return this;

    }

    public FixedDepositPage TDSRounding(String mode) {
        jsClick(TDSRoundingMode, WaitStrategy.CLICKABLE, "TDS Rounding Mode");
        if (getText(interestRounding, WaitStrategy.VISIBLE, "Interest Rounding").equalsIgnoreCase(mode)) {
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

    public FixedDepositPage clickOnPreview() {
        scrollIntoView(btn_preview);
        jsClick(btn_preview, WaitStrategy.CLICKABLE, "Preview");
        return this;
    }

    public int getInterestRows() {
       return ExplicitWaitFactory.performExplicitWait(WaitStrategy.PRESENCE,By.xpath("(//tbody)[5]"))
                .findElements(By.xpath("(//tbody)[5]/tr")).size();
    }

    public FixedDepositPage clickOnGenerateSchedule() {
        clickk(btn_generateSchedule, WaitStrategy.CLICKABLE, "Generate Schedule");
        return this;
    }

    public FixedDepositPage checkIRscheduleGenerated(int schedulesize) {
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        for (int i = 0; i <= 5; i++) {
            List<WebElement> li = DriverManager.getDriver().findElements(By.xpath("(//tbody)[4]/tr"));
            if (schedulesize == li.size()) {
                break;
            } else {
                DriverManager.getDriver().navigate().refresh();
                Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
            }
        }
        return this;
    }

    public void create_FD_with_InterestSchedule() {
        LiabilityDashboardsPage ldp = new LiabilityDashboardsPage();
        ldp.clickAssets().clickFixedDeposit().moveToHamburgerMenu().clickAdd()
                .selectFixedDepositType("Withdrawable")
                .enterDepositNumber(7).selectEntity("ENTITY_FOR_AUTOMATION_ONLY")
                .selectCounterparty("AUTOMATION_PARTY").enterPrincipal("800000")
                .selectOnMaturity("Non Renewable")
                .selectDebitBankAccount("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectBeneficiaryAccount("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .enterTenure("7").clickNewIRSlab()
                .selectIRType("Fixed").enterSpread("8")
                .clickNewTDS().enterTDS("25").enterAdditionalInfo("NA")
                .clickCreate();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        int size = clickInterestScheduleOptions().selectAddEquatedInterestSchedule()
                .selectIRFrequency("Quarterly").selectIRPaymentDay("On 2nd")
                .selectInterestPaymentConvention("FOLW").selectInterestRounding("NONE")
                .TDSRounding("NONE").clickOnPreview().getInterestRows();
        clickOnGenerateSchedule().checkIRscheduleGenerated(size);

    }

}
