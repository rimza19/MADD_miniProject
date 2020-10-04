package com.e.fooddiet.entities;

public class Account {

    private int id ;
    private String email ;
    private String dob ;
    private String gender ;
    private String status ;
    private String height ;
    private String weight ;
    private String GWeight ;
    private String welcome ;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setWelcome(String welcome){
        this.welcome = welcome ;
    }

    public String getWelcome(){
        return welcome ;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getGoal_weight() {
        return GWeight;
    }

    public void setGoal_weight(String GWeight) {
        this.GWeight = GWeight;
    }
}
