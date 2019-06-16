package it.uniroma3.queries;

import it.uniroma3.domain.Consumer;
import it.uniroma3.domain.ConsumerSummary;
import it.uniroma3.events.NewConsumerEvent;
import lombok.extern.slf4j.XSlf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.beans.factory.annotation.Autowired;
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
        queryUpdateEmitter.emit(FindAllQuery.class,
                query -> true,
                consumerSummary
        );
    }
    @QueryHandler
    public List<ConsumerSummary> handle(FindAllQuery query){
        System.out.println("############################# Prima del jpaQuery");

        TypedQuery<ConsumerSummary> jpaQuery =
                entityManager.createNamedQuery("ConsumerSummary.findAll", ConsumerSummary.class);
        System.out.println("#############################");
        System.out.println(jpaQuery.getResultList());
        return jpaQuery.getResultList();

    }
}
