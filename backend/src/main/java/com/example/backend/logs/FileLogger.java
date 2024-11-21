package com.example.backend.logs;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger {

    private static final String LOG_FILE = "filelogger.log";

    public synchronized static void log(String message) {

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true));
            writer.write(message);
            writer.newLine();
            
        } catch (IOException e) {
            System.out.println("Failed to write logs in the file " + e.getMessage());
        }
    }

}
