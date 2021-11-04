package com.eindproject.eindproject.security.v1.controller;

import com.eindproject.eindproject.security.v1.payload.request.AuthenticationRequest;
import com.eindproject.eindproject.security.v1.payload.response.AuthenticationResponse;
import com.eindproject.eindproject.security.v1.service.UserAuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    UserAuthenticateService userAuthenticateService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        AuthenticationResponse authenticationResponse = userAuthenticateService.authenticateUser(authenticationRequest);
        return ResponseEntity.ok(authenticationResponse);
    }
}
