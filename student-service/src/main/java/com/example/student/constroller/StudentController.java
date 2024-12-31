package com.example.student.constroller;

import com.example.student.dto.StudentDto;
import com.example.student.model.StudentEntity;
import com.example.student.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //sample api
    @GetMapping("/hello")
    public String helloworld(){
        return "Hello world";
    }


    //create student details
    @PostMapping("/createstudent")
    public ResponseEntity<Object> createStudent(@RequestBody StudentDto student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(student));
    }

    // Get all students
    @GetMapping("/getstudentdetails")
    public ResponseEntity<Object> getAllStudents() {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudentDetails());
    }

    // Get students between 18 and 25 years
    @GetMapping("/agerange")
    public List<StudentEntity> getStudentsBetween18And25(@RequestParam int minAge, @RequestParam int maxAge) {
        return studentService.getStudentsBetween18And25(minAge,maxAge);
    }

    // Calculate age and update student table
    @PutMapping("/{id}/age")
    public ResponseEntity<Object> updateStudentAge(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(studentService.calculateAndUpdateAge(id));
    }

}
