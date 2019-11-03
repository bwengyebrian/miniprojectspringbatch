package edu.mum.cs.ea.mini.projec1.springbatch610158.service.batch;


import edu.mum.cs.ea.mini.projec1.springbatch610158.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

public class StudentItemProcessor implements ItemProcessor<Student,Student>{
    private static final Logger logger = LoggerFactory.getLogger(StudentItemProcessor.class);
    @Override
    public Student process(Student student) throws Exception {
        Integer studentNumber = student.getStudentNumber();
        String firstName = student.getFirstName().toUpperCase();
        String lastName = student.getLastName().toUpperCase();
        LocalDate dateOfBirth = LocalDate.now().minusYears(Integer.valueOf(student.getAge()));
        student.setDateOfBirth(dateOfBirth);
        Student transformedStudent = new Student(studentNumber,firstName,lastName,dateOfBirth);
        logger.info("Converting (" + student + ") into (" + transformedStudent + ")");
        return transformedStudent;
    }
}
