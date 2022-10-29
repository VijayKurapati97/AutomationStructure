package com.finvisage.drivers;

import java.util.Objects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.finvisage.constants.FrameworkConstants;
import com.finvisage.enums.ConfigProperties;
import com.finvisage.utils.PropertyFileReader;

public final class Drivers {

    private Drivers() {

    }

    public static void initDriver() throws Exception {
        if (Objects.isNull(DriverManager.getDriver())) {
            WebDriverManager.chromedriver().setup();
            WebDriver driver = new ChromeDriver();
            DriverManager.setDriver(driver);
            DriverManager.getDriver().get(PropertyFileReader.get(ConfigProperties.URL));
        }

    }

    public static void quitDriver() {
        if (Objects.nonNull(DriverManager.getDriver())) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }
}
