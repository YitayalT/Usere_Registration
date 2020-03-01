package com.example.usere_registration;

public class User {
    private String fullname, username, password,email,gender, phoneNo;


    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public User(String fullname, String username, String password,
                String email, String phoneNo, String gender) {
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.fullname = fullname;
        this.password = password;
        this.phoneNo = phoneNo;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
