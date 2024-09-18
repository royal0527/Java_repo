package com.example.assessment.controller;

import com.example.assessment.dto.InvoiceDto;
import com.example.assessment.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/invoices")
public class InvoiceController {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    private final InvoiceService invoiceService;

    @PostMapping("/createinvoices")
    public ResponseEntity<Object> createInvoice(@RequestBody InvoiceDto invoiceDto) {
        logger.info("invoice {} ",invoiceDto.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(invoiceService.createInvoice(invoiceDto));
    }

    @GetMapping("/getinvoices")
   public ResponseEntity<Object> getAllInvoices(){
        return ResponseEntity.status(HttpStatus.OK).body(invoiceService.getAllInvoices());
    }

    @PostMapping("/{id}/payments")
    public ResponseEntity<Object> payment(@PathVariable("id") Long id,
                                          @RequestBody InvoiceDto invoiceDto) {
        return ResponseEntity.status(HttpStatus.OK).body(invoiceService.makePayment(id,invoiceDto));
    }

    @PostMapping("/processoverdue")
    public ResponseEntity<Object> processDues(@RequestBody InvoiceDto invoiceDto) {
        invoiceService.processDues(invoiceDto);
        return ResponseEntity.ok("Overdue invoices processed successfully.");
    }

}
