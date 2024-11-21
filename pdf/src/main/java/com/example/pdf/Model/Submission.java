package com.example.pdf.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Submission {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    long Submission_Id;
    long AssginmentId;
    long StudentId;
    String Name;
    int marks;
    @Lob
    private byte[] data;
    String file_name;
    String file_type;
    public Submission() {
    }

    public Submission(long AssginmentId, String Name, long StudentId, long Submission_Id, byte[] data,String file_name,String file_type ) {
        this.AssginmentId = AssginmentId;
        this.Name = Name;
        this.StudentId = StudentId;
        this.Submission_Id = Submission_Id;
        this.data = data;
        this.file_name = file_name;
        this.file_type=file_type;

    }

    public Submission(int marks) {
        this.marks = marks;
    }

    public long getSubmission_Id() {
        return Submission_Id;
    }

    public void setSubmission_Id(long Submission_Id) {
        this.Submission_Id = Submission_Id;
    }

    public long getAssginmentId() {
        return AssginmentId;
    }

    public void setAssginmentId(long AssginmentId) {
        this.AssginmentId = AssginmentId;
    }

    public long getStudentId() {
        return StudentId;
    }

    public void setStudentId(long StudentId) {
        this.StudentId = StudentId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }
    


}
