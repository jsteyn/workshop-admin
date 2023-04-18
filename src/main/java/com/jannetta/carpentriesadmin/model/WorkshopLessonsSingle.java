package com.jannetta.carpentriesadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkshopLessonsSingle {
    Logger logger = LoggerFactory.getLogger(getClass());

    @SerializedName("workshopID")
    @Expose
    String workshopID;

    @SerializedName("Lessons")
    @Expose
    Lessons lessons = new Lessons();

    public WorkshopLessonsSingle(String workshopID) {
        this.workshopID = workshopID;
    }
    public WorkshopLessonsSingle(String workshopID, Lessons lessons) {
        this.workshopID = workshopID;
        this.lessons = lessons;
    }

    public String getWorkshopID() {
        return workshopID;
    }

    public void setWorkshopID(String workshopID) {
        this.workshopID = workshopID;
    }

    public Lessons getLessons() {
        return lessons;
    }

    public void setLessons(Lessons lessons) {
        this.lessons = lessons;
    }
}
