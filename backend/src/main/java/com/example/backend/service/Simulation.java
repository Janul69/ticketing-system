package com.example.backend.service;

import java.util.ArrayList;
import java.util.List;

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

	private List<Vendor> vendors = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    public Simulation(ConfigurationService configurationService, TicketPool ticketPool) {
        this.configurationService = configurationService;
        this.ticketPool = ticketPool;
    }

	public void createVendors(List<VendorRequest> vendorRequests) {
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
		};
	}

	public void createCustomers(List<CustomerRequest> customerRequests) {
		Configuration config = configurationService.getConfiguration();

		for (CustomerRequest request : customerRequests) {
			Customer customer = new Customer(request.getCustomerId(), request.getCustomerName(), config.getCustomerRetrievalRate(), request.getVendorId(), request.getTicketsToBuy());
			customer.setTicketPool(ticketPool);
			customers.add(customer);
		}

		// for (CustomerRequest customerRequest : customerRequests) {
		// 	Customer customer = new Customer(customerRequest.getCustomerId(), customerRequest.getCustomerName(), config.getCustomerRetrievalRate());
		// 	customer.setTicketPool(ticketPool);
		// 	customers.add(customer);
		// }
	}

	public void startSimulation() throws InterruptedException {
		if (!configurationService.isConfigurationSet()) {
			throw new IllegalStateException("Configuration is not set! Please set the configuration first");
		}

		Configuration config = configurationService.getConfiguration();
		ticketPool.setMaxTicketCapacity(config.getMaxTicketCapacity());

		List<Thread> vendorThreads = new ArrayList<>();
		for (Vendor vendor : vendors) {
			Thread vendorThread = new Thread(vendor);
			vendorThreads.add(vendorThread);
			vendorThread.start();
		}

		for (Thread thread : vendorThreads) {
			thread.join();
		}

		System.out.println("Tickets adding finished");
        System.out.println(ticketPool.getSize());

		List<Thread> customerThreads = new ArrayList<>();
		for (Customer customer : customers) {
			Thread customerThread = new Thread(customer);
			customerThreads.add(customerThread);
			customerThread.start();
	    }

		for (Thread thread : customerThreads) {
			thread.join();
		}

		System.out.println("Tickets purchasing finished");
        System.out.println(ticketPool.getSize());
	}

	

    // public void startSimulation() throws InterruptedException {
    //     if (!configurationService.isConfigurationSet()) {
    //         System.out.println("Configuration is not set! Please set the configuration first");
    //     }

    //     Configuration config = configurationService.getConfiguration();
    //     ticketPool.setMaxTicketCapacity(config.getMaxTicketCapacity());

    //      // Create Vendors and inject dependencies
    //     Vendor vendor1 = new Vendor("1", "movie", 20, config.getTicketReleaseRate());
    //     Vendor vendor2 = new Vendor("2", "movie", 5, config.getTicketReleaseRate());
	// 	Vendor vendor3 = new Vendor("3", "movie", 30, config.getTicketReleaseRate());

    //     vendor1.setTicketPool(ticketPool);
    //     vendor2.setTicketPool(ticketPool);
	// 	vendor3.setTicketPool(ticketPool);

    //     // Start Threads
    //     Thread thread1 = new Thread(vendor1);
    //     Thread thread2 = new Thread(vendor2);
	// 	Thread thread3 = new Thread(vendor3);

    //     thread1.start();
    //     thread2.start();
	// 	thread3.start();

	// 	thread1.join();
	// 	thread2.join();
	// 	thread3.join();

	// 	System.out.println("Tickets adding finished");
	// 	System.out.println(ticketPool.getSize());

	// 	Customer customer1 = new Customer("1", "customer 1", 1000);
	// 	Customer customer2 = new Customer("2", "customer 2", 1000);
	// 	Customer customer3 = new Customer("3", "customer 3", 1000);

	// 	customer1.setTicketPool(ticketPool);
	// 	customer2.setTicketPool(ticketPool);
	// 	customer3.setTicketPool(ticketPool);

	// 	Thread cThread1 = new Thread(customer1);
	// 	Thread cThread2 = new Thread(customer2);
	// 	Thread cThread3 = new Thread(customer3);

	// 	cThread1.start();
	// 	cThread2.start();
	// 	cThread3.start();

	// 	cThread1.join();
	// 	cThread2.join();
	// 	cThread3.join();

	// 	System.out.println(ticketPool.getSize());

		
	// }

}
