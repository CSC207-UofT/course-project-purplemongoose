class Person {

  String name;
  String phone;
  String email;
  String pronouns;

  Person(
      this.name,
      this.phone,
      this.email,
      this.pronouns);

  Person.fromJson(Map<String, dynamic> json):
      name = json['name'],
      phone = json['phone'],
      email = json['email'],
      pronouns = json['pronouns'];
}