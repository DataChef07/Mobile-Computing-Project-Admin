package com.example.sportifyadmin.model;

public class EquipmentModel {

    String name;
    String courts;
    String equipments;

    public EquipmentModel() {
    }

    public EquipmentModel(String name, String courts, String equipments) {
        this.name = name;
        this.courts = courts;
        this.equipments = equipments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourts() {
        return courts;
    }

    public void setCourts(String courts) {
        this.courts = courts;
    }

    public String getEquipments() {
        return equipments;
    }

    public void setEquipments(String equipments) {
        this.equipments = equipments;
    }
}
