package com.finvisage.tests;

import com.finvisage.drivers.DriverManager;
import com.finvisage.liabilityPages.LiabilityDashboardsPage;
import com.finvisage.liabilityPages.LiabiltyLogInPage;
import com.finvisage.liabilityPages.LoanFacilityDrawdownPage;
import com.finvisage.liabilityPages.LoanFacilityPage;
import com.finvisage.reports.ExtentManager;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.stream.IntStream;

public class LoanFacilityTests extends BaseTest {
    private LoanFacilityTests() {
    }

    @Test(groups = {"smoke", "Regression"})
    public void LoanFacility_Create_Drawdown(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility().clickOptions().clickAddDrawdown().enterDrawdownExternalID(10)
                .selectPrepayemnts(data.get("PrepaymentType"), data.get("penalty"))
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
    public void LoanFacility_Create_Delete(Map<String,String> data) throws InterruptedException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LoanFacilityPage lf = new LoanFacilityPage();
        String LfExternalID = lf.create_new_LoanFacility().getLfExrnlID();
        String expectedExtId = lf.clickHamburgur().clickDeleteIcon().clickArchivedTab()
                .searchExtId(LfExternalID).getfirstLoan();
        Assertions.assertThat(expectedExtId).isEqualTo(LfExternalID);

    }

    @Test(groups = {"smoke", "Regression"})
    public void LoanFacility_Create_Close(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LoanFacilityPage lf = new LoanFacilityPage();
        String LfExternalID = lf.create_new_LoanFacility().getLfExrnlID();
        lf.clickHamburgur().clickClose().enterCloseNotes(data.get("Notes"))
                .clickSubmitToClose();
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLoanFacility();
        String expectedExtId = lf.gotoLoanFacilityBlotter()
                .clickClosedTab().searchExtId(LfExternalID).getfirstLoan();
        Assertions.assertThat(expectedExtId).isEqualTo(LfExternalID);
    }

    @Test(groups = {"smoke", "Regression"})
    public void LoanFacility_Create_LoanFacility(Map<String, String> data) {
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        lp.LogIn().clickLiability().clickLoanFacility().moveToHamburgerMenu().clickAdd()
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
    public void LoanFacility_Create_Equated_Principal_Schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        String UnallocatedPrincipal = ld.create_New_Drawdown().clickPrincipalScheduleOptions()
                .selectAddEquatedPrincipalSchedule()
                .selectPrincipalPayout(data.get("payout")).selectPrincipalPayemntDay(data.get("PaymentDay"))
                .selectPrincipalPaymentConvention(data.get("PaymentConvention")).
                selectPrincipalRounding(data.get("Rounding")).clickOnPreview().clickOnGenerateSchedule()
                .checkUnallocatedPrincipal().getUnallcatedPrincipal();
        String[] ActualStatus = ld.getPrincipalScheduleStatus();
        Assertions.assertThat(UnallocatedPrincipal).isEqualTo("0.00");
        IntStream.rangeClosed(0, ActualStatus.length - 1)
                .forEachOrdered(i -> Assertions.assertThat(ActualStatus[i])
                        .isEqualTo("Pending"));
    }

    @Test(groups = {"smoke", "Regression"})
    public void LoanFacility_Create_Ad_Hoc_Principal_Schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        String UnallocatedPrincipal = ld.create_New_Drawdown().selectAd_HocPrincipalSchedule()
                .selectValueDate(data.get("ValueDate")).selectPaymentDate(data.get("PaymentDate"))
                .clickSubmit().getUnallcatedPrincipal();
        String[] ActualStatus = ld.getPrincipalScheduleStatus();
        Assertions.assertThat(UnallocatedPrincipal).isEqualTo("0.00");
        IntStream.rangeClosed(0, ActualStatus.length - 1)
                .forEachOrdered(i -> Assertions.assertThat(ActualStatus[i])
                        .isEqualTo("Pending"));
    }

    @Test(groups = {"smoke", "Regression"})
    public void LoanFacility_Create_Equated_Interest_Schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown().generate_LF_Equated_Principal_Schedule()
                .checkUnallocatedPrincipal().clickInterestScheduleOptions().selectAddEquatedInterestSchedule()
                .selectIRFrequency(data.get("Frequency")).selectCompoundingFrequency(data.get("ComFrequency"))
                .IRPaymentDay(data.get("PayDay")).selectInterestPaymentConvention(data.get("PaymentConvention"))
                .selectInterestRounding(data.get("IRMode")).TDSRounding(data.get("TDSRounding"))
                .clickOnPreview().clickOnGenerateSchedule().checkIRscheduleGenerated();
        String[] ActualStatus = ld.getInterestScheduleStatus();
        IntStream.rangeClosed(0, ActualStatus.length - 1)
                .forEachOrdered(i -> Assertions.assertThat(ActualStatus[i])
                        .isEqualTo("Pending"));

    }

    @Test(groups = {"smoke", "Regression"})
    public void LoanFacility_Create_Ad_Hoc_Interest_Schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown()
                .generate_LF_Equated_Principal_Schedule()
                .checkUnallocatedPrincipal().selectAd_HocInterestSchedule()
                .selectValueDate(data.get("ValueDate"))
                .selectPaymentDate(data.get("PaymentDate")).clickSubmit()
                .checkIRscheduleGenerated();
        String[] ActualStatus = ld.getInterestScheduleStatus();
        IntStream.rangeClosed(0, ActualStatus.length - 1)
                .forEachOrdered(i -> Assertions.assertThat(ActualStatus[i])
                        .isEqualTo("Pending"));
    }

    @Test(groups = {"smoke", "Regression"})
    public void LoanFacility_Deactivate_Principal_Interest_Schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        String unallcatedPrincipal = ld.create_New_Drawdown()
                .generate_LF_Equated_Principal_Schedule()
                .generate_LF_Equated_Interest_Schedule()
                .checkIRscheduleGenerated().clickInterestScheduleOptions()
                .clickDeactivate2().clickPrincipalScheduleOptions()
                .clickDeactivate1().getUnallcatedPrincipal();
        Assertions.assertThat(unallcatedPrincipal).isNotEqualTo("0.00");
    }

    @Test(groups = {"smoke", "Regression"})
    public void LoanFacility_MakePrincipal_payments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown()
                .generate_LF_Equated_Principal_Schedule()
                .checkUnallocatedPrincipal()
                .make_Principal_Payments(data.get("PaymentType"));
        String[] ActualStatus = ld.getPrincipalScheduleStatus();
        IntStream.rangeClosed(0, ActualStatus.length - 1)
                .forEachOrdered(i -> Assertions.assertThat(ActualStatus[i])
                        .isEqualTo("Fully Paid"));

    }

    @Test(groups = {"smoke", "Regression"})
    public void LoanFacility_MakeInterest_payments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility();
        LoanFacilityDrawdownPage ld = new LoanFacilityDrawdownPage();
        ld.create_New_Drawdown()
                .generate_LF_Equated_Principal_Schedule()
                .generate_LF_Equated_Interest_Schedule()
                .checkIRscheduleGenerated()
                .make_Interest_Payments(data.get("PaymentType"));
        String[] ActualStatus = ld.getInterestScheduleStatus();
        IntStream.rangeClosed(0, ActualStatus.length - 1)
                .forEachOrdered(i -> Assertions.assertThat(ActualStatus[i])
                        .isEqualTo("Fully Paid"));
    }

}
