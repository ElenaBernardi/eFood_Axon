package it.uniroma3;

import it.uniroma3.commands.CancelTicketCommand;
import it.uniroma3.commands.CheckConsumerIdCommand;
import it.uniroma3.commands.NewTicketCommand;
import it.uniroma3.domain.IKitchenService;
import it.uniroma3.domain.NewTicketDTO;
import it.uniroma3.domain.TicketSummary;
import it.uniroma3.events.*;
import it.uniroma3.queries.FindAllTicketsQuery;
import org.axonframework.commandhandling.CommandCallback;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.CommandResultMessage;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.command.AggregateNotFoundException;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.SagaLifecycle;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


@Saga
public class KitchenSagaHandler {
    @Inject
    private transient CommandGateway commandGateway;
    @Inject
    private transient EntityManager entityManager;
    @Inject
    private transient QueryUpdateEmitter queryUpdateEmitter;

    @StartSaga
    @SagaEventHandler(associationProperty = "id", keyName="orderId")
    public void on(NewOrderEvent evt){
        NewTicketDTO newTicketDTO = new NewTicketDTO();
        newTicketDTO.setOrderId(evt.getId());
        newTicketDTO.setRestaurantId(evt.getRestaurantId());
        newTicketDTO.setConsumerId(evt.getConsumerId());
        NewTicketCommand cmd = new NewTicketCommand(UUID.randomUUID().toString(), newTicketDTO.getOrderId(), newTicketDTO.getRestaurantId(), newTicketDTO.getConsumerId());
        commandGateway.send(cmd);
        System.out.println("############################## inviato command NewTicketCommand #####################");
    }
    @SagaEventHandler(associationProperty = "orderId")
    public void on(NewTicketEvent evt){
        System.out.println("############################## handler #####################");
        TicketSummary ticketSummary = new TicketSummary(evt.getId(), evt.getOrderId(), evt.getTicketState(), evt.getRestaurantId(), evt.getConsumerId());
        entityManager.persist(ticketSummary);
        queryUpdateEmitter.emit(FindAllTicketsQuery.class,
                query -> true,
                ticketSummary);
        CheckConsumerIdCommand cmd = new CheckConsumerIdCommand(evt.getConsumerId(),evt.getOrderId(),evt.getId());
        System.out.println("############################## creato command Check #####################");
        String result = commandGateway.sendAndWait(cmd,3000, TimeUnit.MILLISECONDS);
        if(result == null){
            CancelTicketCommand cmd1 = new CancelTicketCommand(evt.getId(),evt.getOrderId());
            commandGateway.send(cmd1);
        }
        System.out.println("############################## inviato command Check #####################");

   }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void on(CancelTicketEvent evt){
        entityManager.remove(evt.getId());
        System.out.println("############################## cancellato #####################");

    }

}


