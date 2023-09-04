package com.zorem.ct.webbase.driver.factory;

import com.zorem.ct.webbase.driver.Drivers;
import com.zorem.ct.webbase.driver.manager.DriverManager;
import com.zorem.ct.webbase.enums.WebPlatformName;
import com.zorem.ct.webbase.exceptions.DriverInitializationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DriverFactory {

    private static DriverFactory instance;

    public static DriverFactory getInstance(){
        if(instance == null){
            instance = new DriverFactory();
        }
        return instance;
    }


    public static void initializeDriverWeb(WebPlatformName Browser){
        WebDriver driver = null;
        try {
            switch (Browser) {
                case CHROME:
                    driver = Drivers.createChromeDriver();
                    break;
                case FIREFOX:
                    driver = Drivers.createFirefoxDriver();
                    break;
                case OPERA:
//                    driver = Drivers.createOperaDriver();
                    break;
                case EDGE:
                    driver = Drivers.createEdgeDriver();
                    break;
                default:
                    throw new DriverInitializationException("Browser name " + Browser + " is not found. Please check the browser name");
            }

        } catch (DriverInitializationException e){
            Assert.fail(e.getMessage());
        }

        DriverManager.getInstance().setWebDriver(driver);
    }

    public static void quitDriver() {
        if (Objects.nonNull(DriverManager.getInstance().getWebDriver())) {
            DriverManager.getInstance().getWebDriver().quit();
            DriverManager.unload();
        }
    }

}
