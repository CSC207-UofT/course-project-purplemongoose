import 'package:flutter/material.dart';

class SignUp extends StatefulWidget {
  const SignUp({x}) : super();

  @override
  _SignUpState createState() => _SignUpState();

}

class _SignUpState extends State<SignUp> {

  final TextEditingController _newUsernameController = TextEditingController();
  final TextEditingController _newPasswordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        body: Center(
          child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: <Widget>[
                  Padding(padding: const EdgeInsets.only(bottom: 10),
                    child: Container(
                      width: 250,
                      height: 250,
                      child: Image.asset('assets/kard_logo.png'),
                    ),
                  ),

                  const Padding(padding: EdgeInsets.only(bottom: 100),
                      child: Text(
                        'Welcome to kard.\nSign up below',
                        textAlign: TextAlign.center,)
                  ),

                Padding(
                  padding: const EdgeInsets.only(
                      left: 50,
                      right: 50,
                      bottom: 10)
                  ,
                  child: TextFormField(
                    controller: _newUsernameController,
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
                    controller: _newPasswordController,
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
                        flex: 1,
                        child: ElevatedButton(
                          onPressed: () {
                            print('Username: ' + _newUsernameController.text);
                            print('Password: ' + _newPasswordController.text);

                          },
                          child: Text('Sign Up!'),
                          ),
                        ),
                    ],)
                )
              ]
          )
        )
    );
  }
}