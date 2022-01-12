package com.eindproject.eindproject.security.v1.model;

import javax.persistence.*;

@Entity
public class Feedback {

    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1024)
    private String feedback;

    //relations
    @ManyToOne
    @JoinColumn(name="fileDB_id")
    private FileDB fileDB;

    //constructors
    public Feedback() {
    }

    //getters&setters
    public FileDB getFileDB() {
        return fileDB;
    }

    public void setFileDB(FileDB fileDB) {
        this.fileDB = fileDB;
    }

    public Feedback(String feedback) {
        this.feedback = feedback;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
