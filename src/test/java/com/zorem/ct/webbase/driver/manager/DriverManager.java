package com.zorem.ct.webbase.driver.manager;

import org.openqa.selenium.WebDriver;

import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class DriverManager {
    private static final ThreadLocal<WebDriver> threadLocalWebDriver = new ThreadLocal<>();

    private static DriverManager instance;

    public static DriverManager getInstance(){
        if(instance == null){
            instance = new DriverManager();
        }
        return instance;
    }

    public static WebDriver getWebDriver() {
        return threadLocalWebDriver.get();
    }

    public static void setWebDriver(WebDriver driver) {
        if (Objects.nonNull(driver))
            threadLocalWebDriver.set(driver);
    }

    public static void unload() {
        threadLocalWebDriver.remove();
    }

    public static WebDriver getChildWindow(WebDriver driver){
        WebDriver childDriver = null;
        String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        Iterator<String> iterator = allWindowHandles.iterator();

        // Here we will check if child window has other child windows and will fetch the heading of the child window
        while (iterator.hasNext()) {
            String ChildWindow = iterator.next();
            if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
                childDriver = driver.switchTo().window(ChildWindow);
            }
        }
        return childDriver;
    }
}
