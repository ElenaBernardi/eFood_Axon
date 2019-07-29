package it.uniroma3.domain;

import it.uniroma3.FindAllOrdersQuery;
import it.uniroma3.commands.ConsumerApprovedCommand;
import it.uniroma3.commands.ConsumerDisapprovedCommand;
import it.uniroma3.commands.NewConsumerCommand;
import it.uniroma3.queries.FindAllConsumersQuery;
import it.uniroma3.queries.FindAllOrdersByConsumerId;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Tuple;
import java.util.ArrayList;
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
    public CompletableFuture<String> consumerApproved(String consumerId, String orderId, String ticketId){
        ConsumerApprovedCommand cmd = new ConsumerApprovedCommand(consumerId, ticketId, orderId);
        return commandGateway.send(cmd);
    }
    @Override
    public CompletableFuture<String> consumerDisapproved(String consumerId, String orderId, String ticketId){
        ConsumerDisapprovedCommand cmd = new ConsumerDisapprovedCommand(consumerId, ticketId, orderId);
        return commandGateway.send(cmd);
    }
    @Override
    public List<ConsumerSummary> findAll() {
        return queryGateway.query(
                new FindAllConsumersQuery(), ResponseTypes.multipleInstancesOf(ConsumerSummary.class)).join();
    }
    @Override
    public List<OrderSummary> findAllOrders() {
        return queryGateway.query(
                new FindAllOrdersQuery(), ResponseTypes.multipleInstancesOf(OrderSummary.class)).join();
    }
    @Override
    public List<String> findAllOrdersByConsumerId(String consumerId){
        List<Object[]> objects = queryGateway.query(new FindAllOrdersByConsumerId(consumerId), ResponseTypes.multipleInstancesOf(Object[].class)).join();
        List<String> results = new ArrayList<String>();
        for(Object[] object : objects){
            results.add("OrderId: "+object[0]+" , ConsumerId: "+object[1]);
        }
        return results;
    }

}
