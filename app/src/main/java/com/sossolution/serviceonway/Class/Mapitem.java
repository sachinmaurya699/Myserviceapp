package com.sossolution.serviceonway.Class;

public class Mapitem{

    double latitude;
    double longitude;
    String shopname;
    String Id;
    String address;
    String Image;

    public Mapitem()
    {
        this.latitude = latitude;
    }

    public String getId()
    {
        return Id;
    }

    public void setId(String id)
    {
        Id = id;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getImage(String image5)
    {
        return Image;
    }

    public void setImage(String image)
    {
        Image = image;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public String getShopname()
    {
        return shopname;
    }

    public void setShopname(String shopname)
    {
        this.shopname = shopname;
    }

}
