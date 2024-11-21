package com.example.pdf.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.pdf.Model.Submission;
import com.example.pdf.Service.SubmissionService;

@RestController
@CrossOrigin
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @GetMapping("/api/submission")
    public String hii() {
        return "Hiii, I am a Student Submission Controller";
    }

    @PostMapping("/api/submission/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
                                             @RequestParam("assignmentId") long assignmentId,
                                             @RequestParam("studentId") long studentId,
                                             @RequestParam("name") String name) {
        try {
            // Get the file details
            String fileName = name + "-" + assignmentId + "." + file.getOriginalFilename().split("\\.")[1];
            String fileType = file.getContentType();
            byte[] data = file.getBytes();

            Submission submission = new Submission(assignmentId, name, studentId, 0, data,fileName,fileType);

            // Store the file (e.g., save to database)
            submissionService.storeFile(submission);

            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed!");
        }
    }

    @GetMapping("/api/submission/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        Submission submission = submissionService.getFile(id);
        
        if (submission == null) {
            return ResponseEntity.notFound().build();
        }
        String filename = submission.getName();
        if (!filename.endsWith(".pdf")) {
            filename += ".pdf"; 
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(submission.getData());
    }
    
    @GetMapping("/api/submission/view/{id}")
    public ResponseEntity<byte[]> viewFile(@PathVariable Long id) {
        Submission submission = submissionService.getFile(id);
        if (submission == null) {
            return ResponseEntity.notFound().build();
        }
        String filename = submission.getName();
        if (!filename.endsWith(".pdf")) {
            filename += ".pdf"; 
        }
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)  
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")  
                .body(submission.getData());  
    }
    
    @GetMapping("/api/submission/files")
    public ResponseEntity<List<Map<String, Object>>> getAllFiles() {
        List<Submission> submissions = submissionService.getAllFiles();

        if (submissions.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        List<Map<String, Object>> submissionResponses = submissions.stream().map(file -> {
            Map<String, Object> fileData = new HashMap<>();
            fileData.put("id", file.getSubmission_Id());
            fileData.put("fileName", file.getName());
            fileData.put("assignmentId", file.getAssginmentId());
            fileData.put("studentId", file.getStudentId());
            fileData.put("marks", file.getMarks());
            return fileData;
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(submissionResponses);
    }

    @PutMapping("/api/submission/update/{id}")
    public ResponseEntity<String> updateMarks(
            @PathVariable("id") Long submissionId,
            @RequestBody Map<String, Integer> request) {
        try {
            int marks = request.get("marks");

            submissionService.updateMarks(submissionId, marks);

            return ResponseEntity.ok("Marks updated successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Failed to update marks: " + e.getMessage());
        }
    }

  
}
