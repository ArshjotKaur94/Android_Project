package com.example.project_android;

import io.realm.RealmObject;

public class GroceryModel extends RealmObject {

    String name;
    String price;
    int image;

    public GroceryModel() {
    }

    public GroceryModel(String name, String price, int image) {
        this.name = name;
        this.price = price;
        this.image = image;
    }
}
