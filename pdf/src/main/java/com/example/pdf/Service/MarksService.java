package com.example.pdf.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pdf.Reposi.MarksJpaRepo;

@Service
public class MarksService {
    @Autowired
    MarksJpaRepo MJR;

}
