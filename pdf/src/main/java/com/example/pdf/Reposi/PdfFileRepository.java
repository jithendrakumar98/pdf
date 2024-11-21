package com.example.pdf.Reposi;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.pdf.Model.PdfFile;

public interface PdfFileRepository extends JpaRepository<PdfFile, Long> {
}
