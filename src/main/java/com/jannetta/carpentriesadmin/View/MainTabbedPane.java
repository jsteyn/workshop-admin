package com.jannetta.carpentriesadmin.View;

import javax.swing.*;

public class MainTabbedPane extends JTabbedPane{


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MainTabbedPane() {
        super();
        addTab("Workshops", new WorkshopPanel());
        addTab("Lessons", new LessonPanel());
        addTab("People", new PeoplePanel());
        addTab( "Workshop Lessons", new WorkshopLessonsPanel());
    }

}