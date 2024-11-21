package com.example.backend.service;

import org.springframework.stereotype.Service;

import com.example.backend.model.Configuration;

@Service
public class ConfigurationService {
    private Configuration config;

    public synchronized void setConfiguration(Configuration config) {
        this.config = config;
    }

    public synchronized Configuration getConfiguration() {
        return config;
    }

    public synchronized boolean isConfigurationSet() {
        return config != null;
    }


}
