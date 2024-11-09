package com.example.backend.model;

public class Customer implements Runnable{

    private int customerId;
    private String customerName;
    private int retrievalIntervel;

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }


    public int getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getRetrievalIntervel() {
        return this.retrievalIntervel;
    }

    public void setRetrievalIntervel(int retrievalIntervel) {
        this.retrievalIntervel = retrievalIntervel;
    }



}
