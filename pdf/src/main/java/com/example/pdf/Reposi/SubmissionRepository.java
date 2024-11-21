package com.example.pdf.Reposi;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pdf.Model.Submission;


public interface SubmissionRepository extends JpaRepository<Submission, Long> {    
    
}
