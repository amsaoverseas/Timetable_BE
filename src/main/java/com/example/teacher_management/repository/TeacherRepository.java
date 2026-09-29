package com.example.teacher_management.repository;

import com.example.teacher_management.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Optional<Teacher> findByEmailIgnoreCase(String email);

    List<Teacher> findByDepartmentIgnoreCase(String department);

    List<Teacher> findBySubjectIgnoreCase(String subject);
}
