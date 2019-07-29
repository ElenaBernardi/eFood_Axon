package it.uniroma3.commands;

import it.uniroma3.OrderState;

public class TicketCreatedConsumerApprovedCommand extends BaseCommand1<String> {
    private String ticketId;
    private OrderState state;

    public TicketCreatedConsumerApprovedCommand(String id, String ticketId) {
        super(id);
        this.ticketId = ticketId;
        this.state = OrderState.TICKET_CREATED_AND_CONSUMER_APPROVED;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }
}
