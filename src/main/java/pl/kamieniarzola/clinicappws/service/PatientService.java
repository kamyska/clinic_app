package pl.kamieniarzola.clinicappws.service;

import pl.kamieniarzola.clinicappws.shared.dto.PatientDTO;

import java.util.List;

public interface PatientService {

    PatientDTO createPatient(PatientDTO patientDTO) throws Exception;
    PatientDTO getPatientById(String id);
    PatientDTO getPatientByPersonalIdNum(String personalIdNum);
    PatientDTO getPatientByLastName(String lastName);
    PatientDTO updatePatient(String id, PatientDTO patientDTO);
    void deletePatient(String id);

    List<PatientDTO> getPatients(int page, int limit);
}
