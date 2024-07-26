package com.finvisage.tests;

import com.finvisage.constants.FrameworkConstants;
import com.finvisage.drivers.DriverManager;
import com.finvisage.liabilityPages.LiabilityLogInPage;
import com.finvisage.liabilityPages.LoanFacilityDrawdownPage;
import com.finvisage.liabilityPages.LoanFacilityPage;
import com.finvisage.reports.ExtentManager;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.Dimension;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.Map;

public class TestClass extends BaseTest{
    private final ThreadLocal<String[]> userThreadLocal = ThreadLocal.withInitial(() -> null);
    /*@Test(groups = {"Smoke"})
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

    }*/
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
}
