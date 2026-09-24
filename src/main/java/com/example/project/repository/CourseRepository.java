package com.example.project.repository;

import java.util.List;

import com.example.project.model.Course;

public interface CourseRepository {

    List<Course> getAllCourses();

    Course getCourseById(int id);

    Course addCourse(Course course);

    Course updateCourse(int id, Course course);

    void deleteCourse(int id);
}