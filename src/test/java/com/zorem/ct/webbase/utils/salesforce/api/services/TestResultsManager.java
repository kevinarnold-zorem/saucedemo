package com.zorem.ct.webbase.utils.salesforce.api.services;


import com.zorem.ct.webbase.utils.salesforce.api.model.TestCase;
import com.zorem.ct.webbase.utils.salesforce.api.model.TestPlan;
import com.zorem.ct.webbase.utils.salesforce.api.utils.Parser;
import com.sforce.soap.partner.SaveResult;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.soap.partner.Error;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TestResultsManager {

    public static boolean saveTestResult(String testPlanName, String testCaseName, String outcome, String notes) {
        boolean saved = false;
        TestCase testCase = getTestCaseByName(testPlanName, testCaseName);
        createTestResult(testCase.getId(), outcome, notes);
        return saved;
    }

    private static TestPlan getTestPlanByName(String testPlanName) {
        String query = "select Id, Name, Number__c FROM Test_Plan__c WHERE Name = '" + testPlanName + "'";
        return Parser.SObcjetToTestPlan(SForce.findObjectByQuery(query));
    }

    private static void getTestCaseByName(String testCaseName) {
        String query = "select Id, Test_Case_ID__c, Test_Plan__c, Name FROM Test_Case__c WHERE Name = '" + testCaseName + "'";
        SForce.printSObject(SForce.findObjectByQuery(query));
    }

    private static TestCase getTestCaseByName(String testPlanName, String testCaseName) {
        TestPlan testPlan = getTestPlanByName(testPlanName);
        String query = "select Id, Test_Case_ID__c, Test_Plan__c, Name FROM Test_Case__c WHERE Name = '" + testCaseName + "' AND Test_Plan__c = '" + testPlan.getId() + "'";
        return Parser.SObcjetToTestCase(SForce.findObjectByQuery(query));
    }


    private static SObject createTestResult( String testCaseId, String verdict, String notes) {
        String result = null;
        SObject newTestResult = new SObject();
        newTestResult.setType("Test_Run__c");
        newTestResult.setField("Test_Case__c", testCaseId);
        newTestResult.setField("Verdict__c", verdict);
        newTestResult.setField("Notes__c", notes);

        SaveResult[] results = SForce.createObject(newTestResult);

        for (int j = 0; j < results.length; j++) {
            if (results[j].isSuccess()) {
                result = results[j].getId();
                System.out.println("Se creo un Test_Case con la Id: " + result);
            } else {
                for (int i = 0; i < results[j].getErrors().length; i++) {
                    Error err = results[j].getErrors()[i];
                    System.out.println("Se encontraron errores en crear el TestCase " + j);
                    System.out.println("Codigo de Error: " + err.getStatusCode().toString());
                    System.out.println("Mensaje de Error: " + err.getMessage());
                }
            }
        }
        return newTestResult;
    }


    /**
     * Lista todos los Test Plan
     */
    private static List<TestPlan> listTestPlans() {
        List<TestPlan> listTestPlans = new ArrayList<>();
        String query = "select Id, Name, Test_Plan_Description__c FROM Test_Plan__c";
        SObject[] dataResults = SForce.findObjectsByQuery(query);
        Arrays.stream(dataResults).forEach((p) -> {
            listTestPlans.add(Parser.SObcjetToTestPlan(p));
        });
        return listTestPlans;
    }

    private static void getTestCasesByPlanName(String testPlanName) {
        TestPlan testPlan = getTestPlanByName(testPlanName);
        String query = "select Id, Test_Case_ID__c, Test_Plan__c, Name FROM Test_Case__c WHERE Test_Plan__c = '" + testPlan.getId() + "'";
        SObject[] dataResults = SForce.findObjectsByQuery(query);
        Arrays.stream(dataResults).forEach((p) -> {
            SForce.printSObject(p);
        });
    }
}
