package com.example.project.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.project.entity.Exam;
import com.example.project.service.ExamService;

@RestController
@RequestMapping("/exams")
public class ExamController {

    private final ExamService service;

    public ExamController(ExamService service) {
        this.service = service;
    }

    // Create Exam -> POST /exams
    // body: {"examName":"Mid Term","examDate":"2026-01-15","totalMarks":100,"course":{"id":1}}
    @PostMapping
    public Exam createExam(@RequestBody Exam exam) {
        return service.createExam(exam);
    }

    // Get All Exams -> GET /exams
    @GetMapping
    public List<Exam> getAllExams() {
        return service.getAllExams();
    }

    // Get Exam By ID -> GET /exams/{id}
    @GetMapping("/{id}")
    public Exam getExamById(@PathVariable Long id) {
        return service.getExamById(id);
    }

    // Update Exam -> PUT /exams/{id}
    @PutMapping("/{id}")
    public Exam updateExam(
            @PathVariable Long id,
            @RequestBody Exam exam) {
        return service.updateExam(id, exam);
    }

    // Delete Exam -> DELETE /exams/{id}
    @DeleteMapping("/{id}")
    public String deleteExam(@PathVariable Long id) {
        service.deleteExam(id);
        return "Exam deleted successfully";
    }

    // Get Exams By Course -> GET /exams/course/{courseId}
    @GetMapping("/course/{courseId}")
    public List<Exam> getExamsByCourse(@PathVariable Long courseId) {
        return service.getExamsByCourse(courseId);
    }
}
