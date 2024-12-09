package com.example.backend.dto;

public class CustomerRequest {
    private String customerId;
    private String customerName;
    private Long vendorId;
    private int ticketsToBuy;
    private int customerRetrievalRate;


    public Long getVendorId() {
        return this.vendorId;
    }


    public int getTicketsToBuy() {
        return this.ticketsToBuy;
    }



    public String getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerRetrievalRate() {
        return this.customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }



    @Override
    public String toString() {
        return "{" +
            " customerId='" + getCustomerId() + "'" +
            ", customerName='" + getCustomerName() + "'" +
            ", vendorId='" + getVendorId() + "'" +
            ", ticketsToBuy='" + getTicketsToBuy() + "'" +
            ", customerRetrievalRate='" + getCustomerRetrievalRate() + "'" +
            "}";
    }

}