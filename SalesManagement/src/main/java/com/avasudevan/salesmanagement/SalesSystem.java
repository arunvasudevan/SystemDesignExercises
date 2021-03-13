package com.avasudevan.salesmanagement;

import java.util.List;

public class SalesSystem {

    private List<State> states;

    public SalesSystem(List<State> states) {
        this.states = states;
    }

    public void purchase(String itemName, String state, String city, String store, int qty) {
        states.stream().filter(o -> o.getName().equalsIgnoreCase(state)).findFirst()
            .ifPresent(st -> st.getCities().stream().filter(s -> s.getId().equalsIgnoreCase(city)).findFirst()
                .ifPresent(c -> c.getStores().stream().filter(ci -> ci.getId().equalsIgnoreCase(store)).findFirst()
                    .ifPresent(sto -> sto.purchaseItem(itemName, qty))
                ));
    }
}
