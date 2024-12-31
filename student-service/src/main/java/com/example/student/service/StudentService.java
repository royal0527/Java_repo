package com.example.student.service;


import com.example.student.dto.StudentDto;
import com.example.student.model.StudentEntity;

import java.util.List;

public interface StudentService {


     Object createStudent(StudentDto studentDto);

    Object getAllStudentDetails();

    List<StudentEntity> getStudentsBetween18And25(int minAge, int maxAge);

    Object calculateAndUpdateAge(Long studentId);
}
