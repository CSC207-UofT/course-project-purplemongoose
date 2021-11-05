// TODO for some reason spring server controller won't import nicely
// import controller.SpringServerController;
import controller.SpringServerController;

public class Main {

    // Starts a new server instance on this machine
    public static void main(String[] args) {
        SpringServerController server = new SpringServerController();
        server.start(args);
    }

}
