package com.example.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project.entity.Course;
import com.example.project.repository.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public Course createCourse(Course course) {
        return repository.save(course);
    }

    public List<Course> getAllCourses() {
        return repository.findAll();
    }

    public Course getCourseById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    public Course updateCourse(Long id, Course course) {
        Course existingCourse = getCourseById(id);

        existingCourse.setName(course.getName());
        existingCourse.setCode(course.getCode());
        existingCourse.setDescription(course.getDescription());

        return repository.save(existingCourse);
    }

    public void deleteCourse(Long id) {
        Course existingCourse = getCourseById(id);
        repository.delete(existingCourse);
    }
}