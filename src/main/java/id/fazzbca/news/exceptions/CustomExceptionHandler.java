package id.fazzbca.news.exceptions;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth0.jwt.exceptions.JWTCreationException;

import id.fazzbca.news.exceptions.custom.EntityFoundException;
import id.fazzbca.news.payloads.res.ResponseHandler;

@RestControllerAdvice
public class CustomExceptionHandler {
  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<?> globalException(Exception e) {
    return ResponseHandler.responseError(500, e.getMessage(), null);
  }

  @ExceptionHandler(value = IllegalArgumentException.class)
  public ResponseEntity<?> handleIllegalArgEx(IllegalArgumentException e) {
    return ResponseHandler.responseError(400, e.getMessage(), null);
  }

  @ExceptionHandler(value = NoSuchElementException.class)
  public ResponseEntity<?> handleNoElementEx(NoSuchElementException e) {
    return ResponseHandler.responseError(404, e.getMessage(), null);
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleMethodArgNotValidEx(MethodArgumentNotValidException e) {
    Map<String, Object> errorMap = new HashMap<>();

    e.getFieldErrors().forEach(err -> {
      errorMap.put(err.getField(), err.getDefaultMessage());
    });

    return ResponseHandler.responseError(e.getStatusCode().value(), "Error Validation", errorMap);
  }

  @ExceptionHandler(value = EntityFoundException.class)
  public ResponseEntity<?> handleEntityFoundExceptionEx(EntityFoundException e) {
    return ResponseHandler.responseError(400, e.getMessage(), null);
  }

  @ExceptionHandler(value = JWTCreationException.class)
  public ResponseEntity<?> handleJWTCreationExceptionEx(JWTCreationException e) {
    return ResponseHandler.responseError(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage(), null);
  }
}