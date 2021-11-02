package viewmodel;

public class SimpleResponse implements Container {
    public Object response;
    public int errorCode;

    public SimpleResponse() {
        this.response = null;
        this.errorCode = 0;
    }

    @Override
    public void add(Object response) {
        this.response = response;
    }

    public void setError(int error) {
        this.errorCode = error;
    }
}
