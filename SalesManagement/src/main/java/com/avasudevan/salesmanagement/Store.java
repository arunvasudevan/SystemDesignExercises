package com.avasudevan.salesmanagement;

import java.util.HashMap;
import java.util.Map;

public class Store {

    private Map<Item, Double> priceMap;

    private Map<String, Integer> soldQtyMap;

    private Map<String, Integer> totalQtyMap;

    private String id;

    public Store(Map<String, Integer> totalQtyMap, String id) {
        this.totalQtyMap = totalQtyMap;
        this.soldQtyMap = new HashMap<>();
        this.id = id;
    }

    public void setPriceMap(Map<Item, Double> priceMap) {
        this.priceMap = priceMap;
    }


    public String getId() {
        return id;
    }


    public void purchaseItem(String itemName, int qty) {

        if (totalQtyMap.getOrDefault(itemName, 0) - soldQtyMap.getOrDefault(itemName, 0) >= qty) {
            soldQtyMap.put(itemName, qty);
        } else {
            System.out.println("Not Enough Qty Available in Store");
        }
    }

    public int getSoldQtyPerItem(String itemName) {
        return soldQtyMap.getOrDefault(itemName, 0);
    }

    public int getTotalQty(String itemName) {
        return totalQtyMap.getOrDefault(itemName, 0);
    }
}
