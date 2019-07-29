package it.uniroma3.queries;

import it.uniroma3.FindAllOrdersQuery;
import it.uniroma3.OrderState;
import it.uniroma3.domain.OrderSummary;
import it.uniroma3.events.NewOrderEvent;
import it.uniroma3.events.ConsumerApprovedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class OrderProjection {
    private final EntityManager entityManager;
    private final QueryUpdateEmitter queryUpdateEmitter;

    public OrderProjection(EntityManager entityManager, QueryUpdateEmitter queryUpdateEmitter) {
        this.entityManager = entityManager;
        this.queryUpdateEmitter = queryUpdateEmitter;
    }

    @EventHandler
    public void on(NewOrderEvent evt){

        OrderSummary ordererSummary = new OrderSummary(evt.getId(),
                evt.getRestaurantId(),
                evt.getConsumerId(),
                evt.getState(),
                evt.getLineItems());
        entityManager.persist(ordererSummary);
        queryUpdateEmitter.emit(FindAllOrdersQuery.class,
                query -> true,
                ordererSummary
        );
    }
    @EventHandler
    public void on(ConsumerApprovedEvent evt){
        OrderSummary order = entityManager.find(OrderSummary.class, evt.getId());
        order.setTicketId(evt.getTicketId());
        order.setOrderState(OrderState.TICKET_CREATED_AND_CONSUMER_APPROVED);
        queryUpdateEmitter.emit(FindAllOrdersQuery.class,
                query -> true,
                order
        );
    }
    @QueryHandler
    public List<OrderSummary> handle(FindAllOrdersQuery query){

        TypedQuery<OrderSummary> jpaQuery =
                entityManager.createNamedQuery("OrderSummary.findAllOrders", OrderSummary.class);
        return jpaQuery.getResultList();

    }
}
