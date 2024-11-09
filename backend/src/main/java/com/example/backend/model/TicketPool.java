package com.example.backend.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketPool {
    private final List<Ticket> tickets = Collections.synchronizedList(new ArrayList<Ticket>());

    public synchronized void addTickets(List<Ticket> newTickets) {
        tickets.addAll(newTickets);
        System.out.println(newTickets.size() + " tickets added to the pool");
    }

    // public synchronized Ticket purchaseTickets() {

    // }
}
