package it.uniroma3.commands;

import it.uniroma3.OrderState;
import it.uniroma3.domain.OrderLineItem;

import java.util.List;

public class NewOrderCommand extends BaseCommand1<String> {
    private String consumerId;
    private String restaurantId;
    private List<OrderLineItem> lineItems;
    private OrderState state;

    public NewOrderCommand(String id, String consumerId, String restaurantId, List<OrderLineItem> lineItems) {
        super(id);
        this.consumerId = consumerId;
        this.restaurantId = restaurantId;
        this.lineItems = lineItems;
        this.state = OrderState.PENDING;
    }

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

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }
}
