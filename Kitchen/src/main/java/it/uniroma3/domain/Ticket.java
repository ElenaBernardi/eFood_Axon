package it.uniroma3.domain;

import it.uniroma3.commands.CancelTicketCommand;
import it.uniroma3.commands.NewTicketCommand;
import it.uniroma3.events.CancelTicketEvent;
import it.uniroma3.events.NewTicketEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Ticket {
    @AggregateIdentifier
    private String id;
    private String orderId;
    private TicketState state;
    private String restaurantId;
    private String consumerId;

    public Ticket(){}

    @CommandHandler
    public Ticket(NewTicketCommand cmd){
        apply(new NewTicketEvent(cmd.getId(), cmd.getOrderId(), cmd.getRestaurantId(), cmd.getState(), cmd.getConsumerId()));
        System.out.println("############## NewTicketEvent ############");
    }
    @CommandHandler
    public void on(CancelTicketCommand cmd){
        apply(new CancelTicketEvent(cmd.getId(), cmd.getOrderId()));
    }
    @EventSourcingHandler
    public void on(NewTicketEvent evt){
        this.id = evt.getId();
        this.orderId = evt.getOrderId();
        this.state = evt.getTicketState();
        this.restaurantId = evt.getRestaurantId();
        this.consumerId = evt.getConsumerId();
        System.out.println("############## NewTicketEvent handler ###########");

    }


}
