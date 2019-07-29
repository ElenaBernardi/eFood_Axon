package it.uniroma3.domain;

import it.uniroma3.FindAllOrdersQuery;
import it.uniroma3.commands.NewOrderCommand;
import it.uniroma3.commands.OrderInvalidCommand;
import it.uniroma3.web.NewOrderDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
public class OrderService implements IOrderService {
    @Autowired
    private CommandGateway commandGateway;
    @Autowired
    private QueryGateway queryGateway;

    @Override
    public CompletableFuture<String> newOrder(NewOrderDTO newOrderDTO) {
        NewOrderCommand cmd = new NewOrderCommand(UUID.randomUUID().toString(),
                newOrderDTO.getConsumerId(),
                newOrderDTO.getRestaurantId(),
                newOrderDTO.getLineItems()  );
        return commandGateway.send(cmd);
    }

    @Override
    public CompletableFuture<String> orderInvalid(String orderId, String ticketId) {
        OrderInvalidCommand cmd = new OrderInvalidCommand(orderId, ticketId);
        return commandGateway.send(cmd);

    }

    @Override
    public List<OrderSummary> findAll() {
        return queryGateway.query(
                new FindAllOrdersQuery(), ResponseTypes.multipleInstancesOf(OrderSummary.class)).join();
    }

}
