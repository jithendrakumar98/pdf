package com.example.pdf.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pdf.Model.Teachermodel;
import com.example.pdf.Reposi.TeacherJpaRepo;

@Service
public class TeaacherService {

    @Autowired
    private TeacherJpaRepo TJP;

    public List<Teachermodel> getAllUsers() {
        List<Teachermodel> users = TJP.findAll();
        users.forEach(user -> user.setPassword(null));
        return users;
    }

    public Teachermodel saveUser(Teachermodel newUser) {
        String hashedPassword = simpleHash(newUser.getPassword());
        newUser.setPassword(hashedPassword);
        return TJP.save(newUser);
    }

    public boolean login(long id, String password) {
        Teachermodel user = TJP.findByTeacherId(id);
        if (user != null) {
            String hashedInputPassword = simpleHash(password);
            return hashedInputPassword.equals(user.getPassword());
        }
        return false;
    }

    private String simpleHash(String input) {
        StringBuilder hash = new StringBuilder();
        for (char c : input.toCharArray()) {
            hash.append((char) (c + 3));
        }
        return hash.toString();
    }

    public Optional<Teachermodel> getUserById(long teacherId) {
        return TJP.findById(teacherId);
    }
     public List<Teachermodel> saveUsers(List<Teachermodel> newUsers) {
        for (Teachermodel user : newUsers) {
            String hashedPassword = simpleHash(user.getPassword());
            user.setPassword(hashedPassword);
        }
        return TJP.saveAll(newUsers);
    }
    public long getUsersCount() {
        return TJP.count(); 
    }
}
