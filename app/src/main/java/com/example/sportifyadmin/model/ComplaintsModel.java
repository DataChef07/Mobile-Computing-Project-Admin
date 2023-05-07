package com.example.sportifyadmin.model;

import android.util.Log;
import android.widget.Toast;

public class ComplaintsModel {

    String sport;
    String type;
    String text;
    String progress;

    public ComplaintsModel() {
    }

    public ComplaintsModel(String text, String type, String sport,String progress) {
        this.sport = sport;
        this.type = type;
        this.text = text;
        this.progress=progress;
    }

    public String getProgress()
    {
        Log.d("check", "getProgress: ====>"+progress);
        return (progress);
    }
    public String setProgress(String s)
    {
        this.progress=progress;
        return s;
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
