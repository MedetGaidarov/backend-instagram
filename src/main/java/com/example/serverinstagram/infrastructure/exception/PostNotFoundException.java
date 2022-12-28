package com.example.serverinstagram.infrastructure.exception;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
@Getter
@Setter
public class PostNotFoundException extends Exception{


    public  PostNotFoundException(String message)
    {
        super(message);
    }

}
