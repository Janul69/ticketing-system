package com.example.backend.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class TicketPool {
      
    private int maxTicketCapacity;

    private final List<Ticket> tickets = Collections.synchronizedList(new ArrayList<>());

    public synchronized boolean addTickets(Ticket ticket) {
        if (tickets.size() < maxTicketCapacity) {
            tickets.add(ticket);
            return true;
            //System.out.println("Ticket added " + ticket.getTicketId());
        } else {
            return false;
              
        }
    }

    public synchronized List<Ticket> removeTicket(String vendorId, int quantity) {
        List<Ticket> ticketsToRetrieve = new ArrayList<>();
        synchronized (tickets) {
            tickets.removeIf(ticket -> {
                if (!ticket.IsSold() && ticket.getVendorId().equals(vendorId) && ticketsToRetrieve.size() < quantity) {
                    ticketsToRetrieve.add(ticket);
                    return true;
                }
                return false;
            });
        }
        return ticketsToRetrieve;
    }

    public int getMaxTicketCapacity() {
        return this.maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }
    


    public List<Ticket> getTickets() {
        return this.tickets;
    }


    public int getSize() {
        return tickets.size();
    }
   
 
}
