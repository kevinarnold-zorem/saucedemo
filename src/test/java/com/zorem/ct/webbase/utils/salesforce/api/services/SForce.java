package com.zorem.ct.webbase.utils.salesforce.api.services;

import com.sforce.soap.partner.DeleteResult;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.QueryResult;
import com.sforce.soap.partner.SaveResult;
import com.sforce.soap.partner.sobject.SObject;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
import com.sforce.ws.bind.XmlObject;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;


public class SForce {

    private static final ThreadLocal<PartnerConnection> threadLocalConnection = new ThreadLocal<PartnerConnection>();

    public static PartnerConnection getConnection() {
        return threadLocalConnection.get();
    }

    public static void setConnection(PartnerConnection connection) {
        if (Objects.nonNull(connection))
            threadLocalConnection.set(connection);
    }

    public static void openConnection(String URL, String email, String password) {
        setConnection(login(URL,email, password ));
    }
    public static void closeConnection() {

    }
    private static PartnerConnection login(String URL, String email, String password) {
        ConnectorConfig config = new ConnectorConfig();

        config.setAuthEndpoint(URL);
        config.setUsername(email);
        config.setPassword(password);

        PartnerConnection connection = null;
        try {
            connection = new PartnerConnection(config);
        } catch (ConnectionException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static SaveResult[] createObject(SObject newObject) {
        SObject[] newArrayObjects = new SObject[]{newObject};
        SaveResult[] results;
        try {
            results = getConnection().create(newArrayObjects);
        } catch (ConnectionException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    public static SObject findObjectByQuery(String query) {
        QueryResult queryResult = null;
        try {
            queryResult = getConnection().query(query);
        } catch (ConnectionException e) {
            throw new RuntimeException(e);
        }
        SObject[] dataResults = queryResult.getRecords();
        //System.out.println("Size results: " + dataResults.length);
        return dataResults[0];
    }

    public static SObject[] findObjectsByQuery(String query) {
        QueryResult queryResult = null;
        try {
            queryResult = getConnection().query(query);
        } catch (ConnectionException e) {
            throw new RuntimeException(e);
        }
        SObject[] dataResults = queryResult.getRecords();
        System.out.println("Size results: " + dataResults.length);
        return dataResults;
    }

    public static SaveResult[] updateSObject(SObject object) {
        try {
            SaveResult[] saveResults = getConnection().update(new SObject[]{object});
            return saveResults;
        } catch (ConnectionException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeObjectById(String idObject) {
        DeleteResult[] deleteResult = null;
        try {
            deleteResult = getConnection().delete(new String[]{idObject});
        } catch (ConnectionException e) {
            throw new RuntimeException(e);
        }
        Arrays.stream(deleteResult).forEach(System.out::println);
    }

    public static void printSObject(SObject sObject) {
        StringBuilder buf = new StringBuilder();
        buf.append(sObject.getType()).append(" ").append(sObject.getId()).append("\n");
        Iterator<XmlObject> fields = sObject.getChildren();
        while (fields.hasNext()) {
            XmlObject field = fields.next();
            buf.append("\t").append(field.getName().getLocalPart()).append("=").append(field.getValue()).append("\n");
        }
        System.out.println(buf.toString());
    }
}
