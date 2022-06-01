package com.gtappdevelopers.googlemapsroutes.Retrofit;

public class DataModal {
    private String id;
    private String courseName;

    public DataModal(String courseId, String courseName, String courseImg, String courseDuration, String coursePrice, String courseLink) {
        this.id = courseId;
        this.courseName = courseName;
        this.courseImg = courseImg;
        this.courseDuration = courseDuration;
        this.coursePrice = coursePrice;
        this.courseLink = courseLink;
    }

    private String courseImg;
    private String courseDuration;
    private String coursePrice;
    private String courseLink;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseImg() {
        return courseImg;
    }

    public void setCourseImg(String courseImg) {
        this.courseImg = courseImg;
    }

    public String getCourseDuration() {
        return courseDuration;
    }

    public void setCourseDuration(String courseDuration) {
        this.courseDuration = courseDuration;
    }

    public String getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(String coursePrice) {
        this.coursePrice = coursePrice;
    }

    public String getCourseLink() {
        return courseLink;
    }

    public void setCourseLink(String courseLink) {
        this.courseLink = courseLink;
    }


    public String getCourseId() {
        return id;
    }

    public void setCourseId(String courseId) {
        this.id = courseId;
    }
}
