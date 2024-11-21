package com.example.pdf.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pdf.Model.Submission;
import com.example.pdf.Reposi.SubmissionRepository;

@Service
public class SubmissionService {

    @Autowired
    private SubmissionRepository SR;

    public void storeFile(Submission file) {
        SR.save(file);
    }

    public List<Submission> getAllFiles() {
        return SR.findAll();
    }

    public Submission getFile(Long submissionId) {
        return SR.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("File not found"));
    }

    public Submission updateMarks(Long submissionId, int marks) {
        Submission submission = SR.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        submission.setMarks(marks);
        return SR.save(submission);
    }
}
