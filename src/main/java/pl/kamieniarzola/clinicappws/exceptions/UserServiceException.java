package pl.kamieniarzola.clinicappws.exceptions;

public class UserServiceException extends RuntimeException{

    public UserServiceException(String message) {
        super(message);
    }
}
