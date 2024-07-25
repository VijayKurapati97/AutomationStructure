package com.finvisage.drivers;

import com.finvisage.constants.FrameworkConstants;
import com.finvisage.enums.ConfigProperties;
import com.finvisage.frmPages.FRMLogInPage;
import com.finvisage.utils.PropertyFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Map;
import java.util.Objects;

public final class Drivers {
    private static final Logger logger = LogManager.getLogger(FRMLogInPage.class);

    private Drivers() {
    }

    public static void initDriver() throws Exception {
        if (Objects.isNull(DriverManager.getDriver())) {
            WebDriver driver;
            if (PropertyFileReader.get(ConfigProperties.BROWSER).equalsIgnoreCase("chrome") ||
                    PropertyFileReader.get(ConfigProperties.BROWSER).equalsIgnoreCase("")) {
                if (PropertyFileReader.get(ConfigProperties.HEADLESS).equalsIgnoreCase("yes")) {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--window-size=800,600");
                    options.addArguments("--headless");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--no-sandbox");
                    driver = new ChromeDriver(options);
                } else {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--disable-web-security");
                    options.setExperimentalOption("prefs", Map.of("download.default_directory", FrameworkConstants.getDownloadsPath()));
                    driver = new ChromeDriver(options);
                }
                DriverManager.setDriver(driver);
                logger.info("--chrome driver launched--Successfully");
            } else if (PropertyFileReader.get(ConfigProperties.BROWSER).equalsIgnoreCase("firefox")) {
                if (PropertyFileReader.get(ConfigProperties.HEADLESS).equalsIgnoreCase("yes")) {
                    FirefoxOptions options = new FirefoxOptions();
                    options.addArguments("--headless");
                    driver = new FirefoxDriver(options);

                } else {

                    driver = new FirefoxDriver();
                }
                DriverManager.setDriver(driver);
                logger.info("--firefox driver launched--Successfully");
            } else if (PropertyFileReader.get(ConfigProperties.BROWSER).equalsIgnoreCase("Edge")) {
                if (PropertyFileReader.get(ConfigProperties.HEADLESS).equalsIgnoreCase("yes")) {
                    EdgeOptions options = new EdgeOptions();
                    options.addArguments("--headless");
                    driver = new EdgeDriver(options);
                } else {
                    driver = new EdgeDriver();
                }
                DriverManager.setDriver(driver);
                logger.info("--edge driver launched--Successfully");
            }

            DriverManager.getDriver().get(PropertyFileReader.get(ConfigProperties.URL1));
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
