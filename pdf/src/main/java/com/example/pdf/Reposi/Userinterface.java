package com.example.pdf.Reposi;

import java.util.List; 

import com.example.pdf.Model.UserModel;

public interface Userinterface {
    List<UserModel> getAllUsers();
    UserModel saveUser(UserModel newUser);
    boolean login(long id, String password);
    
}
