package com.finvisage.utils;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class DataProviderUtils {


    @DataProvider(parallel = true)
    public static Object[] getData(Method m) {
        String testName = m.getName();
        List<Map<String, String>> totalTests = CsvUtils.getTestDetails(testName);
        List<Map<String, String>> testsToExecute = new ArrayList<>();
        for (Map<String, String> stringStringMap : totalTests) {
            if (stringStringMap.get("Execute").equalsIgnoreCase("yes")) {
                testsToExecute.add(stringStringMap);
            }
        }
        return testsToExecute.toArray();
    }

}
