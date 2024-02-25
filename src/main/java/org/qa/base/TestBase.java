package org.qa.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.qa.util.Utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestBase {

    public static WebDriver driver;
    public static Properties props;
    public static WebDriverWait wait;
    public static Logger log;

    public TestBase() {
        try {
            props = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\org\\qa\\config\\config.properties");
            props.load(ip);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        System.setProperty("logfile.name", props.getProperty("log_fie_path")+props.getProperty("log_file_name"));
        log = Logger.getLogger("McPizza");
    }

    public static void initialize() throws Exception {

        log.info("Logs are generated at : "+System.getProperty("logfile.name"));
        log.info("Initializing the TestBase.");
        String browser = props.getProperty("browser");

        if (browser.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else {
            throw new Exception("Provided browser name is wither incorrect or not supported. Please cross verify.");
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utility.PAGE_LOAD_TIMEOUT));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utility.IMPLICIT_WAIT));

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        log.info("TestBase initialization complete. Launching URL.");
        driver.get(props.getProperty("url"));

    }
}