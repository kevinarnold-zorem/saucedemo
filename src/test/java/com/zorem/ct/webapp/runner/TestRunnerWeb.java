package com.zorem.ct.webapp.runner;

import com.zorem.ct.webbase.driver.ScenarioManager;
import com.zorem.ct.webbase.driver.factory.DriverFactory;

import com.zorem.ct.webbase.enums.WebPlatformName;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.TestNGCucumberRunner;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

@CucumberOptions(
        features = {"src/test/resources/features/Flow 1.feature"},
        glue = {"com/zorem/ct/webapp/stepDefinitions"},
        tags = "@Caso-1",
        plugin = "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
)
public class TestRunnerWeb extends AbstractTestNGCucumberTests {

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpCucumber() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }
    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUpClass(String browser, final ITestContext testContext) {
        DriverFactory.getInstance().initializeDriverWeb(WebPlatformName.valueOf(browser.toUpperCase()));
        ScenarioManager.setStepIndex(0);
        ScenarioManager.resetAllStepNames();
    }

    @AfterMethod
    protected void tearDown(ITestResult result) {
        DriverFactory.quitDriver();
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
    }

}
