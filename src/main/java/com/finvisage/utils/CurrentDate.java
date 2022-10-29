package com.finvisage.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class CurrentDate {
    private CurrentDate() {

    }

    public static String getCurrentDate() {
        DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        Date dt = new Date();
		return dateformat.format(dt);
    }

}
