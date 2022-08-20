package utility;

import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * The TimeManager conducts all time-related responsibilities including compensating, comparing, combining, formatting,
 * and calculating time differences.
 * */
public abstract class TimeManager {
    /**
     * Formatter to make times into a String acceptable for the MySQL Datetime format.
     * */
    private static DateTimeFormatter sqlFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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
    public static ZonedDateTime UTC(ZonedDateTime time) {
        return ZonedDateTime.of(time.toLocalDateTime(), ZoneId.of("UTC"));
    }
    public static String sqlUTC(ZonedDateTime time) {
        return sqlFormat.format(ZonedDateTime.of(time.toLocalDateTime(), ZoneId.of("UTC")));
    }
    public static Timestamp timestampUTC() {
        return Timestamp.valueOf(sqlFormat.format(ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("UTC"))));
    }
    public static Timestamp timestamp(ZonedDateTime time) {
        return Timestamp.valueOf(sqlFormat.format(time));
    }
    public static String labelEST(LocalTime time) {
        ZoneId system = ZoneId.systemDefault();
        ZoneId est = ZoneId.of("America/New_York");
        LocalDateTime today = LocalDateTime.of(LocalDate.now(), time);
        ZonedDateTime systemDateTime = ZonedDateTime.of(today, system);
        ZonedDateTime estDateTime = systemDateTime.withZoneSameInstant(est);
        return " (" + labelFormat.format(estDateTime) + ") EST";
    }
    public static String getDate(ZonedDateTime time) {
        return dayFormat.format(time);
    }
    public static String getTime(ZonedDateTime time) {
        return labelFormat.format(time);
    }
}

/**
 * Coordinated Universal Time (UTC) is used for storing the time in the database,
 * User’s local time is used for display purposes,
 * Eastern Standard Time (EST) is used for the company’s office hours.
 * Local time will be checked against EST business hours before they are stored in the database as UTC.
 * */