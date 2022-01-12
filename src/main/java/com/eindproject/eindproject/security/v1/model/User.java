package com.eindproject.eindproject.security.v1.model;

//import com.eindproject.eindproject.security.v1.documentstorage.DocumentStorageProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    //standaard indeling
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

//    @Column(nullable = false)
//    private boolean enabled;

    @Column(nullable = false, unique = true)
    private String email;

    //relations
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private Set<Authority> authorities = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY,
            mappedBy = "user",
            cascade = CascadeType.ALL)
    private UserProfile userProfile;

//    @OneToMany(
//            mappedBy = "user",
//            cascade = CascadeType.ALL)
//    @JsonIgnore
//    private List<DocumentStorageProperties> fileDB;



    //constructor
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {

    }


    //getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public boolean isEnabled() {
//        return enabled;
//    }
//
//    public void setEnabled(boolean enabled) {
//        this.enabled = enabled;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    //authorisation
    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

//    public void addAuthority(Authority authority) {
//        this.authorities.add(authority);
//    }
//
//    public void addAuthority(String authorityString) {
//        this.authorities.add(new Authority(this.username, authorityString));
//    }
//
//    public void removeAuthority(Authority authority) {
//        this.authorities.remove(authority);
//    }
//
//    public void removeAuthority(String authorityString) {
//        this.authorities.removeIf(authority -> authority.getAuthority().equalsIgnoreCase(authorityString));
//    }
}
