import controller.SpringServerInit;

public class Main {

    // Starts a new server instance on this machine
    public static void main(String[] args) {
        SpringServerInit server = new SpringServerInit();
        server.start(args);
    }

}
