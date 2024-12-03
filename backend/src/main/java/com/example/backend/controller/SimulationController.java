package com.example.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.CustomerRequest;
import com.example.backend.dto.VendorRequest;
import com.example.backend.service.Simulation;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class SimulationController {

    private final Simulation simulation;

    public SimulationController(Simulation simulation) {
        this.simulation = simulation;
    }

    @PostMapping("/simulation/vendors")
    public ResponseEntity<String> createVendors(@RequestBody List<VendorRequest> vendorRequests) {
        if (vendorRequests == null || vendorRequests.isEmpty()) {
            return ResponseEntity.badRequest().body("Vendor requests cannot be null or empty");
        }

        try {
            simulation.createVendors(vendorRequests);
            return ResponseEntity.ok("Vendors created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error while creating vendors: " + e.getMessage());
        }
    }

    @PostMapping("/simulation/customers")
    public ResponseEntity<String> createCustomers(@RequestBody List<CustomerRequest> customerRequests) {
        if (customerRequests == null || customerRequests.isEmpty()) {
            return ResponseEntity.badRequest().body("Customer requests cannot be null or empty");
        }

        try {
            simulation.createCustomers(customerRequests);
            return ResponseEntity.ok("Customers created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error while creating customers: " + e.getMessage());
        }
    }

    @PostMapping("/simulation/start")
    public ResponseEntity<String> runSimulation() {
        try {
            simulation.startSimulation();
            return ResponseEntity.ok("Simulation started successfully");
        } catch (InterruptedException e) {
            return ResponseEntity.badRequest().body("Error while starting simulation: " + e.getMessage());
        }
    }

    @PostMapping("/simulation/stop")
    public ResponseEntity<String> stopSimualtion() {
        simulation.stopSimulation();
        return ResponseEntity.ok("Simulation stopped succcessfully");
    }
}
