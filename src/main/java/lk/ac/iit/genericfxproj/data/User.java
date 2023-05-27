package lk.ac.iit.genericfxproj.data;

import lk.ac.iit.genericfxproj.db.Encryption;

public class User {
    public String firstName;
    public String lastName;
    public int age;
    public String birthday;
    public Gender gender;
    public String civilStatus;
    public String country;
    public String email;
    public String mobile;
    public String username;
    public String password;
    public Encryption encryption;

    public User (
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

        encryption = new Encryption(password);
    }
}
