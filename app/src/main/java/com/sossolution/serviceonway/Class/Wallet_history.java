package com.sossolution.serviceonway.Class;

public class Wallet_history
{
    String transation_id;
    String date;
    String amount;

    public Wallet_history()
    {

    }


    public String getTransation_id()
    {
        return transation_id;
    }

    public void setTransation_id(String transation_id)
    {
        this.transation_id = transation_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
