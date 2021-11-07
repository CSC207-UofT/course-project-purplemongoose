package controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 To connect to the server:
 http://[host IPv4]:[port]/[controller mapping]/.../[method mapping]

 The port can be configured in resources/application.properties
 Note: The default port is port 8082

 Example:
 http://localhost:8082/start/login
 */

@SpringBootApplication
public class SpringServerController {

    /**
     * Start the HTTP server for handling communication between the back end of the
     * application and the user interfaces
     *
     * @param args array of string arguments
     */
    public void start(String[] args) {
        SpringApplication.run(SpringServerController.class, args);
    }

}

