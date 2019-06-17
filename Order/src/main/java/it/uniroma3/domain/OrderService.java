package it.uniroma3.domain;

import it.uniroma3.commands.NewOrderCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
public class OrderService implements IOrderService {

    private final CommandGateway commandGateway;
    public OrderService(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> newOrder(NewOrderDTO newOrderDTO) {
        /*
         * usando il metodo send viene inviato un comando e
         * si mette in attesa di una risposta*/
        NewOrderCommand cmd = new NewOrderCommand(UUID.randomUUID().toString(), newOrderDTO.getConsumerId(), newOrderDTO.getRestaurantId(), newOrderDTO.getTicketId(), newOrderDTO.getLineItems()  );

        return commandGateway.send(cmd);

    }

}
