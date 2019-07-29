package it.uniroma3.events;

public class ConsumerDisapprovedEvent extends BaseEvent<String> {
    private String ticketId;

    public ConsumerDisapprovedEvent(String id, String ticketId) {
        super(id);
        this.ticketId = ticketId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

}
