package com.jannetta.carpentriesadmin.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.table.AbstractTableModel;

/**
 * This class defines the model for the table of workshops
 */
public class WorkshopTableModel extends AbstractTableModel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Workshops workshops = new Workshops();
    private Logger logger = LoggerFactory.getLogger(getClass());

    public WorkshopTableModel() {
        super();
        logger.trace("Create WorkshopTableModel");
    }

    public int getColumnCount() {
//		if (workshops == null)
//			return 0;
//		else
        return workshops.getColumnCount();
    }

    public int getRowCount() {
        if (workshops == null)
            return 0;
        else
            return workshops.size();
    }

    public Object getValueAt(int row, int col) {
        Workshop workshop = workshops.get(row);
        switch (col) {
            case 0:
                return workshop.getWorkshopID();
            case 1:
                return workshop.getWorkshopName();
            case 2:
                return workshop.getWorkshopType();
            case 3:
                return workshop.getStartDate();
            case 4:
                return workshop.getEndDate();
            case 5:
                return workshop.getWorkshopURL();
            case 6:
                return workshop.isPrint();
        }

        return null;

    }

    @Override
    public String getColumnName(int col) {
        return workshops.getColumnNames()[col];
    }

    public Workshops getWorkshops() {
        return workshops;
    }

    public void setWorkshops(Workshops workshops) {
        logger.trace("Set workshops object");
        this.workshops = workshops;
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
