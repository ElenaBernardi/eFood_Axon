package it.uniroma3.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
public interface IOrderService {

    public CompletableFuture<String> newOrder(NewOrderDTO newOrderDTO);
    public List<OrderSummary> findAll();

}
