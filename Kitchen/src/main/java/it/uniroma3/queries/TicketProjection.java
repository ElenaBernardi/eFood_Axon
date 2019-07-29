package it.uniroma3.queries;

import it.uniroma3.domain.IKitchenService;
import it.uniroma3.domain.Ticket;
import it.uniroma3.domain.TicketSummary;
import it.uniroma3.events.NewTicketEvent;
import it.uniroma3.events.OrderInvalidEvent;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class TicketProjection {
    private final EntityManager entityManager;
    private final QueryUpdateEmitter queryUpdateEmitter;
    @Autowired
    private IKitchenService kitchenService;

    public TicketProjection(EntityManager entityManager, QueryUpdateEmitter queryUpdateEmitter) {
        this.entityManager = entityManager;
        this.queryUpdateEmitter = queryUpdateEmitter;
    }

  /*  @EventHandler
    public void on(NewTicketEvent evt) {

        TicketSummary ticketSummary = new TicketSummary(evt.getId(), evt.getOrderId(), evt.getTicketState(), evt.getRestaurantId(), evt.getConsumerId());
        entityManager.persist(ticketSummary);
        queryUpdateEmitter.emit(FindAllTicketsQuery.class,
                query -> true,
                ticketSummary);
    }*/
    @QueryHandler
    public List<TicketSummary> handle(FindAllTicketsQuery query){

        TypedQuery<TicketSummary> jpaQuery =
                entityManager.createNamedQuery("TicketSummary.findAllTickets", TicketSummary.class);
        return jpaQuery.getResultList();

    }
    public void on(OrderInvalidEvent evt){
        entityManager.remove(evt.getTicketId());
    }


}
