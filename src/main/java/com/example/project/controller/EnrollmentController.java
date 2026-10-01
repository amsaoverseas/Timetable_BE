package com.example.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.project.entity.Enrollment;
import com.example.project.service.EnrollmentService;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<Enrollment> createEnrollment(@RequestBody Enrollment enrollment) {
        return new ResponseEntity<>(
                enrollmentService.createEnrollment(enrollment),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable("id")Long id) {
        Enrollment enrollment = enrollmentService.getEnrollmentById(id);

        if (enrollment == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(enrollment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> updateEnrollment(
            @PathVariable Long id,
            @RequestBody Enrollment enrollment) {

        Enrollment updated = enrollmentService.updateEnrollment(id, enrollment);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {

        if (!enrollmentService.deleteEnrollment(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByStudent(
            @PathVariable Long studentId) {
        return ResponseEntity.ok(
                enrollmentService.getEnrollmentsByStudent(studentId)
        );
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByCourse(
            @PathVariable Long courseId) {
        return ResponseEntity.ok(
                enrollmentService.getEnrollmentsByCourse(courseId)
        );
    }
}