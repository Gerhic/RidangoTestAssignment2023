package com.sixtenleemets.ridangotestassignment2023.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "tickets")
public class TicketEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String productName;
    private int priceCents;
    private long dateMillis;

    public TicketEntity(String productName, int priceCents) {
        this.productName = productName;
        this.priceCents = priceCents;
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

    public int getPrice() {
        return priceCents;
    }

    public void setPrice(int priceCents) {
        this.priceCents = priceCents;
    }

    public long getDateMillis() {
        return dateMillis;
    }

    public void setDateMillis(long dateMillis) {
        this.dateMillis = dateMillis;
    }
}

