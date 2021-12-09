import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Request {
    private String current_username;
    private final String url = "cloud.arthurgao.ca:9082";
    public Request(String username){
        this.current_username = username;
    }

    public void setCurrentUsername(String s){
        this.current_username = s;
    }

    /**
     * Method to determine if HTTP request was received and returned correctly by kard-server
     *
     * @param request   HTTP requests to send
     * @param client    an HTTP client
     * @return          Success or error code, 404 if page not found
     */
    private String getRequestError(HttpRequest request, HttpClient client) {
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject res = new JSONObject(response.body());
            return res.getString("errorCode");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "404";
    }

    /**
     * Method to build the JSON containing relevant information for an HTTP request to kard-server defined at endpoint
     * requiring all the contact information of the user.
     *
     * @param first     The first name of the user
     * @param last      The last name of the user
     * @param title     The title the user prefers
     * @param pronoun   The pronoun(s) the user prefers
     * @param phone     A phone number to reach the user at
     * @param email     An email to reach the user at
     * @param endpoint  URL to send the request to
     * @return          A JSON for the HTTP request to kard-server
     */
    private String buildRequestJSON(String first, String last, String title, String pronoun, String phone,
                                    String email, String endpoint) {
        String inputJson = String.format("{\"accountUsername\":\"%s\"," + "\"firstName\":\"%s\","
                        + "\"lastName\":\"%s\","+ "\"title\":\"%s\","+ "\"pronoun\":\"%s\","
                        + "\"phone\":\"%s\"," + "\"email\":\"%s\"}",
                this.current_username, first, last, pronoun, title, phone, email);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        return getRequestError(request, client);
    }

    /**
     * Method to build the JSON contain relevant information where only username is required for the HTTP POST
     *
     * @param input     The username of the user
     * @param endpoint  URL to send the request to
     * @return          The error code from the request if any. 0 if request successful
     */
    private String buildUsernameRequestJSON(String input, String endpoint) {
        String inputJson = String.format("{\"accountUsername\":\"%s\"," +
                        "\"contactUsername\":\"%s\"}",
                this.current_username, input);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        return getRequestError(request, client);
    }

    /**
     * Method used to compile the inputted information into a HTTP request sent to the database,
     * its information is then verified for log in and returns the result.
     *
     * @param username the username of the account
     * @param password the password of the account
     * @return code of response that indicates if the login was successful, returns the status code of the response
     */
    public String submitLogin(String username, String password){
        String endpoint = "http://"+ this.url +"/kard/login/";
        String inputJson = String.format("{\"accountUsername\":\"%s\"," +
                        "\"accountPassword\":\"%s\"}",
                username, password);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                .build();
        HttpClient client = HttpClient.newHttpClient();

        return getRequestError(request, client);
    }

    /**
     * Method used to compile the inputted information into a HTTP request sent to the database,
     * its information is then verified for sign up and returns the result.
     *
     * @param username the username of the account
     * @param password the password of the account
     * @return code of response that indicates if the sign up was successful, returns the status code of the response
     */
    public String submitSignUp(String username, String password){
        String endpoint = "http://"+ this.url +"/account/create/";

        String inputJson = String.format("{\"accountUsername\":\"%s\"," +
                        "\"accountPassword\":\"%s\"}",
                username, password);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject res = new JSONObject(response.body());
            return res.getString("errorCode");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "404";
    }

    /**
     * Method used to compile profile parameters into a POST request to create a new profile
     *
     * @param first the new first name for the profile
     * @param last the new last name for the profile
     * @param pronoun the new pronoun for the profile
     * @param title the new title for the profile
     * @param phone the new phone for the profile
     * @param email the new email for the profile
     * @return an error code
     */
    public String submitProfileCreate(String first, String last, String title, String pronoun, String phone, String email) {
        String endpoint = "http://"+ this.url +"/profile/create/";
        return buildRequestJSON(first, last, title, pronoun, phone, email, endpoint);
    }

    /**
     * Method used to compile profile parameters into a POST request to update the existing profile
     *
     * @param first the new first name for the profile
     * @param last the new last name for the profile
     * @param pronoun the new pronoun for the profile
     * @param title the new title for the profile
     * @param phone the new phone for the profile
     * @param email the new email for the profile
     * @return an error code
     */
    public String submitProfileUpdate(String first, String last, String pronoun, String title, String phone, String email) {
        String endpoint = "http://"+ this.url +"/profile/edit/";
        return buildRequestJSON(first, last, pronoun, title, phone, email, endpoint);
    }

    /**
     * Method used to send a POST request to restore the current profile to a past version as indicated by the index
     *
     * @param index index of past profile
     * @return an error code
     */
    public String submitProfileRestore(String index) {
        String endpoint = "http://"+ this.url +"/profile/restore/";
        String inputJson = String.format("{\"accountUsername\":\"%s\"," + "\"index\":\"%s\"}",
                this.current_username, index);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(inputJson))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        return getRequestError(request, client);
    }

    /**
     * Method used to compile the profile memento of the username into a HTTP GET request to retrieve a formatted
     * list of past profiles which are stored in the server.
     *
     * @return a string of the current users past profiles, compiled already and ready for display
     */
    public String submitProfileMementoDisplay() {
        String endpoint = String.format("http://"+ this.url +"/profile/display/memento?username=%s",
                this.current_username);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject res = new JSONObject(response.body());
            JSONArray arr = res.getJSONArray("response");
            if (arr.length() == 0) {
                return "Your haven't made any changes to your profile yet!";
            } else {
                StringBuilder history = new StringBuilder();
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject profile = arr.getJSONObject(i);
                    history.append(String.format("index: %s # %s | %s | %s | %s | username: %s\n", i,
                            profile.get("name"), profile.get("pronouns"), profile.get("phone"),
                            profile.get("email"), profile.get("username")));
                }
                return history.toString();
//                return response.body();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "Unable to retrieve memento list, please try again!";
    }

    /**
     * Method used to compile the current username into a HTTP GET request to retrieve the current user's profile
     *
     * @return a string of the current users profile, compiled already and ready for display
     */
    public String submitProfileDisplay() {
        String endpoint = String.format("http://"+ this.url +"/profile/display?username=%s",
                this.current_username);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject res = new JSONObject(response.body());
            if (response.body().contains("\"response\":null")) {
                return "Your profile is empty! Go create one now!";
            } else {
                JSONObject profile = res.getJSONObject("response");
                return String.format(" %s | %s | %s | %s | username: %s\n",
                        profile.get("name"), profile.get("pronouns"), profile.get("phone"),
                        profile.get("email"), profile.get("username"));
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "Unable to retrieve your profile, please try again!";
    }

    // ################ Contacts

    /**
     * Method used to compile the inputted information into a HTTP request along with the username of
     * the current user to add the inputted username into the current user's contact.
     *
     * @param input the username of the contact which the user wants to add
     * @return status code of response that indicates if the contact addition was successful
     */
    public String submitContactAddition(String input){
        String endpoint = "http://"+ this.url +"/contact/add/";
        return buildUsernameRequestJSON(input, endpoint);
    }

    /**
     * Method used to compile the inputted information into a HTTP request along with the username of
     * the current user to remove the inputted username from the current user's contact.
     *
     * @param input the username of the contact which the user wants to remove
     * @return status code of response that indicates if the contact removal was successful
     */
    public String submitContactRemoval(String input) {
        String endpoint = "http://"+ this.url +"/contact/remove/";
        return buildUsernameRequestJSON(input, endpoint);
    }

    /**
     * Method used to compile the current username into a HTTP GET request to retrieve a formatted
     * list of contacts which are stored in the server.
     *
     * @return a string of the current users contacts, compiled already and ready for display
     */
    public String submitContactDisplay(String type, String order) {
        String endpoint = String.format("http://"+ this.url +"/contact/display?username=%s&param=%s&order=%s",
                this.current_username, type, order);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpoint))
                .GET()
                .build();
        HttpClient client = HttpClient.newHttpClient();
        try {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject res = new JSONObject(response.body());
            JSONArray arr = res.getJSONArray("response");
            if (arr.length() == 0) {
                return "Your contact list is empty!";
            } else {
                StringBuilder contacts = new StringBuilder();
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject profile = arr.getJSONObject(i);
                    contacts.append(String.format(" %s | %s | %s | %s | username: %s\n",
                            profile.get("name"), profile.get("pronouns"), profile.get("phone"),
                            profile.get("email"), profile.get("username")));
                }
                return contacts.toString();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "Unable to retrieve contacts list, please try again!";
    }
}
