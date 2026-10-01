package com.example.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.project.entity.Course;
import com.example.project.repository.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course) {
        course.setId(null);
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Course updateCourse(Long id, Course course) {

        Optional<Course> existingCourse = courseRepository.findById(id);

        if (existingCourse.isPresent()) {
            Course existing = existingCourse.get();

            existing.setCourseName(course.getCourseName());
            existing.setCourseCode(course.getCourseCode());
            existing.setDescription(course.getDescription());

            return courseRepository.save(existing);
        }

        return null;
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}