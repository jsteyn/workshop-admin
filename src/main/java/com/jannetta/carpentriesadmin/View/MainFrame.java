package com.jannetta.carpentriesadmin.View;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    static MainTabbedPane pn_main = new MainTabbedPane();
    static MenuBar menuBar = new MenuBar();
    private Logger logger = LoggerFactory.getLogger(getClass());

    public MainFrame() {
        super("CarpentriesAdmin");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(1024, 768);
    }
}
