package com.example.pdf.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class UserModel {

    @Id
    @Column(name = "USERID")
    private long userID;

    @Column(name = "NAME")
    private String name;

    @Column(name = "YEAR")
    private int year;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "MOBILENO")
    private String mobileNo;

    @Column(name = "PASSWORD")
    private String password;

    @Lob  
    @Column(name = "IMAGE")
    private byte[] image;

    public UserModel() {
        
    }

    public UserModel(long userID, String name, String email, int year, String mobileNo, String password, byte[] image) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.year = year;
        this.mobileNo = mobileNo;
        this.password = password;
        this.image = image;
    }
    public UserModel(long userID, String name, String email, int year, String mobileNo, String password) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.year = year;
        this.mobileNo = mobileNo;
        this.password = password;
        this.image = null; 
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
