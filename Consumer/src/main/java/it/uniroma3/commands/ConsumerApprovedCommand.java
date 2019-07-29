package it.uniroma3.commands;

public class ConsumerApprovedCommand extends BaseCommand1<String> {
    private String orderId;
    private String ticketId;

    public ConsumerApprovedCommand(String id, String ticketId, String orderId) {
        super(id);
        this.ticketId = ticketId;
        this.orderId = orderId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
