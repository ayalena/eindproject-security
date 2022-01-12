package com.eindproject.eindproject.security.v1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "files")
public class FileDB {

//    FileDB is the data model corresponding to files table in database

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    private byte[] data;

    //relations
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "fileDB", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Feedback> feedback;

    //constructors
    public FileDB() {
    }

    public FileDB(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    //getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Feedback> getFeedback() {
        return feedback;
    }

    public void setFeedback(List<Feedback> feedback) {
        this.feedback = feedback;
    }

    public void add(FileDB fileDB) {
    }
}
