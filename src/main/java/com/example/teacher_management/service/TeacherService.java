package com.example.teacher_management.service;

import com.example.teacher_management.exception.DuplicateEmailException;
import com.example.teacher_management.exception.ResourceNotFoundException;
import com.example.teacher_management.model.Teacher;
import com.example.teacher_management.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    // CREATE
    public Teacher createTeacher(Teacher teacher) {
        teacherRepository.findByEmailIgnoreCase(teacher.getEmail()).ifPresent(existing -> {
            throw new DuplicateEmailException("A teacher with email " + teacher.getEmail() + " already exists");
        });
        return teacherRepository.save(teacher);
    }


    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }


    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Teacher not found with id: " + id));
    }


    public List<Teacher> getTeachersByDepartment(String department) {
        return teacherRepository.findByDepartmentIgnoreCase(department);
    }


    public List<Teacher> getTeachersBySubject(String subject) {
        return teacherRepository.findBySubjectIgnoreCase(subject);
    }


    public Teacher updateTeacher(Long id, Teacher updatedTeacher) {
        Teacher existing = getTeacherById(id);

        if (!existing.getEmail().equalsIgnoreCase(updatedTeacher.getEmail())) {
            teacherRepository.findByEmailIgnoreCase(updatedTeacher.getEmail()).ifPresent(other -> {
                throw new DuplicateEmailException("A teacher with email " + updatedTeacher.getEmail() + " already exists");
            });
        }

        existing.setFirstName(updatedTeacher.getFirstName());
        existing.setLastName(updatedTeacher.getLastName());
        existing.setEmail(updatedTeacher.getEmail());
        existing.setSubject(updatedTeacher.getSubject());
        existing.setDepartment(updatedTeacher.getDepartment());
        existing.setQualification(updatedTeacher.getQualification());
        existing.setJoiningDate(updatedTeacher.getJoiningDate());
        return teacherRepository.save(existing);
    }

    public void deleteTeacher(Long id) {
        Teacher existing = getTeacherById(id);
        teacherRepository.delete(existing);
    }
}
