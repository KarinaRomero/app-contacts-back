package com.contacts.app.exceptions.contact;

import com.contacts.app.exceptions.BusinessException;

public class ContactNotFoundException extends BusinessException {
    public ContactNotFoundException(String message){
        super(message);
    }
}
