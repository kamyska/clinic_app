package pl.kamieniarzola.clinicappws.service.impl;

import pl.kamieniarzola.clinicappws.io.entity.AppointmentEntity;
import pl.kamieniarzola.clinicappws.io.entity.PatientEntity;
import pl.kamieniarzola.clinicappws.io.entity.UserEntity;
import pl.kamieniarzola.clinicappws.io.repositories.AppointmentRepository;
import pl.kamieniarzola.clinicappws.io.repositories.UserRepository;
import pl.kamieniarzola.clinicappws.service.AppointmentService;
import pl.kamieniarzola.clinicappws.shared.dto.AppointmentDTO;
import pl.kamieniarzola.clinicappws.shared.dto.PatientDTO;
import pl.kamieniarzola.clinicappws.shared.dto.UserDTO;
import pl.kamieniarzola.clinicappws.shared.utils.RandomGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RandomGenerator randomGenerator;

    @Override
    public AppointmentDTO createAppointment(AppointmentDTO appointmentDTO) throws Exception {

        UserDTO userDto = appointmentDTO.getUser();
        UserEntity userEntity = new ModelMapper().map(userDto, UserEntity.class);
        if (appointmentRepository.findAppointmentByUserAndDate(userEntity,appointmentDTO.getDate()) != null) {
            throw new Exception("appointment already exists!");
        }

        AppointmentEntity appointmentEntity = new ModelMapper().map(appointmentDTO, AppointmentEntity.class);
        appointmentEntity.setAppointmentId(randomGenerator.generateRandomId(8));
        AppointmentEntity savedAppointment = appointmentRepository.save(appointmentEntity);
        return new ModelMapper().map(savedAppointment, AppointmentDTO.class);
    }

    @Override
    public AppointmentDTO getAppointment(String id) throws Exception {
        AppointmentEntity appointmentEntity = appointmentRepository.findAppointmentById(id);
        if (appointmentEntity == null) {
            throw new Exception("not found!");
        }

        return new ModelMapper().map(appointmentEntity, AppointmentDTO.class);
    }

    @Override
    public AppointmentDTO updateAppointment(String id, AppointmentDTO appointmentDTO) throws Exception {
        AppointmentEntity appointmentEntity = appointmentRepository.findAppointmentById(id);
        if (appointmentEntity == null) {
            throw new Exception("not found!");
        }
        if (appointmentEntity.getDate().isAfter(LocalDateTime.now())) {
            PatientDTO patientDto = appointmentDTO.getPatient();
            PatientEntity patientEntity = new ModelMapper().map(patientDto, PatientEntity.class);
            appointmentEntity.setPatient(patientEntity);
            appointmentEntity.setBooked(appointmentDTO.getBooked());
        }
        String updatedDescription = appointmentEntity.getDescription().concat(appointmentDTO.getDescription());
        appointmentEntity.setDescription(updatedDescription);
        AppointmentEntity savedAppointment = appointmentRepository.save(appointmentEntity);
        return new ModelMapper().map(savedAppointment, AppointmentDTO.class);
    }

    @Override
    public void deleteAppointment(String id) throws Exception {

        AppointmentEntity appointmentEntity = appointmentRepository.findAppointmentById(id);
        if (appointmentEntity == null) {
            throw new Exception("not found!");
        }
        if (appointmentEntity.getDate().isAfter(LocalDateTime.now())) {
            appointmentRepository.delete(appointmentEntity);
        }
    }

    @Override
    public List<AppointmentDTO> getAppointments(String login) {
        List<AppointmentDTO> returnValue = new ArrayList<>();
        UserEntity userEntity = userRepository.findUserByLogin(login);

        if (userEntity == null) {
            return returnValue;
        }

        List<AppointmentEntity> appointmentsList = appointmentRepository.findAllByUser(userEntity);
        for (AppointmentEntity appointment : appointmentsList) {
            returnValue.add(new ModelMapper().map(appointment, AppointmentDTO.class));
        }

        return returnValue;
    }
}
