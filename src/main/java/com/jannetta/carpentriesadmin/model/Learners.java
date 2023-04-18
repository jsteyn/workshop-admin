package com.jannetta.carpentriesadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Learners extends ArrayList<Learner> {
	
	@SerializedName("learners")
	@Expose
	private Learners learners = this;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	/**
	 * The number of attributes (columns) in the Workshop class.
	 * @return
	 */
	public static int getColumnCount() {
		return Learner.getColumnNumber();
	}
	
	public static String[] getColumnNames() {
		return Learner.getColumnNames();
	}

	public Learners getLearners() {
		return learners;
	}

	public void setLearners(Learners learners) {
		this.learners = learners;
	}

	public boolean exists(String learnerID) {
		for (int i = 0; i < this.size(); i++) {
			if (this.get(i).getUser_id().equals(learnerID)) {
				return true;
			}
		}
		return false;
	}

}
