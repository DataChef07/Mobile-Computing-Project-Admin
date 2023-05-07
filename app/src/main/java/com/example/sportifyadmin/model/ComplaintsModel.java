package com.example.sportifyadmin.model;

import android.util.Log;
import android.widget.Toast;

public class ComplaintsModel {

    String sport;
    String type;
    String text;
    String state;

    public ComplaintsModel() {
    }

    public ComplaintsModel(String state,String text, String type, String sport) {
        this.sport = sport;
        this.type = type;
        this.text = text;
        this.state=state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState()
    {
        Log.d("check", "getProgress: ====>"+state);
        return (state);
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
