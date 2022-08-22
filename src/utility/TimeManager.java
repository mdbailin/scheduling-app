package utility;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * The TimeManager conducts all time-related responsibilities including compensating, comparing, combining, formatting,
 * and calculating time differences.
 * */
public abstract class TimeManager {
    private static DateTimeFormatter sqlFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static DateTimeFormatter reportFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm z");
    private static DateTimeFormatter labelFormat = DateTimeFormatter.ofPattern(("HH:mm"));
    private static DateTimeFormatter dayFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Combines a date and time into a formatted DateTime.
     * */
    public static ZonedDateTime combineDateTime(LocalDate date, LocalTime time) {
        if (date != null && time != null) {
            LocalDateTime combinedTime = LocalDateTime.of(date, time);
            ZonedDateTime combinedLocalTime = ZonedDateTime.of(combinedTime, ZoneId.of("America/New_York"));
            return ZonedDateTime.of(combinedLocalTime.toLocalDateTime(), ZoneId.of("UTC"));
        }
        return ZonedDateTime.now();
    }
    /**
     * Creates formatted LocalTime objects.
     * */
    public static LocalTime createLocalTime(int hour) {
        return LocalTime.of(hour, 0, 0);
    }
    // Refactor below
    public static ZonedDateTime toLocal(ZonedDateTime time) {
        return ZonedDateTime.of(time.toLocalDateTime(), ZoneId.systemDefault());
    }
    public static ZonedDateTime toLocal(Timestamp time) {
        return ZonedDateTime.of(time.toLocalDateTime(), ZoneId.systemDefault());
    }
    public static ZonedDateTime EST(ZonedDateTime time) {
        return ZonedDateTime.of(time.toLocalDateTime(), ZoneId.of("America/New_York"));
    }
    /**
     * Converts a LocalTime argument into a LocalTime in EST.
     * @param time LocalTime to be converted to EST.
     * */
    public static LocalTime EST(LocalTime time) {
        ZoneId system = ZoneId.systemDefault();
        ZoneId est = ZoneId.of("America/New_York");
        LocalDateTime today = LocalDateTime.of(LocalDate.now(), time);
        ZonedDateTime systemDateTime = ZonedDateTime.of(today, system);
        ZonedDateTime estDateTime = systemDateTime.withZoneSameInstant(est);
        return estDateTime.toLocalTime();
    }
    /**
     * Returns a formatted String in the format desired for Reports.
     * @param time A ZonedDateTime object to be formatted.
     * @return A String object with a formatted ZonedDateTime.
     * */
    public static String reportEST(ZonedDateTime time) {
        return reportFormat.format(ZonedDateTime.of(time.toLocalDateTime(), ZoneId.of("America/New_York")));
    }
    public static String columnView(ZonedDateTime time) {
        return reportFormat.format(time);
    }
    /**
     * Creates a Timestamp with the value of the current time in UTC, formatted for SQL.
     * @return Timestamp of ZonedDateTime.now() with the ZoneID of UTC.
     * */
    public static Timestamp timestampUTC() {
        return Timestamp.valueOf(sqlFormat.format(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC"))));
    }
    /**
     * Converts a ZonedDateTime into a TTimestamp, formatted for SQL.
     * @param time A ZonedDateTime to be converted to a Timestamp.
     * @return Timestamp created from time.
     * */
    public static Timestamp timestamp(ZonedDateTime time) {
        return Timestamp.valueOf(sqlFormat.format(time));
    }
    /**
     * Returns a label to be used on the AppointmentForm.
     * @param time LocalTime is converted to EST to indicate local time in comparison to the user's system time.
     * @return String containing time converted to EST.
     * */
    public static String labelEST(LocalTime time) {
        ZoneId system = ZoneId.systemDefault();
        ZoneId est = ZoneId.of("America/New_York");
        LocalDateTime today = LocalDateTime.of(LocalDate.now(), time);
        ZonedDateTime systemDateTime = ZonedDateTime.of(today, system);
        ZonedDateTime estDateTime = systemDateTime.withZoneSameInstant(est);
        return " (" + labelFormat.format(estDateTime) + ") EST";
    }
    /**
     * Returns a String with the date value from the ZonedDateTime that is passed in.
     * @return String of the formatted ZonedDateTime.
     * */
    public static String getDate(ZonedDateTime time) {
        return dayFormat.format(time);
    }
    /**
     * Returns a String with the time value from the ZonedDateTime that is passed in.
     * @return String of the formatted ZonedDateTime.
     * */
    public static String getTime(ZonedDateTime time) {
        return labelFormat.format(time);
    }
}