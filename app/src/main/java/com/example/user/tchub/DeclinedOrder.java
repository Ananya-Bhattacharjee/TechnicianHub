package com.example.user.tchub;

/**
 * Created by User on 5/28/2017.
 */

public class DeclinedOrder {
    String id;
    Tech tech;
    Order order;

    public DeclinedOrder() {
    }

    public DeclinedOrder(String id, Tech tech, Order order) {
        this.id = id;
        this.tech = tech;
        this.order = order;
    }

    public String getId() {
        return id;
    }

    public Tech getTech() {
        return tech;
    }

    public Order getOrder() {
        return order;
    }
}
