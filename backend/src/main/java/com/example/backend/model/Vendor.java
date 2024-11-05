package com.example.backend.model;

public class Vendor implements Runnable{

    private int vendorId;
    private String vendorName;
    private int ticketsPerRelease;
    private int releaseInterval;
    private int vendorCount;

    public Vendor(int vendorCount) {
        this.vendorCount = vendorCount;
    }


    @Override
    public void run() {
        for (int i = 0; i <= 5; i++) {
            System.out.println(i + " for vendor: " + vendorCount);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
