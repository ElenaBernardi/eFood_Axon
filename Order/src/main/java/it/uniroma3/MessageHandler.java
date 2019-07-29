package it.uniroma3;

import it.uniroma3.domain.IOrderService;
import it.uniroma3.domain.OrderService;
import it.uniroma3.events.ConsumerApprovedEvent;
import it.uniroma3.events.ConsumerDisapprovedEvent;
import it.uniroma3.events.NewTicketEvent;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Saga
public class MessageHandler {

    @Autowired
    private IOrderService orderService;


    @SagaEventHandler(associationProperty = "id", keyName = "orderId")
    public void on(ConsumerDisapprovedEvent evt){
        this.orderService.orderInvalid(evt.getId(), null);
    }
}
