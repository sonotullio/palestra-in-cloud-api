package it.sonotullio.rockymarciano.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static final String SYSTEM_FORMAT = "yyyy-MM-dd";

    public static Date parse(String date, String format) throws ParseException {

        Date dateDate = new SimpleDateFormat(format).parse(date);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1900 + dateDate.getYear());
        calendar.set(Calendar.MONTH, dateDate.getMonth());
        calendar.set(Calendar.DATE, dateDate.getDate());

        return calendar.getTime();
    }
}
