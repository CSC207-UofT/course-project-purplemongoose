import 'dart:convert';
import 'dart:async';

import 'package:http/http.dart' as http;
import 'package:kard_project_test/person.dart';
import 'package:kard_project_test/user_builder.dart';

Future<User> connectUser(String username, String password) async {
  Map data = {
    'accountUsername' : username,
    'accountPassword' : password
  };

  String body = json.encode(data);

  http.Response response = await http.post(
    Uri.parse('http://' + Constants.address + '/kard/login'),
    headers: {"Content-Type": "application/json"},
    body: body,
  );

  if (response.statusCode == 200) {
    final body = json.decode(response.body);

    User currentUser = User(username, password, body['response']);

    return currentUser;
  } else {
    throw Exception('Failed to connect to the kard server');
  }
}

class User {

  String username;
  String password;
  bool loginSuccess;

  List<Person> allContacts = [];

  User(this.username, this.password, this.loginSuccess);

  Future<void> fetchContacts() async {

    allContacts = [];

    http.Response response = await http.get(
      Uri.parse('http://' + Constants.address +
          '/contact/display?username=' + username +
          '&param=name&order=ascend')
    );

    if (response.statusCode == 200) {
      makeContacts(response);
    }
  }

  void makeContacts(http.Response r) {
    final body = json.decode(r.body);

    print(body);
    List<dynamic> list = body["response"];

    for (var i = 0; i < list.length; i++) {
      allContacts.add(Person.fromJson(list[i]));
    }
  }
}