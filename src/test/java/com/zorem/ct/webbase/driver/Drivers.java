package com.zorem.ct.webbase.driver;

import com.zorem.ct.webbase.exceptions.DriverInitializationException;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Drivers {

    public static WebDriver createChromeDriver() {

        try {

//            String driver_path = System.getProperty("user.dir") + "/src/main/resources/webdrivers/"+getConfigWeb(WebConfigJson.CHROME);
//            System.setProperty("webdriver.chrome.driver", driver_path);

            WebDriverManager.chromedriver().setup();


            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--no-sandbox");
            chromeOptions.addArguments("--no-first-run");
            chromeOptions.addArguments("--enable-extensions");
            chromeOptions.addArguments("--homepage=about:blank");
            chromeOptions.addArguments("--ignore-certificate-errors");
            chromeOptions.addArguments("--disable-dev-shm-usage");
            chromeOptions.addArguments("--disable-gpu");
            chromeOptions.addArguments("--window-size=1920,1080");

            //Selenium 3 y hasta 4.7.0
            chromeOptions.addArguments("--remote-allow-origins=*");
            //chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
            //chromeOptions.setCapability(CapabilityType.VERSION, System.getProperty("browser.version", ""));
            //chromeOptions.setCapability(CapabilityType.BROWSER_VERSION, System.getProperty("browser.version", ""));



            //chromeOptions.setCapability("enableVNC", Boolean.parseBoolean(System.getProperty("enableVNC", "false")));
            //LoggingPreferences logPrefs = new LoggingPreferences();
            //logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
            //chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);


            if (System.getProperty("enable.local", "true").equals("true")) {
                return WebDriverManager.chromedriver().capabilities(chromeOptions).create();
            } else {
                return WebDriverManager.chromedriver().capabilities(chromeOptions).remoteAddress("http://localhost:4444/wd/hub").create();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new DriverInitializationException("Error al inicializar el controlador de Chrome: ");
        }


    }

    public static WebDriver createFirefoxDriver() {

        try {
//            String driver_path = System.getProperty("user.dir") + "/src/main/resources/webdrivers/"+getConfigWeb(WebConfigJson.FIREFOX);
//            System.setProperty("webdriver.gecko.driver", driver_path);

            WebDriverManager.firefoxdriver().setup();

            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("--no-sandbox");
            firefoxOptions.addArguments("--no-first-run");
            firefoxOptions.addArguments("--enable-extensions");
            firefoxOptions.addArguments("--homepage=about:blank");
            firefoxOptions.addArguments("--ignore-certificate-errors");


            //Selenium 3 y hasta 4.7.0
            firefoxOptions.addArguments("--remote-allow-origins=*");
            //firefoxOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            firefoxOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
            //firefoxOptions.setCapability(CapabilityType.VERSION, System.getProperty("browser.version", ""));
            //firefoxOptions.setCapability(CapabilityType.BROWSER_VERSION, System.getProperty("browser.version", ""));


            //firefoxOptions.setCapability("enableVNC", Boolean.parseBoolean(System.getProperty("enableVNC", "false")));

            //LoggingPreferences logPrefs = new LoggingPreferences();
            //logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
            //firefoxOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

//            return new FirefoxDriver(firefoxOptions);
            return WebDriverManager.firefoxdriver().capabilities(firefoxOptions).create();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DriverInitializationException("Error al inicializar el controlador de Firefox.");
        }


    }

//    public static WebDriver createOperaDriver(){
//
//        try {
////        String driver_path = System.getProperty("user.dir") + "/src/main/resources/webdrivers/"+getConfigWeb(WebConfigJson.OPERA);
////        System.setProperty("webdriver.gecko.driver", driver_path);
//
//            WebDriverManager.operadriver().setup();
//
//            OperaOptions operaOptions = new OperaOptions();
//            operaOptions.addArguments("--no-sandbox");
//            operaOptions.addArguments("--no-first-run");
//            operaOptions.addArguments("--enable-extensions");
//            operaOptions.addArguments("--homepage=about:blank");
//            operaOptions.addArguments("--ignore-certificate-errors");
//            operaOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
//            operaOptions.setCapability(CapabilityType.VERSION, System.getProperty("browser.version", ""));
//            operaOptions.setCapability("enableVNC", Boolean.parseBoolean(System.getProperty("enableVNC", "false")));
//            //operaOptions.setHeadless(HEADLESS);
//
//            LoggingPreferences logPrefs = new LoggingPreferences();
//            logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
//            operaOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
//
////            return new OperaDriver(operaOptions);
//            return WebDriverManager.operadriver().capabilities(operaOptions).create();
//        } catch (Exception e) {
//            throw new DriverInitializationException("Error al inicializar el controlador de Opera.");
//        }
//
//    }

    public static WebDriver createEdgeDriver() {

        try {
//        String driver_path = System.getProperty("user.dir") + "/src/main/resources/webdrivers/"+getConfigWeb(WebConfigJson.EDGE);
//        System.setProperty("webdriver.edge.driver", driver_path);

            WebDriverManager.edgedriver().setup();

            EdgeOptions options = new EdgeOptions();

//            return new EdgeDriver(options);
            return WebDriverManager.edgedriver().capabilities(options).create();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DriverInitializationException("Error al inicializar el controlador de Edge.");
        }

    }


}
