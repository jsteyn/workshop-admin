package com.jannetta.carpentriesadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Person {
    private static Logger logger = LoggerFactory.getLogger(Person.class);
    private static final String[] columnNames = {"User ID", "First Name", "Middle Names", "Lastname",  "Email", "Programme", "Print"};

    @SerializedName("personID")
    @Expose
    private String personID;

    @SerializedName("firstName")
    @Expose
    private String firstName;

    @SerializedName("middleNames")
    @Expose
    private String middleNames;

    @SerializedName("lastName")
    @Expose
    private String lastName;

    @SerializedName("contactNumber")
    @Expose
    private String contactNumber;

    @SerializedName("programme")
    @Expose
    private String programme;

    @SerializedName("stage")
    @Expose
    private String stage;

    @SerializedName("school")
    @Expose
    private String school;

    @SerializedName("email")
    @Expose
    private String email;

    private boolean print = false;

    public Person(String personID, String firstName, String middleNames, String lastName, String email, String programme) {
        this.personID = personID;
        this.firstName = firstName;
        this.middleNames = middleNames;
        this.lastName = lastName;
        this.email = email;
        this.programme = programme;
        this.print = false;
    }

    public Person() {
        this.personID = "";
        this.firstName = "";
        this.middleNames = "";
        this.lastName = "";
        this.contactNumber = "";
        this.programme = "";
        this.stage = "";
        this.school = "";
        this.email = "";
    }

    public Person(String personID, String firstName, String middleNames, String lastName, String email, String programme, String contactNumber, String stage, String school) {
        this.personID = personID;
        this.firstName = firstName;
        this.middleNames = middleNames;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
        this.programme = programme;
        this.stage = stage;
        this.school = school;
        this.email = email;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleNames() {
        return middleNames;
    }

    public void setMiddleNames(String middleNames) {
        this.middleNames = middleNames;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public static String[] getColumnNames() {return columnNames;}

    public static int getColumnCount() {return columnNames.length;}


    public boolean isPrint() {
        return print;
    }

    public void setPrint(boolean print) {
        this.print = print;
    }

}
