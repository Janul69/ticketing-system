package com.example.backend.model;

public class Ticket {
    private final String ticketId;
    private final double ticketPrice;
    private final String vendorId;
    private boolean isSold;

    public Ticket(String ticketId, double ticketPrice, String vendorId) {
        this.ticketId = ticketId;
        this.ticketPrice = ticketPrice;
        this.vendorId = vendorId;
        this.isSold = false;
    }

    public String getVendorId() {
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

