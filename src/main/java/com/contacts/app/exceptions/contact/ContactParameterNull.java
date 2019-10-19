package com.contacts.app.exceptions.contact;

import com.contacts.app.exceptions.BusinessException;

public class ContactParameterNull extends BusinessException {
    public ContactParameterNull(String message){
        super(message);
    }
}
