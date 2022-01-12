package com.eindproject.eindproject.security.v1.model;

import javax.persistence.*;

@Entity
@Table(name = "user_profile")
public class UserProfile {

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String firstName;
    private String lastName;
    private int age;
    private String address;
    private String postalCode;
    private String country;
    private String phoneNumber;
    private String message;
    private String sessionType;
    private Boolean isPregnant;
    private Boolean hadAcu;
    private String reference;
    private Boolean agrees;

    //relations
    @OneToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    //constructors
    public UserProfile() {
    }

    public UserProfile(String email, String firstName, String lastName, int age, String address, String postalCode, String country, String phoneNumber, String message, String sessionType, Boolean isPregnant, Boolean hadAcu, String reference, Boolean agrees, User user) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.address = address;
        this.postalCode = postalCode;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.sessionType = sessionType;
        this.isPregnant = isPregnant;
        this.hadAcu = hadAcu;
        this.reference = reference;
        this.agrees = agrees;
        this.user = user;
    }

    //getters & setters
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSessionType() {
        return sessionType;
    }

    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
    }

    public Boolean getPregnant() {
        return isPregnant;
    }

    public void setPregnant(Boolean pregnant) {
        isPregnant = pregnant;
    }

    public Boolean getHadAcu() {
        return hadAcu;
    }

    public void setHadAcu(Boolean hadAcu) {
        this.hadAcu = hadAcu;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Boolean getAgrees() {
        return agrees;
    }

    public void setAgrees(Boolean agrees) {
        this.agrees = agrees;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
