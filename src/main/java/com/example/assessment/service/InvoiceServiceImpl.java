package com.example.assessment.service;

import com.example.assessment.constants.StatusConstants;
import com.example.assessment.dao.InvoiceServiceDao;
import com.example.assessment.dto.InvoiceDto;
import com.example.assessment.model.InvoiceEntity;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final InvoiceServiceDao invoiceServiceDao;

    public Object createInvoice(InvoiceDto invoiceDto) {
        Map<String, Object> response = new HashMap<>();
        try {
            LocalDate due_date=invoiceDto.getDueDate();
            logger.info("amount {},dueDate {}",invoiceDto.getAmount(),due_date);
            InvoiceEntity invoice = new InvoiceEntity();
            invoice.setAmount(invoiceDto.getAmount());
            invoice.setDueDate(due_date);
            invoice.setPaidAmount(0.0);
            invoice.setLateFee(0.0);
            invoice.setOverDueDays(0);
            invoice.setStatus(StatusConstants.PENDING);
            invoiceServiceDao.save(invoice);
            response.put("id", invoice.getId());
        } catch (Exception e) {
            response.put("Error {}", e.getMessage());
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage());
        }
        return response;
    }

  public Object getAllInvoices() {
      Map<String, Object> response = new HashMap<>();
        try
        {
            return invoiceServiceDao.findAll();
        }catch (Exception e) {
            response.put("Error {}", e.getMessage());
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage());
        }
     }

    public Object makePayment(Long id, InvoiceDto invoiceDto) {
        Map<String, Object> response = new HashMap<>();
        try {
            Optional<InvoiceEntity> invoicesById=invoiceServiceDao.findById(id);
            if(invoicesById.isPresent()){
                InvoiceEntity invoice = invoicesById.get();
                invoice.setPaidAmount(invoiceDto.getPaidAmount());
                invoice.setStatus(StatusConstants.PAID);
                invoiceServiceDao.save(invoice);
                response.put("Payment Details ", invoice.toString());
            }
        } catch (Exception e) {
            response.put("Error {}", e.getMessage());
            throw new RuntimeException("An unexpected error occurred: " + e.getMessage());
        }
        return response;
    }

    public void processDues(InvoiceDto invoiceDto) {
        LocalDate overdueDate = LocalDate.now().minusDays(invoiceDto.getOverDueDays());
        List<InvoiceEntity> overdueInvoices = invoiceServiceDao.findByStatusAndDueDateBefore(StatusConstants.PENDING, overdueDate);
        for (InvoiceEntity invoice : overdueInvoices) {
            if (invoice.getPaidAmount() < invoice.getAmount()) {
                if (invoice.getPaidAmount() > 0) {
                    Double remainingAmount = invoice.getAmount() - invoice.getPaidAmount();
                    createNewInvoice(remainingAmount + invoiceDto.getLateFee(), invoiceDto);
                    invoice.setStatus(StatusConstants.PAID);
                } else {
                    createNewInvoice(invoice.getAmount() +invoiceDto.getLateFee(), invoiceDto);
                    invoice.setStatus(StatusConstants.VOID);
                }
                invoiceServiceDao.save(invoice);
            }
        }
    }

    private void createNewInvoice(double amount, InvoiceDto invoiceDto) {
        InvoiceEntity newInvoice = new InvoiceEntity();
        newInvoice.setAmount(amount);
        newInvoice.setPaidAmount(0.0);
        newInvoice.setDueDate(LocalDate.now().plusDays(invoiceDto.getOverDueDays()));
        newInvoice.setStatus(StatusConstants.PENDING);
        newInvoice.setLateFee(invoiceDto.getLateFee());
        newInvoice.setOverDueDays(invoiceDto.getOverDueDays());
        invoiceServiceDao.save(newInvoice);
    }
}
