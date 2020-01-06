package pl.kamieniarzola.clinicappws.service.impl;

import org.springframework.stereotype.Service;
import pl.kamieniarzola.clinicappws.exceptions.PatientServiceException;
import pl.kamieniarzola.clinicappws.io.entity.PatientEntity;
import pl.kamieniarzola.clinicappws.io.repositories.PatientRepository;
import pl.kamieniarzola.clinicappws.service.PatientService;
import pl.kamieniarzola.clinicappws.shared.dto.PatientDTO;
import pl.kamieniarzola.clinicappws.shared.utils.RandomGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.kamieniarzola.clinicappws.ui.model.response.ErrorMessages;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    RandomGenerator randomGenerator;

    @Override
    public PatientDTO createPatient(PatientDTO patientDTO) throws Exception {
        if (patientRepository.findPatientByPatientId(patientDTO.getPatientId())!=null){
            throw new PatientServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }

        if (patientDTO.getFirstName() == null || patientDTO.getLastName() == null
                || patientDTO.getAddress() == null || patientDTO.getPhone() == null || patientDTO.getPersonalIdNumber() == null){
            throw new PatientServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());

        }
        PatientEntity patientEntity = new ModelMapper().map(patientDTO, PatientEntity.class);

        patientEntity.setPatientId(randomGenerator.generateRandomId(8));
        PatientEntity savedPatient = patientRepository.save(patientEntity);
        return new ModelMapper().map(savedPatient,PatientDTO.class);
    }

    @Override
    public PatientDTO getPatientById(String id) {
        PatientEntity patientEntity = patientRepository.findPatientByPatientId(id);
        if (patientEntity == null){
            throw new PatientServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + id);
        }

        return new ModelMapper().map(patientEntity,PatientDTO.class);
    }

    @Override
    public PatientDTO getPatientByPersonalIdNum(String personalIdNum) {
        PatientEntity patientEntity = patientRepository.findPatientByPersonalIdNumber(personalIdNum);
        if (patientEntity == null){
            throw new PatientServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + personalIdNum);
        }

        return new ModelMapper().map(patientEntity,PatientDTO.class);
    }

    @Override
    public PatientDTO getPatientByLastName(String lastName) {
        PatientEntity patientEntity = patientRepository.findPatientByLastName(lastName);
        if (patientEntity == null){
            throw new UsernameNotFoundException(lastName);
        }

        return new ModelMapper().map(patientEntity,PatientDTO.class);    }

    @Override
    public PatientDTO updatePatient(String id, PatientDTO patientDTO) {
        PatientEntity patientEntity = patientRepository.findPatientByPatientId(id);
        if (patientEntity == null){
            throw new PatientServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + id);
        }
        patientEntity.setFirstName(patientDTO.getFirstName());
        patientEntity.setLastName(patientDTO.getLastName());
        patientEntity.setAddress(patientDTO.getAddress());
        patientEntity.setPhone(patientDTO.getPhone());
        PatientEntity updatedPatient = patientRepository.save(patientEntity);

        return new ModelMapper().map(updatedPatient,PatientDTO.class);
    }

    @Override
    public void deletePatient(String id) {
        PatientEntity patientEntity = patientRepository.findPatientByPatientId(id);
        if (patientEntity == null){
            throw new PatientServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + id);
        }
        patientRepository.delete(patientEntity);

    }

    @Override
    public List<PatientDTO> getPatients(int page, int limit) {
        List<PatientDTO> listOfPatients = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<PatientEntity> pageOfPatients = patientRepository.findAll(pageableRequest);
        List<PatientEntity> patientsOnPage = pageOfPatients.getContent();
        if (patientsOnPage.isEmpty()){
            throw new PatientServiceException(ErrorMessages.NO_RECORDS.getErrorMessage());
        }
        for (PatientEntity entity : patientsOnPage) {
            PatientDTO patientDTO = new ModelMapper().map(entity, PatientDTO.class);
            listOfPatients.add(patientDTO);
        }
        return listOfPatients;
    }
}
