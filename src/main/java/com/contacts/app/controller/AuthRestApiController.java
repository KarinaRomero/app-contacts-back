package com.contacts.app.controller;

import com.contacts.app.exceptions.user.Response.HTTPUserNotFound;
import com.contacts.app.exceptions.user.UserNotFound;
import com.contacts.app.security.request.Login;
import com.contacts.app.security.request.Register;
import com.contacts.app.services.AuthRestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthRestApiController {

    @Autowired
    AuthRestApiService authRestApiService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser( @RequestBody Login login) {
        return ResponseEntity.ok(this.authRestApiService.loginUser(login));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser( @RequestBody Register register) throws HTTPUserNotFound {
        try{
            return new ResponseEntity<>(this.authRestApiService.registerUser(register), HttpStatus.OK);
        }catch (UserNotFound userNotFound) {
            throw new HTTPUserNotFound(userNotFound.getMessage());
        }
    }
}
