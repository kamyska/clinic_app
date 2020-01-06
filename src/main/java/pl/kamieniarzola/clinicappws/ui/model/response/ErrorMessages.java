package pl.kamieniarzola.clinicappws.ui.model.response;

public enum ErrorMessages {

    RECORD_ALREADY_EXISTS("Record already exists!"),
    NO_RECORD_FOUND("No record found with provided ID: "),
    COULD_NOT_UPDATE("Could not update record."),
    COULD_NOT_DELETE("Could not delete record."),
    NO_RECORDS("List of records is empty."),
    MISSING_REQUIRED_FIELDS("One of required fields is empty.");

    private String errorMessage;


    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
