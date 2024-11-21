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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.pdf.Model.PdfFile;
import com.example.pdf.Service.PdfFileService;

@RestController
@CrossOrigin
public class PdfFileController {

    @Autowired
    private PdfFileService pdfFileService;

    @GetMapping("/api/pdf")
    public String hii() {
        return "Hiii i am teacher";
    }

    @PostMapping("/api/pdf/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("subject") String subject,
            @RequestParam("description") String description,
            @RequestParam("dueDate") String dueDate,
            @RequestParam("year") int year) {
        try {
            String fileName = title + "-" + subject + "." + file.getOriginalFilename().split("\\.")[1];
            String fileType = file.getContentType();
            byte[] data = file.getBytes();
            PdfFile pdfFile = new PdfFile(fileName, fileType, data, title, subject, description, dueDate, year);
            pdfFileService.storeFile(pdfFile);

            return ResponseEntity.status(HttpStatus.OK).body("File uploaded successfully!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed!");
        }
    }

    @GetMapping("/api/pdf/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {
        PdfFile pdfFile = pdfFileService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pdfFile.getFileName() + "\"")
                .body(pdfFile.getData());
    }

    @GetMapping("/api/pdf/view/{id}")
    public ResponseEntity<byte[]> viewFile(@PathVariable Long id) {
        PdfFile pdfFile = pdfFileService.getFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + pdfFile.getFileName() + "\"")
                .body(pdfFile.getData());
    }

    @GetMapping("/api/pdf/files")
    public ResponseEntity<List<Map<String, Object>>> getAllFiles() {
        List<PdfFile> pdfFiles = pdfFileService.getAllFiles();

        if (pdfFiles.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        List<Map<String, Object>> pdfFileResponses = pdfFiles.stream().map(file -> {
            Map<String, Object> fileData = new HashMap<>();
            fileData.put("id", file.getId());
            fileData.put("fileName", file.getFileName());
            fileData.put("title", file.getTitle());
            fileData.put("subject", file.getSubject());
            fileData.put("description", file.getDescription());
            fileData.put("dueDate", file.getDueDate());
            fileData.put("year", file.getYear());
            return fileData;
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(pdfFileResponses);
    }

    @DeleteMapping("/api/pdf/delete/{id}")
    public ResponseEntity<String> deleteFile(@PathVariable Long id) {
        boolean isDeleted = pdfFileService.deleteFile(id);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body("File deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found!");
        }
    }
}
