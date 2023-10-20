package com.finvisage.drivers;

import com.finvisage.constants.FrameworkConstants;
import org.openqa.selenium.WebDriver;

public final class DriverManager {

	private DriverManager() {
		
	}
	private static final ThreadLocal <WebDriver>Tl=new ThreadLocal<>();
	public static WebDriver getDriver() {
		return Tl.get();
	}
	public static void setDriver(WebDriver dir) {

		Tl.set(dir);
	}
	
	public static void unload() {
		Tl.remove();
	}


}
