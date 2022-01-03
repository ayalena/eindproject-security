package com.eindproject.eindproject.security.v1.service;

import com.eindproject.eindproject.security.v1.model.Authority;
import com.eindproject.eindproject.security.v1.model.EAuthority;
import com.eindproject.eindproject.security.v1.model.User;
import com.eindproject.eindproject.security.v1.model.UserDetailsImpl;
import com.eindproject.eindproject.security.v1.payload.request.AuthenticationRequest;
import com.eindproject.eindproject.security.v1.payload.request.UserPostRequest;
import com.eindproject.eindproject.security.v1.payload.response.AuthenticationResponse;
import com.eindproject.eindproject.security.v1.payload.response.MessageResponse;
import com.eindproject.eindproject.security.v1.repository.AuthorityRepository;
import com.eindproject.eindproject.security.v1.repository.UserRepository;
import com.eindproject.eindproject.security.v1.jwt.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
public class AuthorityService {

    private static UserRepository userRepository;
    private static PasswordEncoder passwordEncoder;
    private static AuthorityRepository authorityRepository;
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtUtil jwtUtil;

    @Autowired
    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Autowired
    public void setEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setJwtUtils(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }



    //register
    public static ResponseEntity<MessageResponse> registerUser(@Valid UserPostRequest userPostRequest) {
        if (userRepository.existsByUsername(userPostRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Username already in use"));
        }
        if (userRepository.existsByEmail(userPostRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Email already in use"));
        }

        //create user
        User user = new User(userPostRequest.getUsername(),
                userPostRequest.getEmail(),
                passwordEncoder.encode(userPostRequest.getPassword()));

        //        user.addAuthority("ROLE_USER");
//        for (String s : userPostRequest.getAuthorities()) {
//            if (!s.startsWith("ROLE_")) {
//                s = "ROLE_" + s;
//            }
//            s = s.toUpperCase();
//            if (!s.equals("ROLE_USER")) {
//                user.addAuthority(s);
//            }
//        }

        Set<String> strAuthorities = userPostRequest.getAuthorities();
        Set<Authority> authorities = new HashSet<>();

        if (strAuthorities == null) {
            Authority userAuthority = (Authority) authorityRepository.findByName(EAuthority.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Authentication not found"));
            authorities.add(userAuthority);
        } else {
            strAuthorities.forEach(role -> {
                switch (role) {
                    case "admin":
                        Authority adminAuthority = (Authority) authorityRepository.findByName(EAuthority.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Authentication not found"));
                        authorities.add(adminAuthority);

                        break;
                    default:
                        Authority userRole = (Authority) authorityRepository.findByName(EAuthority.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        authorities.add(userRole);
                }
            });
        }
        user.setAuthorities(authorities);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("Registration succesful!"));
    }

    //login
    public ResponseEntity<AuthenticationResponse> authenticateUser(@Valid AuthenticationRequest authenticationRequest) {
//        String username = authenticationRequest.getUsername();
//        String password = authenticationRequest.getPassword();

//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username, password)
//            );
//        }
//        catch (BadCredentialsException ex) {
//            throw new UsernameNotFoundException("Incorrect username or password");
//        }

//        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//
//        final String jwt = jwtUtil.generateToken(userDetails);
//
//        return new AuthenticationResponse(jwt);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken((UserDetails) authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new AuthenticationResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));

    }

}
