package pl.kamieniarzola.clinicappws.io.repositories;

import pl.kamieniarzola.clinicappws.io.entity.PatientEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends PagingAndSortingRepository<PatientEntity,Long> {

    PatientEntity findPatientById(String id);
    PatientEntity findPatientByPersonalIdNumber(String personalIdNum);
    PatientEntity findPatientByLastName(String lastName);




}
