package com.gtappdevelopers.googlemapsroutes.RecyclerView;

public class LanguageRVModal {
    private String lngName;
    private int lngImg;
    private String lngLevel;
    private String lngPlatForm;
    private String lngUrl;

    public String getLngUrl() {
        return lngUrl;
    }

    public void setLngUrl(String lngUrl) {
        this.lngUrl = lngUrl;
    }

    public String getLngName() {
        return lngName;
    }

    public void setLngName(String lngName) {
        this.lngName = lngName;
    }

    public int getLngImg() {
        return lngImg;
    }

    public void setLngImg(int lngImg) {
        this.lngImg = lngImg;
    }

    public String getLngLevel() {
        return lngLevel;
    }

    public void setLngLevel(String lngLevel) {
        this.lngLevel = lngLevel;
    }

    public String getLngPlatForm() {
        return lngPlatForm;
    }

    public void setLngPlatForm(String lngPlatForm) {
        this.lngPlatForm = lngPlatForm;
    }

    public LanguageRVModal(String lngName, int lngImg, String lngLevel, String lngPlatForm, String lngUrl) {
        this.lngName = lngName;
        this.lngImg = lngImg;
        this.lngLevel = lngLevel;
        this.lngPlatForm = lngPlatForm;
        this.lngUrl = lngUrl;
    }
}
