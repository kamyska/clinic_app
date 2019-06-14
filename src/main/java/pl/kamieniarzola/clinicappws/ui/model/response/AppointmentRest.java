package pl.kamieniarzola.clinicappws.ui.model.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AppointmentRest implements Serializable {

    private String appointmentId;
    @JsonManagedReference
    private UserRest user;
    private LocalDateTime date;
  @JsonManagedReference
    private PatientRest patient;
    private String description;

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

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }
}
