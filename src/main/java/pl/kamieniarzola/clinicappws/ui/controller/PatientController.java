package pl.kamieniarzola.clinicappws.ui.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.kamieniarzola.clinicappws.service.AppointmentService;
import pl.kamieniarzola.clinicappws.service.PatientService;
import pl.kamieniarzola.clinicappws.shared.dto.AppointmentDTO;
import pl.kamieniarzola.clinicappws.shared.dto.PatientDTO;
import pl.kamieniarzola.clinicappws.ui.model.request.PatientRequestModel;
import pl.kamieniarzola.clinicappws.ui.model.response.AppointmentRest;
import pl.kamieniarzola.clinicappws.ui.model.response.PatientRest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

@Autowired
    PatientService patientService;

@Autowired
    AppointmentService appointmentService;

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public PatientRest getPatient(@PathVariable String id) {

        PatientDTO patientDTO = patientService.getPatientById(id);

        return new ModelMapper().map(patientDTO, PatientRest.class);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public PatientRest createPatient(@RequestBody PatientRequestModel patient) throws Exception {

        PatientDTO patientDTO = new ModelMapper().map(patient, PatientDTO.class);
        PatientDTO createdPatient = patientService.createPatient(patientDTO);

        return new ModelMapper().map(createdPatient, PatientRest.class);
    }

    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public PatientRest updatePatient(@RequestBody PatientRequestModel patient, @PathVariable String id) {

        PatientDTO patientDTO = new ModelMapper().map(patient, PatientDTO.class);
        PatientDTO updatedPatient = patientService.updatePatient(id, patientDTO);

        return new ModelMapper().map(updatedPatient, PatientRest.class);
    }

    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Boolean deletePatient(@PathVariable String id) {

        patientService.deletePatient(id);

        return true;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<PatientRest> getPatients(@RequestParam(value = "page", defaultValue = "0") int page,
                                         @RequestParam(value = "limit", defaultValue = "25") int limit) {


        List<PatientDTO> patients = patientService.getPatients(page, limit);
        Type listType = new TypeToken<List<PatientRest>>() {}.getType();

        return new ModelMapper().map(patients, listType);

    }
    @GetMapping(path = "/{id}/appointments",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<AppointmentRest> getPatientAppointments(@PathVariable String id, @RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "limit", defaultValue = "25") int limit) {

        List<AppointmentRest> appointmentRestList = new ArrayList<>();
        List<AppointmentDTO> appointmentDTOList = appointmentService.getPatientAppointments(id);

        if (appointmentDTOList!=null && !appointmentDTOList.isEmpty()){
            Type listType = new TypeToken<List<AppointmentRest>>() {}.getType();
            appointmentRestList= new ModelMapper().map(appointmentDTOList, listType);
        }

        return appointmentRestList;

    }
}
