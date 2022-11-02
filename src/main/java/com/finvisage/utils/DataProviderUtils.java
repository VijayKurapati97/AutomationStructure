package com.finvisage.utils;

import com.finvisage.constants.FrameworkConstants;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class DataProviderUtils {


    @DataProvider
    public static Object[] getData(Method m) {

        List<Map<String, String>> list;

        String testname = m.getName();
        list = ExcelUtils.getTestDetails(FrameworkConstants.getIterationDataheet());
        List<Map<String, String>> smallList = new ArrayList<>();

        for (Map<String, String> stringStringMap : list) {

            if (stringStringMap.get("TestName").equalsIgnoreCase(testname) && stringStringMap.get("Execute").equalsIgnoreCase("yes")) {

                smallList.add(stringStringMap);
            }
        }

        return smallList.toArray();
    }

}
