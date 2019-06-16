package it.uniroma3.domain;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface IConsumerService {
    public CompletableFuture<String> newConsumer(NewConsumerDTO newConsumerDTO);
    public List<ConsumerSummary> findAll() ;
}
