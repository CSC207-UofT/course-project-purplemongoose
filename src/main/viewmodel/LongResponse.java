package viewmodel;

import java.util.ArrayList;

/**
 * Class that is serialized into a JSON response for the endpoint - contains more than one response for the endpoint
 */
public class LongResponse implements ResponseContainer{
    public ArrayList<Object> responses;
    public int errorCode;

    public LongResponse() {
        this.responses = new ArrayList<>();
        this.errorCode = 0;
    }

    /**
     * Adds one response to the ArrayList of responses
     * @param response the response object to be added
     */
    public void add(Object response) {
        this.responses.add(response);
    }

    /**
     * Setter fpr the error code
     * @param error the error code associated with some error
     */
    public void setError(int error) {
        this.errorCode = error;
    }
}
