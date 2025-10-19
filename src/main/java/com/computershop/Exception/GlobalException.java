package com.computershop.Exception;

import com.computershop.Util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.awt.geom.RectangularShape;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    // Handle General Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        Map<String, Object> res = new HashMap<>();
        res.put("success", false);
        res.put("Code", 500);
        res.put("message",ex.getMessage());
        res.put("Data",null);
        res.put("Timestemp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
    }

    // handle exception validation DTO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String,String>  errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(item -> errors.put(item.getField(), item.getDefaultMessage()));
        Map<String, Object> res = new HashMap<>();
        res.put("success", false);
        res.put("Code", HttpStatus.BAD_REQUEST.value());
        res.put("Data", null);
        res.put("Message", "Validation fails");
        res.put("Timestamp", LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    // handle 404 resource not found in server

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse> NotFoundException(Exception exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.error(exception.getMessage(),HttpStatus.NOT_FOUND));

    }


}
