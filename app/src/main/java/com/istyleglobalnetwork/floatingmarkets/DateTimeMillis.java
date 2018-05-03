package com.istyleglobalnetwork.floatingmarkets;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

    static public String MillisToDateCon(String number) {
        if (number != null && !number.equals("")) {
            long millis = Long.parseLong(number);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(millis);
            return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE);
        }
        return "";
    }

    static public String DateToString(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.YEAR);

    }

    static public List<Date> getRangeDates(String dateString1, String dateString2) {
        ArrayList<Date> dates = new ArrayList<Date>();
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = null;
        Date date2 = null;

        try {
            date1 = df1.parse(dateString1);
            date2 = df1.parse(dateString2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);


        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        while (!cal1.after(cal2)) {
            dates.add(cal1.getTime());
            cal1.add(Calendar.DATE, 1);
        }
        return dates;
    }

    static public Boolean checkRangeDates(String dateString1, String dateString2, Date item) {
        ArrayList<Date> dates = new ArrayList<Date>();
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = null;
        Date date2 = null;

        try {
            date1 = df1.parse(dateString1);
            date2 = df1.parse(dateString2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);


        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        while (!cal1.after(cal2)) {
//            Log.d("checkRange 1", "===== " + (cal1.getTime()));
//            Log.d("checkRange 2", "===== " + (item));
            if (cal1.getTime().equals(item)){
                return true;
            }
//            dates.add(cal1.getTime());
            cal1.add(Calendar.DATE, 1);
        }
        return false;
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

    static public String[] getDateWeek() {
        String[] date = new String[15];

        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 15; i++) {
            String day = calendar.get(Calendar.DATE) + "";
            String month = (calendar.get(Calendar.MONTH) + 1) + "";
            String year = calendar.get(Calendar.YEAR) + "";
            date[i] = day + "-" + month + "-" + year;

            calendar.add(Calendar.DATE, -1);
        }
        return date;

    }

    static public String[] getDateMonth() {
        String[] date = new String[15];

        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 15; i++) {
//            String day = calendar.get(Calendar.DATE) + "";
            String month = (calendar.get(Calendar.MONTH) + 1) + "";
            String year = calendar.get(Calendar.YEAR) + "";
            date[i] = month + "-" + year;

            calendar.add(Calendar.MONTH, -1);
        }
        return date;

    }

    static public String[] getDateQuatre() {
        String[] date = new String[15];

        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 15; i++) {
//            String day = calendar.get(Calendar.DATE) + "";
            int month = (calendar.get(Calendar.MONTH) + 1);
            String Quatre = "";
            if (month == 12 || (month >= 1 && month <= 2)) {
                Quatre = "Q1";
            } else if (month >= 3 && month <= 5) {
                Quatre = "Q2";
            } else if (month >= 6 && month <= 8) {
                Quatre = "Q3";
            } else if (month >= 9 && month <= 11) {
                Quatre = "Q4";
            }
            String year = calendar.get(Calendar.YEAR) + "";
            date[i] = Quatre + "-" + year;

            calendar.add(Calendar.MONTH, -4);
        }
        return date;

    }

    static public String[] getDateYear() {
        String[] date = new String[15];

        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 15; i++) {
//            String day = calendar.get(Calendar.DATE) + "";
//            String month = (calendar.get(Calendar.MONTH)+1) + "";
            String year = calendar.get(Calendar.YEAR) + "";
            date[i] = year;

            calendar.add(Calendar.YEAR, -1);
        }
        return date;

    }

    static public String getTimeNow() {
        String time = "";

        Calendar calendar = Calendar.getInstance();
        String minute = calendar.get(Calendar.MINUTE) + "";
        String hour = calendar.get(Calendar.HOUR_OF_DAY) + "";
        time = hour + ":" + minute;

        calendar.add(Calendar.DATE, -1);
        return time;

    }

    static public String getDateNow() {
        String date = "";

        Calendar calendar = Calendar.getInstance();
        String day = calendar.get(Calendar.DATE) + "";
        String month = (calendar.get(Calendar.MONTH) + 1) + "";
        String year = calendar.get(Calendar.YEAR) + "";
        date = day + "-" + month + "-" + year;

        return date;

    }

    static public String getTimeMillisNow() {
        String time = "";

        Calendar calendar = Calendar.getInstance();
        String minute = calendar.get(Calendar.MINUTE) + "";
        String hour = calendar.get(Calendar.HOUR_OF_DAY) + "";
        time = hour + ":" + minute;

        calendar.add(Calendar.DATE, -1);
        return TimeToMillis(time);

    }

    static public String getDateMillisNow() {
        String date = "";

        Calendar calendar = Calendar.getInstance();
        String day = calendar.get(Calendar.DATE) + "";
        String month = (calendar.get(Calendar.MONTH) + 1) + "";
        String year = calendar.get(Calendar.YEAR) + "";
        date = day + "-" + month + "-" + year;

        return DateToMillis(date);

    }


}
