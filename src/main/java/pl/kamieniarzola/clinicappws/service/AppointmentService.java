package pl.kamieniarzola.clinicappws.service;

import pl.kamieniarzola.clinicappws.shared.dto.AppointmentDTO;
import pl.kamieniarzola.clinicappws.shared.dto.UserDTO;

import java.util.List;

public interface AppointmentService {

    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO, UserDTO userDTO) throws Exception;
    AppointmentDTO getAppointment(String id) throws Exception;
    AppointmentDTO updateAppointment(String id, AppointmentDTO appointmentDTO) throws Exception;
    void deleteAppointment(String id) throws Exception;
    List<AppointmentDTO> getUserAppointments(String userId);

    List<AppointmentDTO> getPatientAppointments(String id);

    List<AppointmentDTO> getHistoryOfUserAppointments(String login);

    List<AppointmentDTO> getAllAppointments();

}
