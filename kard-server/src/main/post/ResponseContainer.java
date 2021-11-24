package post;

/**
 * Class that is serialized into a JSON response for the endpoint - only contains one response
 */
public class ResponseContainer {
    public Object response;
    public String errorCode;

    public ResponseContainer() {
        this.response = null;
        this.errorCode = "0";
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
     * @param error integer error code associated with a specific error
     */
    public void setError(String error) {
        this.errorCode = error;
    }
}
