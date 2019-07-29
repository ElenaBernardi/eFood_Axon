package it.uniroma3.domain;

import it.uniroma3.OrderState;
import it.uniroma3.commands.NewOrderCommand;
import it.uniroma3.commands.OrderInvalidCommand;
import it.uniroma3.commands.TicketCreatedConsumerApprovedCommand;
import it.uniroma3.events.NewOrderEvent;
import it.uniroma3.events.ConsumerApprovedEvent;
import it.uniroma3.events.OrderInvalidEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.List;
import java.util.stream.Collectors;

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
        List<LineItem> lineItems =
                cmd.getLineItems()
                        .stream()
                        .map(x -> new LineItem(x.getLineItemId(),
                                x.getMenuItemId(),
                                x.getQuantity()))
                        .collect(Collectors.toList());
        apply(new NewOrderEvent(cmd.getId(),
                cmd.getConsumerId(),
                cmd.getRestaurantId(),
                cmd.getState(),
                lineItems));
    }

    @CommandHandler
    public void on(TicketCreatedConsumerApprovedCommand cmd){
        apply(new ConsumerApprovedEvent(cmd.getId(),cmd.getTicketId()));
    }
    @CommandHandler
    public void on(OrderInvalidCommand cmd){
        apply(new OrderInvalidEvent(cmd.getId(), cmd.getTicketId()));
    }

    @EventSourcingHandler
    @SagaEventHandler(associationProperty = "id", keyName = "orderId")
    public void on(OrderInvalidEvent evt){
        this.orderState = OrderState.INVALID;
        this.ticketId = null;
    }

    @EventSourcingHandler
    public void on(NewOrderEvent evt){
        this.id = evt.getId();
        this.restaurantId = evt.getRestaurantId();
        this.consumerId = evt.getConsumerId();
        this.orderState = evt.getState();
        List<OrderLineItem> lineItems =
                evt.getLineItems()
                        .stream()
                        .map(x -> new OrderLineItem(x.getLineItemId(),
                                x.getMenuItemId(),
                                x.getQuantity()))
                        .collect(Collectors.toList());
        this.orderLineItems = lineItems;
    }
    @EventSourcingHandler
    @SagaEventHandler(associationProperty = "id", keyName = "orderId")
    @EndSaga
    public void on(ConsumerApprovedEvent evt){
        System.out.println("############# approvato ############");
        this.ticketId = evt.getTicketId();
        this.orderState = OrderState.TICKET_CREATED_AND_CONSUMER_APPROVED;
    }

}
