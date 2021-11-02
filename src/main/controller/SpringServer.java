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

// To test out the controllers for this server:
// http://[IPv4 of hosting machine]:[port]/[controller mapping]/[method mapping]?[arg1=...]&[arg2=...]...

// The port can be configured in resources/application.properties
// Ensure the port is opened up in the firewall to allow external connections

// Example:
// http://123.4.5.678:8082/start/login?username=bob&password=123

