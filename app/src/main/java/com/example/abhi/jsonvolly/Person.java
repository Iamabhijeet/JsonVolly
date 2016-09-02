package com.example.abhi.jsonvolly;

/**
 * Created by abhi on 01-09-2016.
 */
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class Person extends RealmObject {
    String UserName,UserEmail;

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    @PrimaryKey

    String UserID;

    public void setUserName(String personName) {
        UserName = personName;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public String getUserID() {
        return UserID;
    }

    @Override
    public String toString() {
        return "Person{" +
                "UserName='" + UserName + '\'' +
                ", UserID=" + UserID +
                '}';
    }


}