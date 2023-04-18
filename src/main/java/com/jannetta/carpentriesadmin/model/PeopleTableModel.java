package com.jannetta.carpentriesadmin.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.table.AbstractTableModel;

public class PeopleTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    private People people;
    private Logger logger = LoggerFactory.getLogger(getClass());

    public PeopleTableModel() {
        super();
        logger.trace("Create LearnerTableModel");
    }

    @Override
    public int getRowCount() {
        if (people == null)
            return 0;
        else
            return people.size();
    }

    @Override
    public String getColumnName(int columnIndex) { return People.getColumnNames()[columnIndex];}

    @Override
    public int getColumnCount() {
        return People.getColumnCount();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Person person = people.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return person.getPersonID();
            case 1:
                return person.getFirstName();
            case 2:
                return person.getMiddleNames();
            case 3:
                return person.getLastName();
            case 4:
                return person.getEmail();
            case 5:
                return person.getProgramme();
            case 6:
                return person.isPrint();

        }
        return null;
    }

    public void setPeople(People people) {
        logger.trace("Set people object");
        this.people = people;
    }

    public People getPeople() {return people;}

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == getColumnCount() - 1) {
            return Boolean.class;
        } else {
            return String.class;
        }
    }}
