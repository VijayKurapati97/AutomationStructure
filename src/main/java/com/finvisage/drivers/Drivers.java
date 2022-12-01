package com.finvisage.drivers;

import java.util.HashMap;
import java.util.Map;
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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class Drivers {
    private  static Logger logger = LogManager.getLogger(LogInPage.class);
    private Drivers() { }

    public static void initDriver() throws Exception {
        if (Objects.isNull(DriverManager.getDriver())) {
            WebDriver driver;
            if(PropertyFileReader.get(ConfigProperties.BROWSER).equalsIgnoreCase("chrome")||
                    PropertyFileReader.get(ConfigProperties.BROWSER).equalsIgnoreCase("")) {
                WebDriverManager.chromedriver().setup();
               if(PropertyFileReader.get(ConfigProperties.HEADLESS).equalsIgnoreCase("yes")){
                   ChromeOptions options = new ChromeOptions();
                   options.addArguments("--window-size=1280,800");
                   options.addArguments("--headless");
                   driver = new ChromeDriver(options);
               }else {
                   driver = new ChromeDriver();
               }
                DriverManager.setDriver(driver);
                logger.info("--chrome driver launched--Successfully");
            }
            else if(PropertyFileReader.get(ConfigProperties.BROWSER).equalsIgnoreCase("firefox")){
                WebDriverManager.firefoxdriver().setup();
                if(PropertyFileReader.get(ConfigProperties.HEADLESS).equalsIgnoreCase("yes")){
                    FirefoxOptions options =new FirefoxOptions();
                    options.addArguments("--headless");
                    driver = new FirefoxDriver(options);

                }else {

                    driver = new FirefoxDriver();
                }
                DriverManager.setDriver(driver);
                logger.info("--firefox driver launched--Successfully");
            }
            else if(PropertyFileReader.get(ConfigProperties.BROWSER).equalsIgnoreCase("Edge")){
                WebDriverManager.edgedriver().setup();
                if(PropertyFileReader.get(ConfigProperties.HEADLESS).equalsIgnoreCase("yes")){
                    EdgeOptions options =new EdgeOptions();
                    options.addArguments("--headless");
                    driver = new EdgeDriver(options);
                }else {
                    driver = new EdgeDriver();
                }
                DriverManager.setDriver(driver);
                logger.info("--edge driver launched--Successfully");
            }

            DriverManager.getDriver().get(PropertyFileReader.get(ConfigProperties.URL));
            DriverManager.getDriver().manage().window().maximize();
        }

    }

    public static void quitDriver() {
        if (Objects.nonNull(DriverManager.getDriver())) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
    }
}
