package com.zorem.ct.webbase.driver;

import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCase;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ScenarioManager {
    static int stepIndex = 0;
    private static final ThreadLocal<String> threadLocalStepName = new ThreadLocal<>();
    private static final ThreadLocal<Scenario> threadLocalScenario = new ThreadLocal<>();

    private static final ThreadLocal<String> threadLocalAllStepNames = new ThreadLocal<>();

    public static void setScenario(Scenario sc) throws NoSuchFieldException, IllegalAccessException {
        if (Objects.nonNull(sc)) {
            threadLocalScenario.set(sc);
            setStepName(getStepText(sc));
        }
    }
    private static void setStepName(String stepName) {
        if (Objects.nonNull(stepName))
            threadLocalStepName.set(stepName);
            setAllStepNames(stepName);
    }

    private static void setAllStepNames(String stepName){
        String step = (getAllStepNames()!=null)?getAllStepNames():"";
        step = step + "Step: "+ stepName + "\n";
        if (Objects.nonNull(stepName))
            threadLocalAllStepNames.set(step);
    }

    public static void resetAllStepNames(){
        threadLocalAllStepNames.set("");
    }

    public static String getStepName() {
        return threadLocalStepName.get();
    }

    public static String getAllStepNames() {
        return threadLocalAllStepNames.get();
    }
    public static String getNameScenario() {
        return threadLocalScenario.get().getName();
    }

    public static String getStepText(Scenario scenario) throws NoSuchFieldException, IllegalAccessException{
        String  currentStepDescr = null;

        int currentStepDefIndex = stepIndex++;

        Field f = scenario.getClass().getDeclaredField("delegate");
        f.setAccessible(true);
        TestCaseState tcs = (TestCaseState) f.get(scenario);

        Field f2 = tcs.getClass().getDeclaredField("testCase");
        f2.setAccessible(true);
        TestCase r = (TestCase) f2.get(tcs);

        List<PickleStepTestStep> stepDefs = r.getTestSteps()
                .stream()
                .filter(x -> x instanceof PickleStepTestStep)
                .map(x -> (PickleStepTestStep) x)
                .collect(Collectors.toList());

        PickleStepTestStep currentStepDef = stepDefs
                .get(currentStepDefIndex);
        currentStepDescr = currentStepDef.getStep().getText();
        currentStepDefIndex += 1;

        return currentStepDescr ;
    }

    public static void setStepIndex(int stepIndex) {
        ScenarioManager.stepIndex = stepIndex;
    }

}
