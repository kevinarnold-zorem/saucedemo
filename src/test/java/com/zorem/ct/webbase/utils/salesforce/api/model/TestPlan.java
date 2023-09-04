package com.zorem.ct.webbase.utils.salesforce.api.model;

public class TestPlan {
    private String Id;
    private String Name;
    private String Number__c;

    private String Test_Plan_Description__c;

    public TestPlan(String id, String name, String number__c, String test_Plan_Description__c) {
        Id = id;
        Name = name;
        Number__c = number__c;
        Test_Plan_Description__c = test_Plan_Description__c;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getNumber__c() {
        return Number__c;
    }

    public String getTest_Plan_Description__c() {
        return Test_Plan_Description__c;
    }
}
