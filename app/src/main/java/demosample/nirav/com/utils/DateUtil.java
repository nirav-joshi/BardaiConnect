package demosample.nirav.com.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Dipali on 3/1/2018.
 */

public class DateUtil {
    public static final String DATE_FORMAT_1 = "yyyy-MM-dd";
    public static final String DATE_FORMAT_3 = "yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE_FORMAT_4 = "MM/dd/yyyy";
    public static final String DATE_FORMAT_5 = "dd/MM/yyyy";
    public static final String DATE_FORMAT_7 = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_FORMAT_9 = "dd MMM yyyy - hh:mm a";
    public static final String DATE_FORMAT_WITH_TIME = "HH:mm a, dd MMM yyyy";
    public static final String DATE_FORMAT_SEPARATED = "dd MMM yyyy";
    public static final String DATE_IN_HOME_PAGE_PANCHANG = "d MMMM, yyyy";
    public static final String TIME_FORMAT_SEPARATED = "hh:mm a";
    public static final String MONTH = "MM";
    public static final String DAY = "d";
    public static final String MONTH_NAME = "MMM";
    public static final String DAY_MONTH = "d MMM";
    public static final String TIME_FORMAT = "hhmmss";
    private static final String DATE_FORMAT_EVENT_DETAIL = "EEE, dd MMM yyyy";
    private static final String DATE_FORMAT_CALENDAR = "yyyyMMdd'T'hhmmss'Z'";

    private static final String TAG = DateUtil.class.getName();

    DateUtil() {
        throw new IllegalAccessError("Cannot access DateUtil");
    }

    public static String stringDateConversion(String strDate, String outputPattern) {
        SimpleDateFormat inputFormat = new SimpleDateFormat(DATE_FORMAT_SEPARATED, Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.getDefault());

        Date date;
        String str = null;

        try {
            date = inputFormat.parse(strDate);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            Log.e("string conversion", "error", e);
        }
        return str;
    }

    public static String convertToRecurrenceDate(String d) {
        DateFormat f = new SimpleDateFormat(DATE_FORMAT_CALENDAR, Locale.getDefault());
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_1, Locale.getDefault());
        Date date;
        String str = null;
        try {
            date = f.parse(d);
            str = dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String convertToRecurrenceTime(String d) {
        DateFormat f = new SimpleDateFormat(DATE_FORMAT_CALENDAR, Locale.getDefault());
        DateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT, Locale.getDefault());
        Date date;
        String str = null;
        try {
            date = f.parse(d);
            str = dateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String convertTimeForTask(String oldTime) {
        String outputPattern = "HH:mm:ss";
        SimpleDateFormat inputFormat = new SimpleDateFormat(TIME_FORMAT_SEPARATED, Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.getDefault());

        Date date;
        String str = null;
        try {
            date = inputFormat.parse(oldTime);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static boolean isTimeAfter(String startTime, String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT_SEPARATED, Locale.getDefault());
        Date inTime, outTime;
        try {
            inTime = sdf.parse(startTime);
            outTime = sdf.parse(endTime);
            return !outTime.before(inTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String convertToServiceDate(String strDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat(DATE_FORMAT_4, Locale.getDefault());
        SimpleDateFormat outputFormat = new SimpleDateFormat(DATE_FORMAT_3, Locale.getDefault());

        Date date;
        String str = null;

        try {
            date = inputFormat.parse(strDate);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            Log.e("string conversion", "error", e);
        }
        return str;
    }


    public static Double getTimezoneOffset() {
 /*       try {
            Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"),
                    Locale.getDefault());
            Date currentLocalTime = calendar.getTime();
            DateFormat date = new SimpleDateFormat("ZZZZZ");
            String localTime = date.format(currentLocalTime);
            localTime =localTime.replace(":",".");
            return Double.parseDouble(localTime);
        }catch (Exception e)
        {
            Log.e("Timezone",e.getMessage());
            return 0.0;
        }*/

        TimeZone tz = TimeZone.getDefault();
        Date now = new Date();
//Import part : x.0 for double number
        return tz.getOffset(now.getTime()) / 3600000.0;
        //String m2tTimeZoneIs = Double.parseDouble(offsetFromUtc);

    }

    public static String getPanchangDate(Date date) {
        SimpleDateFormat simpleDate = new SimpleDateFormat(DateUtil.DATE_FORMAT_1, Locale.getDefault());
        if (date == null)
            date = new Date();

        return simpleDate.format(date);
    }

    public static String getCurrentServiceDate() {
        SimpleDateFormat outputFormat = new SimpleDateFormat(DATE_FORMAT_3, Locale.getDefault());
        String str = null;

        try {
            str = outputFormat.format(new Date());
        } catch (Exception e) {
            Log.e("string conversion", "error", e);
        }
        return str;
    }


    public static String convertDate(String oldDate) {
        SimpleDateFormat source = new SimpleDateFormat(DATE_FORMAT_3, Locale.getDefault());
        SimpleDateFormat target = new SimpleDateFormat(DATE_FORMAT_SEPARATED, Locale.getDefault());
        try {
            if (oldDate != null) {
                Date newDate = source.parse(oldDate);
                return target.format(newDate);
            }
        } catch (ParseException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    public static boolean checkDates(String d1, String d2) {
        boolean b = false;
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_SEPARATED, Locale.getDefault());
        try {
            //If start date is before end date
            b = dateFormat.parse(d1).before(dateFormat.parse(d2)) || dateFormat.parse(d1).equals(dateFormat.parse(d2));
            //If two dates are equal
            //If start date is after the end date
        } catch (ParseException e) {
            Log.e("on checkDates", "Date Utilization", e);
        }
        return b;
    }

    public static String convertTime(String oldTime) {
        SimpleDateFormat source = new SimpleDateFormat(DATE_FORMAT_3, Locale.getDefault());
        SimpleDateFormat target = new SimpleDateFormat(TIME_FORMAT_SEPARATED, Locale.getDefault());
        try {
            Date newDate = source.parse(oldTime);
            return target.format(newDate);
        } catch (ParseException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    public static String convertDateInLifePlanFormat(String date) {
        SimpleDateFormat source = new SimpleDateFormat(DATE_FORMAT_3, Locale.getDefault());
        SimpleDateFormat target = new SimpleDateFormat(DATE_FORMAT_SEPARATED, Locale.getDefault());
        try {
            Date newDate = source.parse(date);
            return target.format(newDate);
        } catch (ParseException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    public static String convertDateInUTCFormat(String date, String dateFormate) {
        SimpleDateFormat source = new SimpleDateFormat(DATE_FORMAT_3, Locale.getDefault());
        source.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat target = new SimpleDateFormat(dateFormate, Locale.getDefault());
        target.setTimeZone(TimeZone.getDefault());
        try {
            Date newDate = source.parse(date);
            return target.format(newDate);
        } catch (ParseException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    public static Date convertDateInUTCFormattoDate(String date, String dateFormate) {
        SimpleDateFormat source = new SimpleDateFormat(DATE_FORMAT_3, Locale.getDefault());
        source.setTimeZone(TimeZone.getTimeZone("UTC"));
        SimpleDateFormat target = new SimpleDateFormat(dateFormate, Locale.getDefault());
        target.setTimeZone(TimeZone.getDefault());
        try {
            Date newDate = source.parse(date);
            return target.parse(target.format(newDate));
        } catch (ParseException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    public static String convertDateInTimingFormat(String date) {
        SimpleDateFormat source = new SimpleDateFormat(DATE_FORMAT_SEPARATED, Locale.getDefault());
        SimpleDateFormat target = new SimpleDateFormat(DATE_FORMAT_4, Locale.getDefault());
        try {
            Date newDate = source.parse(date);
            return target.format(newDate);
        } catch (ParseException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    public static Date convertstringToDate(String date, String formate) {
        String combineDate = stringDateConversion(date, formate);
        SimpleDateFormat target = new SimpleDateFormat(formate, Locale.getDefault());
        try {
            return target.parse(combineDate);
        } catch (ParseException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    public static Date combineDateandTime(String date, String time) {
        String datearray[] = date.split("T");
        String combineDate = datearray[0] + "T" + time;
        Date myDate = null;
        try {
            myDate = new SimpleDateFormat(DATE_FORMAT_3, Locale.getDefault()).parse(combineDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return myDate;
    }

    public static boolean compareDateBetween(String strminDate, String strmaxDate) {
        SimpleDateFormat source = new SimpleDateFormat(TIME_FORMAT_SEPARATED, Locale.getDefault());
        try {
            if (strminDate != null) {
                Date minDate = source.parse(strminDate);
                Date maxDate = source.parse(strmaxDate);
                Date currentDate = source.parse(source.format(new Date()));
                return currentDate.after(minDate) && currentDate.before(maxDate);
            }
        } catch (ParseException e) {
            Log.e(TAG, e.getMessage());
        }
        return false;
    }

    public static boolean compareDateAfter(Date givenDate) {
        SimpleDateFormat source = new SimpleDateFormat(DATE_FORMAT_7, Locale.getDefault());
        try {
            if (givenDate != null) {
                Date minDate = source.parse(source.format(givenDate));
                Date currentDate = source.parse(source.format(new Date()));
                return minDate.after(currentDate);
            }
        } catch (ParseException e) {
            Log.e(TAG, e.getMessage());
        }
        return false;
    }

    /**
     * Pass time in millis with expected output format from DateFormat and will return output as string
     *
     * @param timeInMillis  as long : time to be converted from
     * @param outDateFormat as String : format from which timeInMillis should be get converted
     * @return string as formatted date/time.
     */
    public static String convertDateTimeFromInput(long timeInMillis, String outDateFormat) {
        SimpleDateFormat target = new SimpleDateFormat(outDateFormat, Locale.getDefault());
        target.setTimeZone(TimeZone.getDefault());
        Date newDate = new Date(timeInMillis);
        return target.format(newDate);
    }
}
