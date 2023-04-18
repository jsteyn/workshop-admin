package com.jannetta.carpentriesadmin.model;

public class Bookings {
    String BookingReference;
    String BookingDate;
    String WorkshopID;
    String PersonID;
    String Attended;

    public String getBookingReference() {
        return BookingReference;
    }

    public void setBookingReference(String bookingReference) {
        BookingReference = bookingReference;
    }

    public String getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(String bookingDate) {
        BookingDate = bookingDate;
    }

    public String getWorkshopID() {
        return WorkshopID;
    }

    public void setWorkshopID(String workshopID) {
        WorkshopID = workshopID;
    }

    public String getPersonID() {
        return PersonID;
    }

    public void setPersonID(String personID) {
        PersonID = personID;
    }

    public String getAttended() {
        return Attended;
    }

    public void setAttended(String attended) {
        Attended = attended;
    }

    public Bookings(String bookingReference, String bookingDate, String workshopID, String lessonID, String personID, String attended) {
        BookingReference = bookingReference;
        BookingDate = bookingDate;
        WorkshopID = workshopID;
        PersonID = personID;
        Attended = attended;
    }

    public Bookings() {
    }
}
