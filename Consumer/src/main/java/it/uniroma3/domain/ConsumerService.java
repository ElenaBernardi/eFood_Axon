package it.uniroma3.domain;

import it.uniroma3.commands.NewConsumerCommand;
import it.uniroma3.queries.FindAllConsumersQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ConsumerService implements IConsumerService {
    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private QueryGateway queryGateway;

    @Override
    public CompletableFuture<String> newConsumer(NewConsumerDTO newConsumerDTO){
        NewConsumerCommand cmd = new NewConsumerCommand(UUID.randomUUID().toString(), newConsumerDTO.getNome(), newConsumerDTO.getCognome());
        return commandGateway.send(cmd);
    }
    @Override
    public List<ConsumerSummary> findAll() {
        return queryGateway.query(
                new FindAllConsumersQuery(), ResponseTypes.multipleInstancesOf(ConsumerSummary.class)).join();
    }
}
