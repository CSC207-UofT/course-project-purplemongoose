package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Run {

    /**
     * Start the HTTP server for handling communication between the back end of the
     * application and the user interfaces
     *
     * @param args array of string arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Run.class, args);
    }
}

