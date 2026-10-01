package com.example.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.entity.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {


    List<Exam> findByCourseId(Long courseId);
}
