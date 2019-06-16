package it.uniroma3.events;

import it.uniroma3.commands.BaseCommand;
import it.uniroma3.domain.TicketState;

public class NewTicketEvent extends BaseEvent<String> {
    private String orderId;
    private String restaurantId;
    private TicketState ticketState;

    public NewTicketEvent(String id, String orderId, String restaurantId, TicketState ticketState) {
        super(id);
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.ticketState = ticketState;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public TicketState getTicketState() {
        return ticketState;
    }

    public void setTicketState(TicketState ticketState) {
        this.ticketState = ticketState;
    }
}
