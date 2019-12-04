package com.example.campusnavigation;

public class Node {
    private String name;
    private String number;
    private String about;
    // 经度
    private double longitude;
    // 纬度
    private double latitude;

    public Node(String name, String number, String about) {
        this.name = name;
        this.number = number;
        this.about = about;
    }

    public Node(String name, String number, double longitude, double latitude) {
        this.name = name;
        this.number = number;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Node(String name, String number, double longitude, double latitude, String about) {
        this.name = name;
        this.number = number;
        this.about = about;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
