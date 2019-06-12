package pl.kamieniarzola.clinicappws.ui.model.request;

import java.time.LocalDateTime;

public class AppointmentRequestModel {

    private UserRequestModel user;
    private PatientRequestModel patientRequestModel;
    private LocalDateTime date;
    private String description;
    private Boolean booked;


    public UserRequestModel getUser() {
        return user;
    }

    public void setUser(UserRequestModel user) {
        this.user = user;
    }

    public PatientRequestModel getPatientRequestModel() {
        return patientRequestModel;
    }

    public void setPatientRequestModel(PatientRequestModel patientRequestModel) {
        this.patientRequestModel = patientRequestModel;
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

    public Boolean getBooked() {
        return booked;
    }

    public void setBooked(Boolean booked) {
        this.booked = booked;
    }
}
