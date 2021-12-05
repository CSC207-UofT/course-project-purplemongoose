import 'package:flutter/material.dart';
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
        // appBar: AppBar(
        //   title: const Text("data"),
        //   leading: IconButton(
        //     icon: const Icon(Icons.ac_unit),
        //     onPressed: () => Navigator.of(context).pop(),
        //   ),
        // ),
        backgroundColor: Colors.grey[900],
          body: Padding(
            padding: const EdgeInsets.fromLTRB(10, 50, 10, 10),
            child: Column(
              children: <Widget>[
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: <Widget>[
                    IconButton(
                      icon: const Icon(Icons.menu, size: 35, color: Colors.white),
                      onPressed: () => Navigator.of(context).pop(),
                    ),
                    const Text('kards',
                      style: TextStyle (
                          color: Colors.white,
                          fontSize: 25
                      ),
                    ),
                    Icon(Icons.notifications_none, size: 35, color: Colors.white)
                  ],
                ),
                Column(
                    children: persons.map((p) {
                      return personDetailCard(p);
                    }).toList()
                )
              ],
            ),
          ),
      ),
    );

    // return Scaffold(
    //   backgroundColor: Colors.grey[900],
    //   body: Padding(
    //     padding: const EdgeInsets.fromLTRB(10, 50, 10, 10),
    //     child: Column(
    //       children: <Widget>[
    //         Row(
    //           mainAxisAlignment: MainAxisAlignment.spaceBetween,
    //           children: const <Widget>[
    //             Icon(Icons.menu, size: 35, color: Colors.white),
    //             Text('Notifications',
    //               style: TextStyle (
    //                   color: Colors.white,
    //                   fontSize: 25
    //               ),
    //             ),
    //             Icon(Icons.notifications_none, size: 35, color: Colors.white)
    //           ],
    //         ),
    //         Column(
    //             children: persons.map((p) {
    //               return personDetailCard(p);
    //             }).toList()
    //         )
    //       ],
    //     ),
    //   ),
    // );
  }


  List<Person> persons = Constants.getCurrentUser()!.allContacts;


  Widget personDetailCard(Person) {
    return Padding(
      padding: const EdgeInsets.all(10.0),
      child: Card(
        color: Colors.grey[800],
        child: Padding(
          padding: const EdgeInsets.all(8.0),
          child: Row(
            children: <Widget>[
              // Padding(
              //   padding: const EdgeInsets.all(8.0),
              //   child: Container(
              //       width: 50.0,
              //       height: 50.0,
              //       decoration: BoxDecoration(
              //           shape: BoxShape.circle,
              //           image: DecorationImage(
              //               fit: BoxFit.cover,
              //               image: AssetImage(Person.profileImg)
              //           )
              //       )),
              // ),
              Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Text(Person.firstName,
                    style: const TextStyle (
                        color: Colors.white,
                        fontSize: 18
                    ),
                  ),
                  Text(Person.phone,
                    style: const TextStyle (
                        color: Colors.white,
                        fontSize: 12
                    ),
                  )
                ],
              )
            ],
          ),
        ),
      ),
    );
  }



}
