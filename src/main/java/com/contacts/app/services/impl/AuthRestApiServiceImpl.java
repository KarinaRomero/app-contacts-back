package com.contacts.app.services.impl;

import com.contacts.app.exceptions.user.UserNotFound;
import com.contacts.app.model.Role;
import com.contacts.app.model.RoleName;
import com.contacts.app.model.User;
import com.contacts.app.repository.RoleRepository;
import com.contacts.app.repository.UserRepository;
import com.contacts.app.security.jwt.JwtProvider;
import com.contacts.app.security.request.Login;
import com.contacts.app.security.request.Register;
import com.contacts.app.security.response.JwtResponse;
import com.contacts.app.security.response.ResponseMessage;
import com.contacts.app.services.AuthRestApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthRestApiServiceImpl implements AuthRestApiService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtProvider jwtProvider;


    @Override
    public JwtResponse loginUser(Login login) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities());
    }

    @Override
    public ResponseMessage registerUser(Register register) throws UserNotFound {
        if(userRepository.existsByUsername(register.getUsername())) {
            throw new UserNotFound("Fail User name is already register");
            //return new ResponseEntity<>(new ResponseMessage("Fail User is already register"), HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByEmail(register.getEmail())) {
            throw new UserNotFound("Fail email is already register");
            //return new ResponseEntity<>(new ResponseMessage("Fail Email is already register"), HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(register.getName());
        user.setUsername(register.getUsername());
        user.setEmail(register.getEmail());
        user.setPassword(encoder.encode(register.getPassword()));
        user.setRoles(convertToRoleModel(register.getRoles()));

        this.userRepository.save(user);
        return new ResponseMessage("User registered successfully");
    }
    private Set<Role> convertToRoleModel(Set<String> roles) {
        Set<Role> tempList =  new HashSet<>();
        roles.forEach(role -> {
            switch (role) {
                case "USER":
                    tempList.add(this.roleRepository.findByName(RoleName.ROLE_USER));
            }
        });
        return tempList;
    }
}
