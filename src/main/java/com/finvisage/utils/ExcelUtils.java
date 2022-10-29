package com.finvisage.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.finvisage.constants.FrameworkConstants;

public final class ExcelUtils {

	private ExcelUtils() {}

	public static List<Map<String, String>> getTestDetails(String sheetname) {
		FileInputStream fs = null;
		List<Map<String,String>>list=new ArrayList<>();
		Map<String,String> map;
		try {
		 fs=new FileInputStream(FrameworkConstants.getExcelpath());

		XSSFWorkbook book=new XSSFWorkbook(fs);
			XSSFSheet sheet=book.getSheet(sheetname);
			
			int lastRowNum=sheet.getLastRowNum();
			int lastColumnNum=sheet.getRow(0).getLastCellNum();
			for(int i=1;i<=lastRowNum;i++) {
				
			map= new HashMap<>();
			
			for(int j=0;j<lastColumnNum;j++) {
				
		String key=sheet.getRow(0).getCell(j).getStringCellValue();
		String value=sheet.getRow(i).getCell(j).getStringCellValue();
		map.put(key,value);
		
			}
			list.add(map);
			
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
			if(Objects.nonNull(fs)) {
			try {
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
		}
		return list;
	
	}

}
