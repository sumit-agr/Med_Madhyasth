package com.cvrce.chatapp;

public class Req {

    public double latitude;
    public double longitude;
    public String bldgrp;

    public Req()
    {

    }

    public Req(double latitude,double longitude,String bldgrp)
    {
        this.latitude = latitude;
        this.longitude = longitude;
        this.bldgrp = bldgrp;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getBldgrp() {
        return bldgrp;
    }

    public void setBldgrp(String bldgrp) {
        this.bldgrp = bldgrp;
    }
}
