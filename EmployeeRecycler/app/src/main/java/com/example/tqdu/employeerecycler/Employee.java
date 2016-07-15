package com.example.tqdu.employeerecycler;

import java.io.Serializable;

/**
 * Created by tqdu on 7/12/2016.
 */
public class Employee implements Serializable{
    private String name;
    private int age;
    private String address;
    private String phone;
    private boolean isMale;

    public Employee(String name, boolean isMale, String address, String phone, int age) {
        this.name = name;
        this.isMale = isMale;
        this.address = address;
        this.phone = phone;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }
}
