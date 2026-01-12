package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ShoppingList {

    private List<Item> groceries;

    @JsonProperty("personal_care")
    private List<Item>personalCare;

    @JsonProperty("household_items")
    private List<Item> householdItems;

    public List<Item> getGroceries() {
        return groceries;
    }

    public void setGroceries(List<Item> groceries) {
        this.groceries = groceries;
    }

    public List<Item> getHouseholdItems() {
        return householdItems;
    }

    public void setHouseholdItems(List<Item> householdItems) {
        this.householdItems = householdItems;
    }

    public List<Item> getPersonalCare() {
        return personalCare;
    }

    public void setPersonalCare(List<Item> personalCare) {
        this.personalCare = personalCare;
    }
}
