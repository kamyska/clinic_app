package pl.kamieniarzola.clinicappws.ui.model.request;

import java.util.List;

public class UserRequestModel {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String role;
    private List<AppointmentRequestModel> appointments;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<AppointmentRequestModel> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<AppointmentRequestModel> appointments) {
        this.appointments = appointments;
    }
}
