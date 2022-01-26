package com.example.soccergame.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class SameEmailAddressException extends RuntimeException {
    public SameEmailAddressException(String message){
        super(message);
    }

}
