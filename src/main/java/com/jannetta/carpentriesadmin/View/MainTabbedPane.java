package com.jannetta.carpentriesadmin.View;

import javax.swing.*;

public class MainTabbedPane extends JTabbedPane{


    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public MainTabbedPane() {
        super();
        addTab("Workshops", new JPanel());
        addTab("Learners", new JPanel());
        addTab("Lessons", new JPanel());
    }

}