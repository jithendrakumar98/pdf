package com.example.pdf.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.example.pdf.Model.PdfFile;
import com.example.pdf.Reposi.PdfFileRepository;
@Service
public class PdfFileService {

    @Autowired
    private PdfFileRepository pdfFileRepository;

    public void storeFile(PdfFile file) {
        pdfFileRepository.save(file);
    }
    public List<PdfFile> getAllFiles() {
        return pdfFileRepository.findAll();  
    }
    public PdfFile getFile(Long id) {
        return pdfFileRepository.findById(id).orElseThrow(() -> new RuntimeException("File not found"));
    }
    public boolean deleteFile(Long id) {
        Optional<PdfFile> file = pdfFileRepository.findById(id);
        if (file.isPresent()) {
            pdfFileRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
