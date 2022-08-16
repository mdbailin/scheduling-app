package utility;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * The TimeManager conducts all time-related responsibilities including compensating, comparing, combining, formatting,
 * and calculating time differences.
 * */
public abstract class TimeManager {
    /**
     * Formatter to be used with times.
     * */
    private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyy");
    private static DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mm");
    private static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("MMM d yyyy  hh:mm a");

    /**
     * Returns current UTC time.
     * @return UTC time in the format of LocalDateTime.
     * */
    public static LocalDateTime nowUTC() {
        return LocalDateTime.now(Clock.systemUTC());
    }
    /**
     * Converts local time to UTC.
     * @param time The LocalDateTime to convert to UTC.
     * @return UTC time in the format of LocalDateTime.
     * */
    public static LocalDateTime getUTC(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
    }
    /**
     * Converts local time to EST.
     * @param time The LocalDateTime to convert to EST.
     * @return EST time in the format of LocalDateTime.
     * */
    public static LocalDateTime getEST(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York")).toLocalDateTime();
    }
    /**
     * Combines a date and time into a formatted DateTime.
     * */
    public static LocalDateTime combineDateTime(LocalDate date, LocalTime time) {
        return LocalDateTime.of(date, time);
    }
    /**
     * Creates formatted LocalTime objects.
     * */
    public static LocalTime createLocalTime(int hour) {
        return LocalTime.of(hour, 0, 0);
    }
    /**
     * Formats the date.
     * */
    public static String formatDate(LocalDate date) {
        return date.format(dateFormat);
    }
    public static String formatDateTime(LocalDateTime ldt) {
        return ldt.format(dateTimeFormat);
    }
}

/**
 * Coordinated Universal Time (UTC) is used for storing the time in the database,
 * User’s local time is used for display purposes,
 * Eastern Standard Time (EST) is used for the company’s office hours.
 * Local time will be checked against EST business hours before they are stored in the database as UTC.
 * */