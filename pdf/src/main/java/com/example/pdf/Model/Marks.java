package com.example.pdf.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Marks {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    long id;
    String ExamName;
    String SubjectName;
    int Marks;
    long StudentId;
    int Sem;
    public Marks() {
    }

    public Marks(String ExamName, int Marks, long StudentId, String SubjectName, long id,int Sem) {
        this.ExamName = ExamName;
        this.Marks = Marks;
        this.StudentId = StudentId;
        this.SubjectName = SubjectName;
        this.id = id;
        this.Sem = Sem;
    }
    


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getExamName() {
        return ExamName;
    }

    public void setExamName(String ExamName) {
        this.ExamName = ExamName;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String SubjectName) {
        this.SubjectName = SubjectName;
    }

    public int getMarks() {
        return Marks;
    }

    public void setMarks(int Marks) {
        this.Marks = Marks;
    }

    public long getStudentId() {
        return StudentId;
    }

    public void setStudentId(long StudentId) {
        this.StudentId = StudentId;
    }

    public int getSem() {
        return Sem;
    }

    public void setSem(int Sem) {
        this.Sem = Sem;
    }

}
