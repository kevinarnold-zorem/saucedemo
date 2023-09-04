package com.zorem.ct.webbase.utils.report;

import com.zorem.ct.webbase.driver.manager.DriverManager;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ManageScreenShoot {

    static int stepIndex = 0;

    public static void takeScreenShoot(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ByteArrayInputStream screenshotInputStream = null;

        if(!Objects.isNull(DriverManager.getWebDriver())){
            screenshotInputStream =  new ByteArrayInputStream(((TakesScreenshot) DriverManager.getInstance().getWebDriver()).getScreenshotAs(OutputType.BYTES));
        }
        Allure.addAttachment("evidencia", screenshotInputStream);
    }

    public static String getFileNameScreenshoot(){
        if (DriverManager.getWebDriver() == null)
            throw new IllegalArgumentException("La sesión del driver no existe");

        String filename;
        try {
            filename = "target\\screenshoots\\evidencia.png";
            File screenshotFileStream = ((TakesScreenshot) DriverManager.getWebDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFileStream, new File(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return filename;
    }

}