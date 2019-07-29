package it.uniroma3.events;
import it.uniroma3.domain.LineItem;
import it.uniroma3.OrderState;

import java.util.List;

public class NewOrderEvent extends BaseEvent<String> {
    private String consumerId;
    private String restaurantId;
    private List<LineItem> lineItems;
    private OrderState state;

    public NewOrderEvent(String id, String consumerId, String restaurantId, OrderState state, List<LineItem> lineItems) {
        super(id);
        this.consumerId = consumerId;
        this.restaurantId = restaurantId;
        this.lineItems = lineItems;
        this.state = state;
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

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }
}
