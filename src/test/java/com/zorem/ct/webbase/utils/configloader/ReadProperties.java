package com.zorem.ct.webbase.utils.configloader;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {
    private static final Properties appPropierties = new Properties();

    public static void setUpProperties(){
        try {
            appPropierties.load(new FileReader("src/test/resources/global.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String prop){
        if (System.getProperty(prop)!=null){
            return System.getProperty(prop);
        }
        return appPropierties.getProperty(prop);
    }

}
