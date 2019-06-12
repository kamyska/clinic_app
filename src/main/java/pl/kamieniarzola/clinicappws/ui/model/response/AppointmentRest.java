package pl.kamieniarzola.clinicappws.ui.model.response;

import java.time.LocalDateTime;

public class AppointmentRest {

    private String appointmentId;
    private UserRest user;
    private LocalDateTime date;
    private PatientRest patient;
    private String description;
    private Boolean booked;

    public UserRest getUser() {
        return user;
    }

    public void setUser(UserRest user) {
        this.user = user;
    }

    public PatientRest getPatient() {
        return patient;
    }

    public void setPatient(PatientRest patient) {
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

    public Boolean getBooked() {
        return booked;
    }

    public void setBooked(Boolean booked) {
        this.booked = booked;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }
}
