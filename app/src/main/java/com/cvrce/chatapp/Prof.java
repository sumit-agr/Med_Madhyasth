package com.cvrce.chatapp;

public class Prof {
    public String userName;
    public String email;
    public String bloodGroup;
    public String phone;

    public Prof()
    {

    }



    public Prof(String userName, String email, String bloodGroup, String phone) {
        this.userName = userName;
        this.email = email;
        this.bloodGroup = bloodGroup;
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
