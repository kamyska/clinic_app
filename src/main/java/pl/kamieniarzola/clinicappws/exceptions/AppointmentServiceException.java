package pl.kamieniarzola.clinicappws.exceptions;

public class AppointmentServiceException extends RuntimeException {
    public AppointmentServiceException(String message) {
        super(message);
    }
}
