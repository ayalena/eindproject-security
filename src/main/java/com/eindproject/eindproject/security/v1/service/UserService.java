package com.eindproject.eindproject.security.v1.service;

import com.eindproject.eindproject.security.v1.exceptions.RecordNotFoundException;
import com.eindproject.eindproject.security.v1.model.Authority;
import com.eindproject.eindproject.security.v1.model.User;
import com.eindproject.eindproject.security.v1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private static PasswordEncoder passwordEncoder;
    private static UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(String username) {
        return userRepository.findById(username);
    }

//    public static ResponseEntity<String> createUser(UserPostRequest userPostRequest) {
//        try {
//            String encryptedPassword = passwordEncoder.encode(userPostRequest.getPassword());
//
//            User user = new User();
//            user.setUsername(userPostRequest.getUsername());
//            user.setPassword(encryptedPassword);
//            user.setEmail(userPostRequest.getEmail());
////            user.setEnabled(true);
//            user.addAuthority("ROLE_USER");
//            for (String s : userPostRequest.getAuthorities()) {
//                if (!s.startsWith("ROLE_")) {
//                    s = "ROLE_" + s;
//                }
//                s = s.toUpperCase();
//                if (!s.equals("ROLE_USER")) {
//                    user.addAuthority(s);
//                }
//            }
//            userRepository.save(user);
//            return ResponseEntity.ok("Registration Successful");
//        }
//        catch (Exception ex) {
////            throw new BadRequestException("Cannot create user.");
//            throw new RecordNotFoundException();
//        }
//    }



    public void updateUser(String username, User newUser) {
        Optional<User> userOptional = userRepository.findById(username);
        if (userOptional.isEmpty()) {
//            throw new UserNotFoundException(username);
            throw new RecordNotFoundException();
        }
        else {
            User user = userOptional.get();
            user.setPassword(passwordEncoder.encode(newUser.getPassword()));
            user.setEmail(newUser.getEmail());
//            user.setEnabled(newUser.isEnabled());
            userRepository.save(user);
        }
    }


    public void deleteUser(String username) {
        if (userRepository.existsById(username)) {
            userRepository.deleteById(username);
        }
        else {
//            throw new UserNotFoundException(username);
            throw new RecordNotFoundException();
        }
    }


    public Set<Authority> getAuthorities(String username) {
        Optional<User> userOptional = userRepository.findById(username);
        if (userOptional.isEmpty()) {
//            throw new UserNotFoundException(username);
            throw new RecordNotFoundException();
        }
        else {
            User user = userOptional.get();
            return user.getAuthorities();
        }
    }


//    public void addAuthority(String username, String authorityString) {
//        Optional<User> userOptional = userRepository.findById(username);
//        if (userOptional.isEmpty()) {
////            throw new UserNotFoundException(username);
//            throw new RecordNotFoundException();
//        }
//        else {
//            User user = userOptional.get();
//            user.addAuthority(authorityString);
//            userRepository.save(user);
//        }
//    }


//    public void removeAuthority(String username, String authorityString) {
//        Optional<User> userOptional = userRepository.findById(username);
//        if (userOptional.isEmpty()) {
////            throw new UserNotFoundException(username);
//            throw new RecordNotFoundException();
//        }
//        else {
//            User user = userOptional.get();
//            user.removeAuthority(authorityString);
//            userRepository.save(user);
//        }
//    }


    public void setPassword(String username, String password) {
        if (username.equals(getCurrentUserName())) {
            if (isValidPassword(password)) {
                Optional<User> userOptional = userRepository.findById(username);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    user.setPassword(passwordEncoder.encode(password));
                    userRepository.save(user);
                } else {
//                    throw new UserNotFoundException(username);
                    throw new RecordNotFoundException();
                }
            }
//            else {
//                throw new InvalidPasswordException();
//            }
//        }
//        else {
//            throw new NotAuthorizedException();
//        }
        }
    }


    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

    private boolean isValidPassword(String password) {
        final int MIN_LENGTH = 8;
        final int MIN_DIGITS = 1;
        final int MIN_LOWER = 1;
        final int MIN_UPPER = 1;
        final int MIN_SPECIAL = 1;
        final String SPECIAL_CHARS = "@#$%&*!()+=-_";

        long countDigit = password.chars().filter(ch -> ch >= '0' && ch <= '9').count();
        long countLower = password.chars().filter(ch -> ch >= 'a' && ch <= 'z').count();
        long countUpper = password.chars().filter(ch -> ch >= 'A' && ch <= 'Z').count();
        long countSpecial = password.chars().filter(ch -> SPECIAL_CHARS.indexOf(ch) >= 0).count();

        boolean validPassword = true;
        if (password.length() < MIN_LENGTH) validPassword = false;
        if (countLower < MIN_LOWER) validPassword = false;
        if (countUpper < MIN_UPPER) validPassword = false;
        if (countDigit < MIN_DIGITS) validPassword = false;
        if (countSpecial < MIN_SPECIAL) validPassword = false;

        return validPassword;
    }

}
