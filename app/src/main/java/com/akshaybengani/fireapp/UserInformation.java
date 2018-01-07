package com.akshaybengani.fireapp;

/**
 * Created by root on 07/01/18.
 */
// This class will help us to store and retrive data in firebase
public class UserInformation {

    //Here we use those variables in which we want to store data in the Firebase
    // Remember to use public as in case to use these data outside the class
    public String name;
    public String address;

    public UserInformation(){


    }

    //We have used this constructor which points both the variables as a parameterized constructor
    // This will help us to pass single object to firebase to access multple data
    public UserInformation(String name, String address) {
        this.name = name;    //This point to name variable
        this.address = address;
    }
}
