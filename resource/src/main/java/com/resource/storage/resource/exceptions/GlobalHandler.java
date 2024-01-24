package com.resource.storage.resource.exceptions;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@Slf4j
@ControllerAdvice
public class GlobalHandler {

    @ExceptionHandler({IdNotFoundException.class, ParserException.class, InvalidScvException.class})
    public ResponseEntity<Map<String, Object>> handleServiceException(Exception exception) {
        Map<String, Object> errorMap = new LinkedHashMap<>();
        if (exception instanceof IdNotFoundException) {
            errorMap.put("statusCode", HttpStatus.NOT_FOUND.toString());
            errorMap.put("message", exception.getMessage());
            return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
        } else {
            errorMap.put("statusCOde", HttpStatus.BAD_REQUEST.toString());
        }
        errorMap.put("message", exception.getMessage());
        log.warn(errorMap.toString());
        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }
}
