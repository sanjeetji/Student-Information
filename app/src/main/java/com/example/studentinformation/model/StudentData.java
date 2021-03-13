package com.example.studentinformation.model;

/**
 * Created by SANJEET KUMAR on 09,February,2021, sk698166@gmail.com
 **/
public class StudentData {
    String roll;
    String name;
    String address;

    public StudentData(String roll, String name, String address) {
        this.roll = roll;
        this.name = name;
        this.address = address;
    }

    public StudentData() {
    }
    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
