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
    Uri.parse('http://' + Constants.address + '/start/login'),
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

  late List<Person> allContacts;

  User(this.username, this.password, this.loginSuccess);

  Future<void> fetchContacts() async {
    /*TODO: Implement.
    http.Response response = await http.get(
      Uri.parse('http://' + Constants.address +
          '/account/display/contact?username=' + username +
          '&param=name&order=ascend')
    );

   print(response.body);*/
   allContacts = [
     Person("firstName",
       "lastName",
       "phone",
       "email",
       "pronouns",
       "title"),
     Person("ooga",
         "booga",
         "123",
         "123@123.com",
         "he",
         "mr"),
     Person("cooga",
         "booga",
         "321",
         "321@321.com",
         "she",
         "ms")];

  }
}