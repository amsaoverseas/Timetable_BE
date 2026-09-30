package com.example.project.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.project.entity.Department;
import com.example.project.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") Long id){
        Optional<Department> department = departmentService.getDepartmentById(id);

        if (department.isPresent()) {
            return ResponseEntity.ok(department.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(
            @PathVariable("id")Long id,
            @RequestBody Department department) {

        try {
            return ResponseEntity.ok(
                    departmentService.updateDepartment(id, department)
            );
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteDepartment(@PathVariable("id") Long id){

        if (departmentService.deleteDepartment(id)) {
            return ResponseEntity.ok(true);
        }

        return ResponseEntity.notFound().build();
    }
}