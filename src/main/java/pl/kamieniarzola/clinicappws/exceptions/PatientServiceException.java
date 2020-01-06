package pl.kamieniarzola.clinicappws.exceptions;

public class PatientServiceException extends RuntimeException{

    public PatientServiceException(String message) {
        super(message);
    }
}
