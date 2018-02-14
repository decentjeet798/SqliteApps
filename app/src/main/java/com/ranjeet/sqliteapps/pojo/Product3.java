package com.ranjeet.sqliteapps.pojo;

/**
 * Created by admin on 12/12/2017.
 */

public class Product3 {
    private int id;
    private String name;
    private int quantity;
    private int price;

    public Product3(String name, int quantity,int price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Product3(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
