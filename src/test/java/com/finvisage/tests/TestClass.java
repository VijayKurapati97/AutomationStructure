package com.finvisage.tests;

import com.finvisage.constants.FrameworkConstants;
import com.finvisage.drivers.DriverManager;
import com.finvisage.liabilityPages.LiabilityLogInPage;
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
    @Test(groups = {"Smoke"})
    public void LoanFacility_Create_Delete(Map<String, String> data) {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);

        LoanFacilityPage lf = new LoanFacilityPage();
        String LfExternalID = lf.create_new_LoanFacility().getLfExrnlID();
        String expectedExtId = lf.clickHamburger().clickDeleteIcon().clickArchivedTab()
                .searchExtId(LfExternalID).getFirstLoan();
        Assertions.assertThat(expectedExtId).isEqualTo("vijay");

    }
}
