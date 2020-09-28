package com.sossolution.serviceonway.Class;

public class ListItem {

    String text;
    String image;

    public ListItem(String text, String image)
    {
        this.text = text;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


}
