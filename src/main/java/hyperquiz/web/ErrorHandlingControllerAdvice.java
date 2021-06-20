package hyperquiz.web;

import hyperquiz.dto.ErrorResponse;
import hyperquiz.exceptions.EmptyEntityCollection;
import hyperquiz.exceptions.EntityDataInvalidException;
import hyperquiz.exceptions.EntityUpdateException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.persistence.EntityNotFoundException;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice(basePackages = "hyperquiz.web" )
public class ErrorHandlingControllerAdvice {


    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleEntityUpdate(EntityUpdateException e) {
        return ResponseEntity.status(BAD_REQUEST).body(new ErrorResponse(BAD_REQUEST.value(), e.getMessage()));
    }


    @ExceptionHandler
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleEntityDataInvalid(EntityDataInvalidException e) {
        return ResponseEntity.status(NOT_FOUND).body(new ErrorResponse(NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleJpaSystem(JpaSystemException e) {
        return ResponseEntity.status(NOT_FOUND).body(new ErrorResponse(NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler
    @ResponseStatus(NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException e) {
        return ResponseEntity.status(NOT_FOUND).body(new ErrorResponse(NOT_FOUND.value(), e.getMessage()));
    }

    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(NOT_FOUND).body(new ErrorResponse(BAD_REQUEST.value(), ex.getMessage(),
                ex.getBindingResult().getAllErrors().stream()
                        .map(err ->{
                            if(err instanceof FieldError){
                                FieldError ferr = (FieldError) err;
                                String message = String.format("'%s': %s",
                                        ferr.getField(), ferr.getDefaultMessage());
                                if(ferr.getRejectedValue() != null && ferr.getRejectedValue().toString().length() > 0){
                                    message += String.format(", invalid value: %s", ferr.getRejectedValue().toString());
                                }
                                return message;
                            } else {
                                return err.getDefaultMessage();
                            }
                        }).collect(Collectors.toList())));
    }

    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationExc(DataIntegrityViolationException ex){
        Throwable cause=ex;
        while(cause.getCause()!=null) {
            cause = cause.getCause();
        }
        return ResponseEntity.status(BAD_REQUEST).body(new ErrorResponse(BAD_REQUEST.value(),cause.getMessage()));
    }

    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleDataEmptyEntityCollectionEx(EmptyEntityCollection ex){
        Throwable cause=ex;
        while(cause.getCause()!=null) {
            cause = cause.getCause();
        }
        return ResponseEntity.status(BAD_REQUEST).body(new ErrorResponse(BAD_REQUEST.value(),cause.getMessage()));
    }


}
