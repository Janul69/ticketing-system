package com.example.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private final String ticketId;
    private final double ticketPrice;
    private final Long vendorId;
    private boolean isSold;

    public Ticket(String ticketId, double ticketPrice, Long vendorId) {
        this.ticketId = ticketId;
        this.ticketPrice = ticketPrice;
        this.vendorId = vendorId;
        this.isSold = false;
    }

    public Long getVendorId() {
        return this.vendorId;
    }


    public String getTicketId() {
        return this.ticketId;
    }

    public double getTicketPrice() {
        return this.ticketPrice;
    }


    public boolean IsSold() {
        return this.isSold;
    }

    public boolean getIsSold() {
        return this.isSold;
    }

    public void setIsSold(boolean isSold) {
        this.isSold = isSold;
    }
}

