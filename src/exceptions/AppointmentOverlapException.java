package exceptions;

import utility.TimeManager;

import java.time.LocalDateTime;

public class AppointmentOverlapException extends RuntimeException {
    public AppointmentOverlapException(Throwable cause) {
        super(cause);
    }
}
