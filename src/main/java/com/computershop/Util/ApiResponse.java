package com.computershop.Util;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    private Boolean success;
    private Integer code;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponse(Boolean success, Integer code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public static <T> ApiResponse<T> create(T data) {
        return new ApiResponse<>(true, HttpStatus.CREATED.value(), "Create success", data);
    }

    public static <T> ApiResponse<T> create(T data,String message){ // create
        return new ApiResponse<>(true, HttpStatus.CREATED.value(),message,data);
    }

    public static <T> ApiResponse<T> success(T data, String message){ //list, list_one, update, remove
        return new ApiResponse<>(true, HttpStatus.OK.value(),message,data);
    }

    public static <T> ApiResponse<T> error(T data, String message, Integer status){ // 401,400,500
        return new ApiResponse<>(false, status,message,data);
    }
}
