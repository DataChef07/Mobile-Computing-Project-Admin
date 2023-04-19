package com.example.sportifyadmin.model;

public class ComplaintsModel {

    String sport;
    String type;
    String text;

    public ComplaintsModel() {
    }

    public ComplaintsModel(String sport, String type, String text) {
        this.sport = sport;
        this.type = type;
        this.text = text;
    }

    public String getSport() {
        return "Sport: "+sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getType() {
        return "Type: "+type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return "Complaint: "+text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
