import 'dart:convert';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:kard_project_test/user.dart';
import 'package:kard_project_test/user_builder.dart';

import 'home_page.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({Key? key}) : super(key: key);

  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  final _formKey = GlobalKey<FormState>();

  late String _username, _password;
  late Future<User> user;
  int _error = 0;

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
            flex: 3,
            child: SizedBox(height: _height*0.05),
          ),
          Flexible(
            flex: 2,
            child: showAlert()
          ),
          Flexible(
            flex: 1,
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
        ]
      ),
    );
  }

  Widget _buildLoginPageForm() {
    return Form(
      key: _formKey,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[

          Padding(
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
                  hintText: 'Username',
                  hintStyle: TextStyle(color: Colors.black26),
                  border: UnderlineInputBorder(
                      borderSide: BorderSide(color: Colors.black)
                  ),
                  focusedBorder: UnderlineInputBorder(
                      borderSide: BorderSide(color: Colors.black)
                  )
              ),
            ),
          ),

          Padding(
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

              onSaved: (value) => _password = value ?? '',
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
          ),
          Padding(
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

                        authenticate(_username, _password);

                      }
                    },
                    child: const Text('Log In'),
                  ),
                )
              ],)
          )
        ],
      ),
    );
  }

  void authenticate(String username, String password) async {

    User loginUser = await connectUser(_username, _password);

      if (loginUser.loginSuccess) {
        Constants.setCurrentUser(loginUser);
        Constants.getCurrentUser()!.fetchContacts().then((value) => {
          Navigator.push(context,
              MaterialPageRoute(builder: (context) => const HomePage()))

        });
      } else {
        setState(() {
          _error = -1;
        });
      }
    }



  Widget showAlert() {
      if (_error != 0) {

        String errorMessage;

        if (_error == -1) {
          errorMessage = "Invalid Username or Password";
        } else {
          errorMessage = "Whoops u did dum dum. This should never happen";
        }

        return Container(
          color: Colors.amberAccent,
          width: double.infinity,
          padding: const EdgeInsets.all(8.0),
          child: Row(
            children: <Widget>[
              const Padding(
                padding: EdgeInsets.only(right: 8.0),
                child: Icon(Icons.error_outline),
              ),
              Expanded(
                child: Text(
                errorMessage,
                  maxLines: 3,
                ),
              ),
              Padding(
                padding: const EdgeInsets.only(left: 8.0),
                child: IconButton(
                  icon: const Icon(Icons.close),
                  onPressed: () {
                    setState(() {
                      _error = 0;
                    });
                },
              ),
            )
          ],
        ),
      );
    }
    return const SizedBox(
      height: 0,
    );
  }


  /*
  Widget showAlert() {
    if (_error != null) {
      return Container(
        color: Colors.amberAccent,
        width: double.infinity,
        padding: EdgeInsets.all(8.0),
        child: Row(
          children: <Widget>[
            Padding(
              padding: const EdgeInsets.only(right: 8.0),
              child: Icon(Icons.error_outline),
            ),
            Expanded(
              child: AutoSizeText(
                _error,
                maxLines: 3,
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(left: 8.0),
              child: IconButton(
                icon: Icon(Icons.close),
                onPressed: () {
                  setState(() {
                    _error = null;
                  });
                },
              ),
            )
          ],
        ),
      );
    }
    return SizedBox(
      height: 0,
    );
  }
   */

}