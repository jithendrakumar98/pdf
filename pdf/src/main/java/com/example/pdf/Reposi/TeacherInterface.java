package com.example.pdf.Reposi;

import java.util.List;

import com.example.pdf.Model.Teachermodel;



public interface TeacherInterface {
    List<Teachermodel> getAllUsers();
    Teachermodel saveUser(Teachermodel newUser);
    boolean login(long id, String password);
}
