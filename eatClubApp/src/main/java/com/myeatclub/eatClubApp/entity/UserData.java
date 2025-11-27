package com.myeatclub.eatClubApp.entity;

public class UserData {
    private String city;
    private String phone;
    private String outletName;

    // Constructors
    public UserData() {}

    public UserData(String city, String phone, String outletName) {
        this.city = city;
        this.phone = phone;
        this.outletName = outletName;
    }

    // Getters
    public String getCity() {
        return city;
    }

    public String getOutletName() {
        return outletName;
    }

    public String getPhone() {
        return phone;
    }

    // Setters
    public void setCity(String city) {
        this.city = city;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserData [city=" + city + ", phoneNumber=" + phone + ", outletName=" + outletName + "]";
    }
}
