package it.uniroma3.commands;

import it.uniroma3.domain.TicketState;

public class NewTicketCommand extends BaseCommand<String> {
    private String orderId;
    private String restaurantId;
    private TicketState state;

    public NewTicketCommand(String id, String orderId, String restaurantId){
        super(id);
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.state = TicketState.PENDING;
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

    public TicketState getState() {
        return state;
    }

    public void setState(TicketState state) {
        this.state = state;
    }
}
