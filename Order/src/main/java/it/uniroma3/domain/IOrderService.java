package it.uniroma3.domain;

import it.uniroma3.web.NewOrderDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@Transactional
public interface IOrderService {

    public CompletableFuture<String> newOrder(NewOrderDTO newOrderDTO);
    public List<OrderSummary> findAll();
    public CompletableFuture<String> orderInvalid(String orderId, String ticketId) ;



    }
