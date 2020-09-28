package com.sossolution.serviceonway.Class;

public class Service{

    String image;
    String price;
    String text;
    String Id;
    private boolean isSelected;

    public boolean getSelected()
    {
        return isSelected;
    }



    public Service(String image, String price, String text, String Id)
    {
        this.image = image;
        this.price = price;
        this.text = text;
        this.Id=Id;
    }


    //mens getter.........


    public void setSelected(boolean selected)

    {
        isSelected = selected;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getText()
    {
        return String.valueOf(text);
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

}
