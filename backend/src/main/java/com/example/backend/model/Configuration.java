package com.example.backend.model;

import org.springframework.stereotype.Component;

@Component
public class Configuration {

    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;


    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }
        

    @Override
    public String toString() {
        return "{" +
            " totalTickets='" + getTotalTickets() + "'" +
            ", ticketReleaseRate='" + getTicketReleaseRate() + "'" +
            ", customerRetrievalRate='" + getCustomerRetrievalRate() + "'" +
            ", maxTicketCapacity='" + getMaxTicketCapacity() + "'" +
            "}";
    }


}

