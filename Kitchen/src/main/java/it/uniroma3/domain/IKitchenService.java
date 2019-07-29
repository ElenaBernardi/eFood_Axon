package it.uniroma3.domain;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface IKitchenService {
    public List<TicketSummary> findAll() ;

}
