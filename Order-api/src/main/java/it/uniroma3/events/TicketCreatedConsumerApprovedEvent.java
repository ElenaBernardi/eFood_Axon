package it.uniroma3.events;

import it.uniroma3.OrderState;
import org.hibernate.criterion.Order;

public class TicketCreatedConsumerApprovedEvent extends BaseEvent<String> {
    private String ticketId;
    private OrderState orderState;

    public TicketCreatedConsumerApprovedEvent(String id, String ticketId, OrderState orderState) {
        super(id);
        this.ticketId = ticketId;
        this.orderState = orderState;
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
}
