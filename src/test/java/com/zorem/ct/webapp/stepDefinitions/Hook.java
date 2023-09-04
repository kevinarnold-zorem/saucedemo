package com.zorem.ct.webapp.stepDefinitions;

import com.zorem.ct.webapp.runner.TestRunnerWeb;
import com.zorem.ct.webbase.driver.ScenarioManager;
import com.zorem.ct.webbase.driver.manager.DriverManager;
import com.zorem.ct.webbase.utils.configloader.ReadProperties;
import io.cucumber.java.After;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Hook extends TestRunnerWeb {

    @BeforeStep(order = 0)
    public void beforeStep(Scenario sc){
        ReadProperties.setUpProperties();
        try {
            ScenarioManager.setScenario(sc);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        shotWhenFail(scenario);
    }

    private void shotWhenFail(Scenario scenario) {
        if (scenario.isFailed())
        {
            if (scenario == null)
                throw new IllegalArgumentException("No-scenario-declared");
            if (DriverManager.getWebDriver() == null)
                throw new IllegalArgumentException("WebDriver dosen't exist. Please, check the application properties file.");
            try {
                Thread.sleep(1000);
                byte[] screenshot = ((TakesScreenshot) DriverManager.getInstance().getWebDriver()).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/jpeg", "evidencia");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}