package com.example.student.service;

import com.example.student.dao.StudentServiceDao;
import com.example.student.dto.StudentDto;
import com.example.student.model.StudentEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StudentServiceDao studentServiceDao;

    public Object createStudent(StudentDto stdDto) {
        Map<String, Object> response = new HashMap<>();
        try
        {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setName(stdDto.getName());
            studentEntity.setBirthDay(stdDto.getBirthDay());
            studentEntity.setBirthMonth(stdDto.getBirthMonth());
            studentEntity.setBirthYear(stdDto.getBirthYear());
            studentEntity.setAge(stdDto.getAge());
            studentServiceDao.save(studentEntity);
            response.put("id", studentEntity.getId());
        } catch (Exception e) {
            response.put("Error {}", e.getMessage());
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage());
        }
        return response;
    }

    @Override
    public Object getAllStudentDetails() {
        return studentServiceDao.findAll();
    }

    @Override
    public List<StudentEntity> getStudentsBetween18And25(int minAge, int maxAge) {
        return studentServiceDao.findByAgeBetween(minAge,maxAge);
    }

    @Override
    public Object calculateAndUpdateAge(Long studentId) {
        StudentEntity student = studentServiceDao.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        student.setAge(student.calculateAge());
        return studentServiceDao.save(student);
    }
}
