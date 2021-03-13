package com.avasudevan.salesmanagement;

import java.util.List;

public class State {

    private List<City> cities;

    private String name;

    public State(List<City> cities, String name) {
        this.cities = cities;
        this.name = name;
    }

    public List<City> getCities() {
        return cities;
    }

    public String getName() {
        return name;
    }
}
