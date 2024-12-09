package com.example.backend.controller;

import java.lang.module.Configuration;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.VendorRequest;
import com.example.backend.service.ConfigurationService;
import com.example.backend.service.VendorService;


@RestController
@RequestMapping("/api/vendor")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }


    @PostMapping("/add")
    public ResponseEntity<String> addTickets(@RequestBody VendorRequest vendorRequest) {
        try {
            vendorService.addTickets(vendorRequest);
            return ResponseEntity.ok("Tickets added successfully!");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        
        
    }

}
