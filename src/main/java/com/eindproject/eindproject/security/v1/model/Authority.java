package com.eindproject.eindproject.security.v1.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private EAuthority name;

    public Authority() {
    }

    public Authority(EAuthority name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EAuthority getName() {
        return name;
    }

    public void setName(EAuthority name) {
        this.name = name;
    }

//    @Id
//    @Column(nullable = false)
//    private String username;
//
//    @Id
//    @Column(nullable = false)
//    private String authority;
//
//
//    //getters and setters
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getAuthority() {
//        return authority;
//    }
//
//    public void setAuthority(String authority) {
//        this.authority = authority;
//    }
//
//
//    //authority constructors
//    public Authority() {}
//    public Authority(String username, String authority) {
//        this.username = username;
//        this.authority = authority;
//    }

}
