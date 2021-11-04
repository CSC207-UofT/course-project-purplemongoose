import 'dart:convert';
import 'dart:async';

import 'package:http/http.dart' as http;

Future<User> loginUser(String username, String password) async {

  //TODO the address can change.....
  final response = await http.get(Uri.parse(
      'http://10.0.1.137:8082/start/login?username=' +
          username + '&password=' + password));

  //if (response.statusCode == 200) {
    // If the server did return a 200 OK response,
    // then parse the JSON.
    return User.fromJson(jsonDecode(response.body));
  //} else {
    // If the server did not return a 200 OK response,
    // then throw an exception.
    //throw Exception('Failed to load album');
  //}
}

class User {

  final bool loginSuccess;

  User(this.loginSuccess);

  User.fromJson(Map<String, dynamic> json):
        loginSuccess = json['login'];

  Map<String, dynamic> toJson() => {

  };

}