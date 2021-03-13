package com.avasudevan.salesmanagement;

public class Item {

    private String name;

    private ITEM_TYPE type;

    public Item(String name, ITEM_TYPE type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }
}
