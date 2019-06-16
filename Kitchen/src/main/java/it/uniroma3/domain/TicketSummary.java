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

    public TicketSummary() {
    }

    public TicketSummary(String id, String orderId, TicketState state, String restaurantId) {
        this.id = id;
        this.orderId = orderId;
        this.state = state;
        this.restaurantId = restaurantId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketSummary that = (TicketSummary) o;
        return id.equals(that.id) &&
                orderId.equals(that.orderId) &&
                state == that.state &&
                restaurantId.equals(that.restaurantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, state, restaurantId);
    }

    @Override
    public String toString() {
        return "TicketSummary{" +
                "id='" + id + '\'' +
                ", orderId='" + orderId + '\'' +
                ", state=" + state +
                ", restaurantId='" + restaurantId + '\'' +
                '}';
    }
}
