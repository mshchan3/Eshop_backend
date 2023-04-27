package com.fsse2212.eshop.data.user;

import jakarta.persistence.*;

@Entity(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;
    private String firebaseUid;
    private String email;

    public UserEntity(FirebaseUserData userData) {
        this.firebaseUid = userData.getFirebaseUid();
        this.email = userData.getEmail();
    }

    public UserEntity() {

    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getFirebaseUid() {
        return firebaseUid;
    }

    public void setFirebaseUid(String firebaseUid) {
        this.firebaseUid = firebaseUid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
