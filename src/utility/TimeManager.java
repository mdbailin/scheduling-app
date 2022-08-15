package utility;

import java.time.*;

/**
 * The TimeManager conducts all time-related responsibilities including compensating, comparing, combining, formatting,
 * and caluclating time differences.
 * */
public abstract class TimeManager {
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
    public static LocalDateTime localToUTC(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime();
    }
    /**
     * Combines a date and time into a formatted DateTime.
     * */
    public static LocalDateTime combineDateTime(LocalDate date, LocalTime time) {
        return LocalDateTime.of(date, time);
    }
}

/**
 * Coordinated Universal Time (UTC) is used for storing the time in the database,
 * User’s local time is used for display purposes,
 * Eastern Standard Time (EST) is used for the company’s office hours.
 * Local time will be checked against EST business hours before they are stored in the database as UTC.
 * */