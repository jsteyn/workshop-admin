package com.jannetta.carpentriesadmin.View;

import com.jannetta.carpentriesadmin.controller.Globals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainFrame extends JFrame {
    Globals globals = Globals.getInstance();
    static MainTabbedPane pn_main = new MainTabbedPane();
    static MenuBar menuBar;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public MainFrame() {
        super("CarpentriesAdmin");
        menuBar = new MenuBar(this);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        try {
            Image icon = toolkit.getImage(ClassLoader.getSystemResource("CarpentriesAdminLogo.png"));
            //URL resource = getClass().getClassLoader().getResource("Certify1.png");
            //BufferedImage image = ImageIO.read(resource);
            setIconImage(icon);
        } catch (NullPointerException e) {
            logger.error("CarpentriesAdmin.png not found.");
        }
        setJMenuBar(menuBar);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closer();
                System.exit(0);
            }
        });

        setContentPane(pn_main);
        pack();
        setVisible(true);
        setSize(1024, 768);
    }

    /**
     * Method to close things before exiting the application
     */
    public boolean closer() {
        logger.debug("People file: " + globals.isPeoplesaved());
        if (!globals.isPeoplesaved()) {
            int result1 = JOptionPane.showConfirmDialog(this, "Do you want to save people before exiting?",
                    "Exit program?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result1 == JOptionPane.YES_OPTION) {
                logger.trace("Save person file");
                globals.saveJSON(globals.getProperty("peoplefile"), globals.getAllPeople());
                globals.setPeoplesaved(true);
                return true;
            } else if (result1 == JOptionPane.NO_OPTION) {
                logger.trace("Quit without saving people");
                return true;
            } else {
                logger.trace("Cancel quitting");
                return false;
            }

        }
        logger.debug("workshops file: " + globals.isWorkshopssaved());
        if (!globals.isWorkshopssaved()) {
            int result2 = JOptionPane.showConfirmDialog(this, "Do you want to save workshops before exiting?",
                    "Exit program?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result2 == JOptionPane.YES_OPTION) {
                logger.trace("Save workshop file");
                globals.saveJSON(globals.getProperty("workshopfile"), globals.getWorkshops());
                globals.setWorkshopssaved(true);
                return true;
            } else if (result2 == JOptionPane.NO_OPTION) {
                logger.trace("Quit without saving workshops");
                return true;
            } else {
                logger.trace("Cancel quitting");
                return false;
            }
        }
        logger.debug("lessons file: " + globals.isLessonssaved());
        if (!globals.isLessonssaved()) {
            int result2 = JOptionPane.showConfirmDialog(this, "Do you want to save lessons before exiting?",
                    "Exit program?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result2 == JOptionPane.YES_OPTION) {
                logger.trace("Save lessons file");
                globals.saveJSON(globals.getProperty("lessonfile"), globals.getLessons());
                globals.setLessonssaved(true);
                return true;
            } else if (result2 == JOptionPane.NO_OPTION) {
                logger.trace("Quit without saving lessons");
                return true;
            } else {
                logger.trace("Cancel quitting");
                return false;
            }
        }
        logger.debug("WorkshopsLessons file: " + globals.isWorkshopLessonssaved());
        if (!globals.isWorkshopLessonssaved()) {
            int result2 = JOptionPane.showConfirmDialog(this, "Do you want to save lessons selected for a workshop before exiting?",
                    "Exit program?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result2 == JOptionPane.YES_OPTION) {
                logger.trace("Save workshops lessons file");
                globals.saveJSON(globals.getProperty("workshopLessonsfile"), globals.getWorkshopsLessons());
                globals.setLessonssaved(true);
                return true;
            } else if (result2 == JOptionPane.NO_OPTION) {
                logger.trace("Quit without saving lessons");
                return true;
            } else {
                logger.trace("Cancel quitting");
                return false;
            }
        }
        return false;
    }
}
