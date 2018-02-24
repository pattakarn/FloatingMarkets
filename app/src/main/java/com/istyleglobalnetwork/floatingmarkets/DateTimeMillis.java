package com.istyleglobalnetwork.floatingmarkets;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sung on 24/2/2018 AD.
 */

public class DateTimeMillis {
    static public String DateToMillis(String date) {
        if (date != null & !date.equals("")) {
            String[] dayTemp = date.split("-");
            int year = Integer.parseInt(dayTemp[2]);
            int month = Integer.parseInt(dayTemp[1]) - 1;
            int day = Integer.parseInt(dayTemp[0]);

//        Log.d("DateToMillis", "^^^^^^^^^^^^^^^^^^^^  " + year + "-" + month + "-" + day);
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.set(Calendar.DATE, day);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.YEAR, year);
            Date dateResult = calendar.getTime();
//        Log.d("DateToMillis", "^^^^^^^^^^^^^^^^^^^^  " + dateResult.getTime() + " + " + dateResult.toString());
            return String.valueOf(dateResult.getTime());
        }
        return "";
    }

    static public String MillisToDate(String number) {
        if (number != null && !number.equals("")) {
            long millis = Long.parseLong(number);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(millis);
            return calendar.get(Calendar.DATE) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.YEAR);
        }
        return "";
    }

    static public String TimeToMillis(String time) {
        if (time != null & !time.equals("")) {
            String[] dayTemp = time.split(":");
            int minute = Integer.parseInt(dayTemp[1]);
            int hour = Integer.parseInt(dayTemp[0]);

//        Log.d("DateToMillis", "^^^^^^^^^^^^^^^^^^^^  " + year + "-" + month + "-" + day);
            Calendar calendar = Calendar.getInstance();
            calendar.clear();
            calendar.set(Calendar.HOUR, hour);
            calendar.set(Calendar.MINUTE, minute);
            Date dateResult = calendar.getTime();
//        Log.d("DateToMillis", "^^^^^^^^^^^^^^^^^^^^  " + dateResult.getTime() + " + " + dateResult.toString());
            return String.valueOf(dateResult.getTime());
        }
        return "";
    }

    static public String MillisToTime(String number) {
        if (number != null) {
            long millis = Long.parseLong(number);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(millis);

//        long hour = TimeUnit.MILLISECONDS.toHours(number);
//        long minute = TimeUnit.MILLISECONDS.toMinutes(number) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(number));

            return calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
        }
        return "";
    }
}
