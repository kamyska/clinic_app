package pl.kamieniarzola.clinicappws.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import pl.kamieniarzola.clinicappws.ui.model.response.ErrorMessage;

import java.util.Date;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
      return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
