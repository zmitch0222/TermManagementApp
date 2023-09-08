package com.mobile.zackmitchellc196.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Users")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int UserID;
    private String Username;
    private String Password;
    private String FirstName;
    private String LastName;
    private String PhoneNumber;
    private String Email;
    private boolean Faculty;

    public User(int userID, String username, String password, String firstName, String lastName, String phoneNumber, String email, boolean faculty) {
        UserID = userID;
        Username = username;
        Password = password;
        FirstName = firstName;
        LastName = lastName;
        PhoneNumber = phoneNumber;
        Email = email;
        Faculty = faculty;
    }

    public User() {
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public boolean isFaculty() {
        return Faculty;
    }

    public void setFaculty(boolean faculty) {
        Faculty = faculty;
    }
}
