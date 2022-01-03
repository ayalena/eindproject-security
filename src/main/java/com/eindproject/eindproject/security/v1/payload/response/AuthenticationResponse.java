package com.eindproject.eindproject.security.v1.payload.response;

import java.util.List;

public class AuthenticationResponse {

//    private final String jwt;
//
//    public AuthenticationResponse(String jwt) {
//        this.jwt = jwt;
//    }
//
//    public String getJwt() {
//        return jwt;
//    }

    private String token;
    private String type = "Bearer";
    private Long id;
    private String username;
    private String email;
    private List<String> authorisations;

    public AuthenticationResponse(String accessToken, Long id, String username, String email, List<String> authorisations) {
        this.token = accessToken;
        this.id = id;
        this.username = username;
        this.email = email;
        this.authorisations = authorisations;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getAuthorisations() {
        return authorisations;
    }
}
