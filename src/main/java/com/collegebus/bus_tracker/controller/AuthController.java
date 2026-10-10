package com.collegebus.bus_tracker.controller;

import com.collegebus.bus_tracker.model.Student;
import com.collegebus.bus_tracker.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final StudentService studentService;

    // Default Admin Credentials
    private static final String ADMIN_USER = "admin";
    private static final String ADMIN_PASS = "admin123";

    @Autowired
    public AuthController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> credentials) {
        String role = credentials.get("role"); // "admin" or "student"
        String username = credentials.get("username");
        String password = credentials.get("password");

        Map<String, Object> response = new HashMap<>();

        if ("admin".equalsIgnoreCase(role)) {
            if (ADMIN_USER.equalsIgnoreCase(username) && ADMIN_PASS.equals(password)) {
                response.put("authenticated", true);
                response.put("role", "ADMIN");
                response.put("name", "Transport Administrator");
                response.put("username", ADMIN_USER);
                return ResponseEntity.ok(response);
            } else {
                response.put("authenticated", false);
                response.put("message", "Invalid Admin username or password");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        } else {
            // Student login
            Optional<Student> studentOpt = studentService.authenticate(username, password);
            if (studentOpt.isPresent()) {
                Student student = studentOpt.get();
                response.put("authenticated", true);
                response.put("role", "STUDENT");
                response.put("student", student);
                return ResponseEntity.ok(response);
            } else {
                response.put("authenticated", false);
                response.put("message", "Invalid Student ID or password");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }
        }
    }
}
