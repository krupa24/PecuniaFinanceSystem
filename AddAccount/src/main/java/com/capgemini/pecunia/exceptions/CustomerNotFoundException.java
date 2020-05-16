package com.capgemini.pecunia.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String msg){
        super(msg);
    }

    public CustomerNotFoundException(String msg,Throwable e){
        super(msg,e);
    }
}
