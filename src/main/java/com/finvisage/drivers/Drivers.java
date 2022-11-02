package com.finvisage.drivers;

import java.util.Objects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.finvisage.constants.FrameworkConstants;
import com.finvisage.enums.ConfigProperties;
import com.finvisage.utils.PropertyFileReader;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class Drivers {

    private Drivers() {

    }

    public static void initDriver() throws Exception {
        if (Objects.isNull(DriverManager.getDriver())) {
            if(PropertyFileReader.get(ConfigProperties.BROWSER).equalsIgnoreCase("chrome")||
                    PropertyFileReader.get(ConfigProperties.BROWSER).equalsIgnoreCase("")) {
                WebDriverManager.chromedriver().setup();
                WebDriver driver = new ChromeDriver();
                DriverManager.setDriver(driver);
            }
            else if(PropertyFileReader.get(ConfigProperties.BROWSER).equalsIgnoreCase("firefox")){
                WebDriverManager.firefoxdriver().setup();
                WebDriver driver = new FirefoxDriver();
                DriverManager.setDriver(driver);
            }
            else if(PropertyFileReader.get(ConfigProperties.BROWSER).equalsIgnoreCase("Edge")){
                WebDriverManager.edgedriver().setup();
                WebDriver driver = new EdgeDriver();
                DriverManager.setDriver(driver);
            }

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
