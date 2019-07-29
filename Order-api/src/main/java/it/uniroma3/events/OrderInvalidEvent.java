package it.uniroma3.events;


public class OrderInvalidEvent extends BaseEvent<String> {
    private String ticketId;

    public OrderInvalidEvent(String orderId, String ticketId) {
        super(orderId);
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}
