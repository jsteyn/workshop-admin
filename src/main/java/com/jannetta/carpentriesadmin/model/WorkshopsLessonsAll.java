package com.jannetta.carpentriesadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class WorkshopsLessonsAll extends ArrayList<WorkshopLessonsSingle> {
    Logger logger = LoggerFactory.getLogger(getClass());

    @SerializedName("WorkshopsLessons")
    @Expose
    WorkshopsLessonsAll workshops__LessonsAll = this;

    public WorkshopLessonsSingle getWorkshop(String workshopID) {
        for (int i = 0; i < this.size(); i++) {
            if (get(i).getWorkshopID().equals(workshopID)) {
                logger.trace("Workshop " + workshopID + " found.");
                return get(i);
            }
        }
        return null;
    }
}
