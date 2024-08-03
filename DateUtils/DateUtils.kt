import android.text.format.DateUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.coroutineContext

/**
 * Provides helper methods for date utilities.
 * 
 * @author [Your Name]
 */
public class DateUtils {

    /**
     * Converts ISO date string to UTC timezone equivalent.
     * 
     * @param dateAndTime ISO formatted time string.
     * @return The UTC formatted date string.
     */
    public static String getUtcTime(String dateAndTime) {
        Date d = parseDate(dateAndTime);

        String format = "yyyy-MM-dd'T'HH:mm:ss'Z'";
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.getDefault());

        // Convert Local Time to UTC
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(d);
    }

    /**
     * Parses date string and returns a {@link java.util.Date} object.
     * 
     * @param date ISO formatted date string.
     * @return The parsed Date object.
     */
    public static Date parseDate(String date) {
        if (date == null) {
            return null;
        }

        StringBuffer sbDate = new StringBuffer();
        sbDate.append(date);
        String newDate = null;
        Date dateDT = null;

        try {
            newDate = sbDate.substring(0, 19).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        String rDate = newDate.replace("T", " ");
        String nDate = rDate.replaceAll("-", "/");

        try {
            dateDT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault()).parse(nDate);
            // Log.v( TAG, "#parseDate dateDT: " + dateDT );
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dateDT;
    }

    /**
     * Converts UTC time formatted as ISO to device local time.
     * 
     * @param utcDate UTC formatted date string.
     * @param format  The format of the date string.
     * @return The local time Date object.
     * @throws Exception If the parsing fails.
     */
    public static Date toLocalTime(String utcDate, SimpleDateFormat format) throws Exception {
        // create a new Date object using
        // the timezone of the specified city
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date localDate = format.parse(utcDate);

        format.setTimeZone(TimeZone.getDefault());
        String dateFormateInUTC = format.format(localDate);

        return format.parse(dateFormateInUTC);
    }

    /**
     * Returns abbreviated (3 letters) day of the week.
     * 
     * @param date ISO format date.
     * @return The abbreviated day of the week (e.g. "Mon", "Tue", etc.).
     */
    public static String getDayOfWeekAbbreviated(String date) {
        Date dateDT = parseDate(date);

        if (dateDT == null) {
            return null;
        }

        // Get current date
        Calendar c = Calendar.getInstance();
        // it is very important to
        // set the date of
        // the calendar.
        c.setTime(dateDT);
        int day = c.get(Calendar.DAY_OF_WEEK);

        String dayStr = null;

        switch (day) {
            case Calendar.SUNDAY:
                dayStr = "Sun";
                break;

            case Calendar.MONDAY:
                dayStr = "Mon";
                break;

            case Calendar.TUESDAY:
                dayStr = "Tue";
                break;

            case Calendar.WEDNESDAY:
                dayStr = "Wed";
                break;

            case Calendar.THURSDAY:
                dayStr = "Thu";
                break;

            case Calendar.FRIDAY:
                dayStr = "Fri";
                break;

            case Calendar.SATURDAY:
                dayStr = "Sat";
                break;
        }

        return dayStr;
    }

    /**
     * Gets the name of the month from the given date.
     * 
     * @param date ISO format date.
     * @return The full name of the month (e.g. "January", "February", etc.).
     */
    public static String getMonth(String date) {
        Date dateDT = parseDate(date);

        if (dateDT == null) {
            return null;
        }

        // Get current date
        Calendar c = Calendar.getInstance();
        // it is very important to
        // set the date of
        // the calendar.
        c.setTime(dateDT);
        int day = c.get(Calendar.MONTH);

        String dayStr = null;

        switch (day) {
            case Calendar.JANUARY:
                dayStr = "January";
                break;

            case Calendar.FEBRUARY:
                dayStr = "February";
                break;

            case Calendar.MARCH:
                dayStr = "March";
                break;

            case Calendar.APRIL:
                dayStr = "April";
                break;

            case Calendar.MAY:
                dayStr = "May";
                break;

            case Calendar.JUNE:
                dayStr = "June";
                break;

            case Calendar.JULY:
                dayStr = "July";
                break;

            case Calendar.AUGUST:
                dayStr = "August";
                break;

            case Calendar.SEPTEMBER:
                dayStr = "September";
                break;

            case Calendar.OCTOBER:
                dayStr = "October";
                break;

            case Calendar.NOVEMBER:
                dayStr = "November";
                break;

            case Calendar.DECEMBER:
                dayStr = "December";
                break;
        }

        return dayStr;
    }

    /**
     * Gets abbreviated name of the month from the given date.
     * 
     * @param date ISO format date.
     * @return The abbreviated name of the month (e.g. "Jan", "Feb", etc.).
     */
    public static String getMonthAbbreviated(String date) {
        Date dateDT = parseDate(date);

        if (dateDT == null) {
            return null;
        }

        // Get current date
        Calendar c = Calendar.getInstance();
        // it is very important to
        // set the date of
        // the calendar.
        c.setTime(dateDT);
        int day = c.get(Calendar.MONTH);

        String dayStr = null;

        switch (day) {
            case Calendar.JANUARY:
                dayStr = "Jan";
                break;

            case Calendar.FEBRUARY:
                dayStr = "Feb";
                break;

            case Calendar.MARCH:
                dayStr = "Mar";
                break;

            case Calendar.APRIL:
                dayStr = "Apr";
                break;

            case Calendar.MAY:
                dayStr = "May";
                break;

            case Calendar.JUNE:
                dayStr = "Jun";
                break;

            case Calendar.JULY:
                dayStr = "Jul";
                break;

            case Calendar.AUGUST:
                dayStr = "Aug";
                break;

            case Calendar.SEPTEMBER:
                dayStr = "Sep";
                break;

            case Calendar.OCTOBER:
                dayStr = "Oct";
                break;

            case Calendar.NOVEMBER:
                dayStr = "Nov";
                break;

            case Calendar.DECEMBER:
                dayStr = "Dec";
                break;
        }

        return dayStr;
    }

    /**
     * Gets a string TimeStamp phrase like 5 mins ago, yesterday, 3 days ago.
     * 
     * @param originalDate The original date.
     * @return The converted date string.
     */
    public static String getTimeStamp(Date originalDate) {
        String convertedDate = DateUtils.getRelativeTimeSpanString(originalDate.getTime(),
                new Date().getTime(),
                DateUtils.SECOND_IN_MILLIS).toString();

        return convertedDate;
    }

    /**
     * Gets a string TimeStamp phrase like 5 mins ago, yesterday, 3 days ago.
     * 
     * @param originalDateTime The original date time in milliseconds since epoch.
     * @return The converted date string.
     */
    public static String getTimeStamp(Long originalDateTime) {
        String convertedDate = DateUtils.getRelativeTimeSpanString(originalDateTime,
                new Date().getTime(),
                DateUtils.SECOND_IN_MILLIS).toString();

        return convertedDate;
    }
}