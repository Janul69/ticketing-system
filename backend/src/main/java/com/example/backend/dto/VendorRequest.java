package com.example.backend.dto;

public class VendorRequest {
    private Long vendorId;
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

    public Long getVendorId() {
        return this.vendorId;
    }

    public double getTicketPrice() {
        return this.ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void setVendorId(Long vendorId) {
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


    @Override
    public String toString() {
        return "{" +
            " vendorId='" + getVendorId() + "'" +
            ", eventName='" + getEventName() + "'" +
            ", ticketsToRelease='" + getTicketsToRelease() + "'" +
            ", ticketReleaseRate='" + getTicketReleaseRate() + "'" +
            ", ticketPrice='" + getTicketPrice() + "'" +
            "}";
    }

}
