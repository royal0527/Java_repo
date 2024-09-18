package com.example.assessment.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class InvoiceEntityTest {

    private InvoiceEntity invoice;

    @BeforeEach
    public void setUp() {
        invoice = new InvoiceEntity();
    }

    @Test
    public void testInvoiceEntityDefaultConstructor() {
        assertNotNull(invoice);
        assertNull(invoice.getId());
        assertNull(invoice.getAmount());
        assertNull(invoice.getPaidAmount());
        assertNull(invoice.getDueDate());
        assertNull(invoice.getStatus());
        assertNull(invoice.getLateFee());
        assertNull(invoice.getOverDueDays());
    }

    @Test
    public void testInvoiceEntityParameterizedConstructor() {
        LocalDate dueDate = LocalDate.of(2023, 12, 31);
        InvoiceEntity invoiceParam = new InvoiceEntity(1L, 500.0, 300.0, dueDate, "Pending", 50.0, 10);

        assertEquals(1L, invoiceParam.getId());
        assertEquals(500.0, invoiceParam.getAmount());
        assertEquals(300.0, invoiceParam.getPaidAmount());
        assertEquals(dueDate, invoiceParam.getDueDate());
        assertEquals("Pending", invoiceParam.getStatus());
        assertEquals(50.0, invoiceParam.getLateFee());
        assertEquals(10, invoiceParam.getOverDueDays());
    }

    @Test
    public void testSettersAndGetters() {
        invoice.setId(1L);
        invoice.setAmount(1000.0);
        invoice.setPaidAmount(800.0);
        LocalDate dueDate = LocalDate.of(2023, 12, 31);
        invoice.setDueDate(dueDate);
        invoice.setStatus("Overdue");
        invoice.setLateFee(25.0);
        invoice.setOverDueDays(5);

        assertEquals(1L, invoice.getId());
        assertEquals(1000.0, invoice.getAmount());
        assertEquals(800.0, invoice.getPaidAmount());
        assertEquals(dueDate, invoice.getDueDate());
        assertEquals("Overdue", invoice.getStatus());
        assertEquals(25.0, invoice.getLateFee());
        assertEquals(5, invoice.getOverDueDays());
    }

    @Test
    public void testToString() {
        invoice.setId(1L);
        invoice.setAmount(1000.0);
        invoice.setPaidAmount(800.0);
        LocalDate dueDate = LocalDate.of(2023, 12, 31);
        invoice.setDueDate(dueDate);
        invoice.setStatus("Overdue");
        invoice.setLateFee(25.0);
        invoice.setOverDueDays(5);

        String expected = "{id=1, amount=1000.0, paidAmount=800.0, dueDate=2023-12-31, status='Overdue', lateFee=25.0, overDueDays=5}";
        assertEquals(expected, invoice.toString());
    }
}
