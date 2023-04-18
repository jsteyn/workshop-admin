package com.jannetta.carpentriesadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Lessons extends ArrayList<Lesson> {
    private static Logger logger = LoggerFactory.getLogger(Lessons.class);

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @SerializedName("lessons")
	@Expose
    private Lessons lessons = this;
    
    public Lessons() {
        super();
        // read file with lessons
    }

    public Lesson getLessonIndex(int index) {
        return get(index);
    }

    public boolean exists(String lessonID) {
        for (int i = 0; i < size(); i++) {
            if (get(i).getLessonID().equals(lessonID)) {
                return true;
            }
        }
        return false;
    }

    public Lesson getLesson(String id) {
        for (int i = 0; i < size(); i++) {
            if (get(i).getLessonID().equals(id)) {
                return get(i);
            }
        }
        return null;
    }

    public void delLesson(String id) {
        int index = this.indexOf(id);
        logger.trace("Delete lesson " + id);
        this.remove(index);
    }

    public void delLessons() {
        ArrayList<Lesson> delList = new ArrayList<>();
        this.forEach(lesson -> {
            if (lesson.isPrint()) {
                delList.add(lesson);
            }
        });
        for (int i = 0; i < delList.size(); i++) {
            Lesson l = delList.get(i);
            lessons.remove(l);
        }
    }


}
