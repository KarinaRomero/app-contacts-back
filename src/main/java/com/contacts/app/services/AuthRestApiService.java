package com.contacts.app.services;

import com.contacts.app.exceptions.user.UserNotFound;
import com.contacts.app.security.request.Login;
import com.contacts.app.security.request.Register;
import com.contacts.app.security.response.JwtResponse;
import com.contacts.app.security.response.ResponseMessage;
import org.springframework.stereotype.Service;

@Service
public interface AuthRestApiService {

    public JwtResponse loginUser(Login login);
    public ResponseMessage registerUser(Register register) throws UserNotFound;
}
