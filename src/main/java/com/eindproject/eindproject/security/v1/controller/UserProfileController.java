package com.eindproject.eindproject.security.v1.controller;

import com.eindproject.eindproject.security.v1.model.User;
import com.eindproject.eindproject.security.v1.model.UserProfile;
import com.eindproject.eindproject.security.v1.repository.UserProfileRepository;
import com.eindproject.eindproject.security.v1.repository.UserRepository;
import com.eindproject.eindproject.security.v1.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
@RequestMapping("api/user-profile")
public class UserProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileService userProfileService;


    //POST
    @PostMapping("")
    public ResponseEntity<Object> saveUserProfile(@RequestBody UserProfile userProfile, Authentication authentication) {
        User user = userRepository.findByUsername(authentication.getName()).get();
        UserProfile userProfile1 = userProfileService.saveUserProfile(userProfile, user.getId());
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(userProfile1)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    //PUT
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUserProfile(@PathVariable("id")Long id, @RequestBody UserProfile userProfile) {
        userProfileService.updateUserProfile(id, userProfile);
        return ResponseEntity.ok().build();
    }


}
