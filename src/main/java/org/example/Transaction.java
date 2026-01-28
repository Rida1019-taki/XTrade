package org.example;

import java.util.Date;

public class Transaction {
    private Date date;
    private String type;
    private double price;
    private int quantity;

    public Transaction(Date date, String type, double price, int quantity) {
        this.date = date;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
