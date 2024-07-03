package com.finvisage.tests;

import com.finvisage.constants.FrameworkConstants;
import com.finvisage.drivers.DriverManager;
import com.finvisage.liabilityPages.*;
import com.finvisage.reports.ExtentManager;
import com.google.common.util.concurrent.Uninterruptibles;
import org.assertj.core.api.Assertions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class CommercialPaperTests extends BaseTest{
    private final ThreadLocal<String[]> userThreadLocal = ThreadLocal.withInitial(() -> null);

    @AfterMethod
    public void Teardown(ITestContext context) {
        String[] user = userThreadLocal.get();
        FrameworkConstants.setUserAvailablity(user);
    }

    private CommercialPaperTests() {
    }


    @Test(groups = {"Smoke"})
    public void CP_Create_CommercialPaper(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LiabilityDashboardsPage ld=new LiabilityDashboardsPage();
        ld.clickLiability().clickCommercialPaper().moveToHamburgerMenu()
                .clickAdd().enterExternalID(6)
                .enterLedgerID(6).enterISIN()
                .enterMaturityDate(data.get("maturityDate"))
                .selectIssuer("ENTITY_FOR_AUTOMATION_ONLY")
                .selectRTA("AUTOMATION_PARTY").selectIPA("TEST_BANK_01")
                .selectCurrency(data.get("currency")).enterIssueCurrentAccount("ICA")
                .enterIssueDematAccount("IDA").selectArranger("ARRANGER_01")
                .clickCreate();
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Commercial Papers - ")
                .doesNotContain("Commercial Paper - New");


    }

    @Test(groups = {"Smoke"})
    public void CP_Edit_CommercialPaper(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        CommercialPaperPage cp=new CommercialPaperPage();
        cp.create_CommercialPaper().clickHamburger()
                .clickEdit().clickContinue()
                .enterMaturityDate(data.get("NewMaturityDate"))
                .clickCreate();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Commercial Papers - ")
                .doesNotContain("Commercial Paper - Edit");
    }
    @Test(groups = {"Smoke"})
    public void CP_Create_Tranche(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        CommercialPaperPage cp = new CommercialPaperPage();
        cp.create_CommercialPaper().clickOptions().clickAddTranche()
                .enterExternalID(6).enterLedgerID(6)
                .enterDealDate(data.get("dealDate")).enterValueDate(data.get("valueDate"))
                .enterNumberOfUnits(data.get("units"))
                .enterInvestmentAmount(data.get("investment")).enterDiscountRate(data.get("discountRate"))
                .enterInitialPaymentDate(data.get("IPD")).enterFinalPaymentDate(data.get("FPD"))
                .primarySecurityDetails().secondarySecurityDetails()
                .personalGuaranteeDetails().corporateGuaranteeDetails()
                .enterAdditionalInfo("NA").clickCreate();
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Tranches - ")
                .doesNotContain("New");
    }
    @Test(groups = {"Smoke"})
    public void CP_Edit_Tranche(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        CommercialPaperPage cp = new CommercialPaperPage();
        cp.create_CommercialPaper();
        CPTranchePage ct=new CPTranchePage();
        ct.create_CP_Tranche().clickHamburger()
                .clickEdit().clickContinue().enterTrustee("Tester")
                .clickCreate();
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Tranches - ")
                .doesNotContain("New");
    }
    @Test(groups = {"Smoke"})
    public void CP_AttachedDocument_Tranche(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        CommercialPaperPage cp = new CommercialPaperPage();
        cp.create_CommercialPaper();
        CPTranchePage ct=new CPTranchePage();
        ct.create_CP_Tranche()
                .clickAttachedDocTab()
                .clickUploadDocuments()
                .uploadAttachedDoc().clickToClose();
        int size = ct.clickAttachedDocTab().getAttachedDocSize();
        Assertions.assertThat(size).isNotZero().isGreaterThan(0).isNotNull();
    }
    @Test(groups = {"Smoke"})
    public void CP_CreditRating_Tranche(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        CommercialPaperPage cp = new CommercialPaperPage();
        cp.create_CommercialPaper();
        CPTranchePage ct=new CPTranchePage();
        ct.create_CP_Tranche()
                .clickCreditRatingTab().clickAddCreditRating()
                .selectRatingAgency(data.get("Agency"))
                .selectRatingSymbol(data.get("Symbol"))
                .selectOutlook(data.get("outlook"))
                .enterCreditRatingDate(data.get("RatingDate"))
                .enterRatedAmount(data.get("RatedAmount"))
                .enterRatingDefinition().clickCreate();
        int size = ct.clickCreditRatingTab().getCreditRatingSize();
        Assertions.assertThat(size).isNotZero().isGreaterThan(0).isNotNull();
    }
    @Test(groups = {"Smoke"})
    public void CP_Tranche_Add_and_Cancel_Fee(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        CommercialPaperPage cp = new CommercialPaperPage();
        cp.create_CommercialPaper();
        CPTranchePage ct=new CPTranchePage();
        ct.create_CP_Tranche().clickFeetab()
                .clickAddFee().feeType(data.get("FeeType"))
                .amountType(data.get("AmountType"))
                .enterFeeValue(data.get("FeeValue"))
                .enterRemarks("NA").clickCreate();
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        Assertions.assertThat(ct.getFeeStatus("2")).isEqualTo("Live");
        ct.cancelFee();
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        ct.clickFeetab();
        Assertions.assertThat(ct.getFeeStatus("1")).isEqualTo("No data to show");
    }

    @Test(groups = {"Smoke"})
    public void CP_Tranche_Add_Covenants(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        CommercialPaperPage cp = new CommercialPaperPage();
        cp.create_CommercialPaper();
        CPTranchePage ct=new CPTranchePage();
        ct.create_CP_Tranche().clickCovenantsTab()
                .clickAddCovenants().covenantTemplate(data.get("CovenantTemplate"))
                .enterAccountablePerson(data.get("AcntblePerson"))
                .enterThirdParty(data.get("ThirdParty")).enterTargetValue(data.get("TargetValue"))
                .enterTargetDate(data.get("TargetDate")).enterOffset(data.get("Offset"))
                .enterCovenantEndDate(data.get("EndDate")).selectMappingConditions(data.get("Mapping"))
                .selectCovenantsEntity(data.get("Entity")).selectRatioName(data.get("Ratio"))
                .enterThresholdPercentage(data.get("TP")).clickCreate();
        int size = ct.getCovenantsSize();
        Assertions.assertThat(size).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);

    }
    @Test(groups = {"Smoke"})
    public void CP_view_Analytics(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        CommercialPaperPage cp = new CommercialPaperPage();
        cp.create_CommercialPaper();
        CPTranchePage ct=new CPTranchePage();
        ct.create_CP_Tranche()
                .clickAnalyticsTab()
                .checkXirrEirValues();
        Assertions.assertThat(ct.getXirrValue()).doesNotContain("0.00")
                .doesNotContain("__.__%");
        Assertions.assertThat(ct.getEirValue()).doesNotContain("0.00")
                .doesNotContain("__.__%");

    }
    @Test(groups = {"Smoke"})
    public void CP_Delete_Tranche(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        CommercialPaperPage cp = new CommercialPaperPage();
        cp.create_CommercialPaper();
        CPTranchePage ct=new CPTranchePage();
        String TrancheID = ct.create_CP_Tranche().getTrancheExrnlID();
        String TrancheID2 = ct.clickHamburger().clickDeleteIcon().clickArchivedTrancheTab()
                .searchExtId(TrancheID).getFirstLoan();
        Assertions.assertThat(TrancheID).isEqualTo(TrancheID2);

    }

    @Test(groups = {"Smoke"})
    public void CP_AttachedDocuments(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        CommercialPaperPage cp = new CommercialPaperPage();
        cp.create_CommercialPaper().clickAttachedDocTab()
                .clickUploadDocuments()
                .uploadAttachedDoc().clickToClose();
        int size = cp.clickAttachedDocTab().getAttachedDocSize();
        Assertions.assertThat(size).isNotZero().isGreaterThan(0).isNotNull();
    }
    @Test(groups = {"Smoke"})
    public void CP_Add_Covenants(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        CommercialPaperPage cp = new CommercialPaperPage();
        cp.create_CommercialPaper().clickCovenantsTab()
                .clickAddCovenants().covenantTemplate(data.get("CovenantTemplate"))
                .enterAccountablePerson(data.get("AcntblePerson"))
                .enterThirdParty(data.get("ThirdParty")).enterTargetValue(data.get("TargetValue"))
                .enterTargetDate(data.get("TargetDate")).enterOffset(data.get("Offset"))
                .enterCovenantEndDate(data.get("EndDate")).selectMappingConditions(data.get("Mapping"))
                .selectCovenantsEntity(data.get("Entity")).selectRatioName(data.get("Ratio"))
                .enterThresholdPercentage(data.get("TP")).clickCreate();
        int size = cp.getCovenantsSize();
        Assertions.assertThat(size).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);

    }
    @Test(groups = {"Smoke"})
    public void CP_Create_close_CommercialPaper(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        CommercialPaperPage cp = new CommercialPaperPage();
        String LExternalID = cp.create_CommercialPaper().getLoanID();
        cp.clickHamburger()
                .clickClose().enterCloseNotes(data.get("notes")).clickSubmitToClose();
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickCommercialPaper();
        CPBlotterPage cb=new CPBlotterPage();
        String expectedExtId = cb.clickClosedTab().searchExtId(LExternalID).getFirstLoan();
        Assertions.assertThat(expectedExtId).isEqualTo(LExternalID);
    }

    @Test(groups = {"Smoke"})
    public void CP_Create_Delete_CommercialPaper(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        CommercialPaperPage cp = new CommercialPaperPage();
        String LExternalID = cp.create_CommercialPaper().getLoanID();
        String expectedExtId = cp.clickHamburger().clickDeleteIcon().clickArchivedTab()
                .searchExtId(LExternalID).getFirstLoan();
        Assertions.assertThat(expectedExtId).isEqualTo(LExternalID);
    }
}
