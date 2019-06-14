package pl.kamieniarzola.clinicappws.ui.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pl.kamieniarzola.clinicappws.service.AppointmentService;
import pl.kamieniarzola.clinicappws.shared.dto.AppointmentDTO;
import pl.kamieniarzola.clinicappws.ui.model.request.AppointmentRequestModel;
import pl.kamieniarzola.clinicappws.ui.model.response.AppointmentRest;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public AppointmentRest getAppointment(@PathVariable String id) throws Exception {

        AppointmentDTO appointmentDTO = appointmentService.getAppointment(id);

        return new ModelMapper().map(appointmentDTO, AppointmentRest.class);
    }



    @PutMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public AppointmentRest updateAppointment(@RequestBody AppointmentRequestModel appointment, @PathVariable String id) throws Exception {

        AppointmentDTO appointmentDTO = new ModelMapper().map(appointment, AppointmentDTO.class);
        AppointmentDTO updatedAppointment = appointmentService.updateAppointment(id, appointmentDTO);

        return new ModelMapper().map(updatedAppointment, AppointmentRest.class);
    }

    @DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Boolean deleteAppointment(@PathVariable String id) throws Exception {

        appointmentService.deleteAppointment(id);

        return true;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<AppointmentRest> getAppointments(@RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "limit", defaultValue = "25") int limit) {


        List<AppointmentDTO> appointmentDTOList = appointmentService.getAllAppointments();
        Type listType = new TypeToken<List<AppointmentRest>>() {}.getType();

        return new ModelMapper().map(appointmentDTOList, listType);

    }
}
