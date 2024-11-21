package com.example.backend.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class Vendor implements Runnable{

    private static final Logger logger = LogManager.getLogger(Vendor.class);

    private final String vendorId;
    private final String eventName;
    private final int ticketsToRelease;
    private final int ticketReleaseRate;
    private final double ticketPrice;
    
    private Configuration config;

    private TicketPool ticketPool;

    @Autowired
    public void setTicketPool(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    @Autowired
    public void setConfig(Configuration config) {
        this.config = config;
    }

    public Vendor(String vendorId, String eventName, int ticketsToRelease, int ticketReleaseRate, double ticketPrice) {
        this.vendorId = vendorId;
        this.eventName = eventName;
        this.ticketsToRelease = ticketsToRelease;
        this.ticketReleaseRate = ticketReleaseRate; 
        this.ticketPrice = ticketPrice;
    }

    @Override
    public void run() {
        for (int i=1; i <= ticketsToRelease; i++) {
            String ticketId = "V" + vendorId + "-T" + i;
            Ticket ticket = new Ticket(ticketId, ticketPrice, vendorId);
            boolean isAdded = ticketPool.addTickets(ticket);
            
            
            if (isAdded) {
                logger.info("Vendor: {} added ticket: {} with price: {}", vendorId, ticketId, ticketPrice);
            } else {
                logger.warn("Vendor: {} tried to add ticket: {} but the capacity is full.", vendorId, ticketId);
            }
            //String logMessage = "Vendor: " + vendorId + " added ticket: " + ticketId;
            //System.out.println(logMessage);
            
            //FileLogger.log(logMessage);

            try {
                Thread.sleep(ticketReleaseRate);
            } catch (InterruptedException e) {
                logger.error("unexpected error in vendor thread {}: {}", vendorId, e.getMessage(), e);
                //FileLogger.log("unexpected error in vendor thread: " + vendorId + e.getMessage());
            }
        }

        
        
    }


    public String getVendorId() {
        return this.vendorId;
    }


    public String getEventName() {
        return this.eventName;
    }


    public int getTicketsToRelease() {
        return this.ticketsToRelease;
    }


    public Configuration getConfig() {
        return this.config;
    }

    public int getTicketReleaseRate() {
        return this.ticketReleaseRate;
    }


    public TicketPool getTicketPool() {
        return this.ticketPool;
    }



}


