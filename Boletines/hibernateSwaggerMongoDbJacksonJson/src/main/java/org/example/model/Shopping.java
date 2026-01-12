package org.example.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "shopping_lists")
public class Shopping {
    @Id
    private String mongoId; // ID interno de MongoDB

    @JsonProperty("shopping_list")
    private ShoppingList shoppingList;

    // Getters y Setters
    public ShoppingList getShoppingList() { return shoppingList; }
    public void setShoppingList(ShoppingList shoppingList) { this.shoppingList = shoppingList; }
}