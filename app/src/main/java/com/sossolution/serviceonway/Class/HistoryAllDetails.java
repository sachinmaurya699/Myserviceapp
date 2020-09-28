package com.sossolution.serviceonway.Class;

public class HistoryAllDetails{

    String Date;
    String Booking_Id;
    String Vechicle;
    String IncludeService;
    String NotIncludeService;
    String maker;
    String Model;

    public HistoryAllDetails() {


    }

    public HistoryAllDetails(String date, String booking_Id, String vechicle, String includeService, String notIncludeService, String maker, String model)
    {
       this.Date = date;
       this.Booking_Id = booking_Id;
       this.Vechicle = vechicle;
       this.IncludeService = includeService;
        this.NotIncludeService = notIncludeService;
        this.maker = maker;
        this.Model = model;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getBooking_Id() {
        return Booking_Id;
    }

    public void setBooking_Id(String booking_Id) {
        Booking_Id = booking_Id;
    }

    public String getVechicle() {
        return Vechicle;
    }

    public void setVechicle(String vechicle) {
        Vechicle = vechicle;
    }

    public String getIncludeService() {
        return IncludeService;
    }

    public void setIncludeService(String includeService) {
        IncludeService = includeService;
    }

    public String getNotIncludeService() {
        return NotIncludeService;
    }

    public void setNotIncludeService(String notIncludeService) {
        NotIncludeService = notIncludeService;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }
}
