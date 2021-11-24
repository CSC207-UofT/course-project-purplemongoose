package controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringServerInit {

    /**
     * Start the HTTP server for handling communication between the back end of the
     * application and the user interfaces
     *
     * @param args array of string arguments
     */
    public void start(String[] args) {
        SpringApplication.run(SpringServerInit.class, args);
    }

}

