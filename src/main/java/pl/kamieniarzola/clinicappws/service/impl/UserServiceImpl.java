package pl.kamieniarzola.clinicappws.service.impl;

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
            throw new Exception("user already exists!");
        }

        UserEntity userEntity = new ModelMapper().map(userDto, UserEntity.class);
        userEntity.setEncodedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userEntity.setLogin(randomGenerator.generateRandomId(8));
        UserEntity savedUser = userRepository.save(userEntity);
        return new ModelMapper().map(savedUser,UserDTO.class);
    }

    @Override
    public UserDTO getUser(String login) {
        UserEntity userEntity = userRepository.findUserByLogin(login);
        if (userEntity == null){
            throw new UsernameNotFoundException(login);
        }

        return new ModelMapper().map(userEntity,UserDTO.class);
    }

    @Override
    public UserDTO updateUser(String login, UserDTO userDto) {
        UserEntity userEntity = userRepository.findUserByLogin(login);
        if (userEntity == null){
            throw new UsernameNotFoundException(login);
        }
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        UserEntity updatedUser = userRepository.save(userEntity);

        return new ModelMapper().map(updatedUser,UserDTO.class);
    }

    @Override
    public void deleteUser(String login) {
        UserEntity userEntity = userRepository.findUserByLogin(login);
        if (userEntity == null){
            throw new UsernameNotFoundException(login);
        }
        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDTO> getUsers(int page, int limit) {
        List<UserDTO> listOfUsers = new ArrayList<>();
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<UserEntity> pageOfUsers = userRepository.findAll(pageableRequest);
        List<UserEntity> usersOnPage = pageOfUsers.getContent();

        for (UserEntity entity : usersOnPage) {
            UserDTO userDto = new ModelMapper().map(entity, UserDTO.class);
            listOfUsers.add(userDto);
        }
        return listOfUsers;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserByLogin(login);

        if (userEntity == null) {
            throw new UsernameNotFoundException(login);
        }
        return new User(userEntity.getLogin(), userEntity.getEncodedPassword(), new ArrayList<>());
    }
}
