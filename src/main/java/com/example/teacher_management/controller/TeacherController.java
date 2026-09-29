package com.example.teacher_management.controller;

import com.example.teacher_management.model.Teacher;
import com.example.teacher_management.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@Valid @RequestBody Teacher teacher) {
        Teacher saved = teacherService.createTeacher(teacher);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers(
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String subject) {
        if (department != null && !department.isBlank()) {
            return ResponseEntity.ok(teacherService.getTeachersByDepartment(department));
        }
        if (subject != null && !subject.isBlank()) {
            return ResponseEntity.ok(teacherService.getTeachersBySubject(subject));
        }
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id,
                                                  @Valid @RequestBody Teacher teacher) {
        return ResponseEntity.ok(teacherService.updateTeacher(id, teacher));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
