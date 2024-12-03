package com.example.backend.service;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.example.backend.dto.VendorRequest;
import com.example.backend.model.Configuration;
import com.example.backend.model.Ticket;
import com.example.backend.model.TicketPool;
import com.example.backend.model.Vendor;

@Service
public class VendorService {

    private final ConfigurationService configurationService;
    private final TicketPool ticketPool;
    private static final Logger logger = LogManager.getLogger(VendorService.class);

    public VendorService(ConfigurationService configurationService, TicketPool ticketPool) {
        this.configurationService = configurationService;
        this.ticketPool = ticketPool;
    }

    public void addTickets(VendorRequest vendorRequest) {
        if (!configurationService.isConfigurationSet()) {
            throw new IllegalStateException("Configuration is not set! Please set the configuration first.");
        }

        Configuration config = configurationService.getConfiguration();

        Vendor vendor = new Vendor(
            vendorRequest.getVendorId(), 
            vendorRequest.getEventName(), 
            vendorRequest.getTicketsToRelease(),
            config.getTicketReleaseRate(),
            vendorRequest.getTicketPrice());
            
            
        //System.out.println(vendor); 
        ticketPool.setMaxTicketCapacity(config.getMaxTicketCapacity());
        vendor.setTicketPool(ticketPool); 
        Thread vendoThread = new Thread(vendor);
        
        try {
            vendoThread.start();   
            vendoThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("vendor finished adding tickets. ticket pool size: {}", ticketPool.getSize());
        ticketPool.getSize();


        

           

    }


}
