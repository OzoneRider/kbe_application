package com.kbe.application.models.storageAPI;

public class Location {
    private String country;
    private String city;
    private String street;
    private String homeNr;
    private String postalCode;

    public Location(){}

    public Location(String country, String city, String street, String homeNr, String postalCode) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.homeNr = homeNr;
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHomeNr() {
        return homeNr;
    }

    public void setHomeNr(String homeNr) {
        this.homeNr = homeNr;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
