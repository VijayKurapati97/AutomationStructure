package com.finvisage.tests;

import com.finvisage.constants.FrameworkConstants;
import com.finvisage.drivers.DriverManager;
import com.finvisage.liabilityPages.*;
import com.finvisage.reports.ExtentManager;
import com.google.common.util.concurrent.Uninterruptibles;
import org.apache.commons.lang3.RandomStringUtils;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.within;

public class SubDebtLoanFacilityTests extends BaseTest {
    private final ThreadLocal<String[]> userThreadLocal = ThreadLocal.withInitial(() -> null);

    @AfterMethod
    public void Teardown(ITestContext context) {
        String[] user = userThreadLocal.get();
        FrameworkConstants.setUserAvailability(user);
    }

    private SubDebtLoanFacilityTests() {
    }

    @Test(groups = {"Smoke"})
    public void SDLF_Create_Drawdown(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage sl = new SDLFPage();
        sl.create_new_SubDebt_LoanFacility().clickOptions().clickAddDrawdown().enterDrawdownExternalID(10)
                .enterDrawdownValueDate(data.get("ValueDate")).enterDrawdownEndDate(data.get("EndDate"))
                .selectPrepaymentsPenalty(data.get("penalty"))
                .enterDrawdownLedgerID(8).enterLoanAcnt("Loan ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPaymentAcnt(data.get("Payment_Account")).clickNewDisbursement()
                .enterDisAmount(data.get("DisAmount"))
                .selectDisbursementType("Standard").clickNewIrSlab()
                .selectIRType(data.get("IRType")).enterSpread(data.get("Spread"))
                .clickNewTDS().enterTDS(data.get("TDS")).enterAdditionalInfo("NA").clickCreate();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Drawdown - LoanFacility-Drawdown-")
                .isNotEmpty()
                .isNotNull();


    }

    @Test(groups = {"Smoke"})
    public void SDLF_Create_LoanFacility(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickSubDebtLF().moveToHamburgerMenu().clickAdd()
                .enterExternalID(10).enterLedgerID(4)
                .selectLoanFacilityType(data.get("LFType"))
                .enterROC(5).enterSanctionDate(data.get("SanctionDate"))
                .enterEndDate(data.get("EndDate")).selectEntity(data.get("Entity"))
                .selectCounterparty(data.get("Counterparty"))
                .enterFacilityAmount(data.get("LFAmount"))
                .covenantDefaultIR(data.get("CDIR")).paymentDefaultIR(data.get("PDIR"))
                .selectArranger(data.get("Arranger")).primarySecurityDetails()
                .secondarySecurityDetails().personalGuaranteeDetails()
                .corporateGuaranteeDetails().enterTrustee(data.get("Trustee"))
                .enterAdditionalInfo(data.get("Addinfo")).clickOnCreate();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Loan Facility - LF")
                .isNotEmpty()
                .isNotNull();

    }

    @Test(groups = {"Smoke"})
    public void SDLF_Update_LoanFacility(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage sl = new SDLFPage();
        sl.create_new_SubDebt_LoanFacility().clickHamburger()
                .clickEdit().clickContinue();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Sub Debt Loan Facilities - Edit");
        NewSDLFpage sd = new NewSDLFpage();
        sd.enterEndDate(data.get("EndDate"))
                .clickOnCreate();
        Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Loan Facility - LF");
    }

    @Test(groups = {"Smoke"})
    public void SDLF_Update_Drawdown(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage sl = new SDLFPage();
        sl.create_new_SubDebt_LoanFacility();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Loan Facility - LF");
        SDLFDrawdownPage sd = new SDLFDrawdownPage();
        sd.create_New_SDLFDrawdown();
        sd.moveToHamburgerMenu().clickEdit().clickContinue();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Drawdown - Edit");
        NewSDLFDrawdownPage sn = new NewSDLFDrawdownPage();
        sn.enterDrawdownEndDate(data.get("EndDate"))
                .clickCreate();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Drawdown - LoanFacility-Drawdown-");


    }

    @Test(groups = {"Smoke"})
    public void SDLF_Create_Equated_Principal_Schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        String UnallocatedPrincipal = ld.create_New_SDLFDrawdown().clickRepaymentScheduleOptions()
                .selectAddEquatedPrincipalSchedule()
                .selectPrincipalPayout(data.get("payout")).selectPrincipalPaymentDay(data.get("PaymentDay"))
                .selectPrincipalPaymentConvention(data.get("PaymentConvention")).
                selectPrincipalRounding(data.get("Rounding")).clickOnPreview().clickOnGenerateSchedule()
                .checkUnallocatedPrincipal().getUnallocatedPrincipal();
        Assertions.assertThat(UnallocatedPrincipal).isEqualTo("0.00");

    }

    @Test(groups = {"Smoke"})
    public void SDLF_Create_Ad_Hoc_Principal_Schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        String UnallocatedPrincipal = ld.create_New_SDLFDrawdown()
                .clickRepaymentScheduleOptions().selectAd_HocPrincipalSchedule()
                .selectValueDate(data.get("ValueDate")).selectPaymentDate(data.get("PaymentDate"))
                .click_Ad_Hoc_ScheduleSubmit().getUnallocatedPrincipal();
        Assertions.assertThat(UnallocatedPrincipal).isEqualTo("0.00");

    }

    @Test(groups = {"Smoke"})
    public void SDLF_UploadPrincipalSchedule(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickSubDebtLF().moveToHamburgerMenu().clickAdd()
                .enterExternalID(10).enterLedgerID(4)
                .selectLoanFacilityType(data.get("LFType"))
                .enterROC(5).enterSanctionDate(data.get("SanctionDate"))
                .enterEndDate(data.get("EndDate")).enterLFAvailableTill(data.get("EndDate"))
                .selectEntity(data.get("Entity"))
                .selectCounterparty(data.get("Counterparty"))
                .enterFacilityAmount(data.get("LFAmount"))
                .selectArranger(data.get("Arranger")).enterTrustee(data.get("Trustee"))
                .enterAdditionalInfo(data.get("Addinfo")).clickOnCreate();
        SDLFPage ldd=new SDLFPage();
        String principal1 = ldd.clickOptions().clickAddDrawdown().enterDrawdownExternalID(10)
                .selectPrepaymentsPenalty(data.get("penalty"))
                .enterDrawdownLedgerID(8).enterLoanAcnt("Loan ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPaymentAcnt(data.get("Payment_Account")).clickNewDisbursement()
                .enterDisAmount(data.get("DisAmount"))
                .selectDisbursementType("Standard").clickNewIrSlab()
                .selectIRType(data.get("IRType")).enterSpread(data.get("Spread"))
                .clickNewTDS().enterTDS(data.get("TDS")).enterAdditionalInfo("NA").clickCreate()
                .getUnallocatedPrincipal();
        SDLFDrawdownPage dr=new SDLFDrawdownPage();
        String principal2 = dr.clickRepaymentScheduleOptions()
                .clickUploadPrincipalSchedule().uploadPrincipalSchedule()
                .enterLiability_upload_name()
                .checkUploadIsCompleted()
                .clickBeginImport().getUnallocatedPrincipal();
        Assertions.assertThat(principal2).isNotEqualTo(principal1).isEqualTo("0.00");

    }

    @Test(groups = {"Smoke"})
    public void SDLF_EditPrincipalPayments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown();
        ld.generate_LF_Equated_Principal_Schedule("On 2nd");
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        IntStream.rangeClosed(1, list.size())
                .forEachOrdered(i -> {
                    if (!DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[5]")).getText().equals("0.00")) {
                        String unallocated = ld.getUnallocatedPrincipal();
                        ld.clickEditRepayment(i).clickEditPrincipalSchedule().editNetAmount(200)
                                .editRepaymentScheduleDates(3, 5)
                                .click_MakePayment_Submit();
                        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
                        DriverManager.getDriver().navigate().refresh();
                        Assertions.assertThat(ld.getUnallocatedPrincipal())
                                .isNotEqualTo(unallocated);
                    }

                });
    }

    @Test(groups = {"Smoke"})
    public void SDLF_MakePrincipal_payments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown()
                .generate_LF_Equated_Principal_Schedule("On 2nd");
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        IntStream.rangeClosed(1, list.size())
                .forEachOrdered(i -> {
                    ld.clickMakePayments(i)
                            .selectOperatingAccount()
                            .selectPaymentAccount(data.get("payAcnt"))
                            .enterNotes("Payment")
                            .selectPaymentType(data.get("PaymentType"))
                            .click_MakePayment_Submit();
                    Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
                    Assertions.assertThat(ld.getRepaymentScheduleStatus(i))
                            .isEqualTo("Fully Paid");
                });

    }

    @Test(groups = {"Smoke"})
    public void SDLF_Delete_PrincipalPayments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown();
        ld.generate_LF_Equated_Principal_Schedule("On 2nd");
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        IntStream.rangeClosed(1, list.size())
                .forEachOrdered(i -> {
                    ld.clickMakePayments(i)
                            .selectOperatingAccount()
                            .selectPaymentAccount(data.get("payAcnt"))
                            .enterNotes("Payment")
                            .selectPaymentType(data.get("PaymentType"))
                            .click_MakePayment_Submit();
                    Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
                    Assertions.assertThat(ld.getRepaymentScheduleStatus(i))
                            .isEqualTo("Fully Paid");
                    ld.clickViewPayments(i)
                            .clickViewPrincipalSchedule()
                            .clickDeletePayment();
                    Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
                    Assertions.assertThat(ld.getRepaymentScheduleStatus(i))
                            .isNotEqualTo("Fully Paid");
                    Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);

                });


    }

    @Test(groups = {"Smoke"})
    public void SDLF_Create_Equated_Interest_Schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown().generate_LF_Equated_Principal_Schedule()
                .clickRepaymentScheduleOptions().selectAddEquatedInterestSchedule()
                .selectIRFrequency(data.get("Frequency")).IRPaymentDay(data.get("PayDay"))
                .selectInterestPaymentConvention(data.get("PaymentConvention"))
                .selectInterestRounding(data.get("IRMode")).TDSRounding(data.get("TDSRounding"))
                .clickOnPreview().clickOnGenerateSchedule().checkTotalInterest();
        String totalInterest = ld.getTotalInterest();
        Assertions.assertThat(totalInterest).isNotEqualTo("0.00");
        ld.checkIRScheduleGenerated();
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        IntStream.rangeClosed(1, list.size())
                .forEachOrdered(i -> {
                    if (!DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText().equals("0.00")) {
                        Assertions.assertThat(ld.getRepaymentScheduleStatus(i))
                                .isEqualTo("Pending");
                    }

                });

    }

    @Test(groups = {"Smoke"})
    public void SDLF_Create_Ad_Hoc_Interest_Schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown()
                .generate_LF_Equated_Principal_Schedule()
                .checkUnallocatedPrincipal()
                .clickRepaymentScheduleOptions()
                .selectAd_HocInterestSchedule()
                .selectValueDate(data.get("ValueDate"))
                .selectPaymentDate(data.get("PaymentDate"))
                .click_Ad_Hoc_ScheduleSubmit()
                .checkTotalInterest();
        String totalInterest = ld.getTotalInterest();
        Assertions.assertThat(totalInterest).isNotEqualTo("0.00");
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        IntStream.rangeClosed(1, list.size())
                .forEachOrdered(i -> {
                    if (!DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText().equals("0.0")) {
                        Assertions.assertThat(ld.getRepaymentScheduleStatus(i))
                                .isEqualTo("Pending");
                    }
                });
    }

    @Test(groups = {"Smoke"})
    public void SDLF_UploadInterestSchedule(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickSubDebtLF().moveToHamburgerMenu().clickAdd()
                .enterExternalID(10).enterLedgerID(4)
                .selectLoanFacilityType(data.get("LFType"))
                .enterROC(5).enterSanctionDate(data.get("SanctionDate"))
                .enterEndDate(data.get("EndDate")).enterLFAvailableTill(data.get("EndDate"))
                .selectEntity(data.get("Entity"))
                .selectCounterparty(data.get("Counterparty"))
                .enterFacilityAmount(data.get("LFAmount"))
                .selectArranger(data.get("Arranger"))
                .enterTrustee(data.get("Trustee"))
                .enterAdditionalInfo(data.get("Addinfo")).clickOnCreate();
        SDLFPage ldd=new SDLFPage();
        ldd.clickOptions().clickAddDrawdown().enterDrawdownExternalID(10)
                .selectPrepaymentsPenalty(data.get("penalty"))
                .enterDrawdownLedgerID(8).enterLoanAcnt("Loan ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPaymentAcnt(data.get("Payment_Account")).clickNewDisbursement()
                .enterDisAmount(data.get("DisAmount"))
                .selectDisbursementType("Standard").clickNewIrSlab()
                .selectIRType(data.get("IRType")).enterSpread(data.get("Spread"))
                .clickNewTDS().enterTDS(data.get("TDS")).enterAdditionalInfo("NA").clickCreate()
                .generate_LF_Equated_Principal_Schedule("On 2nd")
                .clickRepaymentScheduleOptions()
                .clickUploadInterestSchedule()
                .uploadInterestSchedule()
                .enterLiability_upload_name()
                .checkUploadIsCompleted()
                .clickBeginImport()
                .checkIRScheduleGenerated();
        SDLFDrawdownPage dr=new SDLFDrawdownPage();
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        IntStream.rangeClosed(1, list.size())
                .forEachOrdered(i -> {
                    if (!DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText().equals("0.00")) {
                        Assertions.assertThat(dr.getRepaymentScheduleStatus(i))
                                .isEqualTo("Pending");
                    }

                });


    }

    @Test(groups = {"Smoke"})
    public void SDLF_MakeInterest_payments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown()
                .generate_LF_Equated_Principal_Schedule("On 2nd")
                .generate_LF_Equated_Interest_Schedule("On 3rd");
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        IntStream.rangeClosed(1, list.size())
                .forEachOrdered(i -> {
                    if (!DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText().equals("0.00")) {
                        ld.clickMakePayments(i)
                                .selectOperatingAccount()
                                .selectPaymentAccount(data.get("payAcnt"))
                                .enterNotes("Payment")
                                .selectPaymentType(data.get("PaymentType"))
                                .click_MakePayment_Submit();
                        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
                        Assertions.assertThat(ld.getRepaymentScheduleStatus(i))
                                .isEqualTo("Fully Paid");
                    }
                });
    }

    @Test(groups = {"Smoke"})
    public void SDLF_EditInterestPayments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown();
        ld.generate_LF_Equated_Principal_Schedule("On 2nd")
                .generate_LF_Equated_Interest_Schedule("On 3rd");
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        IntStream.rangeClosed(1, list.size()-1)
                .forEachOrdered(i -> {
                    if (!DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText().equals("0.00")) {
                        String netAmount1 = ld.getInterestNetAmount(i);
                        ld.clickEditRepayment(i).clickEditInterestSchedule()
                                .editRepaymentScheduleDates(3, 5)
                                .click_MakePayment_Submit();
                        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
                        String netAmount2 = ld.getInterestNetAmount(i);
                        Assertions.assertThat(netAmount1)
                                .isNotEqualTo(netAmount2);

                    }
                });
    }

    @Test(groups = {"Smoke"})
    public void SDLF_Delete_InterestPayments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown();
        ld.generate_LF_Equated_Principal_Schedule("On 2nd")
                .generate_LF_Equated_Interest_Schedule("On 3rd");
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        IntStream.rangeClosed(1, list.size())
                .forEachOrdered(i -> {
                    if (!DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText().equals("0.00")) {
                        ld.clickMakePayments(i)
                                .selectOperatingAccount()
                                .selectPaymentAccount(data.get("payAcnt"))
                                .enterNotes("Payment")
                                .selectPaymentType(data.get("PaymentType"))
                                .click_MakePayment_Submit();
                        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
                        Assertions.assertThat(ld.getRepaymentScheduleStatus(i))
                                .isEqualTo("Fully Paid");
                        ld.clickViewPayments(i)
                                .clickViewInterestSchedule()
                                .clickDeletePayment();
                        Uninterruptibles.sleepUninterruptibly(3,TimeUnit.SECONDS);
                        Assertions.assertThat(ld.getRepaymentScheduleStatus(i))
                                .isNotEqualTo("Fully Paid");
                        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
                    }
                });
    }

    @Test(groups = {"Smoke"})
    public void SDLF_Make_Prepayments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        lf.clickOptions().clickAddDrawdown().enterDrawdownExternalID(10)
                .selectPrepaymentsPenalty(data.get("penalty"))
                .enterDrawdownLedgerID(8).enterLoanAcnt("Loan ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPaymentAcnt(data.get("Payment_Account")).clickNewDisbursement()
                .enterDisAmount(data.get("DisAmount"))
                .selectDisbursementType("Standard").clickNewIrSlab()
                .selectIRType(data.get("IRType")).enterSpread(data.get("Spread"))
                .clickNewTDS().enterTDS(data.get("TDS")).enterAdditionalInfo("NA")
                .clickCreate();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        double penalty1 = ld.click_PrepaymentOptions().select_MakePrepayments()
                .enterPrepaymentPaymentDate(data.get("prepaymentPaymentdate"))
                .enterPrepaymentValueDate(data.get("prepaymentValueDate"))
                .enterPrepaymentAmount(data.get("prepaymentAmount"))
                .click_MakePayment_Submit()
                .getPrepaymentPenalty();
        double penalty2 = Double.parseDouble(data.get("prepaymentAmount")) / Double.parseDouble(data.get("penalty"));
        Assertions.assertThat(penalty1).isEqualTo(penalty2);

    }

    @Test(groups = {"Smoke"})
    public void SDLF_Make_Prepayments_Payment_and_Delete(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown().make_prepayments()
                .make_prepayments_Payments();
        String[] ActualStatus = ld.getPrepaymentsStatus();
        IntStream.rangeClosed(0, ActualStatus.length - 1)
                .forEachOrdered(i -> Assertions.assertThat(ActualStatus[i])
                        .isEqualTo("Fully Paid"));
        ld.delete_prepayments();
        IntStream.rangeClosed(0, ld.getPrepaymentsStatus().length - 1)
                .forEachOrdered(i -> Assertions.assertThat(ld.getPrepaymentsStatus()[i])
                        .isEqualTo("Pending"));

    }

    @Test(groups = {"Smoke"})
    public void SDLF_Drawdown_AttachedDocuments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown().clickAttachedDocTab()
                .clickUploadDocuments()
                .uploadFile().clickClose_AttachedDocs();
        String fileName = ld.clickAttachedDocTab().getAttachedDocFileName();
        Assertions.assertThat(fileName).isEqualTo("AttachedDocuments");
    }

    @Test(groups = {"Smoke"})
    public void SDLF_Deactivate_Repayment_Schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        String unallocatedPrincipal = ld.create_New_SDLFDrawdown()
                .generate_LF_Equated_Principal_Schedule()
                .generate_LF_Equated_Interest_Schedule()
                .clickRepaymentScheduleOptions()
                .deactivate_Repayment_Schedule().getUnallocatedPrincipal();
        Assertions.assertThat(unallocatedPrincipal).isNotEqualTo("0.00");
        Assertions.assertThat(ld.getTotalInterest()).isEqualTo("0.00");
        boolean value = ld.clickDeactivatedSchedulesTab().checkDeactivatedSchedule();
        Assertions.assertThat(value).isTrue();
    }

    @Test(groups = {"Smoke"})
    public void SDLF_Equated_call_schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown();
        ld.clickCallSchedulesTab().clickCallSchedulesOptions()
                .clickEquatedCallSchedule()
                .selectCallFrequency(data.get("CallFrequency"))
                .selectCallExerciseDay(data.get("ExerciseDay"))
                .clickCallSchedulePreview()
                .clickOnGenerateSchedule().clickCallSchedulesTab();
        List<WebElement> ele = DriverManager.getDriver().findElements(By.xpath("(//tbody)[7]/tr"));
        Assertions.assertThat(ele.size()).isNotEqualTo(0).isGreaterThan(1);
        ele.clear();
        ld.clickCallSchedulesTab().clickDeactivate_callSchedule();
        List<WebElement> ele1 = DriverManager.getDriver().findElements(By.xpath("(//tbody)[7]/tr"));
        Assertions.assertThat(ele1.size()).isLessThanOrEqualTo(1);

    }

    @Test(groups = {"Smoke"})
    public void SDLF_Ad_hoc_call_schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf =new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown();
        ld.clickCallSchedulesTab();
        ld.clickCallSchedulesOptions()
                .clickAdHocCallSchedule()
                .enterCallDate(data.get("date1"))
                .click_Ad_Hoc_ScheduleSubmit().clickCallSchedulesTab()
                .clickCallSchedulesOptions()
                .clickAdHocCallSchedule()
                .enterCallDate(data.get("date2"))
                .click_Ad_Hoc_ScheduleSubmit().clickCallSchedulesTab();
        List<WebElement> ele = DriverManager.getDriver().findElements(By.xpath("(//tbody)[7]/tr"));
        Assertions.assertThat(ele.size()).isNotEqualTo(0).isEqualTo(2);
        ld.clickCallSchedulesTab().clickDeactivate_callSchedule();
        List<WebElement> ele1 = DriverManager.getDriver().findElements(By.xpath("(//tbody)[7]/tr"));
        Assertions.assertThat(ele1.size()).isLessThanOrEqualTo(1);
    }

    @Test(groups = {"Smoke"})
    public void SDLF_Add_and_Cancel_Fee(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility().clickOptions().clickAddDrawdown().enterDrawdownExternalID(10)
                .selectPrepaymentsPenalty("12")
                .enterDrawdownLedgerID(8).enterLoanAcnt("Loan ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPaymentAcnt("Loan Account").clickNewIrSlab()
                .selectIRType("Fixed").enterSpread("18")
                .clickNewTDS().enterTDS("24").enterAdditionalInfo("NA")
                .clickCreate().clickFeetab()
                .clickAddFee().feeType(data.get("FeeType"))
                .amountType(data.get("AmountType"))
                .enterFeeValue(data.get("FeeValue"))
                .enterRemarks("NA").click_Fee_Submit();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        Assertions.assertThat(ld.getFeeStatus("2")).isEqualTo("Live");
        ld.cancelFee();
        Uninterruptibles.sleepUninterruptibly(7, TimeUnit.SECONDS);
        ld.clickFeetab();
        Assertions.assertThat(ld.getFeeStatus("1")).isEqualTo("No data to show");

    }

    @Test(groups = {"Smoke"})
    public void SDLF_viewAnalytics_With_EquatedSchedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown().generate_LF_Equated_Principal_Schedule("On 2nd")
                .generate_LF_Equated_Interest_Schedule("On 3rd")
                .clickFeetab()
                .clickAddFee().feeType("Processing")
                .amountType("Percentage")
                .enterFeeValue("18")
                .selectUseForXirr()
                .selectUseForEir()
                .enterRemarks(data.get("Notes"))
                .clickSubmit();
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        Assertions.assertThat(ld.getFeeStatus("2")).isEqualTo("Live");
        ld.clickAnalyticsTab()
                .checkXirrEirValues();
        Assertions.assertThat(ld.getXirrValue()).doesNotContain("0.00")
                .doesNotContain("__.__%");
        Assertions.assertThat(ld.getEirValue()).doesNotContain("0.00")
                .doesNotContain("__.__%");

    }

    @Test(groups = {"Smoke"})
    public void SDLF_viewAnalytics_With_AdHocSchedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown();
        String endDate = ld.getDrawdownEndDate();
        ld.generate_LF_AdHoc_Principal_Schedule(endDate)
                .generate_LF_AdHoc_Interest_Schedule()
                .clickFeetab()
                .clickAddFee().feeType("Processing")
                .amountType("Percentage")
                .enterFeeValue("18")
                .selectUseForXirr()
                .selectUseForEir()
                .enterRemarks(data.get("Notes"))
                .clickSubmit();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        ld.clickAnalyticsTab()
                .checkXirrEirValues();
        Assertions.assertThat(ld.getXirrValue()).doesNotContain("0.00")
                .doesNotContain("__.__%");
        Assertions.assertThat(ld.getEirValue()).doesNotContain("0.00")
                .doesNotContain("__.__%");
    }

    @Test(groups = {"Smoke"})
    public void SDLF_viewAnalytics_With_UploadSchedules(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickSubDebtLF().moveToHamburgerMenu().clickAdd()
                .enterExternalID(10).enterLedgerID(4)
                .selectLoanFacilityType(data.get("LFType"))
                .enterROC(5).enterSanctionDate(data.get("SanctionDate"))
                .enterEndDate(data.get("EndDate")).selectEntity(data.get("Entity"))
                .selectCounterparty(data.get("Counterparty"))
                .enterFacilityAmount(data.get("LFAmount"))
                .selectArranger(data.get("Arranger")).enterTrustee(data.get("Trustee"))
                .enterAdditionalInfo(data.get("Addinfo")).clickOnCreate();
        SDLFPage ldd=new SDLFPage();
        ldd.clickOptions().clickAddDrawdown().enterDrawdownExternalID(10)
                .selectPrepaymentsPenalty(data.get("penalty"))
                .enterDrawdownLedgerID(8).enterLoanAcnt("Loan ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPaymentAcnt(data.get("Payment_Account")).clickNewDisbursement()
                .enterDisAmount(data.get("DisAmount"))
                .selectDisbursementType("Standard").clickNewIrSlab()
                .selectIRType(data.get("IRType")).enterSpread(data.get("Spread"))
                .clickNewTDS().enterTDS(data.get("TDS")).enterAdditionalInfo("NA")
                .clickCreate();
        SDLFDrawdownPage dr=new SDLFDrawdownPage();
        dr.clickRepaymentScheduleOptions()
                .clickUploadPrincipalSchedule().uploadPrincipalSchedule()
                .enterLiability_upload_name()
                .checkUploadIsCompleted()
                .clickBeginImport();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        dr.clickRepaymentScheduleOptions()
                .clickUploadInterestSchedule()
                .uploadInterestSchedule()
                .enterLiability_upload_name()
                .checkUploadIsCompleted()
                .clickBeginImport();
        dr.clickFeetab()
                .clickAddFee().feeType("Processing")
                .amountType("Percentage")
                .enterFeeValue("18")
                .selectUseForXirr()
                .selectUseForEir()
                .enterRemarks("NA")
                .click_Fee_Submit();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        dr.clickAnalyticsTab()
                .checkXirrEirValues();
        Assertions.assertThat(dr.getXirrValue()).isNotEqualTo("0.00")
                .doesNotContain("__.__%");
        Assertions.assertThat(dr.getEirValue()).isNotEqualTo("0.00")
                .doesNotContain("__.__%");
    }

    @Test(groups = {"Smoke"})
    public void SDLF_Drawdown_Level_Add_Covenants(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lpp = new SDLFPage();
        lpp.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown().clickCovenantsTab()
                .clickAddCovenants().covenantTemplate(data.get("CovenantTemplate"))
                .enterAccountablePerson(data.get("AcntblePerson"))
                .enterThirdParty(data.get("ThirdParty")).enterTargetValue(data.get("TargetValue"))
                .enterTargetDate(data.get("TargetDate")).enterOffset(data.get("Offset"))
                .enterCovenantEndDate(data.get("EndDate")).selectMappingConditions(data.get("Mapping"))
                .selectCovenantsEntity(data.get("Entity")).selectRatioName(data.get("Ratio"))
                .enterThresholdPercentage(data.get("TP")).clickOnCreate();
        int size = ld.getCovenantsSize();
        Assertions.assertThat(size).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);

    }

    @Test(groups = {"Smoke"})
    public void SDLF_Drawdown_Level_LienFD(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        FixedDepositPage fd = new FixedDepositPage();
        fd.create_FD_with_InterestSchedule();
        SDLFPage lpp=new SDLFPage();
        lpp.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown().click_Securitization().click_LienFD_Options()
                .selectAvailableFDs().selectFDs()
                .click_FD_Submit();
        int lienedFDs = ld.click_Securitization().getLiedFDNum();
        Assertions.assertThat(lienedFDs).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);


    }

    @Test(groups = {"Smoke"})
    public void SDLF_Close_Drawdown(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown();
        String date = ld.getDrawdownEndDate();
        ld.generate_LF_AdHoc_Principal_Schedule(date)
                .checkUnallocatedPrincipal();
               // .make_Principal_Payments(data.get("PaymentType"));
        String[] ActualStatus = ld.getPrincipalScheduleStatus();
        IntStream.rangeClosed(0, ActualStatus.length - 1)
                .forEachOrdered(i -> Assertions.assertThat(ActualStatus[i])
                        .isEqualTo("Fully Paid"));

        ld.moveToHamburgerMenu().clickCloseDrawdown()
                .enterCloseNotes("Closed")
                .clickSubmitToClose();
        Assertions.assertThat(ld.closureDocumentsTabDisplayed()).isTrue();
    }

    @Test(groups = {"Smoke"})
    public void SDLF_Delete_Drawdown(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        String extid1 = ld.create_New_SDLFDrawdown().getDrawdownExtId();
        String extid2 = ld.moveToHamburgerMenu()
                .clickDeleteDrawdown().getArchivedDrawdownExtId();
        Assertions.assertThat(extid1).isEqualTo(extid2);
    }


    @Test(groups = {"Smoke"})
    public void SDLF_level_AttachedDocuments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility().clickAttachedDocTab()
                .clickUploadDocuments()
                .uploadFile().clickClosebtn();
        String fileName = lf.getAttachedDocFileName();
        Assertions.assertThat(fileName).isEqualTo("AttachedDocuments");
    }

    @Test(groups = {"Smoke"})
    public void SDLF_Level_Add_Covenants(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lpp = new SDLFPage();
        lpp.create_new_SubDebt_LoanFacility().clickCovenantsTab().clickAddCovenants()
                .covenantTemplate(data.get("CovenantTemplate"))
                .enterAccountablePerson(data.get("AcntblePerson"))
                .enterThirdParty(data.get("ThirdParty")).enterTargetValue(data.get("TargetValue"))
                .enterTargetDate(data.get("TargetDate")).enterOffset(data.get("Offset"))
                .enterCovenantEndDate(data.get("EndDate")).selectMappingConditions(data.get("Mapping"))
                .selectCovenantsEntity(data.get("Entity")).selectRatioName(data.get("Ratio"))
                .enterThresholdPercentage(data.get("TP")).clickOnCreate();
        int size = lpp.getCovenantsSize();
        Assertions.assertThat(size).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);

    }

    @Test(groups = {"Smoke"})
    public void SDLF_Level_LienFD(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        FixedDepositPage fd = new FixedDepositPage();
        fd.create_FD_with_InterestSchedule();
        SDLFPage lpp=new SDLFPage();
        lpp.create_new_SubDebt_LoanFacility().clickLienDetails()
                .clickAddToLienFD().clickOnCreate();
        int lienedFDs = lpp.clickLienDetails().getLiedFDNum();
        Assertions.assertThat(lienedFDs).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);

    }

    @Test(groups = {"Smoke"})
    public void SDLF_Create_Close(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        String LfExternalID = lf.create_new_SubDebt_LoanFacility().getLfExrnlID();
        lf.clickHamburger().clickClose().enterCloseNotes(data.get("Notes"))
                .clickSubmitToClose();
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLoanFacility();
        String expectedExtId = lf.gotLoanFacilityBlotter()
                .clickClosedTab().searchExtId(LfExternalID).getFirstLoan();
        Assertions.assertThat(expectedExtId).isEqualTo(LfExternalID);
    }

    @Test(groups = {"Smoke"})
    public void SDLF_Create_Delete(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        String LfExternalID = lf.create_new_SubDebt_LoanFacility().getLfExrnlID();
        String expectedExtId = lf.clickHamburger().clickDeleteIcon().clickArchivedTab()
                .searchExtId(LfExternalID).getFirstLoan();
        Assertions.assertThat(expectedExtId).isEqualTo(LfExternalID);

    }
    @Test(groups = {"Smoke"})
    public void SDLF_Level_SaveAsDraft(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickSubDebtLF().moveToHamburgerMenu().clickAdd();
        NewSDLFpage lf=new NewSDLFpage();
        String extId = lf.generateExternalID();
        lf.enterExternalID(extId).enterLedgerID(4)
                .selectLoanFacilityType(data.get("LFType"))
                .enterROC(5).enterSanctionDate(data.get("SanctionDate"))
                .enterEndDate(data.get("EndDate")).selectEntity(data.get("Entity"))
                .selectCounterparty(data.get("Counterparty"))
                .enterFacilityAmount(data.get("LFAmount"))
                .covenantDefaultIR(data.get("CDIR")).paymentDefaultIR(data.get("PDIR"))
                .selectArranger(data.get("Arranger"));
        lf.clickSaveAsDraft()
                .clickDraftsTab().searchExtId(extId).clickDraftedLoanEdit()
                .primarySecurityDetails()
                .secondarySecurityDetails().personalGuaranteeDetails()
                .corporateGuaranteeDetails().enterTrustee(data.get("Trustee"))
                .enterAdditionalInfo(data.get("Addinfo")).clickOnCreate();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Loan Facility - LF")
                .isNotEmpty()
                .isNotNull();

    }

    @Test(groups = {"Smoke"})
    public void SDLF_Level_DeleteDraftedRecord(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickSubDebtLF().moveToHamburgerMenu().clickAdd();
        NewSDLFpage lf=new NewSDLFpage();
        String extId = lf.generateExternalID();
        String value = lf.enterExternalID(extId).clickSaveAsDraft()
                .clickDraftsTab().searchExtId(extId).clickDraftedLoanDelete()
                .clickDraftsTab().searchExtId(extId).getFirstLoan();
        Assertions.assertThat(value).isEqualTo("No matching records found");
    }

    @Test(groups = {"Smoke"})
    public void SDLF_Drawdown_DeleteDraftedRecord(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility().clickOptions().clickAddDrawdown()
                .enterDrawdownExternalID(6).enterDrawdownLedgerID(3)
                .clickSaveAsDraft().clickCancel().clickDraftedDrawdownDeleteOption();
        Assertions.assertThat(lf.DraftedDrawdownDeleted(
                By.xpath("//p[text()='Drafted Drawdowns']"))).isTrue();
    }

    @Test(groups = {"Smoke"})
    public void SDLF_Drawdown_SaveAsDraft(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility().clickOptions().clickAddDrawdown()
                .enterDrawdownExternalID(6)
                .selectPrepaymentsPenalty(data.get("penalty"))
                .enterDrawdownLedgerID(8).enterLoanAcnt("Loan ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPaymentAcnt(data.get("Payment_Account"))
                .clickSaveAsDraft().clickCancel().clickDraftedDrawdownEditOption()
                .clickNewIrSlab()
                .selectIRType(data.get("IRType")).enterSpread(data.get("Spread"))
                .clickNewTDS().enterTDS(data.get("TDS")).enterAdditionalInfo("NA").clickCreate();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Drawdown - LoanFacility-Drawdown-")
                .isNotEmpty()
                .isNotNull();

    }

    @Test(groups = {"Regression"})
    public void LoanFacility_CoverDetails_Calculation(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickSubDebtLF().moveToHamburgerMenu().clickAdd()
                .enterExternalID(10).enterLedgerID(4)
                .selectLoanFacilityType(data.get("LFType"))
                .enterROC(5).enterSanctionDate(data.get("SanctionDate"))
                .enterEndDate(data.get("EndDate")).selectEntity(data.get("Entity"))
                .selectCounterparty(data.get("Counterparty"))
                .enterFacilityAmount(data.get("LFAmount"))
                .covenantDefaultIR(data.get("CDIR")).paymentDefaultIR(data.get("PDIR"))
                .selectArranger(data.get("Arranger"));
        NewSDLFpage lf=new NewSDLFpage();
        double psa= lf.calculatePrimarySecurityAmount(data.get("LFAmount"));
        double ssa=lf .calculateSecondarySecurityAmount(data.get("LFAmount"));
        double pga= lf.calculatePersonalGuaranteeAmount(data.get("LFAmount"));
        double cga=lf.calculateCorporateGuaranteeAmount(data.get("LFAmount"));
        lf.enterTrustee(data.get("Trustee"))
                .enterAdditionalInfo(data.get("Addinfo")).clickOnCreate();
        SDLFPage le=new SDLFPage();
        Assertions.assertThat(psa).isEqualTo(le.getPrimarySecurityAmount());
        Assertions.assertThat(ssa).isEqualTo(le.getSecondarySecurityAmount());
        Assertions.assertThat(pga).isEqualTo(le.getPersonalGuaranteeAmount());
        Assertions.assertThat(cga).isEqualTo(le.getCorporateGuaranteeAmount());
        Assertions.assertThat(le.getTotalSecurityAmount()).isEqualTo(psa+ssa);
        Assertions.assertThat(le.getTotalGuaranteeAmount()).isEqualTo(cga+pga);
    }

    @Test(groups = {"Regression"})
    public void SDLF_FacilityAmountSegregation(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        double principal1= ld.create_New_SDLFDrawdown().clickLoanFacilityLink()
                .getTotalPrincipalDrawn();
        Assertions.assertThat(principal1).isEqualTo(lf.getPrincipalUsed());
        double facilityAvailable= lf.getFacilityAmount()-lf.getPrincipalUsed();
        Assertions.assertThat(facilityAvailable).isEqualTo(lf.getFacilityAvailable());

    }

    @Test(groups = {"Regression"})
    public void SDLF_Drawdown_PrincipalSegregation(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown()
                .generate_LF_Equated_Principal_Schedule("On 2nd");
        double settledPrincipal = 0.00;
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        int bound = list.size();
        for (int i = 1; i <= bound; i++) {
            ld.clickMakePayments(i)
                    .selectOperatingAccount()
                    .selectPaymentAccount(data.get("payAcnt"))
                    .enterNotes("Payment")
                    .selectPaymentType(data.get("PaymentType"))
                    .click_MakePayment_Submit();
            settledPrincipal = settledPrincipal + ld.getSettledAmount(i);
            Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
            Assertions.assertThat(settledPrincipal)
                    .isEqualTo(ld.getPrincipalRepaidSum());
            double principalOutstanding = ld.getPrincipalDrawn() - settledPrincipal;
            Assertions.assertThat(principalOutstanding).isEqualTo(ld.getPrincipalOutstanding());
        }
    }

    @Test(groups = {"Smoke"})
    public void SDLF_Create_EMI_Schedule_FixEMI(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        String UnallocatedPrincipal = ld.create_New_SDLFDrawdown().clickRepaymentScheduleOptions()
                .selectAddEmiRepaymentSchedule().selectFix("Emi")
                .selectEmiPaymentFrequency(data.get("payout"))
                .enterEmiAmount(data.get("EmiAmount")).selectEmiPaymentDay(data.get("payday"))
                .selectEmiPaymentConvention("NADJ").emiInterestRounding(data.get("rounding"))
                .emiTDSRounding(data.get("TDSRounding")).clickOnPreview().clickOnGenerateSchedule().checkUnallocatedPrincipal()
                .getUnallocatedPrincipal();
        Assertions.assertThat(UnallocatedPrincipal).isEqualTo("0.00");
    }

    @Test(groups = {"Regression"})
    public void SDLF_Equated_InterestCalculations(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown().generate_LF_Equated_Principal_Schedule("On 2nd")
                .generate_LF_Equated_Interest_Schedule("On 2nd");
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        int bound = list.size();
        for (int i = 1; i <= bound; i++) {
            if (ld.calculateInterestAmount(i) != null) {
                String[] ir = DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText().split(",");
                Assertions.assertThat((double) ld.calculateInterestAmount(i)).isCloseTo(
                        Double.parseDouble(String.join("", ir)), within(0.1)
                );
            }
        }
    }

    @Test(groups = {"Regression"})
    public void SDLF_AdHoc_InterestCalculations(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        String endDate= ld.create_New_SDLFDrawdown().getDrawdownEndDate();
        ld.generate_LF_AdHoc_Principal_Schedule(endDate)
                .generate_LF_AdHoc_Interest_Schedule(data.get("valueDate1"),data.get("paymentDate1"))
                .generate_LF_AdHoc_Interest_Schedule(data.get("valueDate2"),data.get("paymentDate2"))
                .generate_LF_AdHoc_Interest_Schedule(data.get("valueDate3"),data.get("paymentDate3"));
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        int bound = list.size();
        for (int i = 1; i <= bound-1; i++) {
            if (ld.calculateInterestAmount(i) != null) {
                String[]ir=  DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText().split(",");
                Assertions.assertThat((double) ld.calculateInterestAmount(i)).isCloseTo(
                        Double.parseDouble(String.join("",ir)), within(0.1)  );

            }
        }
    }

    @Test(groups = {"Regression"})
    public void SDLF_Equated_UpdateInterestDates_Calculation(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        String endDate1 = ld.create_New_SDLFDrawdown().getDrawdownEndDate();
        ld.generate_LF_Equated_Principal_Schedule("On 5th");
        ld.generate_LF_Equated_Interest_Schedule("On 2nd");
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        for (int i = 1; i < list.size(); i++) {
            if (!DriverManager.getDriver().findElement(By.xpath(
                    "(//tbody)[6]/tr[" + i + "]/td[7]")).getText().equals("0.00")) {
                if (!DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[3]")).getText().equals(endDate1)) {
                    ld.clickEditRepayment(i).clickEditInterestSchedule()
                            .editRepaymentScheduleDates(3, 3)
                            .click_MakePayment_Submit();
                    Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
                    continue;
                }
                break;
            }
        }
        for (int i = 1; i <= list.size(); i++) {
            if (ld.calculateInterestAmount(i) != null) {
                String[]ir= DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]"))
                        .getText().split(",");
                if (!DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[3]")).getText().equals(endDate1)) {
                    Assertions.assertThat((double) ld.calculateInterestAmount(i)).isCloseTo(
                            Double.parseDouble(String.join("",ir)), within(0.1));
                    DriverManager.getDriver().navigate().refresh();
                    continue;
                }
                break;
            }
        }
    }

    @Test(groups = {"Regression"})
    public void SDLF_Adhoc_UpdateInterestDates_Calculation(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        String endDate = ld.create_New_SDLFDrawdown().getDrawdownEndDate();
        ld.generate_LF_AdHoc_Principal_Schedule(endDate)
                .generate_LF_AdHoc_Interest_Schedule(data.get("valueDate1"), data.get("paymentDate1"))
                .generate_LF_AdHoc_Interest_Schedule(data.get("valueDate2"), data.get("paymentDate2"))
                .generate_LF_AdHoc_Interest_Schedule(data.get("valueDate3"), data.get("paymentDate3"));

        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        int bound = list.size();
        for (int i = 1; i < bound - 1; i++) {
            if (!DriverManager.getDriver().findElement(By.xpath(
                    "(//tbody)[6]/tr[" + i + "]/td[7]")).getText().equals("0.0")) {
                if (!DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[3]")).getText().equals(endDate)) {
                    ld.clickEditRepayment(i).clickEditInterestSchedule()
                            .editRepaymentScheduleDates(3, 3)
                            .click_Ad_Hoc_ScheduleSubmit();
                    Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
                    continue;
                }
                break;
            }
        }
        ld.checkIRScheduleGenerated();
        for (int i = 1; i <= bound - 1; i++) {
            if (ld.calculateInterestAmount(i) != null) {
                String[] ir=DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]"))
                        .getText().split(",");
                Assertions.assertThat((double) ld.calculateInterestAmount(i)).isCloseTo(
                        Double.parseDouble(String.join("",ir)), within(0.1)
                );
            }
        }
    }

    @Test(groups = {"Regression"})
    public void SDLF_Equated_InterestCalculationsForSucceeding(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown().generate_LF_Equated_Principal_Schedule("On 2nd")
                .clickRepaymentScheduleOptions()
                .selectAddEquatedInterestSchedule()
                .selectIRFrequency("Annually")
                .IRPaymentDay("On 2nd")
                .selectDayCountConvention(data.get("DaysCountConvention"))
                .selectInterestPaymentConvention("NADJ")
                .selectInterestRounding("NONE").TDSRounding("NONE")
                .clickOnPreview().clickOnGenerateSchedule()
                .checkIRScheduleGenerated();
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        int bound = list.size();
        for (int i = 1; i <= bound; i++) {
            if (ld.calculateInterestAmountForSucceeding(i) != null) {
                String[] ir=DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText().split(",");
                Assertions.assertThat((double) ld.calculateInterestAmountForSucceeding(i)).isCloseTo(
                        Double.parseDouble(String.join("",ir)), within(0.1)
                );
            }
        }
    }

    @Test(groups = {"Regression"})
    public void SDLF_UseFirstDay_Calculation(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown().generate_LF_Equated_Principal_Schedule("On 2nd","Quarterly","NADJ")
                .clickRepaymentScheduleOptions()
                .selectAddEquatedInterestSchedule()
                .selectIRFrequency("Quarterly")
                .IRPaymentDay("On 2nd")
                .selectUseFirstDayForCalculation()
                .selectInterestPaymentConvention("NADJ")
                .selectInterestRounding("NONE").TDSRounding("NONE")
                .clickOnPreview().clickOnGenerateSchedule()
                .checkIRScheduleGenerated();
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        int bound = list.size();
        for (int i = 1; i <= bound; i++) {
            if (ld.calculateInterestAmountForFirstDayCalculation(i) != null) {
                String[] ir=DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText().split(",");
                Assertions.assertThat((double) ld.calculateInterestAmountForFirstDayCalculation(i)).isCloseTo(
                        Double.parseDouble(String.join("",ir)), within(0.1)

                );
            }
        }
    }

    @Test(groups = {"Regression"})
    public void SDLF_UseLastDay_Calculation(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown().generate_LF_Equated_Principal_Schedule("On 2nd","Quarterly","NADJ")
                .clickRepaymentScheduleOptions()
                .selectAddEquatedInterestSchedule()
                .selectIRFrequency("Quarterly")
                .IRPaymentDay("On 2nd")
                .selectUseLastDayForCalculation()
                .selectInterestPaymentConvention("NADJ")
                .selectInterestRounding("NONE").TDSRounding("NONE")
                .clickOnPreview().clickOnGenerateSchedule()
                .checkIRScheduleGenerated();
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        int bound = list.size();
        for (int i = 1; i <= bound; i++) {
            if (ld.calculateInterestAmountForLastDayCalculation(i,bound) != null) {
                String[]ir=DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText().split(",");
                Assertions.assertThat((double) ld.calculateInterestAmountForLastDayCalculation(i,bound)).isCloseTo(
                        Double.parseDouble(String.join("",ir)), within(0.1)
                );
            }
        }
    }

    @Test(groups = {"Regression"})
    public void SDLF_InterestCalculation_On_ClosingBalance(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown().generate_LF_Equated_Principal_Schedule("On 2nd","Semi Annually","NADJ")
                .clickRepaymentScheduleOptions()
                .selectAddEquatedInterestSchedule()
                .selectInterestCalculatedOnClosingBalance()
                .selectIRFrequency("Semi Annually")
                .IRPaymentDay("On 2nd")
                .selectDayCountConvention(data.get("DaysCountConvention"))
                .selectInterestPaymentConvention("NADJ")
                .selectInterestRounding("NONE").TDSRounding("NONE")
                .clickOnPreview().clickOnGenerateSchedule()
                .checkIRScheduleGenerated();
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        int bound = list.size();
        for (int i = 1; i <= bound; i++) {
            if (ld.calculateInterestAmountForClosingBalance(i) != null) {
                String[]ir=DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]"))
                        .getText().split(",");
                Assertions.assertThat((double) ld.calculateInterestAmountForClosingBalance(i)).isCloseTo(
                        Double.parseDouble(String.join("",ir)), within(0.1)
                );
            }
        }
    }

    @Test(groups = {"Regression"})
    public void SDLF_InterestCalculation_with_ConstantDays(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown().generate_LF_Equated_Principal_Schedule("On 2nd","Quarterly","NADJ")
                .clickRepaymentScheduleOptions()
                .selectAddEquatedInterestSchedule()
                .selectIRFrequency("Quarterly")
                .IRPaymentDay("On 2nd")
                .selectConstantDays()
                .enterDaysInaYear(data.get("numberOfDays"))
                .selectInterestPaymentConvention("NADJ")
                .selectInterestRounding("NONE").TDSRounding("NONE")
                .clickOnPreview().clickOnGenerateSchedule()
                .checkIRScheduleGenerated();
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        int bound = list.size();
        for (int i = 1; i <= bound; i++) {
            if (ld.calculateInterestAmountForConstantDays(i, Integer.parseInt(data.get("numberOfDays"))) != null) {
                String[]ir=DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText().split(",");
                Assertions.assertThat((double) ld.calculateInterestAmountForConstantDays(i, Integer.parseInt(data.get("numberOfDays")))).isCloseTo(
                        Double.parseDouble(String.join("",ir)), within(0.1)
                );
            }
        }
    }

    @Test(groups = {"Regression"})
    public void SDLF_InterestCalculation_with_ThirtyBy360(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown().generate_LF_Equated_Principal_Schedule("On 2nd","Quarterly","NADJ")
                .clickRepaymentScheduleOptions()
                .selectAddEquatedInterestSchedule()
                .selectIRFrequency("Quarterly")
                .IRPaymentDay("On 2nd")
                .selectThirtyBy360()
                .selectInterestPaymentConvention("NADJ")
                .selectInterestRounding("NONE").TDSRounding("NONE")
                .clickOnPreview().clickOnGenerateSchedule()
                .checkIRScheduleGenerated();
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        int bound = list.size();
        for (int i = 1; i <= bound; i++) {
            if (ld.calculateInterestAmountForThirtyBy360(i) != null) {
                String[] ir=DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText().split(",");
                Assertions.assertThat((double) ld.calculateInterestAmountForThirtyBy360(i)).isCloseTo(
                        Double.parseDouble(String.join("",ir)), within(0.1)
                );
            }
        }
    }

    @Test(groups = {"Regression"})
    public void SDLF_InterestCalculation_with_ActualDaysAsPerFinYear(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown().generate_LF_Equated_Principal_Schedule("On 2nd","Quarterly","NADJ")
                .clickRepaymentScheduleOptions()
                .selectAddEquatedInterestSchedule()
                .selectIRFrequency("Quarterly")
                .IRPaymentDay("On 2nd")
                .selectActual_days_as_per_financial_year()
                .selectInterestPaymentConvention("NADJ")
                .selectInterestRounding("NONE").TDSRounding("NONE")
                .clickOnPreview().clickOnGenerateSchedule()
                .checkIRScheduleGenerated();
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        int bound = list.size();
        for (int i = 1; i <= bound; i++) {
            if (ld.calculateInterestAmountForActual_days_as_per_financial_year(i) != null) {
                String[]ir=DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText().split(",");
                Assertions.assertThat((double) ld.calculateInterestAmountForActual_days_as_per_financial_year(i)).isCloseTo(
                        Double.parseDouble(String.join("",ir)), within(0.1)
                );
            }
        }
    }

    @Test(groups = {"Smoke"})
    public void SDLF_Add_Disbursement(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility().clickOptions().clickAddDrawdown().enterDrawdownExternalID(10)
                .selectPrepaymentsPenalty(data.get("penalty"))
                .enterDrawdownLedgerID(8).enterLoanAcnt("Loan ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPaymentAcnt(data.get("Payment_Account")).clickNewIrSlab()
                .selectIRType(data.get("IRType")).enterSpread(data.get("Spread"))
                .clickNewTDS().enterTDS(data.get("TDS")).enterAdditionalInfo("NA").clickCreate()
                .selectDisOptions().clickAddDisbursement()
                .enterDisbursementValueDate(data.get("valueDate"))
                .enterDisbursementTransactionDate(data.get("tranDate"))
                .enterDisAmount(data.get("DisAmount")).selectDisbursementType("Standard")
                .enterDisbursementNotes("NA").click_Disbursement_Submit();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        Assertions.assertThat(ld.getUnallocatedPrincipal()).isNotEqualTo("0.00");
    }
    @Test(groups = {"Smoke"})
    public void SDLF_Edit_Disbursement(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown().clickEditDisbursement()
                .enterDisbursementValueDate(data.get("valueDate"))
                .enterDisbursementNotes("Updated")
                .click_MakePayment_Submit();
        Assertions.assertThat(ld.getDisbursementValueDate()).isEqualTo(data.get("valueDate"));

    }

    @Test(groups = {"Smoke"})
    public void SDLF_Delete_Disbursement(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown().clickDeleteDisbursement();
        Assertions.assertThat(ld.getUnallocatedPrincipal()).isEqualTo("0.00");

    }

    @Test(groups = {"Regression"})
    public void SDLF_View_Analytics_with_EquatedPrincipal_And_AdHocInterest(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown().generate_LF_Equated_Principal_Schedule("On 2nd")
                .generate_LF_AdHoc_Interest_Schedule()
                .clickFeetab()
                .clickAddFee().feeType("Processing")
                .amountType("Percentage")
                .enterFeeValue("18")
                .selectUseForXirr()
                .selectUseForEir()
                .enterRemarks(data.get("Notes"))
                .click_Fee_Submit();
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        ld.clickAnalyticsTab()
                .checkXirrEirValues();
        Assertions.assertThat(ld.getXirrValue()).doesNotContain("0.00")
                .doesNotContain("__.__%");
        Assertions.assertThat(ld.getEirValue()).doesNotContain("0.00")
                .doesNotContain("__.__%");

    }

    @Test(groups = {"Regression"})
    public void SDLF_View_Analytics_with_AdHocPrincipal_And_EquatedInterest(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown();
        String endDate = ld.getDrawdownEndDate();
        ld.generate_LF_AdHoc_Principal_Schedule(endDate)
                .generate_LF_Equated_Interest_Schedule("On 3rd")
                .clickFeetab()
                .clickAddFee().feeType("Processing")
                .amountType("Percentage")
                .enterFeeValue("18")
                .selectUseForXirr()
                .selectUseForEir()
                .enterRemarks(data.get("Notes"))
                .click_Fee_Submit();
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        ld.clickAnalyticsTab()
                .checkXirrEirValues();
        Assertions.assertThat(ld.getXirrValue()).doesNotContain("0.00")
                .doesNotContain("__.__%");
        Assertions.assertThat(ld.getEirValue()).doesNotContain("0.00")
                .doesNotContain("__.__%");

    }

    @Test(groups = {"Regression"})
    public void SDLF_View_Analytics_with_UploadPrincipal_And_EquatedInterest(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickSubDebtLF().moveToHamburgerMenu().clickAdd()
                .enterExternalID(10).enterLedgerID(4)
                .selectLoanFacilityType(data.get("LFType"))
                .enterROC(5).enterSanctionDate(data.get("SanctionDate"))
                .enterEndDate(data.get("EndDate")).selectEntity(data.get("Entity"))
                .selectCounterparty(data.get("Counterparty"))
                .enterFacilityAmount(data.get("LFAmount"))
                .selectArranger(data.get("Arranger")).enterTrustee(data.get("Trustee"))
                .enterAdditionalInfo(data.get("Addinfo")).clickOnCreate();
        SDLFPage ldd=new SDLFPage();
        ldd.clickOptions().clickAddDrawdown().enterDrawdownExternalID(10)
                .selectPrepaymentsPenalty(data.get("penalty"))
                .enterDrawdownLedgerID(8).enterLoanAcnt("Loan ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPaymentAcnt(data.get("Payment_Account")).clickNewDisbursement()
                .enterDisAmount(data.get("DisAmount"))
                .selectDisbursementType("Standard").clickNewIrSlab()
                .selectIRType(data.get("IRType")).enterSpread(data.get("Spread"))
                .clickNewTDS().enterTDS(data.get("TDS")).enterAdditionalInfo("NA")
                .clickCreate();
        SDLFDrawdownPage dr=new SDLFDrawdownPage();
        dr.clickRepaymentScheduleOptions()
                .clickUploadPrincipalSchedule().uploadPrincipalSchedule()
                .enterLiability_upload_name()
                .checkUploadIsCompleted()
                .clickBeginImport();
        dr.generate_LF_Equated_Interest_Schedule("On 3rd")
                .clickFeetab()
                .clickAddFee().feeType("Processing")
                .amountType("Percentage")
                .enterFeeValue("18")
                .selectUseForXirr()
                .selectUseForEir()
                .enterRemarks(data.get("Notes"))
                .click_Fee_Submit();
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        dr.clickAnalyticsTab()
                .checkXirrEirValues();
        Assertions.assertThat(dr.getXirrValue()).doesNotContain("0.00")
                .doesNotContain("__.__%");
        Assertions.assertThat(dr.getEirValue()).doesNotContain("0.00")
                .doesNotContain("__.__%");

    }

    @Test(groups = {"Regression"})
    public void SDLF_View_Analytics_with_AdHocPrincipal_And_UploadedInterest(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickSubDebtLF().moveToHamburgerMenu().clickAdd()
                .enterExternalID(10).enterLedgerID(4)
                .selectLoanFacilityType(data.get("LFType"))
                .enterROC(5).enterSanctionDate(data.get("SanctionDate"))
                .enterEndDate(data.get("EndDate")).selectEntity(data.get("Entity"))
                .selectCounterparty(data.get("Counterparty"))
                .enterFacilityAmount(data.get("LFAmount"))
                .selectArranger(data.get("Arranger")).enterTrustee(data.get("Trustee"))
                .enterAdditionalInfo(data.get("Addinfo")).clickOnCreate();
        SDLFPage ldd=new SDLFPage();
        ldd.clickOptions().clickAddDrawdown().enterDrawdownExternalID(10)
                .selectPrepaymentsPenalty(data.get("penalty"))
                .enterDrawdownLedgerID(8).enterLoanAcnt("Loan ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPaymentAcnt(data.get("Payment_Account")).clickNewDisbursement()
                .enterDisAmount(data.get("DisAmount"))
                .selectDisbursementType("Standard").clickNewIrSlab()
                .selectIRType(data.get("IRType")).enterSpread(data.get("Spread"))
                .clickNewTDS().enterTDS(data.get("TDS")).enterAdditionalInfo("NA")
                .clickCreate();
        SDLFDrawdownPage dr=new SDLFDrawdownPage();
        String date=dr.getDrawdownEndDate();
        dr.generate_LF_AdHoc_Principal_Schedule(date);
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        dr.clickRepaymentScheduleOptions()
                .clickUploadInterestSchedule()
                .uploadInterestSchedule()
                .enterLiability_upload_name()
                .checkUploadIsCompleted()
                .clickBeginImport();
        dr.clickFeetab()
                .clickAddFee().feeType("Processing")
                .amountType("Percentage")
                .enterFeeValue("18")
                .selectUseForXirr()
                .selectUseForEir()
                .enterRemarks(data.get("Notes"))
                .click_Fee_Submit();
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        dr.clickAnalyticsTab()
                .checkXirrEirValues();
        Assertions.assertThat(dr.getXirrValue()).doesNotContain("0.00")
                .doesNotContain("__.__%");
        Assertions.assertThat(dr.getEirValue()).doesNotContain("0.00")
                .doesNotContain("__.__%");

    }

    @Test(groups = {"Smoke"})
    public void SDLF_View_Analytics_with_FixEMI_Schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown().clickRepaymentScheduleOptions()
                .selectAddEmiRepaymentSchedule().selectFix("Emi")
                .selectEmiPaymentFrequency("Annually")
                .enterEmiAmount("30000").selectEmiPaymentDay("On 3rd")
                .selectEmiPaymentConvention("NADJ").emiInterestRounding("NONE")
                .emiTDSRounding("NONE").clickOnPreview()
                .clickOnGenerateSchedule().checkUnallocatedPrincipal();
        ld.clickFeetab()
                .clickAddFee().feeType("Processing")
                .amountType("Percentage")
                .enterFeeValue("18")
                .selectUseForXirr()
                .selectUseForEir()
                .enterRemarks(data.get("Notes"))
                .click_Fee_Submit();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        ld.clickAnalyticsTab()
                .checkXirrEirValues();
        Assertions.assertThat(ld.getXirrValue()).doesNotContain("0.00")
                .doesNotContain("__.__%");
        Assertions.assertThat(ld.getEirValue()).doesNotContain("0.00")
                .doesNotContain("__.__%");
    }

    @Test(groups = {"Regression"})
    public void SDLF_ResetDates_Calculations(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        lf.clickOptions().clickAddDrawdown().enterDrawdownExternalID(8)
                .selectPrepaymentsPenalty(data.get("penalty"))
                .enterDrawdownLedgerID(5).enterLoanAcnt("Loan ACNT")
                .selectUseReset().selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPaymentAcnt(data.get("Payment_Account")).clickNewDisbursement()
                .enterDisAmount(data.get("DisAmount"))
                .selectDisbursementType("Standard").clickNewIrSlab()
                .selectIRTypeFloating().selectBenchMark().enterSpread(data.get("Spread"))
                .clickNewTDS().enterTDS(data.get("TDS")).enterAdditionalInfo("NA").clickCreate()
                .clickUseRestOptions().clickAddSchedule()
                .selectFrequency("Annually").selectResetDay("On 2nd")
                .clickOnPreviewButton().clickSubmit().generate_LF_Equated_Principal_Schedule("On 2nd", "Annually", "NADJ")
                .clickRepaymentScheduleOptions()
                .selectAddEquatedInterestSchedule()
                .selectIRFrequency("Annually")
                .IRPaymentDay("On 2nd")
                .selectInterestPaymentConvention("NADJ")
                .selectInterestRounding("NONE").TDSRounding("NONE")
                .clickOnPreview().clickOnGenerateSchedule()
                .checkIRScheduleGenerated();
        SDLFDrawdownPage ld=new SDLFDrawdownPage();
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        int bound = list.size();
        for (int i = 1; i <= bound; i++) {
            if (ld.calculateInterestAmountForResetDates(i) != null) {
                String[] ir=   DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText().split(",");
                Assertions.assertThat((double) ld.calculateInterestAmountForResetDates(i)).isCloseTo(
                        Double.parseDouble(String.join("",ir)), within(0.1)  );
                System.out.println(ld.calculateInterestAmountForResetDates(i)+"---"+String.join("",ir));


            }
        }

    }

    @Test(groups = {"Regression"})
    public void SDLF_UploadPayments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        String name=  RandomStringUtils.random(9,"01234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickSubDebtLF().moveToHamburgerMenu().clickAdd()
                .enterExternalID("LFUploadPayments").enterLedgerID(4)
                .selectLoanFacilityType(data.get("LFType"))
                .enterROC(5).enterSanctionDate(data.get("SanctionDate"))
                .enterEndDate(data.get("EndDate")).enterLFAvailableTill(data.get("EndDate"))
                .selectEntity(data.get("Entity"))
                .selectCounterparty(data.get("Counterparty"))
                .enterFacilityAmount(data.get("LFAmount"))
                .selectArranger(data.get("Arranger"))
                .enterTrustee(data.get("Trustee"))
                .enterAdditionalInfo(data.get("Addinfo")).clickOnCreate();
        SDLFPage ldd=new SDLFPage();
        ldd.clickOptions().clickAddDrawdown().enterDrawdownExternalID("ID123")
                .selectPrepaymentsPenalty(data.get("penalty"))
                .enterDrawdownLedgerID(5).enterLoanAcnt("Loan ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPaymentAcnt(data.get("Payment_Account")).clickNewDisbursement()
                .enterDisAmount(data.get("DisAmount"))
                .selectDisbursementType("Standard").clickNewIrSlab()
                .selectIRType(data.get("IRType")).enterSpread(data.get("Spread"))
                .clickNewTDS().enterTDS(data.get("TDS")).enterAdditionalInfo("NA").clickCreate()
                .generate_LF_Equated_Principal_Schedule("On 2nd","Monthly","NADJ")
                .generate_LF_Equated_Interest_Schedule("On 2nd","Monthly","NADJ")
                .checkIRScheduleGenerated();
        ldd.gotoLoanFacilityBlotter().clickFileOperationsTab().selectFileOperationOptions()
                .selectBulkPaymentsUpload()
                .uploadBulkPayments()
                .enterLiability_uploadPayments_name(name)
                .checkPaymentsUploadIsCompleted(name).searchLoan("LFUploadPayments")
                .selectFirstLoan().selectFirstDrawdown();
        LoanFacilityDrawdownPage le=new LoanFacilityDrawdownPage();
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        IntStream.rangeClosed(1, list.size())
                .forEachOrdered(i -> {
                    Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
                    if(!le.getRepaymentScheduleStatus(i).equals("Not Applicable")) {
                        Assertions.assertThat(le.getRepaymentScheduleStatus(i))
                                .isEqualTo("Fully Paid");
                    }
                });
        le.clickLoanFacilityLink().clickHamburger().clickDeleteIcon();
    }


}
