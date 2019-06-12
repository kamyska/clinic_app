package pl.kamieniarzola.clinicappws.service;

import pl.kamieniarzola.clinicappws.shared.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {


    UserDTO createUser(UserDTO userDto) throws Exception;
    UserDTO getUser(String login);
    UserDTO updateUser(String login, UserDTO userDto);
    void deleteUser(String id);

    List<UserDTO> getUsers(int page, int limit);
}
