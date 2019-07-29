package it.uniroma3.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import java.util.Objects;

@Entity
@NamedQuery(name="TicketSummary.findAllTickets", query="SELECT c FROM TicketSummary c")
public class TicketSummary {
    @Id
    private String id;
    private String orderId;
    private TicketState state;
    private String restaurantId;
    private String consumerId;

    public TicketSummary() {
    }

    public TicketSummary(String id, String orderId, TicketState state, String restaurantId, String consumerId) {
        this.id = id;
        this.orderId = orderId;
        this.state = state;
        this.restaurantId = restaurantId;
        this.consumerId = consumerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public TicketState getState() {
        return state;
    }

    public void setState(TicketState state) {
        this.state = state;
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
}
