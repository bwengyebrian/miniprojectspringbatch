package edu.mum.cs.ea.mini.projec1.springbatch610158.controller;

import edu.mum.cs.ea.mini.projec1.springbatch610158.model.Student;
import edu.mum.cs.ea.mini.projec1.springbatch610158.repository.StudentRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    JobLauncher jobLauncher;


    @Autowired
    @Qualifier("studentJob")
    Job importUserJob;

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping("/process")
    public List<Student> handle() throws Exception{
        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(importUserJob, jobParameters);
        return studentRepository.findAll();
    }
    @RequestMapping("/")
    public String home(){
        return "Application Running Successfully!: to process the batch Run Command in the terminal \n $ curl localhost:8080/process -u user:1234\n";
    }
}
