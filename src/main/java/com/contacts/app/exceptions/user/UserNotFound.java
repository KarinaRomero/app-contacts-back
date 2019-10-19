package com.contacts.app.exceptions.user;

import com.contacts.app.exceptions.BusinessException;

public class UserNotFound extends BusinessException {
    public UserNotFound(String message) {
        super(message);
    }
}
