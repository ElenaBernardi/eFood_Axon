package it.uniroma3.web;

import it.uniroma3.domain.OrderLineItem;

import java.util.List;

public class NewOrderDTO {
    private String consumerId;
    private String restaurantId;
    private List<OrderLineItem> lineItems;

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<OrderLineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<OrderLineItem> lineItems) {
        this.lineItems = lineItems;
    }
}
