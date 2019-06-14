package pl.kamieniarzola.clinicappws.ui.model.request;

import java.time.LocalDateTime;

public class AppointmentRequestModel {

    private UserRequestModel user;
    private PatientRequestModel patient;
    private LocalDateTime date;
    private String description;



    public UserRequestModel getUser() {
        return user;
    }

    public void setUser(UserRequestModel user) {
        this.user = user;
    }

    public PatientRequestModel getPatient() {
        return patient;
    }

    public void setPatient(PatientRequestModel patient) {
        this.patient = patient;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
