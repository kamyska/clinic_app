package pl.kamieniarzola.clinicappws.ui.model.response;

import java.util.List;

public class UserRest {

    private String firstName;
    private String lastName;
    private String login;
    private String role;
    private List<AppointmentRest> appointments;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
