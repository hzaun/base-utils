package com.nuzharukiya.hzaun_app_base;

import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Nuzha Rukiya on 18/01/24.
 */

public class TimeUtils {

    private static final SimpleDateFormat SDF_TYPE_1 = new SimpleDateFormat("dd MMM yyyy", Locale.US);
    private static final SimpleDateFormat SDF_TYPE_2 = new SimpleDateFormat("hh:mm aa", Locale.US);
    private static final SimpleDateFormat SDF_TYPE_3 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);

    private static final SimpleDateFormat SDF_24_HOURS = new SimpleDateFormat("HH:mm", Locale.US);
    private static final SimpleDateFormat SDF_12_HOURS = new SimpleDateFormat("hh:mm a", Locale.US);
    private static final SimpleDateFormat SDF_DATE_YYMMDD = new SimpleDateFormat("yyMMdd", Locale.US);
    private static final SimpleDateFormat SDF_DATE_PRETTY = new SimpleDateFormat("dd MMM, YYYY", Locale.US);

    public TimeUtils() {
    }

    /**
     * Takes Calendar type input
     * Returns a string of type 1
     *
     * @param cal
     * @return TYPE 1 string
     */
    public String formatCalDate(Calendar cal) {
        if (cal != null) return SDF_TYPE_1.format(cal.getTime());
        return "";
    }

    /**
     * Converts date from SDF_TYPE_1 format to milliseconds
     * Used for comparision
     *
     * @param date
     * @return
     */
    public long parseDate_stringToMillis(String date) {
        try {
            Date _date = SDF_TYPE_1.parse(date);
            return _date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long parseTime_stringToMillis(String time) {
        if (!time.equals("")) {
            try {
                Date _date = SDF_TYPE_2.parse(time);
                return _date.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public Date parseDate_stringToDate(String date) {
        try {
            return SDF_TYPE_1.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public Date parseDate_stringToTime(String time) {
        try {
            return SDF_TYPE_2.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    /**
     * Takes Calendar type input
     * Returns a string of type 1
     *
     * @param cal
     * @return TYPE 1 string
     */
    public String formatCalTime(Calendar cal) {
        return SDF_TYPE_2.format(cal.getTime());
    }

    public String formatCalTimeStamp(Calendar cal) {
        return SDF_TYPE_3.format(cal.getTime());
    }

    public String parseEDMTime(String edmTime) {

        if (edmTime.equals("P0Y")) return "12:00 AM";

        try {
            String beyondPT = edmTime.split("PT")[1]; // Always has PT
            String h = "0", m = "00";
            if (beyondPT.contains("H")) {
                h = beyondPT.split("H")[0];
                beyondPT = (beyondPT.split("H")).length > 1 ? beyondPT.split("H")[1] : null;
            }

//        To convert to 12 hour format
            int hour = Integer.parseInt(h);
            String xm = " AM";
            if (hour > 12) {
                hour -= 12;
                xm = " PM";
            } else if (hour == 12) {
                xm = " PM";
            }

            if (beyondPT == null) {
                return (leadingZeros(hour).equals("00") ? "12" : leadingZeros(hour)) + ":" + "00" + xm;
            } else if (beyondPT.contains("M")) {
                m = beyondPT.split("M")[0];
            }

            int min = Integer.parseInt(m);
            return (leadingZeros(hour).equals("00") ? "12" : leadingZeros(hour)) + ":" + leadingZeros(min) + xm;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-";
    }

    public String leadingZeros(int time) {
        String sTime = "";
        if (time < 10) {
            sTime = "0" + time;
        } else sTime += time;

        return sTime;
    }

    public Calendar parseDate_stringToCal(String date) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(SDF_TYPE_1.parse(date));
            return cal;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String prettyDate_context(String date) {

        String prettyDate = "";

        long then;
        try {
            Date parsedDate = SDF_TYPE_1.parse(date);

            then = parsedDate.getTime();

            if (DateUtils.isToday(then)) {
                prettyDate = "Today";
            } else {
                Calendar c1 = Calendar.getInstance();    // Today
                c1.add(Calendar.DAY_OF_YEAR, -1);        // Yesterday

                Calendar c2 = Calendar.getInstance();
                c2.setTime(parsedDate);                    // Card date

                if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
                        && c1.get(Calendar.DAY_OF_YEAR) == c2.get(Calendar.DAY_OF_YEAR)) {
                    prettyDate = "Yesterday";
                } else prettyDate = date;              // Else show date
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return prettyDate;
    }

    public boolean compareDates(Calendar curDate, Calendar prevDate) {
        if ((curDate.get(Calendar.YEAR) == prevDate.get(Calendar.YEAR)) &&
                (curDate.get(Calendar.MONTH) == prevDate.get(Calendar.MONTH)) &&
                (curDate.get(Calendar.DAY_OF_MONTH) == prevDate.get(Calendar.DAY_OF_MONTH))) {
            return true;
        }
        return false;
    }

    /**
     * Compares time
     * Only consideres hours and minutes
     *
     * @param rhsTime
     * @param lhsTime
     * @return
     */
    public int compareTimes(String rhsTime, String lhsTime) {
        Date rhsDate = parseDate_stringToTime(rhsTime);
        Date lhsDate = parseDate_stringToTime(lhsTime);

        if (rhsDate.getHours() == lhsDate.getHours()) {
            if (rhsDate.getMinutes() == lhsDate.getMinutes()) {
                return 0;
            } else return rhsDate.getMinutes() - lhsDate.getMinutes();
        } else {
            return rhsDate.getHours() - lhsDate.getHours();
        }
    }

    public String currentTime_String() {
        return SDF_TYPE_2.format(Calendar.getInstance().getTime());
    }

    public String currentDate_String() {
        return formatCalDate(Calendar.getInstance());
    }

    public String convertDate_simpleToDateTime(String simpleDate) {
        try {
            Date date = SDF_TYPE_1.parse(simpleDate);
            return SDF_TYPE_3.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String convert24To12(String timeIn24) {
        try {
            return SDF_12_HOURS.format(SDF_24_HOURS.parse(timeIn24));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String prettyDate(String dateInYYMMDD) {
        try {
            return SDF_DATE_PRETTY.format(SDF_DATE_YYMMDD.parse(dateInYYMMDD));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static long convert24ToMillis(String timeIn24) {
        try {
            return SDF_24_HOURS.parse(timeIn24).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
