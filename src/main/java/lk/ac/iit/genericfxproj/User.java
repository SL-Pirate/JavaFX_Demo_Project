package lk.ac.iit.genericfxproj;

public class User {
    String firstName;
    String lastName;
    int age;
    String birthday;
    Gender gender;
    String civilStatus;
    String country;
    String email;
    String mobile;
    String username;
    String password;

    User (
        String firstName,
        String lastName,
        int age,
        String birthday,
        Gender gender,
        String civilStatus,
        String country,
        String email,
        String mobile,
        String username,
        String password
        ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.birthday = birthday;
        this.gender = gender;
        this.civilStatus = civilStatus;
        this.country = country;
        this.email = email;
        this.mobile = mobile;
        this.username = username;
        this.password = password;
    }
}

enum Gender {
    Male,
    Female,
    Unspecified
}
