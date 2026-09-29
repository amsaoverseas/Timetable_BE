package com.example.project;

import java.util.List;

public interface CourseRepository {

    List<Course> getAllCourses();

    Course getCourseById(int id);

    Course addCourse(Course course);

    Course updateCourse(int id, Course course);

    void deleteCourse(int id);
}