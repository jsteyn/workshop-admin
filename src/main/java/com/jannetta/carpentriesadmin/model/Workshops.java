package com.jannetta.carpentriesadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Workshops extends ArrayList<Workshop>{
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@SerializedName("workshops")
	@Expose
	Workshops workshops = this;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The number of attributes (columns) in the Workshop class.
	 * @return
	 */
	public int getColumnCount() {
		return Workshop.getColumnCount();
	}
	
	public String[] getColumnNames() {
		return Workshop.getColumnNames();
	}

	public String[] getWorkshopNames() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("All");
		this.forEach((ws) -> {
			names.add(ws.getWorkshopName());
		});
		String[] ret =  names.toArray(new String[0]);
		logger.debug("Workshop names: " + ret.length);
		return ret;
	}

	public String[] getWorkshopIDs() {
		ArrayList<String> ids = new ArrayList<String>();
		ids.add("All");
		this.forEach((ws) -> {
			ids.add(ws.getWorkshopID());
		});
		String[] ret =  ids.toArray(new String[0]);
		logger.debug("Workshop IDs: " + ret.length);
		return ret;
	}

	public boolean exists(String workshopID){
		for (int i = 0; i < this.size(); i++) {
			if (get(i).getWorkshopID().equals(workshopID))
			return true;
		}
		return false;
	}

	public Workshop getWorkshop(String workshopID) {
		for (int i = 0; i < this.size(); i++) {
			if (get(i).getWorkshopID().equals(workshopID))
				return get(i);
		}
		return null;
	}

}
