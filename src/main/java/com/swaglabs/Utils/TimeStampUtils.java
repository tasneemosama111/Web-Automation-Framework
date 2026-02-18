package com.swaglabs.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStampUtils {

public static String getTimestamp(){
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    return formatter.format(date);
}












}
