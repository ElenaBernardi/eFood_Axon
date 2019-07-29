package it.uniroma3.domain;

import it.uniroma3.OrderState;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.util.List;

@Entity
@NamedQuery(name="OrderSummary.findAllOrders", query="SELECT c " +
                                                     "FROM OrderSummary c " +
                                                        "join fetch c.orderLineItems")

public class OrderSummary {
    @Id
    private String id;
    private String restaurantId;
    private String consumerId;
    private String ticketId;

    private OrderState orderState;
    @ElementCollection
    private List<LineItem> orderLineItems;

    public OrderSummary() {
    }

    public OrderSummary(String id, String restaurantId, String consumerId, OrderState orderState, List<LineItem> orderLineItems) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.consumerId = consumerId;
        this.orderState = orderState;
        this.orderLineItems = orderLineItems;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public List<LineItem> getOrderLineItems() {
        return orderLineItems;
    }

    public void setOrderLineItems(List<LineItem> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }
}
