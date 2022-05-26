package com.gtappdevelopers.googlemapsroutes.CustomSpinner;

public class SpinnerModal {
    private int imgID;
    private String name;

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SpinnerModal(int imgID, String name) {
        this.imgID = imgID;
        this.name = name;
    }
}
