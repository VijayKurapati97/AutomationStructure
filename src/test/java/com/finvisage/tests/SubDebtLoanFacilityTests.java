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

public class SubDebtLoanFacilityTests extends BaseTest {
    private final ThreadLocal<String[]> userThreadLocal = ThreadLocal.withInitial(() -> null);

    @AfterMethod
    public void Trardown(ITestContext context) {
        String[] user = userThreadLocal.get();
        FrameworkConstants.setUserAvailablity(user);
    }

    private SubDebtLoanFacilityTests() {
    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_Create_Drawdown(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage sl = new SDLFPage();
        sl.create_new_SubDebt_LoanFacility().clickOptions().clickAddDrawdown().enterDrawdownExternalID(10)
                .enterDrawdownValueDate(data.get("ValueDate")).enterDrawdownEndDate(data.get("EndDate"))
                .selectPrepayemntsPenalty(data.get("penalty"))
                .enterDrwadownLedgerID(8).enterLoanAcnt("Loan ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPayementAcnt(data.get("Payment_Account")).clickNewDisbursement()
                .enterDisAmount(data.get("DisAmount"))
                .selectDisbursementType("Standard").clickNewIrSlab()
                .selectIRType(data.get("IRType")).enterSpread(data.get("Spread"))
                .clickNewTDS().enterTDS(data.get("TDS")).enterAdditionalInfo("NA").clickCreate();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Drawdown - LoanFacility-Drawdown-")
                .isNotEmpty()
                .isNotNull();


    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_Create_LoanFacility(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
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

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_Update_LoanFacility(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage sl = new SDLFPage();
        sl.create_new_SubDebt_LoanFacility().clickHamburgur()
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

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_Update_Drawdown(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
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

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_Create_Equated_Principal_Schedules(Map<String, String> data) {

        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        String UnallocatedPrincipal = ld.create_New_SDLFDrawdown().clickPrincipalScheduleOptions()
                .selectAddEquatedPrincipalSchedule()
                .selectPrincipalPayout(data.get("payout")).selectPrincipalPayemntDay(data.get("PaymentDay"))
                .selectPrincipalPaymentConvention(data.get("PaymentConvention")).
                selectPrincipalRounding(data.get("Rounding")).clickOnPreview().clickOnGenerateSchedule()
                .checkUnallocatedPrincipal().getUnallcatedPrincipal();
        Assertions.assertThat(UnallocatedPrincipal).isEqualTo("0.00");

    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_Create_Ad_Hoc_Principal_Schedules(Map<String, String> data) {

        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        String UnallocatedPrincipal = ld.create_New_SDLFDrawdown().selectAd_HocPrincipalSchedule()
                .selectValueDate(data.get("ValueDate")).selectPaymentDate(data.get("PaymentDate"))
                .clickSubmit().getUnallcatedPrincipal();
        Assertions.assertThat(UnallocatedPrincipal).isEqualTo("0.00");

    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_UploadPrincipalSchedule(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
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
        SDLFPage ldd = new SDLFPage();
        String principal1 = ldd.clickOptions().clickAddDrawdown().enterDrawdownExternalID(10)
                .selectPrepayemntsPenalty(data.get("penalty"))
                .enterDrwadownLedgerID(8).enterLoanAcnt("Loan ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPayementAcnt(data.get("Payment_Account")).clickNewDisbursement()
                .enterDisAmount(data.get("DisAmount"))
                .selectDisbursementType("Standard").clickNewIrSlab()
                .selectIRType(data.get("IRType")).enterSpread(data.get("Spread"))
                .clickNewTDS().enterTDS(data.get("TDS")).enterAdditionalInfo("NA").clickCreate()
                .getUnallcatedPrincipal();
        SDLFDrawdownPage dr = new SDLFDrawdownPage();
        String principal2 = dr.clickPrincipalScheduleOptions()
                .clickPrinicpalUploadSchedule().uploadPrincipalSchedule()
                .enterLiability_upload_name()
                .checkUploadisCompleted()
                .clickBeginImport().getUnallcatedPrincipal();
        Assertions.assertThat(principal2).isNotEqualTo(principal1).isEqualTo("0.00");


    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_EditPrincipalPayments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown();
        String valueDate = ld.getDrwnEndDate();
        String UnallocatedAmount = ld.generate_LF_AdHoc_Principal_Schedule(valueDate)
                .edit_Principal_Schedules().getUnallcatedPrincipal();
        Assertions.assertThat(UnallocatedAmount).isNotEqualTo("0.00");
    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_MakePrincipal_payments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown()
                .generate_LF_Equated_Principal_Schedule()
                .checkUnallocatedPrincipal()
                .make_Principal_Payments(data.get("PaymentType"));
        String[] ActualStatus = ld.getPrincipalScheduleStatus();
        IntStream.rangeClosed(0, ActualStatus.length - 1)
                .forEachOrdered(i -> Assertions.assertThat(ActualStatus[i])
                        .isEqualTo("Fully Paid"));

    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_Delete_PrincipalPayments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown();
        String valueDate = ld.getDrwnEndDate();
        ld.generate_LF_AdHoc_Principal_Schedule(valueDate)
                .make_Principal_Payments(data.get("PaymentType"));
        IntStream.rangeClosed(0, ld.getPrincipalScheduleStatus().length - 1)
                .forEachOrdered(i -> Assertions.assertThat(ld.getPrincipalScheduleStatus()[i])
                        .isEqualTo("Fully Paid"));
        ld.delete_Principal_Schedules();
        IntStream.rangeClosed(0, ld.getPrincipalScheduleStatus().length - 1)
                .forEachOrdered(i -> Assertions.assertThat(ld.getPrincipalScheduleStatus()[i])
                        .isEqualTo("Pending"));


    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_Deactivate_Principal_Interest_Schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        String unallcatedPrincipal = ld.create_New_SDLFDrawdown()
                .generate_LF_Equated_Principal_Schedule()
                .generate_LF_Equated_Interest_Schedule()
                .clickInterestScheduleOptions()
                .clickDeactivate2().clickPrincipalScheduleOptions()
                .clickDeactivate1().getUnallcatedPrincipal();
        Assertions.assertThat(unallcatedPrincipal).isNotEqualTo("0.00");
    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_Create_Equated_Interest_Schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown().generate_LF_Equated_Principal_Schedule()
                .checkUnallocatedPrincipal().clickInterestScheduleOptions().selectAddEquatedInterestSchedule()
                .selectIRFrequency(data.get("Frequency")).selectCompoundingFrequency(data.get("ComFrequency"))
                .IRPaymentDay(data.get("PayDay")).selectInterestPaymentConvention(data.get("PaymentConvention"))
                .selectInterestRounding(data.get("IRMode")).TDSRounding(data.get("TDSRounding"))
                .clickOnPreview().clickOnGenerateSchedule().checktotalInterest();
        String totalInterest = ld.getTotalInterest();
        Assertions.assertThat(totalInterest).isNotEqualTo("0.00");

    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_Create_Ad_Hoc_Interest_Schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown()
                .generate_LF_Equated_Principal_Schedule()
                .checkUnallocatedPrincipal().selectAd_HocInterestSchedule()
                .selectValueDate(data.get("ValueDate"))
                .selectPaymentDate(data.get("PaymentDate")).clickSubmit()
                .checktotalInterest();
        String totalInterest = ld.getTotalInterest();
        Assertions.assertThat(totalInterest).isNotEqualTo("0.00");
    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_UploadInterestSchedule(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
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
                .selectArranger(data.get("Arranger"))
                .enterTrustee(data.get("Trustee"))
                .enterAdditionalInfo(data.get("Addinfo")).clickOnCreate();
        SDLFPage ldd = new SDLFPage();
        ldd.clickOptions().clickAddDrawdown().enterDrawdownExternalID(10)
                .selectPrepayemntsPenalty(data.get("penalty"))
                .enterDrwadownLedgerID(8).enterLoanAcnt("Loan ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPayementAcnt(data.get("Payment_Account")).clickNewDisbursement()
                .enterDisAmount(data.get("DisAmount"))
                .selectDisbursementType("Standard").clickNewIrSlab()
                .selectIRType(data.get("IRType")).enterSpread(data.get("Spread"))
                .clickNewTDS().enterTDS(data.get("TDS")).enterAdditionalInfo("NA").clickCreate()
                .generate_LF_Equated_Principal_Schedule()
                .clickInterestScheduleOptions()
                .clickInterestUploadSchedule()
                .uploadInterestSchedule()
                .enterLiability_upload_name()
                .checkUploadisCompleted()
                .clickBeginImport()
                .checktotalInterest();
        SDLFDrawdownPage sld = new SDLFDrawdownPage();
        String totalInterest = sld.getTotalInterest();
        Assertions.assertThat(totalInterest).isNotEqualTo("0.00");


    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_MakeInterest_payments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown()
                .generate_LF_Equated_Principal_Schedule()
                .generate_LF_Equated_Interest_Schedule()
                .checktotalInterest()
                .make_Interest_Payments(data.get("PaymentType"));
        String[] ActualStatus = ld.getInterestScheduleStatus();
        IntStream.rangeClosed(0, ActualStatus.length - 1)
                .forEachOrdered(i -> Assertions.assertThat(ActualStatus[i])
                        .isEqualTo("Fully Paid"));
    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_EditInterestPayments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown();
        String valueDate = ld.getDrwnEndDate();
        String interest1 = ld.generate_LF_AdHoc_Principal_Schedule(valueDate)
                .generate_LF_AdHoc_Interest_Schedule()
                .checktotalInterest()
                .getTotalInterest();
        String interest2 = ld.edit_Interest_Schedules()
                .getTotalInterest();
        Assertions.assertThat(interest1).isNotEqualTo(interest2);
    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_Delete_InterestPayments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown();
        String valueDate = ld.getDrwnEndDate();
        ld.generate_LF_AdHoc_Principal_Schedule(valueDate)
                .generate_LF_AdHoc_Interest_Schedule()
                .checktotalInterest()
                .make_Interest_Payments(data.get("PaymentType"));
        IntStream.rangeClosed(0, ld.getInterestScheduleStatus().length - 1)
                .forEachOrdered(i -> Assertions.assertThat(ld.getInterestScheduleStatus()[i])
                        .isEqualTo("Fully Paid"));
        ld.delete_Interest_Payments();
        IntStream.rangeClosed(0, ld.getInterestScheduleStatus().length - 1)
                .forEachOrdered(i -> Assertions.assertThat(ld.getInterestScheduleStatus()[i])
                        .isEqualTo("Pending"));
    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_Make_Prepayments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        lf.clickOptions().clickAddDrawdown().enterDrawdownExternalID(10)
                .selectPrepayemntsPenalty(data.get("penalty"))
                .enterDrwadownLedgerID(8).enterLoanAcnt("Loan ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPayementAcnt(data.get("Payment_Account")).clickNewDisbursement()
                .enterDisAmount(data.get("DisAmount"))
                .selectDisbursementType("Standard").clickNewIrSlab()
                .selectIRType(data.get("IRType")).enterSpread(data.get("Spread"))
                .clickNewTDS().enterTDS(data.get("TDS")).enterAdditionalInfo("NA")
                .clickCreate();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        double penalty1 = ld.click_PrepayemntOptions().select_MakePrepayemnts()
                .enterPrepaymentPayementDate(data.get("prepaymentPaymentdate"))
                .enterPrepaymentValueDate(data.get("prepaymentValueDate"))
                .enterPrepaymentAmount(data.get("prepaymentAmount"))
                .clickSubmit()
                .getPrepaymentPenalty();
        double penalty2 = Double.parseDouble(data.get("prepaymentAmount")) / Double.parseDouble(data.get("penalty"));
        Assertions.assertThat(penalty1).isEqualTo(penalty2);

    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_Make_Prepayments_Payment_and_Delete(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown().make_prepayments()
                .make_prepayments_Payments();
        String[] ActualStatus = ld.getprepaymentsStatus();
        IntStream.rangeClosed(0, ActualStatus.length - 1)
                .forEachOrdered(i -> Assertions.assertThat(ActualStatus[i])
                        .isEqualTo("Fully Paid"));
        ld.delete_prepayments();
        IntStream.rangeClosed(0, ld.getprepaymentsStatus().length - 1)
                .forEachOrdered(i -> Assertions.assertThat(ld.getprepaymentsStatus()[i])
                        .isEqualTo("Pending"));

    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_Drawdown_AttachedDocuments(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown().clickAttchedDocTab()
                .clickUploadDocuments()/*.enterUploadDate(data.get("UploadDate"))*/
                .uploadAttchedDoc().clickClose();
        int size = ld.clickAttchedDocTab().getAttachedDocSize();
        Assertions.assertThat(size).isNotZero().isGreaterThan(0).isNotNull();
    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_View_Deactivated_Schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        String unallcatedPrincipal = ld.create_New_SDLFDrawdown()
                .generate_LF_Equated_Principal_Schedule()
                .generate_LF_Equated_Interest_Schedule()
                .clickInterestScheduleOptions()
                .clickDeactivate2().clickPrincipalScheduleOptions()
                .clickDeactivate1().getUnallcatedPrincipal();
        Assertions.assertThat(unallcatedPrincipal).isNotEqualTo("0.00");
        boolean value = ld.clickDeactivatedSchedulesTab().checkDeactivatedSchedule();
        Assertions.assertThat(value).isTrue();
    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_Equated_call_schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown();
        ld.clickCallSchedulesTab().clickCallSchedulesOptions()
                .clickEquatedCallSchedule()
                .selectCallFrequency(data.get("CallFrequency"))
                .selectCallExerciseDay(data.get("ExerciseDay"))
                .clickCallSchedulePreview()
                .clickOnGenerateSchedule().clickCallSchedulesTab();
        java.util.List<WebElement> ele = DriverManager.getDriver().findElements(By.xpath("(//tbody)[8]/tr"));
        Assertions.assertThat(ele.size()).isNotEqualTo(0).isGreaterThan(1);
        ele.clear();
        ld.clickCallSchedulesTab().clickdeactivate_callSchedule();
        java.util.List<WebElement> ele1 = DriverManager.getDriver().findElements(By.xpath("(//tbody)[8]/tr"));
        Assertions.assertThat(ele1.size()).isLessThanOrEqualTo(1);

    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_Ad_hoc_call_schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
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
        java.util.List<WebElement> ele = DriverManager.getDriver().findElements(By.xpath("(//tbody)[8]/tr"));
        Assertions.assertThat(ele.size()).isNotEqualTo(0).isEqualTo(2);
        ld.clickCallSchedulesTab().clickdeactivate_callSchedule();
        List<WebElement> ele1 = DriverManager.getDriver().findElements(By.xpath("(//tbody)[8]/tr"));
        Assertions.assertThat(ele1.size()).isLessThanOrEqualTo(1);
    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_Add_and_Cancel_Fee(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown().clickFeetab()
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

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_viewAnalytics_With_EquatedSchedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown().generate_LF_Equated_Principal_Schedule()
                .generate_LF_Equated_Interest_Schedule()
                .clickAnalyticsTab()
                .checkXirrEirValues();
        Assertions.assertThat(ld.getXirrValue()).doesNotContain("0.00")
                .doesNotContain("__.__%");
        Assertions.assertThat(ld.getEirValue()).doesNotContain("0.00")
                .doesNotContain("__.__%");

    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_viewAnalytics_With_AdHocSchedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown();
        String valueDate = ld.getDrwnEndDate();
        ld.generate_LF_AdHoc_Principal_Schedule(valueDate)
                .generate_LF_AdHoc_Interest_Schedule();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        ld.clickAnalyticsTab()
                .checkXirrEirValues();
        Assertions.assertThat(ld.getXirrValue()).doesNotContain("0.00")
                .doesNotContain("__.__%");
        Assertions.assertThat(ld.getEirValue()).doesNotContain("0.00")
                .doesNotContain("__.__%");
    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_viewAnalytics_With_UploadSchedules(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
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
        SDLFPage ldd = new SDLFPage();
        ldd.clickOptions().clickAddDrawdown().enterDrawdownExternalID(10)
                .selectPrepayemntsPenalty(data.get("penalty"))
                .enterDrwadownLedgerID(8).enterLoanAcnt("Loan ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPayementAcnt(data.get("Payment_Account")).clickNewDisbursement()
                .enterDisAmount(data.get("DisAmount"))
                .selectDisbursementType("Standard").clickNewIrSlab()
                .selectIRType(data.get("IRType")).enterSpread(data.get("Spread"))
                .clickNewTDS().enterTDS(data.get("TDS")).enterAdditionalInfo("NA")
                .clickCreate();
        SDLFDrawdownPage dr = new SDLFDrawdownPage();
        String principal2 = dr.clickPrincipalScheduleOptions()
                .clickPrinicpalUploadSchedule().uploadPrincipalSchedule()
                .enterLiability_upload_name()
                .checkUploadisCompleted()
                .clickBeginImport().getUnallcatedPrincipal();
        Assertions.assertThat(principal2).isEqualTo("0.00");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        dr.clickInterestScheduleOptions()
                .clickInterestUploadSchedule()
                .uploadInterestSchedule()
                .enterLiability_upload_name()
                .checkUploadisCompleted()
                .clickBeginImport()
                .checktotalInterest();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        dr.clickAnalyticsTab()
                .checkXirrEirValues();
        Assertions.assertThat(dr.getXirrValue()).isNotEqualTo("0.00")
                .doesNotContain("__.__%");
        Assertions.assertThat(dr.getEirValue()).isNotEqualTo("0.00")
                .doesNotContain("__.__%");
    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_Drawdown_Level_Add_Covenants(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
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
        int size = ld.getCovenatsSize();
        Assertions.assertThat(size).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);

    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_Drawdown_Level_LienFD(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        FixedDepositPage fd = new FixedDepositPage();
        fd.create_FD_with_InterestSchedule();
        SDLFPage lpp = new SDLFPage();
        lpp.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown().clickLienDetails()
                .clickAddToLienFD().clickOnCreate();
        int lienedFDs = ld.clickLienDetails().getLiedFDNum();
        Assertions.assertThat(lienedFDs).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);
        ;

    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_Close_Drawdown(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility();
        SDLFDrawdownPage ld = new SDLFDrawdownPage();
        ld.create_New_SDLFDrawdown();
        String date = ld.getDrwnEndDate();
        ld.generate_LF_AdHoc_Principal_Schedule(date)
                .checkUnallocatedPrincipal()
                .make_Principal_Payments(data.get("PaymentType"));
        String[] ActualStatus = ld.getPrincipalScheduleStatus();
        IntStream.rangeClosed(0, ActualStatus.length - 1)
                .forEachOrdered(i -> Assertions.assertThat(ActualStatus[i])
                        .isEqualTo("Fully Paid"));

        ld.moveToHamburgerMenu().clickCloseDrawdown()
                .enterCloseNotes("Closed")
                .clickSubmitToClose();
        Assertions.assertThat(ld.closureDocumentsTabDisplayed()).isTrue();
    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_Delete_Drawdown(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
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


    @Test(groups = {"smoke", "Regression"})
    public void SDLF_level_AttachedDocuments(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        lf.create_new_SubDebt_LoanFacility().clickAttchedDocTab()
                .clickUploadDocuments()/*.enterUploadDate(data.get("UploadDate"))*/
                .uploadAttchedDoc().clickClosebtn();
        int size = lf.getAttachedDocSize();
        Assertions.assertThat(size).isNotZero().isGreaterThan(0).isNotNull();
    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_Level_Add_Covenants(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
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
        int size = lpp.getCovenatsSize();
        Assertions.assertThat(size).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);
        ;
    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_Level_LienFD(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        FixedDepositPage fd = new FixedDepositPage();
        fd.create_FD_with_InterestSchedule();
        SDLFPage lpp = new SDLFPage();
        lpp.create_new_SubDebt_LoanFacility().clickLienDetails()
                .clickAddToLienFD().clickOnCreate();
        int lienedFDs = lpp.clickLienDetails().getLiedFDNum();
        Assertions.assertThat(lienedFDs).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);


    }

    @Test(groups = {"smoke", "Regression"})
    public void SDLF_Create_Close(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf = new SDLFPage();
        String LfExternalID = lf.create_new_SubDebt_LoanFacility().getLfExrnlID();
        lf.clickHamburgur().clickClose().enterCloseNotes(data.get("Notes"))
                .clickSubmitToClose();
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLoanFacility();
        String expectedExtId = lf.gotLoanFacilityBlotter()
                .clickClosedTab().searchExtId(LfExternalID).getfirstLoan();
        Assertions.assertThat(expectedExtId).isEqualTo(LfExternalID);


    }
    @Test(groups = {"smoke", "Regression"})
    public void SDLF_Create_Delete(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SDLFPage lf=new SDLFPage();
        String LfExternalID = lf.create_new_SubDebt_LoanFacility().getLfExrnlID();
        String expectedExtId = lf.clickHamburgur().clickDeleteIcon().clickArchivedTab()
                .searchExtId(LfExternalID).getfirstLoan();
        Assertions.assertThat(expectedExtId).isEqualTo(LfExternalID);

    }

}
