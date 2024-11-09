package com.example.backend.model;

public class Vendor implements Runnable{

    private int vendorId;
    private String vendorName;
    private int ticketsPerRelease;
    private int releaseInterval;
    private int vendorCount;

    @Override
    public void run() {
        // TODO Auto-generated method stub
        
    }

    public int getVendorId() {
        return this.vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return this.vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public int getTicketsPerRelease() {
        return this.ticketsPerRelease;
    }

    public void setTicketsPerRelease(int ticketsPerRelease) {
        this.ticketsPerRelease = ticketsPerRelease;
    }

    public int getReleaseInterval() {
        return this.releaseInterval;
    }

    public void setReleaseInterval(int releaseInterval) {
        this.releaseInterval = releaseInterval;
    }

    public int getVendorCount() {
        return this.vendorCount;
    }

    public void setVendorCount(int vendorCount) {
        this.vendorCount = vendorCount;
    }

   


}


