package it.uniroma3.domain;

import it.uniroma3.commands.NewTicketCommand;
import it.uniroma3.queries.FindAllTicketsQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class KitchenService implements IKitchenService{
    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private QueryGateway queryGateway;


    @Override
    public List<TicketSummary> findAll() {
        return queryGateway.query(
                new FindAllTicketsQuery(), ResponseTypes.multipleInstancesOf(TicketSummary.class)).join();
    }
}
