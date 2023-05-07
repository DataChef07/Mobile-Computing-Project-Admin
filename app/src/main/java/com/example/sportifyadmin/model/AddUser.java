package com.example.sportifyadmin.model;

public class AddUser {
    String name;
    String sport;
    String equipment;
    String timeStamp;

    public AddUser() {
    }

    public AddUser(String name, String sport, String equipment, String timeStamp) {
        this.name = name;
        this.sport = sport;
        this.equipment = equipment;
        this.timeStamp = timeStamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
