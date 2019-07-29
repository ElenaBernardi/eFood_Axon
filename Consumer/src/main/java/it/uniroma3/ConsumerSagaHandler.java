package it.uniroma3;

import it.uniroma3.domain.ConsumerSummary;
import it.uniroma3.events.NewTicketEvent;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.spring.stereotype.Saga;

import javax.inject.Inject;
import javax.persistence.EntityManager;

@Saga
public class ConsumerSagaHandler {

    @Inject
    private transient EntityManager entityManager;

    /*@SagaEventHandler(associationProperty = "orderId")
    public void on(NewTicketEvent evt){
        ConsumerSummary consumer = entityManager.find(ConsumerSummary.class, evt.getConsumerId());
        if(consumer!=null){
            consumerService.consumerApproved(evt.getConsumerId(),evt.getOrderId(),evt.getId());
        }
        //else {
        //  throw new ConsumerNotFoundException(evt.getId());

        //consumerService.consumerDisapproved(evt.getConsumerId(),evt.getOrderId(),evt.getId());
        //}
    }*/
}
