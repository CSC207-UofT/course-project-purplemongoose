import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:kard_project_test/profile_setup_one.dart';
import 'package:kard_project_test/user.dart';
import 'package:http/http.dart' as http;
import 'package:kard_project_test/user_builder.dart';

import 'home_page.dart';

class SignUp extends StatefulWidget {
  const SignUp({Key? key}) : super(key: key);

  @override
  _SignUpState createState() => _SignUpState();

}

class _SignUpState extends State<SignUp> {

  final TextEditingController _newUsernameController = TextEditingController();
  final TextEditingController _newPasswordController = TextEditingController();

  final _formKey = GlobalKey<FormState>();

  late String _username, _password;
  late Future<User> user;

  @override
  Widget build(BuildContext context) {

    final _width = MediaQuery.of(context).size.width;
    final _height = MediaQuery.of(context).size.height;

    double keyboard = MediaQuery.of(context).viewInsets.bottom;
    final double logoWidth, logoHeight;
    if (keyboard == 0) {
      logoWidth = _width/2;
      logoHeight = _width/2;
    } else if (_width/_height >= 0.5625) {
      logoWidth = 0;
      logoHeight = 0;
    } else {
      logoWidth = _width/4;
      logoHeight = _width/4;
    }

    return Scaffold(
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          Flexible(
            flex: 4,
            child: SizedBox(height: _height * 0.025),
          ),
          SizedBox(
            width: logoWidth,
            height: logoHeight,
            child: Image.asset('assets/kard_logo.png'),
          ),
          Flexible(
              flex: 10,
              child: _buildLoginPageForm()
          ),
        ],
      ),
    );

  }


  Widget _buildLoginPageForm() {
    return Form(
      key: _formKey,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          signupUsername(),
          signupPassword(),
          signupButton()
        ],
      ),
    );
  }

  Widget signupButton() {
    return Padding(
        padding: const EdgeInsets.only(
            left: 50,
            right: 50,
            bottom: 10),

        child: Row(children: [
          Expanded(
            flex: 10,
            child: ElevatedButton(
              onPressed: (){
                if (_formKey.currentState!.validate()) {

                  _formKey.currentState!.save();

                  signup(_username, _password);

                }
              },
              child: const Text('Create Account'),
            ),
          )
        ],)
    );
  }

  Widget signupPassword() {
    return Padding(
      padding: const EdgeInsets.only(
        left: 50,
        right: 50,
        bottom: 10,
      ),
      child: TextFormField(
        validator: (value) {
          return value == null||value.isEmpty?
          "Password cannot be empty" : null;
        },

        onSaved: (value) => _password = value ?? 'e',
        obscureText: true,
        style: const TextStyle(
          color: Colors.black,
          fontSize: 22,
        ),
        cursorColor: Colors.black,
        decoration: const InputDecoration(
            hintText: 'Password',
            hintStyle: TextStyle(color: Colors.black26),
            border: UnderlineInputBorder(
                borderSide: BorderSide(color: Colors.black)
            ),
            focusedBorder: UnderlineInputBorder(
                borderSide: BorderSide(color: Colors.black)
            )
        ),
      ),
    );
  }

  Widget signupUsername() {
    return Padding(
      padding: const EdgeInsets.only(
          left: 50,
          right: 50,
          bottom: 10,
          top: 10)
      ,
      child: TextFormField(
        validator: (value) {
          return value == null||value.isEmpty?
          "Username cannot be empty" : null;
        },

        onSaved: (value) => _username = value ?? 'e',

        style: const TextStyle(
          color: Colors.black,
          fontSize: 22,
        ),

        cursorColor: Colors.black,

        decoration: const InputDecoration(
            hintText: 'Name',
            hintStyle: TextStyle(color: Colors.black26),
            border: UnderlineInputBorder(
                borderSide: BorderSide(color: Colors.black)
            ),
            focusedBorder: UnderlineInputBorder(
                borderSide: BorderSide(color: Colors.black)
            )
        ),
      ),
    );
  }

  void signup(String username, String password) async {

    Map data = {
      'accountUsername' : username,
      'accountPassword' : password
    };

    String body = json.encode(data);

    http.Response response = await http.post(
        Uri.parse("http://" + Constants.address + "/account/create"),
        headers: {"Content-Type": "application/json"},
        body: body,
    );

    Constants.setCurrentUser(User(username, password, true));

    Navigator.push(context,
            MaterialPageRoute(builder: (context) => const ProfileSetupOne()));

  }

}

