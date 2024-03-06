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

public class ECBTests extends BaseTest{
    private final ThreadLocal<String[]> userThreadLocal = ThreadLocal.withInitial(() -> null);

    @AfterMethod
    public void Trardown(ITestContext context) {
        String[] user = userThreadLocal.get();
        FrameworkConstants.setUserAvailablity(user);
    }

    private ECBTests() {
    }

    @Test(groups = {"smoke", "Regression"})
    public void ECB_Create_Drawdown(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ECBPage sl = new ECBPage();
        sl.create_new_Ecb_liability().clickOptions().clickAddDrawdown().enterDrawdownExternalID(10)
                .enterDrwadownLedgerID(8).selectHedger("AUTOMATION_PARTY")
                .enterDrawdownValueDate(data.get("ValueDate")).enterDrawdownEndDate(data.get("EndDate"))
                .selectPrepayemntsPenalty(data.get("penalty"))
                .enterLoanAcnt("Loan ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPayementAcnt(data.get("Payment_Account")).clickNewDisbursement()
                .enterDisAmount(data.get("DisAmount"))
                .selectDisbursementType("Standard").clickNewIrSlab()
                .selectIRType(data.get("IRType")).enterSpread(data.get("Spread"))
                .clickHedgingslab().selectHedgingIRType(data.get("hedgingIRType"))
                .enterHedgingSpread(data.get("HedgingSpread")).selectHedgingDaysInYeartype(data.get("HDIYT"))
                .enterConversionRate(data.get("conversionRate"))
                .clickNewTDS().enterTDS(data.get("TDS")).clickNewWithholdingTax()
                .enterWithholdingTax(data.get("withholdingtax"))
                .enterAdditionalInfo("NA").clickCreate();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Drawdown - ECB-Drawdown-")
                .isNotEmpty()
                .isNotNull();


    }
    @Test(groups = {"smoke", "Regression"})
    public void ECB_Create_Liability(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickECB().moveToHamburgerMenu().clickAdd()
                .enterExternalID(10).enterLedgerID(4)
                .selectBorrowingCurrency("INR")
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
                .contains("Ecb - EC")
                .isNotEmpty()
                .isNotNull();

    }

    @Test(groups = {"smoke", "Regression"})
    public void ECB_update_liability(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ECBPage eb = new ECBPage();
        eb.create_new_Ecb_liability().clickHamburgur()
                .clickEdit().clickContinue();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Ecbs - Edit");
        NewECBPage ec = new NewECBPage();
        ec.enterEndDate(data.get("EndDate"))
                .clickOnCreate();
        Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Ecb - ECB");
    }

    @Test(groups = {"smoke", "Regression"})
    public void ECB_Update_Drawdown(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ECBPage eb = new ECBPage();
        eb.create_new_Ecb_liability();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Ecb - EC");
        ECBDrawdownPage ed = new ECBDrawdownPage();
        ed.create_New_ECBDrawdown();
        ed.moveToHamburgerMenu().clickEdit().clickContinue();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Drawdown - Edit");
        NewECBDrawdownPage nd = new NewECBDrawdownPage();
        nd.enterDrawdownEndDate(data.get("EndDate"))
                .clickCreate();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Drawdown - ECB-Drawdown-");


    }

    @Test(groups = {"smoke", "Regression"})
    public void ECB_Make_Prepayments(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ECBPage ec = new ECBPage();
        ec.create_new_Ecb_liability();
        ec.clickOptions().clickAddDrawdown().enterDrawdownExternalID(10)
                .enterDrwadownLedgerID(8).selectHedger("AUTOMATION_PARTY")
                .enterDrawdownValueDate(data.get("ValueDate")).enterDrawdownEndDate(data.get("EndDate"))
                .selectPrepayemntsPenalty(data.get("penalty"))
                .enterLoanAcnt("Loan ACNT")
                .selectOperatingAcnt("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectPayementAcnt(data.get("Payment_Account")).clickNewDisbursement()
                .enterDisAmount(data.get("DisAmount"))
                .selectDisbursementType("Standard").clickNewIrSlab()
                .selectIRType(data.get("IRType")).enterSpread(data.get("Spread"))
                .clickHedgingslab().selectHedgingIRType(data.get("hedgingIRType"))
                .enterHedgingSpread(data.get("HedgingSpread")).selectHedgingDaysInYeartype(data.get("HDIYT"))
                .enterConversionRate(data.get("conversionRate"))
                .clickNewTDS().enterTDS(data.get("TDS")).clickNewWithholdingTax()
                .enterWithholdingTax(data.get("withholdingtax"))
                .enterAdditionalInfo("NA").clickCreate();
        ECBDrawdownPage ed = new ECBDrawdownPage();
        double penalty1 = ed.click_PrepayemntOptions().select_MakePrepayemnts()
                .enterPrepaymentPayementDate(data.get("prepaymentPaymentdate"))
                .enterPrepaymentValueDate(data.get("prepaymentValueDate"))
                .enterPrepaymentAmount(data.get("prepaymentAmount"))
                .clickSubmit()
                .getPrepaymentPenalty();
        double penalty2 = Double.parseDouble(data.get("prepaymentAmount")) / Double.parseDouble(data.get("penalty"));
        Assertions.assertThat(penalty1).isEqualTo(penalty2);

    }
    @Test(groups = {"smoke", "Regression"})
    public void ECB_Make_Prepayments_Payment_and_Delete(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ECBPage ec = new ECBPage();
        ec.create_new_Ecb_liability();
        ECBDrawdownPage ed = new ECBDrawdownPage();
        ed.create_New_ECBDrawdown().make_prepayments()
                .make_prepayments_Payments();
        String[] ActualStatus = ed.getprepaymentsStatus();
        IntStream.rangeClosed(0, ActualStatus.length - 1)
                .forEachOrdered(i -> Assertions.assertThat(ActualStatus[i])
                        .isEqualTo("Fully Paid"));
        ed.delete_prepayments();
        IntStream.rangeClosed(0, ed.getprepaymentsStatus().length - 1)
                .forEachOrdered(i -> Assertions.assertThat(ed.getprepaymentsStatus()[i])
                        .isEqualTo("Pending"));

    }

    @Test(groups = {"smoke", "Regression"})
    public void ECB_Drawdown_AttachedDocuments(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ECBPage ec = new ECBPage();
        ec.create_new_Ecb_liability();
        ECBDrawdownPage ed = new ECBDrawdownPage();
        ed.create_New_ECBDrawdown().clickAttchedDocTab()
                .clickUploadDocuments()/*.enterUploadDate(data.get("UploadDate"))*/
                .uploadAttchedDoc().clickClose();
        int size = ed.clickAttchedDocTab().getAttachedDocSize();
        Assertions.assertThat(size).isNotZero().isGreaterThan(0).isNotNull();
    }

    @Test(groups = {"smoke", "Regression"})
    public void ECB_Equated_call_schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ECBPage ec=new ECBPage();
        ec.create_new_Ecb_liability();
        ECBDrawdownPage ed =new ECBDrawdownPage();
        ed.create_New_ECBDrawdown();
        ed.clickCallSchedulesTab().clickCallSchedulesOptions()
                .clickEquatedCallSchedule()
                .selectCallFrequency(data.get("CallFrequency"))
                .selectCallExerciseDay(data.get("ExerciseDay"))
                .clickCallSchedulePreview()
                .clickOnGenerateSchedule().clickCallSchedulesTab();
        java.util.List<WebElement> ele = DriverManager.getDriver().findElements(By.xpath("(//tbody)[12]/tr"));
        Assertions.assertThat(ele.size()).isNotEqualTo(0).isGreaterThan(1);
        ele.clear();
        ed.clickCallSchedulesTab().clickdeactivate_callSchedule();
        java.util.List<WebElement> ele1 = DriverManager.getDriver().findElements(By.xpath("(//tbody)[12]/tr"));
        Assertions.assertThat(ele1.size()).isLessThanOrEqualTo(1);

    }
    @Test(groups = {"smoke", "Regression"})
    public void ECB_Ad_hoc_call_schedules(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ECBPage ec=new ECBPage();
        ec.create_new_Ecb_liability();
        ECBDrawdownPage ed=new ECBDrawdownPage();
        ed.create_New_ECBDrawdown();
        ed.clickCallSchedulesTab();
        ed.clickCallSchedulesOptions()
                .clickAdHocCallSchedule()
                .enterCallDate(data.get("date1"))
                .clickSubmit().clickCallSchedulesTab()
                .clickCallSchedulesOptions()
                .clickAdHocCallSchedule()
                .enterCallDate(data.get("date2"))
                .clickSubmit().clickCallSchedulesTab();
        java.util.List<WebElement> ele = DriverManager.getDriver().findElements(By.xpath("(//tbody)[12]/tr"));
        Assertions.assertThat(ele.size()).isNotEqualTo(0).isEqualTo(2);
        ed.clickCallSchedulesTab().clickdeactivate_callSchedule();
        List<WebElement> ele1 = DriverManager.getDriver().findElements(By.xpath("(//tbody)[12]/tr"));
        Assertions.assertThat(ele1.size()).isLessThanOrEqualTo(1);
    }
    @Test(groups = {"smoke", "Regression"})
    public void ECB_Add_and_Cancel_Fee(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ECBPage ec = new ECBPage();
        ec.create_new_Ecb_liability();
        ECBDrawdownPage ed = new ECBDrawdownPage();
        ed.create_New_ECBDrawdown().clickFeetab()
                .clickAddFee().feeType(data.get("FeeType"))
                .amountType(data.get("AmountType"))
                .enterFeeValue(data.get("FeeValue"))
                .enterRemarks("NA").clickSubmit();
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        Assertions.assertThat(ed.getFeeStatus("2")).isEqualTo("Live");
        ed.cancelFee();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        ed.clickFeetab();
        Assertions.assertThat(ed.getFeeStatus("1")).isEqualTo("No data to show");

    }
    @Test(groups = {"smoke", "Regression"})
    public void ECB_Drawdown_Level_Add_Covenants(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ECBPage ec=new ECBPage();
        ec.create_new_Ecb_liability();
        ECBDrawdownPage ed=new ECBDrawdownPage();
        ed.create_New_ECBDrawdown().clickCovenantsTab()
                .clickAddCovenants().covenantTemplate(data.get("CovenantTemplate"))
                .enterAccountablePerson(data.get("AcntblePerson"))
                .enterThirdParty(data.get("ThirdParty")).enterTargetValue(data.get("TargetValue"))
                .enterTargetDate(data.get("TargetDate")).enterOffset(data.get("Offset"))
                .enterCovenantEndDate(data.get("EndDate")).selectMappingConditions(data.get("Mapping"))
                .selectCovenantsEntity(data.get("Entity")).selectRatioName(data.get("Ratio"))
                .enterThresholdPercentage(data.get("TP")).clickOnCreate();
        int size = ed.getCovenatsSize();
        Assertions.assertThat(size).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);

    }

    @Test(groups = {"smoke", "Regression"})
    public void ECB_Drawdown_Level_LienFD(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        FixedDepositPage fd = new FixedDepositPage();
        fd.create_FD_with_InterestSchedule();
        ECBPage ec=new ECBPage();
        ec.create_new_Ecb_liability();
        ECBDrawdownPage ed=new ECBDrawdownPage();
        ed.create_New_ECBDrawdown().clickLienDetails()
                .clickAddToLienFD().clickOnCreate();
        int lienedFDs = ed.clickLienDetails().getLiedFDNum();
        Assertions.assertThat(lienedFDs).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);
        ;

    }

    @Test(groups = {"smoke", "Regression"})
    public void ECB_Delete_Drawdown(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ECBPage ec=new ECBPage();
        ec.create_new_Ecb_liability();
        ECBDrawdownPage ed=new ECBDrawdownPage();
        String extid1 = ed.create_New_ECBDrawdown().getDrawdownExtId();
        String extid2 = ed.moveToHamburgerMenu()
                .clickDeleteDrawdown().getArchivedDrawdownExtId();
        Assertions.assertThat(extid1).isEqualTo(extid2);
    }

    @Test(groups = {"smoke", "Regression"})
    public void ECB_level_AttachedDocuments(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ECBPage ec=new ECBPage();
        ec.create_new_Ecb_liability().clickAttchedDocTab()
                .clickUploadDocuments()
                .uploadAttchedDoc().clickClosebtn();
        int size = ec.getAttachedDocSize();
        Assertions.assertThat(size).isNotZero().isGreaterThan(0).isNotNull();
    }

    @Test(groups = {"smoke", "Regression"})
    public void ECB_Level_Add_Covenants(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ECBPage ec=new ECBPage();
        ec.create_new_Ecb_liability().clickCovenantsTab().clickAddCovenants()
                .covenantTemplate(data.get("CovenantTemplate"))
                .enterAccountablePerson(data.get("AcntblePerson"))
                .enterThirdParty(data.get("ThirdParty")).enterTargetValue(data.get("TargetValue"))
                .enterTargetDate(data.get("TargetDate")).enterOffset(data.get("Offset"))
                .enterCovenantEndDate(data.get("EndDate")).selectMappingConditions(data.get("Mapping"))
                .selectCovenantsEntity(data.get("Entity")).selectRatioName(data.get("Ratio"))
                .enterThresholdPercentage(data.get("TP")).clickOnCreate();
        int size = ec.getCovenatsSize();
        Assertions.assertThat(size).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);
        ;
    }

    @Test(groups = {"smoke", "Regression"})
    public void ECB_Level_LienFD(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        FixedDepositPage fd = new FixedDepositPage();
        fd.create_FD_with_InterestSchedule();
        ECBPage ec=new ECBPage();
        ec.create_new_Ecb_liability().clickLienDetails()
                .clickAddToLienFD().clickOnCreate();
        int lienedFDs = ec.clickLienDetails().getLiedFDNum();
        Assertions.assertThat(lienedFDs).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);


    }

    @Test(groups = {"smoke", "Regression"})
    public void ECB_Create_Close(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ECBPage ec=new ECBPage();
        String LfExternalID = ec.create_new_Ecb_liability().getLfExrnlID();
        ec.clickHamburgur().clickClose().enterCloseNotes(data.get("Notes"))
                .clickSubmitToClose();
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLoanFacility();
        String expectedExtId = ec.getLoanEcbBlotter()
                .clickClosedTab().searchExtId(LfExternalID).getfirstLoan();
        Assertions.assertThat(expectedExtId).isEqualTo(LfExternalID);


    }

    @Test(groups = {"smoke", "Regression"})
    public void ECB_Create_Delete(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ECBPage ec=new ECBPage();
        String LfExternalID = ec.create_new_Ecb_liability().getLfExrnlID();
        String expectedExtId = ec.clickHamburgur().clickDeleteIcon().clickArchivedTab()
                .searchExtId(LfExternalID).getfirstLoan();
        Assertions.assertThat(expectedExtId).isEqualTo(LfExternalID);

    }

}
