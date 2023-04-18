package com.jannetta.carpentriesadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class WorkshopLearnersSingle {
    @SerializedName("workshopID")
    @Expose
    String workshopID;

    @SerializedName("learners")
    @Expose
    ArrayList<Person> people = new ArrayList<>();

    public String getWorkshopID() {
        return workshopID;
    }

    public void setWorkshopID(String workshopID) {
        this.workshopID = workshopID;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }
}
