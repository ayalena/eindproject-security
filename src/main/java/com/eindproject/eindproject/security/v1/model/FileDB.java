package com.eindproject.eindproject.security.v1.model;

import javax.persistence.*;

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


}
