package pl.kamieniarzola.clinicappws.io.repositories;

import pl.kamieniarzola.clinicappws.io.entity.AppointmentEntity;
import pl.kamieniarzola.clinicappws.io.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends PagingAndSortingRepository<AppointmentEntity,Long> {

    AppointmentEntity findAppointmentById(String id);
    AppointmentEntity findAppointmentByUserAndDate(UserEntity userEntity, LocalDateTime date);
    List<AppointmentEntity> findAllByUser(UserEntity userEntity);
}
