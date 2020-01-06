package pl.kamieniarzola.clinicappws.service.impl;

import pl.kamieniarzola.clinicappws.exceptions.UserServiceException;
import pl.kamieniarzola.clinicappws.io.entity.UserEntity;
import pl.kamieniarzola.clinicappws.io.repositories.UserRepository;
import pl.kamieniarzola.clinicappws.service.UserService;
import pl.kamieniarzola.clinicappws.shared.dto.UserDTO;
import pl.kamieniarzola.clinicappws.shared.utils.RandomGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kamieniarzola.clinicappws.ui.model.response.ErrorMessages;


import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    RandomGenerator randomGenerator;

    @Override
    public UserDTO createUser(UserDTO userDto) throws Exception {

        if (userRepository.findUserByLogin(userDto.getLogin())!=null){
            throw new UserServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }
        if (userDto.getFirstName() == null || userDto.getLastName() == null || userDto.getRole() == null
        || userDto.getPassword() == null){
            throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELDS.getErrorMessage());

        }
        UserEntity userEntity = new ModelMapper().map(userDto, UserEntity.class);
        userEntity.setEncodedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userEntity.setLogin(randomGenerator.generateRandomId(4));
        UserEntity savedUser = userRepository.save(userEntity);
        return new ModelMapper().map(savedUser,UserDTO.class);
    }

    @Override
    public UserDTO getUser(String login) {
        UserEntity userEntity = userRepository.findUserByLogin(login);
        if (userEntity == null){
            throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + login);
        }

        return new ModelMapper().map(userEntity,UserDTO.class);
    }

    @Override
    public UserDTO updateUser(String login, UserDTO userDto) {
        UserEntity userEntity = userRepository.findUserByLogin(login);
        if (userEntity == null){
            throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + login);
        }
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setRole(userDto.getRole());
        UserEntity updatedUser = userRepository.save(userEntity);

        return new ModelMapper().map(updatedUser,UserDTO.class);
    }

    @Override
    public void deleteUser(String login) {
        UserEntity userEntity = userRepository.findUserByLogin(login);
        if (userEntity == null){
            throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + login);
        }
            userRepository.delete(userEntity);

    }

    @Override
    public List<UserDTO> getUsers(int page, int limit) {
        List<UserDTO> listOfUsers = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<UserEntity> pageOfUsers = userRepository.findAll(pageableRequest);
        List<UserEntity> usersOnPage = pageOfUsers.getContent();
        if (usersOnPage.isEmpty()){
            throw new UserServiceException(ErrorMessages.NO_RECORDS.getErrorMessage());
        }
        for (UserEntity entity : usersOnPage) {
            UserDTO userDto = new ModelMapper().map(entity, UserDTO.class);
            listOfUsers.add(userDto);
        }
        return listOfUsers;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserByLogin(login);

        if (userEntity == null){
            throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage() + login);
        }
        return new User(userEntity.getLogin(), userEntity.getEncodedPassword(), new ArrayList<>());
    }
}
