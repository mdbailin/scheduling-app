package exceptions;
/**
 * AppointmentOverlapException is intended to be thrown when Appointments are scheduled with overlapping times.
 * */
public class AppointmentOverlapException extends RuntimeException {
    public AppointmentOverlapException(Throwable cause) {
        super(cause);
    }
}
