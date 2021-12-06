import 'dart:convert';

import 'package:http/http.dart' as http;
import 'package:kard_project_test/user.dart';

class Constants {

  static const String address = "10.0.1.137:8082";

  static User? _currentUser;

  static void setCurrentUser(User value) {
    _currentUser = value;
  }

  static User? getCurrentUser() {
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
      'accountUsername' : Constants.getCurrentUser()!.username,
      'firstName' : firstName,
      'lastName' : lastName,
      'title' : title,
      'pronoun' : pronoun,
      'phone' : phone,
      'email' : email
    };

    String body = json.encode(data);

    http.Response response = await http.post(
      Uri.parse("http://" + Constants.address + "/profile/create"),
      headers: {"Content-Type": "application/json"},
      body: body,
    );

    // TODO do response processing

  }
}