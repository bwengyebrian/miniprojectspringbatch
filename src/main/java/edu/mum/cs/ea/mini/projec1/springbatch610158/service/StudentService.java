package edu.mum.cs.ea.mini.projec1.springbatch610158.service;

import edu.mum.cs.ea.mini.projec1.springbatch610158.model.Student;

public interface StudentService {
    Student saveStudent(Student student);
    void processStudentBatch(String filename);
}
