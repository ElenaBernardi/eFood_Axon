package it.uniroma3.queries;

import it.uniroma3.domain.ConsumerSummary;
import it.uniroma3.events.NewConsumerEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class ConsumerProjection {
    private final EntityManager entityManager;
    private final QueryUpdateEmitter queryUpdateEmitter;

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
                consumerSummary
        );
    }
    @QueryHandler
    public List<ConsumerSummary> handle(FindAllConsumersQuery query){

        TypedQuery<ConsumerSummary> jpaQuery =
                entityManager.createNamedQuery("ConsumerSummary.findAllConsumers", ConsumerSummary.class);
        return jpaQuery.getResultList();

    }
}
