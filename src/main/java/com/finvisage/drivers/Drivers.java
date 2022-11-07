package com.finvisage.drivers;

import java.util.Objects;

import com.finvisage.pages.LogInPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.finvisage.constants.FrameworkConstants;
import com.finvisage.enums.ConfigProperties;
import com.finvisage.utils.PropertyFileReader;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public final class Drivers {
    private  static Logger logger = LogManager.getLogger(LogInPage.class);
    private Drivers() { }

    public static void initDriver() throws Exception {
        if (Objects.isNull(DriverManager.getDriver())) {
            if(PropertyFileReader.get(ConfigProperties.BROWSER).equalsIgnoreCase("chrome")||
                    PropertyFileReader.get(ConfigProperties.BROWSER).equalsIgnoreCase("")) {
                WebDriverManager.chromedriver().setup();
                WebDriver driver = new ChromeDriver();
                DriverManager.setDriver(driver);
                logger.info("--chrome driver launched--Successfully");
            }
            else if(PropertyFileReader.get(ConfigProperties.BROWSER).equalsIgnoreCase("firefox")){
                WebDriverManager.firefoxdriver().setup();
                WebDriver driver = new FirefoxDriver();
                DriverManager.setDriver(driver);
                logger.info("--firefox driver launched--Successfully");
            }
            else if(PropertyFileReader.get(ConfigProperties.BROWSER).equalsIgnoreCase("Edge")){
                WebDriverManager.edgedriver().setup();
                WebDriver driver = new EdgeDriver();
                DriverManager.setDriver(driver);
                logger.info("--edge driver launched--Successfully");
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
