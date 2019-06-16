package it.uniroma3.domain;

import it.uniroma3.commands.NewTicketCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class KitchenService implements IKitchenService{
    @Autowired
    private CommandGateway commandGateway;

    @Override
    public CompletableFuture<String> newTicket(NewTicketDTO newTicketDTO){
        NewTicketCommand cmd = new NewTicketCommand(UUID.randomUUID().toString(), newTicketDTO.getOrderId(), newTicketDTO.getRestaurantId());
        return commandGateway.send(cmd);
    }
}
