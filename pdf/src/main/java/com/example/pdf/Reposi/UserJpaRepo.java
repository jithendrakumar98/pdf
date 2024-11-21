package com.example.pdf.Reposi;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pdf.Model.UserModel;
public interface  UserJpaRepo extends JpaRepository<UserModel, Integer>{
    UserModel findByUserID(long userID);
}
