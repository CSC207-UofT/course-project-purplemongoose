package database;

public abstract class MainFrameGateway {
    // should be changed to an SQLite Connection object
    private final String mfLocation;

    public MainFrameGateway(String mf) {
        this.mfLocation = mf;
    }
}
