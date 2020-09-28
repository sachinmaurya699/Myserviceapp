package com.sossolution.serviceonway.Class;

public class Historyitem{

    String date;
    String address;
    String amount;
    String Id;
    String include;
    String notinclude;
    String buttonname;

    public String getButtonname() {
        return buttonname;
    }

    public void setButtonname(String buttonname) {
        this.buttonname = buttonname;
    }

    public String getNotinclude()
    {
        return notinclude;
    }

    public void setNotinclude(String notinclude)
    {
        this.notinclude = notinclude;
    }




    public String getInclude()
    {
        return include;
    }

    public void setInclude(String include) {
        this.include = include;
    }



    public Historyitem()
    {

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
