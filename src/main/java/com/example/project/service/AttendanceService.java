package com.example.project.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.project.entity.Attendance;
import com.example.project.repository.AttendanceRepository;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    public Attendance createAttendance(Attendance attendance) {
        attendance.setId(null);
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAllAttendance() {
        return attendanceRepository.findAll();
    }

    public Optional<Attendance> getAttendanceById(Long id) {
        return attendanceRepository.findById(id);
    }

    public Attendance updateAttendance(Long id, Attendance attendance) {

        Optional<Attendance> existingAttendance =
                attendanceRepository.findById(id);

        if (existingAttendance.isPresent()) {

            Attendance existing = existingAttendance.get();

            existing.setAttendanceDate(attendance.getAttendanceDate());
            existing.setStatus(attendance.getStatus());
            existing.setStudent(attendance.getStudent());
            existing.setCourse(attendance.getCourse());

            return attendanceRepository.save(existing);
        }

        return null;
    }

    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }

    public List<Attendance> getAttendanceByStudent(Long studentId) {
        return attendanceRepository.findByStudentId(studentId);
    }

    public List<Attendance> getAttendanceByCourse(Long courseId) {
        return attendanceRepository.findByCourseId(courseId);
    }
}
