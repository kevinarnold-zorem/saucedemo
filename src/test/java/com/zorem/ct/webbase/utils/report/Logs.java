package com.zorem.ct.webbase.utils.report;

import com.zorem.ct.webbase.constants.FrameworkConstants;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logs {
    static {

        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd.MMM.yyyy_hh.mm.ss");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat monthFormat =  new SimpleDateFormat("MMM-yyyy");

        System.setProperty("current.month", monthFormat.format(new Date()));
        System.setProperty("current.date", dateFormat.format(new Date()));
        System.setProperty("current.date.time", dateTimeFormat.format(new Date()));
        System.setProperty("logPath", FrameworkConstants.getPathLog());
    }

    static Logger Log = Logger.getLogger(Logs.class.getName());


    public static void INFO(String Message) {

        DOMConfigurator.configure("log4j.xml");
        Log.info(Message);
    }

    public static void ERROR(String Message) {

        DOMConfigurator.configure("log4j.xml");
        Log.error(Message);
    }

}
