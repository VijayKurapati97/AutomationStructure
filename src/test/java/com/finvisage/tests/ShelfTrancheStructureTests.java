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

public class ShelfTrancheStructureTests extends BaseTest {

    private final ThreadLocal<String[]> userThreadLocal = ThreadLocal.withInitial(() -> null);

    @AfterMethod
    public void teardown(ITestContext context) {
        String[] user = userThreadLocal.get();
        FrameworkConstants.setUserAvailability(user);
    }

    private ShelfTrancheStructureTests() {
    }

    @Test(groups = {"Smoke"})
    public void NCDSDD_Create_SDD_ZeroCoupon(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickShelfTrancheStructure().moveToHamburgerMenu().clickAdd()
                .enterExternalID(6).enterLedgerID(6)
                .selectIssuer(data.get("Issuer")).selectCurrency(data.get("currency"))
                .selectArranger(data.get("arranger")).enterISIN()
                .enterMaturityDate(data.get("MaturityDate"))
                .enterTrancheCanBeIssuedUntil(data.get("TIED"))
                .selectSecuredType(data.get("securedType"))
                .selectRedeemableType(data.get("redeemableType")).selectZeroCoupon("Yes")
                .selectPrimaryCounterparty("AUTOMATION_PARTY").selectRatedType(data.get("ratedType"))
                .selectListingType(data.get("listingType")).selectCumulativeType(data.get("cumulativeType"))
                .enterNumberOfUnits(data.get("numOfNCDs")).enterBoardResolutionDate(data.get("BRD"))
                .enterFinanceCommitteeResolutionDate(data.get("FRD"))
                .enterShareholdersGeneralResolutionDate(data.get("SRD")).clickCreate();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Shelf Tranche Structures - NCD").doesNotContain("Shelf Tranche Structure - New");

    }

    @Test(groups = {"Smoke"})
    public void NCDSDD_Create_SDD_Tranche_ZeroCoupon(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShelfTrancheStructurePage sf = new ShelfTrancheStructurePage();
        sf.create_SDD_with_ZeroCoupon();
        sf.clickOptions().clickAddTranche().enterExternalID(7)
                .enterLedgerID(7).enterIssueOpenDate(data.get("IOD"))
                .enterIssueCloseDate(data.get("ICD")).enterAllotmentDate(data.get("AllotmentDate"))
                .enterIssuePrice(data.get("IssuePrice")).enterNumberOfUnits(data.get("numOfUnits"))
                .enterTrancheIssueLimit(data.get("TrancheIssueLimit"))
                .enterOverSubscription(data.get("overSubscription"))
                .selectDaysInYeartype(data.get("DIYT")).primarySecurityDetails()
                .secondarySecurityDetails().personalGuaranteeDetails()
                .corporateGuaranteeDetails().selectRegistrar("NBFC Registrar")
                .selectTrustee("NBFC Trustee").selectDepositories("NBDP")
                .selectLeadManager("Tester ").selectOperatingAccount("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectSettlementBankAcnt("AUTO_SETTLE_BA").clickNewIrSlab().selectIRType(data.get("IRType"))
                .enterSpread(data.get("spread")).clickNewTDS()
                .enterTrancheMaturityDate(data.get("TrancheMaturityDate")).enterTDS(data.get("TDS")).clickCreate();
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Tranche - Tranche 1")
                .isNotEqualTo("Tranche - New");
    }

    @Test(groups = {"Smoke"})
    public void NCDSDD_Update_SDD(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShelfTrancheStructurePage sf = new ShelfTrancheStructurePage();
        sf.create_SDD_with_ZeroCoupon().clickHamburger()
                .clickEdit().clickContinue();
        Assertions.assertThat(DriverManager.getDriver().getTitle()).isEqualTo("Shelf Tranche Structure - Edit");
        NewShelfTrancheStructurePage st = new NewShelfTrancheStructurePage();
        st.enterDescription("SDD Updated").clickCreate();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Shelf Tranche Structures - NCD");
    }

    @Test(groups = {"Smoke"})
    public void NCDSDD_Create_SDD_Tranche_Without_ZeroCoupon(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShelfTrancheStructurePage sf = new ShelfTrancheStructurePage();
        sf.create_SDD_without_ZeroCoupon();
        sf.clickOptions().clickAddTranche().enterExternalID(7)
                .enterLedgerID(7).enterIssueOpenDate(data.get("IOD"))
                .enterIssueCloseDate(data.get("ICD")).enterAllotmentDate(data.get("AllotmentDate"))
                .enterIssuePrice(data.get("IssuePrice")).enterNumberOfUnits(data.get("numOfUnits"))
                .enterTrancheIssueLimit(data.get("TrancheIssueLimit"))
                .enterOverSubscription(data.get("overSubscription"))
                .selectDaysInYeartype(data.get("DIYT")).primarySecurityDetails()
                .secondarySecurityDetails().personalGuaranteeDetails()
                .corporateGuaranteeDetails().selectRegistrar("NBFC Registrar")
                .selectTrustee("NBFC Trustee").selectDepositories("NBDP")
                .selectLeadManager("Tester ").selectOperatingAccount("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectSettlementBankAcnt("AUTO_SETTLE_BA").clickNewIrSlab().selectIRType(data.get("IRType"))
                .enterSpread(data.get("spread")).clickNewTDS()
                .enterTrancheMaturityDate(data.get("TrancheMaturityDate"))
                .enterTDS(data.get("TDS")).clickCreate();
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Tranche - Tranche 1")
                .isNotEqualTo("Tranche - New");
    }

    @Test(groups = {"Smoke"})
    public void NCDSDD_Create_SDD_Without_ZeroCoupon(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickShelfTrancheStructure().moveToHamburgerMenu().clickAdd()
                .enterExternalID(6).enterLedgerID(6)
                .selectIssuer(data.get("Issuer")).selectCurrency(data.get("currency"))
                .selectArranger(data.get("arranger")).enterISIN()
                .enterMaturityDate(data.get("MaturityDate"))
                .enterTrancheCanBeIssuedUntil(data.get("TIED"))
                .selectSecuredType(data.get("securedType"))
                .selectRedeemableType(data.get("redeemableType")).selectZeroCoupon("No")
                .selectPrimaryCounterparty("AUTOMATION_PARTY").selectRatedType(data.get("ratedType"))
                .selectListingType(data.get("listingType")).selectCumulativeType(data.get("cumulativeType"))
                .enterNumberOfUnits(data.get("numOfNCDs"))
                .enterBoardResolutionDate(data.get("BRD"))
                .enterFinanceCommitteeResolutionDate(data.get("FRD"))
                .enterShareholdersGeneralResolutionDate(data.get("SRD"))
                .clickCreate();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Shelf Tranche Structures - NCD").doesNotContain("Shelf Tranche Structure - New");

    }

    @Test(groups = {"Smoke"})
    public void NCDSDD_Tranche_AttachedDocuments(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShelfTrancheStructurePage sf = new ShelfTrancheStructurePage();
        sf.create_SDD_without_ZeroCoupon();
        SDDTranchePage sd = new SDDTranchePage();
        sd.create_SDD_Tranche_nonZeroCoupon().clickAttachedDocTab()
                .clickUploadDocuments()
                .uploadAttachedDoc().clickToClose();
        int size = sd.clickAttachedDocTab().getAttachedDocSize();
        Assertions.assertThat(size).isNotZero().isGreaterThan(0).isNotNull();
    }

    @Test(groups = {"Smoke"})
    public void NCDSDD_Tranche_Add_CreditRating(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShelfTrancheStructurePage sf = new ShelfTrancheStructurePage();
        sf.create_SDD_without_ZeroCoupon();
        SDDTranchePage sd = new SDDTranchePage();
        sd.create_SDD_Tranche_nonZeroCoupon()
                .clickCreditRatingTab().clickAddCreditRating()
                .selectRatingAgency(data.get("Agency"))
                .selectRatingSymbol(data.get("Symbol"))
                .selectOutlook(data.get("outlook"))
                .enterCreditRatingDate(data.get("RatingDate"))
                .enterRatedAmount(data.get("RatedAmount"))
                .enterRatingDefinition().clickCreate();
        int size = sd.clickCreditRatingTab().getCreditRatingSize();
        Assertions.assertThat(size).isNotZero().isGreaterThan(0).isNotNull();
    }

    @Test(groups = {"Smoke"})
    public void NCDSDD_Tranche_Add_and_Cancel_Fee(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShelfTrancheStructurePage sf = new ShelfTrancheStructurePage();
        sf.create_SDD_without_ZeroCoupon();
        SDDTranchePage sd = new SDDTranchePage();
        sd.create_SDD_Tranche_nonZeroCoupon().clickFeetab()
                .clickAddFee().feeType(data.get("FeeType"))
                .amountType(data.get("AmountType"))
                .enterFeeValue(data.get("FeeValue"))
                .enterRemarks("NA").clickCreate();
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        Assertions.assertThat(sd.getFeeStatus("2")).isEqualTo("Live");
        sd.cancelFee();
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        sd.clickFeetab();
        Assertions.assertThat(sd.getFeeStatus("1")).isEqualTo("No data to show");

    }

    @Test(groups = {"Smoke"})
    public void NCDSDD_Tranche_Add_Covenants(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShelfTrancheStructurePage sf = new ShelfTrancheStructurePage();
        sf.create_SDD_without_ZeroCoupon();
        SDDTranchePage sd = new SDDTranchePage();
        sd.create_SDD_Tranche_nonZeroCoupon().clickCovenantsTab()
                .clickAddCovenants().covenantTemplate(data.get("CovenantTemplate"))
                .enterAccountablePerson(data.get("AcntblePerson"))
                .enterThirdParty(data.get("ThirdParty")).enterTargetValue(data.get("TargetValue"))
                .enterTargetDate(data.get("TargetDate")).enterOffset(data.get("Offset"))
                .enterCovenantEndDate(data.get("EndDate")).selectMappingConditions(data.get("Mapping"))
                .selectCovenantsEntity(data.get("Entity")).selectRatioName(data.get("Ratio"))
                .enterThresholdPercentage(data.get("TP")).clickCreate();
        int size = sd.getCovenantsSize();
        Assertions.assertThat(size).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);

    }

    @Test(groups = {"Smoke"})
    public void NCDSDD_Tranche_Create_Benpos_Schedule(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShelfTrancheStructurePage sf = new ShelfTrancheStructurePage();
        sf.create_SDD_without_ZeroCoupon();
        SDDTranchePage sd = new SDDTranchePage();
        sd.create_SDD_Tranche_nonZeroCoupon()
                .clickPrincipalScheduleOptions()
                .selectAddEquatedPrincipalSchedule()
                .selectPrincipalPayout(data.get("payout")).selectPrincipalPaymentDay(data.get("PaymentDay"))
                .selectPrincipalPaymentConvention(data.get("PaymentConvention")).
                selectPrincipalRounding(data.get("Rounding")).clickOnPreview().clickOnGenerateSchedule()
                .checkUnallocatedPrincipal().clickInvestorsTab()
                .clickBenposOptions().selectAddEquatedbenposSchedule()
                .enterRTAAlertOffset(data.get("RTAAlertOffset"))
                .enterRTAIntimationOffset(data.get("RTAIntimationOffset"))
                .enterPaymentAlertOffset(data.get("PaymentAlertOffset"))
                .clickOnPreview().clickOnGenerateSchedule();
        int size = sd.getBenposScheduleSize();
        Assertions.assertThat(size).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);

    }

    @Test(groups = {"Smoke"})
    public void NCDSDD_CreateTranche_CloseTranche(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShelfTrancheStructurePage sf = new ShelfTrancheStructurePage();
        String LoanExternalID = sf.create_SDD_without_ZeroCoupon().getLoanID();
        SDDTranchePage sd = new SDDTranchePage();
        sd.create_SDD_Tranche_nonZeroCoupon();
        sd.clickPrincipalScheduleOptions()
                .selectAddEquatedPrincipalSchedule()
                .selectPrincipalPayout(data.get("payout")).selectPrincipalPaymentDay(data.get("PaymentDay"))
                .selectPrincipalPaymentConvention(data.get("PaymentConvention")).
                selectPrincipalRounding(data.get("Rounding")).clickOnPreview().clickOnGenerateSchedule()
                .checkUnallocatedPrincipal().make_Principal_Payments(data.get("PaymentType"));
        sd.clickHamburger().clickClose().enterCloseNotes(data.get("Notes"))
                .clickSubmitToClose();
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickShelfTrancheStructure().selectFirstSDD(LoanExternalID);
        Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
        ShelfTrancheStructurePage st = new ShelfTrancheStructurePage();
        String TrancheStatus = st.getTrancheStatus();
        Assertions.assertThat(TrancheStatus).isEqualToIgnoringCase("closed");

    }

    @Test(groups = {"Smoke"})
    public void NCDSDD_Create_Tranche_Delete(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShelfTrancheStructurePage sf = new ShelfTrancheStructurePage();
        sf.create_SDD_without_ZeroCoupon();
        SDDTranchePage sd = new SDDTranchePage();
        String TrancheID = sd.create_SDD_Tranche_nonZeroCoupon().getTrancheExrnlID();
        String TrancheID2 = sd.clickHamburger().clickDeleteIcon().clickArchivedTrancheTab()
                .searchExtId(TrancheID).getFirstLoan();
        Assertions.assertThat(TrancheID).isEqualTo(TrancheID2);

    }

    @Test(groups = {"Smoke"})
    public void NCDSDD_AttachedDocuments(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShelfTrancheStructurePage ss = new ShelfTrancheStructurePage();
        ss.create_SDD_with_ZeroCoupon().clickAttachedDocTab()
                .clickUploadDocuments()
                .uploadAttachedDoc().clickToClose();
        int size = ss.clickAttachedDocTab().getAttachedDocSize();
        Assertions.assertThat(size).isNotZero().isGreaterThan(0).isNotNull();
    }

    @Test(groups = {"Smoke"})
    public void NCDSDD_Add_Covenants(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShelfTrancheStructurePage ss = new ShelfTrancheStructurePage();
        ss.create_SDD_with_ZeroCoupon().clickCovenantsTab()
                .clickAddCovenants().covenantTemplate(data.get("CovenantTemplate"))
                .enterAccountablePerson(data.get("AcntblePerson"))
                .enterThirdParty(data.get("ThirdParty")).enterTargetValue(data.get("TargetValue"))
                .enterTargetDate(data.get("TargetDate")).enterOffset(data.get("Offset"))
                .enterCovenantEndDate(data.get("EndDate")).selectMappingConditions(data.get("Mapping"))
                .selectCovenantsEntity(data.get("Entity")).selectRatioName(data.get("Ratio"))
                .enterThresholdPercentage(data.get("TP")).clickCreate();
        int size = ss.getCovenantsSize();
        Assertions.assertThat(size).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);

    }

    @Test(groups = {"Smoke"})
    public void NCDSDD_Create_Close(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShelfTrancheStructurePage ss = new ShelfTrancheStructurePage();
        String LExternalID = ss.create_SDD_without_ZeroCoupon().getLoanID();
        ss.clickHamburger().clickClose().enterCloseNotes(data.get("Notes"))
                .clickSubmitToClose();
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickShelfTrancheStructure();
        ShelfTrancheBlotter sb = new ShelfTrancheBlotter();
        String expectedExtId = sb.clickClosedTab().searchExtId(LExternalID).getFirstLoan();
        Assertions.assertThat(expectedExtId).isEqualTo(LExternalID);
    }
    @Test(groups = {"Smoke"})
    public void NCDSDD_Create_Delete(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShelfTrancheStructurePage ss=new ShelfTrancheStructurePage();
        String LExternalID= ss.create_SDD_with_ZeroCoupon().getLoanID();
        String expectedExtId = ss.clickHamburger().clickDeleteIcon().clickArchivedTab()
                .searchExtId(LExternalID).getFirstLoan();
        Assertions.assertThat(expectedExtId).isEqualTo(LExternalID);

    }
    @Test(groups = {"Smoke"})
    public void NCDSDD_Update_Tranche(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        ShelfTrancheStructurePage sf = new ShelfTrancheStructurePage();
        sf.create_SDD_without_ZeroCoupon();
        SDDTranchePage sd = new SDDTranchePage();
        sd.create_SDD_Tranche_nonZeroCoupon().clickHamburger()
                .clickEdit().clickContinue().selectRegistrar("NBFC Registrar")
                .selectTrustee("NBFC Trustee").clickCreate();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Shelf Tranche Structures - NCD")
                .doesNotContain("Shelf Tranche Structure - New");
    }

}
