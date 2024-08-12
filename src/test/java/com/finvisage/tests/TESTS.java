package com.finvisage.tests;

import com.finvisage.constants.FrameworkConstants;
import com.finvisage.drivers.DriverManager;
import com.finvisage.liabilityPages.*;
import com.finvisage.reports.ExtentManager;
import com.google.common.util.concurrent.Uninterruptibles;
import org.apache.commons.lang3.RandomStringUtils;
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

import static org.assertj.core.api.AssertionsForClassTypes.within;

public class TESTS extends BaseTest {
    private final ThreadLocal<String[]> userThreadLocal = ThreadLocal.withInitial(() -> null);

    @AfterMethod
    public void Teardown(ITestContext context) {
        String[] user = userThreadLocal.get();
        FrameworkConstants.setUserAvailability(user);
    }
    @Test(groups = {"Smoke"})
    public void LoanFacility_level_AttachedDocuments(Map<String, String> data)  {
        ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Smoke");
        LiabilityLogInPage lp = new LiabilityLogInPage();
        String[] user = lp.LogIn(FrameworkConstants.getUser());
        userThreadLocal.set(user);
        LoanFacilityPage lf = new LoanFacilityPage();
        lf.create_new_LoanFacility().clickAttachedDocumentsTab()
                .clickUploadDocuments()
                .uploadFile().clickClosebtn();
        String fileName = lf.getAttachedDocFileName();
        Assertions.assertThat(fileName).isEqualTo("AttachedDocuments");
    }






}