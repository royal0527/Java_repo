package com.example.assessment.service;

import com.example.assessment.dto.InvoiceDto;

public interface InvoiceService {

    Object createInvoice(InvoiceDto invoiceDto);

    Object getAllInvoices();

    Object makePayment(Long id, InvoiceDto invoiceDto);

    void processDues(InvoiceDto invoiceDto);
}
