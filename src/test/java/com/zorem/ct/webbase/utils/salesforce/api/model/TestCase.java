package com.zorem.ct.webbase.utils.salesforce.api.model;

public class TestCase {
    private String Id;
    private String Test_Case_ID__c;
    private String Test_Plan__c;
    private String Name;

    public TestCase(String id, String test_Case_ID__c, String test_Plan__c, String name) {
        Id = id;
        Test_Case_ID__c = test_Case_ID__c;
        Test_Plan__c = test_Plan__c;
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public String getTest_Case_ID__c() {
        return Test_Case_ID__c;
    }

    public String getTest_Plan__c() {
        return Test_Plan__c;
    }

    public String getName() {
        return Name;
    }
}
