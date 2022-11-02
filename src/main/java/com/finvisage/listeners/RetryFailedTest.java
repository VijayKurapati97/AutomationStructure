package com.finvisage.listeners;

import com.finvisage.enums.ConfigProperties;
import com.finvisage.utils.PropertyFileReader;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTest implements IRetryAnalyzer {
    private int count =0;
    private final int retries;

    public RetryFailedTest() {
        retries = 1;
    }

    @Override
    public boolean retry(ITestResult result) {
        try {
            if (PropertyFileReader.get(ConfigProperties.RETRYFAILEDTEST).equalsIgnoreCase("yes")) {
                if (count < retries) {
                    count++;
                    return true;
                }
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
