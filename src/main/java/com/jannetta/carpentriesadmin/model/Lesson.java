package com.jannetta.carpentriesadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lesson {
	private static Logger logger = LoggerFactory.getLogger(Learner.class);
	private static final String[] columnNames = { "Lesson ID", "Description", "Lesson URL", "Exercises URL", "Select" };


    @SerializedName("lessonID")
    @Expose
    private String lessonID;    
    
    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("lessonURL")
    @Expose
    private String lessonURL;

    @SerializedName("exercisesURL")
    @Expose
    private String exercisesURL;

    boolean print = false;

    public Lesson(String lessonID, String description, String lessonURL, String exercisesURL) {
        this.lessonID = lessonID;
        this.description = description;
        this.lessonURL = lessonURL;
        this.exercisesURL = exercisesURL;
        logger.trace("Create a lesson");
	}

	public String getLessonID() {
        return lessonID;
    }

    public void setLessonID(String lessonID) {
        this.lessonID = lessonID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }

    public String getLessonURL() {
        return lessonURL;
    }

    public void setLessonURL(String lessonURL) {
        this.lessonURL = lessonURL;
    }

    public String getExercisesURL() {
        return exercisesURL;
    }

    public void setExercisesURL(String exercisesURL) {
        this.exercisesURL = exercisesURL;
    }

    public boolean isPrint() {
        return print;
    }

    public void setPrint(boolean print) {
        this.print = print;
    }


}
