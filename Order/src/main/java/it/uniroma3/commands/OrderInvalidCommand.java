package it.uniroma3.commands;

public class OrderInvalidCommand extends BaseCommand1<String> {
    private String ticketId;

    public OrderInvalidCommand(String orderId, String ticketId) {
        super(orderId);
        this.ticketId = ticketId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}
