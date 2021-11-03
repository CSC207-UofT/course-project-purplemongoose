package viewmodel;

/**
 * Class that is serialized into a JSON response for the endpoint - only contains one response
 */
public class ShortResponse implements ResponseContainer {
    public Object response;
    public int errorCode;

    public ShortResponse() {
        this.response = null;
        this.errorCode = 0;
    }

    /**
     * Setter for the response
     * @param response the response object to be added
     */
    public void add(Object response) {
        this.response = response;
    }

    /**
     * Setter for the error code
     * @param error the error code associated with a specific error
     */
    public void setError(int error) {
        this.errorCode = error;
    }
}
