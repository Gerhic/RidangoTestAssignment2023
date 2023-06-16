package com.sixtenleemets.ridangotestassignment2023.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "tickets")
public class TicketEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String productName;
    private float price;
    private long dateMillis;

    public TicketEntity(String productName, float price) {
        this.productName = productName;
        this.price = price;
        this.dateMillis = new Date().getTime();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getDateMillis() {
        return dateMillis;
    }

    public void setDateMillis(long dateMillis) {
        this.dateMillis = dateMillis;
    }
}
