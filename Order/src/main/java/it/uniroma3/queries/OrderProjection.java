package it.uniroma3.queries;

import it.uniroma3.domain.LineItem;
import it.uniroma3.domain.OrderSummary;
import it.uniroma3.events.NewOrderEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

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
        List<LineItem> lineItems =
                evt.getLineItems()
                        .stream()
                        .map(x -> new LineItem(x.getLineItemId(),x.getMenuItemId(), x.getQuantity()))
                        .collect(Collectors.toList());
        OrderSummary ordererSummary = new OrderSummary(evt.getId(), evt.getRestaurantId(), evt.getConsumerId(), evt.getTicketId(), evt.getState(), lineItems);
        entityManager.persist(ordererSummary);
        queryUpdateEmitter.emit(FindAllOrdersQuery.class,
                query -> true,
                ordererSummary
        );
    }
    @QueryHandler
    public List<OrderSummary> handle(FindAllOrdersQuery query){

        TypedQuery<OrderSummary> jpaQuery =
                entityManager.createNamedQuery("OrderSummary.findAllOrders", OrderSummary.class);
        return jpaQuery.getResultList();

    }
}
