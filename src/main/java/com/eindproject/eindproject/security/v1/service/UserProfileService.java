package com.eindproject.eindproject.security.v1.service;

import com.eindproject.eindproject.security.v1.exceptions.RecordNotFoundException;
import com.eindproject.eindproject.security.v1.model.User;
import com.eindproject.eindproject.security.v1.model.UserProfile;
import com.eindproject.eindproject.security.v1.repository.UserProfileRepository;
import com.eindproject.eindproject.security.v1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Table;
import java.util.Optional;

@Service
@Table(name = "user_profile")
public class UserProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;


    //methods POST
    public UserProfile saveUserProfile(UserProfile userProfile, Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userRepository.findById(id).get();

            userProfile.setUser(user);

            return userProfileRepository.save(userProfile);
        } else {
            throw new RecordNotFoundException();
        }

    }

    //methods PUT
    public void updateUserProfile(Long id, UserProfile newUserProfile) {
        Optional<UserProfile> userProfileOptional = userProfileRepository.findById(id);
        if (userProfileOptional.isPresent()) {
            UserProfile userProfile = userProfileRepository.findById(id).get();
            userProfile.setEmail(newUserProfile.getEmail());
            userProfile.setFirstName(newUserProfile.getFirstName());
            userProfile.setLastName(newUserProfile.getLastName());
            userProfile.setAge(newUserProfile.getAge());
            userProfile.setAddress(newUserProfile.getAddress());
            userProfile.setPostalCode(newUserProfile.getPostalCode());
            userProfile.setPhoneNumber(newUserProfile.getPhoneNumber());
            userProfile.setMessage(newUserProfile.getMessage());
            userProfile.setSessionType(newUserProfile.getSessionType());
            userProfile.setPregnant(newUserProfile.getPregnant());
            userProfile.setHadAcu(newUserProfile.getHadAcu());
            userProfile.setReference(newUserProfile.getReference());
            userProfile.setFirstName(newUserProfile.getFirstName());
            userProfile.setAgrees(newUserProfile.getAgrees());

            userProfileRepository.save(userProfile);
        } else {
            throw new RecordNotFoundException();
        }
    }
}
