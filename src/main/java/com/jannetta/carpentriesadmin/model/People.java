package com.jannetta.carpentriesadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class People  extends ArrayList<Person> {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    @SerializedName("people")
    @Expose
    private People people = this;

    public People() {
        super();
        // read file with lessons
    }

    public Person getPerson(int index) {
        return get(index);
    }

    public boolean exists(String personID) {
        for (int i = 0; i < size(); i++) {
            if (get(i).getPersonID().equals(personID)) {
                return true;
            }
        }
        return false;
    }
    public static String[] getColumnNames() {return Person.getColumnNames();}

    public static int getColumnCount() {return Person.getColumnCount();}
}
