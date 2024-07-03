package com.finvisage.tests;

import com.finvisage.constants.FrameworkConstants;
import com.finvisage.drivers.DriverManager;
import com.finvisage.liabilityPages.*;
import com.finvisage.reports.ExtentManager;
import com.google.common.util.concurrent.Uninterruptibles;
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

public class LoanFacilityTests extends BaseTest {
    private final ThreadLocal<String[]> userThreadLocal = ThreadLocal.withInitial(() -> null);

    @AfterMethod
    public void Teardown(ITestContext context) {
        String[] user = userThreadLocal.get();
        FrameworkConstants.setUserAvailablity(user);
    }

    private LoanFacilityTests() {
    }

    @Test(groups = {"Smoke"})
    public void LoanFacility_Create_Drawdown(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility().clickOptions().clickAddDrawdown().enterDrawdownExternalID(10)
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
    public void LoanFacility_Create_Delete(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        String LfExternalID = lf.create_new_LoanFacility().getLfExrnlID();
        String expectedExtId = lf.clickHamburger().clickDeleteIcon().clickArchivedTab()
                .searchExtId(LfExternalID).getFirstLoan();
        Assertions.assertThat(expectedExtId).isEqualTo(LfExternalID);

    }

    @Test(groups = {"Smoke"})
    public void LoanFacility_Create_Close(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        String LfExternalID = lf.create_new_LoanFacility().getLfExrnlID();
        lf.clickHamburger().clickClose().enterCloseNotes(data.get("Notes"))
                .clickSubmitToClose();
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLoanFacility();
        String expectedExtId = lf.gotoLoanFacilityBlotter()
                .clickClosedTab().searchExtId(LfExternalID).getFirstLoan();
        Assertions.assertThat(expectedExtId).isEqualTo(LfExternalID);
    }

    @Test(groups = {"Smoke"})
    public void LoanFacility_Create_Update(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility().clickHamburger()
                .clickEdit().clickContinue();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Loan Facilities - Edit");
        NewLoanFacilityPage nl = new NewLoanFacilityPage();
        nl.enterEndDate(data.get("EndDate"))
                .clickOnUpdate();
        Uninterruptibles.sleepUninterruptibly(34, TimeUnit.SECONDS);
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Loan Facility - LF");

    }

    @Test(groups = {"Smoke"})
    public void LoanFacility_Create_LoanFacility(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickLoanFacility().moveToHamburgerMenu().clickAdd()
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
    public void LoanFacility_Create_Equated_Principal_Schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        String UnallocatedPrincipal = ld.create_New_Drawdown().clickRepaymentScheduleOptions()
                .selectAddEquatedPrincipalSchedule()
                .selectPrincipalPayout(data.get("payout")).selectPrincipalPaymentDay(data.get("PaymentDay"))
                .selectPrincipalPaymentConvention(data.get("PaymentConvention")).
                selectPrincipalRounding(data.get("Rounding")).clickOnPreview().clickOnGenerateSchedule()
                .checkUnallocatedPrincipal().getUnallocatedPrincipal();
        Assertions.assertThat(UnallocatedPrincipal).isEqualTo("0.00");
    }

    @Test(groups = {"Smoke"})
    public void LoanFacility_Create_Ad_Hoc_Principal_Schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        String UnallocatedPrincipal = ld.create_New_Drawdown().selectAd_HocPrincipalSchedule()
                .selectValueDate(data.get("ValueDate")).selectPaymentDate(data.get("PaymentDate"))
                .clickSubmit().getUnallocatedPrincipal();
        Assertions.assertThat(UnallocatedPrincipal).isEqualTo("0.00");
    }

    @Test(groups = {"Smoke"})
    public void LoanFacility_Create_Equated_Interest_Schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown().generate_LF_Equated_Principal_Schedule("On 2nd")
                .checkUnallocatedPrincipal().clickRepaymentScheduleOptions().selectAddEquatedInterestSchedule()
                .selectIRFrequency(data.get("Frequency")).selectCompoundingFrequency(data.get("ComFrequency"))
                .IRPaymentDay(data.get("PayDay")).selectInterestPaymentConvention(data.get("PaymentConvention"))
                .enterCompoundPaymentDay(data.get("compoundDay"))
                .selectInterestRounding(data.get("IRMode")).TDSRounding(data.get("TDSRounding"))
                .clickOnPreview().clickOnGenerateSchedule().checkIRScheduleGenerated();
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
    public void LoanFacility_Create_Ad_Hoc_Interest_Schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown()
                .generate_LF_Equated_Principal_Schedule("On 2nd")
                .checkUnallocatedPrincipal().selectAd_HocInterestSchedule()
                .selectValueDate(data.get("ValueDate"))
                .selectPaymentDate(data.get("PaymentDate")).clickSubmit();
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
    public void LoanFacility_Deactivate_Repayment_Schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        String unallocatedPrincipal = ld.create_New_Drawdown()
                .generate_LF_Equated_Principal_Schedule("On 2nd")
                .generate_LF_Equated_Interest_Schedule("On 3rd")
                .clickRepaymentScheduleOptions()
                .clickDeactivateSchedule()
                .getUnallocatedPrincipal();
        Assertions.assertThat(unallocatedPrincipal).isNotEqualTo("0.00");
    }

    @Test(groups = {"Smoke"})
    public void LoanFacility_MakePrincipal_payments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown()
                .generate_LF_Equated_Principal_Schedule("On 2nd");
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        IntStream.rangeClosed(1, list.size())
                .forEachOrdered(i -> {
                    ld.clickMakePayments(i)
                            .selectOperatingAccount()
                            .selectPaymentAccount(data.get("payAcnt"))
                            .enterNotes("Payment")
                            .selectPaymentType(data.get("PaymentType"))
                            .clickSubmit();
                    Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
                    Assertions.assertThat(ld.getRepaymentScheduleStatus(i))
                            .isEqualTo("Fully Paid");
                });
    }

    @Test(groups = {"Smoke"})
    public void LoanFacility_MakeInterest_payments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown()
                .generate_LF_Equated_Principal_Schedule("On 2nd")
                .generate_LF_Equated_Interest_Schedule("On 3rd");
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        IntStream.rangeClosed(1, list.size())
                .forEachOrdered(i -> {
                    if (!DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText().equals("0.0")) {
                        ld.clickMakePayments(i)
                                .selectOperatingAccount()
                                .selectPaymentAccount(data.get("payAcnt"))
                                .enterNotes("Payment")
                                .selectPaymentType(data.get("PaymentType"))
                                .clickSubmit();
                        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
                        Assertions.assertThat(ld.getRepaymentScheduleStatus(i))
                                .isEqualTo("Fully Paid");
                    }
                });
    }

    @Test(groups = {"Smoke"})
    public void LoanFacility_EditPrincipalPayments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown();
        ld.generate_LF_Equated_Principal_Schedule("On 2nd");
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        IntStream.rangeClosed(1, list.size())
                .forEachOrdered(i -> {
                    String unallocated = ld.getUnallocatedPrincipal();
                    ld.clickEditRepayment(i).clickEditPrincipalSchedule().editNetAmount(200)
                            .editRepaymentScheduleDates(3, 5)
                            .clickSubmit();
                    Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
                    Assertions.assertThat(ld.getUnallocatedPrincipal())
                            .isNotEqualTo(unallocated);


                });
    }

    @Test(groups = {"Smoke"})
    public void LoanFacility_EditInterestPayments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown();
        ld.generate_LF_Equated_Principal_Schedule("On 2nd")
                .generate_LF_Equated_Interest_Schedule("On 3rd");
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        IntStream.rangeClosed(1, list.size())
                .forEachOrdered(i -> {
                    if (!DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText().equals("0.0")) {
                        String netAmount1 = ld.getInterestNetAmount(i);
                        ld.clickEditRepayment(i).clickEditInterestSchedule()
                                .editRepaymentScheduleDates(3, 5)
                                .clickSubmit();
                        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
                        String netAmount2 = ld.getInterestNetAmount(i);
                        Assertions.assertThat(netAmount1)
                                .isNotEqualTo(netAmount2);

                    }
                });

    }

    @Test(groups = {"Smoke"})
    public void LoanFacility_Delete_PrincipalPayments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown();
        ld.generate_LF_Equated_Principal_Schedule("On 2nd");
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        IntStream.rangeClosed(1, list.size())
                .forEachOrdered(i -> {
                    ld.clickMakePayments(i)
                            .selectOperatingAccount()
                            .selectPaymentAccount(data.get("payAcnt"))
                            .enterNotes("Payment")
                            .selectPaymentType(data.get("PaymentType"))
                            .clickSubmit();
                    Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
                    Assertions.assertThat(ld.getRepaymentScheduleStatus(i))
                            .isEqualTo("Fully Paid");
                    ld.clickViewPayments(i)
                            .clickViewPrincipalSchedule()
                            .clickDeletePayment();
                    Assertions.assertThat(ld.getRepaymentScheduleStatus(i))
                            .isEqualTo("Pending");
                    Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);

                });


    }

    @Test(groups = {"Smoke"})
    public void LoanFacility_Delete_InterestPayments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown();
        ld.generate_LF_Equated_Principal_Schedule("On 2nd")
                .generate_LF_Equated_Interest_Schedule("On 3rd");
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        IntStream.rangeClosed(1, list.size())
                .forEachOrdered(i -> {
                    if (!DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText().equals("0.0")) {
                        ld.clickMakePayments(i)
                                .selectOperatingAccount()
                                .selectPaymentAccount(data.get("payAcnt"))
                                .enterNotes("Payment")
                                .selectPaymentType(data.get("PaymentType"))
                                .clickSubmit();
                        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
                        Assertions.assertThat(ld.getRepaymentScheduleStatus(i))
                                .isEqualTo("Fully Paid");
                        ld.clickViewPayments(i)
                                .clickViewInterestSchedule()
                                .clickDeletePayment();
                        Assertions.assertThat(ld.getRepaymentScheduleStatus(i))
                                .isEqualTo("Pending");
                        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
                    }
                });
    }

    @Test(groups = {"Smoke"})
    public void LoanFacility_UploadPrincipalSchedule(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickLoanFacility().moveToHamburgerMenu().clickAdd()
                .enterExternalID(10).enterLedgerID(4)
                .selectLoanFacilityType(data.get("LFType"))
                .enterROC(5).enterSanctionDate(data.get("SanctionDate"))
                .enterEndDate(data.get("EndDate")).enterLFAvailableTill(data.get("EndDate"))
                .selectEntity(data.get("Entity"))
                .selectCounterparty(data.get("Counterparty"))
                .enterFacilityAmount(data.get("LFAmount"))
                .selectArranger(data.get("Arranger")).enterTrustee(data.get("Trustee"))
                .enterAdditionalInfo(data.get("Addinfo")).clickOnCreate();
        LoanFacilityPage ldd = new LoanFacilityPage();
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
        LoanFacilityDrawdownPage dr = new LoanFacilityDrawdownPage();
        String principal2 = dr.clickRepaymentScheduleOptions()
                .clickUploadSchedule().uploadPrincipalSchedule()
                .enterLiability_upload_name()
                .checkUploadIsCompleted()
                .clickBeginImport().getUnallocatedPrincipal();
        Assertions.assertThat(principal2).isNotEqualTo(principal1).isEqualTo("0.00");
    }

    @Test(groups = {"Smoke"})
    public void LoanFacility_UploadInterestSchedule(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickLoanFacility().moveToHamburgerMenu().clickAdd()
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
        LoanFacilityPage ldd = new LoanFacilityPage();
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
                .clickUploadSchedule()
                .uploadInterestSchedule()
                .enterLiability_upload_name()
                .checkUploadIsCompleted()
                .clickBeginImport()
                .checkIRScheduleGenerated();
        LoanFacilityDrawdownPage dr = new LoanFacilityDrawdownPage();
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        IntStream.rangeClosed(1, list.size())
                .forEachOrdered(i -> {
                    if (!DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText().equals("0.0")) {
                        Assertions.assertThat(dr.getRepaymentScheduleStatus(i))
                                .isEqualTo("Pending");
                    }
                });
    }

    @Test(groups = {"Smoke"})
    public void LoanFacility_viewAnalytics_With_EquatedSchedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown().generate_LF_Equated_Principal_Schedule("On 2nd")
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
    public void LoanFacility_viewAnalytics_With_AdHocSchedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown();
        String valueDate = ld.getEndDate();
        ld.generate_LF_AdHoc_Principal_Schedule(valueDate)
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
    public void LoanFacility_viewAnalytics_With_UploadSchedules(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickLoanFacility().moveToHamburgerMenu().clickAdd()
                .enterExternalID(10).enterLedgerID(4)
                .selectLoanFacilityType(data.get("LFType"))
                .enterROC(5).enterSanctionDate(data.get("SanctionDate"))
                .enterEndDate(data.get("EndDate")).selectEntity(data.get("Entity"))
                .selectCounterparty(data.get("Counterparty"))
                .enterFacilityAmount(data.get("LFAmount"))
                .selectArranger(data.get("Arranger")).enterTrustee(data.get("Trustee"))
                .enterAdditionalInfo(data.get("Addinfo")).clickOnCreate();
        LoanFacilityPage ldd = new LoanFacilityPage();
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
        LoanFacilityDrawdownPage dr = new LoanFacilityDrawdownPage();
        dr.clickRepaymentScheduleOptions()
                .clickUploadSchedule().uploadPrincipalSchedule()
                .enterLiability_upload_name()
                .checkUploadIsCompleted()
                .clickBeginImport();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        dr.clickRepaymentScheduleOptions()
                .clickUploadSchedule()
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
                .clickSubmit();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        dr.clickAnalyticsTab()
                .checkXirrEirValues();
        Assertions.assertThat(dr.getXirrValue()).isNotEqualTo("0.00")
                .doesNotContain("__.__%");
        Assertions.assertThat(dr.getEirValue()).isNotEqualTo("0.00")
                .doesNotContain("__.__%");
    }

    @Test(groups = {"Smoke"})
    public void LoanFacility_Equated_call_schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown();
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
    public void LoanFacility_Ad_hoc_call_schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown();
        ld.clickCallSchedulesTab();
        ld.clickCallSchedulesOptions()
                .clickAdHocCallSchedule()
                .enterCallDate(data.get("date1"))
                .clickSubmit().clickCallSchedulesTab()
                .clickCallSchedulesOptions()
                .clickAdHocCallSchedule()
                .enterCallDate(data.get("date2"))
                .clickSubmit().clickCallSchedulesTab();
        List<WebElement> ele = DriverManager.getDriver().findElements(By.xpath("(//tbody)[7]/tr"));
        Assertions.assertThat(ele.size()).isNotEqualTo(0).isEqualTo(2);
        ld.clickCallSchedulesTab().clickDeactivate_callSchedule();
        List<WebElement> ele1 = DriverManager.getDriver().findElements(By.xpath("(//tbody)[7]/tr"));
        Assertions.assertThat(ele1.size()).isLessThanOrEqualTo(1);
    }

    @Test(groups = {"Smoke"})
    public void LoanFacility_Make_Prepayments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
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
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        double penalty1 = ld.click_PrepaymentOptions().select_MakePrepayments()
                .enterPrepaymentPaymentDate(data.get("prepaymentPaymentdate"))
                .enterPrepaymentValueDate(data.get("prepaymentValueDate"))
                .enterPrepaymentAmount(data.get("prepaymentAmount"))
                .clickSubmit()
                .getPrepaymentPenalty();
        double penalty2 = Double.parseDouble(data.get("prepaymentAmount")) / Double.parseDouble(data.get("penalty"));
        Assertions.assertThat(penalty1).isEqualTo(penalty2);

    }

    @Test(groups = {"Smoke"})
    public void LoanFacility_Make_Prepayments_Payment_and_Delete(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown().make_prepayments()
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
    public void LoanFacility_Add_and_Cancel_Fee(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown().clickFeetab()
                .clickAddFee().feeType(data.get("FeeType"))
                .amountType(data.get("AmountType"))
                .enterFeeValue(data.get("FeeValue"))
                .enterRemarks("NA").clickSubmit();
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        Assertions.assertThat(ld.getFeeStatus("2")).isEqualTo("Live");
        ld.cancelFee();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        ld.clickFeetab();
        Assertions.assertThat(ld.getFeeStatus("1")).isEqualTo("No data to show");

    }

    @Test(groups = {"Smoke"})
    public void LoanFacility_Drawdown_AttachedDocuments(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown().clickAttachedDocTab()
                .clickUploadDocuments()/*.enterUploadDate(data.get("UploadDate"))*/
                .uploadAttachedDoc().clickClose();
        int size = ld.clickAttachedDocTab().getAttachedDocSize();
        Assertions.assertThat(size).isNotZero().isGreaterThan(0).isNotNull();
    }

    @Test(groups = {"Smoke"})
    public void LoanFacility_level_AttachedDocuments(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility().clickAttachedDocumentsTab()
                .clickUploadDocuments()/*.enterUploadDate(data.get("UploadDate"))*/
                .uploadAttachedDoc().clickClosebtn();
        int size = lf.getAttachedDocSize();
        Assertions.assertThat(size).isNotZero().isGreaterThan(0).isNotNull();
    }

    @Test(groups = {"Smoke"})
    public void LoanFacility_Level_LienFD(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        FixedDepositPage fd = new FixedDepositPage();
        fd.create_FD_with_InterestSchedule();
        LoanFacilityPage lpp = new LoanFacilityPage();
        lpp.create_new_LoanFacility().clickLienDetails()
                .clickAddToLienFD().clickOnCreate();
        int lienedFDs = lpp.clickLienDetails().getLiedFDNum();
        Assertions.assertThat(lienedFDs).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);

    }

    @Test(groups = {"Smoke"})
    public void LoanFacility_Drawdown_Level_LienFD(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        FixedDepositPage fd = new FixedDepositPage();
        fd.create_FD_with_InterestSchedule();
        LoanFacilityPage lpp = new LoanFacilityPage();
        lpp.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown().clickLienDetails()
                .clickAddToLienFD().clickOnCreate();
        int lienedFDs = ld.clickLienDetails().getLiedFDNum();
        Assertions.assertThat(lienedFDs).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);

    }

    @Test(groups = {"Smoke"})
    public void LoanFacility_Drawdown_Level_Add_Covenants(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lpp = new LoanFacilityPage();
        lpp.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown().clickCovenantsTab()
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
    public void LoanFacility_Level_Add_Covenants(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lpp = new LoanFacilityPage();
        lpp.create_new_LoanFacility().clickCovenantsTab().clickAddCovenants()
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
    public void LoanFacility_Level_SaveAsDraft(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickLoanFacility().moveToHamburgerMenu().clickAdd();
        NewLoanFacilityPage lf = new NewLoanFacilityPage();
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
    public void LoanFacility_Drawdown_SaveAsDraft(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility().clickOptions().clickAddDrawdown()
                .enterDrawdownExternalID(6)
                .selectPrepaymentsPenalty(data.get("penalty"))
                .enterDrawdownLedgerID(8).enterLoanAcnt("Loan ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPaymentAcnt(data.get("Payment_Account")).clickNewDisbursement()
                .enterDisAmount(data.get("DisAmount"))
                .selectDisbursementType("Standard")
                .clickSaveAsDraft().clickCancel().clickDraftedDrawdownEditOption()
                .clickNewIrSlab()
                .selectIRType(data.get("IRType")).enterSpread(data.get("Spread"))
                .clickNewTDS().enterTDS(data.get("TDS")).enterAdditionalInfo("NA").clickCreate();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Drawdown - LoanFacility-Drawdown-")
                .isNotEmpty()
                .isNotNull();

    }

    @Test(groups = {"Smoke"})
    public void LoanFacility_Level_DeleteDraftedRecord(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickLoanFacility().moveToHamburgerMenu().clickAdd();
        NewLoanFacilityPage lf = new NewLoanFacilityPage();
        String extId = lf.generateExternalID();
        String value = lf.enterExternalID(extId).clickSaveAsDraft()
                .clickDraftsTab().searchExtId(extId).clickDraftedLoanDelete()
                .clickDraftsTab().searchExtId(extId).getFirstLoan();
        Assertions.assertThat(value).isEqualTo("No matching records found");
    }

    @Test(groups = {"Smoke"})
    public void LoanFacility_Drawdown_DeleteDraftedRecord(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility().clickOptions().clickAddDrawdown()
                .enterDrawdownExternalID(6).enterDrawdownLedgerID(3)
                .clickSaveAsDraft().clickCancel().clickDraftedDrawdownDeleteOption();
        Assertions.assertThat(DriverManager.getDriver().findElement
                (By.xpath("//p[text()='Drafted Drawdowns']")).isDisplayed()).isFalse();


    }

    @Test(groups = {"Regression"})
    public void LoanFacility_Equated_InterestCalculations(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown().generate_LF_Equated_Principal_Schedule("On 2nd")
                .generate_LF_Equated_Interest_Schedule("On 2nd");
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        IntStream.rangeClosed(1, list.size()).filter(i -> ld.calculateInterestAmount(i) != null).forEach(i ->
                Assertions.assertThat((double) ld.calculateInterestAmount(i)).isCloseTo(
                        Double.parseDouble(DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText()), within(0.1)
                ));

    }

    @Test(groups = {"Regression"})
    public void LoanFacility_Equated_UpdateInterestDates_Calculation(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        String endDate1 = ld.create_New_Drawdown().getEndDate();
        ld.generate_LF_Equated_Principal_Schedule("On 5th");
        ld.generate_LF_Equated_Interest_Schedule("On 2nd");
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        for (int i = 1; i < list.size(); i++) {
            if (!DriverManager.getDriver().findElement(By.xpath(
                    "(//tbody)[6]/tr[" + i + "]/td[7]")).getText().equals("0.0")) {
                if (!DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[3]")).getText().equals(endDate1)) {
                    ld.clickEditRepayment(i).clickEditInterestSchedule()
                            .editRepaymentScheduleDates(3, 3)
                            .clickSubmit();
                    Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
                    continue;
                }
                break;
            }
        }
        for (int i = 1; i <= list.size(); i++) {
            if (ld.calculateInterestAmount(i) != null) {
                if (!DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[3]")).getText().equals(endDate1)) {
                    Assertions.assertThat((double) ld.calculateInterestAmount(i)).isCloseTo(
                            Double.parseDouble(DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]"))
                                    .getText()), within(0.1));
                    continue;
                }
                break;
            }
        }
    }

    @Test(groups = {"Regression"})
    public void LoanFacility_Equated_InterestCalculationsForSucceeding(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown().generate_LF_Equated_Principal_Schedule("On 2nd")
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
        IntStream.rangeClosed(1, bound).filter(i -> ld.calculateInterestAmountForSucceeding(i) != null).forEach(i -> Assertions.assertThat((double) ld.calculateInterestAmountForSucceeding(i)).isCloseTo(
                Double.parseDouble(DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText()), within(0.1)
        ));
    }

    @Test(groups = {"Regression"})
    public void LoanFacility_UseFirstDay_Calculation(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown().generate_LF_Equated_Principal_Schedule("On 2nd","Quarterly","NADJ")
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
        IntStream.rangeClosed(1, bound).filter(i -> ld.calculateInterestAmountForFirstDayCalculation(i) != null).forEach(i -> Assertions.assertThat((double) ld.calculateInterestAmountForFirstDayCalculation(i)).isCloseTo(
                Double.parseDouble(DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText()), within(0.1)
        ));
    }

    @Test(groups = {"Regression"})
    public void LoanFacility_UseLastDay_Calculation(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown().generate_LF_Equated_Principal_Schedule("On 2nd","Quarterly","NADJ")
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
        IntStream.rangeClosed(1, bound).filter(i -> ld.calculateInterestAmountForLastDayCalculation(i) != null).forEach(i -> Assertions.assertThat((double) ld.calculateInterestAmountForLastDayCalculation(i)).isCloseTo(
                Double.parseDouble(DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText()), within(0.1)
        ));
    }

    @Test(groups = {"Regression"})
    public void LoanFacility_InterestCalculation_with_ConstantDays(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown().generate_LF_Equated_Principal_Schedule("On 2nd","Quarterly","NADJ")
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
        IntStream.rangeClosed(1, bound).filter(i -> ld.calculateInterestAmountForConstantDays(i, Integer.parseInt(data.get("numberOfDays"))) != null).forEach(i -> Assertions.assertThat((double) ld.calculateInterestAmountForConstantDays(i, Integer.parseInt(data.get("numberOfDays")))).isCloseTo(
                Double.parseDouble(DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText()), within(0.1)
        ));
    }

    @Test(groups = {"Regression"})
    public void LoanFacility_InterestCalculation_with_ActualDaysAsPerFinYear(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown().generate_LF_Equated_Principal_Schedule("On 2nd","Quarterly","NADJ")
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
        IntStream.rangeClosed(1, bound).filter(i -> ld.calculateInterestAmountForActual_days_as_per_financial_year(i) != null).forEach(i -> Assertions.assertThat((double) ld.calculateInterestAmountForActual_days_as_per_financial_year(i)).isCloseTo(
                Double.parseDouble(DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText()), within(0.1)
        ));
    }

    @Test(groups = {"Regression"})
    public void LoanFacility_InterestCalculation_with_ThirtyBy360(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown().generate_LF_Equated_Principal_Schedule("On 2nd","Quarterly","NADJ")
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
                Assertions.assertThat((double) ld.calculateInterestAmountForThirtyBy360(i)).isCloseTo(
                        Double.parseDouble(DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]")).getText()), within(0.1)
                );
            }
        }
    }

    @Test(groups = {"Regression"})
    public void LoanFacility_InterestCalculation_On_ClosingBalance(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown().generate_LF_Equated_Principal_Schedule("On 2nd","Semi Annually","NADJ")
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
                Assertions.assertThat((double) ld.calculateInterestAmountForClosingBalance(i)).isCloseTo(
                        Double.parseDouble(DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]"))
                                .getText()), within(0.1)
                );
            }
        }
    }

    @Test(groups = {"Regression"})
    public void LoanFacility_AdHoc_InterestCalculations(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        String endDate= ld.create_New_Drawdown().getEndDate();
        ld.generate_LF_AdHoc_Principal_Schedule(endDate)
                .generate_LF_AdHoc_Interest_Schedule(data.get("valueDate1"),data.get("paymentDate1"))
                .generate_LF_AdHoc_Interest_Schedule(data.get("valueDate2"),data.get("paymentDate2"))
                .generate_LF_AdHoc_Interest_Schedule(data.get("valueDate3"),data.get("paymentDate3"));
        List<WebElement> list = DriverManager.getDriver().findElements(By.xpath("(//tbody)[6]/tr"));
        int bound = list.size();
        for (int i = 1; i <= bound-1; i++) {
            if (ld.calculateInterestAmount(i) != null) {
                Assertions.assertThat((double) ld.calculateInterestAmount(i)).isCloseTo(
                        Double.parseDouble(DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]"))
                                .getText()), within(0.1)
                );
            }
        }
    }

    @Test(groups = {"Regression"})
    public void LoanFacility_Adhoc_UpdateInterestDates_Calculation(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        String endDate = ld.create_New_Drawdown().getEndDate();
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
                            .clickSubmit();
                    Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
                    continue;
                }
                break;
            }
        }
        ld.checkIRScheduleGenerated();
        for (int i = 1; i <= bound - 1; i++) {
            if (ld.calculateInterestAmount(i) != null) {
                Assertions.assertThat((double) ld.calculateInterestAmount(i)).isCloseTo(
                        Double.parseDouble(DriverManager.getDriver().findElement(By.xpath("(//tbody)[6]/tr[" + i + "]/td[7]"))
                                .getText()), within(0.1)
                );
            }
        }
    }
}
