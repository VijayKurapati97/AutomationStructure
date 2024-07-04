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

public class SubDebtNCDTests extends BaseTest{
    private final ThreadLocal<String[]> userThreadLocal = ThreadLocal.withInitial(() -> null);

    @AfterMethod
    public void Teardown(ITestContext context) {
        String[] user = userThreadLocal.get();
        FrameworkConstants.setUserAvailability(user);
    }

    private SubDebtNCDTests() {
    }
    @Test(groups = {"Smoke"})
    public void SubDebt_Create_NCD_ZeroCoupon(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickSubDebtNCD().moveToHamburgerMenu()
                .clickAdd().selectIssuer("ENTITY_FOR_AUTOMATION_ONLY")
                .selectCurrency(data.get("currency")).selectArranger("ARRANGER_01")
                .enterExternalID(7).enterLedgerID(6)
                .enterISIN().enterMaturityDate(data.get("maturityDate")).selectSecuredType(data.get("securedType"))
                .selectRedeemableType(data.get("redeemableType")).selectZeroCoupon("Yes")
                .selectPrimaryCounterparty("AUTOMATION_PARTY").selectRatedType(data.get("ratedType"))
                .selectListingType(data.get("listingType")).selectCumulativeType(data.get("cumulativeType"))
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
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Sub Debt NCD - SUBDNCD")
                .isNotEqualTo("Standalone Structure - New");

    }
    @Test(groups = {"Smoke"})
    public void SubDebt_Create_NCD_without_ZeroCoupon(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickLiability().clickSubDebtNCD().moveToHamburgerMenu()
                .clickAdd().selectIssuer("ENTITY_FOR_AUTOMATION_ONLY")
                .selectCurrency(data.get("currency")).selectArranger("ARRANGER_01")
                .enterExternalID(7).enterLedgerID(6)
                .enterISIN().enterMaturityDate(data.get("maturityDate")).selectSecuredType(data.get("securedType"))
                .selectRedeemableType(data.get("redeemableType")).selectZeroCoupon("No")
                .selectPrimaryCounterparty("AUTOMATION_PARTY").selectRatedType(data.get("ratedType"))
                .selectListingType(data.get("listingType")).selectCumulativeType(data.get("cumulativeType"))
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
        Assertions.assertThat(DriverManager.getDriver().getTitle()).contains("Sub Debt NCD - SUBDNCD")
                .isNotEqualTo("Standalone Structure - New");
    }
    @Test(groups = {"Smoke"})
    public void SubDebt_Update_NCD(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SubDebtNCDPage sd=new SubDebtNCDPage();
        sd.create_SubDebt_NCD_nonZeroCoupon().clickHamburgur()
                .clickEdit().clickContinue();
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .isEqualTo("Sub Debt NCD - Edit");
        NewSubDebtNCDPage sn=new NewSubDebtNCDPage();
        sn.selectRegistrar(data.get("Registrar")).clickCreate();
        Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
        Assertions.assertThat(DriverManager.getDriver().getTitle())
                .contains("Sub Debt NCD - SUBDNCD");
    }
    @Test(groups = {"Smoke"})
    public void SubDebt_NCD_AttachedDocuments(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SubDebtNCDPage sd=new SubDebtNCDPage();
        sd.create_SubDebt_NCD_nonZeroCoupon().clickAttchedDocTab()
                .clickUploadDocuments()
                .uploadAttchedDoc().clickClose();
        int size = sd.clickAttchedDocTab().getAttachedDocSize();
        Assertions.assertThat(size).isNotZero().isGreaterThan(0).isNotNull();
    }
    @Test(groups = {"Smoke"})
    public void SubDebt_NCD_Add_CreditRating(Map<String, String> data) throws AWTException {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SubDebtNCDPage sd=new SubDebtNCDPage();
        sd.create_SubDebt_NCD_nonZeroCoupon()
                .clickCreditRatingTab().clickAddCreditRating()
                .selectRatingAgency(data.get("Agency"))
                .selectRatingSymbol(data.get("Symbol"))
                .selectOutlook(data.get("outlook"))
                .enterCreditRatingDate(data.get("RatingDate"))
                .enterRatedAmount(data.get("RatedAmount"))
                .enterRatingDefinition().clickCreate();
        int size = sd.clickAttchedDocTab().getCreditRatingSize();
        Assertions.assertThat(size).isNotZero().isGreaterThan(0).isNotNull();
    }
    @Test(groups = {"Smoke"})
    public void SubDebt_NCD_Add_and_Cancel_Fee(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SubDebtNCDPage sd=new SubDebtNCDPage();
        sd.create_SubDebt_NCD_nonZeroCoupon().clickFeetab()
                .clickAddFee().feeType(data.get("FeeType"))
                .amountType(data.get("AmountType"))
                .enterFeeValue(data.get("FeeValue"))
                .enterRemarks("NA").clickCreate();
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
        Assertions.assertThat(sd.getFeeStatus("2")).isEqualTo("Live");
        sd.cancelFee();
        sd.clickFeetab();
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        Assertions.assertThat(sd.getFeeStatus("1")).isEqualTo("No data to show");

    }
    @Test(groups = {"Smoke"})
    public void SubDebt_NCD_Add_Covenants(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SubDebtNCDPage sd=new SubDebtNCDPage();
        sd.create_SubDebt_NCD_nonZeroCoupon().clickCovenantsTab()
                .clickAddCovenants().covenantTemplate(data.get("CovenantTemplate"))
                .enterAccountablePerson(data.get("AcntblePerson"))
                .enterThirdParty(data.get("ThirdParty")).enterTargetValue(data.get("TargetValue"))
                .enterTargetDate(data.get("TargetDate")).enterOffset(data.get("Offset"))
                .enterCovenantEndDate(data.get("EndDate")).selectMappingConditions(data.get("Mapping"))
                .selectCovenantsEntity(data.get("Entity")).selectRatioName(data.get("Ratio"))
                .enterThresholdPercentage(data.get("TP")).clickCreate();
        int size = sd.getCovenatsSize();
        Assertions.assertThat(size).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);

    }
    @Test(groups = {"Smoke"})
    public void SubDebt_NCD_Create_Close(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SubDebtNCDPage sd=new SubDebtNCDPage();
        String LoanExternalID = sd.create_SubDebt_NCD_nonZeroCoupon().getLoanExrnlID();
        sd.clickPrincipalScheduleOptions()
                .selectAddEquatedPrincipalSchedule()
                .selectPrincipalPayout(data.get("payout")).selectPrincipalPayemntDay(data.get("PaymentDay"))
                .selectPrincipalPaymentConvention(data.get("PaymentConvention")).
                selectPrincipalRounding(data.get("Rounding")).clickOnPreview().clickOnGenerateSchedule()
                .checkUnallocatedPrincipal().make_Principal_Payments(data.get("PaymentType"));
        sd.clickHamburgur().clickLoanClose().enterCloseNotes(data.get("Notes"))
                .clickSubmitToClose();
        LiabilityDashboardsPage ld = new LiabilityDashboardsPage();
        ld.clickStandaloneStructure();
        StandaloneBlotterPage sb=new StandaloneBlotterPage();
        String expectedExtId = sb.clickClosedTab().searchExtId(LoanExternalID).getFirstLoan();
        Assertions.assertThat(expectedExtId).isEqualTo(LoanExternalID);


    }
    @Test(groups = {"Smoke"})
    public void SubDebt_NCD_Create_Delete(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SubDebtNCDPage sd=new SubDebtNCDPage();
        String LoanExternalID = sd.create_SubDebt_NCD_nonZeroCoupon().getLoanExrnlID();
        String expectedExtId = sd.clickHamburgur().clickDeleteIcon().clickArchivedTab()
                .searchExtId(LoanExternalID).getfirstLoan();
        Assertions.assertThat(expectedExtId).isEqualTo(LoanExternalID);

    }
    @Test(groups = {"Smoke"})
    public void SubDebt_NCD_Create_Benpos_Schedule(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        SubDebtNCDPage sd=new SubDebtNCDPage();
        sd.create_SubDebt_NCD_nonZeroCoupon()
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
        int size = sd.getBenposScheduleSize();
        Assertions.assertThat(size).isNotZero().isNotNull()
                .isPositive().isGreaterThan(0);

    }
}
