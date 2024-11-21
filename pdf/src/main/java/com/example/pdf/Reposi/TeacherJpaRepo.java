package com.example.pdf.Reposi;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pdf.Model.Teachermodel;



public interface  TeacherJpaRepo extends JpaRepository<Teachermodel, Long> {
    Teachermodel findByTeacherId(Long teacherId);
}
