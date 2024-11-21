package com.example.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.CustomerRequest;
import com.example.backend.dto.VendorRequest;
import com.example.backend.model.Customer;
import com.example.backend.model.TicketPool;
import com.example.backend.model.Vendor;
import com.example.backend.service.Simulation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class SimulationController {

    private final Simulation simulation;

    public SimulationController(Simulation simulation) {
        this.simulation = simulation;
     
    }

    @PostMapping("/simulation")
    public ResponseEntity<String> startSimulation() {
        try {
            simulation.startSimulation();
            return ResponseEntity.ok("Simulation started successfully");
        } catch (InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error while starting simulation");
        }    
    }

    @PostMapping("/simulation/vendors")
    public ResponseEntity<String> createVendors(@RequestBody List<VendorRequest> vendorRequests) {
        try {
            simulation.createVendors(vendorRequests);
            return ResponseEntity.ok("Vendors created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error while creating vendors");
        }
    }

    @PostMapping("/simulation/customers")
    public ResponseEntity<String> createCustomers(@RequestBody List<CustomerRequest> customerRequests) {
        try {
            simulation.createCustomers(customerRequests);
            return ResponseEntity.ok("Customers created successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error while creating customers");
        }
    }

    @PostMapping("/simulation/start")
    public ResponseEntity<String> runSimulation() {
        try {
            simulation.startSimulation();
            return ResponseEntity.ok("Simulation started successfully");
        } catch (InterruptedException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error while starting simulation");
        }
    }

    // @PostMapping("add-vendors")
    // public ResponseEntity<String> addVendors(@RequestBody List<VendorRequest> vendorRequests) {
    

    // }

    // @PostMapping("/simulation/add-vendor")
    // public ResponseEntity<String> addVendor(@RequestBody Vendor vendor) {
    //     vendor.setTicketPool(ticketPool);
    //     vendors.add(vendor);
    //     return ResponseEntity.ok("Vendor added successfully");
    // }

    // @PostMapping("/simulation/add-customer")
    // public ResponseEntity<String> addCustomer(@RequestBody Customer customer) {
    //     customer.setTicketPool(ticketPool);
    //     customers.add(customer);
    //     return ResponseEntity.ok("Customer added successfully");
    // }

    // @PostMapping("simulation/start")
    // public ResponseEntity<String> startSimulation() throws InterruptedException {
    //     simulation.runSimulation(vendors, customers);
    //     return ResponseEntity.ok("Simulation started successfully");
    // }
 

    // @PostMapping("/vendor-simulation")
    // public ResponseEntity<String> startVendors() {
    //     Thread vendorThread = new Thread(new Vendor(null, null, 0, 0));
    // }
    


}