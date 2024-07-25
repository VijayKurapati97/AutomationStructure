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
import org.testng.annotations.Test;

import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.within;


public class TESTS extends BaseTest {
    private final ThreadLocal<String[]> userThreadLocal = ThreadLocal.withInitial(() -> null);

    /* @Test(groups = {"smoke", "Regression"})
     public void CP_CLose_Tranche(Map<String, String> data) {
         ExtentManager.getExtentTest().assignAuthor("Vijay").assignCategory("Regression");
         LiabilityLogInPage lp = new LiabilityLogInPage();
         String[] user = lp.LogIn(FrameworkConstants.getUser());
         userThreadLocal.set(user);
         CommercialPaperPage cp = new CommercialPaperPage();
         cp.create_CommercialPaper();
         CPTranchePage ct=new CPTranchePage();
         ct.create_CP_Tranche();
     }*/



}








