package com.zorem.ct.webbase.utils.date;

import io.cucumber.datatable.DataTable;
import org.apache.commons.lang3.NotImplementedException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilMobile {

    public static String getValueFromDataTable(DataTable dataTable, String title) {
        if (dataTable.getTableConverter().getClass().getSimpleName().equalsIgnoreCase("DataTableTypeRegistryTableConverter"))
            return (String) dataTable.asMaps(String.class, String.class).get(0).get(title);
        else if (dataTable.getTableConverter().getClass().getSimpleName().equalsIgnoreCase("NoConverterDefined"))
            return dataTable.asMaps().get(0).get(title);
        else
            throw new NotImplementedException("DataTable getTableConverter() not implemented.");
    }
    public static String getDate(){
        Date fecha = new Date(Calendar.getInstance().getTimeInMillis());
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy hh:mm a");
        String fechaTexto = formatter.format(fecha);
        return fechaTexto;
    }
}
