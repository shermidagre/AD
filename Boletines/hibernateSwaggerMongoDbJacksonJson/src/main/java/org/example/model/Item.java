package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {

    private int id;

    @JsonProperty("item")
    private String item;

    @JsonProperty("unit")
    private String unit;

    @JsonProperty("quantity")
    private int quantity;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getItems() {
        return item;
    }

    public void setItems(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
