package controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringServer {

    public void start(String[] args) {
        // Starts up the backend HTTP server
        SpringApplication.run(SpringServer.class, args);
    }
}

// To connect to the server
// http://[IPv4 of hosting machine]:[port]/[controller mapping]/.../[method mapping]

// The port can be configured in resources/application.properties
// Ensure the port is opened up in the firewall to allow external connections

// Example:
// http://localhost:8082/start/login

