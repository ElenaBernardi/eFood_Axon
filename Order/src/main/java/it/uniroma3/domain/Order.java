package it.uniroma3.domain;

import it.uniroma3.commands.NewOrderCommand;
import it.uniroma3.events.NewOrderEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
public class Order {
    @AggregateIdentifier
    private String id;

    private String restaurantId;
    private String consumerId;
    private String ticketId;

    private OrderState orderState;
    @AggregateMember
    private List<OrderLineItem> orderLineItems;

    public Order() {}

    @CommandHandler
    public Order(NewOrderCommand cmd){
        apply(new NewOrderEvent(cmd.getId(),cmd.getConsumerId(),cmd.getRestaurantId(),cmd.getTicketId(),cmd.getState(),cmd.getLineItems()));
    }
    @EventSourcingHandler
    public void on(NewOrderEvent evt){
        this.id = evt.getId();
        this.restaurantId = evt.getRestaurantId();
        this.consumerId = evt.getConsumerId();
        this.ticketId = evt.getTicketId();
        this.orderState = evt.getState();
        this.orderLineItems = evt.getLineItems();
    }

}
