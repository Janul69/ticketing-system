package com.example.backend.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.example.backend.dto.CustomerRequest;
import com.example.backend.dto.VendorRequest;
import com.example.backend.model.Configuration;
import com.example.backend.model.Customer;
import com.example.backend.model.TicketPool;
import com.example.backend.model.Vendor;

@Component
public class Simulation {
    private final ConfigurationService configurationService;
    private final TicketPool ticketPool;
    private static final Logger logger = LogManager.getLogger(Simulation.class);

    private final List<Vendor> vendors = Collections.synchronizedList(new ArrayList<>());
    private final List<Customer> customers = Collections.synchronizedList(new ArrayList<>());

	private boolean vendorsCreated = false;
	private boolean customersCreated = false;
	private boolean simulationStarted = false;

    public Simulation(ConfigurationService configurationService, TicketPool ticketPool) {
        this.configurationService = configurationService;
        this.ticketPool = ticketPool;
    }

    public void createVendors(List<VendorRequest> vendorRequests) {

		if (vendorsCreated) {
            logger.warn("Vendors have already been created. No new vendors will be added.");
            return; 
        }

        vendors.clear();  // Clear existing vendors for a fresh start
        Configuration config = configurationService.getConfiguration();

        for (VendorRequest vendorRequest : vendorRequests) {
            Vendor vendor = new Vendor(
                vendorRequest.getVendorId(),
                vendorRequest.getEventName(),
                vendorRequest.getTicketsToRelease(),
                config.getTicketReleaseRate(),
                vendorRequest.getTicketPrice()
            );
            vendor.setTicketPool(ticketPool);
            vendors.add(vendor);
        }

		vendorsCreated = true;
        logger.info("Vendors created: " + vendors.size());
    }

    public void createCustomers(List<CustomerRequest> customerRequests) {

		if (customersCreated) {
            logger.warn("Customers have already been created. No new customers will be added.");
            return;  
        }

        customers.clear();  // Clear existing customers to prevent duplicates
        Configuration config = configurationService.getConfiguration();

        for (CustomerRequest request : customerRequests) {
            Customer customer = new Customer(
                request.getCustomerId(),
                request.getCustomerName(),
                config.getCustomerRetrievalRate(),
                request.getVendorId(),
                request.getTicketsToBuy()
            );
            customer.setTicketPool(ticketPool);
            customers.add(customer);
        }
		customersCreated = true;
        logger.info("Customers created: " + customers.size());
    }

    public void startSimulation() throws InterruptedException {
        if (!configurationService.isConfigurationSet()) {
            throw new IllegalStateException("Configuration is not set! Please set the configuration first.");
        }

		if (simulationStarted) {
            logger.warn("Simulation has already started. If you want to create a new simulation Please stop and restart the simulation ");
            return;  
        }

        Configuration config = configurationService.getConfiguration();
        ticketPool.setMaxTicketCapacity(config.getMaxTicketCapacity());

        List<Thread> threads = new ArrayList<>();

        // Create vendor threads only if vendors are created (and not repeated)
        for (Vendor vendor : vendors) {
            Thread vendorThread = new Thread(vendor);
            threads.add(vendorThread);
            vendorThread.start();
        }
        

        // Create customer threads regardless of vendor presence
        for (Customer customer : customers) {
            Thread customerThread = new Thread(customer);
            threads.add(customerThread);
            customerThread.start();
        }

        // Wait for all threads to finish
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                logger.error("Thread interrupted: " + thread.getName(), e);
                Thread.currentThread().interrupt();
            }
        }
		simulationStarted = true;

        logger.info("Simulation finished. Remaining tickets: " + ticketPool.getSize());
    }

	public void stopSimulation() {
		vendors.clear();
		customers.clear();
		vendorsCreated = false;
		customersCreated = false;	
		simulationStarted = false;
		logger.info("Simulation stopped.");
	}
}
