package com.jannetta.carpentriesadmin.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.table.AbstractTableModel;

public class LearnerTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private Learners learners;
	private Logger logger = LoggerFactory.getLogger(getClass());

	public LearnerTableModel() {
		super();
		logger.trace("Create LearnerTableModel");
	}

	public Object getValueAt(int row, int col) {
		Learner learner = learners.get(row);
		switch (col) {
			case 0:
				return learner.getWorkshop();
			case 1:
				return learner.getBadge();
			case 2:
				return learner.getInstructor();
			case 3:
				return learner.getUser_id();
			case 4:
				return learner.getFirstname();
			case 5:
				return learner.getInitials();
			case 6:
				return learner.getLastname();
			case 7:
				return learner.getEmail();
			case 8:
				return learner.getDate();
			case 9:
				return learner.getLessonString();
			case 10:
				return learner.isPrint();
		}

		return null;
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		if (col == (Learners.getColumnCount() - 1)) {
			return true;
		} else
			return false;
	}

	@Override
	public String getColumnName(int col) {
		return Learners.getColumnNames()[col];
	}

	public int getColumnCount() {
		return Learners.getColumnCount();
	}

	public int getRowCount() {
		if (learners == null)
			return 0;
		else
			return learners.size();
	}

	public void setLearners(Learners learners) {
		logger.trace("Set learners object");
		this.learners = learners;
	}

	public Learners getLearners() {
		return learners;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if (columnIndex == Learners.getColumnCount() - 1) {
			return Boolean.class;
		} else {
			return String.class;
		}
	}

}
