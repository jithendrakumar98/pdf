package com.example.pdf.Model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Teachermodel {
    @Id
    @Column(name = "teacherId")
    private long teacherId;

    @Column(name = "NAME")
    private String name;
    @Column(name = "EMAIL")
    private String email;
    
    @Column(name = "MOBILENO")
    private String mobileNo;

    @Column(name = "PASSWORD")
    private String password;

    private String Subject;
    public Teachermodel() {
    }

    public Teachermodel(String email, String mobileNo, String name, String password, long teacherId,String Subject) {
        this.email = email;
        this.mobileNo = mobileNo;
        this.name = name;
        this.password = password;
        this.teacherId = teacherId;
        this.Subject=Subject;
    }
    
    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
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

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

    

}
