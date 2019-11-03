package edu.mum.cs.ea.mini.projec1.springbatch610158.service.implementation;

import edu.mum.cs.ea.mini.projec1.springbatch610158.model.Student;
import edu.mum.cs.ea.mini.projec1.springbatch610158.repository.StudentRepository;
import edu.mum.cs.ea.mini.projec1.springbatch610158.service.StudentService;

public class StudentServiceImplementation implements StudentService {
    private StudentRepository repository;
    @Override
    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    @Override
    public void processStudentBatch(String filename) {

    }
}
