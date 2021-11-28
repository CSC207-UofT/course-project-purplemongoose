import 'dart:convert';

import 'package:http/http.dart' as http;

class Constants {

  static const String address = "10.0.1.137:8082";

  static String _currentUser = "";

  static void setCurrentUser(String value) {
    _currentUser = value;
  }

  static String getCurrentUser() {
    return _currentUser;
  }

}

class UserBuilder {

  static String firstName = "";
  static String lastName = "";
  static String pronoun = "";
  static String title = "";

  static String phone = "";
  static String email = "";

  static void setFirstName (String name) {
    firstName = name;
  }

  static void setLastName (String name) {
    lastName = name;
  }

  static void setPronoun (String value) {
    pronoun = value;
  }

  static void setTitle (String value) {
    title = value;
  }

  static void setPhone (String value) {
    phone = value;
  }

  static void setEmail (String value) {
    email = value;
  }

  static void initializeProfile() async {

    Map data = {
      'accountUsername' : Constants.getCurrentUser(),
      'firstName' : firstName,
      'lastName' : lastName,
      'title' : title,
      'pronoun' : pronoun,
      'phone' : phone,
      'email' : email
    };

    String body = json.encode(data);

    http.Response response = await http.post(
      Uri.parse("http://" + Constants.address + "/profile/new"),
      headers: {"Content-Type": "application/json"},
      body: body,
    );

    // TODO do response processing

  }
}