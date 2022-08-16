package exceptions;

import utility.TimeManager;

import java.time.LocalDateTime;

public class AppointmentOverlapException extends Exception {
    public AppointmentOverlapException(Throwable cause) {
        super(cause);
    }
}
