package com.jannetta.carpentriesadmin.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.table.AbstractTableModel;

public class LessonTableModel extends AbstractTableModel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Logger logger = LoggerFactory.getLogger(getClass());
    private Lessons lessons = new Lessons();

    public LessonTableModel() {
        super();
        logger.trace("Create LessonTableModel");
    }

    @Override
    public int getRowCount() {
        return lessons.size();
    }

    @Override
    public int getColumnCount() {
        return Lesson.getColumnNames().length;
    }

    @Override
    public String getColumnName(int col) {
        return Lesson.getColumnNames()[col];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Lesson lesson = lessons.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return lesson.getLessonID();
            case 1:
                return lesson.getDescription();
            case 2:
                return lesson.getLessonURL();
            case 3:
                return lesson.getExercisesURL();
            case 4:
                return lesson.isPrint();
        }
        return null;
    }

    public void setLessons(Lessons lessons) {
        this.lessons = lessons;
    }

    public Lessons getLessons() {
        return lessons;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        if (col == (getColumnCount() - 1)) {
            return true;
        } else
            return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == getColumnCount() - 1) {
            return Boolean.class;
        } else {
            return String.class;
        }
    }
}
