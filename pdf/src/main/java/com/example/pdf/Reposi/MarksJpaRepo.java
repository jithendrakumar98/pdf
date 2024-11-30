package com.example.pdf.Reposi;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pdf.Model.Marks;

public interface MarksJpaRepo extends  JpaRepository<Marks,Long>{
    
}
