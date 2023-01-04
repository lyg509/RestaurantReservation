package com.lyg.eatgo.application;

import org.springframework.web.bind.annotation.ControllerAdvice;



public class EmailNotExistedException extends RuntimeException{
    EmailNotExistedException(String email) {
        super("Email is not registered: " + email);
    }
}
