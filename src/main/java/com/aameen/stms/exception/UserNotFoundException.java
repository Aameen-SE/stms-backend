package com.aameen.stms.exception;

public class UserNotFoundException extends RuntimeException{

    public  UserNotFoundException(String msg){
        super(msg);
    }
}
