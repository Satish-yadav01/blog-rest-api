package com.springboot.blog.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class BlogApiException extends RuntimeException{
    private HttpStatus status;
    private String Message;

    public BlogApiException(HttpStatus status, String message) {
        super(String.format("%s & status : %s",message,status));
        this.status = status;
        Message = message;
    }

    public BlogApiException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        Message = message1;
    }
}
