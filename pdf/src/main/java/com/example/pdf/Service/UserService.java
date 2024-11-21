package com.example.pdf.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pdf.Model.UserModel;
import com.example.pdf.Reposi.UserJpaRepo;
import com.example.pdf.Reposi.Userinterface;

@Service
public class UserService implements Userinterface {
    
    @Autowired
    UserJpaRepo UJR;

    @Override
    public List<UserModel> getAllUsers() {
        List<UserModel> users = UJR.findAll();
        List<UserModel> sanitizedUsers = new ArrayList<>();
        for (UserModel user : users) {
            user.setPassword(null); 
            sanitizedUsers.add(user);
        }
        
        return sanitizedUsers; 
    }
    

    @Override
    public UserModel saveUser(UserModel newUser) {
        System.out.println(newUser.getUserID());
        String hashedPassword = simpleHash(newUser.getPassword());
        newUser.setPassword(hashedPassword);
        return UJR.save(newUser);
    }

    @Override
    public boolean login(long id, String password) {
        UserModel user = UJR.findByUserID(id);
        if (user != null) {
            String hashedInputPassword = simpleHash(password);    
            return hashedInputPassword.equals(user.getPassword());
        }
        System.out.println("User not found with ID: " + id);
        return false;
    }


    public List<UserModel> saveUsers(List<UserModel> newUsers) {
        for (UserModel user : newUsers) {
            String hashedPassword = simpleHash(user.getPassword());
            user.setPassword(hashedPassword);
        }
        return UJR.saveAll(newUsers);
    }
    public long getUsersCount() {
        return UJR.count(); 
    }
    public UserModel getUserById(Long userID) {
        UserModel user = UJR.findByUserID(userID);
        if (user != null) {
            user.setPassword(null); 
        }
        return user;
    }
    public List<String> getMailsByYear(int year) {
    List<UserModel> users = UJR.findAll(); 
    return users.stream()
            .filter(user -> user.getYear() == year)
            .map(UserModel::getEmail) 
            .collect(Collectors.toList()); 
}

    

    private String simpleHash(String input) {
        StringBuilder hash = new StringBuilder();
        for (char c : input.toCharArray()) {
            hash.append((char)(c + 3));  
        }
        return hash.toString();
    }
}
