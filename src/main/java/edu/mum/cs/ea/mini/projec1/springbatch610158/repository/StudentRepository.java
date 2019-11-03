package edu.mum.cs.ea.mini.projec1.springbatch610158.repository;

import edu.mum.cs.ea.mini.projec1.springbatch610158.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{
}
