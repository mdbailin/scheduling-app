package exceptions;

import utility.TimeManager;

import java.time.ZonedDateTime;

public class AppointmentOverlapException extends RuntimeException {
    public AppointmentOverlapException(Throwable cause) {
        super(cause);
    }
}
