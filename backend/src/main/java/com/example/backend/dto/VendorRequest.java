package com.example.backend.dto;

public class VendorRequest {
    private String vendorId;
    private String eventName;
    private int ticketsToRelease;
    private int ticketReleaseRate;
    private double ticketPrice;


    public int getTicketReleaseRate() {
        return this.ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public String getVendorId() {
        return this.vendorId;
    }

    public double getTicketPrice() {
        return this.ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getEventName() {
        return this.eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getTicketsToRelease() {
        return this.ticketsToRelease;
    }

    public void setTicketsToRelease(int ticketsToRelease) {
        this.ticketsToRelease = ticketsToRelease;
    }


}
