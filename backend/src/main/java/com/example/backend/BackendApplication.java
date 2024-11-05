package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.backend.model.Vendor;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);

		for (int i=0; i < 5; i++) {
			Vendor vendor = new Vendor(i);
			Thread vendorThread = new Thread(vendor);
			vendorThread.start();
		}
		
		
	}

}
