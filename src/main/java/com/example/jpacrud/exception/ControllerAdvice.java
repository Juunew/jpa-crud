package com.example.jpacrud.exception;

import com.example.jpacrud.dto.response.ResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = {UserException.class})
    public ResponseEntity<ResponseBody> userExceptionHandler(UserException e) {
        log.info("Error = {}", e.toString());
        return new ResponseEntity<>(new ResponseBody("fail", e.getMessage()), e.getUserError().getStatus());
    }

    @ExceptionHandler(value = {PostException.class})
    public ResponseEntity<ResponseBody> postExceptionHandler(PostException e) {
        log.info("Error = {}", e.toString());
        return new ResponseEntity<>(new ResponseBody("fail", e.getMessage()), e.getPostError().getStatus());
    }
}
