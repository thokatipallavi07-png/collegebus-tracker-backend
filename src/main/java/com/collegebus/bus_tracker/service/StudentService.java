package com.collegebus.bus_tracker.service;

import com.collegebus.bus_tracker.model.Student;
import com.collegebus.bus_tracker.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Optional<Student> getByStudentId(String studentId) {
        return studentRepository.findByStudentId(studentId);
    }

    public Optional<Student> authenticate(String studentId, String password) {
        return studentRepository.findByStudentIdAndPassword(studentId, password);
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(Long id, Student details) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));

        student.setName(details.getName());
        student.setEmail(details.getEmail());
        student.setBranch(details.getBranch());
        student.setAssignedBusNumber(details.getAssignedBusNumber());
        student.setAssignedRoute(details.getAssignedRoute());
        student.setBoardingStop(details.getBoardingStop());
        student.setPhone(details.getPhone());
        if (details.getPassword() != null && !details.getPassword().isEmpty()) {
            student.setPassword(details.getPassword());
        }

        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
