package com.example.assessment.dao;

import com.example.assessment.model.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvoiceServiceDao extends JpaRepository<InvoiceEntity,Long> {
    List<InvoiceEntity> findByStatusAndDueDateBefore(String status, LocalDate dueDate);
}
