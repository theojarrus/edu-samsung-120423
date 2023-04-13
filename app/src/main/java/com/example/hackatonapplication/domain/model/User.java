package com.example.hackatonapplication.domain.model;

public class User {

    private long id;
    private String name;
    private String email;
    private String photoUrl;

    public User(long id, String name, String email, String photoUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.photoUrl = photoUrl;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
}
