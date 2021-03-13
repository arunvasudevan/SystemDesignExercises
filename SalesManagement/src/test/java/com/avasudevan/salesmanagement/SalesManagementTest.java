package com.avasudevan.salesmanagement;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class SalesManagementTest {

    SalesSystem system;
    State state;
    City city;
    Store store;

    @Before
    public void init() {

        Item sandwichItem = new Item("Sandwich", ITEM_TYPE.SNACKS);
        Item WaterItem = new Item("Water", ITEM_TYPE.BEVERAGE);
        Map<Item, Double> map= new HashMap<>();
        map.put(sandwichItem, 2.0);
        map.put(WaterItem, 1.0);

        Map<String, Integer> qtyMap= new HashMap<>();
        qtyMap.put("Sandwich", 20);
        qtyMap.put("Water", 5);
        store = new Store(qtyMap, "NW");
        city = new City(Collections.singletonList(store), map, "Austin");
        state = new State(Collections.singletonList(city), "TX");

        system = new SalesSystem(Collections.singletonList(state));
    }

    @Test
    public void getTotalWaterSoldInTexas() {
        system.purchase("Water", state.getName(), city.getId(), store.getId(), 2);

        System.out.println("Water Sold in NW Store in Austin, TX:"+store.getSoldQtyPerItem("Water"));
        System.out.println("Total Water Available in NW Store in Austin, TX:"+ (store.getTotalQty("Water") - store.getSoldQtyPerItem("Water")));

        system.purchase("Water", state.getName(), city.getId(), store.getId(), 4);
    }
}
