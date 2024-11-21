package com.example.pdf.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.pdf.Model.Teachermodel;
import com.example.pdf.Service.TeaacherService;

@RestController
@CrossOrigin
public class TeacherController {

    @Autowired
    private TeaacherService teacherService;

    @GetMapping("/teacher")
    public String hi() {
        return "Hiii";
    }

    @GetMapping("/teacher/cal")
    public int cal(@RequestParam int a, @RequestParam int b) {
        return a + b;
    }

    @GetMapping("/teachers/getteachers")
    public List<Teachermodel> getTeachers() {
        return teacherService.getAllUsers();
    }

    @PostMapping("/teachers/login")
    public boolean login(@RequestBody Teachermodel request) {
        return teacherService.login(request.getTeacherId(), request.getPassword());
    }

    @PostMapping("/teachers/post")
    public ResponseEntity<Teachermodel> addTeacher(@RequestBody Teachermodel newTeacher) {
        Teachermodel teacher = teacherService.saveUser(newTeacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacher);
    }

    @PostMapping("/teachers/postMultiple")
    public ResponseEntity<List<Teachermodel>> addTeachers(@RequestBody List<Teachermodel> newTeachers) {
        List<Teachermodel> teachers = teacherService.saveUsers(newTeachers);
        return ResponseEntity.status(HttpStatus.CREATED).body(teachers);
    }
    @GetMapping("/teachers/{teacherId}")
    public ResponseEntity<Teachermodel> getTeacherById(@PathVariable long teacherId) {
        Optional<Teachermodel> teacher = teacherService.getUserById(teacherId);
        if (teacher.isPresent()) {
            return ResponseEntity.ok(teacher.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/teachers/count")
    public ResponseEntity<Long> getTeachersCount() {
        long count = teacherService.getUsersCount();
        return ResponseEntity.ok(count);
    }
}
