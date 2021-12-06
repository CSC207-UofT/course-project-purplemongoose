class Person {

  String name;
  String phone;
  String email;
  String pronouns;
  String username;

  Person(
      this.name,
      this.phone,
      this.email,
      this.pronouns,
      this.username);

  Person.fromJson(Map<String, dynamic> json):
      name = json['name'] ?? '',
      phone = json['phone'] ?? '',
      email = json['email'] ?? '',
      pronouns = json['pronouns'] ?? '',
      username = json['username'] ?? '';
}