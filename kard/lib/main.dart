import 'package:flutter/material.dart';
import 'package:kard_project_test/sign_in.dart';
import 'package:kard_project_test/sign_up_page.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or simply save your changes to "hot reload" in a Flutter IDE).
        // Notice that the counter didn't reset back to zero; the application
        // is not restarted.
        primarySwatch: Colors.teal,
      ),
      home: const MyHomePage(title: 'Arthur\'s Bullshit'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  final TextEditingController _usernameController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();

  // final _formKey = GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Center(
       child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
            kardLogo(),
            welcomeText(),
            buttons()
          ]
        ),
      )
    );
  }

  Widget kardLogo() {
    return Flexible(
      flex: 2,
      child: Padding(
          padding: const EdgeInsets.only(
              left: 50,
              right: 50,
              bottom: 10,
              top: 10),
          //child: FittedBox(
          //fit: BoxFit.fitHeight,
          child: Image.asset('assets/kard_logo.png')
        //),
     )
    );
  }

  Widget welcomeText() {
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
            // child: Text('hello world')
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

  Widget buttons() {
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
                  MaterialPageRoute(builder: (context) => const SignUp()));
              },
              child: const Text('Sign Up'),
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
                Navigator.push(context,
                  MaterialPageRoute(builder: (context) => const LoginPage()));
              },
              child: const Text('Sign In'),
            ),
          )
        ],
      ),
    );
  }
}