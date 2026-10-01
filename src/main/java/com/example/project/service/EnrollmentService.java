package com.example.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.project.entity.Enrollment;
import com.example.project.repository.EnrollmentRepository;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public Enrollment createEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Enrollment getEnrollmentById(Long id) {
        Optional<Enrollment> enrollment = enrollmentRepository.findById(id);
        return enrollment.orElse(null);
    }

    public Enrollment updateEnrollment(Long id, Enrollment enrollment) {
        Optional<Enrollment> existing = enrollmentRepository.findById(id);

        if (existing.isPresent()) {
            Enrollment updated = existing.get();
            updated.setStudent(enrollment.getStudent());
            updated.setCourse(enrollment.getCourse());
            return enrollmentRepository.save(updated);
        }

        return null;
    }

    public boolean deleteEnrollment(Long id) {
        if (enrollmentRepository.existsById(id)) {
            enrollmentRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public List<Enrollment> getEnrollmentsByStudent(Long studentId) {
        return enrollmentRepository.findByStudentId(studentId);
    }

    public List<Enrollment> getEnrollmentsByCourse(Long courseId) {
        return enrollmentRepository.findByCourseId(courseId);
    }
}