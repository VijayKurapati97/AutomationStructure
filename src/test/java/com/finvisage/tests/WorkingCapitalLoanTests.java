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

public class WorkingCapitalLoanTests extends BaseTest {
    private final ThreadLocal<String[]> userThreadLocal = ThreadLocal.withInitial(() -> null);

    @AfterMethod
    public void Teardown(ITestContext context) {
        String[] user = userThreadLocal.get();
        FrameworkConstants.setUserAvailability(user);
    }

    private WorkingCapitalLoanTests() {

    }

    @Test(groups = {"Smoke"})
    public void WCL_Create_Drawdown(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        WCLPage wc=new WCLPage();
        wc.create_new_WCL_liability().clickOptions().clickAddDrawdown()
                .enterDrawdownExternalID(7).enterDrawdownLedgerID(7)
                .enterDrawdownPrincipal(data.get("principal"))
                .enterDrawdownValueDate(data.get("valuedate"))
                .enterDrawdownEndDate(data.get("endDate"))
                .enterLoanAcnt("LOAN ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPaymentAcnt(data.get("Payment_Account"))
                .selectDaysInYeartype(data.get("DIYT"))
                .clickNewIrSlab().selectIRType(data.get("IRtype"))
                .enterSpread(data.get("spread")).clickNewTDS()
                .enterTDS(data.get("TDS")).clickNewPrepayments()
                .selectPrepaymentsPenalty(data.get("penalty"))
                .enterAdditionalInfo("Drawdown created").clickCreate();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Drawdown - WCL-Drawdown-")
                .isNotEmpty()
                .isNotNull();
    }
    @Test(groups = {"Smoke"})
    public void WCL_Create_Liability(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickWCL().moveToHamburgerMenu().clickAdd()
                .enterExternalID(10).enterLedgerID(4)
                .enterSanctionDate(data.get("SanctionDate"))
                .enterEndDate(data.get("EndDate")).selectEntity(data.get("Entity"))
                .selectCounterparty(data.get("Counterparty"))
                .enterFacilityAmount(data.get("principalAmount"))
                .selectArranger(data.get("Arranger")).primarySecurityDetails()
                .secondarySecurityDetails().personalGuaranteeDetails()
                .corporateGuaranteeDetails().enterTrustee(data.get("Trustee"))
                .enterAdditionalInfo(data.get("Addinfo")).clickOnCreate();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Working Capital Loan - ")
                .isNotEmpty()
                .isNotNull();

    }
    @Test(groups = {"Smoke"})
    public void WCL_update_liability(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        WCLPage wc=new WCLPage();
        wc.create_new_WCL_liability().clickHamburger()
                .clickEdit().clickContinue();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Working Capital Loans - Edit");
        NewShortTermLoanPage se=new NewShortTermLoanPage();
        se.enterEndDate(data.get("EndDate"))
                .clickOnUpdate();
        Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Working Capital Loan -");
    }
    @Test(groups = {"Smoke"})
    public void WCL_Update_Drawdown(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        WCLPage wc=new WCLPage();
        wc.create_new_WCL_liability();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Working Capital Loan -");
        WCLDrawdownPage wd=new WCLDrawdownPage();
        wd.create_New_WCLDrawdown();
        wd.moveToHamburgerMenu().clickEdit().clickContinue();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Drawdown - Edit");
        NewSTLDrawdownPage se=new NewSTLDrawdownPage();
        se.enterDrawdownEndDate(data.get("EndDate"))
                .clickUpdate();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Drawdown - WCL-Drawdown-");


    }

    @Test(groups = {"Smoke"})
    public void WCL_Make_Prepayments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        WCLPage wc=new WCLPage();
        wc.create_new_WCL_liability();
        wc.clickOptions().clickAddDrawdown()
                .enterDrawdownExternalID(7).enterDrawdownLedgerID(7)
                .enterDrawdownPrincipal(data.get("principal"))
                .enterLoanAcnt("LOAN ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPaymentAcnt(data.get("Payment_Account"))
                .selectDaysInYeartype(data.get("DIYT"))
                .clickNewIrSlab().selectIRType(data.get("IRType"))
                .enterSpread(data.get("Spread")).clickNewTDS()
                .enterTDS(data.get("TDS")).clickNewPrepayments()
                .selectPrepaymentsPenalty(data.get("penalty"))
                .enterAdditionalInfo("Drawdown created").clickCreate();
        WCLDrawdownPage wd=new WCLDrawdownPage();
        double penalty1 = wd.click_PrepaymentOptions().select_MakePrepayments()
                .enterPrepaymentPaymentDate(data.get("prepaymentPaymentdate"))
                .enterPrepaymentValueDate(data.get("prepaymentValueDate"))
                .enterPrepaymentAmount(data.get("prepaymentAmount"))
                .clickSubmit()
                .getPrepaymentPenalty();
        double penalty2 = Double.parseDouble(data.get("prepaymentAmount")) / Double.parseDouble(data.get("penalty"));
        Assertions.assertThat(penalty1).isEqualTo(penalty2);

    }
    @Test(groups = {"Smoke"})
    public void WCL_Make_Prepayments_Payment_and_Delete(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        WCLPage wc=new WCLPage();
        wc.create_new_WCL_liability();
        WCLDrawdownPage wd=new WCLDrawdownPage();
        wd.create_New_WCLDrawdown().make_prepayments()
                .make_prepayments_Payments().clickSubmit();
        String[] ActualStatus = wd.getPrepaymentsStatus();
        IntStream.rangeClosed(0, ActualStatus.length - 1)
                .forEachOrdered(i -> Assertions.assertThat(ActualStatus[i])
                        .isEqualTo("Fully Paid"));
        wd.delete_prepayments();
        IntStream.rangeClosed(0, wd.getPrepaymentsStatus().length - 1)
                .forEachOrdered(i -> Assertions.assertThat(wd.getPrepaymentsStatus()[i])
                        .isEqualTo("Pending"));

    }

    @Test(groups = {"Smoke"})
    public void WCL_Drawdown_AttachedDocuments(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        WCLPage wc=new WCLPage();
        wc.create_new_WCL_liability();
        WCLDrawdownPage wd=new WCLDrawdownPage();
        wd.create_New_WCLDrawdown().clickAttachedDocTab()
                .clickUploadDocuments().uploadAttachedDoc().clickClose();
        int size = wd.clickAttachedDocTab().getAttachedDocSize();
        Assertions.assertThat(size).isNotZero().isGreaterThan(0).isNotNull();
    }

    @Test(groups = {"Smoke"})
    public void WCL_Equated_call_schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        WCLPage wc=new WCLPage();
        wc.create_new_WCL_liability();
        WCLDrawdownPage wd=new WCLDrawdownPage();
        wd.create_New_WCLDrawdown();
        wd.clickCallSchedulesTab().clickCallSchedulesOptions()
                .clickEquatedCallSchedule()
                .selectCallFrequency(data.get("CallFrequency"))
                .selectCallExerciseDay(data.get("ExerciseDay"))
                .clickCallSchedulePreview()
                .clickOnGenerateSchedule().clickCallSchedulesTab();
        java.util.List<WebElement> ele = DriverManager.getDriver().findElements(By.xpath("(//tbody)[8]/tr"));
        Assertions.assertThat(ele.size()).isNotEqualTo(0).isGreaterThan(1);
        ele.clear();
        wd.clickCallSchedulesTab().clickDeactivate_callSchedule();
        java.util.List<WebElement> ele1 = DriverManager.getDriver().findElements(By.xpath("(//tbody)[8]/tr"));
        Assertions.assertThat(ele1.size()).isLessThanOrEqualTo(1);

    }
    @Test(groups = {"Smoke"})
    public void WCL_Ad_hoc_call_schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        WCLPage wc=new WCLPage();
        wc.create_new_WCL_liability();
        WCLDrawdownPage wd=new WCLDrawdownPage();
        wd.create_New_WCLDrawdown();
        wd.clickCallSchedulesTab();
        wd.clickCallSchedulesOptions()
                .clickAdHocCallSchedule()
                .enterCallDate(data.get("date1"))
                .clickSubmit().clickCallSchedulesTab()
                .clickCallSchedulesOptions()
                .clickAdHocCallSchedule()
                .enterCallDate(data.get("date2"))
                .clickSubmit().clickCallSchedulesTab();
        java.util.List<WebElement> ele = DriverManager.getDriver().findElements(By.xpath("(//tbody)[8]/tr"));
        Assertions.assertThat(ele.size()).isNotEqualTo(0).isGreaterThanOrEqualTo(1);
        wd.clickCallSchedulesTab().clickDeactivate_callSchedule();
        List<WebElement> ele1 = DriverManager.getDriver().findElements(By.xpath("(//tbody)[8]/tr"));
        Assertions.assertThat(ele1.size()).isLessThanOrEqualTo(1);
    }

    @Test(groups = {"Smoke"})
    public void WCL_Add_and_Cancel_Fee(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        WCLPage wc=new WCLPage();
        wc.create_new_WCL_liability();
        WCLDrawdownPage wd=new WCLDrawdownPage();
        wd.create_New_WCLDrawdown().clickFeetab()
                .clickAddFee().feeType(data.get("FeeType"))
                .amountType(data.get("AmountType"))
                .enterFeeValue(data.get("FeeValue"))
                .enterRemarks("NA").clickSubmit();
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        Assertions.assertThat(wd.getFeeStatus("2")).isEqualTo("Live");
        wd.cancelFee();
        wd.clickFeetab();
        Uninterruptibles.sleepUninterruptibly(5,TimeUnit.SECONDS);
        Assertions.assertThat(wd.getFeeStatus("1")).isEqualTo("No data to show");

    }

    @Test(groups = {"Smoke"})
    public void WCL_Drawdown_Level_Add_Covenants(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        WCLPage wc=new WCLPage();
        wc.create_new_WCL_liability();
        WCLDrawdownPage wd=new WCLDrawdownPage();
        wd.create_New_WCLDrawdown().clickCovenantsTab()
                .clickAddCovenants().covenantTemplate(data.get("CovenantTemplate"))
                .enterAccountablePerson(data.get("AcntblePerson"))
                .enterThirdParty(data.get("ThirdParty")).enterTargetValue(data.get("TargetValue"))
                .enterTargetDate(data.get("TargetDate")).enterOffset(data.get("Offset"))
                .enterCovenantEndDate(data.get("EndDate")).selectMappingConditions(data.get("Mapping"))
                .selectCovenantsEntity(data.get("Entity")).selectRatioName(data.get("Ratio"))
                .enterThresholdPercentage(data.get("TP")).clickOnCreate();
        int size = wd.getCovenantsSize();
        Assertions.assertThat(size).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);

    }

    @Test(groups = {"Smoke"})
    public void WCL_Drawdown_Level_LienFD(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        FixedDepositPage fd = new FixedDepositPage();
        fd.create_FD_with_InterestSchedule();
        WCLPage wc=new WCLPage();
        wc.create_new_WCL_liability();
        WCLDrawdownPage wd=new WCLDrawdownPage();
        wd.create_New_WCLDrawdown().clickLienDetails()
                .clickAddToLienFD().clickOnCreate();
        int lienedFDs = wd.clickLienDetails().getLiedFDNum();
        Assertions.assertThat(lienedFDs).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);
        ;

    }
    @Test(groups = {"Smoke"})
    public void WCL_Delete_Drawdown(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        WCLPage wc=new WCLPage();
        wc.create_new_WCL_liability();
        WCLDrawdownPage wd=new WCLDrawdownPage();
        String extid1 =wd.create_New_WCLDrawdown().getDrawdownExtId();
        String extid2 = wd.moveToHamburgerMenu()
                .clickDeleteDrawdown().getArchivedDrawdownExtId();
        Assertions.assertThat(extid1).isEqualTo(extid2);
    }

    @Test(groups = {"Smoke"})
    public void WCL_level_AttachedDocuments(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        WCLPage wc=new WCLPage();
        wc.create_new_WCL_liability().clickAttachedDocTab()
                .clickUploadDocuments()
                .uploadAttachedDoc().clickClosebtn();
        int size = wc.getAttachedDocSize();
        Assertions.assertThat(size).isNotZero().isGreaterThan(0).isNotNull();
    }

    @Test(groups = {"Smoke"})
    public void WCL_Level_Add_Covenants(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        WCLPage wc=new WCLPage();
        wc.create_new_WCL_liability().clickCovenantsTab().clickAddCovenants()
                .covenantTemplate(data.get("CovenantTemplate"))
                .enterAccountablePerson(data.get("AcntblePerson"))
                .enterThirdParty(data.get("ThirdParty")).enterTargetValue(data.get("TargetValue"))
                .enterTargetDate(data.get("TargetDate")).enterOffset(data.get("Offset"))
                .enterCovenantEndDate(data.get("EndDate")).selectMappingConditions(data.get("Mapping"))
                .selectCovenantsEntity(data.get("Entity")).selectRatioName(data.get("Ratio"))
                .enterThresholdPercentage(data.get("TP")).clickOnCreate();
        int size = wc.getCovenantsSize();
        Assertions.assertThat(size).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);
        ;
    }

    @Test(groups = {"Smoke"})
    public void WCL_Level_LienFD(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        FixedDepositPage fd = new FixedDepositPage();
        fd.create_FD_with_InterestSchedule();
        WCLPage wc=new WCLPage();
        wc.create_new_WCL_liability().clickLienDetails()
                .clickAddToLienFD().clickOnCreate();
        int lienedFDs = wc.clickLienDetails().getLiedFDNum();
        Assertions.assertThat(lienedFDs).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);


    }

    @Test(groups = {"Smoke"})
    public void WCL_Create_Close(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        WCLPage wc=new WCLPage();
        String LfExternalID = wc.create_new_WCL_liability().getLfExrnlID();
        wc.clickHamburger().clickClose().enterCloseNotes(data.get("Notes"))
                .clickSubmitToClose();
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickWCL();
        String expectedExtId = wc.getLoanWCLBlotter()
                .clickClosedTab().searchExtId(LfExternalID).getFirstLoan();
        Assertions.assertThat(expectedExtId).isEqualTo(LfExternalID);


    }

    @Test(groups = {"Smoke"})
    public void WCL_Create_Delete(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        WCLPage wc=new WCLPage();
        String LfExternalID = wc.create_new_WCL_liability().getLfExrnlID();
        String expectedExtId = wc.clickHamburger().clickDeleteIcon().clickArchivedTab()
                .searchExtId(LfExternalID).getFirstLoan();
        Assertions.assertThat(expectedExtId).isEqualTo(LfExternalID);

    }
}

