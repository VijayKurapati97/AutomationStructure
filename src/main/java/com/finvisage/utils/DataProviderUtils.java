package com.finvisage.utils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

public final class DataProviderUtils {
	
	@DataProvider
	public static Object[][] getData(Method m) {
		
 	List<Map<String,String>> list;
 	Object[][] objects = null;
		
		String testName = m.getName();
		list = ExcelUtils.getTestDetails("LoginData");
		List<Map<String,String>> smallList=new ArrayList<>();

		for (Map<String, String> stringStringMap : list) {

			if (stringStringMap.get("TestName").equalsIgnoreCase(testName ) && stringStringMap.get("Execute").equalsIgnoreCase("yes")) {
				smallList.add(stringStringMap);
			}
		}
		list.removeAll(smallList);

		// TODO :: Need to Fix This!!!

		if(!smallList.isEmpty()) {
			objects = new Object[smallList.size()][1];
		}


		return objects;
	}

}
