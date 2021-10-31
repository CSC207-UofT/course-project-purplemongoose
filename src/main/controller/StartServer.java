package controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartServer {

    public static void main(String[] args) {
        // Starts up the backend HTTP server
        SpringApplication.run(StartServer.class, args);
    }
    
}



