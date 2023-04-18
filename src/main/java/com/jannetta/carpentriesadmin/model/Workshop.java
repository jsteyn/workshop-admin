package com.jannetta.carpentriesadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Workshop {
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     String workshopID, String workshopName, String startDate, String endDate,
     String startTime, String endTime, String communityDocURL, String preWorkshopSurveyURL,
     String postWorkshopSurveyURL, String surveyResults, String host, String hostInstitution,
     String workshopWebsite, String emailRemindDate, String postWorkshopSurveyRemindDate
     **/

    private static final String[] columnNames = {"Workshop ID", "Workshop Name", "WorkshopType", "Date From", "Date To", "URL", "Print"};

    @SerializedName("workshopID")
            @Expose
    String workshopID;

    @SerializedName("workshopName")
            @Expose
    String workshopName;

    @SerializedName("workshopType")
    @Expose
    private String workshopType;

    @SerializedName("startDate")
    @Expose
    String startDate;

    @SerializedName("endDate")
    @Expose
    String endDate;

    @SerializedName("startTime")
    @Expose
    String startTime;

    @SerializedName("endTime")
    @Expose
    String endTime;

    @SerializedName("workshopURL")
    @Expose
    String workshopURL;

    @SerializedName("communityDocURL")
    @Expose
    String communityDocURL;

    @SerializedName("preWorkshopSurveyURL")
    @Expose
    String preWorkshopSurveyURL;

    @SerializedName("postWorkshopSurveyURL")
    @Expose
    String postWorkshopSurveyURL;

    @SerializedName("surveyResults")
    @Expose
    String surveyResults;

    @SerializedName("host")
    @Expose
    String host;

    @SerializedName("hostInstitution")
    @Expose
    String hostInstitution;

    @SerializedName("workshopWebsite")
    @Expose
    String workshopWebsite;

    @SerializedName("emailRemindDate")
    @Expose
    String emailRemindDate;

    @SerializedName("postWorkshopSurveyRemindDate")
    @Expose
    String postWorkshopSurveyRemindDate;

    private boolean print = false;

    public String getWorkshopID() {
        return workshopID;
    }

    public void setWorkshopID(String workshopID) {
        this.workshopID = workshopID;
    }

    public String getWorkshopName() {
        return workshopName;
    }

    public void setWorkshopName(String workshopName) {
        this.workshopName = workshopName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getWorkshopURL() {
        return workshopURL;
    }

    public void setWorkshopURL(String workshopURL) {
        this.workshopURL = workshopURL;
    }

    public String getWorkshopType() {
        return workshopType;
    }

    public void setWorkshopType(String workshopType) {
        this.workshopType = workshopType;
    }

    public String getCommunityDocURL() {
        return communityDocURL;
    }

    public void setCommunityDocURL(String communityDocURL) {
        this.communityDocURL = communityDocURL;
    }

    public String getPreWorkshopSurveyURL() {
        return preWorkshopSurveyURL;
    }

    public void setPreWorkshopSurveyURL(String preWorkshopSurveyURL) {
        this.preWorkshopSurveyURL = preWorkshopSurveyURL;
    }

    public String getPostWorkshopSurveyURL() {
        return postWorkshopSurveyURL;
    }

    public void setPostWorkshopSurveyURL(String postWorkshopSurveyURL) {
        this.postWorkshopSurveyURL = postWorkshopSurveyURL;
    }

    public String getSurveyResults() {
        return surveyResults;
    }

    public void setSurveyResults(String surveyResults) {
        this.surveyResults = surveyResults;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getHostInstitution() {
        return hostInstitution;
    }

    public void setHostInstitution(String hostInstitution) {
        this.hostInstitution = hostInstitution;
    }

    public String getWorkshopWebsite() {
        return workshopWebsite;
    }

    public void setWorkshopWebsite(String workshopWebsite) {
        this.workshopWebsite = workshopWebsite;
    }

    public String getEmailRemindDate() {
        return emailRemindDate;
    }

    public void setEmailRemindDate(String emailRemindDate) {
        this.emailRemindDate = emailRemindDate;
    }

    public String getPostWorkshopSurveyRemindDate() {
        return postWorkshopSurveyRemindDate;
    }

    public void setPostWorkshopSurveyRemindDate(String postWorkshopSurveyRemindDate) {
        this.postWorkshopSurveyRemindDate = postWorkshopSurveyRemindDate;
    }

    public static String[] getColumnNames() {
        return columnNames;
    }

    public static int getColumnCount() {
        return columnNames.length;
    }

    public Workshop() {
    }
    /**
     String workshopID, String workshopName, String startDate, String endDate,
     String startTime, String endTime, String communityDocURL, String preWorkshopSurveyURL,
     String postWorkshopSurveyURL, String surveyResults, String host, String hostInstitution,
     String workshopWebsite, String emailRemindDate, String postWorkshopSurveyRemindDate
     **/

    public Workshop(String workshopID, String workshopName, String startDate, String endDate,
                    String startTime, String endTime, String communityDocURL, String preWorkshopSurveyURL,
                    String postWorkshopSurveyURL, String surveyResults, String host, String hostInstitution,
                    String workshopWebsite, String emailRemindDate, String postWorkshopSurveyRemindDate) {
        this.workshopID = workshopID;
        this.workshopName = workshopName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.communityDocURL = communityDocURL;
        this.preWorkshopSurveyURL = preWorkshopSurveyURL;
        this.postWorkshopSurveyURL = postWorkshopSurveyURL;
        this.surveyResults = surveyResults;
        this.host = host;
        this.hostInstitution = hostInstitution;
        this.workshopWebsite = workshopWebsite;
        this.emailRemindDate = emailRemindDate;
        this.postWorkshopSurveyRemindDate = postWorkshopSurveyRemindDate;
        this.print = false;
    }

    public boolean isPrint() {
        return print;
    }

    public void setPrint(boolean print) {
        this.print = print;
    }
}
