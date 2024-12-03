package com.example.backend.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class TicketPool {

    private static final Logger logger = LogManager.getLogger(TicketPool.class);
    
    private int maxTicketCapacity;
    private final List<Ticket> tickets = Collections.synchronizedList(new ArrayList<>());
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition ticketsAvailable = lock.newCondition();


    public boolean addTicket(Ticket ticket) {
        lock.lock();
        try {
            if (tickets.size() < maxTicketCapacity) {
                tickets.add(ticket);
                ticketsAvailable.signalAll();
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }
    
    public List<Ticket> removeTicket(String vendorId, int quantity) {
        List<Ticket> retrievedTickets = new ArrayList<>();
        lock.lock();
        try {
            while (retrievedTickets.size() < quantity) {
                boolean ticketFound = false;
                for (Ticket ticket : tickets) {
                    if (!ticket.IsSold() && ticket.getVendorId().equals(vendorId)) {
                        retrievedTickets.add(ticket);
                        ticketFound = true;
                        tickets.remove(ticket);
                        break;
                    }
                }
                if (!ticketFound) {
                    ticketsAvailable.await();
                }
            }
            return retrievedTickets;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.error("Thread interrupted while waiting for tickets", e);
            return retrievedTickets;
        } finally {
            lock.unlock();
        }
    }

    // Getters and Setters for max ticket capacity
    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    // Getter for the current list of tickets
    public List<Ticket> getTickets() {
        return tickets;
    }

    // Get the size of the ticket pool
    public int getSize() {
        return tickets.size();
    }
}
