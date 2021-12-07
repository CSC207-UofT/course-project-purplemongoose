import 'dart:convert';
import 'package:http/http.dart' as http;
import 'dart:async';


import 'package:flutter/material.dart';
import 'package:kard_project_test/main.dart';
import 'package:kard_project_test/person.dart';
import 'package:kard_project_test/user_builder.dart';

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  _HomePageState createState() => _HomePageState();

}

class _HomePageState extends State<HomePage> {
  @override
  Widget build(BuildContext context) {

    return WillPopScope(
      onWillPop: () async => false,
      child: Scaffold(
        backgroundColor: Colors.grey[900],
          body: Padding(
            padding: const EdgeInsets.fromLTRB(10, 50, 10, 10),
            child: Column(
              children: <Widget>[

                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: <Widget>[
                    IconButton(
                      icon: const Icon(Icons.arrow_back_ios_new, size: 35, color: Colors.white),
                      onPressed: () => Navigator.push(context,
                          MaterialPageRoute(builder: (context) => const MyHomePage(title: "title")))
                    ),
                    const Text('kards',
                      style: TextStyle (
                          color: Colors.white,
                          fontSize: 25
                      ),
                    ),
                    IconButton(
                      icon: const Icon(Icons.account_circle_outlined, size: 35, color: Colors.white),
                      onPressed: () => showDialog<String>(
                          context: context,
                          builder: (BuildContext context) => AlertDialog(
                              title: const Text('This is you!'),
                              content: Text('Your username is ' + Constants.getCurrentUser()!.username),
                              actions: <Widget>[
                                TextButton(
                                  onPressed: () {
                                    Navigator.pop(context, 'OK');
                                  },
                                  child: const Text('OK'),
                                ),
                              ]
                          )
                      ),
                    )
                  ],
                ),
                Expanded(child:
                  ListView(
                      scrollDirection: Axis.vertical,
                      shrinkWrap: true,
                      children: persons.map((p) {
                        return personDetailCard(p);
                      }).toList()
                  )
                ),
              ],
            ),
          ),
          floatingActionButton: FloatingActionButton(
            backgroundColor: const Color(0xff03dac6),
            foregroundColor: Colors.black,
            onPressed: () {
              Navigator.push(
                context,
                MaterialPageRoute<void>(
                  builder: (BuildContext context) => FullScreenDialog(),
                  fullscreenDialog: true,
                ),
              );
            },
            child: Icon(Icons.add),
          )
      ),
    );
  }

  void rebuildAllChildren(BuildContext context) {
    void rebuild(Element el) {
      el.markNeedsBuild();
      el.visitChildren(rebuild);
    }
    (context as Element).visitChildren(rebuild);
  }

  List<Person> persons = Constants.getCurrentUser()!.allContacts;

  Widget personDetailCard(person) {

    final double nameWidth = MediaQuery.of(context).size.width - 145;

    return Padding(
      padding: const EdgeInsets.all(10.0),
      child: GestureDetector(
        onTap: () {print('woop');},
      child: Card(
        color: Colors.grey[800],
        child: Padding(
          padding: const EdgeInsets.all(8.0),

            child: Row(
              children: <Widget>[
                Padding(
                  padding: const EdgeInsets.fromLTRB(2, 0, 8, 0),
                  child: Container(
                      width: 70.0,
                      height: 70.0,
                      child: const Icon(Icons.account_circle, size: 70, color: Colors.white),
                  )
                ),
                Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: <Widget>[
                    SizedBox(
                      width: (nameWidth),
                      child: Row(
                        //mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: <Widget> [
                          Text(person.name,
                            style: const TextStyle (
                                color: Colors.white,
                                fontSize: 18
                            ),
                          ),
                          const Spacer(),
                          IconButton(
                              icon: const Icon(Icons.cancel_outlined, size: 20, color: Colors.white),
                              onPressed: () => showDialog<String>(
                                  context: context,
                                  builder: (BuildContext context) => AlertDialog(
                                      title: const Text('Remove Contact?'),
                                      content: Text('Are you sure you want to remove '
                                      + person.name + ' from your contacts?'),
                                      actions: <Widget>[
                                        TextButton(
                                          onPressed: () => Navigator.pop(context, 'Cancel'),
                                          child: const Text('Cancel'),
                                        ),
                                        TextButton(
                                          onPressed: () {
                                            removeContact(person);
                                            Navigator.pop(context, 'OK');
                                          },
                                          child: const Text('OK'),
                                        ),
                                      ]
                                  )
                              )
                          )
                        ],
                      ),
                    ),

                    const Padding(padding: EdgeInsets.only(
                      bottom: 10
                    )),
                    Text(person.pronouns,
                      style: const TextStyle (
                          color: Colors.white,
                          fontSize: 12
                      ),
                    ),
                    const Padding(padding: EdgeInsets.only(
                        bottom: 3
                    )),
                    Text(person.phone,
                      style: const TextStyle (
                          color: Colors.white,
                          fontSize: 12
                      ),
                    ),
                    const Padding(padding: EdgeInsets.only(
                        bottom: 3
                    )),
                    Text(person.email,
                      style: const TextStyle (
                          color: Colors.white,
                          fontSize: 12
                      )
                    )
                  ],
                )
              ],
            ),
          )
        ),
      ),
    );
  }

  Future<void> removeContact(Person p) async {

      Map data = {
        'accountUsername' : Constants.getCurrentUser()!.username,
        'contactUsername' : p.username
      };

      String body = json.encode(data);

      http.Response response = await http.post(
        Uri.parse('http://' + Constants.address + '/contact/remove'),
        headers: {"Content-Type": "application/json"},
        body: body,
      );

      print(response.body);

      if (response.statusCode == 200) {
        final body = json.decode(response.body);
        if (body['response']) {
          Constants.getCurrentUser()!.fetchContacts().then((value) => {
            Navigator.push(context,
                MaterialPageRoute(builder: (context) => const HomePage()))
          });
        } else {
          print(body['errorCode']);
        }
      }
    }
}

class FullScreenDialog extends StatelessWidget {

  String addUsername = '';
  final _formKey = GlobalKey<FormState>();

  FullScreenDialog({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Colors.grey[900],
        title: const Text('Add a new kard!'),
      ),
      body: Column(
        children: <Widget>[
          Form(
              key: _formKey,
              child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                _usernameField(),
                _next(context)
              ],
            )
          )
        ],
      ),
    );
  }

  Widget _usernameField () {
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
          "username cannot be empty" : null;
        },

        onSaved: (value) => addUsername = value ?? '',

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
    );
  }

  Widget _next(BuildContext context) {
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
                  addContacts(context);
                }
              },
              child: const Text('Next'),
            ),
          )
        ],)
    );
  }

  Future<void> addContacts(BuildContext context) async {
    Map data = {
      'accountUsername' : Constants.getCurrentUser()!.username,
      'contactUsername' : addUsername
    };

    String body = json.encode(data);

    http.Response response = await http.post(
      Uri.parse('http://' + Constants.address + '/contact/add'),
      headers: {"Content-Type": "application/json"},
      body: body,
    );

    print(response.body);

    if (response.statusCode == 200) {
      final body = json.decode(response.body);
      if (body['response']) {
        Constants.getCurrentUser()!.fetchContacts().then((value) => {
          Navigator.push(context,
              MaterialPageRoute(builder: (context) => const HomePage()))
        });
      } else {
        print(body['errorCode']);
      }
    }
  }
}


