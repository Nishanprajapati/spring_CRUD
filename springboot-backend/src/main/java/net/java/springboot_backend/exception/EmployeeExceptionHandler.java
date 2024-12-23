package net.java.springboot_backend.exception;

import net.java.springboot_backend.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler(value = {EmployeeNotFoundException.class})
    public ResponseEntity<Object> handleEmployeeNotFoundException (
            EmployeeNotFoundException employeeNotFoundException){
        EmployeeException employeeException = new EmployeeException(
                employeeNotFoundException.getMessage(),
                employeeNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(employeeException,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String, String>>handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
      Map<String, String> resp = new HashMap<>();
      ex.getBindingResult().getAllErrors().forEach((error) -> {
         String fieldName = ((FieldError)error).getField();
         String Message = error.getDefaultMessage();
         resp.put(fieldName, Message);
      });
      return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
    }
}
