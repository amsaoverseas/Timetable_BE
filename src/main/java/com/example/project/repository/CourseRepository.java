package com.example.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.project.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}