package com.example.assessment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoice")
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount",nullable = false)
    private Double amount;

    @Column(name = "paid_amount",nullable = true)
    private Double paidAmount;

    @Column(name = "due_date",nullable = true)
    @Temporal(TemporalType.DATE)
    private LocalDate dueDate;

    @Column(name = "status",nullable = true)
    private String status;

    @Column(name = "late_fee",nullable = true)
    private Double lateFee;

    @Column(name = "overdue_days",nullable = true)
    private Integer overDueDays;

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", amount=" + amount +
                ", paidAmount=" + paidAmount +
                ", dueDate=" + dueDate +
                ", status='" + status + '\'' +
                ", lateFee=" + lateFee +
                ", overDueDays=" + overDueDays +
                '}';
    }
}
