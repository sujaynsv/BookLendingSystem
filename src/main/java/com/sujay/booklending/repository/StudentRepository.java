package com.sujay.booklending.repository;

import com.sujay.booklending.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
