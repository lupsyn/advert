package com.gumtree.advert.domain.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Copyright (c) 2017.
 * All rights reserved.
 *
 * @author enricodelzotto
 * @since 20/02/2017
 */
public class Advert {

    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("links")
    private List<String> links;
    @SerializedName("price")
    private String price;
    @SerializedName("currency")
    private String currency;
    @SerializedName("location")
    private String location;
    @SerializedName("date")
    private String date;
    @SerializedName("make")
    private String make;
    @SerializedName("transmission")
    private String transmission;
    @SerializedName("model")
    private String model;
    @SerializedName("year")
    private String year;
    @SerializedName("colour")
    private String colour;
    @SerializedName("enginesize")
    private String enginesize;
    @SerializedName("bodytype")
    private String bodytype;
    @SerializedName("sellertype")
    private String sellertype;
    @SerializedName("mileage")
    private String mileage;
    @SerializedName("fueltype")
    private String fueltype;
    @SerializedName("longdescription")
    private String longdescription;
    @SerializedName("mobilenumber")
    private String mobilenumber;
    @SerializedName("mail")
    private String mail;
    @SerializedName("name")
    private String name;
    @SerializedName("postingfor")
    private String postingfor;

    @SerializedName("lat")
    private Double lat;

    @SerializedName("long")
    private Double lng;


    public Advert() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getEnginesize() {
        return enginesize;
    }

    public void setEnginesize(String enginesize) {
        this.enginesize = enginesize;
    }

    public String getBodytype() {
        return bodytype;
    }

    public void setBodytype(String bodytype) {
        this.bodytype = bodytype;
    }

    public String getSellertype() {
        return sellertype;
    }

    public void setSellertype(String sellertype) {
        this.sellertype = sellertype;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getFueltype() {
        return fueltype;
    }

    public void setFueltype(String fueltype) {
        this.fueltype = fueltype;
    }

    public String getLongdescription() {
        return longdescription;
    }

    public void setLongdescription(String longdescription) {
        this.longdescription = longdescription;
    }

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCurrency() {
        return currency;
    }

    public String getName() {
        return name;
    }

    public String getPostingfor() {
        return postingfor;
    }

    public Double getLng() {
        return lng;
    }

    public Double getLat() {
        return lat;
    }

}
