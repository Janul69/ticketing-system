package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.dto.CustomerRequest;
import com.example.backend.model.Configuration;
import com.example.backend.model.Customer;
import com.example.backend.model.TicketPool;

@Service
public class CustomerService {

    private final TicketPool ticketPool;
    private final ConfigurationService configurationService;

    public CustomerService(ConfigurationService configurationService, TicketPool ticketPool) {
        this.configurationService = configurationService;
        this.ticketPool = ticketPool;
    }

    public void buyTickets(CustomerRequest customerRequest) {

        if (!configurationService.isConfigurationSet()) {
            throw new IllegalStateException("Configuration is not set! Please set the configuration first.");
        }

        Configuration config = configurationService.getConfiguration();

        Customer customer = new Customer(
            customerRequest.getCustomerId(), 
            customerRequest.getCustomerName(), 
            config.getCustomerRetrievalRate(), 
            customerRequest.getVendorId(),
            customerRequest.getTicketsToBuy());
            
        customer.setTicketPool(ticketPool);
        
        Thread customerThread = new Thread(customer);

        try {
            customerThread.join();
            customerThread.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        
    }

}
