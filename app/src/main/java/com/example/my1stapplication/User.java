package com.example.my1stapplication;

public class User {

    private String  name;

    private String  email;

    private String  phone;

    private String  address;

    private String  password;


    public User(String name, String phone, String address,String email, String password){

        this.name=name;
        this.phone=phone;
        this.address=address;
        this.email=email;
        this.password=password;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



}