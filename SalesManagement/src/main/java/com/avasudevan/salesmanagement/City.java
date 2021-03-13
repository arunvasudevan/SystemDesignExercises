package com.avasudevan.salesmanagement;

import java.util.List;
import java.util.Map;

public class City {

    private List<Store> stores;

    private Map<Item, Double> prices;

    private String id;

    public City(List<Store> stores, Map<Item, Double> prices, String id) {
        this.stores = stores;
        this.prices = prices;
        this.id = id;

        stores.forEach(st -> {
            st.setPriceMap(prices);
        });
    }

    public List<Store> getStores() {
        return stores;
    }

    public String getId() {
        return id;
    }
}
