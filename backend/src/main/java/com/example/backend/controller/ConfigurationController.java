package com.example.backend.controller;

 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.model.Configuration;
import com.example.backend.model.TicketPool;
import com.example.backend.service.ConfigurationService;

@RestController
public class ConfigurationController {

    private final ConfigurationService configurationService;

    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @PostMapping("/config")
    public ResponseEntity<String> configSetup(@RequestBody Configuration config) {
        configurationService.setConfiguration(config);
        System.out.println("Configuration received: " + config);
        return ResponseEntity.ok("Configuration setup successfully");
    }


} 