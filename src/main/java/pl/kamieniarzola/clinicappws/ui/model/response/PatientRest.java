package pl.kamieniarzola.clinicappws.ui.model.response;

import java.util.List;

public class PatientRest {

    private String id;
    private String firstName;
    private String lastName;
    private String personalIdNumber;
    private String address;
    private String phone;
    private List<AppointmentRest> appointments;

    public List<AppointmentRest> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentRest> appointments) {
        this.appointments = appointments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalIdNumber() {
        return personalIdNumber;
    }

    public void setPersonalIdNumber(String personalIdNumber) {
        this.personalIdNumber = personalIdNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
