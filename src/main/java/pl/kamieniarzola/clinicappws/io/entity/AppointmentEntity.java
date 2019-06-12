package pl.kamieniarzola.clinicappws.io.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "appointments")
public class AppointmentEntity {

    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false, unique = true)
    private String appointmentId;
    @Column(nullable = false)
    private LocalDateTime date;
    @Column
    private String description;
    @Column
    private Boolean booked;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name="user")
    private UserEntity user;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "patient")
    private PatientEntity patient;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }
}
