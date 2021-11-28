import 'dart:convert';
import 'dart:async';
import 'dart:math';

import 'package:http/http.dart' as http;
import 'package:kard_project_test/user_builder.dart';

/* Deprecated given new implementation of backend server
   Code is being saved for future reference
TODO safely delete this method

Future<User> loginUser(String username, String password) async {

  //TODO the address can change.....
  final response = await http.get(Uri.parse(
      'http://localhost:8082/start/login?username=' +
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
}*/

// bool loginUser(String username, String password) async {
//   bool login = false;
//
//   var response = connectUser(username, password);//.then((http.Response response) {
//     final body = json.decode(response.body);
//
//     if (body['response']) {
//       login = true;
//     }
//   });
//
//   print(login);
//
//   return login;
// }

Future<bool> connectUser(String username, String password) async {
  Map data = {
    'accountUsername' : username,
    'accountPassword' : password
  };

  String body = json.encode(data);

  http.Response response = await http.post(
    Uri.parse('http://' + Constants.address + '/start/login'),
    headers: {"Content-Type": "application/json"},
    body: body,
  );

  if (response.statusCode == 200) {
    final body = json.decode(response.body);
    return body['response'];//User.fromJson(response.body);
  } else {
    throw Exception('Failed to connect to the kard server');
  }
}


/*Future<User> signupUser(String username, String password) async {
  final response = await http.get(Uri.parse(
    'http://10.0.1.163:8082/start/signup?username=' + username + '&passowrd=' +
      password));

  return
}*/

class User {

  final bool loginSuccess;

  User(this.loginSuccess);

  User.fromJson(Map<String, dynamic> json):
        loginSuccess = json['login'];

}