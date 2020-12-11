package com.example.project_android;

import io.realm.RealmObject;

public class UserModel extends RealmObject {
    private String firstName="";
    private String lastName="";
    private String phoneNumber="";
    private String userName = "";
    private String password = "";
    private String email="";

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setPassword(String password)

    {
        this.password = password;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getPassword()
    {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
