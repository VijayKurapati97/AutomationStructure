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

public class NCDStandaloneTests extends BaseTest{
    private final ThreadLocal<String[]> userThreadLocal = ThreadLocal.withInitial(() -> null);

    @AfterMethod
    public void Trardown(ITestContext context) {
        String[] user = userThreadLocal.get();
        FrameworkConstants.setUserAvailablity(user);
    }

    private NCDStandaloneTests() {
    }
    @Test(groups = {"smoke", "Regression"})
    public void NCDSNDL_Create_SNDL_ZeroCoupon(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickStandaloneStructure().moveToHamburgerMenu()
                .clickAdd().selectIssuer("ENTITY_FOR_AUTOMATION_ONLY")
                .selectCurrency(data.get("currency")).selectArranger("ARRANGER_01")
                .enterExternalID(7).enterLedgerID(6)
                .enterISIN().enterMaturityDate(data.get("maturityDate")).selectSecuredType(data.get("securedType"))
                .selectRedeemableType(data.get("redeemableType")).selectZeroCoupon("Yes")
                .selectPrimaryCounterparty("AUTOMATION_PARTY").selectRatedType(data.get("ratedType"))
                .selectListingType(data.get("listingType")).selecCumulativeType(data.get("cumulativeType"))
                .enterIssueOpenDate(data.get("openDate")).enterIssueCloseDate(data.get("closeDate"))
                .enterAllotmentDate(data.get("allotmentDate")).enterIssuePrice(data.get("issuePrice"))
                .enterNumberOfUnits(data.get("numberOfUnits")).enterTrancheIssueLimit(data.get("TrancheIssueLimit"))
                .enterOverSubscription(data.get("overSubscription"))
                .selectDaysInYeartype(data.get("DINY")).primarySecurityDetails().secondarySecurityDetails()
                .personalGuaranteeDetails().corporateGuaranteeDetails().selectRegistrar("Registrar")
                .selectTrustee("NBFC Trustee").selectDepositories("NBDP")
                .selectLeadManager("Tester ").selectOperatingAccount("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectSettlementBankAcnt("AUTO_SETTLE_BA").clickNewIrSlab().selectIRType(data.get("IRType"))
                .enterSpread(data.get("spread")).clickNewTDS().enterTDS(data.get("TDS")).clickCreate();
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Standalone Structures")
                .isNotEqualTo("Standalone Structure - New");

    }
    @Test(groups = {"smoke", "Regression"})
    public void NCDSNDL_Create_SNDL_without_ZeroCoupon(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickStandaloneStructure().moveToHamburgerMenu()
                .clickAdd().selectIssuer("ENTITY_FOR_AUTOMATION_ONLY")
                .selectCurrency(data.get("currency")).selectArranger("ARRANGER_01")
                .enterExternalID(7).enterLedgerID(6)
                .enterISIN().enterMaturityDate(data.get("maturityDate")).selectSecuredType(data.get("securedType"))
                .selectRedeemableType(data.get("redeemableType")).selectZeroCoupon("No")
                .selectPrimaryCounterparty("AUTOMATION_PARTY").selectRatedType(data.get("ratedType"))
                .selectListingType(data.get("listingType")).selecCumulativeType(data.get("cumulativeType"))
                .enterIssueOpenDate(data.get("openDate")).enterIssueCloseDate(data.get("closeDate"))
                .enterAllotmentDate(data.get("allotmentDate")).enterIssuePrice(data.get("issuePrice"))
                .enterNumberOfUnits(data.get("numberOfUnits")).enterTrancheIssueLimit(data.get("TrancheIssueLimit"))
                .enterOverSubscription(data.get("overSubscription"))
                .selectDaysInYeartype(data.get("DINY")).primarySecurityDetails().secondarySecurityDetails()
                .personalGuaranteeDetails().corporateGuaranteeDetails().selectRegistrar("Registrar")
                .selectTrustee("NBFC Trustee").selectDepositories("NBDP")
                .selectLeadManager("Tester ").selectOperatingAccount("BANK_ACCOUNT_01 (INR) (AUTOMATION_PARTY)")
                .selectSettlementBankAcnt("AUTO_SETTLE_BA").clickNewIrSlab().selectIRType(data.get("IRType"))
                .enterSpread(data.get("spread")).clickNewTDS().enterTDS(data.get("TDS")).clickCreate();
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Standalone Structures")
                .isNotEqualTo("Standalone Structure - New");
    }
    @Test(groups = {"smoke", "Regression"})
    public void NCDSNDL_Update_StandaloneStructure(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        StandaloneStructurePage ss=new StandaloneStructurePage();
        ss.create_Standalone_Structure_nonZeroCoupon().clickHamburgur()
                .clickEdit().clickContinue();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Standalone Structure - Edit");
        NewStandaloneStructurePage ns=new NewStandaloneStructurePage();
        ns.selectRegistrar(data.get("Registrar")).clickCreate();
        Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Standalone Structures");
    }
    @Test(groups = {"smoke", "Regression"})
    public void NCDSNDL_AttachedDocuments(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        StandaloneStructurePage ss=new StandaloneStructurePage();
        ss.create_Standalone_Structure_nonZeroCoupon().clickAttchedDocTab()
                .clickUploadDocuments()
                .uploadAttchedDoc().clickClose();
        int size = ss.clickAttchedDocTab().getAttachedDocSize();
        Assertions.assertThat(size).isNotZero().isGreaterThan(0).isNotNull();
    }
    @Test(groups = {"smoke", "Regression"})
    public void NCDSNDL_Add_CreditRating(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        StandaloneStructurePage ss = new StandaloneStructurePage();
        ss.create_Standalone_Structure_nonZeroCoupon()
                .clickCreditRatingTab().clickAddCreditRating()
                .selectRatingAgency(data.get("Agency"))
                .selectRatingSymbol(data.get("Symbol"))
                .selectOutlook(data.get("outlook"))
                .enterCreditRatingDate(data.get("RatingDate"))
                .enterRatedAmount(data.get("RatedAmount"))
                .enterRatingDefinition().clickCreate();
        int size = ss.clickAttchedDocTab().getCreditRatingSize();
        Assertions.assertThat(size).isNotZero().isGreaterThan(0).isNotNull();
    }
    @Test(groups = {"smoke", "Regression"})
    public void NCDSNDL_Add_and_Cancel_Fee(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        StandaloneStructurePage ss=new StandaloneStructurePage();
        ss.create_Standalone_Structure_nonZeroCoupon().clickFeetab()
                .clickAddFee().feeType(data.get("FeeType"))
                .amountType(data.get("AmountType"))
                .enterFeeValue(data.get("FeeValue"))
                .enterRemarks("NA").clickCreate();
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        Assertions.assertThat(ss.getFeeStatus("2")).isEqualTo("Live");
        ss.cancelFee();
        ss.clickFeetab();
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        Assertions.assertThat(ss.getFeeStatus("1")).isEqualTo("No data to show");

    }
    @Test(groups = {"smoke", "Regression"})
    public void NCDSNDL_Add_Covenants(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        StandaloneStructurePage ss=new StandaloneStructurePage();
        ss.create_Standalone_Structure_nonZeroCoupon().clickCovenantsTab()
                .clickAddCovenants().covenantTemplate(data.get("CovenantTemplate"))
                .enterAccountablePerson(data.get("AcntblePerson"))
                .enterThirdParty(data.get("ThirdParty")).enterTargetValue(data.get("TargetValue"))
                .enterTargetDate(data.get("TargetDate")).enterOffset(data.get("Offset"))
                .enterCovenantEndDate(data.get("EndDate")).selectMappingConditions(data.get("Mapping"))
                .selectCovenantsEntity(data.get("Entity")).selectRatioName(data.get("Ratio"))
                .enterThresholdPercentage(data.get("TP")).clickCreate();
        int size = ss.getCovenatsSize();
        Assertions.assertThat(size).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);

    }
    @Test(groups = {"smoke", "Regression"})
    public void NCDSNDL_Create_Close(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        StandaloneStructurePage ss=new StandaloneStructurePage();
        String LfExternalID = ss.create_Standalone_Structure_nonZeroCoupon().getLoanExrnlID();
        ss.clickPrincipalScheduleOptions()
                .selectAddEquatedPrincipalSchedule()
                .selectPrincipalPayout(data.get("payout")).selectPrincipalPayemntDay(data.get("PaymentDay"))
                .selectPrincipalPaymentConvention(data.get("PaymentConvention")).
                selectPrincipalRounding(data.get("Rounding")).clickOnPreview().clickOnGenerateSchedule()
                .checkUnallocatedPrincipal().make_Principal_Payments(data.get("PaymentType"));
        ss.clickHamburgur().clickClose().enterCloseNotes(data.get("Notes"))
                .clickSubmitToClose();
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickStandaloneStructure();
        StandaloneBlotterPage sb=new StandaloneBlotterPage();
        String expectedExtId = sb.clickClosedTab().searchExtId(LfExternalID).getfirstLoan();
        Assertions.assertThat(expectedExtId).isEqualTo(LfExternalID);


    }
    @Test(groups = {"smoke", "Regression"})
    public void NCDSNDL_Create_Delete(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        StandaloneStructurePage ss=new StandaloneStructurePage();
        String LfExternalID = ss.create_Standalone_Structure_nonZeroCoupon().getLoanExrnlID();
        String expectedExtId = ss.clickHamburgur().clickDeleteIcon().clickArchivedTab()
                .searchExtId(LfExternalID).getfirstLoan();
        Assertions.assertThat(expectedExtId).isEqualTo(LfExternalID);

    }
    @Test(groups = {"smoke", "Regression"})
    public void NCDSNDL_Create_Benpos_Schedule(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
        LiabiltyLogInPage lp = new LiabiltyLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        StandaloneStructurePage ss = new StandaloneStructurePage();
        ss.create_Standalone_Structure_nonZeroCoupon()
                .clickPrincipalScheduleOptions()
                .selectAddEquatedPrincipalSchedule()
                .selectPrincipalPayout(data.get("payout")).selectPrincipalPayemntDay(data.get("PaymentDay"))
                .selectPrincipalPaymentConvention(data.get("PaymentConvention")).
                selectPrincipalRounding(data.get("Rounding")).clickOnPreview().clickOnGenerateSchedule()
                .checkUnallocatedPrincipal().clickInvestorsTab()
                .clickBenposOptions().selectAddEquatedbenposSchedule()
                .enterRTAAlertOffset(data.get("RTAAlertOffset"))
                .enterRTAIntimationOffset(data.get("RTAIntimationOffset"))
                .enterPaymentAlertOffset(data.get("PaymentAlertOffset"))
                .clickOnPreview().clickOnGenerateSchedule();
        int size = ss.getBenposScheduleSize();
        Assertions.assertThat(size).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);

    }



}
