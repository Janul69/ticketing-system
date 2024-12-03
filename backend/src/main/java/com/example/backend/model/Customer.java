package com.example.backend.model;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class Customer implements Runnable{
    private final String customerId;
    private final String customerName;
    private final String vendorId;
    private final int ticketsToBuy;
    private final int customerRetrievalRate;

    private Configuration config;
    private TicketPool ticketPool;

    private static final Logger logger = LogManager.getLogger(Customer.class);


    @Autowired
    public void setConfig(Configuration config) {
        this.config = config;
    }

    @Autowired
    public void setTicketPool(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    public Customer(String customerId, String customerName, int customerRetrievalRate, String vendorId, int ticketsToBuy) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerRetrievalRate = customerRetrievalRate;
        this.vendorId = vendorId;
        this.ticketsToBuy = ticketsToBuy;
    }

    @Override
    public void run() {
        List<Ticket> purchasedTickets = ticketPool.removeTicket(vendorId, ticketsToBuy);

        if (purchasedTickets.isEmpty()) {
            logger.info("{} could not buy tickets from vendor {}", customerName, vendorId);
        } else {
            for (Ticket ticket : purchasedTickets) {
                ticket.setIsSold(true);
                logger.info("{} purchased ticket {} from vendor {}", customerName, ticket.getTicketId(), vendorId);
                try {
                    Thread.sleep(customerRetrievalRate);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        
    }

    public String getCustomerId() {
        return this.customerId;
    }


    public String getCustomerName() {
        return this.customerName;
    }


    public int getCustomerRetrievalRate() {
        return this.customerRetrievalRate;
    }


    public Configuration getConfig() {
        return this.config;
    }


    public TicketPool getTicketPool() {
        return this.ticketPool;
    }


    public String getVendorId() {
        return this.vendorId;
    }


    public int getTicketsToBuy() {
        return this.ticketsToBuy;
    }



}
