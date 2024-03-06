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

public class ShortTermLoanTests extends BaseTest{
    private final ThreadLocal<String[]> userThreadLocal = ThreadLocal.withInitial(() -> null);

    @AfterMethod
    public void Trardown(ITestContext context) {
        String[] user = userThreadLocal.get();
        FrameworkConstants.setUserAvailablity(user);
    }

    private ShortTermLoanTests() {
    }
    @Test(groups = {"smoke", "Regression"})
    public void STL_Create_Drawdown(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShortTermLoanPage st = new ShortTermLoanPage();
        st.create_new_STL_liability().clickOptions().clickAddDrawdown()
                .enterDrawdownExternalID(7).enterDrwadownLedgerID(7)
                .enterDrwadownPrincipal(data.get("principal"))
                .enterDrawdownValueDate(data.get("valuedate"))
                .enterDrawdownEndDate(data.get("endDate"))
                .enterLoanAcnt("LOAN ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPayementAcnt(data.get("Payment_Account"))
                .selectDaysInYeartype(data.get("DIYT"))
                .clickNewIrSlab().selectIRType(data.get("IRtype"))
                .enterSpread(data.get("spread")).clickNewTDS()
                .enterTDS(data.get("TDS")).clickNewPrepayments()
                .selectPrepayemntsPenalty(data.get("penalty"))
                .enterAdditionalInfo("Drawdown created").clickCreate();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Drawdown - ShortTermLoan-Drawdown-")
                .isNotEmpty()
                .isNotNull();


    }
    @Test(groups = {"smoke", "Regression"})
    public void STL_Create_Liability(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickSTL().moveToHamburgerMenu().clickAdd()
                .enterExternalID(10).enterLedgerID(4)
                .enterStartDate(data.get("SanctionDate"))
                .enterEndDate(data.get("EndDate")).selectEntity(data.get("Entity"))
                .selectCounterparty(data.get("Counterparty"))
                .enterPrincipalAmount(data.get("principalAmount"))
                .selectArranger(data.get("Arranger")).primarySecurityDetails()
                .secondarySecurityDetails().personalGuaranteeDetails()
                .corporateGuaranteeDetails().enterTrustee(data.get("Trustee"))
                .enterAdditionalInfo(data.get("Addinfo")).clickOnCreate();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Short Term Loan - ")
                .isNotEmpty()
                .isNotNull();

    }
    @Test(groups = {"smoke", "Regression"})
    public void STL_update_liability(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShortTermLoanPage st=new ShortTermLoanPage();
        st.create_new_STL_liability().clickHamburgur()
                .clickEdit().clickContinue();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Short term Loans - Edit");
        NewShortTermLoanPage se=new NewShortTermLoanPage();
        se.enterEndDate(data.get("EndDate"))
                .clickOnUpdate();
        Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Short Term Loan -");
    }
    @Test(groups = {"smoke", "Regression"})
    public void STL_Update_Drawdown(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShortTermLoanPage st=new ShortTermLoanPage();
        st.create_new_STL_liability();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Short Term Loan -");
        STLDrawdownPage sd=new STLDrawdownPage();
        sd.create_New_STLDrawdown();
        sd.moveToHamburgerMenu().clickEdit().clickContinue();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Drawdown - Edit");
        NewSTLDrawdownPage se=new NewSTLDrawdownPage();
        se.enterDrawdownEndDate(data.get("EndDate"))
                .clickUpdate();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Drawdown - ShortTermLoan-Drawdown-");


    }
    @Test(groups = {"smoke", "Regression"})
    public void STL_Make_Prepayments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShortTermLoanPage st=new ShortTermLoanPage();
        st.create_new_STL_liability();
        st.clickOptions().clickAddDrawdown()
                .enterDrawdownExternalID(7).enterDrwadownLedgerID(7)
                .enterDrwadownPrincipal(data.get("principal"))
                .enterLoanAcnt("LOAN ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPayementAcnt(data.get("Payment_Account"))
                .selectDaysInYeartype(data.get("DIYT"))
                .clickNewIrSlab().selectIRType(data.get("IRType"))
                .enterSpread(data.get("Spread")).clickNewTDS()
                .enterTDS(data.get("TDS")).clickNewPrepayments()
                .selectPrepayemntsPenalty(data.get("penalty"))
                .enterAdditionalInfo("Drawdown created").clickCreate();
        STLDrawdownPage sd=new STLDrawdownPage();
        double penalty1 = sd.click_PrepayemntOptions().select_MakePrepayemnts()
                .enterPrepaymentPayementDate(data.get("prepaymentPaymentdate"))
                .enterPrepaymentValueDate(data.get("prepaymentValueDate"))
                .enterPrepaymentAmount(data.get("prepaymentAmount"))
                .clickSubmit()
                .getPrepaymentPenalty();
        double penalty2 = Double.parseDouble(data.get("prepaymentAmount")) / Double.parseDouble(data.get("penalty"));
        Assertions.assertThat(penalty1).isEqualTo(penalty2);

    }
    @Test(groups = {"smoke", "Regression"})
    public void STL_Make_Prepayments_Payment_and_Delete(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShortTermLoanPage st = new ShortTermLoanPage();
        st.create_new_STL_liability();
        STLDrawdownPage sd = new STLDrawdownPage();
        sd.create_New_STLDrawdown().make_prepayments()
                .make_prepayments_Payments().clickSubmit();
        String[] ActualStatus = sd.getprepaymentsStatus();
        IntStream.rangeClosed(0, ActualStatus.length - 1)
                .forEachOrdered(i -> Assertions.assertThat(ActualStatus[i])
                        .isEqualTo("Fully Paid"));
        sd.delete_prepayments();
        IntStream.rangeClosed(0, sd.getprepaymentsStatus().length - 1)
                .forEachOrdered(i -> Assertions.assertThat(sd.getprepaymentsStatus()[i])
                        .isEqualTo("Pending"));

    }
    @Test(groups = {"smoke", "Regression"})
    public void STL_Drawdown_AttachedDocuments(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShortTermLoanPage st=new ShortTermLoanPage();
        st.create_new_STL_liability();
        STLDrawdownPage sd=new STLDrawdownPage();
        sd.create_New_STLDrawdown().clickAttchedDocTab()
                .clickUploadDocuments()/*.enterUploadDate(data.get("UploadDate"))*/
                .uploadAttchedDoc().clickClose();
        int size = sd.clickAttchedDocTab().getAttachedDocSize();
        Assertions.assertThat(size).isNotZero().isGreaterThan(0).isNotNull();
    }
    @Test(groups = {"smoke", "Regression"})
    public void STL_Equated_call_schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShortTermLoanPage st=new ShortTermLoanPage();
        st.create_new_STL_liability();
        STLDrawdownPage sd=new STLDrawdownPage();
        sd.create_New_STLDrawdown();
        sd.clickCallSchedulesTab().clickCallSchedulesOptions()
                .clickEquatedCallSchedule()
                .selectCallFrequency(data.get("CallFrequency"))
                .selectCallExerciseDay(data.get("ExerciseDay"))
                .clickCallSchedulePreview()
                .clickOnGenerateSchedule().clickCallSchedulesTab();
        java.util.List<WebElement> ele = DriverManager.getDriver().findElements(By.xpath("(//tbody)[8]/tr"));
        Assertions.assertThat(ele.size()).isNotEqualTo(0).isGreaterThan(1);
        ele.clear();
        sd.clickCallSchedulesTab().clickdeactivate_callSchedule();
        java.util.List<WebElement> ele1 = DriverManager.getDriver().findElements(By.xpath("(//tbody)[8]/tr"));
        Assertions.assertThat(ele1.size()).isLessThanOrEqualTo(1);

    }
    @Test(groups = {"smoke", "Regression"})
    public void STL_Ad_hoc_call_schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShortTermLoanPage st=new ShortTermLoanPage();
        st.create_new_STL_liability();
        STLDrawdownPage sd=new STLDrawdownPage();
        sd.create_New_STLDrawdown();
        sd.clickCallSchedulesTab();
        sd.clickCallSchedulesOptions()
                .clickAdHocCallSchedule()
                .enterCallDate(data.get("date1"))
                .clickSubmit().clickCallSchedulesTab()
                .clickCallSchedulesOptions()
                .clickAdHocCallSchedule()
                .enterCallDate(data.get("date2"))
                .clickSubmit().clickCallSchedulesTab();
        java.util.List<WebElement> ele = DriverManager.getDriver().findElements(By.xpath("(//tbody)[8]/tr"));
        Assertions.assertThat(ele.size()).isNotEqualTo(0).isGreaterThanOrEqualTo(1);
        sd.clickCallSchedulesTab().clickdeactivate_callSchedule();
        List<WebElement> ele1 = DriverManager.getDriver().findElements(By.xpath("(//tbody)[8]/tr"));
        Assertions.assertThat(ele1.size()).isLessThanOrEqualTo(1);
    }
    @Test(groups = {"smoke", "Regression"})
    public void STL_Add_and_Cancel_Fee(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShortTermLoanPage st=new ShortTermLoanPage();
        st.create_new_STL_liability();
        STLDrawdownPage sd=new STLDrawdownPage();
        sd.create_New_STLDrawdown().clickFeetab()
                .clickAddFee().feeType(data.get("FeeType"))
                .amountType(data.get("AmountType"))
                .enterFeeValue(data.get("FeeValue"))
                .enterRemarks("NA").clickSubmit();
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        Assertions.assertThat(sd.getFeeStatus("2")).isEqualTo("Live");
        sd.cancelFee();
        sd.clickFeetab();
        Uninterruptibles.sleepUninterruptibly(5,TimeUnit.SECONDS);
        Assertions.assertThat(sd.getFeeStatus("1")).isEqualTo("No data to show");

    }
    @Test(groups = {"smoke", "Regression"})
    public void STL_Drawdown_Level_Add_Covenants(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShortTermLoanPage st=new ShortTermLoanPage();
        st.create_new_STL_liability();
        STLDrawdownPage sd=new STLDrawdownPage();
        sd.create_New_STLDrawdown().clickCovenantsTab()
                .clickAddCovenants().covenantTemplate(data.get("CovenantTemplate"))
                .enterAccountablePerson(data.get("AcntblePerson"))
                .enterThirdParty(data.get("ThirdParty")).enterTargetValue(data.get("TargetValue"))
                .enterTargetDate(data.get("TargetDate")).enterOffset(data.get("Offset"))
                .enterCovenantEndDate(data.get("EndDate")).selectMappingConditions(data.get("Mapping"))
                .selectCovenantsEntity(data.get("Entity")).selectRatioName(data.get("Ratio"))
                .enterThresholdPercentage(data.get("TP")).clickOnCreate();
        int size = sd.getCovenatsSize();
        Assertions.assertThat(size).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);

    }

    @Test(groups = {"smoke", "Regression"})
    public void STL_Drawdown_Level_LienFD(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        FixedDepositPage fd = new FixedDepositPage();
        fd.create_FD_with_InterestSchedule();
        ShortTermLoanPage st=new ShortTermLoanPage();
        st.create_new_STL_liability();
        STLDrawdownPage sd=new STLDrawdownPage();
        sd.create_New_STLDrawdown().clickLienDetails()
                .clickAddToLienFD().clickOnCreate();
        int lienedFDs = sd.clickLienDetails().getLiedFDNum();
        Assertions.assertThat(lienedFDs).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);
        ;

    }
    @Test(groups = {"smoke", "Regression"})
    public void STL_Delete_Drawdown(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShortTermLoanPage st=new ShortTermLoanPage();
        st.create_new_STL_liability();
        STLDrawdownPage sd=new STLDrawdownPage();
        String extid1 =sd.create_New_STLDrawdown().getDrawdownExtId();
        String extid2 = sd.moveToHamburgerMenu()
                .clickDeleteDrawdown().getArchivedDrawdownExtId();
        Assertions.assertThat(extid1).isEqualTo(extid2);
    }

    @Test(groups = {"smoke", "Regression"})
    public void STL_level_AttachedDocuments(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShortTermLoanPage st=new ShortTermLoanPage();
        st.create_new_STL_liability().clickAttchedDocTab()
                .clickUploadDocuments()
                .uploadAttchedDoc().clickClosebtn();
        int size = st.getAttachedDocSize();
        Assertions.assertThat(size).isNotZero().isGreaterThan(0).isNotNull();
    }
    @Test(groups = {"smoke", "Regression"})
    public void STL_Level_Add_Covenants(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShortTermLoanPage st=new ShortTermLoanPage();
        st.create_new_STL_liability().clickCovenantsTab().clickAddCovenants()
                .covenantTemplate(data.get("CovenantTemplate"))
                .enterAccountablePerson(data.get("AcntblePerson"))
                .enterThirdParty(data.get("ThirdParty")).enterTargetValue(data.get("TargetValue"))
                .enterTargetDate(data.get("TargetDate")).enterOffset(data.get("Offset"))
                .enterCovenantEndDate(data.get("EndDate")).selectMappingConditions(data.get("Mapping"))
                .selectCovenantsEntity(data.get("Entity")).selectRatioName(data.get("Ratio"))
                .enterThresholdPercentage(data.get("TP")).clickOnCreate();
        int size = st.getCovenatsSize();
        Assertions.assertThat(size).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);
        ;
    }
    @Test(groups = {"smoke", "Regression"})
    public void STL_Level_LienFD(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        FixedDepositPage fd = new FixedDepositPage();
        fd.create_FD_with_InterestSchedule();
        ShortTermLoanPage st=new ShortTermLoanPage();
        st.create_new_STL_liability().clickLienDetails()
                .clickAddToLienFD().clickOnCreate();
        int lienedFDs = st.clickLienDetails().getLiedFDNum();
        Assertions.assertThat(lienedFDs).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);


    }
    @Test(groups = {"smoke", "Regression"})
    public void STL_Create_Close(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShortTermLoanPage st=new ShortTermLoanPage();
        String LfExternalID = st.create_new_STL_liability().getLfExrnlID();
        st.clickHamburgur().clickClose().enterCloseNotes(data.get("Notes"))
                .clickSubmitToClose();
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickSTL();
        String expectedExtId = st.getLoanSTLBlotter()
                .clickClosedTab().searchExtId(LfExternalID).getfirstLoan();
        Assertions.assertThat(expectedExtId).isEqualTo(LfExternalID);


    }
    @Test(groups = {"smoke", "Regression"})
    public void STL_Create_Delete(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShortTermLoanPage st=new ShortTermLoanPage();
        String LfExternalID = st.create_new_STL_liability().getLfExrnlID();
        String expectedExtId = st.clickHamburgur().clickDeleteIcon().clickArchivedTab()
                .searchExtId(LfExternalID).getfirstLoan();
        Assertions.assertThat(expectedExtId).isEqualTo(LfExternalID);

    }

}
