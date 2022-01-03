package com.eindproject.eindproject.security.v1.controller;

import com.eindproject.eindproject.security.v1.payload.request.AuthenticationRequest;
import com.eindproject.eindproject.security.v1.payload.request.UserPostRequest;
import com.eindproject.eindproject.security.v1.payload.response.AuthenticationResponse;
import com.eindproject.eindproject.security.v1.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    AuthorityService authorityService;

    @Autowired
    public AuthenticationController(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
       return authorityService.authenticateUser(authenticationRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserPostRequest userPostRequest) {
        return authorityService.registerUser(userPostRequest);
    }

    //    @PostMapping("/user/registration")
//    public String showRegistrationForm(WebRequest request, Model model) {
//        UserDto userDto = new UserDto();
//        model.addAttribute("user", userDto);
//        return "registration";
//    }
}
