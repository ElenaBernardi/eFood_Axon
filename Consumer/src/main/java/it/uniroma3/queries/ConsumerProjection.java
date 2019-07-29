package it.uniroma3.queries;

import it.uniroma3.FindAllOrdersQuery;
import it.uniroma3.domain.ConsumerService;
import it.uniroma3.domain.ConsumerSummary;
import it.uniroma3.domain.IConsumerService;
import it.uniroma3.domain.OrderSummary;
import it.uniroma3.events.NewConsumerEvent;
import it.uniroma3.events.NewOrderEvent;
import it.uniroma3.events.NewTicketEvent;
import it.uniroma3.exception.ConsumerNotFoundException;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class ConsumerProjection {
    private final EntityManager entityManager;
    private final QueryUpdateEmitter queryUpdateEmitter;
    @Autowired
    private IConsumerService consumerService;

    public ConsumerProjection(EntityManager entityManager, QueryUpdateEmitter queryUpdateEmitter) {
        this.entityManager = entityManager;
        this.queryUpdateEmitter = queryUpdateEmitter;
    }

    @EventHandler
    public void on(NewConsumerEvent evt){

        ConsumerSummary consumerSummary = new ConsumerSummary(evt.getId(), evt.getNome(), evt.getCognome());
        entityManager.persist(consumerSummary);
        queryUpdateEmitter.emit(FindAllConsumersQuery.class,
                query -> true,
                consumerSummary);

        queryUpdateEmitter.emit(FindAllOrdersByConsumerId.class,
                query -> query.getConsumerId().equals(evt.getId()),
                consumerSummary);

    }
    @EventHandler
    public void on(NewOrderEvent evt){
        OrderSummary ordererSummary = new OrderSummary(evt.getId(), evt.getRestaurantId(), evt.getConsumerId(), evt.getState(), evt.getLineItems());
        entityManager.persist(ordererSummary);
        queryUpdateEmitter.emit(FindAllOrdersByConsumerId.class,
                query -> query.getConsumerId().equals(evt.getConsumerId()),
                ordererSummary
        );
    }

    @QueryHandler
    public List<ConsumerSummary> handle(FindAllConsumersQuery query){

        TypedQuery<ConsumerSummary> jpaQuery =
                entityManager.createNamedQuery("ConsumerSummary.findAllConsumers", ConsumerSummary.class);
        return jpaQuery.getResultList();

    }
    @QueryHandler
    public List<Object[]> handle(FindAllOrdersByConsumerId query){
        TypedQuery<Object[]> jpaQuery =
                entityManager.createNamedQuery("ConsumerSummary.findAllOrdersByConsumerId", Object[].class);//.setParameter("consumerId",query.getConsumerId());

        return jpaQuery.getResultList();

    }
}
