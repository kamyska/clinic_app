package pl.kamieniarzola.clinicappws.exceptions;

public class ServiceException extends RuntimeException{

    public ServiceException(String message) {
        super(message);
    }
}
