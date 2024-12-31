package com.example.student.dao;


import com.example.student.model.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentServiceDao extends JpaRepository<StudentEntity, Long> {

   /* @Query("SELECT s FROM StudentEntity s WHERE s.age BETWEEN :minAge AND :maxAge")
    List<StudentEntity> findStudentsBetweenAges(@Param("minAge") int minAge, @Param("maxAge") int maxAge);*/

    List<StudentEntity> findByAgeBetween(int minAge, int maxAge);
}
