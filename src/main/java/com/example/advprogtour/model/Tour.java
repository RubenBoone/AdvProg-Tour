package com.example.advprogtour.model;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

@Entity
public class Tour {
    @Id
    private String id;
    private String monuCode;
    private String tourCode;
    private double tourTime;
    private double entryFee;
    private String title;
    private String description;
    private int avgCustomer;

    public Tour() {
    }

    public Tour(String id, String monuCode, String tourCode, double tourTime, double entryFee, String title, String description, int avgCustomer) {
        this.id = id;
        this.monuCode = monuCode;
        this.tourCode = tourCode;
        this.tourTime = tourTime;
        this.entryFee = entryFee;
        this.title = title;
        this.description = description;
        this.avgCustomer = avgCustomer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMonuCode() {
        return monuCode;
    }

    public void setMonuCode(String monuCode) {
        this.monuCode = monuCode;
    }

    public String getTourCode() {
        return tourCode;
    }

    public void setTourCode(String tourCode) {
        this.tourCode = tourCode;
    }

    public double getTourTime() {
        return tourTime;
    }

    public void setTourTime(double tourTime) {
        this.tourTime = tourTime;
    }

    public double getEntryFee() {
        return entryFee;
    }

    public void setEntryFee(double entryFee) {
        this.entryFee = entryFee;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAvgCustomer() {
        return avgCustomer;
    }

    public void setAvgCustomer(int avgCustomer) {
        this.avgCustomer = avgCustomer;
    }
}
