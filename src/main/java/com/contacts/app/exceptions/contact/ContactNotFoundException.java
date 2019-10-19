package com.contacts.app.exceptions;

public class ContactNotFoundException extends BusinessException {
    public ContactNotFoundException(String message){
        super(message);
    }
}
