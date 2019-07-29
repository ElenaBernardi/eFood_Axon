package it.uniroma3.events;


public class ConsumerApprovedEvent extends BaseEvent<String> {
    private String ticketId;


    public ConsumerApprovedEvent(String id, String ticketId) {
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
