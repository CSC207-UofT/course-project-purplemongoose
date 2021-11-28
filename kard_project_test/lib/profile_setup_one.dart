import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:kard_project_test/user_builder.dart';

class ProfileSetupOne extends StatefulWidget {
  const ProfileSetupOne({Key? key}) : super(key: key);

  @override
  _ProfileSetupOne createState() => _ProfileSetupOne();
}

class _ProfileSetupOne extends State<ProfileSetupOne> {

  final _formKey = GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {

    final _height = MediaQuery.of(context).size.height;

    return Scaffold(
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          Flexible(
            flex: 4,
            child: SizedBox(height: _height * 0.025,),
          ),
          _welcomeText(),
          _nameText(),
          _nameForm()
        ],
      ),
    );
  }

  Widget _nameForm () {
    return Form(
      key: _formKey,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          _firstNameField(),
          _lastNameField(),
          _next()
        ],
      ),
    );
  }

  Widget _firstNameField () {
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
          "First name cannot be empty" : null;
        },

        //TODO fix this fucking thing. It's fucking disgusting
        onSaved: (value) => UserBuilder.firstName = value ?? '',

        style: const TextStyle(
          color: Colors.black,
          fontSize: 22,
        ),

        cursorColor: Colors.black,

        decoration: const InputDecoration(
            hintText: 'First Name',
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

  Widget _lastNameField () {
    return Padding(
      padding: const EdgeInsets.only(
          left: 50,
          right: 50,
          bottom: 10,
          top: 10)
      ,
      child: TextFormField(

        //TODO fix this fucking thing. It's fucking disgusting
        onSaved: (value) => UserBuilder.firstName = value ?? '',

        style: const TextStyle(
          color: Colors.black,
          fontSize: 22,
        ),

        cursorColor: Colors.black,

        decoration: const InputDecoration(
            hintText: 'Last Name',
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

  Widget _next() {
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
                  print(UserBuilder.firstName);

                  Navigator.push(context,
                      MaterialPageRoute(builder: (context) =>
                      const ProfileSetupOne()));
                }
              },
              child: const Text('Next'),
            ),
          )
        ],)
    );
  }

  Widget _welcomeText() {
    return Flexible(
        flex: 1,

        child: Row(
          children: <Widget>[
            Flexible(
                flex: 1,
                child: Container()
            ),
            const Flexible(
                flex: 5,
                child: FittedBox(
                  fit: BoxFit.contain,
                  child: Text(
                      'Welcome to kard.',
                      style: TextStyle(fontSize: 400.0)
                  ),
                )
            ),
            Flexible(
                flex: 1,
                child: Container()
            ),
          ],
        )
    );
  }

  Widget _nameText() {
    return Flexible(
        flex: 1,

        child: Row(
          children: <Widget>[
            Flexible(
                flex: 1,
                child: Container()
            ),
            const Flexible(
                flex: 5,
                child: FittedBox(
                  fit: BoxFit.contain,
                  child: Text(
                      'Please enter your name',
                      style: TextStyle(fontSize: 400.0)
                  ),
                )
            ),
            Flexible(
                flex: 1,
                child: Container()
            ),
          ],
        )
    );
  }
}