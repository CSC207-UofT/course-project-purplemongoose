import 'package:flutter/material.dart';

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

        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key}) : super(key: key);


  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {


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
          child: FittedBox(
              fit: BoxFit.fitHeight,
              child: Image.asset('assets/kard_logo.png')
          ),
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
                  // Navigator.push(context,
                  //     MaterialPageRoute(builder: (context) => SignUp()));
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
                // Navigator.push(context,
                //     MaterialPageRoute(builder: (context) => LoginPage()));
              },
              child: const Text('Sign In'),
            ),
          )
        ],
      ),

    );
  }
}
