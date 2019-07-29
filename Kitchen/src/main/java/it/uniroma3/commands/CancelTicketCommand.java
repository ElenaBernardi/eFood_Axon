package it.uniroma3.commands;

public class CancelTicketCommand extends BaseCommand<String> {
    private String orderId;

    public CancelTicketCommand(String ticketId, String orderId){
        super(ticketId);
        this.orderId = orderId;
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
