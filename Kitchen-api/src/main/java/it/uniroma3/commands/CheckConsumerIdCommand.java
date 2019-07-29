package it.uniroma3.commands;

public class CheckConsumerIdCommand extends BaseCommand1<String> {
    private String orderId;
    private String ticketId;
    public CheckConsumerIdCommand(String consumerId, String orderId, String ticketId){
        super(consumerId);
        this.orderId = orderId;
        this.ticketId = ticketId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }
}
