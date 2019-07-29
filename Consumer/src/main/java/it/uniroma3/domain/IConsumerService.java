package it.uniroma3.domain;

import javax.persistence.Tuple;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface IConsumerService {
    public CompletableFuture<String> newConsumer(NewConsumerDTO newConsumerDTO);
    public List<ConsumerSummary> findAll() ;
    public List<OrderSummary> findAllOrders() ;
    public List<String> findAllOrdersByConsumerId(String consumerId) ;
    public CompletableFuture<String> consumerApproved(String consumerId, String ticketId, String orderId);
    public CompletableFuture<String> consumerDisapproved(String consumerId, String ticketId, String orderId);


    }
