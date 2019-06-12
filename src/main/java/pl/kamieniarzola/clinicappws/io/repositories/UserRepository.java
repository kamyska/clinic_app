package pl.kamieniarzola.clinicappws.io.repositories;

import pl.kamieniarzola.clinicappws.io.entity.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends PagingAndSortingRepository<UserEntity,Long> {

    UserEntity findUserByLogin(String login);
}
