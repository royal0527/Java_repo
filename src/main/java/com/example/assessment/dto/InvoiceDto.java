package com.example.assessment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDto {

    private Long id;
    private Double amount;
    private Double paidAmount;
    private LocalDate dueDate;
    private String status;
    private Double lateFee;
    private Integer overDueDays;

}
