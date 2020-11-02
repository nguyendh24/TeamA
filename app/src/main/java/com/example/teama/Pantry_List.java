package com.example.teama;

public class Pantry_List {
    private String item;
    private int quantity;

    public Pantry_List() {
        quantity = 1;
    }

    public Pantry_List(String item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Getters/Setters
     */
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("%-25s %s%d", getItem(),"x ", getQuantity());
    }
}
