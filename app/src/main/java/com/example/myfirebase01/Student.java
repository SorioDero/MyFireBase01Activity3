package com.example.myfirebase01;

public class Student {
    private String name;
    private String phoneNumber;

    public Student() {
        // This is default constructor
    }

    public  String getStudentName() {
        return name;
    }

    public void  setStudentName(String name){
        this.name = name;
    }

    public String getStudentPhoneNumber() {
        return phoneNumber;
    }

    public void setStudentPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
