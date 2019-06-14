package pl.kamieniarzola.clinicappws.ui.controller;

import pl.kamieniarzola.clinicappws.service.AppointmentService;
import pl.kamieniarzola.clinicappws.service.UserService;
import pl.kamieniarzola.clinicappws.shared.dto.AppointmentDTO;
import pl.kamieniarzola.clinicappws.shared.dto.UserDTO;
import pl.kamieniarzola.clinicappws.ui.model.request.AppointmentRequestModel;
import pl.kamieniarzola.clinicappws.ui.model.request.UserRequestModel;
import pl.kamieniarzola.clinicappws.ui.model.response.AppointmentRest;
import pl.kamieniarzola.clinicappws.ui.model.response.UserRest;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AppointmentService appointmentService;


    @GetMapping(path = "/{login}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserRest getUser(@PathVariable String login) {

        UserDTO userDto = userService.getUser(login);

        return new ModelMapper().map(userDto, UserRest.class);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public UserRest createUser(@RequestBody UserRequestModel user) throws Exception {

        UserDTO userDto = new ModelMapper().map(user, UserDTO.class);
        UserDTO createdUser = userService.createUser(userDto);

        return new ModelMapper().map(createdUser, UserRest.class);
    }

    @PutMapping(path = "/{login}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public UserRest updateUser(@RequestBody UserRequestModel userDetails, @PathVariable String login) {

        UserDTO userDto = new ModelMapper().map(userDetails, UserDTO.class);
        UserDTO updateUser = userService.updateUser(login, userDto);

        return new ModelMapper().map(updateUser, UserRest.class);
    }

    @DeleteMapping(path = "/{login}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Boolean deleteUser(@PathVariable String login) {

        userService.deleteUser(login);

        return true;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<UserRest> getUsers(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "limit", defaultValue = "25") int limit) {


        List<UserDTO> users = userService.getUsers(page, limit);
        Type listType = new TypeToken<List<UserRest>>() {}.getType();

        return new ModelMapper().map(users, listType);

    }

    @GetMapping(path = "/{login}/appointments",produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<AppointmentRest> getUserAppointments(@PathVariable String login,@RequestParam(value = "page", defaultValue = "0") int page,
                                                     @RequestParam(value = "limit", defaultValue = "25") int limit) {

        List<AppointmentRest> appointmentRestList = new ArrayList<>();
        List<AppointmentDTO> appointmentDTOList = appointmentService.getUserAppointments(login);

        if (appointmentDTOList!=null && !appointmentDTOList.isEmpty()){
            Type listType = new TypeToken<List<AppointmentRest>>() {}.getType();
            appointmentRestList= new ModelMapper().map(appointmentDTOList, listType);
        }

        return appointmentRestList;

    }

//    @GetMapping(path = "/{login}/appointments/history",produces = {MediaType.APPLICATION_JSON_VALUE})
//    public List<AppointmentRest> getHistoryOfUserAppointments(@PathVariable String login,@RequestParam(value = "page", defaultValue = "0") int page,
//                                                     @RequestParam(value = "limit", defaultValue = "25") int limit) {
//
//        List<AppointmentRest> appointmentRestList = new ArrayList<>();
//        List<AppointmentDTO> appointmentDTOList = appointmentService.getHistoryOfUserAppointments(login);
//
//        if (appointmentDTOList!=null && !appointmentDTOList.isEmpty()){
//            Type listType = new TypeToken<List<AppointmentRest>>() {}.getType();
//            appointmentRestList= new ModelMapper().map(appointmentDTOList, listType);
//        }
//
//        return appointmentRestList;
//
//    }

    @PostMapping(path="/{login}/appointments", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public AppointmentRest createUserAppointment(@PathVariable String login, @RequestBody AppointmentRequestModel appointment) throws Exception {
        UserDTO userDto = userService.getUser(login);

        AppointmentDTO appointmentDTO = new ModelMapper().map(appointment, AppointmentDTO.class);
        AppointmentDTO createdAppointment = appointmentService.createAppointment(appointmentDTO,userDto);

        return new ModelMapper().map(createdAppointment, AppointmentRest.class);
    }
}
