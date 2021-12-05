import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:kard_project_test/profile_setup_three.dart';
import 'package:kard_project_test/sign_in.dart';
import 'package:kard_project_test/sign_up_page.dart';
import 'package:kard_project_test/user_builder.dart';

class ProfileSetupTwo extends StatefulWidget {
  const ProfileSetupTwo({Key? key}) : super(key: key);

  @override
  _ProfileSetupTwo createState() => _ProfileSetupTwo();
}

class _ProfileSetupTwo extends State<ProfileSetupTwo> {

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
          _titleField(),
          _pronounField(),
          _buttons()
        ],
      ),
    );
  }

  Widget _titleField () {
    return Padding(
      padding: const EdgeInsets.only(
          left: 50,
          right: 50,
          bottom: 10,
          top: 10)
      ,
      child: TextFormField(

        //TODO fix this fucking thing. It's fucking disgusting
        onSaved: (value) => UserBuilder.title = value ?? '',

        style: const TextStyle(
          color: Colors.black,
          fontSize: 22,
        ),

        cursorColor: Colors.black,

        decoration: const InputDecoration(
            hintText: 'Title',
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

  Widget _pronounField () {
    return Padding(
      padding: const EdgeInsets.only(
          left: 50,
          right: 50,
          bottom: 10,
          top: 10)
      ,
      child: TextFormField(

        //TODO fix this fucking thing. It's fucking disgusting
        onSaved: (value) => UserBuilder.pronoun = value ?? '',

        style: const TextStyle(
          color: Colors.black,
          fontSize: 22,
        ),

        cursorColor: Colors.black,

        decoration: const InputDecoration(
            hintText: 'Pronoun',
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

  Widget _buttons() {
    return Padding(
      padding: const EdgeInsets.only(
          left: 50,
          right: 50,
          bottom: 10),

      child: Row(
        children: <Widget>[
          Expanded(
              flex: 4,

              child: OutlinedButton(
                onPressed: (){
                  Navigator.push(context,
                      MaterialPageRoute(builder: (context) => ProfileSetupThree()));
                },
                child: const Text('Skip'),
              )
          ),
          Expanded(
            flex: 1,

            child: Container(),
          ),
          Expanded(
            flex: 4,

            child: ElevatedButton(
              onPressed: (){
                _formKey.currentState!.save();
                if (UserBuilder.pronoun != "" ||
                    UserBuilder.title != "") {
                  Navigator.push(context,
                      MaterialPageRoute(builder: (context) => ProfileSetupThree()));
                }
              },
              child: const Text('Next'),
            ),
          )
        ],
      ),

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
            Flexible(
                flex: 5,
                child: FittedBox(
                  fit: BoxFit.contain,
                  child: Text(
                      'Hi! ' + UserBuilder.firstName,
                      style: const TextStyle(fontSize: 400.0)
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
                      'Please enter your preferred pronoun and title',
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