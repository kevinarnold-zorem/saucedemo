package com.zorem.ct.webbase.utils.salesforce.api.utils;

import com.zorem.ct.webbase.utils.salesforce.api.model.TestCase;
import com.zorem.ct.webbase.utils.salesforce.api.model.TestPlan;
import com.sforce.soap.partner.sobject.SObject;

public class Parser {
    public static TestPlan SObcjetToTestPlan(SObject sObject){
        return new TestPlan(sObject.getId(),
                (String) sObject.getField("Name"),
                (String) sObject.getField("Number__c"),
                (String) sObject.getField("Test_Plan_Description__c")
                );
    }
    public static TestCase SObcjetToTestCase(SObject sObject){
        return new TestCase(sObject.getId(),
                (String) sObject.getField("Test_Case_ID__c"),
                (String) sObject.getField("Test_Plan__c"),
                (String) sObject.getField("Name")
        );
    }
}
