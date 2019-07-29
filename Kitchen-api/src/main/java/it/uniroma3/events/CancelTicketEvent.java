package it.uniroma3.events;


public class CancelTicketEvent extends BaseEvent<String> {
    private String orderId;

    public CancelTicketEvent(String ticketId, String orderId){
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
