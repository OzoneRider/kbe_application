package com.kbe.application.models.storageAPI;

public class DeliveryInformation {

    private int productId;
    private Location productLocation;
    private String deliveryTime;
    private int amount;
    private double vat;

    public DeliveryInformation() {
    }

    public DeliveryInformation(int productId, Location productLocation, String deliveryTime, int amount, double vat) {
        this.productId = productId;
        this.productLocation = productLocation;
        this.deliveryTime = deliveryTime;
        this.amount = amount;
        this.vat = vat;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Location getProductLocation() {
        return productLocation;
    }

    public void setProductLocation(Location productLocation) {
        this.productLocation = productLocation;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }
}
