package com.example.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.CustomerRequest;
import com.example.backend.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/buy")
    public ResponseEntity<String> buyTickets(@RequestBody CustomerRequest customerRequest) {
        customerService.buyTickets(customerRequest);
        return ResponseEntity.ok("tickets retireved successfully");

    }
   
}
