package com.example.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.project.entity.Course;
import com.example.project.entity.Exam;
import com.example.project.repository.CourseRepository;
import com.example.project.repository.ExamRepository;

@Service
public class ExamService {

    private final ExamRepository repository;
    private final CourseRepository courseRepository;

    public ExamService(ExamRepository repository, CourseRepository courseRepository) {
        this.repository = repository;
        this.courseRepository = courseRepository;
    }

    public Exam createExam(Exam exam) {
        Course course = resolveCourse(exam);
        exam.setCourse(course);
        return repository.save(exam);
    }

    public List<Exam> getAllExams() {
        return repository.findAll();
    }

    public Exam getExamById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Exam updateExam(Long id, Exam exam) {
        Exam existingExam = repository.findById(id).orElse(null);

        if (existingExam == null) {
            return null;
        }

        existingExam.setExamName(exam.getExamName());
        existingExam.setExamDate(exam.getExamDate());
        existingExam.setTotalMarks(exam.getTotalMarks());

        if (exam.getCourse() != null && exam.getCourse().getId() != null) {
            existingExam.setCourse(resolveCourse(exam));
        }

        return repository.save(existingExam);
    }

    public void deleteExam(Long id) {
        repository.deleteById(id);
    }

    public List<Exam> getExamsByCourse(Long courseId) {
        return repository.findByCourseId(courseId);
    }

    private Course resolveCourse(Exam exam) {
        if (exam.getCourse() == null || exam.getCourse().getId() == null) {
            throw new IllegalArgumentException("A valid course id is required to create or update an exam");
        }
        return courseRepository.findById(exam.getCourse().getId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found with id: " + exam.getCourse().getId()));
    }
}
